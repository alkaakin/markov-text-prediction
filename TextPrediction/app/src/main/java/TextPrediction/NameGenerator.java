/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TextPrediction;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alkaakin
 */
public class NameGenerator {
    
    private final Trie trie;
    private final int order;
    private final int length;
    
    
    public NameGenerator (int order, int volume, int length) throws IOException, InvalidFormatException {
        
        this.order = order;
        this.length = length;
        File file = null;
        try {
            file = new File(App.class.getClassLoader().getResource("mod.xlsx").toURI());
        } catch (URISyntaxException ex) {
            Logger.getLogger(NameGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        this.trie = new Trie(order);
        InputHandler inputHandler = new InputHandler(file, trie, volume);
        inputHandler.wordProcessor();
    }
    
  
        /**
    generateName() is the main functionality of the algorithm.
    The method chooses a random character from the keyset and 
    uses that as the first letter of the to-be generated name.
    The method then gives information about the TrieNode under 
    iteration, the chosen character, counter and the so far formed String to another method. 
    **/
    
    public String generateName() {
  
        String name = "";
        int counter = 0;
        while (counter < length) {
           name += trie.trieFind(name);
           counter ++;
       }
       
       name = capitalizeFirstLetter(name);
       return name;
   }
   
   public String capitalizeFirstLetter(String input) {
       if (input == null || input.isEmpty()) {
           return input;
       }
       return input.substring(0, 1).toUpperCase() + input.substring(1);
   }
        
        
}
    
    

