
package it.polimi.ingsw.View;

        import javafx.application.Application;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Scene;
        import javafx.scene.Parent;
        import javafx.stage.Stage;


public class MainGui extends Application {
    @Override
    public void start(Stage firstStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/try.fxml"));
        firstStage.setTitle("LoginPage");
        firstStage.setScene(new Scene(root, 300, 275));
        firstStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }

}

