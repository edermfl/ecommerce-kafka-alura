package br.com.alura.ecommerce.model;

public class EmailDTO {
    private final String subject, body;

    public EmailDTO(String subject, String body) {
        this.subject = subject;
        this.body = body;
    }
}
