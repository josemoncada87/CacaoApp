package co.edu.icesi.innlab.cacaoapp.model;

/**
 * Created by Usuario on 25/09/2016.
 */

import com.google.firebase.database.IgnoreExtraProperties;

// [START blog_user_class]
@IgnoreExtraProperties
public class Reto {

    public String username;
    public String email;

    //

    public String id;
    public String nombre;
    public String estado;
    public int premio;
    public boolean cacao;


    public Reto() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Reto(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public Reto(String id, String nombre, int premio, boolean esCacao) {
        this.id = id;
        this.estado =  "nuevo";
        this.nombre = nombre;
        this.premio = premio;
        this.cacao = esCacao;
    }

}
// [END blog_user_class]