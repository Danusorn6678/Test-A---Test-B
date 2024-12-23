import java.io.*;

public class TeamB {
    private String name;
    private int age;
    private String grade;

    public void readFile(String filePath) throws IOException {
        File file = new File(filePath);

        if (!file.exists()) {
            throw new IOException("Error reading the file: " + filePath + " (The system cannot find the file specified)");
        }

        if (file.length() == 0) {
            throw new IOException("File is empty or data is incomplete");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Name:")) {
                    this.name = line.substring(5).trim();
                } else if (line.startsWith("Age:")) {
                    try {
                        this.age = Integer.parseInt(line.substring(4).trim());
                    } catch (NumberFormatException e) {
                        // แจ้งข้อผิดพลาดเมื่อไม่สามารถแปลงอายุเป็นตัวเลขได้
                        throw new IOException("Invalid age format");
                    }
                } else if (line.startsWith("Software Testing Grade:")) {
                    this.grade = line.substring(23).trim();
                }
            }

            // ตรวจสอบว่าไฟล์มีข้อมูลครบถ้วน
            if (this.name == null || this.grade == null || this.age == 0) {
                throw new IOException("File is empty or data is incomplete");
            }
        } catch (IOException e) {
            throw new IOException("Error reading the file: " + filePath, e);
        }
    }

    public int calculateBuddhistEra() {
        return 2563 - this.age; 
    }

    public static String convertGradeToRank(String grade) {
        switch (grade) {
            case "A":
                return "Excellent";
            case "B+":
                return "Distinction";
            case "B":
                return "Good";
            case "C":
                return "Average";
            default:
                return "Fail";
        }
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGrade() {
        return grade;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
