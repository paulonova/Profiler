package se.paulo.profiler.data.photos;

/** * Created by Paulo Vila Nova on 2017-05-24.
 */

public class PhotoInjection {

    public static PhotoSource providePhotoSource() {
        return FakePhotoService.getInstance();
    }

}
