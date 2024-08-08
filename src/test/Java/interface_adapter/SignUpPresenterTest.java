package interface_adapter;

import interface_adapter.LoginSignup.LoginSignupViewModel;
import interface_adapter.SignUp.SignUpPresenter;
import interface_adapter.SignUp.SignUpState;
import interface_adapter.SignUp.SignUpViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import use_case.LoginSignup.LoginSignupOutputData;
import use_case.Signup.SignupOutputData;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SignUpPresenterTest {

    private SignUpPresenter presenter;
    private SignUpViewModel mockSignupViewModel;
    private LoginViewModel mockLoginViewModel;
    private ViewManagerModel mockViewManagerModel;
    private LoginSignupViewModel mockLoginSignupViewModel;

    @BeforeEach
    void setUp() {
        mockSignupViewModel = mock(SignUpViewModel.class);
        mockLoginViewModel = mock(LoginViewModel.class);
        mockViewManagerModel = mock(ViewManagerModel.class);
        mockLoginSignupViewModel = mock(LoginSignupViewModel.class);
        presenter = new SignUpPresenter(mockViewManagerModel, mockSignupViewModel, mockLoginViewModel, mockLoginSignupViewModel);
    }

    @Test
    void testPrepareSuccessView() {
        // Arrange
        SignupOutputData response = new SignupOutputData("user123", "2023-01-01T00:00:00", "user123@example.com", false);
        SignUpState signupState = new SignUpState();
        LoginState loginState = new LoginState();
        when(mockSignupViewModel.getState()).thenReturn(signupState);
        when(mockLoginViewModel.getState()).thenReturn(loginState);
        PropertyChangeListener mockListener = mock(PropertyChangeListener.class);
        mockLoginViewModel.addPropertyChangeListener(mockListener);

        // Act
        presenter.prepareSuccessView(response);

        // Assert
        assertEquals("user123", loginState.getUsername());
        verify(mockLoginViewModel).firePropertyChanged();
        verify(mockViewManagerModel).setActiveView(mockLoginViewModel.getViewName());
        verify(mockViewManagerModel).firePropertyChanged();
    }

    @Test
    void testPrepareFailView() {
        // Arrange
        String errorMessage = "Username already taken";
        SignUpState signupState = new SignUpState();
        when(mockSignupViewModel.getState()).thenReturn(signupState);
        PropertyChangeListener mockListener = mock(PropertyChangeListener.class);
        mockSignupViewModel.addPropertyChangeListener(mockListener);

        // Act
        presenter.prepareFailView(errorMessage);

        // Assert
        assertEquals(errorMessage, signupState.getUsernameError());
        verify(mockSignupViewModel).firePropertyChanged();
    }

    @Test
    void testPrepareSuccessViewLoginSignup() {
        // Arrange
        LoginSignupOutputData user = new LoginSignupOutputData("user123");
        LoginState loginState = new LoginState();
        when(mockLoginViewModel.getState()).thenReturn(loginState);
        PropertyChangeListener mockListener = mock(PropertyChangeListener.class);
        mockLoginViewModel.addPropertyChangeListener(mockListener);

        // Act
        presenter.prepareSuccessView(user);

        // Assert
        assertEquals("user123", loginState.getUsername());
        verify(mockLoginViewModel).firePropertyChanged();
        verify(mockViewManagerModel).setActiveView(mockLoginViewModel.getViewName());
        verify(mockViewManagerModel).firePropertyChanged();
    }
}


