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

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AddQuestionFormNode {
    
    private List<Choice> choiceTexts;
    private List<Boolean> choiceGroups;
    private VBox form;
    
    public AddQuestionFormNode() {       
        choiceTexts = new ArrayList<Choice>();
        choiceGroups = new ArrayList<Boolean>();
        form = new VBox();
    }
    
    public String getQuestion() {
    	return ((TextField)((HBox)form.getChildren().get(0)).getChildren().get(1)).getText();               
    }
    
    public String getMetadata() {
    	return ((TextField)((HBox)form.getChildren().get(1)).getChildren().get(1)).getText();
    }    

    
    public String getTopic() {
    	return ((TextField)((HBox)form.getChildren().get(2)).getChildren().get(1)).getText();        
    }

    public String getImage() {
    	return ((TextField)((HBox)form.getChildren().get(3)).getChildren().get(1)).getText();        
    }
    
    public List<Choice> getChoiceTexts() {    	    	
    	int i;
    	for(i = 0;i<5;i++) {
    	HBox hb = (HBox) form.getChildren().get((i*2)+4);
    	
   	
    	Choice choice = new Choice(this.getChoiceGroups().get(i),hb.getChildren().get(1).toString());
    	choiceTexts.add(choice);
    	}
        return choiceTexts;
        
        
    }
    
    public VBox getNode() {
    	return form;
        
    }
    
    public List<Boolean> getChoiceGroups() {
    	for(int i = 0;i<5;i++) {
    		VBox newbox = ((VBox)form.getChildren().get((i*2)+5));
    		
        	RadioButton radioTrue =  (RadioButton)newbox.getChildren().get(0);
        	choiceGroups.add(radioTrue.isSelected());     
    	}
    	return choiceGroups;
    }
}
