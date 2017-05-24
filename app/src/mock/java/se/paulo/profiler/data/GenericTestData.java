package se.paulo.profiler.data;

import se.paulo.profiler.data.auth.Credentials;
import se.paulo.profiler.data.auth.User;
import se.paulo.profiler.data.database.Profile;

/** * Created by Paulo Vila Nova on 2017-05-19.
 */

public class GenericTestData {

    public static String getValidEmail(){
        return "derp@example.com";
    }

    public static String getInvalidEmail(){
        return "derpatexample.com";
    }

    public static String getName(){
        return "Derp";
    }

    public static String getValidPassword(){
        return "123456";
    }

    public static String getInvalidPassword(){
        return "12345";
    }

    public static User getUser(){
        return new User("derp@example.com", "someId");
    }

    public static Credentials getCredentials(){
        return new Credentials("123456", "Derp", "derp@example.com");
    }

    public static Profile getProfile(){
        return new Profile(
                "",
                "",
                "someId",
                "derp@example.com",
                "",
                "Derp"
        );

    }


    public static String getPhotoUri(){
        return "derp/example.jpeg";
    }





}
