import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
//import java.io.FileWriter;

public class StockBot {
    ArrayList<Double> price;
    ArrayList<String> date;
    ArrayList<Double> rsi;
    ArrayList<Double> movAvg;
    double balance;

    public StockBot() {
        this.price = new ArrayList<>();
        this.date = new ArrayList<>();
        this.rsi = new ArrayList<>();
        this.movAvg = new ArrayList<>();
        this.balance = 0;
    }

    public void assignPrice(String file){
        BufferedReader reader = null;
        String line = "";

        try{
            reader = new BufferedReader (new FileReader(file));
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

        public void assignRSI(String file){
            BufferedReader reader = null;
            String line = "";
    
            try{
                reader = new BufferedReader (new FileReader(file));
                while((line = reader.readLine()) != null){
                    String[] data = line.split(",");
                    rsi.add(Double.parseDouble(data[1]));
                    movAvg.add(Double.parseDouble(data[2]));
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

            public void tradeEvaluator(){
                
            }

            public void run(){
                assignPrice("/Users/chris/Documents/Stockton/Spring 2024/Stats/Stats Project Two/Stocks/CSVs/NFLX.csv");
                assignRSI("/Users/chris/Documents/Stockton/Spring 2024/Stats/Stats Project Two/Stocks/CSVs/RSI.csv");            }
                
}
