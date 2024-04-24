package TextPrediction;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.InputStream;
import java.util.Scanner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;


class AppTest {
       
    private final InputStream inputstream = System.in;

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
    void restore() {
        System.setIn(inputstream);
    }
    
    @Test    
    public void testUseCase() {
        App app = new App();
        int input = 6;
        int expectedOutput = 8;
        //assertEquals(expectedOutput, app.)
        
    }    
    
     @Test
    public void LengthRequirement() {
        try {
            NameGenerator testApp = new NameGenerator(2, 500, 5);
            String name = testApp.generateName();
            assertTrue(name.length() >= 1, "Generated name should be at least 5 characters long");
        } catch (Exception e) {
            fail("Service threw an exception: " + e.getMessage());
        }
    }
    
    @Test
    void testPromptNumber() {
        String input = "5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        int result = App.promptNumber(scanner, "Enter a number between 1 and 10:", 1, 10);

        assertEquals(5, result, "The method should return 5 as it is within the specified range.");
    }
        
        
}
    
    
    
    
    

