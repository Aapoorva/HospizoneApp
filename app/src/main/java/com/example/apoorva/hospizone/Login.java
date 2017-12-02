package com.example.apoorva.hospizone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    Button buttonlogin;
    EditText editTextemail,editTextpass;
    TextView textViewsignup;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() !=null ){
            Intent i = new Intent(this,NearByHospital.class);
            finish();
            startActivity(i);
        }

        progressDialog = new ProgressDialog(this);

        buttonlogin=(Button)findViewById(R.id.buttonlogin);
        editTextemail=(EditText)findViewById(R.id.editTextemail);
        editTextpass=(EditText)findViewById(R.id.editTextpass);
        textViewsignup=(TextView)findViewById(R.id.textViewsignup);

        buttonlogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                userlogin();
            }
        });

        textViewsignup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(Login.this,SignUp.class);
                startActivity(i);
            }
        });
    }

    public void userlogin(){

        String email = editTextemail.getText().toString();
        String pass = editTextpass.getText().toString();

        if(TextUtils.isEmpty(email)) {
            Toast.makeText(Login.this, "Email is Blank", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(pass)) {
            Toast.makeText(Login.this, "Password is Blank", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Please Wait....");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            Toast.makeText(Login.this,"Successful Login",Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(Login.this,NearByHospital.class);
                            finish();
                            startActivity(i);
                        }
                        else{
                            progressDialog.dismiss();
                            Toast.makeText(Login.this,"Invalid Login Credentials",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
