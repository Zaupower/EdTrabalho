package src.Exceptionsl;

/**
 * Class EmptyCollectionException
 */
public class EmptyCollectionException extends RuntimeException
{
    /**
     * Exception for collection empty
     * @param collection
     */
    public EmptyCollectionException (String collection)
    {
        super ("The " + collection + " is empty.");
    }
}

