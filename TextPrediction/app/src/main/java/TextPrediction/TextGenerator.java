/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TextPrediction;

import TextPrediction.Trie;
import TextPrediction.Trie.TrieNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author alkaakin
 */
public class TextGenerator {
    /**
    generateName() is the main functionality of the Trie class and the algorithm in general.
    The method chooses a random character from the keyset and uses that as the first letter of the to-be generated name.
    The method then gives information about the TrieNode under iteration, the chosen character, counter and the so far formed String to another method. 
    */
    
    public TrieNode current;
    public int namelength;
    public Trie trie;
    public int n;
    
    public TextGenerator(Trie trie, TrieNode root, int namelength, int n) {
        this.current = root;
        this.namelength = namelength;
        this.trie = trie;
        this.n = n;
    }
    
    public void generateName() {
       
       String name = "";
       int counter = 0;
       while (counter < namelength) {
           name += trie.trieFind(name);
           counter ++;
       }
       
       name = capitalizeFirstLetter(name);
       System.out.println("final name: " + name);
   }
   
   public String capitalizeFirstLetter(String input) {
       if (input == null || input.isEmpty()) {
           return input;
       }
       return input.substring(0, 1).toUpperCase() + input.substring(1);
   }
       
}
