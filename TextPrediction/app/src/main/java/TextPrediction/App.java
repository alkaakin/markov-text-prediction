package TextPrediction;

import java.io.File;
import TextPrediction.Trie;
import java.io.IOException;
import java.util.Scanner;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) throws InvalidFormatException, IOException {
        Scanner inputTaker = new Scanner(System.in);
        System.out.println(new App().getGreeting());
        File test = new File("/home/alkaakin/Documents/markov-text-prediction/TextPrediction/mod.xlsx");
        if (!test.isFile()) {
            System.out.println("File not found.");
        }
        System.out.println("Welcome to the name generator. Please give an order between 1-4. The higher the order, the better the results.");
        int order = 0;
        int volume = 0;
        while(order < 1 || order > 4 || volume < 1 || volume > 10000) {
            System.out.println("Enter your order (1-4):");
            while(!inputTaker.hasNextInt()) {
                System.out.println("That's not a valid number. Please enter a number between 1 and 4.");
                inputTaker.next(); // to consume the incorrect input
            }
            order = inputTaker.nextInt();
            if (order < 1 || order > 4) {
                System.out.println("Please enter a number between 1 and 4.");
            }
        
            System.out.println("Please also indicate the level of complexity in terms of the size of dataset (between 1-10 000)");
            while(!inputTaker.hasNextInt()) {
                System.out.println("That's not a valid number. Please enter a number between 1 and 10 000.");
                inputTaker.next(); // to consume the incorrect input
            }
            volume = inputTaker.nextInt();
            if (volume < 1 || volume > 10000) {
                System.out.println("Please enter a number between 1 and 10 000.");
            }
            
        }
       
        Trie trietest = new Trie(order, volume, test);
        trietest.printList();
        trietest.wordProcessor();
        trietest.generateName();
        
        
    }
}


