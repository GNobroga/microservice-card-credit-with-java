package io.github.gnobroga.mscreditavaluator.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Client {
    private Long id;
    private String name;
    private Integer age;
}
