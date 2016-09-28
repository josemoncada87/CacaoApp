package co.edu.icesi.innlab.cacaoapp.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import co.edu.icesi.innlab.cacaoapp.R;
import co.edu.icesi.innlab.cacaoapp.models.Grupo;

/**
 * Created by 1130613425 on 27/09/2016.
 */

public class GrupoViewHolder extends RecyclerView.ViewHolder {

    public final View mView;
    public final TextView mIdView;
    public final TextView mNombreView;
    public final TextView mCantidadCacaosView;
    public final TextView mCantidadPuntosView;
    public Grupo mItem;

    public GrupoViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mIdView = (TextView) itemView.findViewById(R.id.tv_frag_grupo_identificador);
        mNombreView = (TextView) itemView.findViewById(R.id.tv_frag_grupo_nombre);
        mCantidadCacaosView = (TextView) itemView.findViewById(R.id.tv_frag_grupo_premioCacao_cantidad);
        mCantidadPuntosView = (TextView) itemView.findViewById(R.id.tv_frag_grupo_premioPuntos_cantidad);
    }

    public void bindToReto(Grupo grupo, View.OnClickListener starClickListener) {
        mIdView.setText("voladores");
        mNombreView.setText(grupo.nombre);
        mCantidadCacaosView.setText(String.valueOf(grupo.cantidadCacaos));
        mCantidadPuntosView.setText(String.valueOf(grupo.cantidadPuntos));
        //mPremioPuntosView.setOnClickListener(starClickListener); // para listener...
    }
}
