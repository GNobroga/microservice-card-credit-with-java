package io.github.gnobroga.msclients.adapter.inbound.dtos;

import io.github.gnobroga.msclients.domain.entities.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ClientResponseDTO {
    
    private Long id;

    private String name;

    private String cpf;

    private Integer age;

    public static ClientResponseDTO from(Client source) {
        return ClientResponseDTO.builder()
            .id(source.getId())
            .name(source.getName())
            .cpf(source.getCpf().getValue())
            .age(source.getAge())
            .build();
    }
}
