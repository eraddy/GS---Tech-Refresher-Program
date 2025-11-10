package com.epam.rd.autotasks;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class NewPostOffice {
    private Collection<Box> listBox;
    private static final int COST_KILOGRAM = 5;
    private static final int COST_CUBIC_METER = 100;
    private static final double COEFFICIENT = 0.5;

    public NewPostOffice() {
        listBox = new ArrayList<>();
    }

    public Collection<Box> getListBox() {
        return (Collection<Box>) ((ArrayList<Box>) listBox).clone();
    }

    static BigDecimal calculateCostOfBox(double weight, double volume, int value) {
        BigDecimal costWeight = BigDecimal.valueOf(weight)
                .multiply(BigDecimal.valueOf(COST_KILOGRAM), MathContext.DECIMAL64);
        BigDecimal costVolume = BigDecimal.valueOf(volume)
                .multiply(BigDecimal.valueOf(COST_CUBIC_METER), MathContext.DECIMAL64);
        return costVolume.add(costWeight)
                .add(BigDecimal.valueOf(COEFFICIENT * value), MathContext.DECIMAL64);
    }

    // implements student
    public boolean addBox(String addresser, String recipient, double weight, double volume, int value) {
        if (addresser == null || addresser.isEmpty() || recipient == null || recipient.isEmpty() || addresser.trim().isEmpty() || recipient.trim().isEmpty()) {
            throw new IllegalArgumentException("Addresser and recipient must not be null or empty");
        }
        if (Double.compare(weight, 0.5) < 0 || Double.compare(weight, 20.0) > 0) {
            throw new IllegalArgumentException("Weight must be between 0.5 and 20.0 kg");
        }
        if (Double.compare(volume, 0.0) <= 0 || Double.compare(volume, 0.25) > 0) {
            throw new IllegalArgumentException("Volume must be greater than 0 and less than 0.25");
        }
        if (value <= 0) {
            throw new IllegalArgumentException("Value must be greater than 0");
        }

        BigDecimal cost = calculateCostOfBox(weight, volume, value);
        Box box = new Box(addresser, recipient, weight, volume);
        box.setCost(cost);

        return listBox.add(box);
    }

    public void declineCostOfBox(double percent) {
        BigDecimal percentDecimal = BigDecimal.valueOf(percent)
                .divide(BigDecimal.valueOf(100), 20, RoundingMode.HALF_EVEN);

        for (Box box : listBox) {
            BigDecimal originalCost = box.getCost();
            BigDecimal discount = originalCost.multiply(percentDecimal);
            BigDecimal newCost = originalCost.subtract(discount);

            // round only once at the end
            newCost = newCost.setScale(13,RoundingMode.HALF_EVEN);
            box.setCost(newCost);
        }
    }

    public Collection<Box> deliveryBoxToRecipient(String recipient) {
        List<Box> delivered = new ArrayList<>();
        List<Box> remaining = new ArrayList<>();

        for (Box box : listBox) {
            if (box.getRecipient().equalsIgnoreCase(recipient)) {
                delivered.add(box);
            } else {
                remaining.add(box);
            }
        }

        listBox = remaining;
        return delivered;
    }
}

