package co.edu.icesi.innlab.cacaoapp.models;

/**
 * Created by Usuario on 25/09/2016.
 */

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

// [INICIO reto_model_class]
@IgnoreExtraProperties
public class Reto {

    public String id;
    public String uid;
    public String nombre;
    public String descripcionCorta;
    public String descripcionCompleta;
    public int premioCacao;
    public int premioPuntos;
    public boolean visible;

    public Reto() {
        // Constructor por defecto requerido para DataSnapshot.getValue(Reto.class)
    }

    public Reto(String id, String nombre, String descripcionCorta, String descripcionCompleta ,int premioCacao, int premioPuntos) {
        this.id = id;
        this.nombre = nombre;
        this.descripcionCorta = descripcionCorta;
        this.descripcionCompleta = descripcionCompleta;
        this.premioCacao = premioCacao;
        this.premioPuntos = premioPuntos;
        visible =  true;
    }

    // [START post_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("id", id);
        result.put("nombre", nombre);
        result.put("descripcionCorta", descripcionCorta);
        result.put("descripcionCompleta", descripcionCompleta);
        result.put("premioCacao", premioCacao);
        result.put("premioPuntos", premioPuntos);
        return result;
    }
    // [END post_to_map]

  }
// [FIN reto_model_class]

