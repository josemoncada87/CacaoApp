package co.edu.icesi.innlab.cacaoapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.edu.icesi.innlab.cacaoapp.R;
import co.edu.icesi.innlab.cacaoapp.models.Reto;
import co.edu.icesi.innlab.cacaoapp.viewholders.RetoViewHolder;
import java.util.List;


public class MyRetoRecyclerViewAdapter extends RecyclerView.Adapter<RetoViewHolder> {

    private final List<Reto> mValues;
   // private final OnListFragmentInteractionListener mListener;

    public MyRetoRecyclerViewAdapter(List<Reto> items/*, OnListFragmentInteractionListener listener*/) {
        mValues = items;
      //  mListener = listener;
    }

    @Override
    public RetoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_reto, parent, false);// fragment _ item
        return new RetoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RetoViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText("0");
       // holder.mContentView.setText(mValues.get(position).nombre);
       // holder.mEstadoView.setText(mValues.get(position).estado);
       // holder.mPremioView.setText(""+mValues.get(position).premio);
       /* holder.mNombreView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    System.out.println("pos:"+position+ " " + holder.mNombreView.getText().toString());
                    //mListener.onListFragmentInteraction(holder.);
                }
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

/*  public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
       // public final TextView mContentView;
       // public final TextView mEstadoView;
       // public final TextView mPremioView;
        public RetoItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.tv_frag_reto_identificador);
          //  mContentView = (TextView) view.findViewById(R.id.tv_frag_reto_contenido);
           // mEstadoView = (TextView) view.findViewById(R.id.tv_frag_reto_estado);
           // mPremioView = (TextView) view.findViewById(R.id.tv_frag_reto_premio);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText()" " + "'";
        }
    }*/
}
