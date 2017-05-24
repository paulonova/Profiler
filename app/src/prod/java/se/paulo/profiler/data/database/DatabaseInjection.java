package se.paulo.profiler.data.database;

/** * Created by Paulo Vila Nova on 2017-05-23.
 */

public class DatabaseInjection {
    public static DatabaseSource provideDatabaseSource() {
        return FirebaseDatabaseService.getInstance();
    }
}
