package br.com.ezblue.ezclientservices.controller.exception;

public class ControlerNotFoundException extends RuntimeException{

    public ControlerNotFoundException(String message){
        super(message);
    }

}
