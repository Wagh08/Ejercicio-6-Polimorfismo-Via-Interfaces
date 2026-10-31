import javax.swing.*;

public interface Widgets{
    
    public int getID();

    public float getConsumoKB();

    public boolean getEstado();
    
    public void renderizar(JPanel areaWidgets);

    public String getConfiguracion();

    public JComponent obtenerVista();

}