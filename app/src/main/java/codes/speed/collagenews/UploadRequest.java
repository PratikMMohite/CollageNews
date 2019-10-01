package codes.speed.collagenews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UploadRequest extends AppCompatActivity {
    Button file, req;
    EditText title1, content, catag, cname;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_request);

        file = findViewById(R.id.file);
        title1 = findViewById(R.id.title);
        content = findViewById(R.id.content);
        catag = findViewById(R.id.catagary);
        cname = findViewById(R.id.cname);
        req = findViewById(R.id.reqp);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitPost();
            }


        });


        file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooser();
            }
        });

    }

    private void submitPost() {
        final String tit = title1.getText().toString();
        final String con = content.getText().toString();
        final String cat = catag.getText().toString();
        final String cna = cname.getText().toString();
        UserCon use = new UserCon(tit, con, cat, cna, "empty");
        mDatabase.child("collage").child("Posts").push().setValue(use);

    }


    public void chooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

    public static final int PICK_IMAGE = 1;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE) {
            if (resultCode == RESULT_OK) {

                Toast.makeText(this, "Image Picked", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Image Not Found", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

