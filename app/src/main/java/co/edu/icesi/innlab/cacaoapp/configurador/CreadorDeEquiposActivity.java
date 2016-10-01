package co.edu.icesi.innlab.cacaoapp.configurador;

import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import co.edu.icesi.innlab.cacaoapp.R;
import co.edu.icesi.innlab.cacaoapp.activities.BaseActivity;
import co.edu.icesi.innlab.cacaoapp.models.Grupo;


public class CreadorDeEquiposActivity extends BaseActivity {

    private DatabaseReference mDatabase;

    // Views
    private EditText nombre;
    private EditText cacaos;
    private EditText puntos;
    private Button btnCrear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion_equipos);

        nombre = (EditText) findViewById(R.id.edt_crea_equipo_nombre_equipos);
        cacaos = (EditText) findViewById(R.id.edt_crea_equipo_s_ncacaos);
        puntos = (EditText) findViewById(R.id.edt_crea_equipo_npuntos);
        btnCrear = (Button) findViewById(R.id.btn_crea_equipo_crear);
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generarNuevoGrupo(nombre.getText().toString(), Integer.parseInt(cacaos.getText().toString()), Integer.parseInt(puntos.getText().toString()));
                finish();
            }
        });

        mDatabase = FirebaseDatabase.getInstance().getReference();
        String[] nombresGrupos = {"Coate", "Mazate", "Ocelote", "Coquite", "Chapolín", "Huitzilín", "Michín","Tlacuache"};
        /// TODO: establecer una forma estandar de crear grupos , reserva para backup.
        // for(int i = 0 ; i < nombresGrupos.length;i++) {
        //     generarNuevoGrupo(nombresGrupos[i]);
        // }
    }

    // [START basic_write]
    private void generarNuevoGrupo(String nombre) {
        Grupo grupo = new Grupo(nombre);
        DatabaseReference bdr = mDatabase.child("grupos").child(nombre);
        bdr.setValue(grupo);
    }
    // [END basic_write]

    // [START basic_write]
    private void generarNuevoGrupo(String nombre, int ncacaos, int npuntos) {
        Grupo grupo = new Grupo(nombre);
        grupo.cantidadPuntos=npuntos;
        grupo.cantidadCacaos=ncacaos;
        DatabaseReference bdr = mDatabase.child("grupos").child(nombre);
        bdr.setValue(grupo);
    }
    // [END basic_write]



}
