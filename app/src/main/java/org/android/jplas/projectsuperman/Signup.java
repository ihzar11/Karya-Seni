package org.android.jplas.projectsuperman;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Signup extends AppCompatActivity {
    Button btnsignup;
    EditText textemail, textpassword;
    private FirebaseAuth mAuth;

    String TAG = "TAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        textemail = (EditText)findViewById(R.id.editemail2);
        textpassword = (EditText)findViewById(R.id.editpassword2);
        btnsignup = (Button)findViewById(R.id.btnsignup2);
        mAuth = FirebaseAuth.getInstance();
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String email = textemail.getText().toString();
            String password = textpassword.getText().toString();
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    //Athentication Success
                                    Toast.makeText(Signup.this, "Authentication Success.",
                                            Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(Signup.this, MainActivity.class);
                                    startActivity(i);
                                            //updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(Signup.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                            //updateUI(null);
                                }

                                // ...
                            }
                        });

            }
        });

    }
}
