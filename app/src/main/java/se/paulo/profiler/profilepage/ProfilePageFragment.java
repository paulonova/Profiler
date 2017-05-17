package se.paulo.profiler.profilepage;


import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import se.paulo.profiler.R;
import se.paulo.profiler.login.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilePageFragment extends Fragment implements ProfilePageContract.View {

    private ProfilePageContract.Presenter presenter;

    private TextView userBio, userInterests, userName, userEmail;
    private ImageView thumbnail;
    private FloatingActionButton editDetails;
    private ImageButton settings, logout;
    private ProgressBar avatarProgress, bioProgress, interestsProgress;



    public ProfilePageFragment() {
        // Required empty public constructor
    }

    public static ProfilePageFragment newInstance() {
        ProfilePageFragment fragment = new ProfilePageFragment();
        return fragment;
    }

    @Override
    public void setPresenter(ProfilePageContract.Presenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**This is important: It helps our View/Presenter/Service survive orientation*/
        this.setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile_page, container, false);

        userName = (TextView)rootView.findViewById(R.id.lbl_page_user_name);
        userEmail = (TextView)rootView.findViewById(R.id.lbl_page_user_email);

        avatarProgress = (ProgressBar)rootView.findViewById(R.id.pro_profile_page_thumbnail);
        bioProgress = (ProgressBar)rootView.findViewById(R.id.pro_profile_page_bio);
        interestsProgress = (ProgressBar)rootView.findViewById(R.id.pro_profile_page_interests);

        userBio = (TextView)rootView.findViewById(R.id.lbl_page_user_bio);
        userInterests = (TextView)rootView.findViewById(R.id.lbl_page_user_interests);

        editDetails = (FloatingActionButton) rootView.findViewById(R.id.fab_edit_profile_details);

        editDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onEditProfileClick();
            }
        });



        thumbnail = (ImageView) rootView.findViewById(R.id.imb_page_thumbnail);
        thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onThumbnailClick();
            }
        });

        settings = (ImageButton)rootView.findViewById(R.id.imb_page_user_settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onAccountSettingsClick();
            }
        });
        logout = (ImageButton)rootView.findViewById(R.id.imb_page_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onLogoutClick();
            }
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (presenter == null) {
//            presenter = new ProfilePagePresenter(
//                    AuthInjection.provideAuthSource(),
//                    this,
//                    DatabaseInjection.provideDatabaseSource(),
//                    SchedulerInjection.provideSchedulerProvider()
//            );
        }

    }

    @Override
    public void onDestroy() {
        presenter.unsubscribe();
        super.onDestroy();

    }

    @Override
    public void makeToast(@StringRes int stringId) {
        Toast.makeText(getActivity(), getString(stringId), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void makeToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

    }


    @Override
    public void setName(String name) {
        userName.setText(name);
    }

    @Override
    public void setEmail(String email) {
        userEmail.setText(email);
    }

    @Override
    public void setBio(String bio) {
        //breaking the MVP paradigm a bit by having this logic here, but I can't call
        //getString() from the presenter without adding another dependency to it
        if (bio.equals("")){
            userBio.setText(getString(R.string.default_bio));
        } else {
            userBio.setText(bio);
        }

    }

    @Override
    public void setInterests(String interests) {

        if (interests.equals("")){
            userInterests.setText(getString(R.string.default_interests));
        } else {
            userInterests.setText(interests);
        }
    }

    @Override
    public void setProfilePhotoURL(String profilePhotoURL) {
        Picasso.with(getActivity())
                .load(Uri.parse(profilePhotoURL))
                .noFade()
                .into(thumbnail, new Callback() {
                    @Override
                    public void onSuccess() {
                        presenter.onThumbnailLoaded();
                    }

                    @Override
                    public void onError() {
                        setDefaultProfilePhoto();
                    }
                });
        //CircleImageView requires noFade() to be set

    }

    @Override
    public void setDefaultProfilePhoto() {

        Picasso.with(getActivity())
                .load(R.drawable.default_profile_pic)
                .noFade()
                .into(thumbnail, new Callback() {
                    @Override
                    public void onSuccess() {
                        presenter.onThumbnailLoaded();
                    }

                    @Override
                    public void onError() {
                        Toast.makeText(getActivity(), getString(R.string.error_loading_image),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void startPhotoGalleryActivity() {
//        Intent i = new Intent(getActivity(), PhotoGalleryActivity.class);
//        startActivity(i);

    }

    @Override
    public void startProfileDetailActivity() {
//        Intent i = new Intent(getActivity(), ProfileDetailActivity.class);
//        startActivity(i);

    }

    @Override
    public void startProfileSettingsActivity() {
//        Intent i = new Intent(getActivity(), ProfileSettingsActivity.class);
//        startActivity(i);

    }

    @Override
    public void showLogoutSnackbar() {

        Snackbar.make(getView(),getString(R.string.action_log_out),Snackbar.LENGTH_SHORT)
                .setAction(R.string.action_log_out, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        presenter.onLogoutConfirmed();
                    }
                })
                .show();
    }

    @Override
    public void startLoginActivity() {
        Intent i = new Intent(getActivity(), LoginActivity.class);
        startActivity(i);
    }

    @Override
    public void setThumbnailLoadingIndicator(boolean show) {
        if (show){
            avatarProgress.setVisibility(View.VISIBLE);
            thumbnail.setVisibility(View.INVISIBLE);
            userName.setVisibility(View.INVISIBLE);
            userEmail.setVisibility(View.INVISIBLE);
        } else {
            avatarProgress.setVisibility(View.INVISIBLE);
            thumbnail.setVisibility(View.VISIBLE);
            userName.setVisibility(View.VISIBLE);
            userEmail.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void setDetailLoadingIndicators(boolean show) {
        if (show){
            bioProgress.setVisibility(View.VISIBLE);
            interestsProgress.setVisibility(View.VISIBLE);

            userBio.setVisibility(View.INVISIBLE);
            userInterests.setVisibility(View.INVISIBLE);
        } else {
            bioProgress.setVisibility(View.INVISIBLE);
            interestsProgress.setVisibility(View.INVISIBLE);

            userBio.setVisibility(View.VISIBLE);
            userInterests.setVisibility(View.VISIBLE);
        }

    }
}
