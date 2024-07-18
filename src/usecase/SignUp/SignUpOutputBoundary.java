package usecase.SignUp;

public interface SignUpOutputBoundary {
    void prepareSuccessView(SignUpOutputData user);

    void prepareFailView(String error);
}
