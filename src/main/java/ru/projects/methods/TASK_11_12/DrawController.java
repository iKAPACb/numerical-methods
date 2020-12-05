package ru.projects.methods.TASK_11_12;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ru.projects.methods.TASK_11_12.integrals.IntegralRange;
import ru.projects.methods.TASK_11_12.integrals.IntegralSolver;
import ru.projects.methods.TASK_11_12.integrals.IntegralUtil;
import ru.projects.methods.TASK_11_12.tables.IntegralTable;


public class DrawController {
    @FXML
    public Button task_11;
    public Button task_12_1;
    public Button task_12_2;

    static double methodRygneRobengra(double F1, double F2, double p) {
        return F1 + (F1 - F2) / (Math.pow(2, p) - 1);
    }

    @FXML
    public void initialize() {
        task_12_1.setOnMouseClicked(event -> {


            Proizvodnaya proizvodnaya = new Proizvodnaya(2.0, new double[][]{{1, 1}, {1.5, 0.66667}, {2.0, 0.5}, {2.5, 0.4}, {3.0, 0.33333}});
            proizvodnaya.generateSystem();
            task_12_1.setText(String.format("Первая: %.4f \nВторая: %.4f", proizvodnaya.findFIRSTProizvodnaya(), proizvodnaya.findSECONDProizvodnaya()));


        });
        task_12_2.setOnMouseClicked(event -> {


            IntegralRange integralRange = new IntegralRange(1, 5, 1);
            IntegralSolver integralSolver = new IntegralSolver(integralRange);
            double exactValue = IntegralUtil.integralExactValue();
            double left = integralSolver.methodLeftRectangles();
            double right = integralSolver.methodRightRectangles();
            double middle = integralSolver.methodMiddleRectangles();
            double trapeze = integralSolver.methodTrapeze();
            double simpson = integralSolver.methodSimpson();
            IntegralTable integralTable1 = new IntegralTable("1.0", exactValue, left, exactValue - left, right, exactValue - right, middle, exactValue - middle, trapeze, exactValue - trapeze, simpson, exactValue - simpson);


            IntegralRange integralRange1 = new IntegralRange(1, 5, 0.5);
            IntegralSolver integralSolver1 = new IntegralSolver(integralRange1);
            double left1 = integralSolver1.methodLeftRectangles();
            double right1 = integralSolver1.methodRightRectangles();
            double middle1 = integralSolver1.methodMiddleRectangles();
            double trapeze1 = integralSolver1.methodTrapeze();
            double simpson1 = integralSolver1.methodSimpson();
            IntegralTable integralTable2 = new IntegralTable("0.5", exactValue, left1, exactValue - left1, right1, exactValue - right1, middle1, exactValue - middle1, trapeze1, exactValue - trapeze1, simpson1, exactValue - simpson1);


            double leftY = methodRygneRobengra(left1, left, 1);
            double rightY = methodRygneRobengra(right1, right, 1);
            double middleY = methodRygneRobengra(middle1, middle, 2);
            double trapezeY = methodRygneRobengra(trapeze1, trapeze, 2);
            double simpsonY = methodRygneRobengra(simpson1, simpson, 4);
            IntegralTable integralTable3 = new IntegralTable("Рунге-Ромберга", exactValue, leftY, exactValue - leftY, rightY, exactValue - rightY, middleY, exactValue - middleY, trapezeY, exactValue - trapezeY, simpsonY, exactValue - simpsonY);


            TableView<IntegralTable> table = new TableView<>();

            TableColumn<IntegralTable, String> nameColumn = new TableColumn<>("Метод или шаг");
            TableColumn<IntegralTable, Double> istZnachenieColumn = new TableColumn<>("Истинное значение");
            TableColumn<IntegralTable, Double> leftColumn = new TableColumn<>("М. левых прям.");
            TableColumn<IntegralTable, Double> leftPogreshnColumn = new TableColumn<>("Погрешность");
            TableColumn<IntegralTable, Double> rightColumn = new TableColumn<>("М. правых прям.");
            TableColumn<IntegralTable, Double> rightPogreshnColumn = new TableColumn<>("Погрешность");
            TableColumn<IntegralTable, Double> middleColumn = new TableColumn<>("М. средних прям.");
            TableColumn<IntegralTable, Double> midlePogreshnColumn = new TableColumn<>("Погрешность");
            TableColumn<IntegralTable, Double> trapezeColumn = new TableColumn<>("М. трапеций");
            TableColumn<IntegralTable, Double> trapezePogreshnColumn = new TableColumn<>("Погрешность");
            TableColumn<IntegralTable, Double> simpsonColumn = new TableColumn<>("М. Симпсона");
            TableColumn<IntegralTable, Double> simpsonPogreshnColumn = new TableColumn<>("Погрешность");

            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            istZnachenieColumn.setCellValueFactory(new PropertyValueFactory<>("istZnachenie"));
            leftColumn.setCellValueFactory(new PropertyValueFactory<>("left"));
            leftPogreshnColumn.setCellValueFactory(new PropertyValueFactory<>("leftPogreshn"));
            rightColumn.setCellValueFactory(new PropertyValueFactory<>("right"));
            rightPogreshnColumn.setCellValueFactory(new PropertyValueFactory<>("rightPogreshn"));
            middleColumn.setCellValueFactory(new PropertyValueFactory<>("middle"));
            midlePogreshnColumn.setCellValueFactory(new PropertyValueFactory<>("middlePogreshn"));
            trapezeColumn.setCellValueFactory(new PropertyValueFactory<>("trapeze"));
            trapezePogreshnColumn.setCellValueFactory(new PropertyValueFactory<>("trapezePogreshn"));
            simpsonColumn.setCellValueFactory(new PropertyValueFactory<>("simpson"));
            simpsonPogreshnColumn.setCellValueFactory(new PropertyValueFactory<>("simpsonPogreshn"));

            ObservableList<IntegralTable> list = FXCollections.observableArrayList();
            list.add(integralTable1);
            list.add(integralTable2);
            list.add(integralTable3);
            table.setItems(list);

            table.getColumns().addAll(nameColumn, istZnachenieColumn, leftColumn, leftPogreshnColumn, rightColumn, rightPogreshnColumn, middleColumn, midlePogreshnColumn, trapezeColumn, trapezePogreshnColumn, simpsonColumn, simpsonPogreshnColumn);

            StackPane root = new StackPane();
            root.setPadding(new Insets(5));
            root.getChildren().add(table);

            Scene scene = new Scene(root);
            Stage newWindow = new Stage();

            newWindow.setTitle("таблица");
            newWindow.setScene(scene);
            newWindow.show();

        });
        task_11.setOnMouseClicked(event -> {
            AreaChart<Number, Number> areaChart = new AreaChart<>(new NumberAxis(), new NumberAxis());

            XYChart.Series<Number, Number> FUNC = new XYChart.Series<>();
            XYChart.Series<Number, Number> POLYNOMIAL1 = new XYChart.Series<>();
            XYChart.Series<Number, Number> POLYNOMIAL2 = new XYChart.Series<>();

            double[][] XandYFunc = new double[][]{{-0.7, 1.6462}, {-0.4, 1.5823}, {-0.1, 1.571}, {0.2, 1.5694}, {0.5, 1.5472}, {0.8, 1.4435}};

            MNK mnk = new MNK(2, XandYFunc);

            for (double[] xy : XandYFunc) {
                FUNC.getData().add(new XYChart.Data<>(xy[0], xy[1]));
            }

            for (double x = XandYFunc[0][0] - 0.5; x < XandYFunc[XandYFunc.length - 1][0] + 0.5; x += 0.01) {
                POLYNOMIAL1.getData().add(new XYChart.Data<>(x, mnk.Fpribl1(x)));
                POLYNOMIAL2.getData().add(new XYChart.Data<>(x, mnk.Fpribl2(x)));
            }

            String v1 = "Сумма квадратов ошибок:\n";
            String v2 = String.format("Для первого многочлена = %.5f\n", Math.pow(1.6462 - mnk.Fpribl1(-0.7), 2) + Math.pow(1.5823 - mnk.Fpribl1(-0.4), 2) + Math.pow(1.571 - mnk.Fpribl1(-0.1), 2) + Math.pow(1.5694 - mnk.Fpribl1(0.2), 2) + Math.pow(1.5472 - mnk.Fpribl1(0.5), 2) + Math.pow(1.4435 - mnk.Fpribl1(0.8), 2));
            String v3 = String.format("Для второго многочлена = %.5f\n", Math.pow(1.6462 - mnk.Fpribl2(-0.7), 2) + Math.pow(1.5823 - mnk.Fpribl2(-0.4), 2) + Math.pow(1.571 - mnk.Fpribl2(-0.1), 2) + Math.pow(1.5694 - mnk.Fpribl2(0.2), 2) + Math.pow(1.5472 - mnk.Fpribl2(0.5), 2) + Math.pow(1.4435 - mnk.Fpribl2(0.8), 2));

            task_11.setText(v1+v2+v3);

            FUNC.setName("FUNC");
            POLYNOMIAL1.setName("POLYNOMIAL1");
            POLYNOMIAL2.setName("POLYNOMIAL2");
            areaChart.getData().setAll(FUNC, POLYNOMIAL1, POLYNOMIAL2);
            Scene scene = new Scene(areaChart);
            Stage newWindow = new Stage();
            newWindow.setTitle("TASK_11");
            newWindow.setScene(scene);
            newWindow.show();
        });
    }
}
