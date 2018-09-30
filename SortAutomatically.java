package Algorytmy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class SortAutomatically {

    private String[] fileName = {"/home/zayl/IdeaProjects/School/src/sortowanie_1.txt", "/home/zayl/IdeaProjects/School/src/sortowanie_2.txt", "/home/zayl/IdeaProjects/School/src/sortowanie_3.txt"};
    private int sumPositive = 0;
    private int sumNegative = 0;
    private List<Integer> numbers = new ArrayList<>();
    private List<Integer> evenNumbers = new ArrayList<>();
    private List<Integer> oddNumbers = new ArrayList<>();
    private List<Integer> positiveNumbers = new ArrayList<>();
    private List<Integer> negativeNumbers = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        SortAutomatically sortAutomatically = new SortAutomatically();
        sortAutomatically.executeScript();
    }

    private void clear() {
        numbers.clear();
        evenNumbers.clear();
        oddNumbers.clear();
        positiveNumbers.clear();
        negativeNumbers.clear();
        sumPositive = 0;
        sumNegative = 0;
    }

    private void insertionSortUp(List<Integer> numbers) {
        for (int i = 1; i < numbers.size(); i++) {
            for(int j = i; j > 0; j--){
                if(numbers.get(j - 1) > numbers.get(j)){
                    int temp = numbers.get(j);
                    numbers.set(j, numbers.get(j - 1));
                    numbers.set(j - 1, temp);
                }
            }
        }
    }

    private void insertionSortDown(List<Integer> numbers) {
        for (int i = 1; i < numbers.size(); i++) {
            for(int j = i; j > 0; j--){
                if(numbers.get(j) > numbers.get(j - 1)){
                    int temp = numbers.get(j);
                    numbers.set(j, numbers.get(j - 1));
                    numbers.set(j - 1, temp);
                }
            }
        }
    }

    private void checkIfEvenOrOdd() {
        for (int number : numbers) {
            if (number % 2 == 0) {
                evenNumbers.add(number);
            } else {
                oddNumbers.add(number);
            }
        }
    }

    private void checkIfPositiveOrNegative() {
        for (int posOrNeg : numbers) {
            if (posOrNeg > 0) {
                positiveNumbers.add(posOrNeg);
            } else {
                negativeNumbers.add(posOrNeg);
            }
        }

        for (Integer positiveNumber : positiveNumbers)
            sumPositive += positiveNumber;

        for (Integer negativeNumbers : negativeNumbers)
            sumNegative += negativeNumbers;
    }

    private void readFromFile(Scanner readDataFromFile) {
        clear();

        while(readDataFromFile.hasNextInt()){
            numbers.add(readDataFromFile.nextInt());
        }
    }

    private void writeToFile(BufferedWriter writeDataToFile) throws IOException {
        writeDataToFile.write("Niemalejaco liczby patrzyste:\n");
        for (int evenNumber : evenNumbers)
            writeDataToFile.write(String.valueOf(evenNumber) + " ");

        writeDataToFile.write("\n\n");
        writeDataToFile.write("Nierosnąco liczby niepatrzyste:\n");
        for (int oddNumber : oddNumbers)
            writeDataToFile.write(String.valueOf(oddNumber) + " ");

        if (Math.abs(sumNegative) > sumPositive) {
            writeDataToFile.write("\n\n\n");
            writeDataToFile.write("Suma bezwgledna liczb ujemnych jest większa niz suma liczb dodatnich.\n");
            writeDataToFile.write(String.format("\nSuma bezwzgledna liczb ujemnych: %s\nSuma liczb dodatnich: %s", Math.abs(sumNegative), sumPositive));
        } else if (Math.abs(sumNegative) < sumPositive) {
            writeDataToFile.write("\n\n");
            writeDataToFile.write("Suma bezwgledna liczb ujemnych jest mniejsza sumie liczb dodatnich.\n");
            writeDataToFile.write(String.format("\nSuma bezwzgledna liczb ujemnych: %s\nSuma liczb dodatnich: %s", Math.abs(sumNegative), sumPositive));
        } else {
            writeDataToFile.write("\n\n");
            writeDataToFile.write("Suma bezwgledna liczb ujemnych jest równa sumie liczb dodatnich.\n");
            writeDataToFile.write(String.format("\nSuma bezwzgledna liczb ujemnych: %s\nSuma liczb dodatnich: %s", Math.abs(sumNegative), sumPositive));
        }

        writeDataToFile.close();
    }

    private void executeScript() throws IOException {
        for (int iterator = 0; iterator < 3; iterator++) {
            readFromFile(new Scanner(new File(fileName[iterator])));

            checkIfEvenOrOdd();

            insertionSortUp(evenNumbers);
            insertionSortDown(oddNumbers);

            checkIfPositiveOrNegative();

            writeToFile(new BufferedWriter(new FileWriter(fileName[iterator])));
        }
    }
}
