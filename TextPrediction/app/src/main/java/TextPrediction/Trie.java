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
    
    public void printList() throws InvalidFormatException, IOException {
        list = excelreader.fileToList();
        for (String item : list) {
            System.out.println(item);
        }
    }

   public void generateName() {
       String name = "";
       Character randChar = null;
       System.out.println(current.children.keySet());
       List<Character> keysAsArray = new ArrayList<Character>(current.children.keySet());
       Random r = new Random();
       //First letter needs to be a random key under root
       if (!keysAsArray.isEmpty()) {
           int randomIndex = r.nextInt(keysAsArray.size());
           randChar = keysAsArray.get(randomIndex);
       }
       System.out.println("This is the first letter of the name: " + randChar);
       name += randChar;
       System.out.println(name);
       TrieNode nextNode = root.children.get(randChar);
       name += generateLetters(nextNode, randChar, 1, "");
       name = capitalizeFirstLetter(name);
       System.out.println("final name: " + name);
   }
   
   
   public String generateLetters(TrieNode node, Character z, int counter, String string) {  
       
       if (counter<=6) { 
           
               z = node.frequencyTable.maxFrequency(); 
               string += z; 
               counter++; 
               node = root.children.get(z);
            
           System.out.println("this testprint should include the updated string: " + string);
           System.out.println("this counter should update each time a letter is added: " + counter);
           System.out.println("this should point to the node thats being handled: " + current);
           string = generateLetters(node, z, counter, string);
        }
       return string;
        }
   
   public String capitalizeFirstLetter(String input) {
       if (input == null || input.isEmpty()) {
           return input;
       }
       return input.substring(0, 1).toUpperCase() + input.substring(1);
   }
       
   public void insertTrie(String nGram) throws InvalidFormatException, IOException {
        
        Character firstLetter = nGram.substring(0,1).charAt(0);
        Character secondLetter = nGram.substring(1,2).charAt(0);
        
        System.out.println(firstLetter + " first");
        System.out.println(secondLetter + " second");
        System.out.println("To be inserted: " + firstLetter + secondLetter);
       
        current = root;
        
        if (current.children.containsKey(firstLetter)) {
            System.out.println("First letter present, moving on to the next.");
            current = current.children.get(firstLetter);
            if (current.children.containsKey(secondLetter)) {
                System.out.println("Second letter present, only updating frequency.");
                current.frequencyTable.updateFrequency(secondLetter);
                System.out.println("The frequencies of the children are " + current.frequencyTable.frequencies.values());
            }
            if (!current.children.containsKey(secondLetter)) {
                System.out.println("Second letter absent, inserting.");
                current.children.put(secondLetter, null);
                current.frequencyTable.updateFrequency(secondLetter);
                System.out.println("The frequencies of the children are " + current.frequencyTable.frequencies.values());
            }
        }
        
        current = root;
        
        if (!current.children.containsKey(firstLetter)) {
            System.out.println("First letter absent, inserting.");
            current.children.put(firstLetter, new TrieNode());
            current = current.children.get(firstLetter);
            System.out.println("Second letter absent, inserting.");
            current.children.put(secondLetter, null);
            current.frequencyTable.updateFrequency(secondLetter);
            System.out.println(current.frequencyTable.frequencies.values());
        }
        
        }
    
    
    //wordProcessor splits given words into ngrams and gives them to InsertTrie method
    //the ngram functionality should be implemented here (=the order of ngrams, given to Trie at the beginning)
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
    }
    
    
    public class TrieNode {
        
        HashMap<Character, TrieNode> children;
        FrequencyTable frequencyTable;
        //int counter;
        
        
        public TrieNode() {
           //this.counter = 1; 
           this.frequencyTable = new FrequencyTable();
           children = new HashMap<Character, TrieNode>();     
        }
        
    }
    
    public class FrequencyTable {
        HashMap<Character, Integer> frequencies;
        
        public FrequencyTable() {
            frequencies = new HashMap<>();
        }
        
        public void updateFrequency(Character k) {
            frequencies.put(k, frequencies.getOrDefault(k, 0)+1);
        }
        
        public Character maxFrequency() {
            int max = Integer.MIN_VALUE;
            Character maxKey = null;
            ArrayList<Character> maxKeys = new ArrayList<>();
            
            for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
                Character c = entry.getKey();
                int value = entry.getValue();
                
                if (value > max) {
                    maxKeys.clear();
                    maxKeys.add(c);
                    max = value;
                } else if (value == max) {
                    maxKeys.add(c);
                }
                    
                }
            if (!maxKeys.isEmpty()) {
                Random random = new Random();
                int i = random.nextInt(maxKeys.size());
                return maxKeys.get(i);
            }
            return null;
              
        }
        
    }
    
    public class ExcelReader {
        
        File excelFile;
        ArrayList<String> words;
        
        public ExcelReader(File file) {
            this.excelFile = file;
        }
        
        public ArrayList<String> fileToList() throws InvalidFormatException, IOException {
            ArrayList<String> words = new ArrayList<String>();
            try (FileInputStream inputStream = new FileInputStream(excelFile);
                Workbook workbook = new XSSFWorkbook(inputStream)) { 
                Sheet sheet = workbook.getSheetAt(0);
                //Iterating over columns by using rows
                Iterator<Row> rowIterator = sheet.rowIterator();
                Integer c = 0;
                
                while (rowIterator.hasNext() && c <1000) {
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
    
    


