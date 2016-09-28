package co.edu.icesi.innlab.cacaoapp.viewholders;

/**
 * Created by 1130613425 on 26/09/2016.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import co.edu.icesi.innlab.cacaoapp.R;
import co.edu.icesi.innlab.cacaoapp.models.Reto;

public class RetoViewHolder extends RecyclerView.ViewHolder {

    public final View mView;
    public final TextView mIdView;
    public final TextView mNombreView;
    public final TextView mDescripcionCortaView;
    public final TextView mPremioCacaosView;
    public final TextView mPremioPuntosView;
    public Reto mItem;

    public RetoViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mIdView = (TextView) itemView.findViewById(R.id.tv_frag_reto_identificador);
        mNombreView = (TextView) itemView.findViewById(R.id.tv_frag_grupo_nombre);
        mDescripcionCortaView = (TextView) itemView.findViewById(R.id.tv_frag_grupo_inventario);
        mPremioCacaosView = (TextView) itemView.findViewById(R.id.tv_frag_reto_premioCacao_cantidad);
        mPremioPuntosView = (TextView) itemView.findViewById(R.id.tv_frag_reto_premioPuntos_cantidad);
    }

    public void bindToReto(Reto reto, View.OnClickListener starClickListener) {
        mIdView.setText(reto.id);
        mNombreView.setText(reto.nombre);
        mDescripcionCortaView.setText(reto.descripcionCorta);
        mPremioCacaosView.setText(String.valueOf(reto.premioCacao));
        mPremioPuntosView.setText(String.valueOf(reto.premioPuntos));
        //mPremioPuntosView.setOnClickListener(starClickListener); // para listener...
    }
}
