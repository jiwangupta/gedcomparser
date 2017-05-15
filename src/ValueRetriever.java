import exception.InvalidLineItemException;


public class ValueRetriever {

    String[] dataList;

    public ValueRetriever(String lineItem) throws InvalidLineItemException{
        if(lineItem==null || lineItem.isEmpty()){
            throw new InvalidLineItemException("Line item value in empty or null");
        }
        dataList = lineItem.split(" ");
        if(dataList.length < 2){
            throw new InvalidLineItemException("Value "+lineItem+" does not have sufficient information to create XML tag");
        }
    }

    /**
     * Method will retrieve first word from input string like 0 @I0001@ INDI
     * @return 0
     */
    public int getLevel(){
        return Integer.parseInt(dataList[0]);
    }

    /**
     * Method will retrieve second word from input string like 0 @I0001@ INDI
     * @return @I0001@
     */
    public String getTagOrID(){
        return dataList[1];
    }

    /**
     * Method will return complete string after second word
     * input 1 NAME Elizabeth Alexandra Mary /Windsor/
     * @return Elizabeth Alexandra Mary /Windsor/
     */
    public String getData(){
        if(dataList.length > 2){
            StringBuffer data = new StringBuffer();
            int i=1;
            while (i++ < dataList.length-1){
                data.append(dataList[i]).append(" ");
            }
            return data.toString().trim();
        }
        else return "";

    }

    public boolean isLineItemHasID(){
        return getTagOrID().startsWith("@") &&
        getTagOrID().endsWith("@");
    }
}
