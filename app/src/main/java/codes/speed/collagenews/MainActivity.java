package codes.speed.collagenews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button login,su;
   TextView user,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    user =findViewById(R.id.editText);
    pass=findViewById(R.id.editText2);
    su=findViewById(R.id.button2);
login=findViewById(R.id.button);
        final Intent log = new Intent(this, Homepage.class);
        final Intent signUp = new Intent(this, SignUp.class);

login.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        if(user.getText().toString().equals("a")&&pass.getText().toString().equals("1"))
        {
            startActivity(log);
        }
        else {

            Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
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
}
