package com.example.organizzeapp2.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.organizzeapp2.R;
import com.example.organizzeapp2.config.ConfiguracaoFirebase;
import com.example.organizzeapp2.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CadastroActivity extends AppCompatActivity {
    private EditText campoNome, campoEmail, campoSenha;
    private Button botaoCadastrar;
    private FirebaseAuth autenticacao;

    private Usuario usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        campoNome = findViewById(R.id.editTextNome);
        campoEmail = findViewById(R.id.editTextEmail);
        campoSenha = findViewById(R.id.editTextSenha);
        botaoCadastrar = findViewById(R.id.buttonCadastro);

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String textoNome = campoNome.getText().toString();
                String textoEmail = campoEmail.getText().toString();
                String textoSenha = campoSenha.getText().toString();

                //Validar se os campos foram preenchidos
                if (!textoNome.isEmpty()) {
                    if (!textoEmail.isEmpty()) {
                        if (!textoSenha.isEmpty()) {
                            usuario = new Usuario();
                            usuario.setNome(textoNome);
                            usuario.setEmail(textoEmail);
                            usuario.setSenha(textoSenha);
                            cadastrarUsuario();


                        } else {
                            Toast.makeText(CadastroActivity.this,
                                    "Preencha a senha",
                                    Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(CadastroActivity.this,
                                "Preencha o email",
                                Toast.LENGTH_LONG).show();

                    }

                } else {
                    Toast.makeText(CadastroActivity.this,
                            "Preencha o nome",
                            Toast.LENGTH_LONG).show();

                }
            }

        });


    }

    public void cadastrarUsuario() {
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(this, task -> {
            if(task.isSuccessful()){
                Toast.makeText(CadastroActivity.this,
                        "Sucesso ao cadastrar usuário!",
                        Toast.LENGTH_LONG).show();

        } else{
                Toast.makeText(CadastroActivity.this,
                        "Erro ao cadastrar usuário!",
                        Toast.LENGTH_LONG).show();
            }
        });

    }

}


