package com.baroque.game;

import com.baroque.objects.Player;

import java.util.Arrays;
import java.util.List;

public class PlayerManager {

    public enum PLAYER_COLOR {RED, ORANGE, BLUE, BLACK, GREEN}

    public final static List<Integer> FEALTY_CARDS = Arrays.asList(1, 2, 2, 3, 3, 4);
    public final static List<Integer> ENLIGHTENMENT_ACTIONS = Arrays.asList(2, 3, 3, 4, 4, 5);
    List<Player> players;
    Player host;

    public PlayerManager() {

    }

    public void addPlayer(String name) {
        if (players.size() < 5) {
            players.add(new Player(name, players.size()));
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getPlayer(int i) {
        return players.get(i);
    }

}
