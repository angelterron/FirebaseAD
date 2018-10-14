package e.valka.firebasead;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth firebase;
    Button iniciar;
    Button registrarse;
    EditText email;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciar  = findViewById(R.id.iniciarsesion);
        registrarse = findViewById(R.id.registrarse);
        email = findViewById(R.id.correo);
        password = findViewById(R.id.contrasenia);
        firebase = FirebaseAuth.getInstance();
        iniciar.setOnClickListener((view)->{
            firebase.signOut();
            firebase.signInWithEmailAndPassword(""+email.getText().toString(),""+password.getText().toString()).addOnSuccessListener((s)->{
                Toast.makeText (this, "¡Bienvenido!", Toast.LENGTH_SHORT).show ();
                Intent datos = new Intent(this,Main3Activity.class);
                startActivity(datos);
            }).addOnFailureListener((f)->{
                Toast.makeText (this, ""+f.getMessage(), Toast.LENGTH_SHORT).show ();
            });
        });
        registrarse.setOnClickListener((view)->{
            Intent activityRegistrar = new Intent(this,Main2Activity.class);
            startActivity(activityRegistrar);
        });
    }
}
