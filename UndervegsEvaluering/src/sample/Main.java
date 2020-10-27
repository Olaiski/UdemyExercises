package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.*;
import javafx.stage.Stage;

/*
 * Lag eit spørreskjema for Undervegsevaluering.
 * Bruk passande panel for å få det design du ynskjer.
 * Bruk Label til å stille spørsmål,
 * TextField for ei-lines input og TextArea for fleir-liners input.
 * Programmet skal ikkje kunne lese inn data frå desse felta - det kjem i seinare kapittel.
 *
 */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        //BorderPane root = new BorderPane();
        GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setPrefSize(700,700);






        Font font = Font.font("Courier", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 15);
        Font sFont = Font.font("Courier", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 25);

        /*root.setTop(getHbox());
        root.setLeft(getGrid());
        root.setCenter(getCenterGrid());*/

        root.add(getHbox(),0,0);
        root.add(getGrid(),0,1);
        root.add(getCenterGrid(),0,3);
        root.add(bottomBox(),0,4);




        Scene scene = new Scene(root);


        primaryStage.setTitle("Undervegseval");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private HBox getHbox(){
        Font sFont = Font.font("Courier", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 25);
        HBox hBox = new HBox(20);
        hBox.setPadding(new Insets(15));
        hBox.setStyle("-fx-border-color: black");

        hBox.setStyle("-fx-background-color: lightblue");
        Label label = new Label("Undervegsevaluering");
        label.setAlignment(Pos.TOP_CENTER);
        label.setFont(sFont);


        hBox.getChildren().add(label);

        return hBox;
    }

    private GridPane getGrid(){

        Font font = Font.font("Courier", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20);
        Font font2 = Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.REGULAR, 15);
        GridPane pane = new GridPane();



        pane.setVgap(5.5);
        pane.setHgap(5.5);

        Label label = new Label("Studentinfo");
        label.setFont(font);
        label.setPadding(new Insets(20,0,20,0));


        Label navn = new Label("Fornavn:");
        navn.setFont(font2);
        pane.setAlignment(Pos.TOP_LEFT);

        pane.add(label,0,0);

        pane.add(navn, 0,1);
        pane.add(new TextField(),1,1);

        navn = new Label("Etternavn:");
        navn.setFont(font2);
        pane.add(navn,0,2);
        pane.add(new TextField(),1,2);

        navn = new Label("StudentNr:");
        navn.setFont(font2);
        pane.add(navn,0,3);
        pane.add(new TextField(), 1,3);

        return pane;

    }

    private GridPane getCenterGrid(){

        Font font = Font.font("Courier", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20);
        Font font2 = Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.REGULAR, 17);
        GridPane pane = new GridPane();



        pane.setVgap(5.5);
        pane.setHgap(5.5);

        pane.setAlignment(Pos.CENTER_LEFT);

        Label label = new Label("Evaluering:");
        label.setFont(font);
        label.setPadding(new Insets(20,0,20,0));
        pane.add(label,0,1);

        Label sprsml = new Label("a) Kommenter hvordan du vurderer din egen innsats i undervisningen til nå. Begurnn svaret ditt.");
        sprsml.setFont(font2);
        pane.add(sprsml,0,2);

        TextArea textArea = new TextArea();
        textArea.setPrefColumnCount(20);
        textArea.setPrefRowCount(2);
        pane.add(textArea,0,3);

        sprsml = new Label("b) Er det noe ved undervisningen du opplever som spesielt lærerikt? I så fall hva?");
        sprsml.setFont(font2);
        pane.add(sprsml,0,4);

        TextArea textArea1 = new TextArea();
        textArea1.setPrefColumnCount(20);
        textArea1.setPrefRowCount(2);
        pane.add(textArea1,0,5);


        sprsml = new Label("c) Er det noe ved undervisningen du opplever som vanskelig? I så fall hva?");
        sprsml.setFont(font2);
        pane.add(sprsml,0,6);

        TextArea textArea2 = new TextArea();
        textArea2.setPrefColumnCount(20);
        textArea2.setPrefRowCount(2);
        pane.add(textArea2,0,7);


        sprsml = new Label("d) Er det noen temaer i studielitteraturen du kunne tenke deg å få nærmere belyst i undervisningen?");
        sprsml.setFont(font2);
        pane.add(sprsml,0,8);

        TextArea textArea3 = new TextArea();
        textArea3.setPrefColumnCount(20);
        textArea3.setPrefRowCount(2);
        pane.add(textArea3,0,9);

        return pane;
    }

    private HBox bottomBox(){

        Font sFont = Font.font("Courier", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 25);
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(20));

        hBox.setAlignment(Pos.BOTTOM_RIGHT);

        Button button = new Button("Send inn?");


        hBox.getChildren().addAll(button);

        return hBox;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
