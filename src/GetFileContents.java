import java.io.File;
import java.io.FileInputStream;

class GetFileContents {

    public String ReadFileContents(String file) {
        File fileToRead = new File(file);
        String fileToString = "";
        try (FileInputStream fileInputStream = new FileInputStream(file)){
            int content;
            while ( (content = fileInputStream.read()) != -1) {
                // convert to char and display:
//                System.out.print((char) content);
                fileToString += (char) content;
            }
        } catch (Exception e) {
            System.out.println("Could not open file...");
        }
        return fileToString;
    }

    public void PrintFileContents(String file) {
        System.out.println(ReadFileContents(file));

    }
}
