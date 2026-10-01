package com.example.attendencemarkingapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etId, etPassword;
    Button btnLogin,btnCreateAcc;
    MyDatabaseHelper mdbh;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etId = findViewById(R.id.etId);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnCreateAcc = findViewById(R.id.btnCreateAccount);
        mdbh = new MyDatabaseHelper(this);
        db = mdbh.getWritableDatabase();

        btnCreateAcc.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, CreateAccount.class);
            startActivity(i);
            finish();
        });
        btnLogin.setOnClickListener(v -> {
            String id = etId.getText().toString();
            String password = etPassword.getText().toString();

            id = "admin";
            password = "1234";

            if (id.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter all details", Toast.LENGTH_SHORT).show();
            } else {

                String query = "SELECT * FROM " + MyDatabaseHelper.TABLE_USERS + " WHERE "
                        + MyDatabaseHelper.COL_ID + " = ? AND "
                        + MyDatabaseHelper.COL_PASSWORD + " = ?";

                Cursor cursor = db.rawQuery(query, new String[]{id, password});

                if (cursor.moveToFirst()) {
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(MainActivity.this, Dashboard.class);
                    startActivity(i);
                    finish();

                } else {
                    Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
                }

                cursor.close();



            }
        });
        btnLogin.performClick();
    }
}
