package sk.stuba.fei.uim.oop.card;

import org.w3c.dom.ls.LSOutput;
import sk.stuba.fei.uim.oop.deck.Deck;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

public abstract class Card {
    protected String name;
    protected Deck deck;
    protected Player[] players;

    public Card() {
    }

    public Card(String name) {
        this.name = name;

    }

    public Card(String name, Player[] players) {
        this.name = name;
        this.players = players;
    }

    public Card(String name, Deck deck) {
        this.name = name;
        this.deck = deck;
    }

    public String getName() {
        return this.name;
    }

    public void playCard(Player player) {
        System.out.println("--- " + player.getName() + " choose " + this.name + " card to play. ---");
    }

    public void checkEffect(Player player) {
        System.out.println("--- " + player.getName() + " has " + this.name + " in front of him ---");
    }

    protected int choosingPlayerToAttack(Player player) {
        int i = 0;
        for (Player p : players) {
            if (p.isAlive()) {
                System.out.println("Player " + i + " " + p.getName());
            }
            i++;
        }
        int numberOfPlayer = 0;
        while (true) {
            numberOfPlayer = ZKlavesnice.readInt("Enter number of player you want to use card on, if you want to choose diffrent card enter -1 : ");
            if (numberOfPlayer < -1 || numberOfPlayer > players.length - 1) {
                System.out.println("!!! You entered an invalid player number. Try again!");
            } else if (numberOfPlayer == -1) {
                return numberOfPlayer;
            } else if (players[numberOfPlayer] == player) {
                System.out.println("Careful, you are aiming at yourself!");
            } else if (!players[numberOfPlayer].isAlive()) {
                System.out.println("Player is dead, leave him!");
            } else {
                break;
            }
        }
        return numberOfPlayer;
    }

}
