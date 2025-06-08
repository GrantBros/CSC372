package Bank;

/**
 * This UI was a little challenging in deciding how to accomplish the randomization
 * I chose a true randomization instead of building a list of pre-defined colors to cycle through
 * used preset red and blues at 80 to get nicer looking green hues
 * had to read some about centering with JavaFx.geometry. 
 * addAll saves tons of code space 
 */
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;

/*Broke down start function into multiple individual functions for modularity.
 * This provides easier manipulation of each piece of the GUI without impacting initiation of GUI.
 * This will allow troubleshooting of individual pieces of the GUI
 */
public class ColorMenuApp extends Application {

    private TextArea textArea;
    private VBox mainPane;
    private Random rand = new Random();
    private MenuItem showDateTime;
    private MenuItem saveToFile;
    private MenuItem changeColor;
    private MenuItem exit;

    @Override
    public void start(Stage primaryStage) {
    	//break down into smaller modular functions
    	textArea = buildText();
        MenuBar menuBar = buildMenuBar();
        StackPane centerPane = buildCPane();
        
        mainPane = new VBox(menuBar, centerPane);
        Scene scene = new Scene(mainPane, 500, 400); 
   
        menuActions(primaryStage);
        
        primaryStage.setTitle("Four Horsemen");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

private TextArea buildText() {
        	
	TextArea area = new TextArea();
	area.setPrefHeight(50);
	area.setPrefSize(250, 150);
	return area;
	}
private MenuBar buildMenuBar() {
    MenuBar menuBar = new MenuBar();
    Menu menu = new Menu("Make your choice");

    showDateTime = new MenuItem("It is but a Construct");
    saveToFile = new MenuItem("Record the Past");
    changeColor = new MenuItem("A different View");
    exit = new MenuItem("Retreat");

    menu.getItems().addAll(showDateTime, saveToFile, changeColor, exit);
    menuBar.getMenus().add(menu);
    return menuBar;
}
private StackPane buildCPane() {
	//setting pane dimensions and text
    StackPane center = new StackPane(textArea);
    center.setPrefHeight(350);
    StackPane.setMargin(textArea, new Insets(40));
    return center;
}
private void menuActions(Stage primaryStage) {
	//event handling function
    showDateTime.setOnAction(e -> {
        String dateTime = LocalDateTime.now().toString();
        textArea.appendText("Current Date & Time: " + dateTime + "\n");
    });
    saveToFile.setOnAction(e -> {
        try (FileWriter writer = new FileWriter("log.txt")) {
            writer.write(textArea.getText());
            textArea.appendText("Saved to log.txt (overwritten)\n");
        } catch (IOException ex) {
            textArea.appendText("Error writing to file.\n");
        }
    });

    changeColor.setOnAction(e -> {
        Color randomGreen = getRandomGreenHue();
        mainPane.setBackground(new Background(new BackgroundFill(randomGreen, null, null)));

        String hexColor = colorToHex(randomGreen);
        changeColor.setText("Change Perspective from Green Hue: " + hexColor);
        textArea.appendText("Background changed to color: " + hexColor + "\n");
    });

    exit.setOnAction(e -> primaryStage.close());
}

    // Helper: generate random greenish hue
    private Color getRandomGreenHue() {
    	 int r = rand.nextInt(80);             
         int g = 150 + rand.nextInt(106);      
         int b = rand.nextInt(80);             
         return Color.rgb(r, g, b);
     }

    // Helper: convert Color to HEX
    private String colorToHex(Color color) {
        int r = (int) (color.getRed() * 255);
        int g = (int) (color.getGreen() * 255);
        int b = (int) (color.getBlue() * 255);
        return String.format("#%02X%02X%02X", r, g, b);
    }

    public static void main(String[] args) {
        launch(args);
    }
}