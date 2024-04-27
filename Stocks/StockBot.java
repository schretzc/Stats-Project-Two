import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * this runs stock trading algorithms from given files. It uses data input as csv files.
 */
public class StockBot {
    ArrayList<Double> price;
    ArrayList<String> date;
    ArrayList<Double> rsi;
    ArrayList<Double> movAvg;
    ArrayList<Double> hodlWorth;
    ArrayList<Double> fidelityWorth;
    ArrayList<Double> funWorth;

    /**
     * Constructor
     */
    public StockBot() {
        this.price = new ArrayList<>();
        this.date = new ArrayList<>();
        this.rsi = new ArrayList<>();
        this.movAvg = new ArrayList<>();
        this.hodlWorth = new ArrayList<>();
        this.fidelityWorth = new ArrayList<>();
        this.funWorth = new ArrayList<>();
    }

    /**
     * This method reads a csv file and assigns contents to arraylists for price and date
     * @param file desired file to read from
     */
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

        /**
         * This method reads a csv file and assigns contents to arraylists for rsi
         * @param file desired file to read from
         */
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

         
         /**
          * HODL buys on day one, holds for a year, and then turns into fidelity algorthim.
          * @return
          */ 
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
        
            /**
             * Fidelity algorithm that is less risky and a common approach
             * @return returns the fidelity algorithm as a double
             */
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
                        } else if (rsi.get(i) < 30) { //&& price.get(i) < movAvg.get(i)
                            double amount = (balance / price.get(i)) * 0.5;
                            balance -= amount;
                            stocks += amount;
                        } else {
                        }
                    netWorth = Math.round(balance + (stocks * price.get(i)));
                    fidelityWorth.add(netWorth); 
                }
                return netWorth;
            }


            /**
             * CUSTOM algorithm that is more risky than the fidelity algorithm
             * @return returns the custom algorithm as a double
             */
            public double customAlgorithm() {
                double balance = 10000;
                double stocks= 0;
                double netWorth = 0;
                for (int i = 0; i < date.size(); i++) {
                    if (rsi.get(i) > 80) { //price.get(i) > movAvg.get(i)
                            double sellAmount = stocks * 0.3;
                            if (sellAmount > stocks){
                                sellAmount = stocks;
                            }
                            balance += sellAmount * price.get(i);
                            stocks -= sellAmount;
                        } else if (rsi.get(i) < 20) { //&& price.get(i) < movAvg.get(i)
                            double amount = (balance / price.get(i)) * 0.5;
                            balance -= amount;
                            stocks += amount;
                        } else {
                        }
                    netWorth = Math.round(balance + (stocks * price.get(i)));
                    funWorth.add(netWorth); 
                }
                return netWorth;
            }
              

            /**
             * prints all values from testing
             */
            public void printValues(){
                for(int i = 0; i < date.size(); i++){
                    System.out.println(date.get(i) + " " + price.get(i) + " " + rsi.get(i) + " " + movAvg.get(i));
                }
            }

            /**
             * prints fidelity worth
             */
            public void printFidelityWorth(){
                System.out.println("Fidelity Worth");
                for(int i = 0; i < fidelityWorth.size(); i++){
                    System.out.println(fidelityWorth.get(i));
                }
            }
            /**
             * exports fidelity data
             */
            public void exportFidelityData(){
                try( FileWriter csvWriter = new FileWriter("FidelityWBA.csv")){
                    csvWriter.append("Date,Net Worth" + "\n");
                    for(int i = 0; i < date.size(); i++){
                        csvWriter.append(date.get(i) + "," + fidelityWorth.get(i)+ "\n");
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            /**
             * exports hodl data
             */
            public void exportHodlData(){
                try( FileWriter csvWriter = new FileWriter("HodlWBA.csv")){
                    csvWriter.append("Date,Net Worth" + "\n");
                    for(int i = 0; i < date.size(); i++){
                        csvWriter.append(date.get(i) + "," + hodlWorth.get(i)+ "\n");
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

            /**
             * exports custom algorithm data
             */
            public void exportCustomAlgorithmData(){
                try( FileWriter csvWriter = new FileWriter("CustomAlgorithmWBA.csv")){
                    csvWriter.append("Date,Net Worth" + "\n");
                    for(int i = 0; i < date.size(); i++){
                        csvWriter.append(date.get(i) + "," + funWorth.get(i)+ "\n");
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }


                

            public void run(){
                 assignPrice("/Users/chris/Documents/Stockton/Spring 2024/Stats/Stats Project Two/Stocks/CSVs/WBA/WBA.csv");
                 assignRSI("/Users/chris/Documents/Stockton/Spring 2024/Stats/Stats Project Two/Stocks/CSVs/WBA/WBARSI.csv");
                // System.out.println(fidelityAlgorithm());
                 //System.out.println(hodl());
                 //exportHodlData();
                 //exportFidelityData();
                 fidelityAlgorithm();
                 exportFidelityData();
                 hodl();
                 exportHodlData();
                 customAlgorithm();
                 exportCustomAlgorithmData();
            }
}
