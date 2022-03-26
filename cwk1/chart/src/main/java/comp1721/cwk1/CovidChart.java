package comp1721.cwk1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class CovidChart extends Application {
    public void start(Stage stage) throws IOException {
        // create the dataset
        CovidDataset dataset = new CovidDataset();
        // read the data from the file
        try {
            dataset.readDailyCases("../datafiles/2020-active.csv");
        }catch (FileNotFoundException e){
            System.out.println("No such file");
        }
        int active = 0;
        // create a array list to store the sum of the cases
        // create the lower bound and upperbound
        int lowerBound = dataset.getRecord(0).getDate().getDayOfYear();
        int upperBound = dataset.getRecord(dataset.size()-1).getDate().getDayOfYear();
        ArrayList<Integer> sum = new ArrayList<Integer>();
        for(int i = 0; i < dataset.size(); i++){
            active = 0;
            active += dataset.getRecord(i).getStaffCases();
            active += dataset.getRecord(i).getStudentCases();
            active += dataset.getRecord(i).getOtherCases();
            sum.add(active);
        }

        // define the x axis
        NumberAxis xAxis = new NumberAxis(lowerBound, upperBound, 5);
        xAxis.setLabel("Day of Year");

        // define the y axis
        NumberAxis yAxis = new NumberAxis(0, 850, 50);
        yAxis.setLabel("Active cases");

        // create chart object, pass x axis and y axis
        LineChart linechart = new LineChart(xAxis, yAxis);

        // define the data needed for the chart
        XYChart.Series series = new XYChart.Series();
        series.setName("Active Coronavirus Cases, University of Leeds");

        // add data
        for(int i = 0; i < sum.size(); i ++){
            series.getData().add(new XYChart.Data(lowerBound + i, sum.get(i)));
        }

        // pass the data to the chart
        linechart.getData().add(series);

        // create scene object
        Scene scene = new Scene(linechart, 600, 400);

        // create the title of the scene
        stage.setTitle("Active Coronavirus Cases, University of Leeds");

        // show the window
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String args[]){
        launch(args);
    }
}