package com.example.organizzeapp2.config;

import com.google.firebase.auth.FirebaseAuth;

public class ConfiguracaoFirebase {
    private static FirebaseAuth autencicacao;

    public static FirebaseAuth getFirebaseAutenticacao() {
        autencicacao = FirebaseAuth.getInstance();
        if (autencicacao == null) {
            autencicacao = FirebaseAuth.getInstance();
        }
        return autencicacao;

    }
}
