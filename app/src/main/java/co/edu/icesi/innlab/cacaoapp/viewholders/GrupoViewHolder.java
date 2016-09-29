package co.edu.icesi.innlab.cacaoapp.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import co.edu.icesi.innlab.cacaoapp.R;
import co.edu.icesi.innlab.cacaoapp.models.Grupo;

/**
 * Created by 1130613425 on 27/09/2016.
 */

public class GrupoViewHolder extends RecyclerView.ViewHolder {

    public  View mView;
    public  TextView mIdView;
    public  TextView mNombreView;
    public  TextView mCantidadCacaosView;
    public  TextView mCantidadPuntosView;
    public ImageView mImagenGrupo;
    public Grupo mItem;

    public GrupoViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mIdView = (TextView) itemView.findViewById(R.id.tv_frag_grupo_identificador);
        mNombreView = (TextView) itemView.findViewById(R.id.tv_frag_grupo_nombre);
        mCantidadCacaosView = (TextView) itemView.findViewById(R.id.tv_frag_grupo_premioCacao_cantidad);
        mCantidadPuntosView = (TextView) itemView.findViewById(R.id.tv_frag_grupo_premioPuntos_cantidad);
        mImagenGrupo = (ImageView) itemView.findViewById(R.id.imgv_frag_grupo_imagen);
    }

    public void bindToReto(Grupo grupo, View.OnClickListener starClickListener) {
        mItem = grupo;
        mIdView.setText("voladores");
        mNombreView.setText(grupo.nombre);
        mCantidadCacaosView.setText(String.valueOf(grupo.cantidadCacaos));
        mCantidadPuntosView.setText(String.valueOf(grupo.cantidadPuntos));

      /*  <item>Coate</item>
        <item>Mazate</item>
        <item>Ocelote</item>
        <item>Coquite</item>
        <item>Chapolín</item>
        <item>Huitzilín</item>
        <item>Michín</item>
        <item>Tlacuache</item>*/

        if(grupo.nombre.equals("Coate")){
            mImagenGrupo.setImageResource(R.drawable.coate_icon);
        }
        if(grupo.nombre.equals("Mazate")){
            mImagenGrupo.setImageResource(R.drawable.mazate_icon);
        }
        if(grupo.nombre.equals("Coquite")){
            mImagenGrupo.setImageResource(R.drawable.coquite_icon);
        }
        if(grupo.nombre.equals("Chapolín")){
            mImagenGrupo.setImageResource(R.drawable.chapolin_icon);
        }
        if(grupo.nombre.equals("Huitzilín")){
            mImagenGrupo.setImageResource(R.drawable.huitzilin_icon);
        }
        if(grupo.nombre.equals("Michín")){
            mImagenGrupo.setImageResource(R.drawable.michin_icon);
        }
        if(grupo.nombre.equals("Tlacuache")){
            mImagenGrupo.setImageResource(R.drawable.tlacuache_icon);
        }
        if(grupo.nombre.equals("Ocelote")){
            mImagenGrupo.setImageResource(R.drawable.ocelote_icon);
        }

        //mPremioPuntosView.setOnClickListener(starClickListener); // para listener...

    }
}

