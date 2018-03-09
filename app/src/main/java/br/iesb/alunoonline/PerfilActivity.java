package br.iesb.alunoonline;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.UUID;

public class PerfilActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;

    public static class interesse{
        public String id;
        public String tag;
    }

    public static class Aluno{
    public String nome;
    public int matricula;
    public String curso;
    public String campus;
    public ArrayList<interesse> interesses= new ArrayList<>();
    public Boolean ativo;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        Button inserir= findViewById(R.id.inserir);
        inserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inserir();
            }
        });
    }
    public void inserir(){
        FirebaseDatabase database= FirebaseDatabase.getInstance();
        Aluno aluno= new Aluno();
        aluno.nome="Maria";
        aluno.matricula=11111;
        aluno.curso="ADS";
        aluno.campus="SUL";
        aluno.ativo=true; //(true or false)


        String ints="portugues,matematica,ingles";
        for(String s : ints.split(",")){
            interesse it= new interesse();
            it.id= UUID.randomUUID().toString();
            it.tag=s;
            aluno.interesses.add(it);
        }
        DatabaseReference novoAluno=database.getReference("iesb/alunos/" + UUID.randomUUID().toString());
        novoAluno.setValue(aluno);

    }
}