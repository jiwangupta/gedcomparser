import exception.InvalidLineItemException;
import org.junit.Assert;
import org.junit.Test;


public class ValueRetrieverTest {

    @Test
    public void testGetLevel() throws InvalidLineItemException {
        ValueRetriever valueRetriever = new ValueRetriever("0 @I0001@ INDI");
        assert valueRetriever.getLevel()==0:"Not valid value";
    }

    @Test
    public void testGetTagOrID() throws InvalidLineItemException {
        ValueRetriever valueRetriever = new ValueRetriever("0 @I0001@ INDI");
        assert valueRetriever.getTagOrID().equals("@I0001@"):"Not valid value";
    }

    @Test
    public void testGetData() throws InvalidLineItemException {
        ValueRetriever valueRetriever = new ValueRetriever("0 @I0001@ INDI");
        assert valueRetriever.getData().equals("INDI"):"Not valid value";
    }

    @Test
    public void testGetDataWithMultiValue() throws InvalidLineItemException {
        ValueRetriever valueRetriever = new ValueRetriever("1 NAME Elizabeth Alexandra Mary");
        assert valueRetriever.getData().equals("Elizabeth Alexandra Mary"):"Not valid value";
    }

    @Test
    public void testGetDataWithNoValue() throws InvalidLineItemException {
        ValueRetriever valueRetriever = new ValueRetriever("1 NAME");
        assert valueRetriever.getData().equals(""):"Not valid value";
    }

    @Test
    public void testIsLineItemHasIdWithPass() throws InvalidLineItemException {
        ValueRetriever valueRetriever = new ValueRetriever("0 @I0001@ INDI");
        Assert.assertTrue("Line item does not contains Id",valueRetriever.isLineItemHasID());
    }

    @Test
    public void testIsLineItemHasIdWithFail() throws InvalidLineItemException {
        ValueRetriever valueRetriever = new ValueRetriever("1 NAME Elizabeth Alexandra Mary");
        Assert.assertFalse("Line item should not contains Id",valueRetriever.isLineItemHasID());
    }

}
