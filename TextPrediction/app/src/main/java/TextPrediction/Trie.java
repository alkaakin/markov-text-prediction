/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TextPrediction;

import java.io.*;
import java.util.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 *
 * @author alkaakin
 */
public class Trie {
    
    TrieNode root;
    TrieNode current;
    ExcelReader excelreader;
    File itr;
    ArrayList<String> list;
    int n;
    
    public Trie(int n, File file) {
        this.root = new TrieNode();
        current = root;
        this.itr = file;
        this.excelreader = new ExcelReader(itr);
        ArrayList<String> list = new ArrayList<String>();
        this.n = n;
    }

    public TrieNode returnRoot() {
        return this.root;
    }
    
    public void printList() throws InvalidFormatException, IOException {
        list = excelreader.fileToList();
        for (String item : list) {
            System.out.println(item);
        }
    }
    
    public void insertTrie(String nGram) throws InvalidFormatException, IOException {
        int markovOrder = n;
        
    }
        
    public void wordProcessor() throws InvalidFormatException, IOException {
        list = excelreader.fileToList();
        ArrayList<String> bigrams = new ArrayList<String>();
        for (String word : list) {
            for (int i = 0; i < word.length() - 1; i++) {
                word = word.toLowerCase();
                String nGram = word.substring(i, i + 2);
                insertTrie(nGram);
                bigrams.add(nGram);
            }
        }
        
        for (String bigram : bigrams) {
            System.out.println(bigram);
        }
        
        
        //iterate through the list, save ngrams to trie as trienodes
        //for n=2, save doubles, n¤3, save triples
        //save frequency for each pass-through to latter node
        //always save first letter under the root. save the second under that one and the third under the second one
        //frequencyupdater will hold information in hashmap, children and frequencies
    }
    
    public boolean searchTrie() {
        return false;
    }
    
    public class TrieNode {
        
        FrequencyUpdater frequencies;
        int counter;
        
        public TrieNode() {
           this.counter = 1; 
           this.frequencies = new FrequencyUpdater();
                
        }
        
    }
    
    public class FrequencyUpdater {
        HashMap<Character, Integer> frequencies;
        
        public FrequencyUpdater() {
            frequencies = new HashMap<>();
        }
    }
    
    public class ExcelReader {
        
        File excelFile;
        ArrayList<String> words;
        
        public ExcelReader(File file) {
            this.excelFile = file;
            //ArrayList<String> words = new ArrayList<String>(); 
        }
        
        public ArrayList<String> fileToList() throws InvalidFormatException, IOException {
            ArrayList<String> words = new ArrayList<String>();
            try (FileInputStream inputStream = new FileInputStream(excelFile);
                Workbook workbook = new XSSFWorkbook(inputStream)) { 
                Sheet sheet = workbook.getSheetAt(0);
                //Iterating over columns by using rows
                Iterator<Row> rowIterator = sheet.rowIterator();
                Integer c = 0;
                
                while (rowIterator.hasNext() && c <10) {
                    Row row = rowIterator.next();
                    Cell cell = row.getCell(0);
                    if (cell != null) {
                        words.add(cell.getStringCellValue());
                        c++;
                    }
                }
                
            }
            return words;
        }
            
            
            
    }
}
    
    

    //Trie class splits a word/text file into 3-grams and stores it into its structure
    //We need to add nodes under nodes.
    

