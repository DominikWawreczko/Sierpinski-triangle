package mandelbrot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;





public class CanvasFX extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //primaryStage.setTitle("Hello World");
        //CzyPunktySaWspoliniowe test=new CzyPunktySaWspoliniowe(1.0 ,2.0,3.0,6.0,0.0,4.7);
        primaryStage.setScene(new Scene(root));//bez długości i wielkości automatycznie dobiera rozmiar
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
