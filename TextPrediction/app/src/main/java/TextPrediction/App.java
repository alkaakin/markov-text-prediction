package TextPrediction;

import java.io.File;
import TextPrediction.Trie;
import java.io.IOException;
import java.util.Scanner;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

public class App {


    public static void main(String[] args) {
        try {
        Scanner inputTaker = new Scanner(System.in);
        System.out.println("Welcome to the name generator. The name generator generates natural language mimicking names based on real names.");
        System.out.println("For more authentic names, please use a higher order. Please note, however, that higher orders produce shorter names, and vice versa.");
        System.out.println("Please give an order between 1-4. The higher the order, the better the results.");
        int order = promptNumber(inputTaker, "Enter your order (1-4):", 1, 4);
        
        System.out.println("Please also indicate the level of complexity in terms of the size of dataset (between 1-10 000). The larger the dataset, the longer the names can be.");
        int volume = promptNumber(inputTaker, "Enter volume (1-10,000):", 1, 10000);
        
        System.out.println("Indicate the desired maximum length of the name (3-10 characters).");
        int length = promptNumber(inputTaker, "Enter length (3-10):", 3, 10);
        
         NameGenerator generatorApp = new NameGenerator(order, volume, length);
         String name = generatorApp.generateName();
         System.out.println("Generated Name: " + name);
        
        
        
    } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static int promptNumber(Scanner scanner, String prompt, int min, int max) {
        int number;
        do {
            System.out.println(prompt);
            while (!scanner.hasNextInt()) {
                System.out.println("That's not a valid number. Try again.");
                scanner.next();
            }
            number = scanner.nextInt();
        } while (number < min || number > max);
        return number;
    }
}


