package com.example.newbanco;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class FormularioActivity extends AppCompatActivity {

    private EditText etNome;
   // private EditText etID;
    private TextView tvID;
    private Spinner spCategorias;
    private Button btSalvar;
    private String acao;
    private Filme filme;
    private String idioma;
    private String legenda;
    private RadioButton rbIngles;
    private RadioButton rbPortugues;
    private CheckBox cbIngles;
    private CheckBox cbPortugues;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
       // etID = findViewById(R.id.editID);
        etNome = findViewById(R.id.editNome);
        spCategorias = findViewById(R.id.spinnerCategoria);
        rbIngles = (RadioButton) findViewById(R.id.rbIngles);
        rbPortugues = (RadioButton) findViewById(R.id.rbPortugues);
        cbIngles = (CheckBox) findViewById(R.id.cbIngles);
        cbPortugues = (CheckBox) findViewById(R.id.cbPortugues);
        btSalvar = findViewById(R.id.buttonSalvar);
        acao = getIntent().getStringExtra("acao");

        if(acao.equals("editar"))
        {
            carregarFormulario();
        }
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });
    }

    public void salvar()
    {
        String nome = etNome.getText().toString();
        if(rbIngles.isChecked()){
            idioma = rbIngles.getText().toString();
        } else {
            idioma = rbPortugues.getText().toString();
        }

        if(cbIngles.isChecked() || cbPortugues.isChecked()){
            legenda = cbIngles.getText().toString()+"||"+cbPortugues.getText().toString();
        } else
            if(cbIngles.isChecked()){
                legenda = cbIngles.getText().toString();
            } else {
                legenda = cbPortugues.getText().toString();
            }

        if(nome.isEmpty()||spCategorias.getSelectedItemPosition()==0)
        {
            Toast.makeText(this, "Ambos os campos devem ser preenchidos!", Toast.LENGTH_LONG).show();
        }
        else
        {
            if(acao.equals("inserir")){
               filme = new Filme();
        }
            filme.setNome(nome);
            filme.setCategoria(spCategorias.getSelectedItem().toString());
            filme.setIdioma(idioma);
            filme.setLegenda(legenda);

            if(acao.equals("inserir")){
                FilmeDAO.inserir(this,filme);
                etNome.setText("");
                spCategorias.setSelection(0,true);

                if(rbIngles.isChecked()){
                    idioma = rbIngles.getText().toString();
                } else {
                    idioma = rbPortugues.getText().toString();
                }

                if(cbIngles.isChecked() || cbPortugues.isChecked()){
                    legenda = cbIngles.getText().toString()+"||"+cbPortugues.getText().toString();
                } else
                    if(cbIngles.isChecked()){
                        legenda = cbIngles.getText().toString();
                    } else {
                        legenda = cbPortugues.getText().toString();
                    }
           }
            else
            {
                FilmeDAO.editar(this,filme);

            }
            finish();
        }
    }

    public void carregarFormulario()
    {
        int id = getIntent().getIntExtra("idFilme",0);
        tvID = findViewById(R.id.tvIDFilme);
        filme = FilmeDAO.getFilmebyId(this,id);
        tvID.setText(String.valueOf(id));
        etNome.setText(filme.getNome());

        if(rbIngles.isChecked()){
            //rbIngles.setChecked();
        } else {
            //rbPortugues.setChecked(filme.getIdioma());
        }

        if(cbIngles.isChecked()){
            //cbIngles.setChecked(filme.getLegenda()); -- eu juro que tentei sor ;-;
        } else {
            //cbPortugues.setChecked(filme.getLegenda());
        }

        String[] categorias = getResources().getStringArray(R.array.strCategorias);
        for(int i =0;i<categorias.length;i++){
                if(filme.getCategoria().equals(categorias[i])){
                    spCategorias.setSelection(i);
                    break;
                }
        }



    }
}