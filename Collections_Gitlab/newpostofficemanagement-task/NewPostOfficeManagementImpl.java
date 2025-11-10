package com.epam.rd.autotasks.collections;

import java.math.BigDecimal;
import java.util.*;

public class NewPostOfficeManagementImpl implements NewPostOfficeManagement {

    private List<Box> parcels;

    public NewPostOfficeManagementImpl(Collection<Box> boxes) {
        if (boxes == null) {
            throw new NullPointerException();
        }

        for (Box box : boxes) {
            if (box == null) {
                throw new NullPointerException();
            }
        }

        this.parcels = new ArrayList<>(boxes);
    }


    @Override
    public Optional<Box> getBoxById(int id) {
        List<Box> sorted = new ArrayList<>(parcels);
        Collections.sort(sorted, new Comparator<Box>() {
            @Override
            public int compare(Box o1, Box o2) {
                return Integer.compare(o1.getId(), o2.getId());
            }
        });

        int index = Collections.binarySearch(sorted, new BoxStub(id), new Comparator<Box>() {
            @Override
            public int compare(Box o1, Box o2) {
                return Integer.compare(o1.getId(), o2.getId());
            }
        });

        if (index >= 0) {
            return Optional.of(sorted.get(index));
        }
        return Optional.empty();
    }

    @Override
    public String getDescSortedBoxesByWeight() {
        List<Box> sorted = new ArrayList<>(parcels);
        Collections.sort(sorted, new Comparator<Box>() {
            @Override
            public int compare(Box o1, Box o2) {
                return Double.compare(o2.getWeight(), o1.getWeight()); // descending
            }
        });

        StringBuilder sb = new StringBuilder();
        for (Box b : sorted) {
            sb.append(b).append("\n");
        }
        if(sb.length() > 0)
            return sb.deleteCharAt(sb.length()-1).toString();
        return sb.toString();
    }

    @Override
    public String getAscSortedBoxesByCost() {
        List<Box> sorted = new ArrayList<>(parcels);
        Collections.sort(sorted, new Comparator<Box>() {
            @Override
            public int compare(Box o1, Box o2) {
                return  o1.getCost().compareTo(o2.getCost()) != 0 ?
                        o1.getCost().compareTo(o2.getCost()) :
                        Integer.compare(o1.getId(), o2.getId()); // Tie-breaking by ID
            }
        });

        StringBuilder sb = new StringBuilder();
        for (Box b : sorted) {
            sb.append(b).append("\n");
        }
        if(sb.length() > 0)
            return sb.deleteCharAt(sb.length()-1).toString();
        return sb.toString();
    }

    @Override
    public List<Box> getBoxesByRecipient(String recipient) {
        if (recipient == null) {
            throw new NullPointerException();
        }

        // Debug the parcels list before sorting
        System.out.println("Before sorting: " + parcels);

        parcels.sort(Comparator.comparing(Box::getRecipient).thenComparing(Box::getCity));

        // Debug the parcels list after sorting
        System.out.println("After sorting: " + parcels);

        int index = Collections.binarySearch(parcels, new BoxStub(recipient),
                Comparator.comparing(Box::getRecipient));

        // Debugging: Output the index returned by binary search
        System.out.println("Binary search index: " + index);

        List<Box> result = new ArrayList<>();
        if (index < 0) {
            // Binary search failed
            System.out.println("Binary search failed. Empty result returned.");
            return result;
        }

        // Collect boxes with the same recipient
        result.add(parcels.get(index));
        int lowerIndex = index - 1;
        while (lowerIndex >= 0 && parcels.get(lowerIndex).getRecipient().equals(recipient)) {
            result.add(0, parcels.get(lowerIndex));
            lowerIndex--;
        }

        int upperIndex = index + 1;
        while (upperIndex < parcels.size() && parcels.get(upperIndex).getRecipient().equals(recipient)) {
            result.add(parcels.get(upperIndex));
            upperIndex++;
        }

        // Debugging: Output the result list
        System.out.println("Result: " + result);

        return result;
    }



    // Helper stub class for binary search
    private static class BoxStub extends Box {
        private final Integer idStub;
        private final String recipientStub;
        private final String cityStub;

        public BoxStub(int id) {
            super("", "", 0.0, 0.0, BigDecimal.ZERO, "", 0);
            this.idStub = id;
            this.recipientStub = null;
            this.cityStub = null;
        }

        public BoxStub(String recipient) {
            super("", recipient, 0.0, 0.0, BigDecimal.ZERO, "", 0); // Keep everything else as default
            this.recipientStub = recipient;
            this.idStub = null;
            this.cityStub = ""; // Assign the smallest lexicographical value
        }

        @Override
        public int getId() {
            return idStub != null ? idStub : super.getId();
        }

        @Override
        public String getRecipient() {
            return recipientStub != null ? recipientStub : super.getRecipient();
        }

        @Override
        public String getCity() {
            return cityStub != null ? cityStub : super.getCity(); // Return the stubbed city value
        }
    }
}
