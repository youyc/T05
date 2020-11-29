package com.yongchean;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static java.lang.Thread.sleep;

public class T05 extends JFrame implements ActionListener {

    private JLabel label_mean = new JLabel("Mean = ");
    private JLabel label_mode = new JLabel("Mode = ");
    private JLabel label_median = new JLabel("Median = ");
    private JProgressBar progressbar = new JProgressBar(0, 100);
    private JButton button_start = new JButton("Start");

    public T05(){
        label_mean.setBounds(100, 30, 200, 30);
        label_mode.setBounds(100, 60, 1800, 30);
        label_median.setBounds(100, 90, 200, 30);
        progressbar.setBounds(70, 150, 1760, 20);
        button_start.setBounds(370, 320, 100, 30);

        add(label_mean);
        add(label_mode);
        add(label_median);
        add(progressbar);
        add(button_start);

        //label_mean.setVisible(false);

        button_start.addActionListener(this);

        setTitle("T05 issue");
        setSize(1900, 400);
        setLayout(null);
        //stop program when x is pressed
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        T05 windows1 = new T05();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //JOptionPane.showMessageDialog(this, label_mean);
        //label_mean.setVisible(true);
        Random random = new Random();
        int[] random_numbers = new int[1000];
        ExecutorService executor_service_1 = Executors.newSingleThreadExecutor();

        executor_service_1.execute(()->{
            try {
                //generate 1000 random numbers
                for (int i=0; i<random_numbers.length; i++){
                    random_numbers[i] = random.nextInt(101);
                    System.out.print(random_numbers[i] + ", ");
                    //array to keep new set of numbers
                    int size = i+1;
                    int[] numbers = new int [size];
                    for (int a=0; a< numbers.length; a++){
                        numbers[a] = random_numbers[a];
                        if (a % 10 == 0) {
                            System.out.println();
                            System.out.print(numbers[a] + ", ");
                        }
                        else
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
                    label_mean.setText(String.format("MEAN = %.2f", mean));

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
                    String mode_numbers = "";
                    for(int m = 0; m< numCount.length; m++) {
                        if(numCount[m] == maxCount) {
                            System.out.print(numbers[m] + " ");
                            mode_numbers = mode_numbers + "  " + numbers[m];
                            //label_mode.setText(String.valueOf(numbers[m]));
                        }
                    }
                    label_mode.setText(String.format("MODE = " + mode_numbers));
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
                    label_median.setText(String.format("MEDIAN = %.2f", median));

                    //Delay Period
                    sleep(3000);
                }
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        });
    }
}
