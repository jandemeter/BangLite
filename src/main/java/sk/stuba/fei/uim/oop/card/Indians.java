package sk.stuba.fei.uim.oop.card;

import sk.stuba.fei.uim.oop.player.Player;

public class Indians extends Card {
    private static final String CARD_NAME = "Indians";

    public Indians(Player[] players) {
        super(CARD_NAME, players);
    }

    @Override
    public void playCard(Player player) {
        super.playCard(player);
        for (Player p : players) {
            if (p == player) {
                continue;
            } else if (p.isAlive()) {
                if (!p.checkIfBang()) {
                    p.healthMinus();
                    System.out.println("Indians took 1 health from player " + p.getName() + " and he has " + p.getHealth() + " health");
                } else {
                    for (Card card : p.getCards()) {
                        if (card instanceof Bang) {
                            p.setCardsToThrow(card);
                            p.getCards().remove(card);
                            System.out.println(p.getName() + ": Not this time!");
                            break;
                        }
                    }
                }
            }
        }
    }
}
