package se.paulo.profiler.createaccount;

import android.support.annotation.StringRes;

import se.paulo.profiler.BasePresenter;
import se.paulo.profiler.BaseView;

/** * Created by Paulo Vila Nova on 2017-05-16.
 */

public interface CreateAccountContract {

    interface View extends BaseView<Presenter> {
        void makeToast(@StringRes int stringId);

        String getEmail();

        String getPassword();

        String getPasswordConfirmation();

        String getName();

        //TODO: add name input to this component

        void startLoginActivity();

        void startProfilePageActivity();

        void setPresenter(CreateAccountContract.Presenter presenter);

        void showProgressIndicator(boolean show);
    }

    interface Presenter extends BasePresenter {
        void onCreateAccountClick();

        void subscribe();

        void unsubscribe();
    }
}
