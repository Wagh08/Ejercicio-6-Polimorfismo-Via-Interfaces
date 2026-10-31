import javax.swing.*;
import java.awt.*;

public class TaskWidget implements WidgetsInteractivos {

    private int id;
    private float consumo;
    private boolean estadoActivo;
    private String[] tareas;
    private boolean[] completadas;

    public TaskWidget(int id, String[] tareas){
        if (tareas == null || tareas.length > 5) {
            throw new IllegalArgumentException("Debes enviar un arreglo de hasta cinco tareas.");
        }

        this.id = id;
        consumo = 200;
        estadoActivo = false;
        this.tareas = tareas.clone();
        completadas = new boolean[tareas.length];
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
        panel.setBorder(BorderFactory.createTitledBorder("Tareas"));

        JPanel listaTareas = new JPanel(new GridLayout(0, 1));
        JLabel[] etiquetas = new JLabel[tareas.length];

        for (int i = 0; i < tareas.length; i++) {
            String estado = "No completada";

            if (completadas[i]) {
                estado = "Completada";
            }

            etiquetas[i] = new JLabel(tareas[i] + " - " + estado);
            etiquetas[i].setEnabled(i == 0 || completadas[i - 1]);
            listaTareas.add(etiquetas[i]);
        }

        panel.add(new JScrollPane(listaTareas), BorderLayout.CENTER);

        JButton botonCompletar = new JButton("Completar siguiente");
        botonCompletar.setEnabled(tareas.length > 0 && !completadas[tareas.length - 1]);

        botonCompletar.addActionListener(e -> {
            respuestaInteraccion();

            for (int i = 0; i < tareas.length; i++) {
                String estado = "No completada";

                if (completadas[i]) {
                    estado = "Completada";
                }

                etiquetas[i].setText(tareas[i] + " - " + estado);
                etiquetas[i].setEnabled(i == 0 || completadas[i - 1]);
            }

            botonCompletar.setEnabled(tareas.length > 0 && !completadas[tareas.length - 1]);
        });

        panel.add(botonCompletar, BorderLayout.SOUTH);

        estadoActivo = true;
        return panel;
    }

    public String getConfiguracion(){
        String configuracion = "ID: " + id
            + "\nConsumo: " + consumo + " KB"
            + "\nActivo: " + estadoActivo;

        for (int i = 0; i < tareas.length; i++) {
            configuracion += "\n" + (i + 1) + ". " + tareas[i] + " - ";

            if (completadas[i]) {
                configuracion += "Completada";
            } else {
                configuracion += "No completada";
            }
        }

        return configuracion;
    }

    public void respuestaInteraccion(){
        for (int i = 0; i < tareas.length; i++) {
            if (!completadas[i]) {
                completadas[i] = true;
                return;
            }
        }
    }
}