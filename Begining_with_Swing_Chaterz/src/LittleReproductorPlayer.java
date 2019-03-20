import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class LittleReproductorPlayer extends Application {

    @Override
    public void start(Stage primaryStage ) {
        TextArea info = new TextArea();
        //Button btn = new Button();
        Button play = new Button();
        Button stop = new Button();
        info.setText("Play your Favourite Song Here!!");
        //btn.setText("Say 'Hello World'");
        play.setText("Play");

        stop.setText("Stop");
        stop.fire();
        stop.cancelButtonProperty();
        stop.getAccessibleRoleDescription();
        stop.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("No Mp3 FiLe Found , Contact the best Webmaster Chaterz");
            }
        });
        play.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("No Mp3 FiLe Found , Contact the best Webmaster Chaterz");
            }
        });

        StackPane root = new StackPane();
        StackPane root1 = new StackPane();
        StackPane root2 = new StackPane();
        root.getChildren().add(play);
        root1.getChildren().add(stop);
        root2.getChildren().add(info);

        //Scene scene = new Scene(root, 250, 250);
       // Scene scene2 = new Scene(root2, 200,300);
        Scene scene1 = new Scene(root, 300,300);

        primaryStage.setTitle("Music Player By Chaterz ");
        primaryStage.alwaysOnTopProperty();

        primaryStage.setScene(scene1);
       // primaryStage.setScene(scene2);
        primaryStage.getIcons();
        Window owner = primaryStage.getOwner();
        primaryStage.getStyle();
        primaryStage.getFullScreenExitKeyCombination();
        primaryStage.show();
        stop.getAccessibleRoleDescription();
        play.getAccessibleRoleDescription();
        play.cancelButtonProperty();
        play.applyCss();
        root2.getCursor();
    }
    public static void main(String[] args) {
        launch(args);
    }
}