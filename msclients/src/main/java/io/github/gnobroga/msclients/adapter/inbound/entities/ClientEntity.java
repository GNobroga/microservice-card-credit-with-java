package io.github.gnobroga.msclients.adapter.inbound.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.github.gnobroga.msclients.domain.entities.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clients")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpf;

    private String name;

    private Integer age;

    public Client toClient() {
        return new Client(id, name, cpf, age);
    }

    public static ClientEntity from(Client source) {
        return new ClientEntity(
            source.getId(), 
            source.getCpf().getValue(), 
            source.getName(), 
            source.getAge()
        );
    }
}
