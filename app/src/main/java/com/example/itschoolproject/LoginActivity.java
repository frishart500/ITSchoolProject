package com.example.itschoolproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//import com.google.android.gms.auth.api.Auth;
//import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
//import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {

    TextView forgetPassword;
    String emailString;
    private Button btnEnter;
    private EditText email, password;
    private TextView textForgetPass, textRegistraition;
    private FirebaseAuth mAuth;//auth - авторизация
    private ProgressDialog mLoadingBar;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private int RC_SIGN_IN = 1;

    private SignInButton signInButton;
   // GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
        init();
      //  GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
      //          .requestIdToken(getString(R.string.default_web_client_id))
      //          .requestEmail()
      //          .build();
      //  mGoogleSignInClient = GoogleSignIn.getClient(this, gso);



        OnClickListenerBTNs();

        if(mAuth.getCurrentUser() != null)
        {
            startActivity(new Intent(getApplicationContext(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
        }

    }



    private void OnClickListenerBTNs(){

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth();
            }
        });


        textForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                emailString = email.getText().toString();

                startActivity(new Intent(getApplicationContext(), ForgetPasswordActivity.class));
            }
        });

        textRegistraition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegistraitionActivity.class));
            }
        });

       // signInButton.setOnClickListener(new View.OnClickListener() {
       //     @Override
       //     public void onClick(View v) {
       //         Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        //         startActivityForResult(signInIntent, RC_SIGN_IN);

          //  }
        //});
    }

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> task){

        try{
            GoogleSignInAccount acc = task.getResult(ApiException.class);
            Toast.makeText(this, "Вход успешен!", Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(acc);
        }catch (Exception ex) {
            Toast.makeText(this, "Вход провален!", Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(null);
        }
    }

    private void FirebaseGoogleAuth(GoogleSignInAccount acc){

        AuthCredential authCredential = GoogleAuthProvider.getCredential(acc.getIdToken(), null);
        mAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Успешено!", Toast.LENGTH_SHORT).show();
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }else{
                    Toast.makeText(getApplicationContext(), "Провалено!", Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
            }
        });

    }

    private void updateUI(FirebaseUser user){
        GoogleSignInAccount acc = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if(acc != null){
            String name = acc.getDisplayName();
            String email = acc.getEmail();
            String id = acc.getId();
            Uri photo = acc.getPhotoUrl();

            Toast.makeText(this, name  + email, Toast.LENGTH_SHORT).show();
        }
    }*/

    private void init(){
        forgetPassword = findViewById(R.id.forgetPassword);
        btnEnter = findViewById(R.id.enterBtn);
        textForgetPass = findViewById(R.id.forgetPassword);
        textRegistraition = findViewById(R.id.registration);
      //  signInButton = findViewById(R.id.signInBtn);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        mAuth = FirebaseAuth.getInstance();
        mLoadingBar = new ProgressDialog(LoginActivity.this);

    }

    private void auth(){
        String passwordS = password.getText().toString();
        String emailS = email.getText().toString();

        if(passwordS.isEmpty() | passwordS.length() < 6){
            showError(password, "Введите пароль, пароль должен состоять не менешь чем из 6 символов");
        }
        else if(emailS.isEmpty()){
            showError(email, "Введите email");
        }

        else{

            mLoadingBar.setTitle("Вход");
            mLoadingBar.setMessage("Пожалуйста подождите, пока мы проверяем ваши полномочия");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            mAuth.signInWithEmailAndPassword(emailS, passwordS).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        mLoadingBar.dismiss();
                        Toast.makeText(LoginActivity.this, "Вход успешен!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class)
                                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                    }else{
                        mLoadingBar.dismiss();//эта функция делает так чтобы mLoadingBar перестал работать
                        Toast.makeText(LoginActivity.this, "Вход провален!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            }
        }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Выход")
                .setMessage("Вы действительно хотите выйти из приложения?")
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setNegativeButton("Нет", null).show();
    }
    private void showError(EditText input, String s){
        input.setError(s);
        input.requestFocus();
    }
}