import javafx.application.*;
import javafx.scene.Scene;
import javafx.stage.*;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.util.*;
import java.io.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ClaimsPaidBarChartEC extends Application {

	/* PROJECT: You will set these values based on the data file. */

	private int yAxisMin, yAxisMax; // the min and max of claims paid
	private String dataLabel1,dataLabel2, dataLabel3; // the year of the data

	private static final int TICK_INTERVAL = 1000; // used to customize the visual display

	/* PROJECT: Test with all three valid file names and one invalid file name. */
	//private final String fileName = "Disability_Insurance_2016.csv";
	// private final String fileName = "Disability_Insurance_2017.csv";
	// private final String fileName = "Disability_Insurance_2018.csv";
	// private final String fileName = "Disability_Insurance_BadFileName.csv";

	// use this file ONLY if completing the extra credit
	 private final String fileName = "Disability_Insurance_2016-2018_ExtraCredit.csv";

	@Override
	public void start(Stage stage) {

		/* creates the chart, x-axis, and y-axis */
		CategoryAxis xAxis = new CategoryAxis();
		NumberAxis yAxis = new NumberAxis();
		yAxis.setAutoRanging(false);
		BarChart barChart = new BarChart(xAxis, yAxis);
		barChart.setTitle("Disability Insurance Claims Paid");
		xAxis.setLabel("Month");
		yAxis.setLabel("Claims Paid");
		yAxis.setTickUnit(TICK_INTERVAL);

		/*
		 * PROJECT: This is the series for the chart. You will add data to this series.
		 */
		Series claimsPaidByMonth2016 = new Series();
		Series claimsPaidByMonth2017 = new Series();
		Series claimsPaidByMonth2018 = new Series();

		boolean success = fillData(claimsPaidByMonth2016, claimsPaidByMonth2017, claimsPaidByMonth2018);
		

		/*
		 * PROJECT: Once the fillData method is done, you have set the yAxisMin,
		 * yAxisMax, and dataLabel variables. This code now updates the display using
		 * those values.
		 */
		
		if (success) {
		int yAxisDisplayMin = getVisuallyAppealingAxisValue(yAxisMin);
		int yAxisDisplayMax = getVisuallyAppealingAxisValue(yAxisMax);
		yAxis.setLowerBound(yAxisDisplayMin - TICK_INTERVAL);
		yAxis.setUpperBound(yAxisDisplayMax + TICK_INTERVAL);
		claimsPaidByMonth2016.setName(dataLabel1);
		claimsPaidByMonth2017.setName(dataLabel2);
		claimsPaidByMonth2018.setName(dataLabel3);
		// adds the data series to the chart, creates a scene, sets the stage, and
		// shows!
		barChart.getData().addAll(claimsPaidByMonth2016, claimsPaidByMonth2017,claimsPaidByMonth2018);
		Scene scene = new Scene(barChart, 800, 600);
		stage.setScene(scene);
		stage.show();
		}
	}

	/*
	 * PROJECT: Your code goes in this method. I've added comments below to remind
	 * you of what this method needs to do.
	 */
	private boolean fillData(Series series1, Series series2, Series series3) {
		/* PROJECT: YOUR CODE GOES HERE! */

		//
		try (FileReader reader = new FileReader(new File(fileName));
				Scanner fileScan = new Scanner(reader)) {


			yAxisMax=Integer.MIN_VALUE;
			yAxisMin=Integer.MAX_VALUE;
			boolean firstLine = true;
			String monthLabel;
			int monthValue,monthValue2, monthValue3;

			while (fileScan.hasNext()) {

				String oneLine = fileScan.nextLine();

				Scanner lineScan = new Scanner(oneLine);
				lineScan.useDelimiter(",");
				if(firstLine){
					monthLabel = lineScan.next();
					dataLabel1 = lineScan.next();
					dataLabel2 = lineScan.next();
					dataLabel3 = lineScan.next();
					firstLine=false;
				}
				else{
					monthLabel = lineScan.next();
					monthValue = Integer.parseInt(lineScan.next());
					monthValue2 = Integer.parseInt(lineScan.next());
					monthValue3 = Integer.parseInt(lineScan.next());
					series1.getData().add(new Data(monthLabel, monthValue));
					series2.getData().add(new Data(monthLabel, monthValue2));
					series3.getData().add(new Data(monthLabel, monthValue3));
					
					if (monthValue >= yAxisMax) {
						yAxisMax = monthValue;
					}
					if (monthValue2 >= yAxisMax) {
						yAxisMax = monthValue2;
					}
					if (monthValue3 >= yAxisMax) {
						yAxisMax = monthValue3;
					}

					if (monthValue <= yAxisMin) {
						yAxisMin = monthValue;
					}
					if (monthValue2 <= yAxisMin) {
						yAxisMin = monthValue2;
					}
					if (monthValue3 <= yAxisMin) {
						yAxisMin = monthValue3;
					}
				}	
				

				
		

			}

		} catch (IOException ex) {
			System.out.println(ex.getMessage());
			Alert resultAlert = new Alert(AlertType.ERROR);
			resultAlert.setTitle("Error!");
			resultAlert.setHeaderText(null);
			String errorMessage = "File not found: " + fileName + "\nCannot run the program.";
			resultAlert.setContentText(errorMessage);
			
			
			resultAlert.showAndWait();
			return false;
		}
		return true;
		//

		// read in the data file
		// get the dataLabel (the year) from the first line of the file
		// use a Scanner to parse the remaining lines of the file, which each contain a
		// month name and value
		// create a Data object to add to the series for each month/value pair:
		// series.getData().add(new Data(monthLabel, monthValue));
		// calculate the min and max values seen and update yAxisMin and yAxisMax based
		// on these values
		// use exception handling to account for a bad file. if a file is invalid,
		// display an alert

		//return true; // this line here only so the code compiles; remove and updated as appropriate!
	}

	private int getVisuallyAppealingAxisValue(int value) {
		return (value + (TICK_INTERVAL - 1)) / TICK_INTERVAL * TICK_INTERVAL;
	}

	public static void main(String[] args) {
		launch(args);
	}
}