package interface_adapter;

import interface_adapter.LoginSignup.LoginSignupViewModel;
import interface_adapter.SignUp.SignUpViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_In.LoggedInState;
import interface_adapter.logged_In.LoggedInViewModel;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import use_case.Login.LoginOutputData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class LoginPresenterTest {

    private LoginPresenter presenter;
    private ViewManagerModel mockViewManagerModel;
    private LoggedInViewModel mockLoggedInViewModel;
    private LoginViewModel mockLoginViewModel;
    private SignUpViewModel mockSignUpViewModel;
    private LoginSignupViewModel mockLoginSignupViewModel;

    @BeforeEach
    void setUp() {
        mockViewManagerModel = mock(ViewManagerModel.class);
        mockLoggedInViewModel = mock(LoggedInViewModel.class);
        mockLoginViewModel = mock(LoginViewModel.class);
        mockSignUpViewModel = mock(SignUpViewModel.class);
        mockLoginSignupViewModel = mock(LoginSignupViewModel.class);

        presenter = new LoginPresenter(mockViewManagerModel, mockLoggedInViewModel, mockLoginViewModel, mockSignUpViewModel, mockLoginSignupViewModel);
    }

    @Test
    void testPrepareSuccessView_withResponse() {
        // Arrange
        LoginOutputData response = new LoginOutputData("user123", false);
        LoggedInState mockLoggedInState = mock(LoggedInState.class);
        when(mockLoggedInViewModel.getState()).thenReturn(mockLoggedInState);
        when(mockLoggedInViewModel.getViewName()).thenReturn("LoggedInView");

        // Act
        presenter.prepareSuccessView(response);

        // Assert
        verify(mockLoggedInState).setUsername("user123");
        verify(mockLoggedInViewModel).setState(mockLoggedInState);
        verify(mockLoggedInViewModel).firePropertyChanged();
        verify(mockViewManagerModel).setActiveView("LoggedInView");
        verify(mockViewManagerModel).firePropertyChanged();
    }

    @Test
    void testPrepareFailView() {
        // Arrange
        String error = "Invalid credentials";
        LoginState mockLoginState = mock(LoginState.class);
        when(mockLoginViewModel.getState()).thenReturn(mockLoginState);

        // Act
        presenter.prepareFailView(error);

        // Assert
        verify(mockLoginState).setUsernameError(error);
        verify(mockLoginViewModel).firePropertyChanged();
    }

    @Test
    void testPrepareSuccessView_withoutResponse() {
        // Arrange
        when(mockSignUpViewModel.getViewName()).thenReturn("SignUpView");

        // Act
        presenter.prepareSuccessView();

        // Assert
        verify(mockViewManagerModel).setActiveView("SignUpView");
        verify(mockViewManagerModel).firePropertyChanged();
    }
}
