package se.paulo.profiler.data.database;

import io.reactivex.Completable;
import io.reactivex.Maybe;

/** * Created by Paulo Vila Nova on 2017-05-23.
 */

public interface DatabaseSource {

    /**
     * Create a profile to write to the Firebase Cloud Database
     */
    Completable createProfile(Profile profile);

    /**
     *
     */
    Maybe<Profile> getProfile(String uid);

    /**
     *
     */
    Completable deleteProfile(String uid);

    /**
     *
     */
    Completable updateProfile(Profile profile);

    void setReturnFail(boolean bool);

    void setReturnEmpty(boolean bool);
}
