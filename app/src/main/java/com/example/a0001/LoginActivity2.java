package com.example.a0001;

import static android.content.ContentValues.TAG;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity2 extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        mAuth = FirebaseAuth.getInstance();

        TextView signupTextView = findViewById(R.id.signupTextView);
        Button loginButton = findViewById(R.id.loginButton);
        EditText usernameEditText = findViewById(R.id.username);
        EditText passwordEditText = findViewById(R.id.password);

        signupTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup(v);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    // Exibir mensagem de erro informando que os campos são obrigatórios
                    Toast.makeText(LoginActivity2.this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Chame a função para autenticar o usuário
                signIn(username, password);
            }
        });
    }

    private void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        try {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Intent intent = new Intent(LoginActivity2.this, MainActivity.class);
                                startActivity(intent);
                                finish(); // Fecha a LoginActivity2 para impedir que o usuário volte para ela usando o botão "Voltar"
                            } else {
                                // If sign in fails, display a message to the user.
                                String errorMessage = "Autenticação falhou.";
                                if (task.getException() != null) {
                                    throw task.getException();  // Lança a exceção para ser capturada abaixo
                                }

                                Log.e(TAG, errorMessage); // Log detalhado do erro
                                Toast.makeText(LoginActivity2.this, errorMessage, Toast.LENGTH_SHORT).show();
                            }
                        } catch (FirebaseAuthInvalidCredentialsException e) {
                            Log.e(TAG, "Credenciais inválidas: " + e.getLocalizedMessage());
                            // Adicione um Toast ou mensagem para o usuário, se necessário
                        } catch (FirebaseAuthInvalidUserException e) {
                            Log.e(TAG, "Usuário inválido: " + e.getLocalizedMessage());
                            // Adicione um Toast ou mensagem para o usuário, se necessário
                        } catch (Exception e) {
                            Log.e(TAG, "Erro durante a autenticação: " + e.getLocalizedMessage());
                            // Adicione um Toast ou mensagem para o usuário, se necessário
                        }
                    }
                });
    }

    public void signup(View view) {
        Intent intent = new Intent(this, RegistrationActivity2.class);
        startActivity(intent);
    }
}