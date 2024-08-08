package app;
import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.LoginSignup.LoginSignupViewModel;
import interface_adapter.SignUp.SignUpViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_In.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_case.Cancel.CancelInputBoundary;
import use_case.Cancel.CancelInteractor;
import use_case.Cancel.CancelOutputBoundary;
import use_case.Login.LoginInputBoundary;
import use_case.Login.LoginInteractor;
import use_case.Login.LoginOutputBoundary;
import view.LoginView;

import javax.swing.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class LoginUseCaseFactoryTest {

    private ViewManagerModel viewManagerModel;
    private LoginViewModel loginViewModel;
    private LoggedInViewModel loggedInViewModel;
    private FileUserDataAccessObject userDataAccessObject;
    private SignUpViewModel signUpViewModel;
    private LoginSignupViewModel loginSignupViewModel;

    @BeforeEach
    void setUp() {
        viewManagerModel = mock(ViewManagerModel.class);
        loginViewModel = mock(LoginViewModel.class);
        loggedInViewModel = mock(LoggedInViewModel.class);
        userDataAccessObject = mock(FileUserDataAccessObject.class);
        signUpViewModel = mock(SignUpViewModel.class);
        loginSignupViewModel = mock(LoginSignupViewModel.class);
    }

    @Test
    void testCreateLoginView() {
        LoginView loginView = LoginUseCaseFactory.create(
                viewManagerModel,
                loginViewModel,
                loggedInViewModel,
                userDataAccessObject,
                signUpViewModel,
                loginSignupViewModel
        );

        assertNotNull(loginView);
        assertNotNull(loginView.getController());
        assertTrue(loginView.getController() instanceof LoginController);

        LoginController controller = loginView.getController();
        assertNotNull(controller.getLoginInputBoundary());
        assertTrue(controller.getLoginInputBoundary() instanceof LoginInteractor);

        LoginInteractor loginInteractor = (LoginInteractor) controller.getLoginInputBoundary();
        assertNotNull(loginInteractor.getUserDataAccessObject());
        assertNotNull(loginInteractor.getOutputBoundary());

        assertNotNull(controller.getCancelInputBoundary());
        assertTrue(controller.getCancelInputBoundary() instanceof CancelInteractor);

        CancelInteractor cancelInteractor = (CancelInteractor) controller.getCancelInputBoundary();
        assertNotNull(cancelInteractor.getOutputBoundary());
    }

}
