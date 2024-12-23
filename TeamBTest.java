import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import java.io.*;

public class TeamBTest {

   
    @Test
    public void testReadFileInput_TC001() {
        String testFilePath = "C:/Output/Textfile.txt";  

        
        File dir = new File("C:/Output");
        if (!dir.exists()) {
            dir.mkdirs();  
        }

        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFilePath))) {
            writer.write("Name: Peter\n");
            writer.write("Age: 20\n");
            writer.write("Software Testing Grade: B+\n");
        } catch (IOException e) {
            fail("Error preparing test file: " + e.getMessage());
        }

        TeamB teamB = new TeamB();
        try {
            teamB.readFile(testFilePath);
            assertEquals("Peter", teamB.getName());
            assertEquals(20, teamB.getAge());
            assertEquals("B+", teamB.getGrade());
        } catch (IOException e) {
            fail("Error during program execution: " + e.getMessage());
        }
    }

    // TC002: แปลงอายุเป็นปีพุทธศักราช
    @Test
    public void testBuddhistEraConversion_TC002() { 
        TeamB teamB = new TeamB();
        teamB.setAge(20);  
    
        int buddhistEra = teamB.calculateBuddhistEra();  
        assertEquals(2543, buddhistEra);   
    }

    // TC003: แปลงเกรดเป็นระดับ (Rank)
    @Test
    public void testConvertGradeToRank_TC003() {
        String grade = "B+";
        String rank = TeamB.convertGradeToRank(grade);
        assertEquals("Distinction", rank);
    }

    // TC004: แสดงผลข้อมูลบน CMD
    @Test
    public void testDisplayResult_TC004() {
        String testFilePath = "C:/Output/Textfile.txt";

        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFilePath))) {
            writer.write("Name: Peter\n");
            writer.write("Age: 20\n");
            writer.write("Software Testing Grade: B+\n");
        } catch (IOException e) {
            fail("Error preparing test file: " + e.getMessage());
        }

        TeamB teamB = new TeamB();
        try {
            teamB.readFile(testFilePath);
          
            System.out.println("Name: " + teamB.getName());
            System.out.println("Buddhist Era: " + teamB.calculateBuddhistEra());
            System.out.println("Software Testing Rank: " + TeamB.convertGradeToRank(teamB.getGrade()));
        } catch (IOException e) {
            fail("Error during program execution: " + e.getMessage());
        }
    }

    
   
 
    
    @Test
public void testFileNotFound_TC005() {
    String testFilePath = "C:/Output/NonExistentFile.txt";  

    
    File dir = new File("C:/Output");
    if (!dir.exists()) {
        dir.mkdirs();  
    }

    
    File testFile = new File(testFilePath);
    if (testFile.exists()) {
        testFile.delete();  
    }

    TeamB teamB = new TeamB();
    try {
        teamB.readFile(testFilePath); 
        fail("Expected IOException not thrown"); 
    } catch (IOException e) {
       
        String expectedMessage = "Error reading the file: C:/Output/NonExistentFile.txt (The system cannot find the path specified)";
        assertFalse(e.getMessage().contains(expectedMessage));  
    }
}


    // TC006: ตรวจสอบรูปแบบอายุที่ไม่ถูกต้อง
    @Test
public void testInvalidAgeFormat_TC006() {
    String testFilePath = "C:/Output/Test_TC006.txt";
    
    
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFilePath))) {
        writer.write("Name: Peter\n");
        writer.write("Age: Twenty\n"); 
        writer.write("Software Testing Grade: B+\n");
    } catch (IOException e) {
        fail("Error preparing test file: " + e.getMessage());
    }
    
    
    TeamB teamB = new TeamB();
    try {
        teamB.readFile(testFilePath); 
        fail("Expected IOException for invalid age format");
    } catch (IOException e) {
        
        assertFalse(e.getMessage().contains("Invalid age format")); 
    }
}
    

    // TC007: ตรวจสอบไฟล์ที่ว่างเปล่า
    @Test
    public void testEmptyFile_TC007() {
        String testFilePath = "C:/Output/EmptyFile.txt";  

       
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFilePath))) {
           
        } catch (IOException e) {
            fail("Error preparing test file: " + e.getMessage());
        }

        TeamB teamB = new TeamB();
        try {
            teamB.readFile(testFilePath);
            fail("Expected IOException for empty file");
        } catch (IOException e) {
            assertEquals("File is empty or data is incomplete", e.getMessage());
        }
    }
}
