import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
public class RSI{

        private ArrayList<Integer> price;
        private ArrayList<String> date;
        private String header;
        
    
        /**
         * Constructor
         */
        public RSI(){
            this.price = new ArrayList<>();
            this.date = new ArrayList<>();
            this.header = "";
        }

        public void assignValue(String file){
            BufferedReader reader = null;
            String line = "";
    
            try{
                reader = new BufferedReader (new FileReader(file));
                header = reader.readLine();
                while((line = reader.readLine()) != null){
                    String[] data = line.split(",");
                    date.add((data[0]));
                    price.add(Integer.parseInt(data[1]));
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



            public void exportData(){
                try( FileWriter csvWriter = new FileWriter("RSI.csv")){
                    csvWriter.append(header + "\n");
                    for(int i = 0; i < date.size(); i++){
                        csvWriter.append(date.get(i) + "," + price.get(i) + "\n");
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
    public void run(){

    }
    
}