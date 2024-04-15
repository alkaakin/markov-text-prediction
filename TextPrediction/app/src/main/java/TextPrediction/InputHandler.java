/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TextPrediction;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author alkaakin
 */
public class InputHandler {
    
    public File file;
    public Trie trie;
    public int n;
    public int volume;
    public ArrayList<String> words;
    
    public InputHandler(File file, Trie trie, int volume) {
            this.file = file;
            this.trie = trie;
            this.n = trie.n;
            this.volume = volume;
        }
    
    public ArrayList<String> fileToList() throws InvalidFormatException, IOException {
            ArrayList<String> words = new ArrayList<String>();
            try (FileInputStream inputStream = new FileInputStream(file);
                Workbook workbook = new XSSFWorkbook(inputStream)) { 
                Sheet sheet = workbook.getSheetAt(0);
                Iterator<Row> rowIterator = sheet.rowIterator();
                Integer c = 0;
                String word = "";
                while (rowIterator.hasNext() && c < volume) {
                    Row row = rowIterator.next();
                    Cell cell = row.getCell(0);
                    if (cell != null) {
                        word = cell.getStringCellValue();
                        word.replaceAll("[^a-z]", "");
                        word.toLowerCase();
                        words.add(word);
                        c++;
                    }
                }
                
            }
            return words;
        }
            
        /**
   Method wordProcessor() picks words from a list processed by ExcelReader.
   These words are split into nGrams according to user-given 'Markov order'. N-grams are then given to InsertTrie() method.
   */
    public void wordProcessor() throws InvalidFormatException, IOException {
        
        ArrayList<String> list = new ArrayList<String>();
        list = fileToList();
        ArrayList<String> ngrams = new ArrayList<String>();
        for (String word : list) {
            for (int i = 0; i < word.length() - n; i++) {
                String nGram = word.substring(i, i + n + 1);
                nGram = nGram.toLowerCase().replaceAll("[^a-zäö-]", ""); //removes all letters that are not a-z, ä, ö or hyphens.
                System.out.println("Inserting this nGram: " + nGram);
                trie.insertTrie(nGram);
                ngrams.add(nGram);
            }
        }
        
        for (String ngram : ngrams) {
            System.out.println(ngram);
        }
    }
}
