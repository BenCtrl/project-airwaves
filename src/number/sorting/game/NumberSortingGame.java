package number.sorting.game;

import java.util.Scanner;

public class NumberSortingGame {
    int[] numbers;

    public static void main(String[] args) {
        try {
            // Initialize game objects & core functionality
            Scanner userInput = new Scanner(System.in);
            NumberSortingGame game = new NumberSortingGame();
            NumberArray numberArray = new NumberArray();
            int range, count, selectedNumber, newLocation;

            System.out.print("Choose a range for numbers to be generated within (from zero): ");
            range = userInput.nextInt();

            System.out.print("How many numbers in the list?: ");
            count = userInput.nextInt();

            numberArray.generateNumberList(count, range);
            game.numbers = numberArray.getNumberList();

            game.printNumberList();
            
            // game loop
            while (game.numbersSorted(game.numbers) == false) {
                System.out.print("Select a number: ");
                selectedNumber = userInput.nextInt();

                System.out.print("Select a new location: ");
                newLocation = userInput.nextInt();

                game.shuffleNumberList(selectedNumber, newLocation);
            }
            
            System.out.println("GAME COMPLETE!!!!");
        } catch(IllegalArgumentException e) {
            System.out.println("Something went tits up!");
        }
    }
    
    public void shuffleNumberList(int selectedNumber, int newLocation) {
        // Gather data from player input
        int selectedNumberIndex = this.indexOf(numbers, selectedNumber);
        int newLocationIndex = newLocation - 1;
        
        if (selectedNumberIndex == -1) {
            System.out.println("ERROR | Could not find index for number '" + selectedNumber + "'");
        } else if (newLocationIndex == -1) {
            System.out.println("ERROR | Could not find index for new location");
        }
        
        if (selectedNumberIndex > newLocationIndex) {
            // Shuffle DOWN
            int pointer = 0;    //Used to point at the selectedNumber
            int j = 0;  //Used to increment the pointer backwards to count DOWN the array.

            for (int i = newLocationIndex; i < selectedNumberIndex; i++) {
                pointer = selectedNumberIndex - j;
                int temp = numbers[pointer - 1];

                numbers[pointer - 1] = numbers[pointer];
                numbers[pointer] = temp;
                j++;
            }
        } else {
            // Shuffle UP
            for(int i=selectedNumberIndex; i <= newLocationIndex - 1; i++) {
                int temp = numbers[i + 1];
                numbers[i + 1] = selectedNumber;
                numbers[i] = temp;
            }
        }
        
        // Shuffle Number List
        System.out.println("\n \n \n \n \n \n \n \n \n \n \n");
        for (int number: this.numbers) {
            System.out.print(number+", ");
        }
        System.out.println("");
    }

    boolean numbersSorted(int[] numberArray) {
        int arrayLength = numberArray.length;
        
        for (int i = 0; i < arrayLength; i++) {
            if ( i + 1 != arrayLength && numberArray[i] > numberArray[i + 1]) {
                return false;
            } else {
                // Resume checking
            }
        }

        return true;
    }

    public int[] shuffle(int[] numberArray, int lowestIndex, int highestIndex, int selectedNumber) {
        for (int i = lowestIndex ; i < highestIndex ; i++) {
            int temp = numberArray[i + 1];
            numberArray[i + 1] = selectedNumber;
            numberArray[i] = temp;
        }
        return numberArray;
    }
    
    public int[] reverseArray(int[] array) {
        for(int i=0; i < (array.length/2); i++) {
            int temp = 0;
            if (i != (array.length - 1)) {
                temp = array[array.length-(i+1)];
                array[array.length-(i+1)] = array[i];
                array[i] = temp;
            } else {
               // 
            }
        }
        return array;
    }
    
    public int findNumberIndex(int[] numberArray, int target) {
        int targetIndex = 0;
        for (int i = 0 ; i < numberArray.length ; i++){
            if (numberArray[i] == target) {
                targetIndex = i;
                i = numberArray.length;
            }
        }
        return targetIndex;
    }
    
    public int indexOf(int[] numberList, int givenNumber) {
        for (int i=0 ; i < numberList.length ; i++) {
            if(numberList[i] == givenNumber) {
                return i;
            }
        }
        return -1;
    }
    
    private void printNumberList() {
        for (int number : this.numbers) {
            System.out.print(number + ", ");
        }
        System.out.print("\n");
    }
}
