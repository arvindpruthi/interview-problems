/*
 # Given a list of words (ie a vocabulary), a start word, and an end word, determine the minimum number 
 # of valid transformations applied to the start word to arrive at the end word.
# A transformation is valid if it changes one letter from a word, and the resulting word is included in the vocabulary.
# Eg Start = APE, end = MAN, vocabulary = APE, TAP, OAT, OAR, MAN, OPT, APT, ATE, MAT
# APE -> MAN  : APE -> APT -> OPT -> OAT -> MAT -> MAN
*/

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

class doublets {
    static String[] dictionary = new String[] {"APE", "TAP", "OAT", "OAR", "MAN", "OPT", "APT", "ATE", "MAT"};
    Map<String, List<String>> AdjGraph;

    private int distance(String from, String to) {
        if (from.length() != to.length())
            return -1;
        
        int distance = 0;
        for (int i=0; i<from.length(); i++) {
            if (from.charAt(i) != to.charAt(i))
                distance++;
        }
        return distance;
    }

    private Map<String, List<String>> createAdjGraph() {
        Map<String, List<String>> adj = new HashMap<>();
        for (int i=0; i<dictionary.length; i++) {
            for (int j=i+1; j<dictionary.length; j++) {
                String from = dictionary[i];
                String to = dictionary[j];
                int distance = distance(from, to);
                if (distance == 1) {
                    // There should be graph entry between these 2 nodes.
                    adj.computeIfAbsent(from, key -> new ArrayList<>());
                    adj.computeIfPresent(from, (key, val) -> {
                        val.add(to);
                        return val;
                    });
                    adj.computeIfAbsent(to, key -> new ArrayList<>());
                    adj.computeIfPresent(to, (key, val) -> {
                        val.add(from);
                        return val;
                    });
                }
            }
        }
        return adj;
    }

    public doublets() {
        this.AdjGraph = createAdjGraph();
    }

    public void printShortestDistance(String from, String to) {
        if (from == to) {
            System.out.println("Both from and to are the same words");
            return;
        }

        Queue<String> q = new LinkedList<>();
        Map<String, Integer> distance = new HashMap<>();
        Map<String, String> shortestPath = new HashMap<>();
        q.add(from);
        int currentDistance = 0;
        distance.put(from, currentDistance);
        
        while (!q.isEmpty()) {
            String next = q.poll();
            currentDistance = distance.get(next)+1;
            for (String entry : AdjGraph.get(next)) {
                // Update the distance here
                Integer keyDistance = distance.get(entry);
                if (keyDistance == null || keyDistance > currentDistance) {
                    distance.put(entry, currentDistance);
                    shortestPath.put(entry, next);
                    q.add(entry);
                }             
            }
        }

        System.out.println("Distance from " + from + " and " + to + " is: " + distance.get(to));
        System.out.println("Path is: ");
        List<String> path = new ArrayList<>();
        String entry = to; 
        path.add(to);
        while (entry != from) {
            entry = shortestPath.get(entry);
            path.add(entry);
        }

        for (int i=path.size()-1; i>0; i--) {
            System.out.print(path.get(i) + "->");
        }
        System.out.println(to);


    }

    public static void main(String[] args) {
        doublets myDoublets = new doublets();
        myDoublets.printShortestDistance("APE", "MAN");
    }
}