package sk.stuba.fei.uim.oop.card;

import sk.stuba.fei.uim.oop.player.Player;

public class Bang extends Card {
    private static final String CARD_NAME = "Bang";

    public Bang(Player[] players) {
        super(CARD_NAME, players);
    }

    @Override
    public void playCard(Player player) {
        super.playCard(player);
        int barrelProtect = 0;
        int number = this.choosingPlayerToAttack(player);
        if (number == -1) {
            player.setPlayerWantDiffrentCard(true);
            return;
        }
        if (players[number].getCardsInFront().size() > 0) {
            if (players[number].checkIfBarrel()) {
                int randomNumber = (int) (Math.random() * 4);
                if (randomNumber == 3) {
                    System.out.println(players[number].getName() + ": Barrel took it :P");
                    barrelProtect = 1;
                } else {
                    System.out.println(players[number].getName() + ": Barrel didnt take it:(");
                }
            }
        }

        if (!players[number].checkIfMissed() && barrelProtect == 0) {
            players[number].healthMinus();
            System.out.println("Player " + players[number].getName() + " has been shot and has " + players[number].getHealth() + " health");
        } else if (barrelProtect == 0) {
            for (Card card : players[number].getCards()) {
                if (card instanceof Missed) {
                    players[number].setCardsToThrow(card);
                    players[number].getCards().remove(card);
                    System.out.println(players[number].getName() + ": You missed!");
                    break;
                }
            }
        }
    }
}