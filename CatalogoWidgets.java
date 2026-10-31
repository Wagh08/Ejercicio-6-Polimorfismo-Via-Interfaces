import java.util.ArrayList;

public class CatalogoWidgets {

    public static ArrayList<Widgets> cargarWidgets(){
        ArrayList<Widgets> widgets = new ArrayList<>();

        widgets.add(new WeatherWidget(1, "Guatemala", 25));
        widgets.add(new ClockWidget(2));

        widgets.add(new TaskWidget(3, new String[]{
            "Estudiar Java",
            "Entregar proyecto",
            "Repasar interfaces"
        }));

        widgets.add(new QuoteWidget(4, "Cada paso cuenta."));
        widgets.add(new AlertWidget(5, "Revisar la entrega", 1));
        widgets.add(new WeatherWidget(6, "Quetzaltenango", 18));
        widgets.add(new ClockWidget(7));

        widgets.add(new TaskWidget(8, new String[]{
            "Leer la guía",
            "Actualizar el UML",
            "Probar el dashboard"
        }));

        widgets.add(new QuoteWidget(9, "Aprender exige práctica."));
        widgets.add(new AlertWidget(10, "Respaldar los archivos", 2));

        return widgets;
    }
}