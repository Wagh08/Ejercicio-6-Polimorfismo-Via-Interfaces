import javax.swing.*;

public class DashboardController {

    private Dashboard modelo;
    private DashboardView vista;

    public DashboardController(Dashboard modelo, DashboardView vista){
        this.modelo = modelo;
        this.vista = vista;

        vista.registrarEventos(
            e -> listarActivos(),
            e -> buscarWidget(),
            e -> ordenarWidgets()
        );
    }

    public void iniciar(){
        vista.mostrarWidgets(modelo.getWidgets());
        vista.setVisible(true);
    }

    private void listarActivos(){
        vista.mostrarLista(modelo.listarActivos(), "Widgets activos");
    }

    private void buscarWidget(){
        String texto = vista.pedirID();

        if (texto == null || texto.trim().isEmpty()) {
            return;
        }

        int id;

        try {
            id = Integer.parseInt(texto.trim());
        } catch (NumberFormatException error) {
            vista.mostrarMensaje("Escribe un ID numérico válido.");
            return;
        }

        Widgets widget = modelo.buscarWidget(id);

        if (widget == null) {
            vista.mostrarMensaje("No existe un widget con ese ID.");
            return;
        }

        vista.mostrarConfiguracion(widget.getConfiguracion());
    }

    private void ordenarWidgets(){
        vista.mostrarLista(
            modelo.ordenarPorConsumo(),
            "Widgets por consumo: menor a mayor"
        );
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            Dashboard modelo = new Dashboard(CatalogoWidgets.cargarWidgets());
            DashboardView vista = new DashboardView();
            DashboardController controlador = new DashboardController(modelo, vista);

            controlador.iniciar();
        });
    }
}