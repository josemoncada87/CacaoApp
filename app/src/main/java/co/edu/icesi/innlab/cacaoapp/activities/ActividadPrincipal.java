package co.edu.icesi.innlab.cacaoapp.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import co.edu.icesi.innlab.cacaoapp.R;
import co.edu.icesi.innlab.cacaoapp.configurador.CreadorDeRetosActivity;
import co.edu.icesi.innlab.cacaoapp.fragments.EstadisticasFragment;
import co.edu.icesi.innlab.cacaoapp.fragments.MyRetoFragment;
import co.edu.icesi.innlab.cacaoapp.fragments.PerfilFragment;
import co.edu.icesi.innlab.cacaoapp.models.Reto;

public class ActividadPrincipal extends BaseActivity implements PerfilFragment.OnFragmentInteractionListener, EstadisticasFragment.OnFragmentInteractionListener{

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



////////////////////

    private FragmentPagerAdapter mPagerAdapter;
    // private ViewPager mViewPager;

    // [START declare_database_ref]
    private DatabaseReference mDatabase;
    // [END declare_database_ref]


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_principal);

        // [START initialize_database_ref]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END initialize_database_ref]

        //////////////////////////////////////////////////////////////

        // Create the adapter that will return a fragment for each section
        mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            private final Fragment[] mFragments = new Fragment[]{
                    PerfilFragment.newInstance("", ""),
                    new EstadisticasFragment(),
                    new MyRetoFragment(),
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

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        // mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager()); //
        System.out.println("info: " + mPagerAdapter.getItem(0).getTag());
        //getFragmentManager().findFragmentById(R.id.your_fragment).getView().findViewById(R.id.your_view);
        //nombreUsuario = (TextView) .getView().findViewById(R.id.tv_frag_perfil_nombre);


        // System.out.println(nombreEquipo+" "+nombreEquipo +" "+ numeroDeCacaos +" "+ rankingEquipo);

        /*// obtener información del usuario
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();
            Task<GetTokenResult> resultado = user.getToken(true);
            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            String uid = user.getUid();
            System.out.println("información usuario: " + name +" "+ email +" "+ photoUrl +" "+ uid +" " + resultado);
        }*/

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             /*   Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                // RetoContent.addItem(RetoContent.createRetoItem("nuevo reto", 3000, true));
                // retoTabFragment.getmRetoViewAdapter().notifyDataSetChanged();

                // Write a message to the database
               /* FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("retos/reto/"+RetoContent.ITEMS.size());
                myRef.setValue("nuevo reto");*/
                writeNewPost("","","","");
            }
        });

    }



    // [START write_fan_out]
    private void writeNewPost(String userId, String username, String title, String body) {
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        String key = mDatabase.child("retos").push().getKey();
        Reto reto = new Reto("id", "nombre", "descripcion", "ejemplo completo" ,0, 50);
        Map<String, Object> retoValues = reto.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/retos/" + key, retoValues);
      //  childUpdates.put("/user-posts/" + userId + "/" + key, postValues);
        mDatabase.updateChildren(childUpdates);
    }
    // [END write_fan_out]


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

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settings) {
            return true;
        }*/
        if (id == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, SignInActivity.class));
            finish();
            return true;
        }

        if (id == R.id.action_crear_reto) {
            //FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, CreadorDeRetosActivity.class));
            //finish();
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
