package com.example.sam_loc_2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        String[] suits = {"Hearts", "Clubs", "Diamonds", "Spades"};
        for (String rank : ranks) {
            for (String suit : suits) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    // Shuffle the deck
    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card dealOneCard() {
        if (!this.cards.isEmpty()) {
            return this.cards.remove(0);  // Xóa lá bài đầu tiên trong danh sách
        }
        return null;
    }
}

