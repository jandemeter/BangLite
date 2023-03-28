package sk.stuba.fei.uim.oop.card;

import sk.stuba.fei.uim.oop.player.Player;

public class Dynamite extends Card {
    private static final String CARD_NAME = "Dynamite";

    public Dynamite(Player[] players) {
        super(CARD_NAME, players);
    }

    @Override
    public void checkEffect(Player player) {
        super.checkEffect(player);
        int randomNumber = (int) (Math.random() * 8);
        if (randomNumber == 7) {
            player.healthMinus();
            player.healthMinus();
            player.healthMinus();
            System.out.println(player.getName() + ": Dynamite exploded");
            for (Card card : player.getCardsInFront()) {
                if (card instanceof Dynamite) {
                    player.setCardsToThrow(card);
                    player.getCardsInFront().remove(card);
                    break;
                }
            }
        } else {
            System.out.println(player.getName() + ": Dynamite doesnt explode");
            int playerNum = 0;
            for (int i = 0; i < players.length; i++) {
                if (player.equals(players[i])) {
                    playerNum = i;
                    break;
                }
            }
            while (true) {
                if (playerNum == 0) {
                    playerNum = players.length;
                }
                playerNum--;
                if (players[playerNum].isAlive()) {
                    for (Card card : player.getCardsInFront()) {
                        if (card instanceof Dynamite) {
                            players[playerNum].addCardToFront(card);
                            player.getCardsInFront().remove(card);
                            break;
                        }
                    }
                    break;
                }
            }

        }

    }

    @Override
    public void playCard(Player player) {
        super.playCard(player);
        player.getCardsInFront().add(this);
    }
}


