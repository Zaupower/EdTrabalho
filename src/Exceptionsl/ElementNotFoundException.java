package src.Exceptionsl;

/**
 *Class for element not found
 */
public class ElementNotFoundException extends RuntimeException
{
    /**
     * Exception element not found
     * @param collection
     */
    public ElementNotFoundException (String collection)
    {
        super ("The target element is not in this " + collection);
    }
}
