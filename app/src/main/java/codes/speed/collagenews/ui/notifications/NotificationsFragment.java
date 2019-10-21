package codes.speed.collagenews.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import codes.speed.collagenews.MainActivity;
import codes.speed.collagenews.R;

public class NotificationsFragment extends Fragment {
    Button sout;
    TextView a, b, c, d;
    private NotificationsViewModel notificationsViewModel;
    private DatabaseReference mDatabase;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        final TextView textView = root.findViewById(R.id.spemail);
        a = root.findViewById(R.id.spname);
        b = root.findViewById(R.id.spcollage);
        c = root.findViewById(R.id.spbranch);
        d = root.findViewById(R.id.sprollno);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("collage").child("Users").child(user.getUid()).child("profile").child("branch").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                c.setText(snapshot.getValue().toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        mDatabase.child("collage").child("Users").child(user.getUid()).child("profile").child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                a.setText(snapshot.getValue().toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        mDatabase.child("collage").child("Users").child(user.getUid()).child("profile").child("collage").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                b.setText(snapshot.getValue().toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        mDatabase.child("collage").child("Users").child(user.getUid()).child("profile").child("rnum").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                d.setText(snapshot.getValue().toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        notificationsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        sout = root.findViewById(R.id.signout);
        sout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();

                Intent a = new Intent(getContext(), MainActivity.class);
                startActivity(a);
                Toast.makeText(getContext(), "Successfully Sign Out", Toast.LENGTH_SHORT).show();

            }
        });
        return root;
    }
}