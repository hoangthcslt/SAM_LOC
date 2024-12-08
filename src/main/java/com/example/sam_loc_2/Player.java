package com.example.sam_loc_2;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name; // Tên người chơi
    private List<Card> hand;
    private List<Card> selectedCards = new ArrayList<>();// Các lá bài trong tay

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    // Thêm 1 lá bài vào tay
    public void addCard(Card cards) {
        this.hand.add(cards);
    }

    public void selectCard(Card card) {
        selectedCards.add(card);
    }

    public void deselectCard(Card card) {
        selectedCards.remove(card);
    }

    public void removeCards(List<Card> cards) {
        hand.removeAll(cards); // Giả định "hand" là danh sách các lá bài trong tay
    }

    public List<Card> getSelectedCards() {
        // Trả về danh sách các lá bài đã được chọn
        return selectedCards; // Giả định bạn có biến `selectedCards` trong lớp Player
    }

    // Xóa bài đã đánh
    public void removePlayedCards(List<Card> playedCards) {
        hand.removeAll(playedCards);
    }

    // Đếm bài còn lại
    public int countCards() {
        return hand.size();
    }

    // Getter cho tên người chơi
    public String getName() {
        return name;
    }

    // Getter cho các lá bài trong tay
    public List<Card> getHand() {
        return hand;
    }

    public List<Card> getCards() {
        return hand;
    }
}

