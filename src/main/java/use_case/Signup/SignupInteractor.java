package use_case.Signup;

import entity.User;
import entity.UserFactory;

import java.time.LocalDateTime;


/**
 * The SignupInteractor class implements the SignupInputBoundary interface and handles the user signup process.
 * It manages user registration by validating input data, interacting with the data access layer, and presenting results.
 */
public class SignupInteractor implements SignupInputBoundary {
    final SignupUserDataAccessInterface userDataAccessObject;
    final SignupOutputBoundary userPresenter;
    final UserFactory userFactory;

    /**
     * Constructs a new SignupInteractor instance with the specified data access interface, output boundary, and user factory.
     *
     * @param signupDataAccessInterface the interface for user data access operations.
     * @param signupOutputBoundary      the boundary for presenting signup results.
     * @param userFactory               the factory for creating User objects.
     */
    public SignupInteractor(SignupUserDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
    }

    /**
     * Executes the signup process using the provided input data.
     * It validates the input data, interacts with the data access layer to save the user,
     * and presents success or failure results based on the validation.
     *
     * @param signupInputData the input data for user signup, including username, email, password, and password confirmation.
     */
    @Override
    public void execute(SignupInputData signupInputData) {
        if (userDataAccessObject.existsByName(signupInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists.");
        } else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        } else {

            LocalDateTime now = LocalDateTime.now();
            User user = userFactory.create(signupInputData.getUsername(), signupInputData.getEmail(), signupInputData.getPassword(), now);
            userDataAccessObject.save(user);

            SignupOutputData signupOutputData = new SignupOutputData(user.getName(), user.getEmail(), now.toString(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }
}