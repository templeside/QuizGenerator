package application;

import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class Main extends Application {
    private QuestionDatabase questionDB;
    private List<Question> questions;
    private Question currQuestion;
    private int currQuestionNum;
    private int totalNumQuestions;
    private int numIncorrect;

    @Override
    public void start(Stage primaryStage) {
        try {
            primaryStage.setFullScreen(true);
            // top
            Label topLabel = new Label("Welcome to Quiz Generator");
            topLabel.setFont(Font.font("Amble CN", FontWeight.BOLD, 20));
            BorderPane root = new BorderPane();
            root.setTop(topLabel);
            BorderPane.setAlignment(topLabel, Pos.CENTER);
            Scene scene = new Scene(root, 400, 400);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();

            // left
            VBox leftVB = new VBox();
            leftVB.setPadding(new Insets(10, 50, 50, 50));
            leftVB.setSpacing(10);

            Label leftVBLabel =
                new Label("Would you like to add a question, load file, or save the current file?");
            leftVBLabel.setFont(Font.font("Amble CN", FontWeight.BOLD, 16));
            leftVB.getChildren().add(leftVBLabel);

            Button btn1 = new Button();
            btn1.setText("Add a question");
            leftVB.getChildren().add(btn1);

            Button btn2 = new Button();
            btn2.setText("Load a json file");
            leftVB.getChildren().add(btn2);

            Button btn3 = new Button();
            btn3.setText("Save the current questions to a json file");
            leftVB.getChildren().add(btn3);

            root.setLeft(leftVB);

            // right
            VBox rightVB = new VBox();
            rightVB.setPadding(new Insets(10, 50, 50, 50));
            rightVB.setSpacing(10);

            Label rightVBLabel = new Label("User Settings");
            rightVBLabel.setFont(Font.font("Amble CN", FontWeight.BOLD, 16));
            rightVB.getChildren().add(rightVBLabel);

            Label numQLabel = new Label("Enter the number of questions you would like to answer:");
            TextField textField = new TextField();
            HBox hb = new HBox();
            hb.getChildren().addAll(numQLabel, textField);
            hb.setSpacing(10);
            rightVB.getChildren().addAll(hb);

            Label topicLabel = new Label("Topics:");
            ComboBox<String> topicBox = new ComboBox<String>(
                FXCollections.observableArrayList("Graphs", "Hash Tables", "Linux", "Trees"));
            rightVB.getChildren().addAll(topicLabel, topicBox);

            root.setRight(rightVB);

            // Center
            VBox centerVB = new VBox();
            centerVB.setPadding(new Insets(10, 50, 50, 50));
            centerVB.setSpacing(10);

            Label centerLabel = new Label("Press Start to begin the quiz");
            centerLabel.setFont(Font.font("Amble CN", FontWeight.BOLD, 16));

            Button startButton = new Button();
            startButton.setText("Start the quiz");
            centerVB.getChildren().addAll(centerLabel, startButton);

            root.setCenter(centerVB);

            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayAddQuestionForm() {

    }

    private void displayQuiz() {

    }

    private void displayQuestion() {

    }

    private void displaySubmit(QuestionNode qn) {

    }

    private void displayResults() {
        Stage s = new Stage();
        s.setFullScreen(true);

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 400, 400);
        VBox centerVB = new VBox();
        centerVB.setPadding(new Insets(10, 50, 50, 50));
        centerVB.setSpacing(10);
        s.setScene(scene);
        s.show();

        // correct label
        Label correct =
            new Label("Number of Correct Answers: " + (this.totalNumQuestions - this.numIncorrect));
        correct.setFont(Font.font("Amble CN", FontWeight.BOLD, 16));
        centerVB.getChildren().add(correct);
        // total question label
        Label totalQuestions = new Label("Number of Questions: " + this.totalNumQuestions);
        totalQuestions.setFont(Font.font("Amble CN", FontWeight.BOLD, 16));
        centerVB.getChildren().add(totalQuestions);
        // percent label
        double percent =
            (this.totalNumQuestions - this.numIncorrect) / (double) (this.totalNumQuestions) * 100;
        Label score = new Label("Score: " + percent + "%");
        score.setFont(Font.font("Amble CN", FontWeight.BOLD, 16));
        centerVB.getChildren().add(score);

        root.setCenter(centerVB);
        s.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
