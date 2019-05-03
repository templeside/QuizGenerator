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
import javafx.scene.Group;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;


public class Main extends Application {
    private QuestionDatabase questionDB;
    private List<Question> questions;
    private Question currQuestion;
    private int currQuestionNum;
    private int totalNumQuestions;
    private int numIncorrect;
    private String topic;
    private ComboBox<String> grabTopic;
    
    Stage primaryStage;
    Scene scene, questionScene;
    
    //chanwoong jhon change
    
    @Override
     public void start(Stage primaryStage) {
        try {
            //initialize all of the fields
        	this.primaryStage = primaryStage;
        	questions = null;
        	questionDB = new QuestionDatabase();
        	grabTopic = new ComboBox<String>();
        	currQuestion = null;
        	currQuestionNum = 0;
        	totalNumQuestions = 0;
        	numIncorrect = 0;
            primaryStage.setFullScreen(true);
		
		//for closing	
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		public void handle(WindowEvent we) {
			ending_helper();
		}
		});
            // top
            FileChooser fileChooser = new FileChooser(); 
            Desktop desktop = Desktop.getDesktop();
            Label topLabel = new Label("Welcome to Quiz Generator");//top of screen
            topLabel.setFont(Font.font("Amble CN", FontWeight.BOLD, 20));
            BorderPane root = new BorderPane();
            root.setTop(topLabel);
            BorderPane.setAlignment(topLabel, Pos.CENTER);
            scene = new Scene(root, 400, 400);
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
            //add a question
            Button btn1 = new Button();
            btn1.setText("Add a question");
            leftVB.getChildren().add(btn1);
            EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() { 
                public void handle(ActionEvent e) 
                {
                	displayAddQuestionForm();//calls method to show new scene
                }
            };
         // when button is pressed 
            btn1.setOnAction(event1); 

            //loading a json file
            Button btn2 = new Button();
            btn2.setText("Load a json file");
            leftVB.getChildren().add(btn2);
            EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() { 
                public void handle(ActionEvent e) 
                {
                	//get file
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
                	//button to go back to main page
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
            //save to a json file
            Button btn3 = new Button();
            btn3.setText("Save the current questions to a json file");
            leftVB.getChildren().add(btn3);
            EventHandler<ActionEvent> event3 = new EventHandler<ActionEvent>() { 
                public void handle(ActionEvent e) 
                {
                	//file name
                	VBox questionBox = new VBox();
                	Label newQuestion = new Label();
                	newQuestion.setText("Enter the file name: ");
                	questionBox.getChildren().add(newQuestion);
                	TextField fileName = new TextField();
                	questionBox.getChildren().add(fileName);
                	
                	//button to save
                	Button jsonButton = new Button();
                	jsonButton.setText("Save to file");
                	questionBox.getChildren().add(jsonButton);
                	 jsonButton.setOnAction(new EventHandler<ActionEvent>() {
                      	@Override
                      	public void handle(ActionEvent event) {
                      	File f = new File(fileName.getText());//get text for file name
                      	saveToJson(f);
                      	}	
                     });
                	 //back to main page
                	 Button backBtn = new Button();
                     backBtn.setText("Back to Main Page");
                     questionBox.getChildren().add(backBtn);
                     backBtn.setOnAction(new EventHandler<ActionEvent>() {
                     	@Override
                     	public void handle(ActionEvent event) {
                     		primaryStage.setScene(scene);
                     		primaryStage.setFullScreen(true);
                             primaryStage.show();
                     	}
                     		
                    });
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
           
            //user settings
            Label rightVBLabel = new Label("User Settings");
            rightVBLabel.setFont(Font.font("Amble CN", FontWeight.BOLD, 16));
            rightVB.getChildren().add(rightVBLabel);
            
            //number of questions
            Label numQLabel = new Label("Enter the number of questions you would like to answer:");
            Button button = new Button("Submit");
            TextField text = new TextField();
            button.setOnAction(e -> {
                if (Integer.parseInt(text.getText()) > this.totalNumQuestions) {
                    this.totalNumQuestions = questionDB.getNumQuestions();
                } else {
                    this.totalNumQuestions = Integer.parseInt(text.getText());
                }
            });
            HBox hb = new HBox();
            hb.getChildren().addAll(numQLabel, text, button);
            hb.setSpacing(10);
            rightVB.getChildren().addAll(hb);
            
            //topics
            Label topicLabel = new Label("Topics:");
            Button updateButton = new Button("Update Topics");
            ComboBox<String> topicBox = new ComboBox<String>(
                FXCollections.observableArrayList("No topics are currently loaded"));
            rightVB.getChildren().addAll(topicLabel, topicBox, updateButton);

            updateButton.setOnAction(e -> {
                this.grabTopic =
                    new ComboBox<String>(FXCollections.observableArrayList(questionDB.getTopics()));
                if (questionDB.getTopics().isEmpty()) {

                } else {
                    rightVB.getChildren().clear();
                    rightVB.getChildren().addAll(rightVBLabel, hb, topicLabel, grabTopic,
                        updateButton);
                }
            });

            root.setRight(rightVB);

            // Center
            VBox centerVB = new VBox();
            centerVB.setPadding(new Insets(10, 50, 50, 50));
            centerVB.setSpacing(10);
            
            //starting the quiz
            Label centerLabel = new Label("Press Start to begin the quiz");
            centerLabel.setFont(Font.font("Amble CN", FontWeight.BOLD, 16));

            Button startButton = new Button();
            startButton.setText("Start the quiz");
            centerVB.getChildren().addAll(centerLabel, startButton);

            root.setCenter(centerVB);

            startButton.setOnAction(e -> {
                System.out.println(grabTopic.getValue());
                this.topic = grabTopic.getValue();
                displayQuiz(this.topic);
            });

            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void saveToJson(File f) {
    	//saves the questions to file f
    	this.questionDB.saveQuestionsToJSON(f);
    }
    private void displayAddQuestionForm() {
//    	sceneAddFormNode
    	AddQuestionFormNode addQuestionFormNode = new AddQuestionFormNode();//set each new question
    	VBox questionBox = addQuestionFormNode.getNode();//set Vbox
    	
    	Label numQLabel = new Label("Enter your question: ");//label 
        TextField textField = new TextField();//making text box for question
        textField.setPrefWidth(300);//text width
        HBox hb = new HBox();//horizontally
        hb.getChildren().addAll(numQLabel, textField);//connect text field to Hbox 
        hb.setSpacing(29);//space between label and text field
        questionBox.getChildren().addAll(hb);//connect text field to question box 
        
        //metadata
        Label metaLabel = new Label("Enter your Metadata:");//label
        TextField textField2 = new TextField();//making text box for metadata
        textField2.setPrefWidth(300);//text width
        HBox hb2 = new HBox();//horizontally
        hb2.getChildren().addAll(metaLabel, textField2);//connect text field to Hbox 
        hb2.setSpacing(28);//space between label and text field
        questionBox.getChildren().addAll(hb2);//connect text field to question box 
        
        //topic
        Label topicLabel = new Label("Enter your topic:");//label
        TextField textField3 = new TextField();//making text box for metadata
        textField3.setPrefWidth(300);//text width
        HBox hb3 = new HBox();//horizontally
        hb3.getChildren().addAll(topicLabel, textField3);//connect text field to Hbox
        hb3.setSpacing(58);//space between label and text field
        questionBox.getChildren().addAll(hb3);//connect text field to question box 
        //image
        FileChooser f = new FileChooser();//making file chooser
        Button browse = new Button("Browse Image File");//making browse file button
        browse.setOnAction((event) ->//defining event for browse button
        {
        	File file = f.showOpenDialog(primaryStage);
        	Image img = new Image(file.toURI().toString());//getting and loading the image
        	ImageView mv = new ImageView(img);
        	mv.setImage(img);
        });
        questionBox.getChildren().addAll(browse);//set the browse button to question box
        
        //adding choice1        
        Label choice1Label = new Label("Choice1:");//label
        TextField textField5 = new TextField();//making text box for choice
        textField5.setPrefWidth(300);//text width
        HBox hb5 = new HBox();//horizontally
        hb5.getChildren().addAll(choice1Label, textField5);//connect text field to Hbox
        hb5.setSpacing(100);//space between label and text field
        questionBox.getChildren().addAll(hb5);//connect text field to question box 
        
        // create radiobuttons for true or false choices
        VBox r = new VBox(); 
        RadioButton r1 = new RadioButton("True"); 
        RadioButton r2 = new RadioButton("False"); 
        ToggleGroup tg = new ToggleGroup(); 
        
        r1.setToggleGroup(tg);//set the tags for true and false 
        r2.setToggleGroup(tg);
        r.getChildren().addAll(r1, r2);//set the buttons to r
        
        questionBox.getChildren().addAll(r);//connect r to question box


        
        //add choice2
        Label choice2Label = new Label("Choice2:");
        TextField textField6 = new TextField();
        textField6.setPrefWidth(300);
        HBox hb6 = new HBox();
        hb6.getChildren().addAll(choice2Label, textField6);
        hb6.setSpacing(100);
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
        textField7.setPrefWidth(300);
        HBox hb7 = new HBox();
        hb7.getChildren().addAll(choice3Label, textField7);
        hb7.setSpacing(100);
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
        
          //add choice4
        Label choice4Label = new Label("Choice4:");
        TextField textField8 = new TextField();
        textField8.setPrefWidth(300);
        HBox hb8 = new HBox();
        hb8.getChildren().addAll(choice4Label, textField8);
        hb8.setSpacing(100);
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
        
        //add choice5
        Label choice5Label = new Label("Choice5:");
        TextField textField9 = new TextField();
        textField9.setPrefWidth(300);
        HBox hb9 = new HBox();
        hb9.getChildren().addAll(choice5Label, textField9);
        hb9.setSpacing(100);
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
        		
        		
        		
//        		1. Here, get all the text from the respective textfields like question test, choices
//        		and everything else.
//        		2. Create a new Question object
//        		3. add the question object to the QuestionDatabase
        		//Scene backScene = new Scene(root, 400, 400);    
//        		Question
        		
        	}
        });
        //back button
        Button backBtn = new Button();
        backBtn.setText("Back to Main Page");
        backBtn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent event) {
        		primaryStage.setScene(scene);
        		primaryStage.setFullScreen(true);
                primaryStage.show();
        	}
        		
       });
        
        questionBox.getChildren().addAll(addQuestionBtn);
        questionBox.getChildren().addAll(backBtn);
    
//        Scene scene = new Scene(questionBox, 500,500);
//    	primaryStage.setScene(scene);
//        primaryStage.show();
        
//        1. create a new button for saving the question
//        2. do the following steps in the button onclick event handler
//        3. get all the question details from the question box fields - TextField
//        4. Create a new Question object
//        5. Add Question object to QuestionDatabase
//        6. At the end we need a button to go back to the main page
        
        questionScene = new Scene(questionBox, 600, 800);//set the size for question window              
    	primaryStage.setScene(questionScene);//connect the question page to primary stage
        primaryStage.show();
   }
    

    
    
	private void displayQuiz(String topic) {

		currQuestionNum = 0;
        questions = questionDB.getQuestions(topic);
        for (int i = 0; i < totalNumQuestions; i++) { // doesn't the #of Questions has to be chosen
                                                      // by the user?
            currQuestion = questions.get(i);
            this.currQuestionNum++;
            displayQuestion();
        }
	}
    /**
	 * Once settings have been made: 
	 * User presses button to generate a quiz.
	 * The application shows one question at a time and accepts the users answer for the question.
	 * Some questions may have corresponding images, that will appear in a 200x200 window in your GUI.  
	 * If a question has no image, this window will be blank, or show a background color or image.
	 */
    private void displayQuestion() {
		Stage s = new Stage();
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 400, 400);

		//not sure if we still need this
		//		VBox centerVB = new VBox();
		//		centerVB.setPadding(new Insets(10, 50, 50, 50));
		//		centerVB.setSpacing(10);
		
		s.setScene(scene);
		s.show();

		// Question #/ Total Q#
		// Check if this works
		VBox box = new VBox();
		box.setPadding(new Insets(10, 50, 50, 50));
		box.setSpacing(10);
		Label questionNumber = new Label("Current Question: " + this.currQuestionNum + "/  Total: " + totalNumQuestions);
		QuestionNode q = new QuestionNode(this.currQuestion);
		box.getChildren().add(questionNumber);
		box.getChildren().add(q.getNode());

		//Question(Label) : Displays the question text
		TextFlow textFlow = new TextFlow();
		textFlow.setLayoutX(40);
		textFlow.setLayoutY(40);
		Text text1 = new Text(currQuestion.getQuestion());
		box.getChildren().add(text1);
		root.setCenter(text1);

		//Image 
		// check if this works
		Image image = new Image(currQuestion.getImage());
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(200); //200x200 pixel frame
		imageView.setFitWidth(200);
		root.setCenter(imageView);

		//Choices(ToggleGroup)
		//Radio buttons for choices
		RadioButton[] buttons = new RadioButton[5]; // max 10 multiple choice?? no limit??
		ToggleGroup group2 = new ToggleGroup();
		
		for( int i=0; i<currQuestion.getChoices().size(); i++) {
			buttons[i] = new RadioButton(currQuestion.getChoices().get(i).choice);
			buttons[i].setToggleGroup(group2);
			box.getChildren().addAll(buttons[i]);
		}
	
		Button submit = new Button();
		submit.setText("Submit"); // IF SUBMIT THEN displaySubmit()
		box.getChildren().add(submit);
		root.setBottom(box);
		
		//Submit -> check answer
		EventHandler<ActionEvent> isCorrect = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				displaySubmit(q);	//NEED a question node		
			}
		};
		//if submit is pressed, run isCorrect
		submit.setOnAction(isCorrect);
	}


    /**
	 * After each question is answered:
	 * The result is indicated to the user as "correct" or "incorrect" in some manner.
	 * Do not use only color to indicate correctness.
	 * @param qn
	 */
    private void displaySubmit(QuestionNode qn) {
		Stage s = new Stage();

		//consider CORRECT/INCORRECT
		if(qn.getChoices().equals(qn.getNode().getUserData())) { //TA
			String family = "Helvetica";
			double size = 50;

			TextFlow textFlow = new TextFlow();
			textFlow.setLayoutX(40);
			textFlow.setLayoutY(40);
			Text text1 = new Text("CORRECT!");
			text1.setFont(Font.font(family, size));
			text1.setFill(Color.GREEN);

			textFlow.getChildren().addAll(text1);

			Group group = new Group(textFlow);
			Scene scene = new Scene(group, 350, 150, Color.WHITE);
			s.setTitle("Your Answer is");
			s.setScene(scene);
			s.show();
		}
		else {
			String family = "Helvetica";
			double size = 50;

			TextFlow textFlow = new TextFlow();
			textFlow.setLayoutX(40);
			textFlow.setLayoutY(40);
			Text text1 = new Text("INCORRECT!");
			text1.setFont(Font.font(family, size));
			text1.setFill(Color.RED);

			textFlow.getChildren().addAll(text1);

			Group group = new Group(textFlow);
			Scene scene = new Scene(group, 350, 150, Color.WHITE);
			s.setTitle("Your Answer is");
			s.setScene(scene);
			s.show();
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
	 * Ask the user for a file name to save all questions to a json file.   
	 *
	 * Provide two buttons on the form:  Save, or Exit without Save.
	 * Show an alert confirming their choice and providing a goodbye message. 
	 */
	private void ending_helper() { // this method was not stated on the TA's Draft
		Stage s = new Stage();
		TilePane mainTilePane = new TilePane(); 
		TextFlow textFlow = new TextFlow();

		// SAVE BUTTON
		Button save = new Button("Save"); 
		create_save_button(s,save); // create


		// inner class action event 
		EventHandler<ActionEvent> last = new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent e) { 
				have_A_great_day(s,textFlow); // good-bye message
			} 
		}; 

		// Saving File
		EventHandler<ActionEvent> browser = new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent e) { 
				BorderPane root = new BorderPane();

				File selectedFile = choose_file(root,s);

				TextFlow textFlow2 = new TextFlow();
				have_A_great_day(s,textFlow2);
			}
		};

		save.setOnAction(browser);


		//2. "Exit without Save" button
		Button Exit_Button = new Button();
		Exit_Button.setText("Exit without Save");

		EventHandler<ActionEvent> no_save_button = new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent e) { 
				TextFlow textFlow3 = new TextFlow();
				have_A_great_day(s,textFlow3);
				Scene greatday = new Scene(textFlow3, 100, 100);
								
				s.setScene(greatday);
				s.show();
			}
		};
		// if exit button --> no_save_button method
		Exit_Button.setOnAction(no_save_button);

		// 1. Main Buttons
		mainTilePane.getChildren().add(save); 
		mainTilePane.getChildren().add(Exit_Button);

		// 2. Save or No Save 
		save.setOnAction(browser); 	// save -> choose file -> last
		Exit_Button.setOnAction(last); // exit_no_save -> confirmation -> last

		Scene sc = new Scene(mainTilePane, 100, 100); 		// create a scene 

		// set the scene 
		s.setScene(sc); 
		s.show();
	}


	/**
	 * Method that makes "Save" button and displays
	 * @param s
	 * @param save
	 * @return save button
	 */
	private Button create_save_button(Stage s, Button save) {
		//1. SAVE 
		save = new Button("Save"); 

		// if clicked save -> go to file chooser
		save.setOnAction(actionEvent -> {
			FileChooser chooser = new FileChooser();
			File selectedFile = chooser.showOpenDialog(s); // where to save

			if (selectedFile != null) { 
				questionDB.saveQuestionsToJSON(selectedFile); //save file method
			}
		});
		return save;
	}


	/**
	 * Goodbye message that will be shown to the user right before they leave
	 * @param s
	 * @param textFlow
	 * @return
	 */
	private TextFlow have_A_great_day(Stage s, TextFlow textFlow) {
		System.out.println("in here");
		textFlow.setLayoutX(40);
		textFlow.setLayoutY(40);

		Text greatday = new Text("Have a great day!    "); // written
		greatday.setFont(Font.font("Verdana",20));
		//Exit --> shutdown
		Button exit = new Button();
		exit.setText("EXIT");

		EventHandler<ActionEvent> shutdown = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Platform.exit();
			}
		};
		//display
		textFlow.getChildren().add(greatday);
		textFlow.getChildren().add(exit);

		exit.setOnAction(shutdown); //when clicked shut down everything
		
		Scene scene = new Scene(textFlow, 400,100);
		s.setScene(scene);
		
		return textFlow;
	}


	/**
	 * choose file location that you want to save
	 * @param root
	 * @param s
	 * @return
	 */
	private File choose_file(BorderPane root,Stage s) {
		Stage Stage = new Stage();
		FileChooser fileChooser = new FileChooser(); //create
		Stage.setTitle("JavaFX App");

		//select file folder
		File selectedFile = fileChooser.showOpenDialog(Stage);

		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Text Files", "*.txt")
				,new FileChooser.ExtensionFilter("HTML Files", "*.htm")
				);

		return selectedFile;
	}


    /**
	 * main method executing Main class 
	 * @param args
	 */
    public static void main(String[] args) {
        launch(args);
    }
}
