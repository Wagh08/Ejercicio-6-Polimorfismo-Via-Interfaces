import javax.swing.*;

public interface Widgets extends Comparable<Widgets>{

    public int getID();

    public float getConsumoKB();

    public boolean getEstado();

    public void renderizar(JPanel areaWidgets);

    public JComponent obtenerVista();

    public String getConfiguracion();

    public default int compareTo(Widgets otro){
        return Float.compare(getConsumoKB(), otro.getConsumoKB());
    }
}