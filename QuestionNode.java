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

import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 *This is the QuestionNode class.
 */
public class QuestionNode {
	//fields 
	private VBox node;
	private ToggleGroup choices;
	private ImageView imageView;

	/**
	 *This is the constructor for the class.
	 *@param q is the Question used to make the QuestionNode
	 */

	public QuestionNode(Question q) {
		node = new VBox();
		if (!q.getImage().equals("none")) {
			Image image = new Image(q.getImage());
			imageView = new ImageView(image);
		}
		Label questionLb = new Label(q.getQuestion());
		for (int i = 0; i < q.getChoices().size(); i++) {
			ToggleButton choice = new ToggleButton(q.getChoices().get(i).getChoice());
			choice.setToggleGroup(this.choices);
		}     
		if (!q.getImage().equals("none")) {
			node.getChildren().addAll(imageView, questionLb);
		} else {
			node.getChildren().addAll(questionLb);
		}
	}

	/**
	 *getter for this QuestionNode
	 *@return node
	 */
	public VBox getNode() {
		return this.node;
	}

	/**
	 *getter for this choices(multiple)
	 *@return choices
	 */
	public ToggleGroup getChoices() {
		return this.choices;
	}
}