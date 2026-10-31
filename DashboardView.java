import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DashboardView extends JFrame {

    private JPanel areaWidgets;
    private JButton botonActivos;
    private JButton botonBuscar;
    private JButton botonOrdenar;

    public DashboardView(){
        super("Dashboard");

        JLabel titulo = new JLabel("DASHBOARD");
        titulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        titulo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 2, 0, Color.DARK_GRAY),
            BorderFactory.createEmptyBorder(12, 18, 12, 18)
        ));

        JLabel tituloMenu = new JLabel("MENÚ", SwingConstants.CENTER);
        tituloMenu.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));

        botonActivos = new JButton("Listar widgets activos");
        botonBuscar = new JButton("Buscar widget por ID");
        botonOrdenar = new JButton("<html><center>Ordenar widgets<br>por consumo</center></html>");

        JPanel botonesMenu = new JPanel(new GridLayout(0, 1, 0, 16));

        for (JButton boton : new JButton[]{botonActivos, botonBuscar, botonOrdenar}) {
            boton.setPreferredSize(new Dimension(200, 54));
            botonesMenu.add(boton);
        }

        JPanel menu = new JPanel(new BorderLayout(0, 20));
        menu.setBorder(BorderFactory.createEmptyBorder(16, 12, 16, 12));
        menu.add(tituloMenu, BorderLayout.NORTH);
        menu.add(botonesMenu, BorderLayout.CENTER);

        JPanel contenidoMenu = new JPanel(new BorderLayout());
        contenidoMenu.add(menu, BorderLayout.NORTH);

        JScrollPane desplazamientoMenu = new JScrollPane(contenidoMenu);
        desplazamientoMenu.setPreferredSize(new Dimension(235, 0));

        areaWidgets = new JPanel(new GridLayout(0, 4, 12, 12));
        areaWidgets.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JPanel contenido = new JPanel(new BorderLayout());
        contenido.add(areaWidgets, BorderLayout.NORTH);

        setLayout(new BorderLayout(12, 0));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(titulo, BorderLayout.NORTH);
        add(desplazamientoMenu, BorderLayout.WEST);
        add(new JScrollPane(contenido), BorderLayout.CENTER);
        setSize(1280, 600);
        setLocationRelativeTo(null);
    }

    public void registrarEventos(ActionListener activos, ActionListener buscar, ActionListener ordenar){
        botonActivos.addActionListener(activos);
        botonBuscar.addActionListener(buscar);
        botonOrdenar.addActionListener(ordenar);
    }

    public void mostrarWidgets(ArrayList<Widgets> widgets){
        areaWidgets.removeAll();

        for (int i = 0; i < widgets.size(); i++) {
            areaWidgets.add(widgets.get(i).obtenerVista());
        }

        areaWidgets.revalidate();
        areaWidgets.repaint();
    }

    public void mostrarLista(ArrayList<Widgets> lista, String titulo){
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay widgets para mostrar.", titulo, JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String[] columnas = {"ID", "Consumo (KB)", "Estado"};
        Object[][] datos = new Object[lista.size()][3];

        for (int i = 0; i < lista.size(); i++) {
            Widgets widget = lista.get(i);

            datos[i][0] = widget.getID();
            datos[i][1] = widget.getConsumoKB();
            datos[i][2] = widget.getEstado() ? "Activo" : "Inactivo";
        }

        JTable tabla = new JTable(datos, columnas);
        tabla.setDefaultEditor(Object.class, null);
        tabla.setRowHeight(25);
        tabla.setFillsViewportHeight(true);
        tabla.getTableHeader().setReorderingAllowed(false);

        JScrollPane desplazamiento = new JScrollPane(tabla);
        desplazamiento.setPreferredSize(new Dimension(640, 300));

        JOptionPane.showMessageDialog(this, desplazamiento, titulo, JOptionPane.PLAIN_MESSAGE);
    }

    public String pedirID(){
        return JOptionPane.showInputDialog(this, "Escribe el ID del widget:");
    }

    public void mostrarConfiguracion(String configuracion){
        JTextArea texto = new JTextArea(configuracion, 12, 40);
        texto.setEditable(false);
        texto.setLineWrap(true);
        texto.setWrapStyleWord(true);

        JOptionPane.showMessageDialog(
            this,
            new JScrollPane(texto),
            "Configuración del widget",
            JOptionPane.PLAIN_MESSAGE
        );
    }

    public void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(this, mensaje);
    }
}