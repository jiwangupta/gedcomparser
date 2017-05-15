package writer;

import java.io.FileWriter;
import java.io.IOException;


public class GedcomFileWriter {

    String fileName;
    public GedcomFileWriter(String fileName){
        this.fileName = fileName;
    }

    public boolean writeToFile(String fileContent){
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(fileContent);
            fileWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
