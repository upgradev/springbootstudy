package br.com.upgrade.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.upgrade.jwt.security.JwtAuthFilter;
import br.com.upgrade.jwt.security.JwtService;
import br.com.upgrade.service.impl.UsuarioServiceImpl;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UsuarioServiceImpl usuarioService; 

    @Autowired
    private JwtService jwtService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }

    @Bean
    public OncePerRequestFilter jwtFilter(){
        return new JwtAuthFilter(jwtService, usuarioService);
    }

    // autenticacao
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // TODO Auto-generated method stub
        auth.userDetailsService(usuarioService)
        .passwordEncoder(passwordEncoder());
        // auth.inMemoryAuthentication().passwordEncoder(passwordEncoder()).withUser("fulano")
        //         .password(passwordEncoder().encode("123")).roles("USER", "ADMIN");
    }

    // autorizacao atraves da autenticacao do usuario, se tem autorizacao a acessar
    // determinadas coisas
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO Auto-generated method stub
        http.csrf().disable() // permite que tenha uma segurança entre api web e o back
                .authorizeRequests()
                .antMatchers("/api/clientes/**") // quem acessa o que no sistema
                .hasAnyRole("USER", "ADMIN")

                .antMatchers("/api/pedidos/**")
                .hasAnyRole("USER", "ADMIN")
                
                .antMatchers("/api/produtos/**")
                .hasAnyRole("ADMIN")
                
                .antMatchers(HttpMethod.POST, "/api/usuarios/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                
                .and() //volta para a raiz do objeto, o http nesse caso
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
                // .httpBasic();
                // .formLogin(); //formulario padrao ou se quiser pode fazer seu formulario

    }

}