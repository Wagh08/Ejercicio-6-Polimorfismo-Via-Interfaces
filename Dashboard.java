import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Dashboard {

    private ArrayList<Widgets> widgets;

    public Dashboard(ArrayList<Widgets> widgets){
        this.widgets = new ArrayList<>(widgets);
    }

    public ArrayList<Widgets> listarActivos(){
        ArrayList<Widgets> activos = new ArrayList<>();

        for (int i = 0; i < widgets.size(); i++) {
            if (widgets.get(i).getEstado()) {
                activos.add(widgets.get(i));
            }
        }

        return activos;
    }

    public Widgets buscarWidget(int id){
        for (int i = 0; i < widgets.size(); i++) {
            if (widgets.get(i).getID() == id) {
                return widgets.get(i);
            }
        }

        return null;
    }

    public ArrayList<Widgets> ordenarPorConsumo(){
        ArrayList<Widgets> ordenados = new ArrayList<>(widgets);

        ordenados.sort((primero, segundo) ->
            Float.compare(primero.getConsumoKB(), segundo.getConsumoKB())
        );

        return ordenados;
    }

    public ArrayList<Widgets> getWidgets(){
        return new ArrayList<>(widgets);
    }

}