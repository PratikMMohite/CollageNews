package codes.speed.collagenews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    EditText name, cname, bname, email, pass, roll;
    Button signUp;
    ProgressBar bar;
    private FirebaseAuth mAuth;


    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        bar = findViewById(R.id.progressBar);
        roll = findViewById(R.id.rollnum);
        name = findViewById(R.id.sfname);
        cname = findViewById(R.id.scname);
        bname = findViewById(R.id.branch);
        email = findViewById(R.id.semail);
        signUp = findViewById(R.id.signup);
        pass = findViewById(R.id.spass);
        bar.setVisibility(View.GONE);
        final Intent homepage = new Intent(this, Homepage.class);
        final Intent login = new Intent(this, MainActivity.class);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bar.setVisibility(View.VISIBLE);
                mAuth = FirebaseAuth.getInstance();
                mAuth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                        .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    mDatabase = FirebaseDatabase.getInstance().getReference();
                                    FirebaseUser user = mAuth.getCurrentUser();

                                    mDatabase.child("collage").child("Users").child(user.getUid()).child("profile").child("name").setValue(name.getText().toString());
                                    mDatabase.child("collage").child("Users").child(user.getUid()).child("profile").child("collage").setValue(cname.getText().toString());
                                    mDatabase.child("collage").child("Users").child(user.getUid()).child("profile").child("branch").setValue(bname.getText().toString());
                                    mDatabase.child("collage").child("Users").child(user.getUid()).child("profile").child("rnum").setValue(roll.getText().toString());
                                    bar.setVisibility(View.GONE);
                                    startActivity(homepage);
                                } else {
                                    bar.setVisibility(View.GONE);
                                    Toast.makeText(SignUp.this, "Failed to signUp,User already exist", Toast.LENGTH_SHORT).show();
                                    startActivity(login);
                                }


                            }
                        });
            }
        });


    }
}
