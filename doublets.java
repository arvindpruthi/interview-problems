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

class doublets {
    static String[] dictionary = {"APE", "TAP", "OAT", "OAR", "MAN", "OPT", "APT", "ATE", "MAT"}
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
                    adj.computeIfPresent(from, (key, val) -> val.add(to));
                    adj.computeIfAbsent(to, new ArrayList<>());
                    adj.computeIfPresent(to, (key, val) -> val.add(from));
                }                
            }
        }
        for (String str : dictionary) {

        }
    }

    public doublets() {

    }

}