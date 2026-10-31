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

    private void mostrarLista(ArrayList<Widgets> lista, String titulo){
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay widgets para mostrar.", titulo, JOptionPane.INFORMATION_MESSAGE);
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

        JOptionPane.showMessageDialog(null, desplazamiento, titulo, JOptionPane.PLAIN_MESSAGE);
    }

    private void buscarConfiguracion(){
        String criterio = JOptionPane.showInputDialog(null, "Escribe el ID del widget:");

        if (criterio == null || criterio.trim().isEmpty()) {
            return;
        }

        int id;

        try {
            id = Integer.parseInt(criterio.trim());
        } catch (NumberFormatException error) {
            JOptionPane.showMessageDialog(null, "Escribe un ID numérico válido.");
            return;
        }

        Widgets elegido = buscarWidget(id);

        if (elegido == null) {
            JOptionPane.showMessageDialog(null, "No existe un widget con ese ID.");
            return;
        }

        JTextArea configuracion = new JTextArea(elegido.getConfiguracion(), 12, 40);
        configuracion.setEditable(false);
        configuracion.setLineWrap(true);
        configuracion.setWrapStyleWord(true);

        JOptionPane.showMessageDialog(
            null,
            new JScrollPane(configuracion),
            "Configuración del widget " + elegido.getID(),
            JOptionPane.PLAIN_MESSAGE
        );
    }

    public void mostrar(){
        JLabel titulo = new JLabel("DASHBOARD");
        titulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        titulo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 2, 0, Color.DARK_GRAY),
            BorderFactory.createEmptyBorder(12, 18, 12, 18)
        ));

        JLabel tituloMenu = new JLabel("MENÚ", SwingConstants.CENTER);
        tituloMenu.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));

        JButton botonActivos = new JButton("Listar widgets activos");
        JButton botonBuscar = new JButton("Buscar widget por ID");
        JButton botonOrdenar = new JButton("<html><center>Ordenar widgets<br>por consumo</center></html>");

        botonActivos.addActionListener(e ->
            mostrarLista(listarActivos(), "Widgets activos")
        );

        botonBuscar.addActionListener(e -> buscarConfiguracion());

        botonOrdenar.addActionListener(e ->
            mostrarLista(ordenarPorConsumo(), "Widgets por consumo: menor a mayor")
        );

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

        JPanel areaWidgets = new JPanel(new GridLayout(0, 4, 12, 12));
        areaWidgets.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        for (int i = 0; i < widgets.size(); i++) {
            areaWidgets.add(widgets.get(i).obtenerVista());
        }

        JPanel contenido = new JPanel(new BorderLayout());
        contenido.add(areaWidgets, BorderLayout.NORTH);

        JFrame ventana = new JFrame("Dashboard");
        ventana.setLayout(new BorderLayout(12, 0));
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.add(titulo, BorderLayout.NORTH);
        ventana.add(desplazamientoMenu, BorderLayout.WEST);
        ventana.add(new JScrollPane(contenido), BorderLayout.CENTER);
        ventana.setSize(1280, 600);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            Dashboard dashboard = new Dashboard(CatalogoWidgets.cargarWidgets());
            dashboard.mostrar();
        });
    }
}