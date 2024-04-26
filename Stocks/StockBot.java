import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;


public class StockBot {
    ArrayList<Double> price;
    ArrayList<String> date;
    ArrayList<Double> rsi;
    ArrayList<Double> movAvg;
    ArrayList<Double> hodlWorth;
    ArrayList<Double> fidelityWorth;
    ArrayList<Double> funWorth;

    public StockBot() {
        this.price = new ArrayList<>();
        this.date = new ArrayList<>();
        this.rsi = new ArrayList<>();
        this.movAvg = new ArrayList<>();
        this.hodlWorth = new ArrayList<>();
        this.fidelityWorth = new ArrayList<>();
        this.funWorth = new ArrayList<>();
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
            double balance = 10000;
            double stocks= 0;
            double netWorth = 0;
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
                        double sellAmount = stocks * 0.3;
                        if (sellAmount > stocks){
                            sellAmount = stocks;
                        }
                        balance += sellAmount * price.get(i);
                        stocks -= sellAmount;
                        System.out.println("Sold " + sellAmount + " stocks at price " + price.get(i));
                    } else if (rsi.get(i) < 30) { //&& price.get(i) < movAvg.get(i)
                        double amount = (balance / price.get(i)) * 0.5;
                        balance -= amount;
                        stocks += amount;
                        System.out.println("Bought " + amount + " stocks at price " + price.get(i));
                    } else {
                        System.out.println("No action taken.");
                    }
                }
                
                netWorth = Math.round(balance + (stocks * price.get(i)));
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
                double balance = 10000;
                double stocks= 0;
                double netWorth = 0;
                for (int i = 0; i < date.size(); i++) {
                    if (rsi.get(i) > 70) { //price.get(i) > movAvg.get(i)
                            double sellAmount = stocks * 0.3;
                            if (sellAmount > stocks){
                                sellAmount = stocks;
                            }
                            balance += sellAmount * price.get(i);
                            stocks -= sellAmount;
                          //  System.out.println("sold " + amount + " stocks at price " + price.get(i));
                        } else if (rsi.get(i) < 30) { //&& price.get(i) < movAvg.get(i)
                            double amount = (balance / price.get(i)) * 0.5;
                            balance -= amount;
                            stocks += amount;
                           // System.out.println("bought " + amount + " stocks at price " + price.get(i));
                        } else {
                           // System.out.println("didnt do shit");
                        }
                    netWorth = Math.round(balance + (stocks * price.get(i)));
                    fidelityWorth.add(netWorth); 
                }
                return netWorth;
            }


            public double customAlgorithm() {
                double balance = 10000;
                double stocks= 0;
                double netWorth = 0;
                for (int i = 0; i < date.size(); i++) {
                    if (rsi.get(i) > 70) { //price.get(i) > movAvg.get(i)
                            double sellAmount = stocks * 0.3;
                            if (sellAmount > stocks){
                                sellAmount = stocks;
                            }
                            balance += sellAmount * price.get(i);
                            stocks -= sellAmount;
                          //  System.out.println("sold " + amount + " stocks at price " + price.get(i));
                        } else if (rsi.get(i) < 30) { //&& price.get(i) < movAvg.get(i)
                            double amount = (balance / price.get(i)) * 0.5;
                            balance -= amount;
                            stocks += amount;
                           // System.out.println("bought " + amount + " stocks at price " + price.get(i));
                        } else {
                           // System.out.println("didnt do shit");
                        }
                    netWorth = Math.round(balance + (stocks * price.get(i)));
                    funWorth.add(netWorth); 
                }
                return netWorth;
            }
              

            //prints all arrays for testing
            public void printValues(){
                for(int i = 0; i < date.size(); i++){
                    System.out.println(date.get(i) + " " + price.get(i) + " " + rsi.get(i) + " " + movAvg.get(i));
                }
            }

            //prints fidelity data
            public void printFidelityWorth(){
                System.out.println("Fidelity Worth");
                for(int i = 0; i < fidelityWorth.size(); i++){
                    System.out.println(fidelityWorth.get(i));
                }
            }
            //exports fidelity data
            public void exportFidelityData(){
                try( FileWriter csvWriter = new FileWriter("FidelityNFLX.csv")){
                    csvWriter.append("Date,Net Worth" + "\n");
                    for(int i = 0; i < date.size(); i++){
                        csvWriter.append(date.get(i) + "," + fidelityWorth.get(i)+ "\n");
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            //exports hodl data
            public void exportHodlData(){
                try( FileWriter csvWriter = new FileWriter("HodlNFLX.csv")){
                    csvWriter.append("Date,Net Worth" + "\n");
                    for(int i = 0; i < date.size(); i++){
                        csvWriter.append(date.get(i) + "," + hodlWorth.get(i)+ "\n");
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

            //exports hodl data
            public void exportCustomAlgorithmData(){
                try( FileWriter csvWriter = new FileWriter("CustomAlgorithmNFLX.csv")){
                    csvWriter.append("Date,Net Worth" + "\n");
                    for(int i = 0; i < date.size(); i++){
                        csvWriter.append(date.get(i) + "," + funWorth.get(i)+ "\n");
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }


                

            public void run(){
                 assignPrice("/Users/chris/Documents/Stockton/Spring 2024/Stats/Stats Project Two/Stocks/CSVs/NFLX.csv");
                 assignRSI("/Users/chris/Documents/Stockton/Spring 2024/Stats/Stats Project Two/Stocks/CSVs/NFLXRSI.csv");
                 System.out.println(fidelityAlgorithm());
                 System.out.println(hodl());
                 exportHodlData();
                 exportFidelityData();
            }
}
