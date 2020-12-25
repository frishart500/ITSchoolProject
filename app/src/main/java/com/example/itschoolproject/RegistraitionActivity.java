package com.example.itschoolproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class RegistraitionActivity extends AppCompatActivity {
    private String USER_KEY = "User";

    private Button btnRegistr;
    private ImageView dataImg;
    private EditText editData, email, password, name, phone;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private FirebaseAuth mAuth;//auth - авторизация
    private ProgressDialog mLoadingBar;
    private ImageView back;
    private DatabaseReference mDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registraition);
        init();
        OnClickListenerBTNs();


        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = day + "." + month + "." + year;
                String m = String.valueOf(month);
                String d = String.valueOf(day);
                System.out.println("MONTH " + month);
                if (String.valueOf(day).length() == 1)
                {
                    d = ("0" + day);
                }

                if (String.valueOf(month).length() == 1)
                {
                    m = ("0" + month);
                }

                date = d + "." + m + "." + year;


                editData.setText(date);
            }
        };

        dataImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(RegistraitionActivity.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth, mDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

    }

    private void OnClickListenerBTNs(){
        btnRegistr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                funk(email.getText().toString().trim(), password.getText().toString().trim());
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }

    private void init(){
        btnRegistr = findViewById(R.id.registrBtn);
        dataImg = findViewById(R.id.dataImg);

        back = findViewById(R.id.back);

        editData = findViewById(R.id.editData);
        name = findViewById(R.id.nameEditText);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        mAuth = FirebaseAuth.getInstance();
        mDataBase = FirebaseDatabase.getInstance().getReference(USER_KEY);
        mLoadingBar = new ProgressDialog(RegistraitionActivity.this);
    }

    private void funk(final String emailForString, String passwordForString){

        final String dataS = editData.getText().toString();
        final String nameS = name.getText().toString();
        final String phoneS = phone.getText().toString();
        String passwordS = password.getText().toString();
        final String emailS = email.getText().toString();

        if(nameS.isEmpty()){
            showError(name, "Введите имя");
        }else if(dataS.isEmpty()){
            showError(editData, "Введите или выберите дату рождения");
        }else if(phoneS.isEmpty()){
            showError(phone, "Введите свой номер телефона");
        }else if(passwordS.isEmpty() | passwordS.length() < 6){
            showError(password, "Введите пароль, пароль должен состоять не менешь чем из 6 символов");
        }
        else if(emailS.isEmpty()){
            showError(email, "Введите email");
        }

        else{
            mLoadingBar.setTitle("Регистрация");
            mLoadingBar.setMessage("Пожалуйста подождите, пока мы проверяем ваши полномочия");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            mAuth.createUserWithEmailAndPassword(emailForString, passwordForString).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){//если пользователь смог зарегаться то
                        Toast.makeText(RegistraitionActivity.this, "Регистрация успешна!", Toast.LENGTH_SHORT).show();

                        User user = new User(dataS, emailForString, nameS, phoneS);
                        mDataBase.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user);

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(RegistraitionActivity.this, "Регистрация провалена", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            //создаем пользователя с email и паролем
        }

    }
    private void showError(EditText input, String s){
        input.setError(s);
        input.requestFocus();
    }

}