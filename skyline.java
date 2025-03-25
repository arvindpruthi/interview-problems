import java.util.TreeMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

class skyline {

 
    // This is an intervals problem. We have to convert this data into a series of intervals with max height.
    static class interval implements Comparable<interval> {
        int x1;
        int x2;
        interval(int x1, int x2) {
            this.x1 = x1;
            this.x2 = x2;
        }

        @Override
        public int compareTo(interval to) {
            if (x1 < to.x1)
                return -1;
            if (x1 > to.x1)
                return 1;
            if (x2 < to.x2)
                return -1;
            if (x2 > to.x2)
                return 1;
            return 0;
        }
    }    

    static class coord {
        int x;
        int y;
        public coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public void printCoord() {
            System.out.println("(" + x + ", " + y + ")");
        }
    }

    
    public static List<coord> easyCalc(int[][] buildings) {
        List<coord> coordlist = new ArrayList<>();

        // determine max coordinates from the buildings array
        int maxXCoord = 0;
        for (int i=0; i<buildings.length; i++) {
            if (buildings[i][1] > maxXCoord)
                maxXCoord = buildings[i][1];
        }
        int[] htMap = new int[maxXCoord+2];

        for (int i=0; i<buildings.length; i++) {
            for (int j=buildings[i][0]; j<buildings[i][1]; j++) {
                int y = buildings[i][2];
                if (y > htMap[j])
                    htMap[j] = y;
            }
        }

        int prevHt = 0;
        for (int i=0; i<=maxXCoord+1; i++) {
            Integer xcoord = i;
            Integer ycoord = htMap[i];
            if (ycoord != prevHt) {
                coordlist.add(new coord(xcoord, ycoord));
                prevHt = ycoord;
            }
        }

        return coordlist;
    }

    public static List<coord> intervalMethod(int[][] buildings) {
        List<coord> coordlist = new ArrayList<>();
        Map<interval, Integer> intervalMap = new TreeMap<>();
        Map<interval, Integer> processedMap = new TreeMap<>();
        for (int i=0; i<buildings.length; i++) {
            intervalMap.put(new interval(buildings[i][0], buildings[i][1]), buildings[i][2]);
        }
        
        interval prevInterval = null;
        int prevHt = 0;
        for (interval current : intervalMap.keySet()) {
            int ht = intervalMap.get(current);
            if (prevInterval != null) {
                // Check for overlap
                if (prevInterval.x2 >= current.x1) {
                    if (ht > prevHt) {
                        // We have overlapping intervals & current Ht is greater than the previous ht, then cut the prev interval shorter
                        processedMap.remove(prevInterval);
                        prevInterval.x2 = current.x1;
                        // reinsert
                        processedMap.put(prevInterval, prevHt);
                    } else if (prevHt > ht) {
                        // cut short the current interval
                        current.x1 = prevInterval.x2;
                        
                    }
                }
            }
            processedMap.put(current, ht);
            prevInterval = current;
            prevHt = ht;
        }

        prevInterval = null;
        prevHt = 0;
        for (interval current : processedMap.keySet()) {
            int ht = intervalMap.get(current);
            if (prevInterval != null) {
                if (prevInterval.x2 < current.x1) {
                    // first introduce a 0
                    coordlist.add(new coord(prevInterval.x2, 0));    
                }
            }
            coordlist.add(new coord(current.x1, ht));
            prevHt = ht;
            prevInterval = current;
        }
        coordlist.add(new coord(prevInterval.x2, 0));
        return coordlist;
    }
    public static void main(String[] args) {
        int[][] buildings = {
            {2,9,10},
            {3,7,15},
            {5,12,12},
            {15,20,10},
            {19,24,8}
        };
    
        List<coord> coordList = skyline.easyCalc(buildings);

        System.out.println("Calculated coordinates for the silhouette are: ");
        for (coord entry : coordList) {
            entry.printCoord();
        }

        coordList = skyline.intervalMethod(buildings);
        System.out.println("Calculated coordinates for the silhouette using the interval method are: ");
        for (coord entry : coordList) {
            entry.printCoord();
        }
    }
}