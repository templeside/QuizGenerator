//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION ////////////////////////
// Title:           ATEAM PROJECT                                               //
// Files:           AddQuestionFormNode, Choice, Main, QuestionNode             //
//                  Question, QuestionDatabase, QuestionDatabaseADT             //  
// Course:          CS 400, Spring 2018                                         //
// Author:          ATEAM106 (Andrew Lutkus, Chanwoong Jhon,                    //
//                            Elaheh Jabbarifard, Chaiyeen Oh, Sara Haines)     //
// Email:           alutkus@wisc.edu, cjhon@wisc.edu,                           //
//                  jabbarifard@wisc.edu, coh26@wisc.edu, schaines@wisc.edu     //
// Lecturer's Name: Andrew Kuemmel, Deb Deppeler                                //
// Due Date : 5/2/2019 10pm                                                     //
//                                                                              //
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:            //
//   _X_ Write-up states that pair programming is allowed for this assignment.  //
//   _X_ We have both read and understand the course Pair Programming Policy.   //
//   _X_ We have registered our team prior to the team registration deadline.   //
//                                                                              //
///////////////////////////// CREDIT OUTSIDE HELP ////////////////////////////////
//                                                                              //
// Students who get help from sources other than their partner must fully       //
// acknowledge and credit those sources of help here.  Instructors and TAs do   //
// not need to be credited here, but tutors, friends, relatives, room mates,    //
// strangers, and others do.  If you received no outside help from either type  //
//  of source, then please explicitly indicate NONE.                            //
//                                                                              //
// Persons:         X                                                           //
// Online Sources:  X                                                           //
//                                                                              //
/////////////////////////////// 80 COLUMNS WIDE //////////////////////////////////
package application;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
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
    private QuestionDatabase questionDB;// database with questions and topics
    private List<Question> questions;// list of questions
    private Question currQuestion;// current question in the quiz
    private int currQuestionNum;// current question number
    private int totalNumQuestions;// total number of questions
    private int numIncorrect;// number of incorrect answers given by the user
    private String topic;// topic of the quiz
    private ComboBox<String> grabTopic;// combobox of the topics available for quiz
    private Scene mainScene;
    private int numInQList;
    private Stage tempQuestionStage;
    private Stage tempResultStage;
    private Stage tempEndStage;

    Stage primaryStage;// Stage for the GUI
    Scene scene, questionScene;// scenes for home page, add new question, etc.

    @Override
    public void start(Stage primaryStage) {
        try {

            this.primaryStage = primaryStage;
            questions = null;
            questionDB = new QuestionDatabase();
            grabTopic = new ComboBox<String>();
            currQuestion = null;
            currQuestionNum = 0;
            totalNumQuestions = 0;
            numIncorrect = 0;
            numInQList = 0;
            primaryStage.setFullScreen(true);

            // top
            FileChooser fileChooser = new FileChooser();
            Label topLabel = new Label("Welcome to Quiz Generator");
            topLabel.setFont(Font.font("Amble CN", FontWeight.BOLD, 20));
            BorderPane root = new BorderPane();
            root.setTop(topLabel);
            BorderPane.setAlignment(topLabel, Pos.CENTER);
            scene = new Scene(root, 400, 400);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
            this.mainScene = scene;

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
                public void handle(ActionEvent e) {
                    displayAddQuestionForm();
                }
            };

            // when button is pressed
            btn1.setOnAction(event1);

            Button btn2 = new Button();
            btn2.setText("Load a json file");
            leftVB.getChildren().add(btn2);
            EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e) {

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
                            System.out.println("FILE is " + file);
                            questionDB.loadQuestionsFromJSON(file);

                        } catch (Exception ex) {
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
                            primaryStage.setFullScreen(true);
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
                public void handle(ActionEvent e) {
                    // file name
                    VBox questionBox = new VBox();
                    Label newQuestion = new Label();
                    newQuestion.setText("Enter the file name: ");
                    questionBox.getChildren().add(newQuestion);
                    TextField fileName = new TextField();
                    questionBox.getChildren().add(fileName);

                    // button to save
                    Button jsonButton = new Button();
                    jsonButton.setText("Save to file");
                    questionBox.getChildren().add(jsonButton);
                    jsonButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            File f = new File(fileName.getText());// get text for file name
                            saveToJson(f);
                        }
                    });
                    // back to main page
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
            
            Label rightVBLabel = new Label("User Settings");
            rightVBLabel.setFont(Font.font("Amble CN", FontWeight.BOLD, 16));
            rightVB.getChildren().add(rightVBLabel);

            Label numQLabel = new Label("Enter the number of questions you would like to answer:");
            Button NumQuestionbutton = new Button("Submit");
            TextField text = new TextField();

            // when Submit pressed : #Questions input from the user
            NumQuestionbutton.setOnAction(e -> {
                this.topic = grabTopic.getValue();
                this.totalNumQuestions = questionDB.getQuestions(this.topic).size();

                try {
                    if (Integer.parseInt(text.getText()) < 1) {
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setContentText("Number of questions needs to be greater than 0");
                        alert.show();
                    }

                    if (Integer.parseInt(text.getText()) < this.totalNumQuestions) {
                        this.totalNumQuestions = Integer.parseInt(text.getText());
                        System.out
                            .println("totalNumQuestions after NumQuestionbutton is pressed22222"
                                + totalNumQuestions);

                    } else {
                        this.totalNumQuestions = questionDB.getQuestions(this.topic).size();
                        System.out.println("totalNumQuestions after NumQuestionbutton is pressed"
                            + totalNumQuestions);
                    }
                } catch (Exception e1) {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setContentText("Please type in appropriate input");
                    alert.show();
                }
            });
            
            //adding available questions uploaded on the main screen
            Label DisplayTotalQuestion = new Label("Total Questions: " + totalNumQuestions);
            rightVB.getChildren().add(DisplayTotalQuestion);

            HBox hb = new HBox();
            hb.getChildren().addAll(numQLabel, text, NumQuestionbutton);
            hb.setSpacing(10);
            rightVB.getChildren().addAll(hb);

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
                    // update DisplayTotalQuestion before displaying updated Main screen
                    totalNumQuestions = questionDB.getNumQuestions();
                    DisplayTotalQuestion.setText("Total Questions: " + totalNumQuestions);
                    
                    rightVB.getChildren().addAll(rightVBLabel,DisplayTotalQuestion, hb, topicLabel, grabTopic,
                        updateButton);
                }
            });
            root.setRight(rightVB);

            // Center
            VBox centerVB = new VBox();
            centerVB.setPadding(new Insets(10, 50, 50, 50));
            centerVB.setSpacing(10);

            Label centerLabel = new Label("Click start to begin");
            centerLabel.setFont(Font.font("Amble CN", FontWeight.BOLD, 16));

            Button startButton = new Button();
            Button helpButton = new Button("Instructions");
            startButton.setText("Start the quiz");
            centerVB.getChildren().addAll(centerLabel, startButton, helpButton);

            root.setCenter(centerVB);


            startButton.setOnAction(e -> {
                this.topic = grabTopic.getValue();
                displayQuiz(this.topic);
            });

            helpButton.setOnAction(e -> {
                Stage stage = new Stage();

                stage.setTitle("Instructions");
                Text step1 = new Text();
                Text step2 = new Text();
                Text step3 = new Text();
                Text step4 = new Text();
                step1.setText("1. Add Question or load from a json file");
                step2.setText("2. Update Topics list and choose a topic");
                step3.setText(
                    "3. Type the number of questions you would like to answer, must be greater than 0");
                step4.setText("4. Start the Quiz");
                BorderPane br = new BorderPane();
                VBox vb = new VBox();
                vb.getChildren().addAll(step1, step2, step3, step4);
                br.setCenter(vb);
                Scene secondScene = new Scene(br);
                stage.setScene(secondScene);
                stage.show();
            });

            primaryStage.show();

            // for closing
            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

                public void handle(WindowEvent we) {
                    ending_helper();
                }
            });
            startButton.setOnAction(e -> {
                this.topic = grabTopic.getValue();
                displayQuiz(this.topic);

                primaryStage.show();

            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveToJson(File f) {
        // saves the questions to file f
        this.questionDB.saveQuestionsToJSON(f);
    }

    private void displayAddQuestionForm() {
        // sceneAddFormNode
        AddQuestionFormNode addQuestionFormNode = new AddQuestionFormNode();// set each new question
        VBox questionBox = addQuestionFormNode.getNode();// set Vbox


        Label numQLabel = new Label("Enter your question: ");// label
        TextField textField = new TextField();// making text box for question
        textField.setPrefWidth(300);// text width
        HBox hb = new HBox();// horizontally
        hb.getChildren().addAll(numQLabel, textField);// connect text field to Hbox
        hb.setSpacing(27);// space between label and text field
        questionBox.getChildren().addAll(hb);// connect text field to question box

        // metadata
        Label metaLabel = new Label("Enter your Metadata:");// label
        TextField textField2 = new TextField();// making text box for metadata
        textField2.setPrefWidth(300);// text width
        HBox hb2 = new HBox();// horizontally
        hb2.getChildren().addAll(metaLabel, textField2);// connect text field to Hbox
        hb2.setSpacing(26);// space between label and text field
        questionBox.getChildren().addAll(hb2);// connect text field to question box

        // topic
        Label topicLabel = new Label("Enter your topic:");// label
        TextField textField3 = new TextField();// making text box for metadata
        textField3.setPrefWidth(300);// text width
        HBox hb3 = new HBox();// horizontally
        hb3.getChildren().addAll(topicLabel, textField3);// connect text field to Hbox
        hb3.setSpacing(53);// space between label and text field
        questionBox.getChildren().addAll(hb3);// connect text field to question box

        // image
        Label imageLabel = new Label("Enter Image File name:");
        TextField textField4 = new TextField("none");// textfield for image path
        textField4.setPrefWidth(300);
        HBox hb4 = new HBox();
        hb4.getChildren().addAll(imageLabel, textField4);// add label and textfield to hbox
        hb4.setSpacing(15);// space between label and textfield
        questionBox.getChildren().addAll(hb4);// connect hbox to questionBox

        // adding choices and radio boxes
        addChoiceHboxAndRadioBox(questionBox);

        Button addQuestionBtn = new Button("Add Question");// press to save question
        addQuestionBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Boolean isdisabled = true;
                String newMetadata = addQuestionFormNode.getMetadata();// metadata from user
                String newQuestion = addQuestionFormNode.getQuestion();// question from user
                String newTopic = addQuestionFormNode.getTopic();// topic from user
                String newImage = addQuestionFormNode.getImage();// image path from user
                List<Choice> newChoices = addQuestionFormNode.getChoiceTexts();// choices from user
                String answer = "";

                // if image name is invalid
                if (!answer.equals("")) {
                    try {
                        Image image = new Image(newImage);
                        ImageView imageView = new ImageView(image);
                    } catch (IllegalArgumentException e) {
                        isdisabled = false;
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setContentText("Please type a valid picture name");
                        alert.show(); // show this dialog
                    }
                }

                int trueAnswers = 0;
                for (int i = 0; i < newChoices.size(); i++) {
                    if (newChoices.get(i).getIsCorrect() == true) {
                        trueAnswers++;
                    }
                }
                if (trueAnswers != 1) {
                    isdisabled = false;
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setContentText("Invalid number of true. Please select only one true.");
                    alert.show(); // show this dialog
                }

                for (int i = 0; i < newChoices.size(); i++) {
                    if (newChoices.get(i).getIsCorrect()) {
                        answer = newChoices.get(i).getChoice();
                    }
                }

                Question question =
                    new Question(newMetadata, newQuestion, newTopic, newImage, newChoices, answer);

                if (isdisabled == true) {
                    questionDB.addQuestion(question);
                }
            }
        });

        // back button
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

        questionBox.getChildren().addAll(addQuestionBtn);// add buttons to questionBox
        questionBox.getChildren().addAll(backBtn);

        // Scene scene = new Scene(questionBox, 500,500);
        // primaryStage.setScene(scene);
        // primaryStage.show();

        questionScene = new Scene(questionBox, 600, 800);// dimensions of page
        primaryStage.setScene(questionScene);
        primaryStage.show();// show add question page
    }



    private void addChoiceHboxAndRadioBox(VBox questionBox) {
        int j;
        for (j = 0; j < 5; j++) {
            Label choice2Label = new Label("Choice" + j + ":");// label for each choice
            TextField textField6 = new TextField();// textfield for each choice
            textField6.setPrefWidth(300);// width of textfield
            HBox hb6 = new HBox();
            hb6.getChildren().addAll(choice2Label, textField6);// add label and textfield
            hb6.setSpacing(100);// spacing between textfield and label
            questionBox.getChildren().addAll(hb6);// add hbox to questionBox

            // create radiobuttons
            VBox r = new VBox();
            RadioButton r1 = new RadioButton("True");
            RadioButton r2 = new RadioButton("False");
            ToggleGroup tg2 = new ToggleGroup();

            r1.setToggleGroup(tg2); // set the toggle group for true and false
            r2.setToggleGroup(tg2);
            r.getChildren().addAll(r1, r2); // add the buttons to vbox

            questionBox.getChildren().addAll(r);// add vbox to QuestionBox
        }

    }

    private void displayQuiz(String topic) {
        questions = questionDB.getQuestions(topic);
        this.currQuestion = questions.get(numInQList);
        this.numInQList++;
        this.currQuestionNum++;
        displayQuestion();
    }

    /**
     * Once settings have been made: User presses button to generate a quiz. The application shows
     * one question at a time and accepts the users answer for the question. Some questions may have
     * corresponding images, that will appear in a 200x200 window in your GUI. If a question has no
     * image, this window will be blank, or show a background color or image.
     */
    private void displayQuestion() {

        Stage s = new Stage();
        s.setTitle("Each Question");
        BorderPane root = new BorderPane();

        // Current Question:# Total Q#
        VBox box = new VBox();
        box.setPadding(new Insets(20, 50, 50, 50));
        box.setSpacing(10);
        Label questionNumber = new Label(
            "Current Question: " + this.currQuestionNum + "     Total: " + totalNumQuestions);
        QuestionNode q = new QuestionNode(this.currQuestion);
        box.getChildren().add(questionNumber);
        box.getChildren().add(q.getNode());


        // Image
        // check if this works
        if (!currQuestion.getImage().equals("none")) {
            Image image = new Image(currQuestion.getImage());
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(200); // 200x200 pixel frame
            imageView.setFitWidth(200);
            root.setCenter(imageView);
        }

        // Choices(ToggleGroup)
        // Radio buttons for choices
        ToggleGroup group2 = new ToggleGroup();

        // System.out.println(currQuestion.getChoices().size());

        for (int i = 0; i < currQuestion.getChoices().size(); i++) {
            RadioButton rb = new RadioButton(currQuestion.getChoices().get(i).choice);
            rb.setToggleGroup(group2);
            box.getChildren().addAll(rb);
        }

        Button submit = new Button();
        submit.setText("Submit"); // IF SUBMIT THEN displaySubmit()
        box.getChildren().add(submit);

        // Submit -> check answer
        submit.setOnAction(e -> {
            displaySubmit(((Labeled) group2.getSelectedToggle()).getText());
        });

        root.setBottom(box);

        Scene scene = new Scene(root, 400, 400);
        s.setScene(scene);
        this.tempQuestionStage = s;
        s.show();

    }


    /**
     * After each question is answered: The result is indicated to the user as "correct" or
     * "incorrect" in some manner. Do not use only color to indicate correctness.
     * 
     * @param qn
     */
    private void displaySubmit(String choice) {
        Stage s = new Stage();

        // consider CORRECT/INCORRECT
        if (choice.equals(this.currQuestion.getAnswer())) { // TA
            String family = "Helvetica";
            double size = 50;


            // will display if user gets answer correct
            TextFlow textFlow = new TextFlow();
            textFlow.setLayoutX(40);
            textFlow.setLayoutY(40);
            Text text1 = new Text("CORRECT!");
            text1.setFont(Font.font(family, size));
            text1.setFill(Color.GREEN);

            textFlow.getChildren().addAll(text1);


            Button nextQuestion = new Button("Next Question");
            textFlow.getChildren().add(nextQuestion);
            nextQuestion.setOnAction(e -> {
                this.tempResultStage.close();
                this.tempQuestionStage.close();
                displayQuiz(currQuestion.getTopic());
            });

            Group group = new Group(textFlow);
            Scene scene = new Scene(group, 350, 150, Color.WHITE);
            s.setTitle("Your Answer is");
            s.setScene(scene);
            this.tempResultStage = s;
            s.show();
            if (currQuestionNum == totalNumQuestions) {
                this.tempQuestionStage.close();
                displayResults();
            }
        } else {
            String family = "Helvetica";
            double size = 50;

            // will display if user is incorrect
            TextFlow textFlow = new TextFlow();
            textFlow.setLayoutX(40);
            textFlow.setLayoutY(40);
            this.numIncorrect++;
            Text text1 = new Text("INCORRECT!");
            text1.setFont(Font.font(family, size));
            text1.setFill(Color.RED);

            textFlow.getChildren().addAll(text1);

            Button nextQuestion = new Button("Next Question");
            textFlow.getChildren().add(nextQuestion);
            nextQuestion.setOnAction(e -> {
                this.tempQuestionStage.close();
                this.tempResultStage.close();
                displayQuiz(currQuestion.getTopic());
            });



            // shows what the user chose
            Group group = new Group(textFlow);
            Scene scene = new Scene(group, 350, 150, Color.WHITE);
            s.setTitle("Your Answer is");
            s.setScene(scene);
            this.tempResultStage = s;
            s.show();
            if (currQuestionNum == totalNumQuestions) {
                this.tempQuestionStage.close();
                this.tempResultStage.close();
                displayResults();
            }
        }

    }

    /**
     * After all quiz questions have been answered: Shows the final quiz scores (# correct, #
     * answered, percent correct). Do not show correct answers. Allow user to change settings and
     * try a new quiz
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


        Button backBtn = new Button();
        backBtn.setText("Back to Main Page");
        backBtn.setOnAction(e -> {
            this.currQuestion = null;
            this.currQuestionNum = 0;
            this.totalNumQuestions = 0;
            this.numIncorrect = 0;
            this.numInQList = 0;
            this.tempResultStage.close();
            this.tempEndStage.close();
            primaryStage.setScene(mainScene);
            primaryStage.setFullScreen(true);
            primaryStage.show();
        });

        centerVB.getChildren().add(backBtn);

        root.setCenter(centerVB);
        this.tempEndStage = s;
        s.show();
    }

    /**
     * When the user exits the program: Ask the user for a file name to save all questions to a json
     * file.
     *
     * Provide two buttons on the form: Save, or Exit without Save. Show an alert confirming their
     * choice and providing a goodbye message.
     */
    private void ending_helper() { // this method was not stated on the TA's Draft
        Stage s = new Stage();
        TilePane mainTilePane = new TilePane();
        TextFlow textFlow = new TextFlow();

        // SAVE BUTTON
        Button save = new Button("Save");
        create_save_button(s, save); // create


        // inner class action event
        EventHandler<ActionEvent> last = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                have_A_great_day(s, textFlow); // good-bye message
            }
        };

        // Saving File
        EventHandler<ActionEvent> browser = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                BorderPane root = new BorderPane();

                File selectedFile = choose_file(root, s);

                TextFlow textFlow2 = new TextFlow();
                have_A_great_day(s, textFlow2);
            }
        };

        save.setOnAction(browser);


        // 2. "Exit without Save" button
        Button Exit_Button = new Button();
        Exit_Button.setText("Exit without Save");

        EventHandler<ActionEvent> no_save_button = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                TextFlow textFlow3 = new TextFlow();
                have_A_great_day(s, textFlow3);
                Scene greatday = new Scene(textFlow3, 400, 100);

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
        save.setOnAction(browser); // save -> choose file -> last
        Exit_Button.setOnAction(last); // exit_no_save -> confirmation -> last

        Scene sc = new Scene(mainTilePane, 400, 100); // create a scene

        // set the scene
        s.setScene(sc);
        s.show();
    }


    /**
     * Method that makes "Save" button and displays
     * 
     * @param s
     * @param save
     * @return save button
     */
    private Button create_save_button(Stage s, Button save) {
        // 1. SAVE
        save = new Button("Save");

        // if clicked save -> go to file chooser
        save.setOnAction(actionEvent -> {
            FileChooser chooser = new FileChooser();
            File selectedFile = chooser.showOpenDialog(s); // where to save

            if (selectedFile != null) {
                questionDB.saveQuestionsToJSON(selectedFile); // save file method
            }
        });
        return save;
    }


    /**
     * Goodbye message that will be shown to the user right before they leave
     * 
     * @param s
     * @param textFlow
     * @return
     */
    private TextFlow have_A_great_day(Stage s, TextFlow textFlow) {
        textFlow.setLayoutX(40);
        textFlow.setLayoutY(40);

        Text greatday = new Text("Have a great day!    "); // written
        greatday.setFont(Font.font("Verdana", 20));
        // Exit --> shutdown
        Button exit = new Button();
        exit.setText("EXIT");

        EventHandler<ActionEvent> shutdown = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Platform.exit();
            }
        };
        // display
        textFlow.getChildren().add(greatday);
        textFlow.getChildren().add(exit);

        exit.setOnAction(shutdown); // when clicked shut down everything

        Scene scene = new Scene(textFlow, 400, 100);
        s.setScene(scene);

        return textFlow;
    }


    /**
     * choose file location that you want to save
     * 
     * @param root
     * @param s
     * @return
     */
    private File choose_file(BorderPane root, Stage s) {
        Stage Stage = new Stage();
        FileChooser fileChooser = new FileChooser(); // create
        Stage.setTitle("JavaFX App");

        // select file folder
        File selectedFile = fileChooser.showOpenDialog(Stage);

        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Text Files", "*.txt"),
            new FileChooser.ExtensionFilter("HTML Files", "*.htm"));

        return selectedFile;
    }

    /**
     * main method executing Main class
     * 
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
