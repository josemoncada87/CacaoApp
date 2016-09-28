package co.edu.icesi.innlab.cacaoapp.fragments;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;


public class EstadisticasFragment extends FragmentBaseListaGrupo {
    public EstadisticasFragment() {}
    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        return databaseReference.child("grupos");
    }
}
