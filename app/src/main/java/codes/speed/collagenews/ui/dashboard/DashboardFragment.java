package codes.speed.collagenews.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

import codes.speed.collagenews.Homepage;
import codes.speed.collagenews.R;
import codes.speed.collagenews.loadClub;

public class DashboardFragment extends Fragment {
    FirebaseDatabase database;
    DatabaseReference reference;
    private TextView t1;

    private ListView lv;
    List<String> club = new ArrayList<String>();
    List<String> keys = new ArrayList<String>();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final Intent showactivity = new Intent(getContext(), loadClub.class);
        lv = root.findViewById(R.id.clublist);
        t1 = root.findViewById(R.id.clubname);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("clubs");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                club.clear();
                keys.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    System.out.println(ds.getValue(String.class));
                    club.add(ds.getValue(String.class));
                    keys.add(ds.getKey());
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
                // Toast.makeText(getContext(), club.get(position).toString(), Toast.LENGTH_SHORT).show();
                showactivity.putExtra("clubname", club.get(position));
                showactivity.putExtra("clubkey", keys.get(position));
                startActivity(showactivity);
            }
        });

        return root;
    }
}


