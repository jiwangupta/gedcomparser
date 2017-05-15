import exception.InvalidLineItemException;
import org.junit.Test;


public class XMLGeneratorTest {

    @Test
    public void testGetXMLValueWithOneTag(){
        String xmlValue = "<gedcom><indi id=\"@l1@\"></indi></gedcom>";
        XMLGenerator xmlGenerator = new XMLGenerator("gedcom");
        try {
            xmlGenerator.addNodeTag(new ValueRetriever("0 @l1@ INDI"));
        } catch (InvalidLineItemException e) {
            e.printStackTrace();
        }

        assert xmlGenerator.getXMLValue().equals(xmlValue):"Not valid value";
    }

    @Test
    public void testGetXMLValueWithMultiTag() throws InvalidLineItemException {
        String xmlValue = "<gedcom><indi id=\"@l1@\">"+
                            "<name value=\"Jamis Gordon /Buck/\">"+
                            "<surn>Buck</surn>"+
                            "<givn>Jamis Gordon</givn>"+
                            "</name><sex>M</sex>"+
                            "</indi></gedcom>";
        XMLGenerator xmlGenerator = new XMLGenerator("gedcom");
        xmlGenerator.addNodeTag(new ValueRetriever("0 @l1@ INDI"));
        xmlGenerator.addNodeTag(new ValueRetriever("1 NAME Jamis Gordon /Buck/"));
        xmlGenerator.addNodeTag(new ValueRetriever("2 SURN Buck"));
        xmlGenerator.addNodeTag(new ValueRetriever("2 GIVN Jamis Gordon"));
        xmlGenerator.addNodeTag(new ValueRetriever("1 SEX M"));

        assert xmlGenerator.getXMLValue().equals(xmlValue):"Not valid value";
    }
}
