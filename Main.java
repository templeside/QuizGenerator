package application;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

import javax.swing.event.ChangeListener;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
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
            FileChooser fileChooser = new FileChooser(); 
            Desktop desktop = Desktop.getDesktop();
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
            EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() { 
                public void handle(ActionEvent e) 
                {
                	VBox questionBox = new VBox();
                	Label numQLabel = new Label("Enter your question: ");
                    TextField textField = new TextField();
                    HBox hb = new HBox();
                    hb.getChildren().addAll(numQLabel, textField);
                    hb.setSpacing(10);
                    questionBox.getChildren().addAll(hb);
                    //metadata
                    Label metaLabel = new Label("Enter your Metadata:");
                    TextField textField2 = new TextField();
                    HBox hb2 = new HBox();
                    hb2.getChildren().addAll(metaLabel, textField2);
                    hb2.setSpacing(10);
                    questionBox.getChildren().addAll(hb2);
                    //topic
                    Label topicLabel = new Label("Enter your topic:");
                    TextField textField3 = new TextField();
                    HBox hb3 = new HBox();
                    hb3.getChildren().addAll(topicLabel, textField3);
                    hb3.setSpacing(10);
                    questionBox.getChildren().addAll(hb3);
                    //image
                    Label imageLabel = new Label("Enter Image File name:");
                    TextField textField4 = new TextField();
                    HBox hb4 = new HBox();
                    hb4.getChildren().addAll(imageLabel, textField4);
                    hb4.setSpacing(10);
                    questionBox.getChildren().addAll(hb4);
                    //adding choice1
                    
                    Label choice1Label = new Label("Choice1:");
                    TextField textField5 = new TextField();
                    HBox hb5 = new HBox();
                    hb5.getChildren().addAll(choice1Label, textField5);
                    hb5.setSpacing(10);
                    questionBox.getChildren().addAll(hb5);
                    
                    // create radiobuttons
                    VBox r = new VBox(); 
                    RadioButton r1 = new RadioButton("True"); 
                    RadioButton r2 = new RadioButton("False"); 
                    ToggleGroup tg = new ToggleGroup(); 
                    
                    r1.setToggleGroup(tg); 
                    r2.setToggleGroup(tg);
                    r.getChildren().addAll(r1, r2);
                    
                    questionBox.getChildren().addAll(r);
          

                    
                    //add choice2
                    Label choice2Label = new Label("Choice2:");
                    TextField textField6 = new TextField();
                    HBox hb6 = new HBox();
                    hb6.getChildren().addAll(choice2Label, textField6);
                    hb6.setSpacing(10);
                    questionBox.getChildren().addAll(hb6);
                    
                    // create radiobuttons
                    VBox rr = new VBox();   
                    RadioButton rr1 = new RadioButton("True"); 
                    RadioButton rr2 = new RadioButton("False"); 
                    ToggleGroup tg2 = new ToggleGroup(); 
                    
                    rr1.setToggleGroup(tg2); 
                    rr2.setToggleGroup(tg2);
                    rr.getChildren().addAll(rr1, rr2); 

                    questionBox.getChildren().addAll(rr);
                    
                  //add choice3
                              
                    Label choice3Label = new Label("Choice3:");
                    TextField textField7 = new TextField();
                    HBox hb7 = new HBox();
                    hb7.getChildren().addAll(choice3Label, textField7);
                    hb7.setSpacing(10);
                    questionBox.getChildren().addAll(hb7);
                    
                    // create radiobuttons
                    VBox rrr = new VBox();   
                    RadioButton rrr1 = new RadioButton("True"); 
                    RadioButton rrr2 = new RadioButton("False"); 
                    ToggleGroup tg3 = new ToggleGroup(); 
                    
                    rrr1.setToggleGroup(tg3); 
                    rrr2.setToggleGroup(tg3);
                    rrr.getChildren().addAll(rrr1,rrr2); 

                    questionBox.getChildren().addAll(rrr);
                    
                      //add choice 4
                    Label choice4Label = new Label("Choice4:");
                    TextField textField8 = new TextField();
                    HBox hb8 = new HBox();
                    hb8.getChildren().addAll(choice4Label, textField8);
                    hb8.setSpacing(10);
                    questionBox.getChildren().addAll(hb8);
                    // create radiobuttons
                    VBox rrrr = new VBox();   
                    RadioButton rrrr1 = new RadioButton("True"); 
                    RadioButton rrrr2 = new RadioButton("False"); 
                    ToggleGroup tg4 = new ToggleGroup(); 
                    
                    rrrr1.setToggleGroup(tg4); 
                    rrrr2.setToggleGroup(tg4);
                    rrrr.getChildren().addAll(rrrr1,rrrr2); 

                    questionBox.getChildren().addAll(rrrr);
                    
                    //add choice 5
                    Label choice5Label = new Label("Choice5:");
                    TextField textField9 = new TextField();
                    HBox hb9 = new HBox();
                    hb9.getChildren().addAll(choice5Label, textField9);
                    hb9.setSpacing(10);
                    questionBox.getChildren().addAll(hb9);
                    //radio buttons
                    VBox rrrrr = new VBox();   
                    RadioButton rrrrr1 = new RadioButton("True"); 
                    RadioButton rrrrr2 = new RadioButton("False"); 
                    ToggleGroup tg5 = new ToggleGroup(); 
                    
                    rrrrr1.setToggleGroup(tg5); 
                    rrrrr2.setToggleGroup(tg5);
                    rrrrr.getChildren().addAll(rrrrr1,rrrrr2); 

                    questionBox.getChildren().addAll(rrrrr);
                   
                    Button addQuestionBtn = new Button("Add Question");
                    addQuestionBtn.setOnAction(new EventHandler<ActionEvent>() {
                    	
                    	@Override
                    	public void handle(ActionEvent event) {
                    		
//                    		1. Here, get all the text from the respective textfields like question test, choices
//                    		and everything else.
//                    		2. Create a new Question object
//                    		3. add the question object to the QuestionDatabase
                    		//Scene backScene = new Scene(root, 400, 400);                	
                        	primaryStage.setScene(scene);
                            primaryStage.show();
                    		
                    	}
                    });
                    //back button
                    Button backBtn = new Button();
                    backBtn.setText("Back to Main Page");
                    leftVB.getChildren().add(backBtn);
                    backBtn.setOnAction(new EventHandler<ActionEvent>() {
                    	@Override
                    	public void handle(ActionEvent event) {
                    		primaryStage.setScene(scene);
                            primaryStage.show();
                    	}
                    		
                   });
                    
                    questionBox.getChildren().addAll(addQuestionBtn);
                    questionBox.getChildren().addAll(backBtn);
                    
                    
//                    1. create a new button for saving the question
//                    2. do the following steps in the button onclick event handler
//                    3. get all the question details from the question box fields - TextField
//                    4. Create a new Question object
//                    5. Add Question object to QuestionDatabase
//                    6. At the end we need a button to go back to the main page
                    
                    Scene questionScene = new Scene(questionBox, 400, 400);                	
                	primaryStage.setScene(questionScene);
                    primaryStage.show();
               }
            };
         // when button is pressed 
            btn1.setOnAction(event1); 

            Button btn2 = new Button();
            btn2.setText("Load a json file");
            leftVB.getChildren().add(btn2);
            EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() { 
                public void handle(ActionEvent e) 
                {
                	
                	File file = fileChooser.showOpenDialog(primaryStage);
                	String output = "file path";
                	StringBuffer stringBuffer = new StringBuffer();
                    if (file != null) {
                    	try {
                    		FileReader fileReader = new FileReader(file);
                			BufferedReader bufferedReader = new BufferedReader(fileReader);
                			
                			String line;
                			while ((line = bufferedReader.readLine()) != null) {
                				stringBuffer.append(line);
                				stringBuffer.append("\n");
                				System.out.println(line);
                			}
                			                           
                			fileReader.close();
                    	}
                    	catch(Exception ex)
                    	{
                    		ex.printStackTrace();
                    	}
                    }
                	VBox jsonBox = new VBox();
                	Label newJson = new Label();
                	newJson.setText(stringBuffer.toString());

                	jsonBox.getChildren().add(newJson);
                	Button backBtn = new Button();
                    backBtn.setText("Back to Main Page");
                    leftVB.getChildren().add(backBtn);
                    backBtn.setOnAction(new EventHandler<ActionEvent>() {
                    	@Override
                    	public void handle(ActionEvent event) {
                    		primaryStage.setScene(scene);
                            primaryStage.show();
                    	}
                    		
                   });
                	jsonBox.getChildren().addAll(backBtn);
                	Scene jsonScene = new Scene(jsonBox, 400, 400);                	
                	primaryStage.setScene(jsonScene);
                    primaryStage.show();
               }
            };
         // when button is pressed 
            btn2.setOnAction(event2); 

            Button btn3 = new Button();
            btn3.setText("Save the current questions to a json file");
            leftVB.getChildren().add(btn3);
            EventHandler<ActionEvent> event3 = new EventHandler<ActionEvent>() { 
                public void handle(ActionEvent e) 
                {
                	VBox questionBox = new VBox();
                	Label newQuestion = new Label();
                	newQuestion.setText("Enter the question");
                	questionBox.getChildren().add(newQuestion);
                	Scene questionScene = new Scene(questionBox, 400, 400);                	
                	primaryStage.setScene(questionScene);
                    primaryStage.show();
               }
            };
         // when button is pressed 
            btn3.setOnAction(event3); 

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
