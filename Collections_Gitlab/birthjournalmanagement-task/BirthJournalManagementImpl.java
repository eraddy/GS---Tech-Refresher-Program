package com.epam.rd.autotasks.collections;

import java.util.*;

public class BirthJournalManagementImpl implements BirthJournalManagement {

    private Map<WeekDay, List<Baby>> journal;
    private boolean committed = false;

    public BirthJournalManagementImpl() {
        this.journal = new EnumMap<>(WeekDay.class);
    }

    @Override
    public boolean addEntryOfBaby(WeekDay day, Baby baby) {
        if (committed) {
            return false; // no modifications allowed after commit
        }
        if (day == null || baby == null) {
            return false;
        }

        List<Baby> babies = journal.get(day);
        if (babies == null) {
            babies = new ArrayList<>();
            journal.put(day, babies);
        }

        babies.add(baby);
        return true;
    }

    @Override
    public void commit() {
        // Make journal immutable
        Map<WeekDay, List<Baby>> unmodifiable = new EnumMap<>(WeekDay.class);
        for (Map.Entry<WeekDay, List<Baby>> entry : journal.entrySet()) {
            unmodifiable.put(entry.getKey(),
                    Collections.unmodifiableList(new ArrayList<>(entry.getValue())));
        }
        journal = Collections.unmodifiableMap(unmodifiable);
        committed = true;
    }

    @Override
    public int amountBabies() {
        int count = 0;
        for (List<Baby> babies : journal.values()) {
            count += babies.size();
        }
        return count;
    }

    @Override
    public List<Baby> findBabyWithHighestWeight(String gender) {
        double maxWeight = Double.MIN_VALUE;

        // Find max weight for the given gender
        for (List<Baby> babies : journal.values()) {
            for (Baby b : babies) {
                if (b.getGender().equalsIgnoreCase(gender)) {
                    if (b.getWeight() > maxWeight) {
                        maxWeight = b.getWeight();
                    }
                }
            }
        }

        // Collect all with that max weight
        List<Baby> result = new ArrayList<>();
        for (List<Baby> babies : journal.values()) {
            for (Baby b : babies) {
                if (b.getGender().equalsIgnoreCase(gender) &&
                        Double.compare(b.getWeight(), maxWeight) == 0) {
                    result.add(b);
                }
            }
        }

        // Sort alphabetically by name
        Collections.sort(result, new Comparator<Baby>() {
            @Override
            public int compare(Baby b1, Baby b2) {
                return b1.getName().compareTo(b2.getName());
            }
        });

        return Collections.unmodifiableList(result);
    }

    @Override
    public List<Baby> findBabyWithSmallestHeight(String gender) {
        int minHeight = Integer.MAX_VALUE;

        // Find min height
        for (List<Baby> babies : journal.values()) {
            for (Baby b : babies) {
                if (b.getGender().equalsIgnoreCase(gender)) {
                    if (b.getHeight() < minHeight) {
                        minHeight = b.getHeight();
                    }
                }
            }
        }

        // Collect all with that height
        List<Baby> result = new ArrayList<>();
        for (List<Baby> babies : journal.values()) {
            for (Baby b : babies) {
                if (b.getGender().equalsIgnoreCase(gender)
                        && b.getHeight() == minHeight) {
                    result.add(b);
                }
            }
        }

        // Sort by increasing weight
        Collections.sort(result, new Comparator<Baby>() {
            @Override
            public int compare(Baby b1, Baby b2) {
                return Double.compare(b1.getWeight(), b2.getWeight());
            }
        });

        return Collections.unmodifiableList(result);
    }

    @Override
    public Set<Baby> findBabiesByBirthTime(String from, String to) {
        Set<Baby> result = new LinkedHashSet<>();

        String normFrom = normalizeTime(from);
        String normTo = normalizeTime(to);

        for (List<Baby> babies : journal.values()) {
            for (Baby baby : babies) {
                String babyTime = normalizeTime(baby.getTime());
                if (babyTime.compareTo(normFrom) >= 0 && babyTime.compareTo(normTo) <= 0) {
                    result.add(baby);
                }
            }
        }

        return Collections.unmodifiableSet(result);
    }

    // Helper to convert HH:MM -> minutes since midnight
    private int parseTimeToMinutes(String time) {
        String[] parts = time.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        return hour * 60 + minute;
    }

    private String normalizeTime(String time) {
        String[] parts = time.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        return String.format("%02d:%02d", hour, minute);
    }
}
