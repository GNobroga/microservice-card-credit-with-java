package io.github.gnobroga.mscreditavaluator.domain.exceptions;

public class CardIssuanceErrorException extends NoStackTraceException {

    public CardIssuanceErrorException(String message) {
        super(message);
    }
    
}
