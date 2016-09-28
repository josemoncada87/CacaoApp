package co.edu.icesi.innlab.cacaoapp.models;

/**
 * Created by Usuario on 25/09/2016.
 */

import com.google.firebase.database.IgnoreExtraProperties;

// [START blog_user_class]
@IgnoreExtraProperties
public class Usuario {

    public String username;
    public String email;
    public String grupo;
    public String rol;

    public Usuario() {
        // Default constructor required for calls to DataSnapshot.getValue(Usuario.class)
    }

    public Usuario(String username, String email) {
        this.username = username;
        this.email = email;
        this.grupo = "no_asignado";
        this.rol = "user";
    }
}
// [END blog_user_class]
