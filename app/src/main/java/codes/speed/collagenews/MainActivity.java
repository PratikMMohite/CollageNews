package codes.speed.collagenews;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button login, su;
    TextView user, pass;
    Intent log;
    ProgressBar pro;
    private FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            startActivity(log);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = findViewById(R.id.editText);
        pass = findViewById(R.id.editText2);
        su = findViewById(R.id.button2);
        login = findViewById(R.id.button);
        pro = findViewById(R.id.progressBar2);
        log = new Intent(this, Homepage.class);
        final Intent signUp = new Intent(this, SignUp.class);
        pro.setVisibility(View.GONE);
        mAuth = FirebaseAuth.getInstance();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(user.getText().toString().trim()) && !TextUtils.isEmpty(pass.getText().toString().trim())) {
                    LoginUser(user.getText().toString(), pass.getText().toString());
                } else {
                    Toast.makeText(MainActivity.this, "Email Or Password Can't be Empty", Toast.LENGTH_SHORT).show();
                }
            }

        });
        su.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(signUp);

            }
        });

    }

    private void LoginUser(String email, String password) {
        pro.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            pro.setVisibility(View.GONE);
                            startActivity(log);

                        } else {

                            Toast.makeText(MainActivity.this, "Login Failed. Enter valid Email and Password", Toast.LENGTH_SHORT).show();
                            pro.setVisibility(View.GONE);

                        }

                    }
                });

    }
}
