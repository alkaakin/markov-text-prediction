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
    int n;

    public Trie(int order) throws InvalidFormatException, IOException {
        this.root = new TrieNode();
        n = order;
    }
    
   /**
   The insertTrie method inserts the given names to trie as nGrams. Characters are added as chains under the Trie. 
   If a character has already been added under another circumstance, only the frequency is updated. 
   */
   public void insertTrie(String nGram) throws InvalidFormatException, IOException {
       
       if (nGram == null || nGram.isEmpty()) return;
  
        TrieNode current = root;
        
        for (int i = 0; i < nGram.length(); i++) {
           
           char ch = nGram.charAt(i);
           
           if (current.children.containsKey(ch)) {
               System.out.println("Character already under node, just updating frequency.");
               current.children.get(ch).updateValue();
           }
           
           if (!current.children.containsKey(ch)) {
               TrieNode newNode = new TrieNode();
               System.out.println("Inserting into trie: " + ch);
               current.children.put(ch, newNode);
            }
       
           current = current.children.get(ch);
        
    }
       
   }
   
       public String trieFind(String name) {
       //haetaan aina rootista n mukaisesti:
       //counting n letter from the end of the given name's last letter
       //what is the weighed keyset following the last n letters
       //TrieNode current = root;
       TrieNode current = root;
           System.out.println(name);
       //Kuljetaan lenkki viimeiseen lapseen saakka
       /**
       if (n <= name.length()) {
           System.out.println(n);
           System.out.println(name);
       String ngram = name.substring(name.length()-n+1, name.length());
       for (int i = 0; i < ngram.length(); i++) {
           current = current.children.get(ngram.charAt(i));
            }
       }
       */
       List<Character> keys = new ArrayList<Character>();
       keys.addAll(current.children.keySet());
           System.out.println(keys);
       ArrayList<Integer> values = new ArrayList<>();
       //add values to another arraylist
       for (TrieNode node : current.children.values()) {
           values.add(node.frequency);
       }
           System.out.println(values);
       //count the frequencies of the second arraylist
       int sum = 0;
       for (int i = 0; i < values.size(); i++) {
           sum += values.get(i);
       }
           System.out.println("Sum is" + sum);
       //Get a random number from the sum
       Random r = new Random();
       int weight = r.nextInt(sum);
           System.out.println("Weight is" + weight);
       //create a loop for choosing one of the list items randomly
       int comparable = values.get(0);
       int finalIndex = 0;
       for (int i = 0; i < values.size(); i++) {
           if (weight > comparable) {
               comparable += values.get(i);
               continue;
           }
           if (weight <= comparable) {
              finalIndex = values.indexOf(i);
              continue;
           }
       }
        //now return the corresponding character.
       return keys.get(finalIndex).toString();
   } 
    
    public class TrieNode {
        
        HashMap<Character, TrieNode> children;
        public int frequency;
        
        public TrieNode() {
           this.children = new HashMap<Character, TrieNode>();
           this.frequency = 1;
        }
        
        public void updateValue() {
            this.frequency += 1;
        }
        
        public int returnFrequency() {
            return this.frequency;
        }
        
    }
    

}
    


    
    


