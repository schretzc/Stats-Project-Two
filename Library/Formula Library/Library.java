import java.math.BigInteger;
import java.util.Arrays;
public class Library
{

    /**
     * Library of methods for statistics in the second half of the semester
     */
    public Library(){

    }

    public Library(double[] userInput){

    }

      /**
       * calculates the factorial of user input returning as a long.
       * @param userInput input integer
       * @return returns the factorial of userinput as biginteger type
       */
      public BigInteger factorialBigInt(int userInput){
        BigInteger factorial = BigInteger.valueOf(userInput);
        if (userInput == 0){
            return BigInteger.valueOf(1);
        }
        for (int i = 1; i < userInput; i++){
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }

        return factorial;
      }

        /**
         *  returns poisson distribution
         * @param lambda number of events per unit of time
         * @param x number of units
         * @return returns poisson distribution as a double
         */
        public double poissonDistribution(double lambda, int x){
            double poisson = (Math.pow(Math.E, -lambda) * Math.pow(lambda, x)) / (factorialBigInt(x).doubleValue());
            return poisson;
        }

        /**
         * returns expected value of poisson distribution
         * @param lambda number of events per unit of time
         * @return returns expected value of poisson distribution as a double
         */
        public double expectedValuePD(double lambda){
            return lambda;
        }

        /**
         * returns variance of poisson distribution
         * @param lambda number of events per unit of time
         * @return returns variance of poisson distribution as a double
         */
        public double variancePD(double lambda){
            return lambda;
        }

        /**
         * takes in a k value and returns the chebyshev value
         * @param k K value
         * @return returns chebyshev as a double
         */
        public double chebyshev(double k){
            double kSquared = Math.pow(k, 2);
            double chebyshev = 1 - (1/kSquared);
            return chebyshev;
        }

        public double uniformDistribution(double a, double b){
            double bmina = b-a;
            double uniformDistribution = 1 / bmina;

            return uniformDistribution;
        }

        public double expectedValueUD(double a, double b){
            double expectedValue = (a + b)/(2);
            return expectedValue;
        }
        public double varianceUD(double a, double b){
            double variance = Math.pow(b-a, 2)/(12);
            return variance;
        }



    /**
     * test method for all of the methods in the class
     */
    public void runTest(){

        System.out.println("The poisson distribution is " + poissonDistribution(2, 4));
        System.out.println("expected result .0902........");
        System.out.println("The expected value of the poisson distribution is " + expectedValuePD(1));
        System.out.println("expected result 1");
        System.out.println("The variance of the poisson distribution is " + variancePD(1));
        System.out.println("expected result 1");
        System.out.println();

        System.out.println("The Chebyshev value is " + chebyshev(2.085));
        System.out.println("expected result .77");
        System.out.println();

        System.out.println("The uniform distribution is " + uniformDistribution(0,25));
        System.out.println("expected result .04");
        System.out.println("The expected value of the uniform distribution is " + expectedValueUD(0, 40));
        System.out.println("expected result 20");
        System.out.println("The variance of the uniform distribution is " + varianceUD(0, 40));
        System.out.println("expected result 133.333");
        System.out.println();



    }
    












}