package com.epam.rd.autocode.queue;

import java.util.Deque;
import java.util.LinkedList;

public class CashBox {

    private final int number;
    private final Deque<Buyer> buyers;
    private State state;

    public enum State {
        ENABLED,
        DISABLED,
        IS_CLOSING
    }

    public CashBox(int number) {
        this.number = number;
        this.buyers = new LinkedList<>();
        this.state = State.DISABLED;
    }

    public Deque<Buyer> getQueue() {
        return new LinkedList<>(buyers);
    }

    public Buyer serveBuyer() {
        Buyer served = buyers.pollFirst();
        if (state == State.IS_CLOSING && buyers.isEmpty()) {
            state = State.DISABLED;
        }
        return served;
    }

    public boolean inState(State state) {
        return this.state == state;
    }

    public boolean notInState(State state) {
        return this.state != state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void addLast(Buyer buyer) {
        buyers.addLast(buyer);
    }

    public Buyer removeLast() {
        return buyers.pollLast();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("#").append(number).append("[").append(state == State.ENABLED ? "+" : state == State.IS_CLOSING ? "|" : "-").append("]");
        for (Buyer buyer : buyers) {
            sb.append(buyer);
        }
        return sb.toString();
    }
}