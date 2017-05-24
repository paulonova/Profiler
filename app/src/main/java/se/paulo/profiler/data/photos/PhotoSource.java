package se.paulo.profiler.data.photos;

import android.content.ContentResolver;

import java.util.List;

import io.reactivex.Maybe;

/** * Created by Paulo Vila Nova on 2017-05-24.
 */

public interface PhotoSource {
    Maybe<List<Photo>> getThumbnails(ContentResolver resolver);

    void setReturnFail(boolean bool);
}

