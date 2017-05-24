package se.paulo.profiler.data.photos;

/** * Created by Paulo Vila Nova on 2017-05-24.
 */

public class Photo {

    private String photoUri;

    public Photo(String photoUri) {
        this.photoUri = photoUri;
    }

    public String getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }
}
