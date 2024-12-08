package com.example.sam_loc_2;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AIPlayer extends Player {
    private GameLogic gameLogic; // Thêm trường GameLogic

    // Constructor mới
    public AIPlayer(String name) {
        super(name);
        this.gameLogic = new GameLogic(); // Khởi tạo GameLogic
    }

    // Phương thức chính để chọn nước đi
    public List<Card> chooseMove(List<Card> lastMoveCards, GameLogic gameLogic) {
        List<Card> hand = getHand(); // Lấy bài trên tay

        // Trường hợp 1: Không có nước đi trước - đánh bài nhỏ nhất
        if (lastMoveCards == null || lastMoveCards.isEmpty()) {
            return playSmallestCards(1);
        }

        // Trường hợp 2: Tìm nước đi hợp lệ để đánh
        List<Card> validMove = findValidMove(lastMoveCards);

        if (validMove != null) {
            return validMove;
        }

        // Trường hợp 3: Không thể đánh - trả về null (bỏ lượt)
        return null;
    }

    // Tìm nước đi hợp lệ để đánh
    private List<Card> findValidMove(List<Card> lastMoveCards) {
        // Nhóm bài theo rank
        Map<String, List<Card>> cardsByRank = getHand().stream()
                .collect(Collectors.groupingBy(Card::getRank));

        // 1. Đánh bài cùng số lượng và rank cao hơn
        for (List<Card> rankGroup : cardsByRank.values()) {
            if (rankGroup.size() == lastMoveCards.size()) {
                // Kiểm tra xem có thể đánh được không
                if (gameLogic.canBeatPreviousMove(lastMoveCards, rankGroup)) {
                    return rankGroup;
                }
            }
        }

        // 2. Đánh dãy bài
        List<Card> sequenceMove = findValidSequence(lastMoveCards);
        if (sequenceMove != null) {
            return sequenceMove;
        }

        // 3. Trường hợp đặc biệt: Lá "2"
        if (lastMoveCards.size() == 1 && lastMoveCards.get(0).getRank().equals("2")) {
            List<Card> fourOfAKind = findFourOfAKind();
            if (fourOfAKind != null) {
                return fourOfAKind;
            }
        }

        return null;
    }

    // Tìm dãy bài hợp lệ
    private List<Card> findValidSequence(List<Card> lastMoveCards) {
        // Sắp xếp bài theo thứ tự rank
        List<Card> sortedHand = getHand().stream()
                .sorted(Comparator.comparing(Card::getRankSequence))
                .collect(Collectors.toList());

        // Tìm dãy bài có độ dài bằng lastMoveCards
        for (int i = 0; i <= sortedHand.size() - lastMoveCards.size(); i++) {
            List<Card> potentialSequence = sortedHand.subList(i, i + lastMoveCards.size());

            // Kiểm tra tính hợp lệ của dãy bài
            if (gameLogic.isValidSequence(potentialSequence) &&
                    gameLogic.canBeatPreviousMove(lastMoveCards, potentialSequence)) {
                return potentialSequence;
            }
        }

        return null;
    }

    // Tìm 4 lá bài cùng rank (để đánh lá "2")
    private List<Card> findFourOfAKind() {
        Map<String, List<Card>> cardsByRank = getHand().stream()
                .collect(Collectors.groupingBy(Card::getRank));

        for (List<Card> rankGroup : cardsByRank.values()) {
            if (rankGroup.size() == 4) {
                return rankGroup;
            }
        }

        return null;
    }

    // Đánh các lá bài nhỏ nhất
    private List<Card> playSmallestCards(int count) {
        // Sắp xếp bài theo rank
        List<Card> sortedHand = getHand().stream()
                .sorted(Comparator.comparing(Card::getRankValue))
                .collect(Collectors.toList());

        // Lấy các lá bài nhỏ nhất
        List<Card> smallestCards = sortedHand.stream()
                .limit(count)
                .collect(Collectors.toList());

        return smallestCards;
    }

    // Phương thức để xóa bài đã chọn khỏi tay
    public void removeCards(List<Card> cardsToRemove) {
        getHand().removeAll(cardsToRemove);
    }

    // Phương thức để lấy tên người chơi
    public String getName() {
        return super.getName();
    }
}
