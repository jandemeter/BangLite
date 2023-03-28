package sk.stuba.fei.uim.oop.card;

import sk.stuba.fei.uim.oop.player.Player;

public class Barrel extends Card {
    private static final String CARD_NAME = "Barrel";

    public Barrel() {
        super(CARD_NAME);
    }

    @Override
    public void playCard(Player player) {
        super.playCard(player);
        player.getCardsInFront().add(this);
    }

}
