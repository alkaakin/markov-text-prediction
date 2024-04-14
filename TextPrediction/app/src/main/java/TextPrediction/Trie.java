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

       TrieNode current = root;
           System.out.println(name);
       int effectiveN = Math.min(n, name.length());
       
       if (effectiveN > 0) {
           System.out.println("The order is " + effectiveN);
           System.out.println("The name so far is " + name);
           String ngram = name.substring((name.length() - effectiveN));
           System.out.println("nGram under handling is: " + ngram);
           for (int i = 0; i < ngram.length(); i++) {
           current = current.children.get(ngram.charAt(i));
           if (current == null) return "";
           }
       }
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

       if (values.get(0) != null) {
           System.out.println(values.get(0));
       }
       else if (values.get(0) == null) {
           System.out.println("values(0) is null.");
       }
       int comparable = 0;
       int finalIndex = 0;
       for (int i = 0; i < values.size()-1; i++) {
           comparable += values.get(i); 
           System.out.println("comparable is " + comparable);
           if (weight <= comparable) {
            finalIndex = i;
            break;
           }
           if (weight > comparable) {
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
    


    
    


