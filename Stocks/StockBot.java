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
        this.balance = 10000;
    }

    public void assignPrice(String file){
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

        public void assignRSI(String file){
            BufferedReader reader = null;
            String line = "";
    
            try{
                reader = new BufferedReader (new FileReader(file));
                String header = reader.readLine();
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

            //track each day and make a decision based on the RSI and moving average
            //return balance at the end of the period
            //sell if RSI > 70 and price > moving average
            //buy if RSI < 30 and price < moving average
            //hold if neither
            public double rSIAlgorithm(){
                for(int i = 0; i < date.size(); i++){
                    if(rsi.get(i) > 70 && price.get(i) > movAvg.get(i)){
                        balance += price.get(i);
                    }
                    else if(rsi.get(i) < 30 && price.get(i) < movAvg.get(i)){
                        balance -= price.get(i);
                    }
                }
                return balance;
            }
            
              

            //prints all arrays for testing
            public void printValues(){
                for(int i = 0; i < date.size(); i++){
                    System.out.println(date.get(i) + " " + price.get(i) + " " + rsi.get(i) + " " + movAvg.get(i));
                }
            }
                

            public void run(){
                assignPrice("/Users/chris/Documents/Stockton/Spring 2024/Stats/Stats Project Two/Stocks/CSVs/NFLX.csv");
                assignRSI("/Users/chris/Documents/Stockton/Spring 2024/Stats/Stats Project Two/Stocks/CSVs/RSI.csv");    
                System.out.println(rSIAlgorithm());
            
            }
            
                
            
}
