package e.valka.firebasead;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Main2Activity extends AppCompatActivity {
    FirebaseAuth firebase;
    Button iniciar;
    Button registrarse;
    EditText email;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        registrarse = findViewById(R.id.registrarse);
        email = findViewById(R.id.correo);
        password = findViewById(R.id.contrasenia);
        firebase = FirebaseAuth.getInstance();
        registrarse.setOnClickListener((view)->{
            firebase.createUserWithEmailAndPassword(""+email.getText().toString(),""+password.getText().toString()).addOnSuccessListener((s)->{
                Toast.makeText (this, "El usuario ha sido creado exitosamente.", Toast.LENGTH_SHORT).show ();
                finish();
            }).addOnFailureListener((f)->{
                Toast.makeText (this, ""+f.getMessage(), Toast.LENGTH_SHORT).show ();
            });
        });

    }
}
