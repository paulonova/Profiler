package se.paulo.profiler.data.scheduler;

import se.paulo.profiler.util.BaseSchedulerProvider;
import se.paulo.profiler.util.ImmediateSchedulerProvider;

/** * Created by Paulo Vila Nova on 2017-05-23.
 */

public class SchedulerInjection {

    public static BaseSchedulerProvider provideSchedulerProvider(){
        return ImmediateSchedulerProvider.getInstance();
    }

}
