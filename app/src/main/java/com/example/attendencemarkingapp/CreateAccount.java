package com.example.attendencemarkingapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateAccount extends AppCompatActivity {

    EditText etNewId, etNewPassword, etConfirmPassword;
    Button btnRegister;
    MyDatabaseHelper mdbh;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        etNewId = findViewById(R.id.etNewId);
        etNewPassword = findViewById(R.id.etNewPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);

        mdbh = new MyDatabaseHelper(this);
        db = mdbh.getWritableDatabase();


        btnRegister.setOnClickListener(v -> {

            String id = etNewId.getText().toString().trim();
            String pass = etNewPassword.getText().toString().trim();
            String confirmPass = etConfirmPassword.getText().toString().trim();

            if (id.isEmpty() || pass.isEmpty() || confirmPass.isEmpty()) {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!pass.equals(confirmPass)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }


            ContentValues cv = new ContentValues();
            cv.put(MyDatabaseHelper.COL_ID, id);
            cv.put(MyDatabaseHelper.COL_PASSWORD, pass);

            long result = db.insert(MyDatabaseHelper.TABLE_USERS, null, cv);

            Intent i = new Intent(CreateAccount.this, MainActivity.class);
            startActivity(i);
            finish();

            if (result != -1) {
                Toast.makeText(this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                finish(); // go back to login
            } else {
                Toast.makeText(this, "User already exists", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
