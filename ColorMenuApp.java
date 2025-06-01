package Bank;

/**
 * This UI was a little challenging in deciding how to accomplish the randomization
 * I chose a true randomization instead of building a list of pre-defined colors to cycle through
 * used preset red and blues at 80 to get nicer looking green hues
 * had to read some about centering with JavaFx.geometry. 
 * addAll saves tons of code space 
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;

public class ColorMenuApp extends Application {

    private TextArea textArea;
    private VBox mainPane;
    private Random rand = new Random();

    @Override
    public void start(Stage primaryStage) {
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Make your choice");

        MenuItem showDateTime = new MenuItem("It is but a Construct");
        MenuItem saveToFile = new MenuItem("Record the Past");
        MenuItem changeColor = new MenuItem("A different View");
        MenuItem exit = new MenuItem("Retreat");

        menu.getItems().addAll(showDateTime, saveToFile, changeColor, exit);
        menuBar.getMenus().add(menu);

        textArea = new TextArea();
        textArea.setPrefHeight(50);

        mainPane = new VBox();
        mainPane.getChildren().add(menuBar);
        
        StackPane centerpane = new StackPane();
        centerpane.setPrefHeight(350);
        centerpane.getChildren().add(textArea);
        textArea.setPrefSize(200, 100);
        
        StackPane.setMargin(textArea, new javafx.geometry.Insets(40));
        mainPane.getChildren().add(centerpane);

        Scene scene = new Scene(mainPane, 500, 400);

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

        // Change background color to a new random green hue
        changeColor.setOnAction(e -> {
            Color randomGreen = getRandomGreenHue();
            mainPane.setBackground(new Background(new BackgroundFill(randomGreen, null, null)));

            String hexColor = colorToHex(randomGreen);
            changeColor.setText("Change Perspective from Green Hue: " + hexColor);
            textArea.appendText("Background changed to color: " + hexColor + "\n");
        });

        exit.setOnAction(e -> primaryStage.close());

        primaryStage.setTitle("Four Options");
        primaryStage.setScene(scene);
        primaryStage.show();
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