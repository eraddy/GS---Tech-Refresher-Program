package com.example;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class DeckRevealer {

    /**
     * Returns an ordering of the deck that reveals cards in increasing order.
     * @param deck an array of unique integers representing the deck
     * @return ordered deck
     * @throws IllegalArgumentException if deck is null
     */
    public static int[] deckRevealedIncreasing(int[] deck) {
        if (deck == null) {
            throw new IllegalArgumentException("Input deck cannot be null.");
        }
        if (deck.length <= 1) {
            return deck; // Empty or single-element deck
        }

        // Sort the deck in ascending order
        Arrays.sort(deck);

        // Use a deque to simulate the reverse revealing process
        Deque<Integer> cardDeque = new LinkedList<>();

        // Place cards from largest to smallest
        for (int i = deck.length - 1; i >= 0; i--) {
            if (!cardDeque.isEmpty()) {
                // Move the last card to the front
                cardDeque.addFirst(cardDeque.removeLast());
            }
            cardDeque.addFirst(deck[i]);
        }

        // Convert deque to array
        int[] orderedDeck = new int[deck.length];
        int index = 0;
        for (int card : cardDeque) {
            orderedDeck[index++] = card;
        }

        return orderedDeck;
    }

    public static void main(String[] args) {
        // Sample test cases
        int[] deck1 = {17, 13, 11, 2, 3, 5, 7};
        System.out.println("Output: " + Arrays.toString(deckRevealedIncreasing(deck1)));
        // Expected Output: [2, 13, 3, 11, 5, 17, 7]

        int[] deck2 = {1, 1000};
        System.out.println("Output: " + Arrays.toString(deckRevealedIncreasing(deck2)));
        // Expected Output: [1, 1000]

        int[] deck3 = {};
        System.out.println("Output: " + Arrays.toString(deckRevealedIncreasing(deck3)));
        // Expected Output: []

        int[] deck4 = {42};
        System.out.println("Output: " + Arrays.toString(deckRevealedIncreasing(deck4)));
        // Expected Output: [42]
    }
}
