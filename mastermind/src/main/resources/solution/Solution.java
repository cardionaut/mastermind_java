package mastermind.src.main.resources.solution;

import java.util.Random;
import mastermind.src.main.java.App;

public class Solution {
    private static final Random generator = new Random();

    public static int[] generateSolution() {
        int[] solution = new int[App.NUM_TO_GUESS];
        for (int i = 0; i < App.NUM_TO_GUESS; i++) {
            solution[i] = generator.nextInt(App.COLORS.length);
        }
        return solution;
    }
}
