package codes.speed.collagenews.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import codes.speed.collagenews.PostAdapter;
import codes.speed.collagenews.R;
import codes.speed.collagenews.UserCon;

public class HomeFragment extends Fragment {


    private RecyclerView postview;
    private PostAdapter postAdapter;
    private List<UserCon> postList;


    private ProgressBar bar;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        postview = root.findViewById(R.id.postview);
        postview.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        postview.setLayoutManager(linearLayoutManager);
        postList = new ArrayList<>();
        postAdapter = new PostAdapter(getContext(), postList);
        postview.setAdapter(postAdapter);
        bar = root.findViewById(R.id.homeprogress);
        bar.setVisibility(View.VISIBLE);
        readData();

        return root;

    }

    private void readData() {
        FirebaseDatabase database;
        DatabaseReference reference;
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("collage").child("Posts");

        reference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                postList.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    UserCon post = data.getValue(UserCon.class);
                    postList.add(post);
                }
                bar.setVisibility(View.INVISIBLE);
                postAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
