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

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javafx.collections.ObservableList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Contains all questions from .json files and added manually by user
 * @author ateam 106
 *
 */
public class QuestionDatabase implements QuestionDatabaseADT {

	//fields
	private Map<String, List<Question>> topics;

	//no arg constructor 
	public QuestionDatabase() {
		topics = null;
	}

	/**
	 * Adds the questions given by the user with topic and question
	 * @param topic
	 * @param q
	 */
	public void addQuestion(String topic, Question q) {
		topics.get(topic).add(q); // new question added into list of questions
	}

	/**
	 * Returns the number of questions that are already in List of questions
	 * @return
	 */
	public int getNumQuestions() {
		int num = 0;
		for(int i = 0; i < topics.size(); i++) {
			num+= topics.get(i).size();
		}
		return num; // number of questions in "topics"
	}

	/**
	 * This method saves all the questions from the "topics" (List of questions) into the json file 
	 *@param file is the file that the questions are saved to.
	 */
	public void saveQuestionsToJSON(File file) {

		FileWriter f; // "topics" file
		JSONObject obj = new JSONObject();

		try { //CHECK THIS!!!!
			f = new FileWriter(file);

			//each topic = List
			Iterator itr1 = ((List) f).iterator();

			while(itr1.hasNext()) {
				List q_List = (List) itr1.next();
				JSONArray list = new JSONArray(); // list of questions

				for(int i=0; i<q_List.size(); i++) { //add each question -> q_list
					list.add(q_List.get(i));
				}
				obj.put(q_List, list); // each topic into obj
			}
			f.write(obj.toJSONString()); // write obj into a file

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 *This method returns a list of Questions given their topic.
	 *@param topic is the topic of the Questions
	 */
	public List<Question> getQuestions(String topic) {
		return topics.get(topic);
	}

	/**
	 *This method loads Questions from a JSON file.
	 *@param file is the file that Questions are being loaded from
	 */
	public void loadQuestionsFromJSON(File file) {
		Object obj;

		try {
			obj = new JSONParser().parse(new FileReader(file));
			JSONObject jo = (JSONObject) obj;
			JSONArray questionArr = (JSONArray) jo.get("questionArray");
			List<Choice> choiceList = new ArrayList<Choice>();
			Choice corrChoice = null;

			for (int i = 0; i < questionArr.size(); i++) {
				JSONObject qo = (JSONObject) questionArr.get(i);
				String metadata = (String) qo.get("meta-data");
				String question = (String) qo.get("questionText");
				String topic = (String) qo.get("topic");
				String image = (String) qo.get("image");
				JSONArray choiceArr = (JSONArray) qo.get("choiceArray");
				for (int j = 0; j < choiceArr.size(); j++) {
					JSONObject co = (JSONObject) choiceArr.get(i);
					boolean isCorr = (boolean) co.get("isCorrect");
					String choiceText = (String) co.get("choice");
					Choice newChoice = new Choice(isCorr, choiceText);
					if (isCorr == true) {
						corrChoice = newChoice;
					}
					choiceList.add(newChoice);
				}

				Question newQuestion =
						new Question(metadata, question, topic, image, choiceList, corrChoice.choice);
				choiceList.clear();
			}

		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 *@return an ObservableList of the topics
	 */
	public ObservableList<String> getTopics() {
		return null;
	}
}
