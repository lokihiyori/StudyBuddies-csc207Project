package interface_adapter.logOut;
/**
 * Output boundary interface for handling logout operations.
 * This interface defines the method to prepare the view after a successful logout.
 */
public interface LogoOutOutputBoundary {

    /**
     * Prepares the view for a successful logout operation.
     * Implementations of this method should update the view to reflect that the user has been logged out.
     */
    void prepareSuccessView();

}
