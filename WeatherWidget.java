import javax.swing.*;
import java.awt.*;

public class WeatherWidget implements WidgetsInteractivos {

    private int id;
    private float consumo;
    private boolean estadoActivo;
    private String ciudad;
    private int temperatura;
    private String unidadMedida;

    public WeatherWidget(int id, String ciudad, int temperatura){
        this.id = id;
        consumo = 200;
        estadoActivo = false;
        this.ciudad = ciudad;
        this.temperatura = temperatura;
        unidadMedida = "C";
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
        panel.setBorder(BorderFactory.createTitledBorder("Clima"));
        panel.add(new JLabel("Ciudad: " + ciudad), BorderLayout.NORTH);

        JLabel cuadroTemperatura = new JLabel(temperatura + "°" + unidadMedida, SwingConstants.CENTER);
        cuadroTemperatura.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 28));
        cuadroTemperatura.setOpaque(true);
        cuadroTemperatura.setBackground(new Color(220, 240, 255));
        cuadroTemperatura.setForeground(new Color(30, 100, 180));
        cuadroTemperatura.setBorder(
        BorderFactory.createLineBorder(new Color(30, 100, 180), 2));

        panel.add(cuadroTemperatura, BorderLayout.CENTER);

        JButton botonUnidades = new JButton("Cambiar °C/°F");
        botonUnidades.addActionListener(e -> {
            respuestaInteraccion();
            cuadroTemperatura.setText(temperatura + "°" + unidadMedida);
        });

        panel.add(botonUnidades, BorderLayout.SOUTH);

        estadoActivo = true;
        return panel;
    }

    public String getConfiguracion(){
        return "ID: " + id
            + "\nConsumo: " + consumo + " KB"
            + "\nActivo: " + estadoActivo
            + "\nCiudad: " + ciudad
            + "\nTemperatura: " + temperatura + "°" + unidadMedida;
    }

    public void respuestaInteraccion(){
        if (unidadMedida.equals("C")) {
            temperatura = Math.round(temperatura * 9.0f / 5 + 32);
            unidadMedida = "F";
        } else {
            temperatura = Math.round((temperatura - 32) * 5.0f / 9);
            unidadMedida = "C";
        }
    }
}