/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package TextPrediction;

import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author alkaakin
 */
public class TrieTest {
    
    public TrieTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of insertTrie method, of class Trie.
     */
    @Test
    public void testInsertTrie() throws Exception {
        System.out.println("insertTrie");
        String nGram = "";
        Trie instance = new Trie(2);
        instance.insertTrie(nGram);
        // TODO review the generated test code and remove the default call to fail.
        /*fail("The test case is a prototype.");*/
    }

    /**
     * Test of trieFind method, of class Trie.
     */
    @Test
    public void testTrieFind() throws InvalidFormatException, IOException {
        System.out.println("trieFind");
        String name = "mark";
        Trie instance = new Trie(4);
        instance.insertTrie(name);
        String expResult = "mark";
        String result = instance.trieFind(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        /*fail("The test case is a prototype.");
        }*/
    }
}
