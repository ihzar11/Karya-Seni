package org.android.jplas.projectsuperman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textidentitas, textemail, textpassword;
    Button btnsignup, btnlogin;
    EditText edtemail, edtpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textemail = (TextView)findViewById(R.id.email);
        textpassword = (TextView)findViewById(R.id.password);
        textidentitas = (TextView)findViewById(R.id.identitasapk1);
        Typeface customFont= Typeface.createFromAsset(getAssets(),"font/Hollows.ttf");
        Typeface customFont2= Typeface.createFromAsset(getAssets(),"font/gomarice_round_pop.ttf");
        textemail.setTypeface(customFont2);
        textpassword.setTypeface(customFont2);
        textidentitas.setTypeface(customFont);
        btnsignup = (Button)findViewById(R.id.btnsignup);
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Signup.class);
                startActivity(i);
            }
        });
        edtemail = (EditText)findViewById(R.id.editemail);
        edtpassword = (EditText)findViewById(R.id.editpassword);
        btnlogin = (Button)findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String email = edtemail.getText().toString();
            String password = edtpassword.getText().toString();
            if (email.isEmpty())
            {
                edtemail.setError("Please Input Email!");
                edtemail.requestFocus();
            }
            else if(password.isEmpty())
            {
                edtpassword.setError("Please Input Correct Password");
                edtpassword.requestFocus();
            }
            else{
                Intent i = new Intent(MainActivity.this, Camera.class);
                startActivity(i);
            }
            }
        });
    }
}
