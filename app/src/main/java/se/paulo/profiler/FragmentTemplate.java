package se.paulo.profiler;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTemplate extends Fragment {

//    private Contract.Presenter presenter;

    public FragmentTemplate newInstance() {
        FragmentTemplate fragmentTemplate = new FragmentTemplate();
        return fragmentTemplate;
    }


//    @Override
//    public void setPresenter(Contract.Presenter presenter){
//        this.presenter = presenter;
//    }
//
//    public FragmentTemplate() {
//        // Required empty public constructor
//    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**This is important: It helps our View/Presenter/Service survive orientation*/
        this.setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile_page, container, false);


        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        if(presenter == null){
//            presenter = new Presenter();
//        }
//        presenter.subscribe();

    }

    @Override
    public void onDestroy() {
//        presenter.unsubscribe();
        super.onDestroy();

    }
}
