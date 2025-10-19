package numberrangesummarizer;

import java.util.*;

public class RobertSolution implements NumberRangeSummarizer {

    /**
     * Collects numbers from a comma-separated string.
     * Parses the string into integers, sorts them, and removes duplicates.
     */
    @Override
    public Collection<Integer> collect(String input) {
        try {
            // Split input string by commas
            String[] inputArray = input.split(",");
            List<Integer> inputList = new ArrayList<>();

            // Parse each string number to int and add to the list
            for (int i = 0; i < inputArray.length; i++) {
                int intInputNum = Integer.parseInt(inputArray[i]);
                inputList.add(intInputNum);
            }

            // Sort numbers
            Collections.sort(inputList);

            // Remove duplicates using streams and return as a list
            return inputList.stream().distinct().toList();
        } catch (NumberFormatException e) {
            // Return null if any value is not a valid integer
            return null;
        }
    }

    /**
     * Summarizes a collection of integers into a range string.
     */
    @Override
    public String summarizeCollection(Collection<Integer> input) {
        if (input == null) {
            return "";
        } else if (input.size() == 1) {
            // Single element case: remove brackets from toString()
            String inputStr = input.toString();
            return inputStr.substring(1, inputStr.length() - 1);
        }

        // Convert collection to list
        List<Integer> inputList = new ArrayList<>(input);

        // Flags for range tracking
        boolean isRange = false;
        boolean endInRange = false;

        // Result string
        String output = "";

        // Pointers for tracking consecutive numbers
        int p1 = -1;
        int p2 = 0;
        int num1 = -1;
        int num2 = -1;
        int numInRange = 0; // counter for range length

        while (true) {
            boolean done = p2 == input.size() - 1; // check if at last element

            // Increment pointers pi and get current numbers if not at end
            if (p2 < input.size() - 1) {
                p1 += 1;
                p2 += 1;
                num1 = inputList.get(p1);
                num2 = inputList.get(p2);
            }

            if (isNextNumber(num1, num2) && !done) {
                // Current number is consecutive then part of a range
                numInRange++;
                isRange = true;

            } else if (isRange) {
                // Closing a range and getting indices of range
                int newP1 = p1 - numInRange;
                int newP2;

                if (!done) {
                    newP2 = p2 - 1;
                } else {
                    // End of list is in a range
                    endInRange = true;
                    newP1++;
                    newP2 = p2;
                }

                int rangeNum1 = inputList.get(newP1);
                int rangeNum2 = inputList.get(newP2);

                output += rangeNum1 + "-" + rangeNum2 + ", ";

                // Reset range tracking
                numInRange = 0;
                isRange = false;

            } else if (!done) {
                // Single number (not in range)
                output += num1 + ", ";
                isRange = false;
            }

            // Handle last element
            if (done) {
                if (!endInRange) {
                    output += num2;
                } else {
                    // Remove trailing comma and space
                    output = output.substring(0, output.length() - 2);
                }
                break;
            }
        }

        return output;
    }

    /**
     * Checks if two integers are consecutive
     *
     * @param x first number
     * @param y second number
     * @return true if y is exactly one greater than x or y is the next number after x
     */
    public boolean isNextNumber(int x, int y) {
        return y == (x + 1);
    }
}
