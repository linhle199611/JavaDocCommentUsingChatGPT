import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;


public class readFile {
    public static void main(String[] args) {
        // Specify the file path you want to read
        String filePath = "sum.txt";
        String result = read(filePath);
        System.out.println(result);
    }

    public static String read(String s){
        Path filePath = Path.of(s);
        String fileContent = "";

        try{
            // Read the file contents into a string
            fileContent = Files.readString(filePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileContent;
    }
    
}
