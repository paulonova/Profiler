package se.paulo.profiler.data.auth;

import io.reactivex.Completable;
import io.reactivex.Maybe;

/** * Created by Paulo Vila Nova on 2017-05-18.
 */

public interface AuthSource {


    Completable createAccount(Credentials cred);

    Completable attemptLogin(Credentials cred);

    Completable deleteUser();

    Maybe<User> getUser();

    Completable logUserOut();

    Completable reauthenticateUser(String password);

    void setReturnFail(boolean bool);
}
