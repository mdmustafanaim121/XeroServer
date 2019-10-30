package com.project.xeroserver;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.xeroserver.Common.Common;
import com.project.xeroserver.Model.User;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignIn extends AppCompatActivity {

    EditText xPhone,xPassword;
    Button signIn;

    FirebaseDatabase db;
    DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        xPhone = (MaterialEditText)findViewById(R.id.phoneNo);
        xPassword = (MaterialEditText)findViewById(R.id.xPassword);
        signIn = (Button)findViewById(R.id.signIn);


        //Init Firebase
        db = FirebaseDatabase.getInstance();
        users = db.getReference("user");

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser(xPhone.getText().toString(),xPassword.getText().toString());
            }
        });



    }

    private void loginUser(String phoneNo, String userPassword) {

        final ProgressDialog mDialog = new ProgressDialog(SignIn.this);
        mDialog.setMessage("Please wait");
        mDialog.show();

        final String localPhone = phoneNo;
        final String localPassword = userPassword;
        users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(localPhone).exists()) {
                    mDialog.dismiss();
                    User user = dataSnapshot.child(localPhone).getValue(User.class);
                    user.setPhone(localPhone);
                    if (Boolean.parseBoolean(user.getIsStaff())) //If IsStaff = true
                    {
                        if (user.getPassword().equals(localPassword)) {
                            Intent intent = new Intent(SignIn.this,Home.class);
                            Common.currUser = user;
                            startActivity(intent);
                            finish();

                        } else {
                            Toast.makeText(SignIn.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SignIn.this, "Only admin can log in! ", Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    mDialog.dismiss();
                    Toast.makeText(SignIn.this, "User does not exist", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
