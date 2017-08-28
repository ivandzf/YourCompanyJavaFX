package appproject08;

import function.navigation;
import java.io.PrintWriter;
import java.io.StringWriter;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AppProject08 extends Application {
    
    private double x;
    private double y;
    navigation nav = new navigation();
    
    @Override
    public void start(Stage stage)  {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(nav.getLogin()));
            stage.setTitle("Login");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initStyle(StageStyle.TRANSPARENT);
            root.setOnMousePressed(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event){
                    x=event.getSceneX();
                    y=event.getSceneY();
                }
            });

            root.setOnMouseDragged(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event){
                    stage.setX(event.getScreenX()-x);
                    stage.setY(event.getScreenY()-y);
                }
            });

            Scene scene = new Scene(root);
            scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
            stage.getIcons().add(nav.applicationIcon);
            stage.centerOnScreen();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            showExceptionDialog(e);
        }
        
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public static void showExceptionDialog(Exception e) {
    Alert alert = new Alert(Alert.AlertType.ERROR);

    alert.setTitle("Exception Dialog");
    alert.setHeaderText("An error occurred:");

    String content = "Error: ";
    if (null != e) {
        content += e.toString() + "\n\n";
    }

    alert.setContentText(content);

    Exception ex = new Exception(e);

    //Create expandable Exception.
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    ex.printStackTrace(pw);

    String exceptionText = sw.toString();

    //Set up TextArea
    TextArea textArea = new TextArea(exceptionText);
    textArea.setEditable(false);
    textArea.setWrapText(true);


    textArea.setPrefHeight(600);
    textArea.setPrefWidth(800);


    //Set expandable Exception into the dialog pane.
    alert.getDialogPane().setExpandableContent(textArea);


    alert.showAndWait();
}
    
}
