package sk.stuba.fei.uim.oop.player;

import sk.stuba.fei.uim.oop.card.*;
import sk.stuba.fei.uim.oop.deck.Deck;

import java.util.ArrayList;

public class Player {
    private final String name;
    protected int health;
    private boolean alive;
    private boolean inPrison;
    private ArrayList<Card> cards;
    private ArrayList<Card> cardsToTrow;
    private ArrayList<Card> cardsInFront;
    private boolean playerWantDiffrentCard;

    public Player(String name) {
        this.name = name;
        this.health = 4;
        this.alive = true;
        this.inPrison = false;
        this.cards = new ArrayList<Card>();
        this.cardsToTrow = new ArrayList<Card>();
        this.cardsInFront = new ArrayList<Card>();
        this.playerWantDiffrentCard = false;

    }

    public boolean getPlayerWantDiffrentCard() {
        return playerWantDiffrentCard;
    }

    public void setPlayerWantDiffrentCard(boolean playerWantDiffrentCard) {
        this.playerWantDiffrentCard = playerWantDiffrentCard;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setInPrison(boolean inPrison) {
        this.inPrison = inPrison;
    }

    public boolean isInPrison() {
        return this.inPrison;
    }

    public void setCardsToThrow(Card cardToThrow) {
        this.cardsToTrow.add(cardToThrow);
    }

    public Card getCardsToThrow() {
        Card card = this.cardsToTrow.get(0);
        this.cardsToTrow.clear();
        return card;
    }

    public String getName() {
        return this.name;
    }

    public int getHealth() {
        return this.health;
    }

    public ArrayList<Card> getCards() {
        return this.cards;
    }

    public ArrayList<Card> getCardsInFront() {
        return this.cardsInFront;
    }

    public boolean isGetCardsToThrowEmpty() {
        return this.cardsToTrow.isEmpty();
    }

    public void healthMinus() {
        this.health--;
    }

    public void healthPlus() {
        this.health++;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void addCardToFront(Card card) {
        cardsInFront.add(card);
    }

    public void printCards() {
        int i = 0;
        for (Card card : cards) {
            System.out.println(i + ". " + card.getName());
            i++;
        }
    }

    public boolean checkIfMissed() {
        for (Card card : cards) {
            if (card instanceof Missed) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfBang() {
        for (Card card : cards) {
            if (card instanceof Bang) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfBarrel() {
        for (Card card : cardsInFront) {
            if (card instanceof Barrel) {
                return true;
            }
        }
        return false;
    }

    public void checkEffectOfBlueCard() {
        if (this.cardsInFront.size() == 0) {
            return;
        }
        for (Card card : cardsInFront) {
            if (card instanceof Dynamite) {
                card.checkEffect(this);
                return;
            }
        }
        for (Card card : cardsInFront) {
            if (card instanceof Prison) {
                card.checkEffect(this);
                return;
            }
        }
    }

    public void throwCard(Card card, Deck deckOfUsed) {
        deckOfUsed.add(card);
    }

    public void throwCardThirdPart(Player player, Deck deckOfUsed) {
        while (player.getHealth() < player.getCards().size()) {
            int max = player.getCards().size();
            int randomNumber = (int) (Math.random() * max);
            deckOfUsed.add(player.cards.get(randomNumber));
            player.cards.remove(randomNumber);
        }
    }


}
