package co.edu.icesi.innlab.cacaoapp.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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

import co.edu.icesi.innlab.cacaoapp.R;
import co.edu.icesi.innlab.cacaoapp.configurador.CreadorDeEquiposActivity;
import co.edu.icesi.innlab.cacaoapp.configurador.CreadorDeRetosActivity;
import co.edu.icesi.innlab.cacaoapp.configurador.EditorGrupos;
import co.edu.icesi.innlab.cacaoapp.fragments.EstadisticasFragment;
import co.edu.icesi.innlab.cacaoapp.fragments.RetosFragment;
import co.edu.icesi.innlab.cacaoapp.fragments.PerfilFragment;
import co.edu.icesi.innlab.cacaoapp.models.Usuario;

public class ActividadPrincipal extends BaseActivity implements PerfilFragment.OnFragmentInteractionListener{
    private static final String TAG = "ACTIVIDAD_PRINCIPAL";

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
   // private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private FragmentPagerAdapter mPagerAdapter;
    // [START declare_database_ref]
    private DatabaseReference mDatabase;
    private boolean usuarioAdministrador;
    // [END declare_database_ref]


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_principal);
        usuarioAdministrador =  false;
        // [START initialize_database_ref]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END initialize_database_ref]

        //////////////////////////////////////////////////////////////
        verificarPermisosAdministrador();
        //////////////////////////////////////////////////////////////

        // Create the adapter that will return a fragment for each section
        mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            private final Fragment[] mFragments = new Fragment[]{
                    PerfilFragment.newInstance("", ""),
                    new EstadisticasFragment(),
                    new RetosFragment(),
            };
            private final String[] mFragmentNames = new String[]{
                    getString(R.string.titulo_perfil),
                    getString(R.string.titulo_estadisticas),
                    getString(R.string.titulo_retos)
            };

            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }

            @Override
            public int getCount() {
                return mFragments.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mFragmentNames[position];
            }
        };

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);

        // mViewPager.setAdapter(mSectionsPagerAdapter); //
        mViewPager.setAdapter(mPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        //////////////////////////////////////////////////////////////

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });*/
    }

    private void verificarPermisosAdministrador() {
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
                        Log.d(TAG, "****** el usuario es: " + user.username + " rol: " + user.rol);
                        usuarioAdministrador =  user.rol.equals("admin");
                    }
                    @Override
                    public void onCancelled(DatabaseError error) {
                        Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_actividad_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, SignInActivity.class));
            finish();
            return true;
        }

        if (id == R.id.action_crear_reto) {
            if(usuarioAdministrador) {
                startActivity(new Intent(this, CreadorDeRetosActivity.class));
            }else{
                Snackbar.make(mViewPager, "Requiere permisos de administrador", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
            return true;
        }

        if (id == R.id.action_crear_equipo) {
            if(usuarioAdministrador) {
                startActivity(new Intent(this, CreadorDeEquiposActivity.class));
            }else{
                Snackbar.make(mViewPager, "Requiere permisos de administrador", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
            return true;
        }

        if (id == R.id.action_editar_equipo) {
            if(usuarioAdministrador) {
                startActivity(new Intent(this, EditorGrupos.class));
            }else{
                Snackbar.make(mViewPager, "Requiere permisos de administrador", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
// TODO cambiar para perfil
    @Override
    public void onFragmentInteraction(Uri uri) {
        System.out.println("testing...");
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_actividad_principal, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }
}
