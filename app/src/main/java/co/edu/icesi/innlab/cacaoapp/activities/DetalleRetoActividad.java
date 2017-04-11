package co.edu.icesi.innlab.cacaoapp.activities;

/**
 * Created by Jose Moncada on 25/09/2016.
 * Version 1
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import co.edu.icesi.innlab.cacaoapp.R;
import co.edu.icesi.innlab.cacaoapp.models.Reto;

public class DetalleRetoActividad extends AppCompatActivity {

    public static final String KEY_RETO = "informacion_reto";
    private String uid;
    //Views
    private TextView retoNombre;
    private TextView retoCantCacaos;
    private TextView retoCantPuntos;
    private TextView retoDescCompleta;
    private TextView retoDescCorta;
    private Button btnRetoOk;
    //BD
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_reto_actividad);

        Intent lanzador = getIntent();
        uid = lanzador.getStringExtra(KEY_RETO);

        retoNombre      = (TextView) findViewById(R.id.tv_detalle_reto_nombre);
        retoCantCacaos  = (TextView) findViewById(R.id.tv_detalle_reto_cant_cacaos);
        retoCantPuntos  = (TextView) findViewById(R.id.tv_detalle_reto_cant_puntos);
        retoDescCompleta= (TextView) findViewById(R.id.tv_detalle_reto_desc_completa);
        retoDescCorta   = (TextView) findViewById(R.id.tv_detalle_reto_desc_corta);
        btnRetoOk       = (Button)   findViewById(R.id.btn_detalle_reto_ok);

        btnRetoOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ref = FirebaseDatabase.getInstance().getReference("retos").child(uid);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Reto r = dataSnapshot.getValue(Reto.class);
                if(r!=null) {
                    retoNombre.setText(r.nombre);
                    retoCantCacaos.setText(String.valueOf(r.premioCacao));
                    retoCantPuntos.setText(String.valueOf(r.premioPuntos));
                    retoDescCompleta.setText(r.descripcionCompleta);
                    retoDescCorta.setText(r.descripcionCorta);
                }else{
                    ref.removeEventListener(this);
                    finish();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
