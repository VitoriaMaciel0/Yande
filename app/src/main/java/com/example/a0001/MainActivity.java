package com.example.a0001;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private RecyclerView.Adapter adapterPopular;
private RecyclerView recyclerViewPupolar;
    private FirebaseAuth mAuth;
    private TextView textViewNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();
        bottomNavigation();
    }

    private void bottomNavigation() {
        LinearLayout homeBtn=findViewById(R.id.homeBtn);
        LinearLayout cartBtn=findViewById(R.id.cartBtn);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MainActivity.class));
            }
        });

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CartActivity2.class));
            }
        });
    }


    @SuppressLint("WrongViewCast")
    private void initRecyclerView() {
        ArrayList<PopularDomain> itens=new ArrayList<>();
        itens.add(new PopularDomain("Produtos naturais","Explore uma coleção cuidadosamente selecionada de artigos derivados de ingredientes naturais da Amazônia. \n" +
                "Descubra uma seleção de produtos feitos a partir de ingredientes naturais da Amazônia. Desde óleos essenciais e extratos de plantas medicinais até produtos para cuidados com a pele e cabelo, cada item é formulado para proporcionar benefícios naturais, respeitando os recursos da floresta.","art2",15,4,20));
        itens.add(new PopularDomain("Beleza","Mime-se com uma variedade de produtos de beleza que celebram a diversidade da fauna e flora amazônicas. \n" +
                "Explore uma gama de produtos de beleza inspirados na biodiversidade da Amazônia. Maquiagens, perfumes, sabonetes artesanais e outros itens de cuidados pessoais, todos feitos com ingredientes naturais e técnicas tradicionais para realçar a beleza de maneira sustentável.","beleza",1,4.5,10));
        itens.add(new PopularDomain("Gastronomia Regional","Explore os segredos culinários da Amazônia com a categoria \"Gastronomia Amazônica\".\n" +
                "Delicie-se com uma experiência gastronômica única, experimentando os sabores autênticos da Amazônia. Produtos como chocolates finos, condimentos exóticos, cafés especiais e snacks feitos a partir de ingredientes locais frescos e sustentáveis.","comidas",10,4.3,50));
        itens.add(new PopularDomain("Saúde","Cuide de sua saúde e bem-estar com uma seleção dedicada a produtos holísticos inspirados no conhecimento tradicional das comunidades locais. \n" +
                "Encontre produtos dedicados à saúde e ao bem-estar, como chás medicinais, suplementos naturais e itens de cuidados holísticos. Cada produto é desenvolvido com o conhecimento tradicional das comunidades locais, proporcionando opções saudáveis e conscientes.","saude",18,4.8,100));



        recyclerViewPupolar=findViewById(R.id.view);
        recyclerViewPupolar.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

        adapterPopular=new PupolarAdapter(itens);
        recyclerViewPupolar.setAdapter(adapterPopular);
    }
}