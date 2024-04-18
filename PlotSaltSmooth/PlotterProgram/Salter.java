import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
/**
 * write program that accepts a csv with x and y values
 * loop through the y values and +/- a random number from it
 *  the random number comes from a range you can configure
 *  be sure when you are representing the output,
 *      choose a significantly larger salt value
 *      have significant number of data points (no, 10 is not enough) 
 */
public class Salter {
    private ArrayList<Integer> x;
    private ArrayList<Integer> y;
    private String header;
    

    /**
     * Constructor
     */
    public Salter(){
        this.x = new ArrayList<>();
        this.y = new ArrayList<>();
        this.header = "";
    }

    /**
     * This method reads a csv file and prints out the contents
     * I used the following link to learn how to read a csv file as input
     * https://www.youtube.com/watch?v=zKDmzKaAQro
     * https://www.youtube.com/watch?v=Ag-JPiwp1Oo
     * https://www.geeksforgeeks.org/read-file-into-an-array-in-java/
     * //takes csv file as input
     *reads the file and assigns the values to x and y
     *the first value is the x value and the second value is the y value
     *the arraylist x will hold the x values
     *the arraylist y will hold the y values
     *assigns the values to biginteger in arraylist
     * @param file desired file to read from
     */
    public void assignValue(String file){
        BufferedReader reader = null;
        String line = "";

        try{
            reader = new BufferedReader (new FileReader(file));
            header = reader.readLine();
            while((line = reader.readLine()) != null){
                String[] data = line.split(",");
                x.add(Integer.parseInt(data[0]));
                y.add(Integer.parseInt(data[1]));
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

       

        /**
         * This method salts/randomizes the y value
         * www.baeldung.com/java-generating-random-numbers-in-range
         * specifies the range of the random number
         * @param y y value
         * @param min minimum value for the random number
         * @param max maximum value for the random number
         * @return returns the new y value
         */
        public int salt(int y, int min, int max){
            int plusMinus = (int)(Math.random() * 2) + 1;

            if (plusMinus == 1){
            int salt = (int)(Math.random() * (max - min)) + min;
            int result = y + salt;
            return result;
            }
            else {
            int salt = (int)(Math.random() * (max - min)) + min;
            int result = y - salt;
            return result;
            }
        }

        /**
         * This method salt/randomizes the y values in the arraylist
         * @param min minimum value for the random number
         * @param max maximum value for the random number
         */
        public void saltValues(int min, int max){
            for(int i = 0; i < y.size(); i++){
                y.set(i, salt(y.get(i), min, max));
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
            try( FileWriter csvWriter = new FileWriter("SaltedCS.csv")){
                csvWriter.append(header + "\n");
                for(int i = 0; i < x.size(); i++){
                    csvWriter.append(x.get(i) + "," + y.get(i) + "\n");
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        /**
         * This method prints the x and y values to the console for debugging purposes
         */
        public void printArrays(){
            System.out.println(header);
            for(int i = 0; i < x.size(); i++){
                System.out.println(x.get(i) + "," + y.get(i));
            }
        }
        /**
         * this method runs the program
         */
        public void run(){
            assignValue("/Users/chris/Documents/Stockton/Spring 2024/Stats/Stats Project Two/PlotterProgram/CSV/ProgramPlotterCS.csv");
            saltValues(-100000, 100000);
            exportData();
        }
        
}