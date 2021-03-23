package com.example.firebasedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    EditText name,email,phone;
    Button sendBtn;
    int maxid=0;
    Member member;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=findViewById(R.id.name_et);
        email=findViewById(R.id.email_et);
        phone=findViewById(R.id.phone_et);
        sendBtn=findViewById(R.id.send_btn);

        member=new Member();
        databaseReference=firebaseDatabase.getInstance()
                .getReference().child("User");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists())
                {
                    maxid=(int)snapshot.getChildrenCount();
                }
                else
                {

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });
//haha
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                member.setName(name.getText().toString());
                member.setEmail(email.getText().toString());
                member.setPhone(phone.getText().toString());

                databaseReference.push().setValue(member);

            }
        });
    }
}