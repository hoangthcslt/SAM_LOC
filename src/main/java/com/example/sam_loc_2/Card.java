package com.example.sam_loc_2;

import java.util.Objects;

public class Card {
    private String rank; // 2, 3, ..., 10, Jack, Queen, King, Ace
    private String suit;// Hearts, Clubs, Diamonds, Spades

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public enum Rank {
        THREE(1), FOUR(2), FIVE(3), SIX(4), SEVEN(5),
        EIGHT(6), NINE(7), TEN(8), JACK(9), QUEEN(10),
        KING(11), ACE(12), TWO(13);

        private final int value;

        Rank(int value) {
            this.value = value;
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return rank == card.rank && suit == card.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }

    // Getter for rank
    public String getRank() {
        return rank;
    }

    // Getter for suit
    public String getSuit() {
        return suit;
    }

    // Method to get a numerical value of rank for easier comparison
    public int getRankValue() {
        switch (rank) {
            case "2":
                return 13; // Highest value in Sam Loc
            case "Ace":
                return 12;
            case "King":
                return 11;
            case "Queen":
                return 10;
            case "Jack":
                return 9;
            case "10":
                return 8;
            case "9":
                return 7;
            case "8":
                return 6;
            case "7":
                return 5;
            case "6":
                return 4;
            case "5":
                return 3;
            case "4":
                return 2;
            case "3":
                return 1;
            default:
                return 0; // Invalid rank
        }
    }

    public int getRankSequence() {
        switch (rank) {
            case "2":
                return 1;
            case "3":
                return 2;
            case "4":
                return 3;
            case "5":
                return 4;
            case "6":
                return 5;
            case "7":
                return 6;
            case "8":
                return 7;
            case "9":
                return 8;
            case "10":
                return 9;
            case "Jack":
                return 10;
            case "Queen":
                return 11;
            case "King":
                return 12;
            case "Ace":
                return 13;
            default:
                return 0;
        }
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}


