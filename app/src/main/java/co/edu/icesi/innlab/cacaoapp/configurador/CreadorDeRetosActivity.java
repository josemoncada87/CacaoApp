package co.edu.icesi.innlab.cacaoapp.configurador;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import co.edu.icesi.innlab.cacaoapp.R;
import co.edu.icesi.innlab.cacaoapp.activities.BaseActivity;
import co.edu.icesi.innlab.cacaoapp.models.Reto;

public class CreadorDeRetosActivity extends BaseActivity {

    // [START declare_database_ref]
    private DatabaseReference mDatabase;
    // [END declare_database_ref]


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creador_de_retos);

        // [START initialize_database_ref]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END initialize_database_ref]

        writeNewPost("reto", "Nuevo Reto", "lanzador", "debes crear un lanzador con papel", 100, 100);
    }

    // [START write_fan_out]
    private void writeNewPost(String id, String nombre, String descripcionCorta, String descripcionCompleta, int premioCacao, int premioPuntos) {
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        String key = mDatabase.child("retos").push().getKey();
        Reto reto = new Reto(id, nombre, descripcionCorta, descripcionCompleta ,premioCacao, premioPuntos);
        Map<String, Object> retoValues = reto.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/retos/" + key, retoValues);
        //  childUpdates.put("/user-posts/" + userId + "/" + key, postValues);
        mDatabase.updateChildren(childUpdates);
    }
    // [END write_fan_out]
}
