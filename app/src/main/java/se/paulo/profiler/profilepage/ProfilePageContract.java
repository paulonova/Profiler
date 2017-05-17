package se.paulo.profiler.profilepage;

import se.paulo.profiler.BasePresenter;
import se.paulo.profiler.BaseView;

/** * Created by Paulo Vila Nova on 2017-05-16.
 */

public interface ProfilePageContract {

    interface View extends BaseView<Presenter>{

        void setPresenter(ProfilePageContract.Presenter presenter);

        void setName (String name);

        void setEmail (String email);

        void setBio (String bio);

        void setInterests (String interests);

        void setProfilePhotoURL (String profilePhotoURL);

        void setDefaultProfilePhoto ();

        void startPhotoGalleryActivity();

        void startProfileDetailActivity();

        void startProfileSettingsActivity();

        void showLogoutSnackbar ();

        void startLoginActivity();

        void setThumbnailLoadingIndicator(boolean show);

        void setDetailLoadingIndicators(boolean show);

    }

    // Events!
    interface Presenter extends BasePresenter{
        void onThumbnailClick();

        void onEditProfileClick();

        void onLogoutClick();

        void onLogoutConfirmed();

        void onAccountSettingsClick();

        void onThumbnailLoaded();

    }
}
