package sk.stuba.fei.uim.oop.deck;

import sk.stuba.fei.uim.oop.card.*;
import sk.stuba.fei.uim.oop.player.Player;


import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> deck;

    public Deck() {
        deck = new ArrayList<Card>();
    }

    public Deck(Player[] players) {
        deck = new ArrayList<Card>();
        deck.add(new Barrel());
        deck.add(new Barrel());
        deck.add(new Dynamite(players));
        for (int i = 0; i < 3; i++) {
            deck.add(new Prison(players));
        }
        for (int i = 0; i < 30; i++) {
            deck.add(new Bang(players));
        }
        for (int i = 0; i < 15; i++) {
            deck.add(new Missed());
        }
        for (int i = 0; i < 8; i++) {
            deck.add(new Beer());
        }
        for (int i = 0; i < 6; i++) {
            deck.add(new CatBalou(players));
        }
        for (int i = 0; i < 4; i++) {
            deck.add(new Stagecoach(this));
        }
        deck.add(new Indians(players));
        deck.add(new Indians(players));
        Collections.shuffle(deck);
    }

    public void add(Card card) {
        deck.add(card);
    }

    public Card getCard() {
        return this.deck.get(0);
    }

    public void removeCard() {
        this.deck.remove(0);
    }

    public int size() {
        return deck.size();
    }

    private void clearDeck() {
        this.deck.clear();
    }

    public void newDeck(Deck deckOfUsed) {
        if (deck.isEmpty()) {
            deck.addAll(deckOfUsed.deck);
            Collections.shuffle(deck);
            deckOfUsed.clearDeck();
        }
    }
}

