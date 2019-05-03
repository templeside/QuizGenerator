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
import java.util.List;

/**
 *This is the Question class.
 */
public class Question {
	private String metadata;//metadata for the question
	private String question;//the typed question
	private String topic;//topic of the question
	private String image;//image with the question
	private List<Choice> choices;//choices of the question
	private String answer;//correct choice


	/**
	 *This is the constructor for the class.
	 */
	public Question(String newMetadata, String newQuestion, String newTopic, String newImage,
			List<Choice> newChoices, String newAnswer) {
		this.metadata = newMetadata;
		this.question = newQuestion;
		this.topic = newTopic;
		this.image = newImage;
		this.choices = newChoices;
		this.answer = newAnswer;
	}

	/**
	 * getter for the user's typed question
	 *@return the question
	 */
	public String getQuestion() {
		return this.question;
	}

	/**
	 * getter for the user's typed (multiple) choices
	 *@return the multiple choices for the Question
	 */
	public List<Choice> getChoices() {
		return this.choices;
	}

	/**
	 * getter for the user's typed correct answer
	 *@return the correct answer for the Question
	 */
	public String getAnswer() {
		return this.answer;
	}

	/**
	 * getter for the user-given metadata
	 *@return the metadata for the Question
	 */
	public String getMetadata() {
		return this.metadata;
	}

	/**
	 * getter for the user's typed topic
	 *@return the topic for the Question
	 */
	public String getTopic() {
		return this.topic;
	}

	/**
	 * getter for the user-given image
	 *@return the image for the Question
	 */
	public String getImage() {
		return this.image;
	}
}
