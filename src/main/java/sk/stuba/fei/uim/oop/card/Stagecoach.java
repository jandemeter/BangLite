package sk.stuba.fei.uim.oop.card;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.deck.Deck;

public class Stagecoach extends Card {
    private static final String CARD_NAME = "Stagecoach";
    private Deck deck;

    public Stagecoach(Deck deck) {
        super(CARD_NAME);
        this.deck = deck;
    }


    @Override
    public void playCard(Player player) {
        super.playCard(player);
        for (int i = 0; i < 2; i++) {
            player.addCard(this.deck.getCard());
            deck.removeCard();
        }
    }
}
