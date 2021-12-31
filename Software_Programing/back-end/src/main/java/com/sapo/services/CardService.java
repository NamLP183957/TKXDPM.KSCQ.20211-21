package com.sapo.services;

import com.sapo.entities.Card;
import org.springframework.stereotype.Service;

@Service
public interface CardService {

    public Card saveCard(Card card);
}
