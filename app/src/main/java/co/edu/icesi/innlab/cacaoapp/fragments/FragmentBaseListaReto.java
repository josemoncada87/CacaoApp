package co.edu.icesi.innlab.cacaoapp.fragments;

/**
 * Created by 1130613425 on 26/09/2016.
 */

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;

        import com.firebase.ui.database.FirebaseRecyclerAdapter;

        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.Query;
        import co.edu.icesi.innlab.cacaoapp.R;
        import co.edu.icesi.innlab.cacaoapp.activities.DetalleRetoActividad;
        import co.edu.icesi.innlab.cacaoapp.models.Reto;
        import co.edu.icesi.innlab.cacaoapp.viewholders.RetoViewHolder;

public abstract class FragmentBaseListaReto extends Fragment {

    private static final String TAG = "Lista Fragmento:";

    // [START define_database_reference]
    private DatabaseReference mDatabase;
    // [END define_database_reference]

    protected FirebaseRecyclerAdapter<Reto, RetoViewHolder> mAdapter;
    protected RecyclerView mRecycler;
    protected LinearLayoutManager mManager;

    public FragmentBaseListaReto() {}

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_reto_list, container, false);
        // [START create_database_reference]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END create_database_reference]
        mRecycler = (RecyclerView) rootView.findViewById(R.id.list);
        mRecycler.setHasFixedSize(true);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Set up Layout Manager, reverse layout
        mManager = new LinearLayoutManager(getActivity());
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);

        // Set up FirebaseRecyclerAdapter with the Query
        Query retoQuery = getQuery(mDatabase);
        mAdapter = new FirebaseRecyclerAdapter<Reto, RetoViewHolder>(Reto.class, R.layout.fragment_reto,
                RetoViewHolder.class, retoQuery) {
            @Override
            protected void populateViewHolder(final RetoViewHolder viewHolder, final Reto model, final int position) {
                final DatabaseReference retoRef = getRef(position);

                // Set click listener for the whole reto view
                final String retoKey = retoRef.getKey();
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       //System.out.println("CLICK: " + retoKey);
                       // lanza DetalleRetoActividad y le entrega el uid
                       Intent intent = new Intent(getActivity(), DetalleRetoActividad.class);
                       intent.putExtra(DetalleRetoActividad.KEY_RETO, retoKey);
                       startActivity(intent);
                    }
                });

                // Determine if the current user has liked this post and set UI accordingly
                /* if (model.stars.containsKey(getUid())) {
                    viewHolder.starView.setImageResource(R.drawable.ic_toggle_star_24);
                } else {
                    viewHolder.starView.setImageResource(R.drawable.ic_toggle_star_outline_24);
                }*/
                // Bind Reto to ViewHolder, setting OnClickListener for the star button
                viewHolder.bindToReto(model, new View.OnClickListener() {
                    @Override
                    public void onClick(View starView) {
                        // Need to write to both places the post is stored
                       // DatabaseReference globalRetosRef = mDatabase.child("retos").child(retoRef.getKey());
                        //DatabaseReference userPostRef = mDatabase.child("user-posts").child(model.uid).child(retoRef.getKey());
                        // Run two transactions
                        // onStarClicked(globalPostRef);
                        // onStarClicked(userPostRef);
                    }
                });
            }
        };
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mAdapter != null) {
            mAdapter.cleanup();
        }
    }

    public String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public abstract Query getQuery(DatabaseReference databaseReference);

}