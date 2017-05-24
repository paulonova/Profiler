package se.paulo.profiler;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import se.paulo.profiler.data.auth.AuthInjection;
import se.paulo.profiler.data.auth.AuthSource;
import se.paulo.profiler.data.scheduler.SchedulerInjection;
import se.paulo.profiler.login.LoginContract;
import se.paulo.profiler.login.LoginPresenter;

/** * Created by Paulo Vila Nova on 2017-05-18.
 *
 * This is a Unit Test for the Presenter
 */

@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest extends Mockito {

    public static final String VALID_PASSWORD = "123456";

    public static final String INVALID_PASSWORD = "12345";

    public static final String LONG_PASSWORD = "12345678912345678912";

    public static final String USERNAME = "Derp";

    public static final String INVALID_EMAIL = "userexample.com";

    public static final String VALID_EMAIL = "user@example.com";

    @Mock
    private LoginContract.View view;

    private AuthSource authSource;
//    private DatabaseSource databaseSource;

    private LoginPresenter presenter;




    @Before  //setUp things before run the test
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this); // set all classes that have this @Mock annotation as a Mock

        authSource = AuthInjection.provideAuthSource();
        presenter = new LoginPresenter(
                authSource,
                view,
                SchedulerInjection.provideSchedulerProvider()
        );

        //authSource.setReturnFail();
    }


    /**
     * When user clicks login button, attempt to Log the user in.
     * */
    @Test
    public void whenUserLoginAttemptSucceeds() throws Exception{

        presenter.onLogInClick();
        verify(view).startProfileActivity(); //
    }

    @Test
    public void whenUserLoginAttemptFails() throws Exception{
        verify(view).makeToast(Mockito.anyString());

    }

    @Test
    public void whenUserClicksRegisterButton() throws Exception{
        verify(view).startCreateAccountActivity();

    }

    @Test
    public void whenActiveUserFound() throws Exception{
        verify(view).startProfileActivity();

    }

    @Test
    public void whenEmailEmpty () throws Exception {
        when(view.getEmail()).thenReturn("");
        when(view.getPassword()).thenReturn("123456");
//        verify(view).makeToast(R.string.error_empty_input);
        verify(view).makeToast(anyString());
    }


    @Test
    public void whenEmailInvalid () throws Exception {
        when(view.getEmail()).thenReturn("testemail");
        when(view.getPassword()).thenReturn("derp");
        presenter.onLogInClick();
        verify(view).makeToast(R.string.error_invalid_email);
    }

    @Test
    public void whenPasswordEmpty () throws Exception {
        when(view.getEmail()).thenReturn("derp@example.com");
        when(view.getPassword()).thenReturn("");
        presenter.onLogInClick();
        verify(view).makeToast(R.string.error_empty_input);
    }

    @Test
    public void whenPasswordTooLong () throws Exception {
        when(view.getEmail()).thenReturn("derp@example.com");
        when(view.getPassword()).thenReturn("12345678912345678912");
        presenter.onLogInClick();
        verify(view).makeToast(R.string.error_password_too_long);
    }




}
