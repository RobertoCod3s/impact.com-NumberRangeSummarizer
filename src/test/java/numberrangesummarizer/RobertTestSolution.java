package numberrangesummarizer;

import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class RobertTestSolution {
    RobertSolution sol = new RobertSolution();
    @Test
    void testNormalCase() {
        String input = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
        Collection<Integer> inputSet = sol.collect(input);
        String expected = "1, 3, 6-8, 12-15, 21-24, 31";
        assertEquals(expected, sol.summarizeCollection(inputSet));
    }

    @Test
    void testAllSingles() {
        String input = "1,3,5,7,9";
        Collection<Integer> inputSet = sol.collect(input);
        String expected = "1, 3, 5, 7, 9";
        assertEquals(expected, sol.summarizeCollection(inputSet));
    }

    @Test
    void testAllConsecutive() {
        String input = "1,2,3,4,5,6";
        Collection<Integer> inputSet = sol.collect(input);
        String expected = "1-6";
        assertEquals(expected, sol.summarizeCollection(inputSet));
    }

    @Test
    void testConsecutiveAtStartAndEnd() {
        String input = "1,2,3,5,7,8,9";
        Collection<Integer> inputSet = sol.collect(input);
        String expected = "1-3, 5, 7-9";
        assertEquals(expected, sol.summarizeCollection(inputSet));
    }

    @Test
    void testSingleNumber() {
        String input = "42";
        Collection<Integer> inputSet = sol.collect(input);
        String expected = "42";
        assertEquals(expected, sol.summarizeCollection(inputSet));
    }

    @Test
    void testTwoConsecutiveNumbers() {
        String input = "7,8";
        Collection<Integer> inputSet = sol.collect(input);
        String expected = "7-8";
        assertEquals(expected, sol.summarizeCollection(inputSet));
    }

    @Test
    void testTwoNonConsecutiveNumbers() {
        String input = "7,9";
        Collection<Integer> inputSet = sol.collect(input);
        String expected = "7, 9";
        assertEquals(expected, sol.summarizeCollection(inputSet));
    }

    @Test
    void testEmptyInput() {
        String input = "";
        Collection<Integer> inputSet = sol.collect(input);
        String expected = "";
        assertEquals(expected, sol.summarizeCollection(inputSet));
    }

    @Test
    void testNegativeNumbers() {
        String input = "-3,-2,-1,0,1,2";
        Collection<Integer> inputSet = sol.collect(input);
        String expected = "-3-2";
        assertEquals(expected, sol.summarizeCollection(inputSet));
    }

    @Test
    void testUnsortedInput() {
        String input = "5,1,3,2,4";
        Collection<Integer> inputSet = sol.collect(input);
        String expected = "1-5";
        assertEquals(expected, sol.summarizeCollection(inputSet));
    }

}
