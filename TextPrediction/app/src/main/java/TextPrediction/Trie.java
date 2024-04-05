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
    int volume;
    
    public Trie(int n, int volume, File file) {
        this.root = new TrieNode();
        this.root.setDepth(0);
        this.volume = volume;
        current = root;
        this.itr = file;
        this.excelreader = new ExcelReader(itr, volume);
        this.list = new ArrayList<String>();
        if (n > 4 || n < 1) {
            throw new IllegalArgumentException("Value of n must be between 1-4. The maximum Markov order of the Trie is 4.");
        }
        else {
            this.n = n;
        }
    }
    
    /*
    A helper method for printing out the names in the excel file.
    */
    public void printList() throws InvalidFormatException, IOException {
        list = excelreader.fileToList();
        for (String item : list) {
            System.out.println(item);
        }
    }
   /*
    generateName() is the main functionality of the Trie class and the algorithm in general.
    The method chooses a random character from the keyset and uses that as the first letter of the to-be generated name.
    The method then gives information about the TrieNode under iteration, the chosen character, counter and the so far formed String to another method. 
    */
   public void generateName() {
       current = root;
       String name = "";
       Character randChar = null;
       System.out.println(current.children.keySet());
       List<Character> keysAsArray = new ArrayList<Character>(current.children.keySet());
       Random r = new Random();
       keysAsArray.removeIf(c -> c == '-');
       //First letter needs to be a random key under root
       if (!keysAsArray.isEmpty()) {
           int randomIndex = r.nextInt(keysAsArray.size());
           randChar = keysAsArray.get(randomIndex);
       }
       System.out.println("This is the first letter of the name: " + randChar);
       name += randChar;
       System.out.println(name);
       TrieNode nextNode = root.children.get(randChar);
       System.out.println("Possible followers are: " + nextNode.frequencyTable.frequencies);
       name += generateLetters(nextNode, randChar, 1, "");
       name = capitalizeFirstLetter(name);
       System.out.println("final name: " + name);
   }
   
   /*
   The generateLetters method is a recursive method used for picking the most probable letters under each given character.
   Each character picked via this method is inserted to the string and given as an input to the method itself (to be recursively used for traversing the trie).
   The method stops after the counter reaches 6, to the formed name's length is 7 (as of now). 
   A functionality for tracking the "realness" of names generated is under construction.
   */
   public String generateLetters(TrieNode node, Character z, int counter, String string) {  
       
       if (counter<=6) { 
           
               if (node.children.isEmpty()) {
                   node = root.children.get(z);
               }
                
               char c = node.frequencyTable.maxFrequency().charValue();

               if (node.children.containsKey(c)) {
                   node = node.children.get(c);
                   System.out.println("The most probable next letter is " + z);
                   System.out.println("Possible followers are: " + node.frequencyTable.frequencies);
                   if (node.frequencyTable.frequencies.isEmpty()) {
                       System.out.println("No possible followers, moving to root.");
                   }
               }

               
               string += c; 
               counter++; 
            
           System.out.println("this testprint should include the updated string: " + string);
           System.out.println("this counter should update each time a letter is added: " + counter);
           System.out.println("this should point to the node thats being handled: " + current);
           string = generateLetters(node, c, counter, string);
        }
       return string;
        }
   
   public String capitalizeFirstLetter(String input) {
       if (input == null || input.isEmpty()) {
           return input;
       }
       return input.substring(0, 1).toUpperCase() + input.substring(1);
   }
       
   
   /*
   The insertTrie method inserts the given names to trie as nGrams. Characters are added as chains under the Trie. 
   If a character has already been added under another circumstance, only the frequency is updated. 
   */
   public void insertTrie(String nGram) throws InvalidFormatException, IOException {
       
       if (nGram == null || nGram.isEmpty()) return;
       
       
        current = root;
        
        for (int i = 0; i < nGram.length(); i++) {
           
           char ch = nGram.charAt(i);
           
           if (current.children.containsKey(ch)) {
               System.out.println("Character already under node, just updating frequency.");
               if (current.depth != 0) {
               current.frequencyTable.updateFrequency(ch);
               }
           }
           
           if (!current.children.containsKey(ch)) {
               TrieNode newNode = new TrieNode();
               newNode.setDepth(current.depth + 1);
               System.out.println("Inserting into trie: " + ch);
               current.children.put(ch, newNode);
               if (current.depth != 0) {
                   current.frequencyTable.updateFrequency(ch);
               }

            }
       
           current = current.children.get(ch);
        
    }
       
   }
    
   /*
   Method wordProcessor() picks words from a list processed by ExcelReader.
   These words are split into nGrams according to user-given 'Markov order'. N-grams are then given to InsertTrie() method.
   */
    public void wordProcessor() throws InvalidFormatException, IOException {
        
        list = excelreader.fileToList();
        ArrayList<String> ngrams = new ArrayList<String>();
        for (String word : list) {
            /*Singular words are looped through so that the last nGram to be looped starts n letters from the end of the word.
            n is the Markov order given to the Trie object at its inception.
            */
            for (int i = 0; i < word.length() - n; i++) {
                String nGram = word.substring(i, i + n + 1);
                nGram = nGram.toLowerCase().replaceAll("[^a-zäö-]", ""); //removes all letters that are not a-z, ä, ö or hyphens.
                System.out.println("Inserting this nGram: " + nGram);
                insertTrie(nGram);
                ngrams.add(nGram);
            }
        }
        
        for (String ngram : ngrams) {
            System.out.println(ngram);
        }
    }
    
    public class Terminal {
        //To be implemented during week 4. The Terminal class will check for endings of words to create more plausible names.
    }
    
    /*
    The TrieNode class is a foundation for the Trie model. Each TrieNode holds a HashMap for storing the child nodes, as well as a FrequencyTable object for storing probabilities.
    The class also has int depth as an attribute to check when it's time to traverse the Trie from the root when generating words (as maximum depth indicates that there are no children
    under the most recent TrieNode). Depth is also used to not accidentally update frequencies for nodes directly under root, as this would lead to an unbalanced formation of random
    names.
    */
    public class TrieNode {
        
        HashMap<Character, TrieNode> children;
        FrequencyTable frequencyTable;
        public int depth;
        
        
        public TrieNode() {
            
           this.frequencyTable = new FrequencyTable();
           children = new HashMap<Character, TrieNode>();     
        }
        
        public void setDepth(int i) {
            this.depth = i;
        }
        
    }
    
    /* 
    The FrequencyTable object implements the so called Markov chain functionality for the algorithm. It is used for checking the most populated
    child character under each node (via the maxFrequency method).
    */
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
    
    /*
    The ExcelReader helper class contains the fileToList method for processing the excel file in question. 
    */
    public class ExcelReader {
        
        File excelFile;
        ArrayList<String> words;
        int volume;
        
        public ExcelReader(File file, int volume) {
            this.excelFile = file;
            this.volume = volume;
        }
        
        public ArrayList<String> fileToList() throws InvalidFormatException, IOException {
            ArrayList<String> words = new ArrayList<String>();
            try (FileInputStream inputStream = new FileInputStream(excelFile);
                Workbook workbook = new XSSFWorkbook(inputStream)) { 
                Sheet sheet = workbook.getSheetAt(0);
                Iterator<Row> rowIterator = sheet.rowIterator();
                Integer c = 0;
                String word = "";
                //A scanner functionality for inputting the desired amount of input data will be added during week 4.
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
            
            
            
    }
}
    
    


