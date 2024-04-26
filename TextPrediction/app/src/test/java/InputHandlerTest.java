/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package TextPrediction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.apache.poi.hssf.usermodel.HeaderFooter.file;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

class InputHandlerTest {
    
    private Trie trie;
    private InputHandler handler;
    private File testFile;

    @BeforeEach
    void setup() throws IOException, InvalidFormatException, URISyntaxException {
    trie = new Trie(2);  // Setting up a trie with an order of 2
    File file = new File(App.class.getClassLoader().getResource("mod.xlsx").toURI());
    handler = new InputHandler(file, trie, 10);  // Assume we want to read up to 10 entries
    }

    @Test
    void testFileToList() throws IOException, InvalidFormatException {
        ArrayList<String> words = handler.fileToList();
        assertNotNull(words, "The word list should not be null.");
        assertFalse(words.isEmpty(), "The word list should not be empty.");
        assertTrue(words.contains("Juhani"), "The word list should contain specific expected words.");
    }

}
