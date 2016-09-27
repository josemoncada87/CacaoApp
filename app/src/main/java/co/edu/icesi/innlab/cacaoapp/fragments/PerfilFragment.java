package co.edu.icesi.innlab.cacaoapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import co.edu.icesi.innlab.cacaoapp.R;
import co.edu.icesi.innlab.cacaoapp.models.Grupo;
import co.edu.icesi.innlab.cacaoapp.models.Usuario;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PerfilFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PerfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PerfilFragment extends Fragment {

    // Views
    private TextView nombreUsuario;
    private TextView nombreGrupo;
    private TextView numeroDeCacaos;
    private TextView numeroDePuntos;
    private TextView rankingEquipo;

     private OnFragmentInteractionListener mListener;

    private static final String TAG = "FRAG_PERFIL:";


    public PerfilFragment() {
        // Constructor por defecto requerido
    }

    public static PerfilFragment newInstance(String param1, String param2) {
        PerfilFragment fragment = new PerfilFragment();
        Bundle args = new Bundle();
       // args.putString(ARG_PARAM1, param1);
       // args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    String grupoTemp = "";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View retorno = inflater.inflate(R.layout.fragment_perfil, container, false);

        // Asignar views del fragment
        nombreUsuario  = (TextView) retorno.findViewById(R.id.tv_frag_perfil_nombre);
        nombreGrupo    = (TextView) retorno.findViewById(R.id.tv_frag_perfil_nombre_equipo);
        numeroDeCacaos = (TextView) retorno.findViewById(R.id.tv_frag_perfil_tx_cant_cacaos);
        numeroDePuntos = (TextView) retorno.findViewById(R.id.tv_frag_perfil_tx_cant_puntos);
        rankingEquipo  = (TextView) retorno.findViewById(R.id.tv_frag_perfil_tx_ranking_equipo);

        // Se busca el usuario actual autenticado
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null) {
            String uid = user.getUid();
            DatabaseReference referenciaAlusuario = FirebaseDatabase.getInstance().getReference("usuarios").child(uid);
            if(referenciaAlusuario!=null) {
                // Lectura desde la base de datos
                referenciaAlusuario.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // actualiza el valor de usuario cuando haya un cambio en la bd
                        Usuario user = dataSnapshot.getValue(Usuario.class);
                        nombreUsuario.setText(user.username);
                        nombreGrupo.setText(user.grupo);
                        grupoTemp = user.grupo;
                        Log.d(TAG, "****** el usuario es: " + user.username + " grupo: " + grupoTemp);
                        actualizarDatosGrupo(user.grupo);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });
            }
        }
        return retorno;
    }

    private void actualizarDatosGrupo(String grupo) {
        DatabaseReference refGrupoUsuario = FirebaseDatabase.getInstance().getReference("grupos").child(grupoTemp);
        System.out.println("REFERENCIA: "+ refGrupoUsuario);
        if(refGrupoUsuario!=null) {
            // Lectura desde la base de datos
            refGrupoUsuario.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // actualizar los cacaos y puntos de grupos
                    Grupo gr = dataSnapshot.getValue(Grupo.class)!=null?dataSnapshot.getValue(Grupo.class):new Grupo("no_asignado");

                    /*
                        System.out.println("-------------------> CAMBIO EN LOS GRUPOS <--------------------");
                        System.out.println(gr.nombre);
                        System.out.println(gr.cantidadCacaos);
                        System.out.println(gr.cantidadPuntos);
                        System.out.println("---------------------------------------------------------------");
                    */

                    nombreGrupo.setText(gr.nombre);
                    numeroDeCacaos.setText(String.valueOf(gr.cantidadCacaos));
                    numeroDePuntos.setText(String.valueOf(gr.cantidadPuntos));

                }
                @Override
                public void onCancelled(DatabaseError error) {
                    Log.w(TAG, "Failed to read value.", error.toException());
                }
            });
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}


