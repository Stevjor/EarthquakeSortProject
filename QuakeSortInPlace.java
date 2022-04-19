
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
     * Returns true if the earthquakes are in sorted order
     * by magnitude from smallest to largest. Otherwise this methods 
     * returns false.*/
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes) {
        
        //I have to check if they are in order. For this, I can make a copy of quakes first.
        ArrayList<QuakeEntry> quakesClone = new ArrayList<QuakeEntry>(quakes);
        
        //Then compare if the copy equals quake after calling sortByMagnitude method. 
        sortByMagnitude(quakesClone);
        //If it is equal, then return true. 
        
        /*System.out.println("");
        System.out.println(quakes);
        System.out.println(quakesClone);
        System.out.println("");*/
        
        //Otherwise,return false.
        return quakesClone.equals(quakes);
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

    
    /** 
     * Makes one buble pass through the quakeData ArrayList.
     */
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
        //I take the numSorted to represent the index of the first number.
        
        
        for (int i = 0; i < quakeData.size() - 1; i++) {
            double firstQuakeMagnitude = quakeData.get(i).getMagnitude();
            double secondQuakeMagnitude = quakeData.get(i + 1).getMagnitude();
            QuakeEntry firstQuake = quakeData.get(i);
            QuakeEntry secondQuake = quakeData.get(i + 1);
      
            if (firstQuakeMagnitude > secondQuakeMagnitude) {
                quakeData.set(i, secondQuake);
                quakeData.set(i + 1, firstQuake);
            }
        }
    }
    
    /**
     * Sorts an ArrayList of type QuakeEntry from least to greatest.
       */
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
        int timesToIterate = in.size() - 1;
        for (int i = 0; i < timesToIterate; i++) {
            System.out.println("");
            System.out.println("Printing earthquakes after pass " + i + ":");
            onePassBubbleSort(in, i);
            
            for (QuakeEntry qe: in) {
            
            System.out.println(qe);
            } 
        
        }
    }
    
    /**
     * This method prints how many passes were needed to sort the elements. 
     */
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
        //Create an element to count the number of times the loop was executed or in other words, 
        //the number of times the array was sorted calling checkInSortedOrder method.
        int times = 0;
        
        //N elements represents the size of the array. So, I have to iterate this loop (N - 1) times
        for (int i = 0; i < in.size() - 1; i++) {
            
            //and each time the loop iterates, it first checks if the array is in sorted order. So, if it
            //is already in sorted order, then break the loop 
            //Count times += 1 to keep count of the current time number executed.
            
            times += 1;
            onePassBubbleSort(in, i);
            if (checkInSortedOrder(in)) break;
            
            //System.out.println("");
            //for (QuakeEntry qe : in) System.out.println(qe);
        }
        //and prit the number of times the loop was executed.
        System.out.println("The number of passes required to sort the elements from the array is " + times + ".");
    }
    
    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        String source = "data/earthquakeDataSampleSix1.atom";
        
        ArrayList<QuakeEntry> list  = parser.read(source);  
        
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        //sortByLargestDepth(list);
        for (QuakeEntry qe: list) {
            
            System.out.println(qe);
        } 
        
        ArrayList<QuakeEntry> cloneList = new ArrayList<QuakeEntry>(list);
        
        
        sortByMagnitudeWithBubbleSort(list);
        
        System.out.println("");
        System.out.println("Earthquakes in sorted order:");
        for (QuakeEntry qe: list) {
            
            System.out.println(qe);
        } 
        
        System.out.println("");
        sortByMagnitudeWithBubbleSortWithCheck(cloneList);
        
        
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
