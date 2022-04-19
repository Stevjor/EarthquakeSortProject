
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    
    /**
     * This method gets the largest depth of an ArrayList of type QuakeEntry
     * and returns the position of such index.
     */
    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from) {
        int maxIndex = from;
        //Iterate through the quakeData starting from "from + 1" position
        for (int i = from + 1; i < quakeData.size(); i++) {
            double currentDepth = quakeData.get(i).getDepth();
            double currentMaxDepth = quakeData.get(maxIndex).getDepth();
            if (currentDepth > currentMaxDepth) {
                currentMaxDepth = currentDepth;
                maxIndex = i;
            }
        }
        
        return maxIndex;
    }
    
    
    /**
     * Sorts an ArrayList of type QuakeEntry from largest depth to lowest
     * depth.
    */
    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {
        //for each element in myArray from index 0 to the index of the last element from my array and assing "i" each index position:
        for (int i = 0; i < in.size(); i++) {
            //call getLargetDepth at index "i" and assing value "largest".
            int largest = getLargestDepth(in, i);
            
            QuakeEntry iQuake = in.get(i);
            QuakeEntry largestQuake = in.get(largest);
            //swap elements at indexes "i" and "largest".
            in.set(i, largestQuake);
            in.set(largest, iQuake);
        }
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        sortByLargestDepth(list);
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
        
    }
}
