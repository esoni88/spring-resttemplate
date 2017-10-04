package it.italiancoders.frongillo.resttemplate.model;

public class HelloMessage {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HelloMessage(String message){
        this.message = message;
    }

    public HelloMessage(){}

    @Override
    public String toString() {
        return "HelloMessage{" +
                "message='" + message + '\'' +
                '}';
    }
}
