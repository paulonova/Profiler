package se.paulo.profiler.util;

import io.reactivex.Scheduler;

/** * Created by Paulo Vila Nova on 2017-05-23.
 */

public interface BaseSchedulerProvider {

    Scheduler computation();

    Scheduler io();

    Scheduler ui();
}
