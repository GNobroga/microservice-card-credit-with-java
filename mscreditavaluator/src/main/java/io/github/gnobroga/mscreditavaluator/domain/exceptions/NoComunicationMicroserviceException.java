package io.github.gnobroga.mscreditavaluator.domain.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoComunicationMicroserviceException extends NoStackTraceException {

    private Integer status;

    public NoComunicationMicroserviceException(String message, Integer status) {
        super(message);
    }
    
}
