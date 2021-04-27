package br.com.upgrade.exception;

public class SenhaInvalidaExpection extends RuntimeException {
    public SenhaInvalidaExpection(){
        super("Senha inválida");
    }
}
