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
    
    private Trie trie;
    
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

    @BeforeEach
    void setup() throws InvalidFormatException, IOException {
        this.trie = new Trie(2);  
       
    }
    
    @Test
   
    void testInsertAndFind() {
        assertDoesNotThrow(() -> trie.insertTrie("cat"));
        assertEquals("a", trie.trieFind("c"), "Should give 'a' after 'c'");
        assertEquals("t", trie.trieFind("ca"), "Should give 't' after 'ca'");
    }

    @Test
    void testBoundaries() {
        assertDoesNotThrow(() -> trie.insertTrie("ab"));  // test minimal length insert
        assertDoesNotThrow(() -> trie.insertTrie("abc"));  // test exact n-gram length insert
        assertDoesNotThrow(() -> trie.insertTrie("abcd"));  // test beyond n-gram length insert, should not insert
        assertEquals("", trie.trieFind("abc"), "No followers should be given for 'ab'");
    }
    
    @Test
    void testForFollowers() {
    assertDoesNotThrow(() -> trie.insertTrie("mik"));
    assertDoesNotThrow(() -> trie.insertTrie("mil"));
    // Giving the next character after the 2-character prefix "mi" (from "mik" and "mil")
    String prediction = trie.trieFind("mi");
    assertNotNull(prediction, "Next letter should not be null");
    assertTrue(prediction.length() == 1, "Next letter should be a single character");
    assertTrue(prediction.equals("k") || prediction.equals("l"), "Should give 'k' or 'l' as the next character after 'mi'");
}
    
    @Test
    void testNoFollowers() {
    assertEquals("", trie.trieFind("xyz"), "Non-existing prefix should return empty prediction");
    }
    
    @Test
    void testEmptyInput() throws IOException, InvalidFormatException {
    trie.insertTrie("boc");  
    trie.insertTrie("abd");
    String prediction = trie.trieFind("");    
    assertTrue(!prediction.isEmpty());
    }
    
}
    
    
    
    

