package io.github.gnobroga.msclients.domain.entities;

import java.util.Objects;

import io.github.gnobroga.msclients.domain.valueObject.CPF;
import lombok.Data;

@Data
public class Client {
    
    private Long id;

    private CPF cpf;

    private String name;

    private Integer age;

    public Client(Long id, String name, String cpf, Integer age) {
        this.id = id;
        this.name = name;
        this.cpf = new CPF(cpf);
        this.age = age;
        this.validate();
    }

    public Client(String name, String cpf, Integer age) {
        this.name = name;
        this.cpf = new CPF(cpf);
        this.age = age;
        this.validate();
    }

    public void validate() {
        if (Objects.isNull(cpf)) {
            throw new IllegalArgumentException("The cpf is required.");
        }

        if (Objects.isNull(name) || name.isBlank()) {
            throw new IllegalArgumentException("The name is required.");
        }

        if (Objects.isNull(age)) {
            throw new IllegalArgumentException("The age is required.");
        }

        if (age < 1 || age > 120) {
            throw new IllegalArgumentException("The age must be greater than one and less than 120.");
        }
    }

    
}
