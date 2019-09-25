package codes.speed.collagenews;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class SignUp extends AppCompatActivity {
    RecyclerView rec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        rec = findViewById(R.id.rec);
    }
}
