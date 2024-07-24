package io.github.gnobroga.mscreditavaluator.domain.exceptions;

public class ClientNotFoundException extends NoStackTraceException {

    public ClientNotFoundException() {
        super("Dados do cliente não encontrado para o CPF informado.");
    }
    
}
