/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TextPrediction;

import java.io.*;

/**
 *
 * @author alkaakin
 */
public class Trie {
    
    TrieNode root;
    TrieNode current;
    FileInputStream stream;
    BufferedReader reader;
    
public Trie() {
    this.root = new TrieNode();
    current = root;
}

public TrieNode returnRoot() {
    return this.root;
}



    
    
    //Trie class splits a word/text file into 3-grams and stores it into its structure
    //We need to add nodes under nodes.
    
}
