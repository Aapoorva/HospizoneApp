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

public class SignUp extends AppCompatActivity {

    String email,pass,repass,aadhars;
    Button submit;
    EditText em,pas,repas,aadhar;
    TextView textViewlogin;

    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() !=null ){
            Intent i = new Intent(this,NearByHospital.class);
            finish();
            startActivity(i);
        }

        progressDialog = new ProgressDialog(this);

        submit = (Button) findViewById(R.id.buttonsignup);
        em=(EditText)findViewById(R.id.editTextemail);
        pas=(EditText)findViewById(R.id.editTextpass);
        repas=(EditText)findViewById(R.id.editTextrepass);
        textViewlogin=(TextView)findViewById(R.id.textviewlogin);
        aadhar = (EditText)findViewById(R.id.editTextaadhar);

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                UserSignUp();
            }
        });
        textViewlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignUp.this,Login.class);
                startActivity(i);
            }
        });
    }

    public void UserSignUp(){
        email=em.getText().toString().trim();
        pass=pas.getText().toString().trim();
        repass=repas.getText().toString().trim();
        aadhars=aadhar.getText().toString().trim();


        if(TextUtils.isEmpty(email)) {
            Toast.makeText(SignUp.this, "Email is Empty", Toast.LENGTH_SHORT).show();
            em.setBackgroundResource(R.drawable.edittext_border);
            pas.setBackgroundResource(R.drawable.remove_border);
            repas.setBackgroundResource(R.drawable.remove_border);
            aadhar.setBackgroundResource(R.drawable.remove_border);
            return;
        }

        if(TextUtils.isEmpty(pass)) {
            Toast.makeText(SignUp.this, "Password is Empty", Toast.LENGTH_SHORT).show();
            em.setBackgroundResource(R.drawable.remove_border);
            pas.setBackgroundResource(R.drawable.edittext_border);
            repas.setBackgroundResource(R.drawable.remove_border);
            aadhar.setBackgroundResource(R.drawable.remove_border);
            return;
        }

        if(TextUtils.isEmpty(repass)) {
            Toast.makeText(SignUp.this, "Re type password", Toast.LENGTH_SHORT).show();
            em.setBackgroundResource(R.drawable.remove_border);
            pas.setBackgroundResource(R.drawable.remove_border);
            repas.setBackgroundResource(R.drawable.edittext_border);
            aadhar.setBackgroundResource(R.drawable.remove_border);
            return;
        }

        if(!(pass.equals(repass))){
            em.setBackgroundResource(R.drawable.remove_border);
            pas.setBackgroundResource(R.drawable.edittext_border);
            repas.setBackgroundResource(R.drawable.edittext_border);
            aadhar.setBackgroundResource(R.drawable.remove_border);
            Toast.makeText(SignUp.this, "Fields do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        if((TextUtils.isEmpty(aadhars))){
            em.setBackgroundResource(R.drawable.remove_border);
            pas.setBackgroundResource(R.drawable.remove_border);
            repas.setBackgroundResource(R.drawable.remove_border);
            aadhar.setBackgroundResource(R.drawable.edittext_border);
            return;
        }
        progressDialog.setMessage("Signing Up....");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            Toast.makeText(SignUp.this,"Registered",Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(SignUp.this,NearByHospital.class);
                            finish();
                            startActivity(i);
                        }
                        else{
                            progressDialog.dismiss();
                            Toast.makeText(SignUp.this,"Invalid Email or Already Exist",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
