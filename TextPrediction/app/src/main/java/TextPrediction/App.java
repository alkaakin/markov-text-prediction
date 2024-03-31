package TextPrediction;

import java.io.File;
import TextPrediction.Trie;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) throws InvalidFormatException, IOException {
        System.out.println(new App().getGreeting());
        File test = new File("/home/alkaakin/Documents/markov-text-prediction/TextPrediction/mod.xlsx");
        if (!test.isFile()) {
            System.out.println("File not found.");
        }
        Trie trietest = new Trie(2, test);
        trietest.printList();
        trietest.wordProcessor();
        trietest.generateName();
        
        
    }
}
