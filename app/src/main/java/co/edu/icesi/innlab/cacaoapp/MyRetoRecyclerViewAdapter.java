package co.edu.icesi.innlab.cacaoapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import co.edu.icesi.innlab.cacaoapp.RetoFragment.OnListFragmentInteractionListener;
import co.edu.icesi.innlab.cacaoapp.retos.RetoContent.RetoItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link RetoItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyRetoRecyclerViewAdapter extends RecyclerView.Adapter<MyRetoRecyclerViewAdapter.ViewHolder> {

    private final List<RetoItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyRetoRecyclerViewAdapter(List<RetoItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_reto, parent, false);// fragment _ item
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText("0");
        holder.mContentView.setText(mValues.get(position).nombre);
        holder.mEstadoView.setText(mValues.get(position).estado);
        holder.mPremioView.setText(""+mValues.get(position).premio);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final TextView mEstadoView;
        public final TextView mPremioView;
        public RetoItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.tv_frag_reto_identificador);
            mContentView = (TextView) view.findViewById(R.id.tv_frag_reto_contenido);
            mEstadoView = (TextView) view.findViewById(R.id.tv_frag_reto_estado);
            mPremioView = (TextView) view.findViewById(R.id.tv_frag_reto_premio);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
