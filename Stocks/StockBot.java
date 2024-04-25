import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
//import java.io.FileWriter;

public class StockBot {
    ArrayList<Double> price;
    ArrayList<String> date;
    ArrayList<Double> rsi;
    ArrayList<Double> movAvg;
    ArrayList<Double> hodlWorth;
    ArrayList<Double> fidelityWorth;
    double balance;
    double stocks;
    double netWorth;

    public StockBot() {
        this.price = new ArrayList<>();
        this.date = new ArrayList<>();
        this.rsi = new ArrayList<>();
        this.movAvg = new ArrayList<>();
        this.balance = 100000;
        this.stocks = 0;
        this.netWorth = 100000;
        this.hodlWorth = new ArrayList<>();
        this.fidelityWorth = new ArrayList<>();
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

            //hold for a year
          // Hold for a year
          ////sell if RSI > 70 and price > moving average
            //buy if RSI < 30 and price < moving average
            //updates balance, stocks, and returns net worth at the end while adding net worth to the array
            //track each day and make a decision based on the RSI and moving average
            //return balance at the end of the period
            //sell if RSI > 70 and price > moving average
            //buy if RSI < 30 and price < moving average
            //hold if neither
            //uses moving average and rsi to make decisions
            //when buying, buy 70% when selling, sell 40%
        public double hodl() {
            for (int i = 0; i < date.size(); i++) {
                System.out.println("date: " + date.get(i) + ", balance: " + balance + ", stocks: " + stocks + ", Net worth: " + netWorth);
                
                if (i == 0) {
                    double amount = (balance / price.get(i)) * 0.5;
                    balance -= amount;
                    stocks += amount;
                    System.out.println("initially purchase: Bought " + amount + " stocks at price " + price.get(i));
                }
                
                if (i > 52) {
                    if (rsi.get(i) > 70) { //price.get(i) > movAvg.get(i)
                        double amount = (balance / price.get(i)) * 0.2;
                        balance += amount;
                        stocks -= amount;
                        System.out.println("Sold " + amount + " stocks at price " + price.get(i));
                    } else if (rsi.get(i) < 40) { //&& price.get(i) < movAvg.get(i)
                        double amount = (balance / price.get(i)) * 0.5;
                        balance -= amount;
                        stocks += amount;
                        System.out.println("Bought " + amount + " stocks at price " + price.get(i));
                    } else {
                        System.out.println("No action taken.");
                    }
                }
                
                netWorth = balance + (stocks * price.get(i));
                hodlWorth.add(netWorth); 
            }
            return netWorth;
        }
        
            //track each day and make a decision based on the RSI and moving average
            //return balance at the end of the period
            //sell if RSI > 70 and price > moving average
            //buy if RSI < 30 and price < moving average
            //hold if neither
            //uses moving average and rsi to make decisions
            //when buying, buy 70% when selling, sell 40%
            public double fidelityAlgorithm() {
                for (int i = 0; i < date.size(); i++) {
                   // System.out.println("date: " + date.get(i) + ", balance: " + balance + ", stocks: " + stocks + ", Net worth: " + netWorth);
                    
                    if (i == 0) {
                        double amount = (balance / price.get(i)) * 0.5;
                        balance -= amount;
                        stocks += amount;
                        //System.out.println("initially purchase: bought " + amount + " stocks at price " + price.get(i));
                    }
                    
                    
                        if (rsi.get(i) > 70) { //price.get(i) > movAvg.get(i)
                            double amount = (balance / price.get(i)) * 0.2;
                            balance += amount;
                            stocks -= amount;
                          //  System.out.println("sold " + amount + " stocks at price " + price.get(i));
                        } else if (rsi.get(i) < 40) { //&& price.get(i) < movAvg.get(i)
                            double amount = (balance / price.get(i)) * 0.5;
                            balance -= amount;
                            stocks += amount;
                           // System.out.println("bought " + amount + " stocks at price " + price.get(i));
                        } else {
                           // System.out.println("didnt do shit");
                        }
                    netWorth = balance + (stocks * price.get(i));
                    fidelityWorth.add(netWorth); 
                }
                return netWorth;
            }
            
              

            //prints all arrays for testing
            public void printValues(){
                for(int i = 0; i < date.size(); i++){
                    System.out.println(date.get(i) + " " + price.get(i) + " " + rsi.get(i) + " " + movAvg.get(i));
                }
            }

            public void printFidelityWorth(){
                System.out.println("Fidelity Worth");
                for(int i = 0; i < fidelityWorth.size(); i++){
                    System.out.println(fidelityWorth.get(i));
                }
            }
                

            public void run(){
                StockBot hodl = new StockBot();
                StockBot fid = new StockBot();
                fid.assignPrice("/Users/chris/Documents/Stockton/Spring 2024/Stats/Stats Project Two/Stocks/CSVs/NFLX.csv");
                fid.assignRSI("/Users/chris/Documents/Stockton/Spring 2024/Stats/Stats Project Two/Stocks/CSVs/NFLXRSI.csv");
                hodl.assignPrice("/Users/chris/Documents/Stockton/Spring 2024/Stats/Stats Project Two/Stocks/CSVs/NFLX.csv");
                hodl.assignRSI("/Users/chris/Documents/Stockton/Spring 2024/Stats/Stats Project Two/Stocks/CSVs/NFLXRSI.csv");    
                System.out.println("HODL net worth"+hodl.hodl());
              // System.out.println("FIDELITY net worth"+ fid.fidelityAlgorithm());
               //fid.printWeeklyWorth();
                
                
            
            }
            
                
            
}
