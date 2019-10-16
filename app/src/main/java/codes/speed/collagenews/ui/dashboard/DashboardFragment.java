package codes.speed.collagenews.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import codes.speed.collagenews.Homepage;
import codes.speed.collagenews.R;

public class DashboardFragment extends Fragment {
    FirebaseDatabase database;
    DatabaseReference reference;

    private ListView lv;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        lv = root.findViewById(R.id.clublist);


        database = FirebaseDatabase.getInstance();
        reference = database.getReference("clubs");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> club = new ArrayList<String>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    System.out.println(ds.getValue(String.class));
                    club.add(ds.getValue(String.class));
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                            getContext(),
                            android.R.layout.simple_list_item_1,
                            club);

                    lv.setAdapter(arrayAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        final Intent home = new Intent(getContext(), Homepage.class);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(home);

            }
        });

        return root;
    }
}


