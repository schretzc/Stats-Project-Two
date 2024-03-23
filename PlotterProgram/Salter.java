import java.io.BufferedReader;
import java.io.FileReader;

public class Salter {

    /**
     * This method reads a csv file and prints out the contents
     * I used the following link to learn how to read a csv file as input
     * https://www.youtube.com/watch?v=zKDmzKaAQro
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