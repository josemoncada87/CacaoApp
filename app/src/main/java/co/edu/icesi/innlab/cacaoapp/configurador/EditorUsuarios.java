package co.edu.icesi.innlab.cacaoapp.configurador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import co.edu.icesi.innlab.cacaoapp.R;
import co.edu.icesi.innlab.cacaoapp.models.Grupo;
import co.edu.icesi.innlab.cacaoapp.models.Usuario;

public class EditorUsuarios extends AppCompatActivity {

    public static final String ID_USUARIO= "uid_usuario";

    // Views
    private EditText nombreUsuario;
    private EditText emailUsuario;
    private EditText grupoUsuario;
    private EditText rolUsuario;
    private Button btnActualizar;

    // From Model
    private String username;
    private String email;
    private String grupo;
    private String rol;

    // Database
    private DatabaseReference mDatabase;

    // User
    private Usuario usuarioActual;
    private String uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_usuarios);
        nombreUsuario   = (EditText) findViewById(R.id.edt_editor_usuario_usuario);
        emailUsuario    = (EditText) findViewById(R.id.edt_editor_usuario_email);
        grupoUsuario    = (EditText) findViewById(R.id.edt_editor_usuario_grupo);
        rolUsuario      = (EditText) findViewById(R.id.edt_editor_usuario_rol);

        Intent lanzador = getIntent();
        uid = lanzador.getStringExtra(ID_USUARIO);

        if(uid == null) {
         //   uid = selectorGrupo.getSelectedItem().toString();
        }

    }

    public void cargarInformacionUsuario(String nGrupo){
        DatabaseReference bdr = mDatabase.child("grupos").child(nGrupo);
        bdr.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                usuarioActual = dataSnapshot.getValue(Usuario.class);
                if(usuarioActual!=null) {
                    nombreUsuario.setText(usuarioActual.username);
                    emailUsuario.setText(usuarioActual.email);
                    grupoUsuario.setText(usuarioActual.grupo);
                    rolUsuario.setText(usuarioActual.rol);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
