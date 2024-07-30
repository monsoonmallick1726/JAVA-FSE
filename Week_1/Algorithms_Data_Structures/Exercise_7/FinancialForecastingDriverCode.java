import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class FinancialForecasting {
    private static Map<Integer, Double> memo = new HashMap<>();

    /**
     * Predicts future values based on past growth rates using a recursive approach.
     * 
     * @param pastValues List of past values
     * @param yearsToPredict Number of years to predict into the future
     * @return Predicted future value
     */
    public static double predictFutureValue(List<Double> pastValues, int yearsToPredict) {
        // Check if we've already calculated this value
        if (memo.containsKey(yearsToPredict)) {
            return memo.get(yearsToPredict);
        }

        // Base case: if we're not predicting any years into the future, return the last known value
        if (yearsToPredict == 0) {
            return pastValues.get(pastValues.size() - 1);
        }
        
        // Base case: if we only have one past value, we can't calculate growth rate
        if (pastValues.size() < 2) {
            throw new IllegalArgumentException("Need at least two past values to predict future value");
        }
        
        // Calculate the average growth rate from past values
        double avgGrowthRate = calculateAverageGrowthRate(pastValues);
        
        // Add the predicted value for the next year to the list
        double nextValue = pastValues.get(pastValues.size() - 1) * (1 + avgGrowthRate);
        pastValues.add(nextValue);
        
        // Recursive call to predict the next year
        double result = predictFutureValue(pastValues, yearsToPredict - 1);
        
        // Store the calculated value in the memo before returning
        memo.put(yearsToPredict, result);
        return result;
    }

    /**
     * Predicts future values based on past growth rates using an iterative approach.
     * 
     * @param pastValues List of past values
     * @param yearsToPredict Number of years to predict into the future
     * @return Predicted future value
     */
    public static double predictFutureValueIterative(List<Double> pastValues, int yearsToPredict) {
        if (yearsToPredict == 0) {
            return pastValues.get(pastValues.size() - 1);
        }
        if (pastValues.size() < 2) {
            throw new IllegalArgumentException("Need at least two past values to predict future value");
        }
        
        double avgGrowthRate = calculateAverageGrowthRate(pastValues);
        double currentValue = pastValues.get(pastValues.size() - 1);
        
        for (int i = 0; i < yearsToPredict; i++) {
            currentValue *= (1 + avgGrowthRate);
        }
        
        return currentValue;
    }

    /**
     * Calculates the average growth rate from a list of past values.
     * 
     * @param values List of past values
     * @return Average growth rate
     */
    private static double calculateAverageGrowthRate(List<Double> values) {
        double totalGrowthRate = 0;
        for (int i = 1; i < values.size(); i++) {
            double growthRate = (values.get(i) - values.get(i-1)) / values.get(i-1);
            totalGrowthRate += growthRate;
        }
        return totalGrowthRate / (values.size() - 1);
    }

    /**
     * Clears the memoization cache.
     */
    public static void clearMemo() {
        memo.clear();
    }
}

public class FinancialForecastingDriverCode {
    public static void main(String[] args) {
        List<Double> pastValues = new ArrayList<>(Arrays.asList(100.0, 105.0, 110.25, 115.76));
        int yearsToPredict = 5;
        
        try {
            System.out.println("Recursive Approach:");
            double predictedValue = FinancialForecasting.predictFutureValue(new ArrayList<>(pastValues), yearsToPredict);
            System.out.printf("Predicted value after %d years: %.2f%n", yearsToPredict, predictedValue);
            
            System.out.println("\nYearly predictions (Recursive):");
            for (int i = 0; i < pastValues.size() + yearsToPredict; i++) {
                double value = FinancialForecasting.predictFutureValue(new ArrayList<>(pastValues), i);
                System.out.printf("Year %d: %.2f%n", i, value);
            }

            System.out.println("\nIterative Approach:");
            double predictedValueIterative = FinancialForecasting.predictFutureValueIterative(pastValues, yearsToPredict);
            System.out.printf("Predicted value after %d years: %.2f%n", yearsToPredict, predictedValueIterative);

            System.out.println("\nYearly predictions (Iterative):");
            for (int i = 0; i < pastValues.size() + yearsToPredict; i++) {
                double value = FinancialForecasting.predictFutureValueIterative(pastValues, i);
                System.out.printf("Year %d: %.2f%n", i, value);
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Demonstrate memoization
        System.out.println("\nDemonstrating memoization:");
        FinancialForecasting.clearMemo();
        long startTime = System.nanoTime();
        FinancialForecasting.predictFutureValue(new ArrayList<>(pastValues), 100);
        long endTime = System.nanoTime();
        System.out.printf("First call duration: %d ns%n", endTime - startTime);

        startTime = System.nanoTime();
        FinancialForecasting.predictFutureValue(new ArrayList<>(pastValues), 100);
        endTime = System.nanoTime();
        System.out.printf("Second call duration (memoized): %d ns%n", endTime - startTime);
    }
}
