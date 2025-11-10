package com.epam.rd.autotasks.collections;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Predicate;

public class NewPostOfficeStorageImpl implements NewPostOfficeStorage {
    private List<Box> parcels;

    public NewPostOfficeStorageImpl() {
        this.parcels = new LinkedList<>();
    }

    public NewPostOfficeStorageImpl(Collection<Box> boxes) {
        if (boxes == null) {
            throw new NullPointerException("Boxes collection cannot be null");
        }

        // Check for null elements
        Iterator<Box> iterator = boxes.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == null) {
                throw new NullPointerException("Boxes collection contains null value");
            }
        }

        // Safe add all
        this.parcels = new LinkedList<>();
        for (Box b : boxes) {
            this.parcels.add(b);
        }
    }

    @Override
    public boolean acceptBox(Box box) {
        if (box == null) {
            throw new NullPointerException("Box cannot be null");
        }
        parcels.add(box);
        return true;
    }

    @Override
    public boolean acceptAllBoxes(Collection<Box> boxes) {
        if (boxes == null) {
            throw new NullPointerException("Boxes collection cannot be null");
        }

        // Validate before adding
        Iterator<Box> iterator = boxes.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == null) {
                throw new NullPointerException("Boxes collection contains null value");
            }
        }

        boolean changed = false;
        try {
            Iterator<Box> it = boxes.iterator();
            while (it.hasNext()) {
                parcels.add(it.next());
                changed = true;
            }
        } catch (Exception e) {
            // Rollback if anything fails
            Iterator<Box> it = boxes.iterator();
            while (it.hasNext()) {
                parcels.remove(it.next());
            }
            throw e;
        }
        return changed;
    }

    @Override
    public boolean carryOutBoxes(Collection<Box> boxes) {
        if (boxes == null) {
            throw new NullPointerException("Boxes collection cannot be null");
        }

        Iterator<Box> check = boxes.iterator();
        while (check.hasNext()) {
            if (check.next() == null) {
                throw new NullPointerException("Boxes collection contains null value");
            }
        }

        boolean changed = false;
        Iterator<Box> it = parcels.iterator();
        while (it.hasNext()) {
            Box current = it.next();
            Iterator<Box> bIt = boxes.iterator();
            while (bIt.hasNext()) {
                if (current.equals(bIt.next())) {
                    it.remove();
                    changed = true;
                    break;
                }
            }
        }
        return changed;
    }

    @Override
    public List<Box> carryOutBoxes(Predicate<Box> predicate) {
        if (predicate == null) {
            throw new NullPointerException("Predicate cannot be null");
        }

        List<Box> removed = new LinkedList<>();
        Iterator<Box> it = parcels.iterator();
        while (it.hasNext()) {
            Box b = it.next();
            if (predicate.test(b)) {
                removed.add(b);
                it.remove();
            }
        }
        return removed;
    }

    @Override
    public List<Box> getAllWeightLessThan(double weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be positive");
        }

        return searchBoxes(new Predicate<Box>() {
            @Override
            public boolean test(Box box) {
                return box.getWeight() < weight;
            }
        });
    }

    @Override
    public List<Box> getAllCostGreaterThan(BigDecimal cost) {
        if (cost == null) {
            throw new NullPointerException("Cost cannot be null");
        }
        if (cost.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Cost cannot be negative");
        }

        return searchBoxes(new Predicate<Box>() {
            @Override
            public boolean test(Box box) {
                return box.getCost().compareTo(cost) > 0;
            }
        });
    }

    @Override
    public List<Box> getAllVolumeGreaterOrEqual(double volume) {
        if (volume < 0) {
            throw new IllegalArgumentException("Volume cannot be negative");
        }

        return searchBoxes(new Predicate<Box>() {
            @Override
            public boolean test(Box box) {
                return box.getVolume() >= volume;
            }
        });
    }

    @Override
    public List<Box> searchBoxes(Predicate<Box> predicate) {
        if (predicate == null) {
            throw new NullPointerException("Predicate cannot be null");
        }

        List<Box> result = new LinkedList<>();
        for (Box b : parcels) {
            if (predicate.test(b)) {
                result.add(b);
            }
        }
        return result;
    }

    @Override
    public void updateOfficeNumber(Predicate<Box> predicate, int newOfficeNumber) {
        if (predicate == null) {
            throw new NullPointerException("Predicate cannot be null");
        }

        for (Box b : parcels) {
            if (predicate.test(b)) {
                b.setOfficeNumber(newOfficeNumber);
            }
        }
    }
}
