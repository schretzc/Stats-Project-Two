//https://www.jfree.org/jfreechart/api/javadoc/index.html
//https://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/plot/XYPlot.html
//https://commons.apache.org/proper/commons-math/commons-math-docs/apidocs/index.html



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
public class PlotSaltSmooth {
    ArrayList<Double> data;
    ArrayList<Double> saltedData;
    ArrayList<Double> smoothedData;
    ArrayList<Integer> xValues;
    
    public PlotSaltSmooth(){
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
    public ArrayList<Double> plotData(int start, int end, int increment){
        for(int i = start; i <= end; i+=increment){
            data.add(function(i));
            xValues.add(i);
        }
        return data;
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
    public ArrayList<Double> saltValues(int min, int max){
        for (int i = 0; i < data.size(); i++) {
            double saltedValue = salt(data.get(i), min, max);
            saltedData.add(saltedValue);
        }
        return saltedData;    
    }

   /**
    * This method smooths the data
    * @param windowValue the window value is the number of values to the left and right of the current value
    */
    public ArrayList<Double> smoother(int windowValue){
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
        return smoothedData;
    }

    public ArrayList<Integer> getX(){
        return xValues;
    }

    /**
     * This method prints the data for testing purposes
     */
    public void printArrays(){
        ArrayList<Double> data = plotData(-100, 100, 1);
        ArrayList<Double> salted = saltValues(0,10);
        ArrayList<Double> smoothed = smoother(10);
        ArrayList<Integer> xValues = getX();

        for (int i = 0; i < data.size(); i++){
            System.out.println("X: " + xValues.get(i) + " Y: " + data.get(i));
        }
        for (int i = 0; i < salted.size(); i++){
            System.out.println("X: " + xValues.get(i) + " Y: " + salted.get(i));
        }
        for (int i = 0; i < smoothed.size(); i++){
            System.out.println("X: " + xValues.get(i) + " Y: " + smoothed.get(i));
        }

    }

    

    public void run(){
    printArrays();
    }
}
