package co.edu.icesi.innlab.cacaoapp.configurador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import co.edu.icesi.innlab.cacaoapp.R;
import co.edu.icesi.innlab.cacaoapp.models.Grupo;

public class EditorGrupos extends AppCompatActivity {

    public static final String NOMBRE_GRUPO = "nombre_grupo";
    private DatabaseReference mDatabase;
    private String nombre;
    private Grupo grupoActual;


    // Views
    Spinner selectorGrupo; // TODO: Deseado a futuro.

    private EditText nombreGrupo;
    private EditText cacaosGrupo;
    private EditText puntosGrupo;
    private Button btnActualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_grupos);

        Intent lanzador = getIntent();
        nombre = lanzador.getStringExtra(NOMBRE_GRUPO);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        nombreGrupo     =  (EditText) findViewById(R.id.edt_editor_grupos_nombre_equipos);
        cacaosGrupo     =  (EditText) findViewById(R.id.edt_editor_grupos_ncacaos);
        puntosGrupo     =  (EditText) findViewById(R.id.edt_editor_grupos_npuntos);
        btnActualizar   =  (Button) findViewById(R.id.btn_editor_grupos_editar);

        final Spinner selectorGrupo = (Spinner) findViewById(R.id.spinner_editor_grupos_nombre);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.teams_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectorGrupo.setAdapter(adapter);

        if(nombre == null) {
            nombre = selectorGrupo.getSelectedItem().toString();
        }

        cargarInformacionGrupo(nombre);

        selectorGrupo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                nombre = parent.getItemAtPosition(position).toString();
                cargarInformacionGrupo(nombre);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grupoActual = new Grupo(nombre);
                grupoActual.cantidadCacaos = Integer.parseInt(cacaosGrupo.getText().toString());
                grupoActual.cantidadPuntos = Integer.parseInt(puntosGrupo.getText().toString());
                DatabaseReference bdr = mDatabase.child("grupos").child(nombre);
                bdr.setValue(grupoActual);
                finish();
            }
        });
    }

    public void cargarInformacionGrupo(String nGrupo){
        DatabaseReference bdr = mDatabase.child("grupos").child(nGrupo);
        bdr.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                grupoActual = dataSnapshot.getValue(Grupo.class);
                if(grupoActual!=null) {
                    nombreGrupo.setText(grupoActual.nombre);
                    cacaosGrupo.setText(String.valueOf(grupoActual.cantidadCacaos));
                    puntosGrupo.setText(String.valueOf(grupoActual.cantidadPuntos));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}