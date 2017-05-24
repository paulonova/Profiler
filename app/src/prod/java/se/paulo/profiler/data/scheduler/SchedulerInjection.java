package se.paulo.profiler.data.scheduler;

import se.paulo.profiler.util.BaseSchedulerProvider;
import se.paulo.profiler.util.ImmediateSchedulerProvider;

/** * Created by Paulo Vila Nova on 2017-05-23.
 */

//The real one

public class SchedulerInjection {

    public static BaseSchedulerProvider provideSchedulerProvider(){
        return SchedulerProvider.getInstance();
    }

}
