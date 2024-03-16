/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TextPrediction;
import java.util.*;


/**
 *
 * @author alkaakin
 */
public class TrieNode {
    
    Map<Character, TrieNode> children = new HashMap<>();
    boolean terminal = false;
   
public TrieNode() {
    
}

public void addChild(Character c) {
    children.putIfAbsent(c, new TrieNode());
}

public TrieNode getChild(Character c) {
    return children.get(c);
}

public boolean isTerminal() {
    return terminal;
}

public void markAsTerminal(boolean bool) {
    this.terminal = bool;
}
    
//MaxKeyChild method left for the Markov Model class.    
        
}
