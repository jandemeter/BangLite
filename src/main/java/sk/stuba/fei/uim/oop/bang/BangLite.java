package sk.stuba.fei.uim.oop.bang;

import sk.stuba.fei.uim.oop.card.*;
import sk.stuba.fei.uim.oop.deck.Deck;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;


public class BangLite {
    private final Player[] players;
    private Deck deck;
    private Deck deckOfUsed;

    public BangLite() {
        System.out.println("Welcome to Bang Lite");
        int numberPlayers = 0;
        while (numberPlayers < 2 || numberPlayers > 4) {
            numberPlayers = ZKlavesnice.readInt("Enter number of players between 2-4: ");
        }
        this.players = new Player[numberPlayers];
        for (int i = 0; i < numberPlayers; i++) {
            this.players[i] = new Player(ZKlavesnice.readString("Enter PLAYER " + i + " name:"));
        }
        this.deck = new Deck(players);
        this.deckOfUsed = new Deck();
        startGame();
    }

    private Player[] getPlayers() {
        return this.players;
    }

    private void startGame() {
        System.out.println("--- GAME STARTED ---");
        for (Player player : players) {
            for (int j = 0; j < 4; j++) {
                player.addCard(deck.getCard());
                deck.removeCard();
            }
        }

        int i = 0;
        while (getNumberActivePlayers() > 1) {
            Player currentPlayer = players[i];
            while (!currentPlayer.isAlive()) {
                if (i + 1 == players.length) {
                    i = 0;
                }
                i++;
                currentPlayer = players[i];
            }
            this.makeTurn(currentPlayer);
            i++;
            if (i == players.length) {
                i = 0;
            }
        }
    }

    private void makeTurn(Player player) {
        System.out.println("---------------------------");
        System.out.println("Player " + player.getName() + " started his round");
        System.out.println("Player lives: " + player.getHealth());
        player.checkEffectOfBlueCard();
        if (player.isInPrison()) {
            player.setInPrison(false);
            return;
        }
        for (int i = 0; i < 2; i++) {
            if (deck.size() < 3) {
                deck.newDeck(deckOfUsed);
            }
            player.addCard(deck.getCard());
            deck.removeCard();
        }

        while (true) {
            if (player.getHealth() < 1) {
                this.checkIfDead();
                if (this.getNumberActivePlayers() == 1) {
                    this.getWinner();
                }
                break;
            }
            this.checkIfDead();
            if (this.getNumberActivePlayers() == 1) {
                this.getWinner();
                break;
            }
            player.printCards();
            int chosenCard = this.chooseCard(player);
            if (chosenCard == -1) {
                System.out.println("Player doesnt choose any card");
                break;
            } else {
                playCard(chosenCard, player);
            }

        }
        if (player.getHealth() < player.getCards().size() && player.isAlive()) {
            player.throwCardThirdPart(player, deckOfUsed);
        }
    }

    private int chooseCard(Player player) {
        int numberOfCard = 0;
        while (true) {
            numberOfCard = ZKlavesnice.readInt("Enter number of card you want to use, if none enter -1: ");
            if (numberOfCard < -1 || numberOfCard > player.getCards().size() - 1) {
                System.out.println("!!! You entered an invalid card number. Try again!");
            } else if (numberOfCard != -1 && player.getCards().get(numberOfCard) instanceof Missed) {
                System.out.println("You cant use this card");
            } else {
                break;
            }
        }
        return numberOfCard;
    }

    private void playCard(int number, Player player) {
        int cardsBefore = player.getCards().size();
        for (Card card : player.getCardsInFront()) {
            if (card instanceof Barrel && player.getCards().get(number) instanceof Barrel) {
                System.out.println("You have barrel in front of you already!");
                return;
            }
        }

        player.getCards().get(number).playCard(player);
        if (player.getPlayerWantDiffrentCard()) {
            System.out.println("player want to play diffrent card");
            player.setPlayerWantDiffrentCard(false);
            return;
        }
        int cardsAfter = player.getCards().size();
        if (cardsBefore == cardsAfter || player.getCards().get(number) instanceof Stagecoach) {
            if (!(player.getCards().get(number) instanceof Prison) && !(player.getCards().get(number) instanceof Dynamite) && !(player.getCards().get(number) instanceof CatBalou)) {
                player.throwCard(player.getCards().get(number), deckOfUsed);
                player.getCards().remove(number);
            } else if (player.getCards().get(number) instanceof Prison || player.getCards().get(number) instanceof Dynamite) {
                player.getCards().remove(number);
            }
        }
        for (Player p : getPlayers()) {
            if (!p.isGetCardsToThrowEmpty()) {
                p.throwCard(p.getCardsToThrow(), deckOfUsed);
            }
        }
    }

    private int getNumberActivePlayers() {
        int count = 0;
        for (Player player : this.players) {
            if (player.isAlive()) {
                count++;
            }
        }
        return count;
    }

    private void getWinner() {
        Player winner = null;
        for (Player player : this.players) {
            if (player.isAlive()) {
                winner = player;
            }
        }
        System.out.println("--- GAME FINISHED ---");
        System.out.println("And the WINNER is " + winner.getName());
        System.exit(0);
    }

    private void checkIfDead() {
        int i = 0;
        for (Player player : players) {
            if (player.getHealth() <= 0) {
                if (player.getCards().size() != 0) {
                    for (Card card : player.getCards()) {
                        deckOfUsed.add(card);
                    }
                    player.getCards().clear();
                }
                System.out.println("!!! " + "player " + player.getName() + " is dead !!!");
                player.setAlive(false);
            }
        }
    }
}
