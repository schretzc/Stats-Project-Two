import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
public class CSVReader{

        private ArrayList<Integer> x;
        private ArrayList<Integer> y;
        private String header;
        
    
        /**
         * Constructor
         */
        public CSVReader(){
            this.x = new ArrayList<>();
            this.y = new ArrayList<>();
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
    public void run(){

    }
    
}