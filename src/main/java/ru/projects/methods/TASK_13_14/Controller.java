package ru.projects.methods.TASK_13_14;

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
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Controller {
    @FXML
    public Button graph13;
    @FXML
    public Button euler13;
    @FXML
    public Button eulerkoshi13;
    @FXML
    public Button ryngekyt14;
    @FXML
    public Button adams14;

    @FXML
    public void initialize() {

        ArrayList<AdamsBbIvod> adamsListH1 = new Adams(0.1).createCells();
        ArrayList<AdamsBbIvod> adamsListH2 = new Adams(0.05).createCells();
        ArrayList<AdamsBbIvod> adamsList = new ArrayList<>();
        adamsList.addAll(adamsListH1);
        adamsList.addAll(adamsListH2);

        ArrayList<RyngeKytBbIvod> ryngeKytListH1 = new RyngeKyt(0.1).createCells();
        ArrayList<RyngeKytBbIvod> ryngeKytListH2 = new RyngeKyt(0.05).createCells();
        ArrayList<RyngeKytBbIvod> ryngeKytList = new ArrayList<>();
        ryngeKytList.addAll(ryngeKytListH1);
        ryngeKytList.addAll(ryngeKytListH2);

        ArrayList<EulerKoshiBbIvod> eulerKoshiListH1 = new EulerKoshi(0.1).createCells();
        ArrayList<EulerKoshiBbIvod> eulerKoshiListH2 = new EulerKoshi(0.05).createCells();
        ArrayList<EulerKoshiBbIvod> eulerKoshiList = new ArrayList<>();
        eulerKoshiList.addAll(eulerKoshiListH1);
        eulerKoshiList.addAll(eulerKoshiListH2);

        ArrayList<EulerBbIvod> eulerListH1 = new EuleR(0.1).createCells();
        ArrayList<EulerBbIvod> eulerListH2 = new EuleR(0.05).createCells();
        ArrayList<EulerBbIvod> eulerList = new ArrayList<>();
        eulerList.addAll(eulerListH1);
        eulerList.addAll(eulerListH2);



        graph13.setOnMouseClicked(event -> {
            AreaChart<Number, Number> areaChart = new AreaChart<>(new NumberAxis(1,2,0.1), new NumberAxis());


            XYChart.Series<Number, Number> seriesFunc = new XYChart.Series<>();
            XYChart.Series<Number, Number> seriesEuler = new XYChart.Series<>();
            XYChart.Series<Number, Number> seriesEulerKoshi = new XYChart.Series<>();
            XYChart.Series<Number, Number> seriesRyngeKyt = new XYChart.Series<>();

            for (int x = 0; x <= eulerListH1.size() - 1; x++) {
                seriesFunc.getData().add(new XYChart.Data<>(eulerListH1.get(x).getX(), eulerListH1.get(x).getIstY()));
                seriesEuler.getData().add(new XYChart.Data<>(eulerListH1.get(x).getX(), eulerListH1.get(x).getY()));
                seriesEulerKoshi.getData().add(new XYChart.Data<>(eulerKoshiListH1.get(x).getX(), eulerKoshiListH1.get(x).getY()));
                seriesRyngeKyt.getData().add(new XYChart.Data<>(ryngeKytListH1.get(x).getX(), ryngeKytListH1.get(x).getY()));
            }


            seriesFunc.setName("FUNC_IST");
            seriesEuler.setName("EULER");
            seriesEulerKoshi.setName("EULER-KOSHI");
            seriesRyngeKyt.setName("RYNGE-KYT");
            areaChart.getData().setAll(seriesFunc, seriesEuler,seriesEulerKoshi,seriesRyngeKyt);
            Scene scene = new Scene(areaChart);
            Stage newWindow = new Stage();
            newWindow.setTitle("GRAPHICS");
            newWindow.setScene(scene);
            newWindow.show();
        });


        euler13.setOnMouseClicked(event -> {

            TableView<EulerBbIvod> table = new TableView<>();

            TableColumn<EulerBbIvod, Integer> iColumn = new TableColumn<>("i");
            TableColumn<EulerBbIvod, Integer> hColumn = new TableColumn<>("h");
            TableColumn<EulerBbIvod, Double> xColumn = new TableColumn<>("x");
            TableColumn<EulerBbIvod, Double> yColumn = new TableColumn<>("y");
            TableColumn<EulerBbIvod, Double> zColumn = new TableColumn<>("z");
            TableColumn<EulerBbIvod, String> delta_zColumn = new TableColumn<>("delta_z");
            TableColumn<EulerBbIvod, String> delta_yColumn = new TableColumn<>("delta_y");
            TableColumn<EulerBbIvod, Double> istYColumn = new TableColumn<>("istY");
            TableColumn<EulerBbIvod, Double> accuracyColumn = new TableColumn<>("accuracy");

            iColumn.setCellValueFactory(new PropertyValueFactory<>("i"));
            hColumn.setCellValueFactory(new PropertyValueFactory<>("h"));
            xColumn.setCellValueFactory(new PropertyValueFactory<>("x"));
            yColumn.setCellValueFactory(new PropertyValueFactory<>("y"));
            zColumn.setCellValueFactory(new PropertyValueFactory<>("z"));
            delta_zColumn.setCellValueFactory(new PropertyValueFactory<>("delta_z"));
            delta_yColumn.setCellValueFactory(new PropertyValueFactory<>("delta_y"));
            istYColumn.setCellValueFactory(new PropertyValueFactory<>("istY"));
            accuracyColumn.setCellValueFactory(new PropertyValueFactory<>("accuracy"));

            ObservableList<EulerBbIvod> list = FXCollections.observableArrayList();
            list.setAll(eulerList);
            table.setItems(list);

            table.getColumns().addAll(iColumn, hColumn, xColumn, yColumn, zColumn, delta_zColumn, delta_yColumn, istYColumn, accuracyColumn);

            StackPane root = new StackPane();
            root.setPadding(new Insets(5));
            root.getChildren().add(table);

            Scene scene = new Scene(root);
            Stage newWindow = new Stage();

            newWindow.setTitle("Эйлера таблица");
            newWindow.setScene(scene);
            newWindow.show();

            /////RYNGE ROBERG

            StringBuilder res = new StringBuilder();

            for (int i = 0; i <= eulerListH1.size() - 1; i++) {

                double F1 = eulerListH1.get(i).getY();
                double F2 = eulerListH2.get(2*i).getY();
                double RR = F2 + ((F2 - F1) / (Math.pow(2, 2) - 1));
                res.append(String.format("x = %.3f  y = %.3f    acc = %.3f\n", eulerList.get(i).getX(), RR, Math.abs(RR - eulerListH1.get(i).getIstY())));

            }


            TextArea textArea = new TextArea();
            textArea.setPrefColumnCount(50);
            textArea.setPrefRowCount(10);

            textArea.setText(res.toString());

            Scene scene1 = new Scene(textArea);
            Stage newWindow1 = new Stage();
            newWindow1.setTitle("Rynge-Roberg");
            newWindow1.setScene(scene1);
            newWindow1.show();
        });

        eulerkoshi13.setOnMouseClicked(event -> {

            TableView<EulerKoshiBbIvod> table = new TableView<>();

            TableColumn<EulerKoshiBbIvod, Integer> iColumn = new TableColumn<>("i");
            TableColumn<EulerKoshiBbIvod, Integer> hColumn = new TableColumn<>("h");
            TableColumn<EulerKoshiBbIvod, Double> xColumn = new TableColumn<>("x");
            TableColumn<EulerKoshiBbIvod, Double> yColumn = new TableColumn<>("y");
            TableColumn<EulerKoshiBbIvod, Double> zColumn = new TableColumn<>("z");
            TableColumn<EulerKoshiBbIvod, Double> yrColumn = new TableColumn<>("yr");
            TableColumn<EulerKoshiBbIvod, Double> zrColumn = new TableColumn<>("zr");
            TableColumn<EulerKoshiBbIvod, String> delta_zColumn = new TableColumn<>("delta_z");
            TableColumn<EulerKoshiBbIvod, String> delta_yColumn = new TableColumn<>("delta_y");
            TableColumn<EulerKoshiBbIvod, Double> istYColumn = new TableColumn<>("istY");
            TableColumn<EulerKoshiBbIvod, Double> accuracyColumn = new TableColumn<>("accuracy");

            iColumn.setCellValueFactory(new PropertyValueFactory<>("i"));
            hColumn.setCellValueFactory(new PropertyValueFactory<>("h"));
            xColumn.setCellValueFactory(new PropertyValueFactory<>("x"));
            yColumn.setCellValueFactory(new PropertyValueFactory<>("y"));
            zColumn.setCellValueFactory(new PropertyValueFactory<>("z"));
            yrColumn.setCellValueFactory(new PropertyValueFactory<>("yr"));
            zrColumn.setCellValueFactory(new PropertyValueFactory<>("zr"));
            delta_zColumn.setCellValueFactory(new PropertyValueFactory<>("delta_z"));
            delta_yColumn.setCellValueFactory(new PropertyValueFactory<>("delta_y"));
            istYColumn.setCellValueFactory(new PropertyValueFactory<>("istY"));
            accuracyColumn.setCellValueFactory(new PropertyValueFactory<>("accuracy"));

            ObservableList<EulerKoshiBbIvod> list = FXCollections.observableArrayList();
            list.setAll(eulerKoshiList);
            table.setItems(list);

            table.getColumns().addAll(iColumn, hColumn, xColumn, yColumn, zColumn, yrColumn,zrColumn, delta_zColumn, delta_yColumn, istYColumn, accuracyColumn);

            StackPane root = new StackPane();
            root.setPadding(new Insets(5));
            root.getChildren().add(table);

            Scene scene = new Scene(root);
            Stage newWindow = new Stage();

            newWindow.setTitle("Эйлера-Коши таблица");
            newWindow.setScene(scene);
            newWindow.show();

            /////RYNGE ROBERG

            StringBuilder res = new StringBuilder();

            for (int i = 0; i <= eulerKoshiListH1.size() - 1; i++) {

                double F1 = eulerKoshiListH1.get(i).getY();
                double F2 = eulerKoshiListH2.get(2*i).getY();
                double RR = F2 + ((F2 - F1) / (Math.pow(2, 3) - 1));
                res.append(String.format("x = %.3f  y = %.3f    acc = %.3f\n", eulerKoshiListH1.get(i).getX(), RR, Math.abs(RR - eulerKoshiListH1.get(i).getIstY())));

            }

            TextArea textArea = new TextArea();
            textArea.setPrefColumnCount(50);
            textArea.setPrefRowCount(10);

            textArea.setText(res.toString());

            Scene scene1 = new Scene(textArea);
            Stage newWindow1 = new Stage();
            newWindow1.setTitle("Rynge-Roberg");
            newWindow1.setScene(scene1);
            newWindow1.show();
        });

        ryngekyt14.setOnMouseClicked(event ->{

            TableView<RyngeKytBbIvod> table = new TableView<>();

            TableColumn<RyngeKytBbIvod, Integer> iColumn = new TableColumn<>("i");
            TableColumn<RyngeKytBbIvod, Integer> hColumn = new TableColumn<>("h");
            TableColumn<RyngeKytBbIvod, Double> xColumn = new TableColumn<>("x");
            TableColumn<RyngeKytBbIvod, Double> yColumn = new TableColumn<>("y");
            TableColumn<RyngeKytBbIvod, Double> zColumn = new TableColumn<>("z");
            TableColumn<RyngeKytBbIvod, String> delta_zColumn = new TableColumn<>("delta_z");
            TableColumn<RyngeKytBbIvod, String> delta_yColumn = new TableColumn<>("delta_y");
            TableColumn<RyngeKytBbIvod, Double> istYColumn = new TableColumn<>("istY");
            TableColumn<RyngeKytBbIvod, String> accuracyColumn = new TableColumn<>("accuracy");

            iColumn.setCellValueFactory(new PropertyValueFactory<>("i"));
            hColumn.setCellValueFactory(new PropertyValueFactory<>("h"));
            xColumn.setCellValueFactory(new PropertyValueFactory<>("x"));
            yColumn.setCellValueFactory(new PropertyValueFactory<>("y"));
            zColumn.setCellValueFactory(new PropertyValueFactory<>("z"));
            delta_zColumn.setCellValueFactory(new PropertyValueFactory<>("delta_z"));
            delta_yColumn.setCellValueFactory(new PropertyValueFactory<>("delta_y"));
            istYColumn.setCellValueFactory(new PropertyValueFactory<>("istY"));
            accuracyColumn.setCellValueFactory(new PropertyValueFactory<>("accuracy"));

            ObservableList<RyngeKytBbIvod> list = FXCollections.observableArrayList();
            list.setAll(ryngeKytList);
            table.setItems(list);

            table.getColumns().addAll(iColumn, hColumn, xColumn, yColumn, zColumn, delta_zColumn, delta_yColumn, istYColumn, accuracyColumn);

            StackPane root = new StackPane();
            root.setPadding(new Insets(5));
            root.getChildren().add(table);

            Scene scene = new Scene(root);
            Stage newWindow = new Stage();

            newWindow.setTitle("Рунге-Кутты таблица");
            newWindow.setScene(scene);
            newWindow.show();

            /////RYNGE ROBERG

            StringBuilder res = new StringBuilder();

            for (int i = 0; i <= ryngeKytListH1.size() - 1; i++) {

                double F1 = ryngeKytListH1.get(i).getY();
                System.out.println(F1);
                double F2 = ryngeKytListH2.get(2*i).getY();
                System.out.println(F2);
                double RR = F2 + ((F2 - F1) / (Math.pow(2, 4) - 1));
                res.append(String.format("x = %.3f  y = %.3f    acc = %.3e\n", ryngeKytListH1.get(i).getX(), RR, Math.abs(RR - ryngeKytListH1.get(i).getIstY())));

            }

            TextArea textArea = new TextArea();
            textArea.setPrefColumnCount(50);
            textArea.setPrefRowCount(10);

            textArea.setText(res.toString());

            Scene scene1 = new Scene(textArea);
            Stage newWindow1 = new Stage();
            newWindow1.setTitle("Rynge-Kyt");
            newWindow1.setScene(scene1);
            newWindow1.show();
        });

        adams14.setOnMouseClicked(event ->{

            TableView<AdamsBbIvod> table = new TableView<>();

            TableColumn<AdamsBbIvod, Integer> iColumn = new TableColumn<>("i");
            TableColumn<AdamsBbIvod, Integer> hColumn = new TableColumn<>("h");
            TableColumn<AdamsBbIvod, Double> xColumn = new TableColumn<>("x");
            TableColumn<AdamsBbIvod, Double> yColumn = new TableColumn<>("y");
            TableColumn<AdamsBbIvod, Double> ydColumn = new TableColumn<>("yd");
            TableColumn<AdamsBbIvod, Double> yddColumn = new TableColumn<>("ydd");
            TableColumn<AdamsBbIvod, Double> zColumn = new TableColumn<>("z");
            TableColumn<AdamsBbIvod, String> delta_zColumn = new TableColumn<>("delta_z");
            TableColumn<AdamsBbIvod, String> delta_yColumn = new TableColumn<>("delta_y");
            TableColumn<AdamsBbIvod, Double> istYColumn = new TableColumn<>("istY");
            TableColumn<AdamsBbIvod, String> accuracyColumn = new TableColumn<>("accuracy");

            iColumn.setCellValueFactory(new PropertyValueFactory<>("i"));
            hColumn.setCellValueFactory(new PropertyValueFactory<>("h"));
            xColumn.setCellValueFactory(new PropertyValueFactory<>("x"));
            yColumn.setCellValueFactory(new PropertyValueFactory<>("y"));
            ydColumn.setCellValueFactory(new PropertyValueFactory<>("yd"));
            yddColumn.setCellValueFactory(new PropertyValueFactory<>("ydd"));
            zColumn.setCellValueFactory(new PropertyValueFactory<>("z"));
            delta_zColumn.setCellValueFactory(new PropertyValueFactory<>("delta_z"));
            delta_yColumn.setCellValueFactory(new PropertyValueFactory<>("delta_y"));
            istYColumn.setCellValueFactory(new PropertyValueFactory<>("istY"));
            accuracyColumn.setCellValueFactory(new PropertyValueFactory<>("accuracy"));

            ObservableList<AdamsBbIvod> list = FXCollections.observableArrayList();
            list.setAll(adamsList);
            table.setItems(list);

            table.getColumns().addAll(iColumn, hColumn, xColumn, yColumn,ydColumn,yddColumn, istYColumn, accuracyColumn);

            StackPane root = new StackPane();
            root.setPadding(new Insets(5));
            root.getChildren().add(table);

            Scene scene = new Scene(root);
            Stage newWindow = new Stage();

            newWindow.setTitle("Адамс таблица");
            newWindow.setScene(scene);
            newWindow.show();

            /////RYNGE ROBERG

            StringBuilder res = new StringBuilder();

            for (int i = 0; i <= adamsListH1.size() - 1; i++) {

                double F1 = adamsListH1.get(i).getY();
                double F2 = adamsListH2.get(2*i).getY();
                double RR = F2 + ((F2 - F1) / (Math.pow(2, 4) - 1));
                res.append(String.format("x = %.3f  y = %.3f    acc = %.3e\n", adamsListH1.get(i).getX(), RR, Math.abs(RR - adamsListH1.get(i).getIstY())));

            }

            TextArea textArea = new TextArea();
            textArea.setPrefColumnCount(50);
            textArea.setPrefRowCount(10);

            textArea.setText(res.toString());

            Scene scene1 = new Scene(textArea);
            Stage newWindow1 = new Stage();
            newWindow1.setTitle("Adams");
            newWindow1.setScene(scene1);
            newWindow1.show();
        });
    }
}