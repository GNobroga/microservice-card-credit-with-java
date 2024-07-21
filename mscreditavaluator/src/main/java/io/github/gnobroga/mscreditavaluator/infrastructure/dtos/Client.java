package io.github.gnobroga.mscreditavaluator.infrastructure.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Client {
    private Long id;
    private String name;
}
