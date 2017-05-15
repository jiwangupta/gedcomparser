import java.util.Stack;


public class XMLGenerator {

    StringBuffer xmlBuffer = new StringBuffer();

    Stack<String> tagStack = new Stack();
    int lastTagLevelValue=-1;

    public XMLGenerator(String rootTag){
        xmlBuffer.append("<").append(rootTag).append(">");
        tagStack.push(rootTag);
    }

    /**
     * add an xml tag by getting the value from input
     * @param valueRetriever
     */
    public void addNodeTag(ValueRetriever valueRetriever){
        //logic inside if will determine how many tags to be close before creating any new tag
        if(lastTagLevelValue >= valueRetriever.getLevel()) {
            while (lastTagLevelValue-- >= valueRetriever.getLevel()) {
                xmlBuffer.append("</").append(tagStack.pop().toLowerCase()).append(">");
            }
        }
        appendNode(valueRetriever);
        lastTagLevelValue = valueRetriever.getLevel();
    }

    private void appendNode(ValueRetriever valueRetriever) {
        if(valueRetriever.isLineItemHasID()){
            xmlBuffer.append("<").
                        append(valueRetriever.getData().toLowerCase()).
                        append(" id=\"").
                        append(valueRetriever.getTagOrID().toLowerCase()).
                        append("\">");
            tagStack.push(valueRetriever.getData());
        }
        else {
            //if tag is name then construct tag with value attribute else no value attribute
            if(valueRetriever.getTagOrID().equalsIgnoreCase("name")){
                xmlBuffer.append("<").append(valueRetriever.getTagOrID().toLowerCase()).
                        append(" value=\"").append(valueRetriever.getData()).append("\">");
            }
            else {
                xmlBuffer.append("<").append(valueRetriever.getTagOrID().toLowerCase()).
                        append(">").append(valueRetriever.getData());
            }
            tagStack.push(valueRetriever.getTagOrID());
        }

    }

    public String getXMLValue(){
        while (!tagStack.empty()){
            xmlBuffer.append("</").append(tagStack.pop().toLowerCase()).append(">");
        }
        return xmlBuffer.toString();
    }
}
