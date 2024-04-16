//https://www.jfree.org/jfreechart/api/javadoc/index.html
//https://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/plot/XYPlot.html
//https://commons.apache.org/proper/commons-math/commons-math-docs/apidocs/index.html

//import java.lang.reflect.Array;

//import org.apache.commons.math4.legacy.stat.descriptive.DescriptiveStatistics;
import java.util.ArrayList;

/**
 * This class is used to plot data using JFreeChart
 * Similar to my ProgramPlotter class, this class will plot data
 * The difference is that this class will use JFreeChart to plot the data
 * The data will be stored in a arraylist and then plottt
 * salter function will be used to randomize the data
 * smoother function will be used to smooth the data
 */
public class JFreePlotter {
    ArrayList<Double> data;
    ArrayList<Double> saltedData;
    ArrayList<Double> smoothedData;
    ArrayList<Integer> xValues;
    /**
     * Constructor
     * Initialize the hashmap
     * Use hashmap for the purpose of a graph (key, value)
     * where the key is the x value and the data is the output of the graph
     */
    public JFreePlotter(){
        data = new ArrayList<Double>();
        saltedData = new ArrayList<Double>();
        smoothedData = new ArrayList<Double>();
        xValues = new ArrayList<Integer>();
     
    }

    /**
     * using y = (x^2)+13 as the fuction
     * @param x value to input into the function
     * @return returns the result of the function as double
     */
    public double function(int x){
        double result = (x*x) + 13;
        return result;
    }

    /**
     * This method generates data for a given function and stores it in an hashMap
     * @param start start point
     * @param end end point
     * @param increment interval between points
     */
    public void storeData(int start, int end, int increment){
        for(int i = start; i <= end; i+=increment){
            data.add(function(i));
            xValues.add(i);
        }
    }

    /**
     *  This method salts the data
     * @param y the value to salt
     * @param min lower bound of the salt
     * @param max upper bound of the salt
     * @return
     */
    public double salt(double y, int min, int max){
        int plusMinus = (int)(Math.random() * 2) + 1;

        if (plusMinus == 1){
        double salt = (Math.random() * (max - min)) + min;
        double result = y + salt;
        return result;
        }
        else {
        double salt = (Math.random() * (max - min)) + min;
        double result = y - salt;
        return result;
        }
    }

    /**
     * This method salts the data
     * @param min lower bound of the salt
     * @param max upper bound of the salt
     */
    public void saltValues(int min, int max){
        for (int i = 0; i < data.size(); i++) {
            double saltedValue = salt(data.get(i), min, max);
            saltedData.add(saltedValue);
        }    
    }

   /**
    * This method smooths the data
    * @param windowValue the window value is the number of values to the left and right of the current value
    */
    public void smoother(int windowValue){
        for(int i = 0; i < saltedData.size(); i++){ 
            int count = 0;
            double sum = 0;
            for(int j = i - windowValue; j <= i + windowValue; j++){
               if(j >= 0 && j < saltedData.size()){
                sum+= saltedData.get(j);
                count++;
               }
            }
            double average = sum / count;
            double roundedAverage = Math.round(average);
            smoothedData.add(roundedAverage);
            } 
    }

    /**
     * This method prints the data for testing purposes
     */
    public void printArrays(){
        System.out.println("Data: ");
        for(int i = 0; i < data.size(); i++){
            System.out.println("X: " + xValues.get(i) + " Y: " + data.get(i));
        }
        System.out.println("Salted Data: ");
        for(int i = 0; i < saltedData.size(); i++){
            System.out.println("X: " + xValues.get(i) + " Y: " + saltedData.get(i));
        }
        System.out.println("Smoothed Data: ");
        for(int i = 0; i < smoothedData.size(); i++){
            System.out.println("X: " + xValues.get(i) + " Y: " + smoothedData.get(i));
        }
    }

    public void run(){
        storeData(0, 100, 1);
        saltValues(0, 10);
        smoother(5);
        printArrays();
    }

}
