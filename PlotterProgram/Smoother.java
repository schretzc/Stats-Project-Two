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
public class Smoother {
    private ArrayList<Double> x;
    private ArrayList<Double> y;
    private String header;

    /**
     * Constructor
     */
    public Smoother(){
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
                x.add(Double.parseDouble(data[0]));
                y.add(Double.parseDouble(data[1]));
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
         * This method takes the average of the windowed points and replaces the y value with that amount
         * @param windowValue number of points to left and right of the point
         */
        public void smoother(int windowValue){
            for(int i = 0; i < y.size(); i++){ 
                int count = 0;
                double sum = 0;
                for(int j = i - windowValue; j <= i + windowValue; j++){
                   if(j >= 0 && j < y.size()){
                    sum+= y.get(j);
                    count++;
                   }
                }
                double average = sum / count;
                double roundedAverage = Math.round(average);
                y.set(i, roundedAverage); 
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
            try( FileWriter csvWriter = new FileWriter("SmoothieCS.csv")){
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
         * This method runs the program
         */
        public void run(){
            assignValue("/Users/chris/Documents/Stockton/Spring 2024/Stats/Stats Project Two/PlotterProgram/CSV/SaltedCS.csv");
            smoother(60);
            exportData();
        }

        
}
