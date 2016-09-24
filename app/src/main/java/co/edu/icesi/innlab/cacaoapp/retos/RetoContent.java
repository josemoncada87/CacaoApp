package co.edu.icesi.innlab.cacaoapp.retos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class RetoContent {

    /**
     * An array of sample (reto) items.
     */
    public static final List<RetoItem> ITEMS = new ArrayList<RetoItem>();

    /**
     * A map of sample (reto) items, by ID.
     */
    public static final Map<String, RetoItem> ITEM_MAP = new HashMap<String, RetoItem>();

    private static final int COUNT = 5;

   static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createRetoItem("monk", 330, true));
        }
    }

    public static void addItem(RetoItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    public static RetoItem createRetoItem(String nombre, int premio, boolean cacao) {
        //return new RetoItem(String.valueOf(position), " pos " + position, makeDetails(position));
        return new RetoItem(String.valueOf(0), String.valueOf(nombre), premio, cacao);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A reto item representing a piece of content.
     */
    public static class RetoItem {
        public final String id;
        public final String nombre;
        public final String estado;
        public final int premio;
        public final boolean cacao;

        public RetoItem(String id, String nombre, int premio, boolean esCacao) {
            this.id = id;
            this.estado =  "nuevo";
            this.nombre = nombre;
            this.premio = premio;
            this.cacao = esCacao;
        }

        @Override
        public String toString() {
            return nombre;
        }
    }
}
