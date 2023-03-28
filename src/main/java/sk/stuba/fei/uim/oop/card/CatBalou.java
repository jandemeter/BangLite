package sk.stuba.fei.uim.oop.card;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

public class CatBalou extends Card {
    private static final String CARD_NAME = "CatBalou";

    public CatBalou(Player[] players) {
        super(CARD_NAME, players);
    }

    @Override
    public void playCard(Player player) {
        super.playCard(player);
        int number = this.choosingPlayerToAttack(player);
        if (number == -1) {
            player.setPlayerWantDiffrentCard(true);
            return;
        }

        if (players[number].getCards().size() == 0 && players[number].getCardsInFront().size() == 0) {
            System.out.println("You cant play this card on this player ");
            return;
        }
        int num;
        while (true) {
            num = -2;
            while (num < 0 || num > 1) {
                System.out.println("0. Cards in hand");
                System.out.println("1. Cards in the front");
                num = ZKlavesnice.readInt("From where do you want to take players card?");
            }
            if (players[number].getCards().size() == 0 && num == 0) {
                System.out.println("There is nothing to take");
            } else if (players[number].getCardsInFront().size() == 0 && num == 1) {
                System.out.println("There is nothing to take");
            } else {
                break;
            }
        }
        if (num == 0) {
            int randomNumber = (int) (Math.random() * players[number].getCards().size());
            players[number].setCardsToThrow(players[number].getCards().get(randomNumber));
            System.out.println(players[number].getName() + ": throw " + (players[number].getCards().get(randomNumber).getName()));
            players[number].getCards().remove(randomNumber);
        } else {
            int randomNumber = (int) (Math.random() * players[number].getCardsInFront().size());
            players[number].setCardsToThrow(players[number].getCardsInFront().get(randomNumber));
            System.out.println(players[number].getName() + ": throw " + (players[number].getCardsInFront().get(randomNumber).getName()));
            players[number].getCardsInFront().remove(randomNumber);
        }
        for (Card card : player.getCards()) {
            if (card instanceof CatBalou) {
                player.setCardsToThrow(card);
                player.getCards().remove(card);
                break;
            }
        }
    }
}
