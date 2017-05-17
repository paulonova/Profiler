package se.paulo.profiler.profilepage;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import se.paulo.profiler.R;

public class ProfilePageActivity extends AppCompatActivity {

    private static final String PROFILE_PAGE_FRAGMENT = "ProfilePageActivity";

    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        manager = this.getFragmentManager();
        ProfilePageFragment fragment = (ProfilePageFragment) manager.findFragmentByTag(PROFILE_PAGE_FRAGMENT);

        if(fragment == null){
            fragment = ProfilePageFragment.newInstance();
        }
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.cont_profile_page_fragment, fragment, PROFILE_PAGE_FRAGMENT).commit();
    }
}
