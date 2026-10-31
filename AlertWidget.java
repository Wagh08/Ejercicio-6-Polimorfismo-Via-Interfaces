import javax.swing.*;
import java.awt.*;

public class AlertWidget implements WidgetsInteractivos {

    private int id;
    private float consumo;
    private boolean estadoActivo;
    private String mensaje;
    private int prioridad;
    private boolean leida;

    public AlertWidget(int id, String mensaje, int prioridad){
        this.id = id;
        consumo = 200;
        estadoActivo = false;
        this.mensaje = mensaje;
        this.prioridad = prioridad;
        leida = false;
    }

    public int getID(){
        return id;
    }

    public float getConsumoKB(){
        return consumo;
    }

    public boolean getEstado(){
        return estadoActivo;
    }

    public void renderizar(JPanel areaWidgets){
        areaWidgets.add(obtenerVista());
        areaWidgets.revalidate();
        areaWidgets.repaint();
    }

    public JComponent obtenerVista(){
        JPanel panel = new JPanel(new BorderLayout(5, 5));

        panel.setPreferredSize(new Dimension(220, 120));
        panel.setBorder(BorderFactory.createTitledBorder("Alerta"));
        panel.add(new JLabel("Prioridad: " + prioridad), BorderLayout.NORTH);

        JLabel cuadroMensaje = new JLabel(mensaje, SwingConstants.CENTER);
        cuadroMensaje.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        cuadroMensaje.setOpaque(true);
        cuadroMensaje.setBackground(new Color(255, 230, 230));
        cuadroMensaje.setForeground(new Color(170, 40, 40));
        cuadroMensaje.setBorder(
        BorderFactory.createLineBorder(new Color(170, 40, 40), 2));

        panel.add(cuadroMensaje, BorderLayout.CENTER);

        JButton botonAlerta = new JButton("Leer alerta");
        botonAlerta.addActionListener(e -> respuestaInteraccion());
        panel.add(botonAlerta, BorderLayout.SOUTH);

        estadoActivo = true;
        return panel;
    }

    public String getConfiguracion(){
        return "ID: " + id
            + "\nConsumo: " + consumo + " KB"
            + "\nActivo: " + estadoActivo
            + "\nMensaje: " + mensaje
            + "\nPrioridad: " + prioridad
            + "\nLeída: " + leida;
    }

    public void respuestaInteraccion(){
        leida = true;
        JOptionPane.showMessageDialog(null, "La alerta se marcó como leída.");
    }
}