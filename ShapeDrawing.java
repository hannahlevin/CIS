import javafx.application.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.stage.*;

public class ShapeDrawing extends Application {

	private int clicks = 0, rectangles = 0;
	private int OUTLINE_WIDTH = 500, OUTLINE_HEIGHT = 400;
	private double startX, startY;
	private Pane drawingBox;
	private Rectangle outlineBox;
	private RadioButton redButton, yellowButton, blueButton, thinBorderButton, thickBorderButton;
	private CheckBox fillCheckBox;
	private Button clearButton;
	private boolean drawingTempRectangle = false;

	public void start(Stage primaryStage) {

		outlineBox = new Rectangle(0, 0, OUTLINE_WIDTH, OUTLINE_HEIGHT);
		outlineBox.setFill(Color.TRANSPARENT);
		outlineBox.setStroke(Color.BLACK);
		outlineBox.setStrokeWidth(2);

		redButton = new RadioButton("Red");
		redButton.setSelected(true);
		yellowButton = new RadioButton("Yellow");
		blueButton = new RadioButton("Blue");

		ToggleGroup colorGroup = new ToggleGroup();
		redButton.setToggleGroup(colorGroup);
		yellowButton.setToggleGroup(colorGroup);
		blueButton.setToggleGroup(colorGroup);

		thinBorderButton = new RadioButton("Thin Border");
		thinBorderButton.setSelected(true);
		thickBorderButton = new RadioButton("Thick Border");

		ToggleGroup borderGroup = new ToggleGroup();
		thinBorderButton.setToggleGroup(borderGroup);
		thickBorderButton.setToggleGroup(borderGroup);

		fillCheckBox = new CheckBox("Fill");

		clearButton = new Button("Clear");
		clearButton.setOnAction(this::handleClear);

		drawingBox = new Pane(outlineBox);

		HBox colorButtonsBox = new HBox(redButton, yellowButton, blueButton);
		colorButtonsBox.setAlignment(Pos.CENTER);
		colorButtonsBox.setSpacing(5);
		colorButtonsBox.setPadding(new Insets(0, 20, 0, 20));

		HBox thicknessButtonsBox = new HBox(thinBorderButton, thickBorderButton, fillCheckBox);
		thicknessButtonsBox.setAlignment(Pos.CENTER);
		thicknessButtonsBox.setSpacing(5);

		HBox clearButtonBox = new HBox(clearButton);
		clearButtonBox.setAlignment(Pos.CENTER);

		VBox mainBox = new VBox(drawingBox, colorButtonsBox, thicknessButtonsBox, clearButtonBox);

		mainBox.setSpacing(10);

		drawingBox.setOnMouseClicked(this::handleClick);

		drawingBox.setOnMouseMoved(this::handleMovement);

		Scene scene = new Scene(mainBox, 500, 500);
		primaryStage.setTitle("Rectangles!");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

	}

	private void handleClick(MouseEvent event) {

		clicks++;

		if (clicks % 2 == 0) {

			drawingBox.getChildren().remove(rectangles);

			rectangles--;

			drawingTempRectangle = false;

			drawARectangle(startX, startY, event.getX(), event.getY());

			clicks = 0;

		} else {

			startX = event.getX();

			startY = event.getY();

		}

	}

	private void handleMovement(MouseEvent event) {

		if (clicks == 1) {

			if (drawingTempRectangle) {

				drawingBox.getChildren().remove(rectangles);

				rectangles--;

			}

			drawingTempRectangle = true;

			drawARectangle(startX, startY, event.getX(), event.getY());

		}

	}

	private void handleClear(ActionEvent event) {

		drawingBox.getChildren().clear();

		drawingBox.getChildren().add(outlineBox);

		clicks = 0;

		rectangles = 0;

		drawingTempRectangle = false;

	}

	private void drawARectangle(double x, double y, double finalX, double finalY) {

		if (finalX > OUTLINE_WIDTH) {

			finalX = OUTLINE_WIDTH;

		}

		if (finalY > OUTLINE_HEIGHT) {

			finalY = OUTLINE_HEIGHT;

		}

		double width = finalX - startX;
		double height = finalY - startY;

		if (width < 0) {

			x = finalX;

		}

		if (height < 0) {

			y = finalY;

		}

		width = Math.abs(width);
		height = Math.abs(height);

		Rectangle rectangle = new Rectangle(x, y, width, height);

		Color color = null;

		if (redButton.isSelected()) {

			color = Color.RED;

		}

		if (yellowButton.isSelected()) {

			color = Color.YELLOW;

		}

		if (blueButton.isSelected()) {

			color = Color.BLUE;

		}

		rectangle.setStroke(color);

		if (thinBorderButton.isSelected()) {

			rectangle.setStrokeWidth(2);

		}

		if (thickBorderButton.isSelected()) {

			rectangle.setStrokeWidth(10);

		}

		rectangle.setFill(fillCheckBox.isSelected() ? color : Color.TRANSPARENT);

		drawingBox.getChildren().add(rectangle);

		rectangles++;

	}

	public static void main(String[] args) {

		launch(args);

	}

}