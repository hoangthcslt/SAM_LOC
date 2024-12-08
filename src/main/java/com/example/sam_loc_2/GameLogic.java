package com.example.sam_loc_2;

import java.util.List;
import java.util.stream.Collectors;

public class GameLogic {
    // Kiểm tra tính hợp lệ của bài được chọn để đánh
    // Kiểm tra có phải dây không
    public static boolean isValidSequence(List<Card> cards) {
        // Logic kiểm tra dãy bài phức tạp hơn
        List<Integer> rankValues = cards.stream()
                .map(Card::getRankSequence)
                .sorted()
                .collect(Collectors.toList());
        // Kiểm tra tính liên tục của rank
        for (int i = 1; i < rankValues.size(); i++) {
            if (rankValues.get(i) - rankValues.get(i - 1) != 1) {
                return false;
            }
        }
        return true;
    }

    //Kiểm tra xem bài có hợp lệ để đánh lượt đầu 1 turn không
    public static boolean isValidMove(List<Card> cards) {
        if (cards.size() == 1) {
            return true;
        } else {
            if (isValidSequence(cards) && cards.size() > 2) {
                return true;
            } else {
                boolean Rank = cards.stream()
                        .allMatch(card -> card.getRank().equals(cards.get(0).getRank()));
                if (Rank) return true;
            }
        }
        return false;
    }
    //Kiểm tra có đánh được bài người trước không

    public static boolean canBeatPreviousMove(List<Card> previousMove, List<Card> currentMove) {
        // Kiểm tra nếu không có nước đi trước
        if (previousMove == null || previousMove.isEmpty()) return true;

        if (previousMove.size() == 1 && currentMove.size() == 1) {

            return currentMove.get(0).getRankValue() > previousMove.get(0).getRankValue();

        }

        // Trường hợp 1: Lá "2" đặc biệt
        if (previousMove.size() == 1 && previousMove.get(0).getRank().equals("2")) {
            return currentMove.size() == 4 &&
                    currentMove.stream().allMatch(card -> card.getRank().equals(currentMove.get(0).getRank()));
        }

        // Kiểm tra số lượng lá bài
        if (currentMove.size() != previousMove.size()) return false;

        // Trường hợp 2.1: Các lá cùng rank
        boolean prevSameRank = previousMove.stream()
                .allMatch(card -> card.getRank().equals(previousMove.get(0).getRank()));

        if (prevSameRank) {
            boolean currentSameRank = currentMove.stream()
                    .allMatch(card -> card.getRank().equals(currentMove.get(0).getRank()));

            return currentSameRank &&
                    currentMove.get(0).getRankValue() > previousMove.get(0).getRankValue();
        }

        // Trường hợp 2.2: Kiểm tra dãy bài
        boolean prevIsSequence = isValidSequence(previousMove);

        if (prevIsSequence) {
            // Kiểm tra current move có phải dãy bài không
            if (!isValidSequence(currentMove)) return false;

            // So sánh rank của dãy bài
            return currentMove.get(0).getRankSequence() > previousMove.get(0).getRankSequence();
        }

        // Các trường hợp khác
        return false;
    }
}

