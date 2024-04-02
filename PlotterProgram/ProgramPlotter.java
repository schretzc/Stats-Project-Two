import java.io.FileWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

public class ProgramPlotter {
    //pick formula
    //write program that plots output in csv
    //pick finction
    //pick starting and ending point for x values, ex, -10.0 to 10.0 in increments of .1
    //plug in those values into your function so that you get a y value
    //store the x and y values into a data structure of your choice

    //use any file exporting technique from your past or the internet(cite it if youre not free handing it) 
    //to export out as your your data.csv

    //plotter result
    //open in excel and create a graph
    //make sure the program accepts parameters to determine the range
    //ie 0-100, -100 to 100
    //also write parameter to cetermine number of points to plot, or the interval between points
    //collect sample outputs of this program in excel chart form showing several different configs


    HashMap<Integer, Integer> data;

    /**
     * Constructor
     * Initialize the hashmap
     * Use hashmap for the purpose of a graph (key, value)
     * where the key is the x value and the data is the output of the graph
     */
    public ProgramPlotter(){
        this.data = new HashMap<Integer, Integer>();
    }

    /**
     * using y = (x^2)+13 as the fuction
     * @param x value to input into the function
     * @return returns the result of the function as bigdecimal
     */
    public int function(int x){
        //BigDecimal result = x.pow(2).add(new BigDecimal(13));
        int result = (x*x) + 13;
     
        return result; 
    } 
    
    /**
     * This method generates data for a given function and stores it in an hashMap
     * @param start start point
     * @param end end point
     * @param increment interval between points
     */
    public void storeData(int start, int end, int increment){
        for(int i = start; i <= end; i++){
            data.put(i, function(i));
        }
    }

    /**
     * This method exports the data to a csv file
     * The data is stored in a hashmap
     * To export the data, I used the guide from the link below
     * https://www.baeldung.com/java-write-hashmap-csv
     * For the loop to iterate through the hashmap, I used the following link
     * https://www.geeksforgeeks.org/write-hashmap-to-a-text-file-in-java/
     * This was perfect since i was already using a hashmap to store the data
     */
    public void exportData(){
       try( FileWriter csvWriter = new FileWriter("ProgramPlotterCS.csv")){
        csvWriter.append("X, (x)^2 + 13\n");
        for(HashMap.Entry<Integer, Integer> entry : data.entrySet()){
            csvWriter.append(entry.getKey() + "," + entry.getValue() + "\n");
        }


       //csvWriter.close(); not needed since try statement will close the file
       } catch (Exception e){
           e.printStackTrace();
       }
    }

    public void run(){
        //storeData(new BigDecimal(-10.0), new BigDecimal(10.0), new BigDecimal(0.1));
        storeData(1,100,1);
        exportData();
    }
}
