package io.github.gnobroga.mscards.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.gnobroga.mscards.entity.ClientCard;
import io.github.gnobroga.mscards.repository.ClientCardRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientCardService {
    
    private final ClientCardRepository repository;

    public List<ClientCard> listByDocument(String value) {
        return repository.findByDocument(value);
    }
}
