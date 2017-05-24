package se.paulo.profiler.data.auth;

/** * Created by Paulo Vila Nova on 2017-05-18.
 */

public class AuthInjection  {

    public static AuthSource provideAuthSource(){
        return FirebaseAuthService.getInstance();
    }

}
