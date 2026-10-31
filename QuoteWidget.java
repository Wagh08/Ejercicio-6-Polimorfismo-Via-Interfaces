import javax.swing.*;
import java.awt.*;

public class QuoteWidget implements Widgets {

    private int id;
    private float consumo;
    private boolean estadoActivo;
    private String frase;

    public QuoteWidget(int id, String frase){
        this.id = id;
        consumo = 200;
        estadoActivo = false;
        this.frase = frase;
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
        panel.setBorder(BorderFactory.createTitledBorder("Frase"));

        JTextArea cuadroFrase = new JTextArea(frase);
        cuadroFrase.setLineWrap(true);
        cuadroFrase.setWrapStyleWord(true);
        cuadroFrase.setEditable(false);
        cuadroFrase.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 16));
        cuadroFrase.setBackground(new Color(255, 250, 220));
        cuadroFrase.setForeground(new Color(120, 90, 20));
        cuadroFrase.setBorder(
        BorderFactory.createLineBorder(new Color(180, 150, 50), 2));

        panel.add(new JScrollPane(cuadroFrase), BorderLayout.CENTER);

        estadoActivo = true;
        return panel;
    }

    public String getConfiguracion(){
        return "ID: " + id
            + "\nConsumo: " + consumo + " KB"
            + "\nActivo: " + estadoActivo
            + "\nFrase: " + frase;
    }
}