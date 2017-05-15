import exception.InvalidLineItemException;
import reader.FileReader;
import reader.TextFileReader;
import writer.GedcomFileWriter;



public class AppStarter {

    public static void main(String[] args) {
        String FILENAME = "sample.txt";

        FileReader fileReader = new TextFileReader(FILENAME);

        XMLGenerator xmlGenerator = new XMLGenerator("gedcom");
        while (!fileReader.isEndOfFile()){
            try {
                xmlGenerator.addNodeTag(new ValueRetriever(fileReader.getNextLine()));
            } catch (InvalidLineItemException e) {
                System.out.println("Error while parsing line item "+e.getMessage());
            }
        }
        GedcomFileWriter gedcomFileWriter = new GedcomFileWriter("gedcomXML");
        boolean status = gedcomFileWriter.writeToFile(xmlGenerator.getXMLValue());
        if (status){
            System.out.println("Text file has been converted to XML file successfully");
        }
        else {
            System.out.println("There is some issue observe while converting Text file to XML");
        }


    }


}
