package codes.speed.collagenews;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class loadClub extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    TextView clname;
    private RecyclerView specificclub;
    private PostAdapter postAdapter;
    private List<UserCon> cludlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_club);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("collage").child("Posts");
        cludlist = new ArrayList<>();
        specificclub = findViewById(R.id.specificclubinfo);


        clname = findViewById(R.id.clubinfoname);
        Intent intent = getIntent();
        String str = intent.getStringExtra("clubname");
        final String keys = intent.getStringExtra("clubkey");
        Toast.makeText(this, keys, Toast.LENGTH_SHORT).show();
        clname.setText(str);

        specificclub.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        specificclub.setLayoutManager(linearLayoutManager);
        postAdapter = new PostAdapter(this, cludlist);
        specificclub.setAdapter(postAdapter);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                cludlist.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    UserCon user = new UserCon();
                    user = data.getValue(UserCon.class);
                    if (user.getCatagary().equals(keys)) {
                        cludlist.add(user);
                        Log.d("jack", "Matched");
                    }

                }
                postAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
