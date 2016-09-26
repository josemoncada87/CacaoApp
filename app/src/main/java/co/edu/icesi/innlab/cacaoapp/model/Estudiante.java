package co.edu.icesi.innlab.cacaoapp.model;

/**
 * Created by Usuario on 25/09/2016.
 */

import com.google.firebase.database.IgnoreExtraProperties;

// [START blog_user_class]
@IgnoreExtraProperties
public class Estudiante {

    public String username;
    public String email;

    public Estudiante() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Estudiante(String username, String email) {
        this.username = username;
        this.email = email;
    }

}
// [END blog_user_class]
