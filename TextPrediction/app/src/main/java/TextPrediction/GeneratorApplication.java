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

/**
 *
 * @author alkaakin
 */
public class GeneratorApplication {
    
    private final Trie trie;
    private final int order;
    private final int length;
    
    
    public GeneratorApplication (int order, int volume, int length) throws IOException, InvalidFormatException, URISyntaxException {
        
        this.order = order;
        this.length = length;
        File file = new File(App.class.getClassLoader().getResource("mod.xlsx").toURI());

        
        this.trie = new Trie(order);
        InputHandler inputHandler = new InputHandler(file, trie, volume);
        inputHandler.wordProcessor();
    }
    
    public String generateName() {
        TextGenerator generator = new TextGenerator(trie, trie.root, length, trie.n);
        return generator.generateName();
    }
    
    
}
