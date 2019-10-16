package codes.speed.collagenews.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import codes.speed.collagenews.R;

public class HomeFragment extends Fragment {

    FirebaseDatabase database;
    DatabaseReference reference;
    ProgressBar bar;
    private ListView lv;
    TextView desc;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        View litem = inflater.inflate(R.layout.listitem, container, false);
        bar = root.findViewById(R.id.progressBar4);
        desc = litem.findViewById(R.id.description);

        bar.setVisibility(View.VISIBLE);
        lv = root.findViewById(R.id.homelist);


        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("collage").child("Posts");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> club = new ArrayList<String>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    club.add(ds.child("title").getValue().toString());
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                            getContext(),
                            R.layout.listitem,
                            R.id.tittle,
                            club);
                    bar.setVisibility(View.INVISIBLE);
                    lv.setAdapter(arrayAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        return root;

    }

}
