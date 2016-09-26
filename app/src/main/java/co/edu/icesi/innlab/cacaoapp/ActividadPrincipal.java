package co.edu.icesi.innlab.cacaoapp;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;

import co.edu.icesi.innlab.cacaoapp.model.RetoContent;

public class ActividadPrincipal extends BaseActivity implements PerfilFragment.OnFragmentInteractionListener, RetoFragment.OnListFragmentInteractionListener, EstadisticasFragment.OnFragmentInteractionListener{

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

    // fragments
    private RetoFragment retoTabFragment;
    private PerfilFragment perfilTabFragment;
    private EstadisticasFragment estadisticas;

    // Views
    private TextView nombreUsuario;
    private TextView nombreEquipo;
    private TextView numeroDeCacaos;
    private TextView rankingEquipo;


////////////////////

    private FragmentPagerAdapter mPagerAdapter;
  //  private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_principal);

        //////////////////////////////////////////////////////////////

        // Create the adapter that will return a fragment for each section
        mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            private final Fragment[] mFragments = new Fragment[] {
                    PerfilFragment.newInstance("",""),
                    new EstadisticasFragment(),
                    new RetoFragment(),
            };
            private final String[] mFragmentNames = new String[] {
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
       /* nombreEquipo = (TextView) findViewById(R.id.tv_frag_perfil_nombre_equipo);
        numeroDeCacaos = (TextView) findViewById(R.id.tv_frag_perfil_tx_cacaos);
        rankingEquipo = (TextView) findViewById(R.id.tv_frag_perfil_tx_ranking_equipo);*/

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

        }
        nombreUsuario.setText("chiwilngo rodriguez");
        nombreEquipo.setText("Renacuajos Bebedores");
        numeroDeCacaos.setText("Cacaos = 50");
        rankingEquipo.setText("Ranking = 2");*/

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
            }
        });





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

        return super.onOptionsItemSelected(item);
    }
// TODO cambiar para perfil
    @Override
    public void onFragmentInteraction(Uri uri) {
        System.out.println("testing...");
    }

    // TODO cambiar para retos
    @Override
    public void onListFragmentInteraction(RetoContent.RetoItem item) {

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

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            Fragment retorno = null;
            switch(position){
                case 0:
                    retorno = perfilTabFragment;
                    break;
                case 1:
                    retorno = EstadisticasFragment.newInstance("","");
                    break;
                case 2:
                    retorno = retoTabFragment;
                    break;
            }
            return retorno;
        }
        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "PERFIL";
                case 1:
                    return "ESTADÍSTICAS";
                case 2:
                    return "RETOS";
            }
            return null;
        }
    }
}
