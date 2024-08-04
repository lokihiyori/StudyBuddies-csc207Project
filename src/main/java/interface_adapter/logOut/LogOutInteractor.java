package interface_adapter.logOut;


public class LogOutInteractor implements LogOutInputBoundary {
    final LogoOutOutputBoundary logoOutOutputBoundary;


    public LogOutInteractor(LogoOutOutputBoundary logoOutOutputBoundary) {
        this.logoOutOutputBoundary = logoOutOutputBoundary;

    }

    @Override
    public void execute() {
        logoOutOutputBoundary.prepareSuccessView();

    }
}
