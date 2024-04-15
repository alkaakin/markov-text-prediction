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
    
    final TrieNode root;
    final int n;

    public Trie(int order) throws InvalidFormatException, IOException {
        this.root = new TrieNode();
        n = order;
    }
    
   /**
   The insertTrie method inserts the given names to trie as nGrams. Characters are added as chains under the Trie. 
   If a character has already been added under another circumstance, only the frequency is updated.
   * @param nGram the n-gram to insert
   */
   public void insertTrie(String nGram) throws InvalidFormatException, IOException {
       
       if (nGram == null || nGram.isEmpty()) return;
  
        TrieNode current = root;
        
        for (char ch : nGram.toCharArray()) {
            current = current.children.computeIfAbsent(ch, k -> {
                System.out.println("Inserting into trie: " + ch);
                return new TrieNode();
            });
            current.frequency++;
        }
       
   }
      /**
   The trieFind function returns a possible character to follow after a given sequence.
   The function first checks the length of the string given. If the length = 0, a random letter under root node will be chosen.
   If the length > 0, the singular characters forming the string will be traversed one by one until the last node is reached.
   effectiveN will always be <= string.length. 
   * @param sequence is the input sequence to base the next letter on.
   * @return the next character as String.
   */
   
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
       ArrayList<Integer> values = new ArrayList<>();
       for (TrieNode node : current.children.values()) {
           values.add(node.frequency);
       }
       int sum = 0;
       for (int i = 0; i < values.size(); i++) {
           sum += values.get(i);
       }
       Random r = new Random();
       int weight = r.nextInt(sum);
       int comparable = 0;
       int finalIndex = 0;
       for (int i = 0; i < values.size()-1; i++) {
           comparable += values.get(i); 
           if (weight <= comparable) {
            finalIndex = i;
            break;
           }
           if (weight > comparable) {
               continue;
           }
       }
       return keys.get(finalIndex).toString();
   } 
    
    public class TrieNode {
        
        HashMap<Character, TrieNode> children;
        public int frequency;
        
        public TrieNode() {
           this.children = new HashMap<Character, TrieNode>();
           this.frequency = 1;
        }
 
    }
    

}
    


    
    


