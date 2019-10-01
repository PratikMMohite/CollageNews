package codes.speed.collagenews.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import codes.speed.collagenews.R;

public class HomeFragment extends Fragment {


//    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //  homeViewModel =ViewModelProviders.of(this).get(HomeViewModel.class)
//        final TextView textView = root.findViewById(R.id.text_home);

//        homeViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });


        View root = inflater.inflate(R.layout.fragment_home, container, false);



        return root;
    }
}