package se.paulo.profiler;

import android.support.annotation.StringRes;

/** * Created by Paulo Vila Nova on 2017-05-16.
 */

public interface BaseView<T> {
    void setPresenter(T presenter);

    void makeToast(@StringRes int stringId);

    void makeToast(String message);
}
