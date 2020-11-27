package com.yongchean;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        int[] random_numbers = new int[20];

        //generate 1000 random numbers
        for (int i=0; i<random_numbers.length; i++){
            random_numbers[i] = random.nextInt(10);
            System.out.print(random_numbers[i] + ", ");
            //array to keep new set of numbers
            int size = i+1;
            int[] numbers = new int [size];
            for (int a=0; a< numbers.length; a++){
                numbers[a] = random_numbers[a];
                System.out.print(numbers[a] + ", ");
            }
            Arrays.sort(numbers);
            System.out.println();

            //Mean
            double sum = 0;
            double mean = 0;

            for (int number : numbers) {
                sum = sum + number;
            }
            mean = sum / numbers.length;
            System.out.printf("Mean = %.2f\n", mean);

            //Mode
            int maxCount = 0;
            int[] numCount = new int[size];
            for (int k=0; k<numbers.length; k++){
                int count = 1;
                for (int j=k+1; j<numbers.length; j++) {
                    if (numbers[j] == numbers[k])
                        count++;
                }
                numCount[k] = count;
            }
            maxCount = numCount[0];
            for(int l = 0; l < numCount.length; l++) {
                if (numCount[l] >= maxCount) {
                    maxCount = numCount[l];
                }
            }
            System.out.print("Mode number = ");
            for(int m = 0; m< numCount.length; m++) {
                if(numCount[m] == maxCount) {
                    System.out.print(numbers[m] + " ");
                }
            }
            System.out.println();

            //Median
            int middle = numbers.length/2;
            double median = 0;

            if (numbers.length%2 == 1) {
                median = numbers[middle];
            } else {
                median = (numbers[middle-1] + numbers[middle]) / 2.0;
            }
            System.out.printf("Median = %.2f\n", median);

        }
    }
}
