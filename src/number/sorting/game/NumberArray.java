package number.sorting.game;

import java.util.ArrayList;
import java.util.Random;

public class NumberArray {
    Random random = new Random();
    ArrayList<Integer> numberArray = new ArrayList<Integer>();
    
    public void generateNumberList(int count, int range) throws IllegalArgumentException {
        if (range < count) {
            throw new IllegalArgumentException("Range cannot be lower than number count!");
        }

        for (int i = 0 ; i < count ; i++) {
            int randomNumber = random.nextInt(range);
            boolean numberInserted = false;

            while (!numberInserted) {
                if (numberArray.contains(randomNumber)) {
                    randomNumber = random.nextInt(range);
                } else {
                    numberArray.add(randomNumber);
                    numberInserted = true;
                }
            }
        }
    }
    
    public int[] getNumberList() {
        int[] array = new int[numberArray.size()];
        for (int i = 0 ; i < numberArray.size() ; i++) {
            array[i] = numberArray.get(i);
        }
        return array;
    }
}
