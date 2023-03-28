package sk.stuba.fei.uim.oop.card;

import sk.stuba.fei.uim.oop.player.Player;

public class Prison extends Card {
    private static final String CARD_NAME = "Prison";

    public Prison(Player[] players) {
        super(CARD_NAME, players);
    }

    @Override
    public void checkEffect(Player player) {
        super.checkEffect(player);
        int randomNumber = (int) (Math.random() * 4);
        if (randomNumber != 3) {
            player.setInPrison(true);
            System.out.println(player.getName() + ": I am in prison:(");
        } else {
            player.setInPrison(false);
            System.out.println(player.getName() + ": I escaped the prison!");
        }
        for (Card card : player.getCardsInFront()) {
            if (card instanceof Prison) {
                player.setCardsToThrow(card);
                player.getCardsInFront().remove(card);
                break;
            }
        }
    }

    @Override
    public void playCard(Player player) {
        super.playCard(player);
        int number = 0;
        while (true) {
            int counter = 0;
            number = this.choosingPlayerToAttack(player);
            if (number == -1) {
                player.setPlayerWantDiffrentCard(true);
                return;
            }
            for (Card card : players[number].getCardsInFront()) {
                if (card instanceof Prison) {
                    counter++;
                    System.out.println("this player have this card in front of him already");
                }
            }
            if (counter == 0) {
                break;
            }
        }
        players[number].addCardToFront(this);
    }
}
