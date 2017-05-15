package reader;

import java.io.*;
import java.util.Scanner;


public class TextFileReader implements FileReader {

    Scanner scanner = null;
    public TextFileReader(String fileName){

        try {
            scanner = new Scanner(new File(System.getProperty("user.dir")+"\\"+fileName)).useDelimiter("\\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


    public String getNextLine() {
        return scanner.nextLine();
    }

    public boolean isEndOfFile(){
        return !scanner.hasNextLine();
    }
}
