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
            System.out.println("ERROR | " + e.getMessage());
        }
    }
    
    public void shuffleNumberList(int selectedNumber, int newLocation) {
        // Process indexes of desired numbers in list
        int selectedNumberIndex = this.indexOf(numbers, selectedNumber);
        int newLocationIndex = newLocation - 1;
        
        if (selectedNumberIndex == -1) {
            System.out.println("ERROR | Could not find index for number '" + selectedNumber + "'");
        } else if (newLocation < 0 || newLocation >= numbers.length) { 
            System.out.println("ERROR | Invalid location");
        } else {
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
    }

    // Validates number array is in correct ascending order
    private boolean numbersSorted(int[] numberArray) {
        int arrayLength = numberArray.length;
        
        for (int i = 0; i < arrayLength; i++) {
            if ( i + 1 != arrayLength && numberArray[i] > numberArray[i + 1]) {
                return false;
            }
        }
        return true;
    }
    
    // Reverse the values for an array
    public int[] reverseArray(int[] array) {
        for(int i=0; i < (array.length/2); i++) {
            int temp = array[array.length-(i+1)];
            array[array.length-(i+1)] = array[i];
            array[i] = temp;
        }
        return array;
    }
    
    // Returns the index of the provided expected number in a given array
    public int indexOf(int[] numberList, int givenNumber) {
        for (int i=0 ; i < numberList.length ; i++) {
            if(numberList[i] == givenNumber) {
                return i;
            }
        }
        return -1;
    }
    
    // Prints the formatted number list to the console
    private void printNumberList() {
        for (int number : this.numbers) {
            System.out.print(number + ", ");
        }
        System.out.print("\n");
    }
}