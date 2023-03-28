package sk.stuba.fei.uim.oop.card;

import sk.stuba.fei.uim.oop.player.Player;

public class Missed extends Card {
    private static final String CARD_NAME = "Missed";

    public Missed() {
        super(CARD_NAME);
    }

    @Override
    public void playCard(Player player) {
        System.out.println("This card is played automatically");
    }
}
