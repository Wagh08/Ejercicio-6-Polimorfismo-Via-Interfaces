import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ClockWidget implements Widgets {

    private int id;
    private float consumo;
    private boolean estadoActivo;

    public ClockWidget(int id){
        this.id = id;
        consumo = 200;
        estadoActivo = false;
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
        JPanel panel = new JPanel(new BorderLayout());

        panel.setPreferredSize(new Dimension(220, 120));
        panel.setBorder(BorderFactory.createTitledBorder("Reloj"));

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm:ss");
        JLabel cuadroHora = new JLabel(LocalTime.now().format(formato), SwingConstants.CENTER);

        cuadroHora.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 28));
        cuadroHora.setOpaque(true);
        cuadroHora.setBackground(new Color(220, 240, 255));
        cuadroHora.setForeground(new Color(30, 100, 180));
        cuadroHora.setBorder(
        BorderFactory.createLineBorder(new Color(30, 100, 180), 2));

        panel.add(cuadroHora, BorderLayout.CENTER);

        Timer reloj = new Timer(1000, e -> {
            cuadroHora.setText(LocalTime.now().format(formato));
        });

        reloj.start();

        estadoActivo = true;
        return panel;
    }

    public String getConfiguracion(){
        return "ID: " + id
            + "\nConsumo: " + consumo + " KB"
            + "\nActivo: " + estadoActivo
            + "\nHora actual: " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}