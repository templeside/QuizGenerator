//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION ////////////////////////
// Title:           ATEAM PROJECT                    							//
// Files:           AddQuestionFormNode, Choice, Main, QuestionNode             //
//                  Question, QuestionDatabase, QuestionDatabaseADT	        	//	
// Course:          CS 400, Spring 2018						     				//
// Author:          ATEAM106 (Andrew Lutkus, Chanwoong Jhon,                    //
//                            Elaheh Jabbarifard, Chaiyeen Oh, Sara Haines)     //
// Email:           alutkus@wisc.edu, cjhon@wisc.edu,                           //
//                  jabbarifard@wisc.edu, coh26@wisc.edu, schaines@wisc.edu     //
// Lecturer's Name: Andrew Kuemmel, Deb Deppeler								//
// Due Date : 5/2/2019 10pm														//
// 																				//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:			//
//   _X_ Write-up states that pair programming is allowed for this assignment.	//
//   _X_ We have both read and understand the course Pair Programming Policy.	//
//   _X_ We have registered our team prior to the team registration deadline.	//
//																				//
///////////////////////////// CREDIT OUTSIDE HELP ////////////////////////////////
//																				//
// Students who get help from sources other than their partner must fully 		//
// acknowledge and credit those sources of help here.  Instructors and TAs do 	//
// not need to be credited here, but tutors, friends, relatives, room mates, 	//
// strangers, and others do.  If you received no outside help from either type	//
//  of source, then please explicitly indicate NONE.							//
//																				//
// Persons:         X															//
// Online Sources:  X															//
//																				//
/////////////////////////////// 80 COLUMNS WIDE //////////////////////////////////
package application;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ChangeListener;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
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

	//

	private Button startButton;

	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setFullScreen(true);
			
			//for closing // HELPPP
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we) {
					ending_helper();
					Platform.exit();
				}
			});

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
					r1.setUserData(true);
					RadioButton r2 = new RadioButton("False");
					r2.setUserData(false);
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
					rr1.setUserData(true);
					RadioButton rr2 = new RadioButton("False"); 
					rr1.setUserData(false);
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
					rrr1.setUserData(true);
					RadioButton rrr2 = new RadioButton("False"); 
					rrr2.setUserData(false);
					ToggleGroup tg3 = new ToggleGroup(); 

					rrr1.setToggleGroup(tg3); 
					rrr2.setToggleGroup(tg3);
					rrr.getChildren().addAll(rrr1,rrr2); 

					questionBox.getChildren().addAll(rrr);

					Button addQuestionBtn = new Button("Add Question");
					addQuestionBtn.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {

							//                    		1. Here, get all the text from the respective textfields like question test, choices
							//                    		and everything else.
							//                    		2. Create a new Question object
							//                    		3. add the question object to the QuestionDatabase
						}
					});

					questionBox.getChildren().addAll(addQuestionBtn);


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

			questionDB = null; ////////// here

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
							questionDB.loadQuestionsFromJSON(file); ///////// here
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

			startButton = new Button();
			startButton.setText("Start the quiz");
			centerVB.getChildren().addAll(centerLabel, startButton);

			root.setCenter(centerVB);

			primaryStage.show();

			/////////
			EventHandler<ActionEvent> event4 = new EventHandler<ActionEvent>() { 
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

				}
			};
			////
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void displayAddQuestionForm() {

	}

	/**
	 * Once settings have been made: 
	 * User presses button to generate a quiz.
	 * The application shows one question at a time and accepts the users answer for the question.
	 *  Some questions may have corresponding images, that will appear in a 200x200 window in your GUI.  
	 *  If a question has no image, this window will be blank, or show a background color or image.
	 */
	private void displayQuiz() {

		Stage stageQuiz = new Stage();
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 400, 400);
		VBox centerVB = new VBox();
		centerVB.setPadding(new Insets(10, 50, 50, 50));
		centerVB.setSpacing(10);
		stageQuiz.setScene(scene);
		stageQuiz.show();



	}

	private void displayQuestion() {
		Stage s = new Stage();
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 400, 400);

		VBox centerVB = new VBox();
		centerVB.setPadding(new Insets(10, 50, 50, 50));
		centerVB.setSpacing(10);
		s.setScene(scene);
		s.show();

		VBox box = new VBox();
		box.setPadding(new Insets(10, 50, 50, 50));
		box.setSpacing(10);
		Label questionNumber = new Label("Current Question: " + this.currQuestionNum);
		QuestionNode q = new QuestionNode(this.currQuestion);
		box.getChildren().add(questionNumber);
		box.getChildren().add(q.getNode());

		//need radio buttons for choices
		Button submit = new Button();
		submit.setText("Submit");
		box.getChildren().add(submit);
		root.setCenter(box);


	}

	/**
	 * After each question is answered:
	 * The result is indicated to the user as "correct" or "incorrect" in some manner.
	 * Do not use only color to indicate correctness.
	 * @param qn
	 */
	private void displaySubmit(QuestionNode qn) {
		Stage s = new Stage();
		s.setFullScreen(true);

		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 400, 400);
		VBox centerVB = new VBox();
		centerVB.setPadding(new Insets(10, 50, 50, 50));
		centerVB.setSpacing(10);
		s.setScene(scene);
		s.show();

		HBox hb = new HBox();

		//consider CORRECT/INCORRECT
		TextField each_result = new TextField();

		if(qn.getChoices().equals(qn.getNode().getUserData())) { //??????
			each_result.setText("CORRECT!");
			each_result.setStyle("-fx-text-fill: green; -fx-font-size: 16px;");
			hb.getChildren().addAll(each_result);    
			hb.setSpacing(10);
		}
		else {
			each_result.setText("INCORRECT!");
			each_result.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
			hb.getChildren().addAll(each_result); 
			hb.setSpacing(10);
		}

	}

	/**
	 * After all quiz questions have been answered: 
	 * Shows the final quiz scores (# correct, # answered, percent correct). 
	 * Do not show correct answers. 
	 * Allow user to change settings and try a new quiz
	 */
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

	/** 
	 * When the user exits the program: 
	 * Ask the user for a file name to save all questions to a json file.   ******help
	 */
	private void ending() {

	}

	/**
	 * Provide two buttons on the form:  Save, or Exit without Save.
	 * Show an alert confirming their choice and providing a goodbye message. 
	 */
	private void ending_helper() { // this method was not stated on the TA's Draft
		Stage s = new Stage();
		s.setTitle("Are you Not Saving?"); 
		
		//1. SAVE 
		Button save = new Button("save"); 

		// create a tile pane 
		TilePane r = new TilePane(); 
		Alert a = new Alert(AlertType.NONE);  // ALERT
		
		// inner class action event 
		EventHandler<ActionEvent> event = new 
				EventHandler<ActionEvent>() { 
			public void handle(ActionEvent e) 
			{ 
				a.setAlertType(AlertType.CONFIRMATION); // type
				a.setContentText("You file have been saved! Have a great day!"); 
				a.show(); 
			} 
		}; 

		//2. "Exit without Save" button
		Button Exit_Button = new Button();
		Exit_Button.setText("Exit without Save");

		Alert alert2 = new Alert(AlertType.NONE);

		//inner class event handler
		EventHandler<ActionEvent> confirm = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				//alert type
				alert2.setAlertType(AlertType.CONFIRMATION);
				alert2.setContentText("Are you sure that you want to exit without saving? Have a great day!");
				alert2.show();
			}
		};

		// when button is pressed 
		save.setOnAction(event); 
		Exit_Button.setOnAction(confirm);

		// add button 
		r.getChildren().add(save); 
		r.getChildren().add(Exit_Button);

		// create a scene 
		Scene sc = new Scene(r, 100, 100); 

		// set the scene 
		s.setScene(sc); 
		s.show();
	}


	/**
	 * main method executing Main class 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
