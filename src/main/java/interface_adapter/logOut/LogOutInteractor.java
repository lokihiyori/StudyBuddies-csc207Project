package interface_adapter.logOut;


/**
 * Interactor class that handles the logic for logging out a user.
 * Implements the {@link LogOutInputBoundary} interface to define the execute method.
 */
public class LogOutInteractor implements LogOutInputBoundary {

    /**
     * Output boundary for handling success view preparation.
     * This is used to notify the view about the success of the logout operation.
     */
    final LogoOutOutputBoundary logoOutOutputBoundary;

    /**
     * Constructs a {@code LogOutInteractor} with the specified {@code LogoOutOutputBoundary}.
     *
     * @param logoOutOutputBoundary the output boundary for preparing the success view
     */
    public LogOutInteractor(LogoOutOutputBoundary logoOutOutputBoundary) {
        this.logoOutOutputBoundary = logoOutOutputBoundary;

    }

    /**
     * Executes the logout operation.
     * Calls the {@code prepareSuccessView} method on the {@code LogoOutOutputBoundary}
     * to notify the view that the logout was successful.
     */

    @Override
    public void execute() {
        logoOutOutputBoundary.prepareSuccessView();

    }
}
