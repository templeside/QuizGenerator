package application;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JRadioButton;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.event.ActionEvent; 
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*; 
import javafx.stage.Stage; 
import javafx.scene.control.Alert.AlertType; 
  
public class practice extends Application { 
  
	private List<String> questions;
	private int totalNumQuestions;
	private Question currQuestion;
	private int currQuestionNum;


    // launch the application 
    public void start(Stage s){
    	List<Choice> ll = new ArrayList<Choice>();
//public Choice(boolean correctAnswer, String newChoice) {
    	
    	Choice firstchoice = new Choice(false, "1st choice");
    	ll.add(firstchoice);
    	Choice secondchoice = new Choice(true, "2nd choice");
    	ll.add(secondchoice);
    	
    	currQuestion = new Question("newMetadata", "newQuestion", "newTopic", "newImage",
    			ll, "right/newAnswer");

//    	totalNumQuestions = 3;
//    	questions.add(1, "one");
//    	questions.add(2, "two");
//    	questions.add(3,"three");
//    	
//    	for(int i=0; i<totalNumQuestions; i++) {
//			questions.get(i);
//		}
    	
    	BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 400, 400);

		s.setScene(scene);
		s.show();

		VBox box = new VBox();
		
		//need radio buttons for choices
		Button submit = new Button();
		submit.setText("Submit"); // NEED THE ACTION METHOD HERE : IF SUBMIT THEN WHAT?
		box.getChildren().add(submit);
		root.setBottom(box);
		
		
		RadioButton[] buttons = new RadioButton[20];
		ToggleGroup group2 = new ToggleGroup();
		
		for( int i=0; i<currQuestion.getChoices().size(); i++) {
			buttons[i] = new RadioButton(currQuestion.getChoices().get(i).choice);
			buttons[i].setToggleGroup(group2);
		}
		box.getChildren().add(group2);

		
		
		
		//Question label 
		// make this into separate textFlow
		TextFlow textFlow = new TextFlow();
		textFlow.setLayoutX(40);
		textFlow.setLayoutY(40);
		
		Text text1 = new Text(currQuestion.getQuestion());
		
		box.getChildren().add(text1);
		root.setCenter(text1);

				
    } 
  
    public static void main(String args[]) 
    { 
        // launch the application 
        launch(args); 
    } 
} 