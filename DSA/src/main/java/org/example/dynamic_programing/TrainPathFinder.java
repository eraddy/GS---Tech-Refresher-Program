package org.example.dynamic_programing;

import java.util.*;

public class TrainPathFinder {
    private static class Station {
        private String name;
        private List<Station> neighbours;

        public Station(String name) {
            this.name = name;
            this.neighbours = new ArrayList<>();
        }

        public String getName() {
            return name;
        }

        public List<Station> getNeighbours() {
            return neighbours;
        }

        public void addNeighbour(Station station) {
            this.neighbours.add(station);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Station station = (Station) obj;
            return Objects.equals(name, station.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

    private static class TrainMap {
        private HashMap<String, Station> stations;

        public TrainMap() {
            this.stations = new HashMap<>();
        }

        public TrainMap addStation(String name) {
            Station s = new Station(name);
            this.stations.putIfAbsent(name, s);
            return this;
        }

        public Station getStation(String name) {
            return this.stations.get(name);
        }

        public TrainMap connectStations(Station fromStation, Station toStation) {
            if (fromStation == null) throw new IllegalArgumentException("From station is null");
            if (toStation == null) throw new IllegalArgumentException("To station is null");

            fromStation.addNeighbour(toStation);
            toStation.addNeighbour(fromStation);
            return this;
        }

        public List<Station> shortestPath(String from, String to) {
            Station start = getStation(from);
            Station end = getStation(to);
            if (start == null || end == null) return Collections.emptyList();

            Queue<Station> queue = new LinkedList<>();
            Map<Station, Station> parentMap = new HashMap<>();
            Set<Station> visited = new HashSet<>();

            queue.add(start);
            visited.add(start);
            parentMap.put(start, null);

            while (!queue.isEmpty()) {
                Station current = queue.poll();
                if (current.equals(end)) {
                    return buildPath(parentMap, end);
                }

                for (Station neighbour : current.getNeighbours()) {
                    if (!visited.contains(neighbour)) {
                        visited.add(neighbour);
                        parentMap.put(neighbour, current);
                        queue.add(neighbour);
                    }
                }
            }

            return Collections.emptyList(); // no path found
        }

        private List<Station> buildPath(Map<Station, Station> parentMap, Station end) {
            List<Station> path = new LinkedList<>();
            for (Station at = end; at != null; at = parentMap.get(at)) {
                path.add(0, at); // prepend
            }
            return path;
        }

        public static String convertPathToStringRepresentation(List<Station> path) {
            if (path.isEmpty()) return "";
            return path.stream().map(Station::getName)
                    .reduce((s1, s2) -> s1 + "->" + s2)
                    .get();
        }
    }

    // ✅ Test the implementation
    public static void main(String[] args) {
        TrainMap map = new TrainMap();

        map.addStation("King's Cross St Pancras")
                .addStation("Angel")
                .addStation("Old Street")
                .addStation("Farringdon")
                .addStation("Barbican")
                .addStation("Moorgate")
                .addStation("Russell Square")
                .addStation("Holborn")
                .addStation("Chancery Lane")
                .addStation("St Paul’s")
                .addStation("Bank");

        map.connectStations(map.getStation("King's Cross St Pancras"), map.getStation("Angel"))
                .connectStations(map.getStation("Angel"), map.getStation("Old Street"))
                .connectStations(map.getStation("King's Cross St Pancras"), map.getStation("Farringdon"))
                .connectStations(map.getStation("Farringdon"), map.getStation("Barbican"))
                .connectStations(map.getStation("Barbican"), map.getStation("Moorgate"))
                .connectStations(map.getStation("Russell Square"), map.getStation("King's Cross St Pancras"))
                .connectStations(map.getStation("Russell Square"), map.getStation("Holborn"))
                .connectStations(map.getStation("Holborn"), map.getStation("Chancery Lane"))
                .connectStations(map.getStation("Chancery Lane"), map.getStation("St Paul’s"))
                .connectStations(map.getStation("St Paul’s"), map.getStation("Bank"))
                .connectStations(map.getStation("Moorgate"), map.getStation("Bank"));

        List<Station> path = map.shortestPath("King's Cross St Pancras", "Bank");
        System.out.println("Shortest Path: " + TrainMap.convertPathToStringRepresentation(path));
    }
}
