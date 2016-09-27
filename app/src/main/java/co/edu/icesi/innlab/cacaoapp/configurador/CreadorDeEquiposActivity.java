package co.edu.icesi.innlab.cacaoapp.configurador;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import co.edu.icesi.innlab.cacaoapp.R;
import co.edu.icesi.innlab.cacaoapp.activities.BaseActivity;
import co.edu.icesi.innlab.cacaoapp.models.Grupo;
import co.edu.icesi.innlab.cacaoapp.models.Usuario;

public class CreadorDeEquiposActivity extends BaseActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion_equipos);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        String[] nombresGrupos = {"Coate", "Mazate", "Ocelote", "Coquite", "Chapolín", "Huitzilín", "Michín","Tlacuache"};
        for(int i = 0 ; i < nombresGrupos.length;i++) {
            generarNuevoGrupo(nombresGrupos[i]);
        }
    }

    // [START basic_write]
    private void generarNuevoGrupo(String nombre) {
        Grupo grupo = new Grupo(nombre);
        DatabaseReference bdr = mDatabase.child("grupos").child(nombre);
        bdr.setValue(grupo);
    }
    // [END basic_write]

}
