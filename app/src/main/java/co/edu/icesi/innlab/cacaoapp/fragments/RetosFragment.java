package co.edu.icesi.innlab.cacaoapp.fragments;

/**
 * Created by 1130613425 on 26/09/2016.
 */

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class RetosFragment extends FragmentBaseListaReto {
    public RetosFragment() {}
    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        return databaseReference.child("retos");
    }
}