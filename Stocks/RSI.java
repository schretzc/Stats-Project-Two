import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
/**
 * https://www.macroption.com/rsi-calculation/
 */
public class RSI{

        private ArrayList<Double> price;
        private ArrayList<String> date;
        private ArrayList<Double> rsi;
        private ArrayList<Double> movAvg;
        
    
        /**
         * Constructor
         */
        public RSI(){
            this.price = new ArrayList<>();
            this.date = new ArrayList<>();
            this.rsi = new ArrayList<>();
            this.movAvg = new ArrayList<>();
        }

        public void assignValue(String file){
            BufferedReader reader = null;
            String line = "";
            try{
                reader = new BufferedReader (new FileReader(file));
                String header = reader.readLine();
                while((line = reader.readLine()) != null){
                    String[] data = line.split(",");
                    date.add((data[0]));
                    price.add(Double.parseDouble(data[4]));
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
             * calculates the RSA of a given period
             * @param n period of RSI
             */
            public double calcRSI(int window, int index){ //common period = 14
                double avgU;
                double avgD;
                double rS; //avgU / avgD
                double u;
                double uCount = 0;
                double dCount = 0;
                double d;
                for (int i = index - window + 1; i < index; i++){
                    double change = price.get(i) - price.get(i-1);
                    if (change > 0){
                        u = change;
                        uCount += u;
                    }
                    else{
                        u = 0;
                        d = Math.abs(change);
                        dCount += d;
                    }
                }
                avgU = uCount / window;
                avgD = dCount / window;

                rS = avgU / avgD;
                double rsi = 100 - (100 / (1 + rS));
                return rsi;
                }

                public void assignRSI(int window){

                    for (int i = 0; i < date.size(); i++){
                        if (i < window){
                            rsi.add(0.0);
                        }
                        else{
                            double roundedRSI = Math.round(calcRSI(window, i) * 1000.0) / 1000.0; //https://www.baeldung.com/java-round-decimal-number
                            rsi.add(roundedRSI);
                        }
                    }
                }

                public void movingAverage(int windowValue){
                    ArrayList<Double> temp = new ArrayList<>(rsi);
                    for(int i = 0; i < temp.size(); i++){ 
                        int count = 0;
                        double sum = 0;
                        for(int j = i - windowValue; j <= i + windowValue; j++){
                           if(j >= 0 && j < temp.size()){
                            sum+= temp.get(j);
                            count++;
                           }
                        }
                        double average = sum / count;
                        double roundedAverage = Math.round(average * 1000.0) / 1000.0;
                        movAvg.add(roundedAverage);
                        }
                }
                    



            public void exportData(){
                try( FileWriter csvWriter = new FileWriter("RSI.csv")){
                    csvWriter.append("Date,RSI,Moving Average" + "\n");
                    for(int i = 0; i < date.size(); i++){
                        csvWriter.append(date.get(i) + "," + rsi.get(i) + "," + movAvg.get(i) + "\n");
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
    public void run(){
        assignValue("/Users/chris/Documents/Stockton/Spring 2024/Stats/Stats Project Two/Stocks/CSVs/NFLX.csv");
        assignRSI(14);
        movingAverage(4);
        exportData();

    }
    
}