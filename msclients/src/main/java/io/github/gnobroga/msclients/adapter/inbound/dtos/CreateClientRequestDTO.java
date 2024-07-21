package io.github.gnobroga.msclients.adapter.inbound.dtos;

import io.github.gnobroga.msclients.domain.entities.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateClientRequestDTO {

    private String name;

    private String cpf;

    private Integer age;

    public Client toClient() {
        return new Client(name, cpf, age);
    }
}