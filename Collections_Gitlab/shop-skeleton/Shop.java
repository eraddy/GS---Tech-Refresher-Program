package com.epam.rd.autocode.queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Shop {

    private final List<CashBox> cashBoxes;

    public Shop(int count) {
        this.cashBoxes = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            cashBoxes.add(new CashBox(i));
        }
    }

    public void addBuyer(Buyer buyer) {
        CashBox minQueueCashBox = null;
        int minQueueSize = Integer.MAX_VALUE;

        for (CashBox cashBox : cashBoxes) {
            if (cashBox.inState(CashBox.State.ENABLED) && cashBox.getQueue().size() < minQueueSize) {
                minQueueSize = cashBox.getQueue().size();
                minQueueCashBox = cashBox;
            }
        }

        if (minQueueCashBox != null) {
            minQueueCashBox.addLast(buyer);
        }
    }

    public void setCashBoxState(int cboxNumber, CashBox.State state) {
        CashBox cashBox = cashBoxes.get(cboxNumber);
        if (cashBox.getState() == CashBox.State.DISABLED && state == CashBox.State.ENABLED) {
            balanceQueues();
        }
        cashBox.setState(state);
    }

    public CashBox getCashBox(int cboxNumber) {
        return cashBoxes.get(cboxNumber);
    }

    public void tact() {
        for (CashBox cashBox : cashBoxes) {
            if (cashBox.inState(CashBox.State.ENABLED) || cashBox.inState(CashBox.State.IS_CLOSING)) {
                cashBox.serveBuyer(); // Serve one buyer
            }
        }

        // Balance the buyers across ENABLED cashboxes
        balanceQueues();

        // Update the state of cashboxes in IS_CLOSING state
        for (CashBox cashBox : cashBoxes) {
            if (cashBox.inState(CashBox.State.IS_CLOSING) && cashBox.getQueue().isEmpty()) {
                cashBox.setState(CashBox.State.DISABLED);
            }
        }
    }

    private void balanceQueues() {
        // Create a queue to store defectors (buyers that will be redistributed)
        Queue<Buyer> defectors = new LinkedList<>();

        // Step 1: Extract defectors from longer queues
        int enabledCount = 0; // Number of ENABLED cashboxes
        int totalBuyers = 0;  // Total buyers in ENABLED cashboxes

        for (CashBox cashBox : cashBoxes) {
            if (cashBox.inState(CashBox.State.ENABLED)) {
                enabledCount++;
                totalBuyers += cashBox.getQueue().size();
            }
        }

        if (enabledCount == 0) {
            return; // No enabled cashboxes, nothing to balance
        }

        int minQueueSize = totalBuyers / enabledCount;
        int maxQueueSize = minQueueSize + (totalBuyers % enabledCount == 0 ? 0 : 1);

        for (CashBox cashBox : cashBoxes) {
            if (cashBox.inState(CashBox.State.ENABLED)) {
                while (cashBox.getQueue().size() > maxQueueSize) {
                    defectors.add(cashBox.removeLast()); // Move to defectors
                }
            }
        }

        // Step 2: Redistribute defectors to shorter queues
        for (CashBox cashBox : cashBoxes) {
            if (cashBox.inState(CashBox.State.ENABLED)) {
                while (cashBox.getQueue().size() < minQueueSize && !defectors.isEmpty()) {
                    cashBox.addLast(defectors.poll()); // Fill up cashbox
                }
            }
        }
    }

    public void print() {
        cashBoxes.forEach(System.out::println);
    }

    public int getCashBoxCount() {
        return cashBoxes.size();
    }
}