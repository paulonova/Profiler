package se.paulo.profiler.data.auth;

/** * Created by Paulo Vila Nova on 2017-05-16.
 */

public class FirebaseAuthService implements AuthSource {

    public FirebaseAuthService() {
    }


    public static AuthSource getInstance() {
        return new FirebaseAuthService();
    }



}
