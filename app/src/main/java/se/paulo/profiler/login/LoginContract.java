package se.paulo.profiler.login;

import android.support.annotation.StringRes;

import se.paulo.profiler.BasePresenter;
import se.paulo.profiler.BaseView;

/** * Created by Paulo Vila Nova on 2017-05-16.
 */

public interface LoginContract {

    interface View extends BaseView<LoginContract.Presenter> {
        void makeToast(@StringRes int stringId);

        void makeToast(String message);

        String getEmail();

        String getPassword();

        void startProfileActivity();

        void startCreateAccountActivity();

        void setPresenter(LoginContract.Presenter presenter);

        void showProgressIndicator(boolean show);
    }

    interface Presenter extends BasePresenter {
        void onLogInClick();

        void onCreateClick();

    }
}
