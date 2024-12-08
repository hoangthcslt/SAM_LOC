package com.example.sam_loc_2;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private GameLogger logger;
    public static List<Player> players;
    private int currentPlayerIndex;
    private List<Card> cardsInTable; // Lưu bài của lượt trước
    private final GameLogic gameLogic;

    public GameManager(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
        this.logger = new GameLogger();
        this.currentPlayerIndex = 0;
    }

    public GameManager(List<Player> players) {
        this.players = players;
        this.currentPlayerIndex = 0;
        this.cardsInTable = new ArrayList<>();
        this.gameLogic = new GameLogic();
    }

    // Thêm người chơi AI vào game
    public static void addAIPlayer(AIPlayer aiPlayer) {
        players.add(aiPlayer);
    }
}

