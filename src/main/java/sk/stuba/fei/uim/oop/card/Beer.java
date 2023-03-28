package sk.stuba.fei.uim.oop.card;

import sk.stuba.fei.uim.oop.player.Player;

public class Beer extends Card {
    private static final String CARD_NAME = "Beer";

    public Beer() {
        super(CARD_NAME);
    }


    @Override
    public void playCard(Player player) {
        super.playCard(player);
        player.healthPlus();
        System.out.println(player.getName() + ": +1 health");
    }
}
