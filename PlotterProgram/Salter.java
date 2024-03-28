import java.io.BufferedReader;
import java.io.FileReader;
/**
 * write program that accepts a csv with x and y values
 * loop through the y values and +/- a random number from it
 *  the random number comes from a range you can configure
 *  be sure when you are representing the output,
 *      choose a significantly larger salt value
 *      have significant number of data points (no, 10 is not enough) 
 */

public class Salter {

    /**
     * This method reads a csv file and prints out the contents
     * I used the following link to learn how to read a csv file as input
     * https://www.youtube.com/watch?v=zKDmzKaAQro
     * https://www.youtube.com/watch?v=Ag-JPiwp1Oo
     * 
     * @param file desired file to read from
     */
    public void readCSV(String file){

    BufferedReader reader = null;
    String line = "";

    try{
        reader = new BufferedReader (new FileReader(file));
        while((line = reader.readLine()) != null){
            String[] data = line.split(",");
            for(String index : data){
                System.out.println(index);
            }
            System.out.println();
        }
    }
    catch(Exception e){
        e.printStackTrace();
    }
    finally{
        try{
            reader.close();
            }
        catch(Exception e){
            e.printStackTrace();
            }
        }
    }
}