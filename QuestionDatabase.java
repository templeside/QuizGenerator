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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
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
        topics = new HashMap<String, List<Question>>();
    }


	/**
	 * Adds the questions given by the user with topic and question
	 * @param topic
	 * @param q
	 */
	public void addQuestion(String topic, Question q) {
        List<Question> addQuestionList = new ArrayList<Question>();
        addQuestionList.add(q);
        if (topics.containsKey(q.getTopic())) {
            topics.get(q.getTopic()).addAll(addQuestionList);
        } else {
            topics.put(q.getTopic(), addQuestionList);
        }

    }

	/**
	 * Returns the number of questions that are already in List of questions
	 * @return
	 */
	public int getNumQuestions() {
	     int count = 0;
	        List keys = new ArrayList(topics.keySet());
	        for (int i = 0; i < keys.size(); i++) {
	            for (int j = 0; j < topics.get(keys.get(i)).size(); j++) {
	                count++;
	            }
	        }
	        return count;
	    }

	/**
	 * This method saves all the questions from the "topics" (List of questions) into the json file 
	 *@param file is the file that the questions are saved to.
	 */
	public void saveQuestionsToJSON(File file) {
		
//		Map<String, List<Question>> topics;

		FileWriter f; // "topics" file
		JSONObject obj = new JSONObject();
		
		try { //CHECK THIS!!!!
			f = new FileWriter(file);
			
			
			for(Map.Entry<String, List<Question>> entry: this.topics.entrySet()) {
				JSONArray array = new JSONArray();
				
				for(int i = 0; i < entry.getValue().size(); i++) {
					array.add(makeEntryToString(entry));
				}
				obj.put(entry.getKey(), array);
				
			}
			f.write(obj.toJSONString());
			f.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private JSONArray makeEntryToString(Entry<String, List<Question>> entry) {
		JSONArray arrayForQuestion = new JSONArray();
		JSONArray arrayForChoiceList = new JSONArray();

		JSONObject first = new JSONObject();
		JSONObject properties = new JSONObject();
		JSONObject questionArray = new JSONObject();
		JSONObject items = new JSONObject();
		JSONObject
		
		first.put("$schema", "");
		first.put("title", "object");
		first.put("properties", properties );
		
		properties.put("questionArray", "description");
		description.put("description", "The array of questions");

		array.add(entry.getKey());
		
		//question that i need extract sources
		Question aQuestion = (Question)entry.getValue();
		arrayForQuesetion.add(meta-data)
		
		arrayForQuestion.meta-data = aQuestion.getMetadata());
		arrayForQuestion.add(aQuestion.getQuestion());
		arrayForQuestion.add(aQuestion.getTopic());
		arrayForQuestion.add(aQuestion.getImage());
		arrayForQuestion.add(arrayForChoiceList);

		//add choices by iterating.
		List<Choice> choiceList = aQuestion.getChoices();
		for(int j=0; j<choiceList.size();j++) {
			arrayForChoiceList.add(choiceList.get(j).getChoice());
		}
		
		
		return array;
		
	}

	/**
	 *This method returns a list of Questions given their topic.
	 *@param topic is the topic of the Questions
	 */
    public List<Question> getQuestions(String topic) {
        List<Question> questionList = new ArrayList<Question>();
        questionList = topics.get(topic);
        return questionList;

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
            List<Question> questionList = new ArrayList<Question>();
            Choice corrChoice = null;

            for (int i = 0; i < questionArr.size(); i++) {
                JSONObject qo = (JSONObject) questionArr.get(i);
                String metadata = (String) qo.get("meta-data");
                String question = (String) qo.get("questionText");
                String topic = (String) qo.get("topic");
                String image = (String) qo.get("image");
                JSONArray choiceArr = (JSONArray) qo.get("choiceArray");
                for (int j = 0; j < choiceArr.size(); j++) {
                    JSONObject co = (JSONObject) choiceArr.get(j);
                    String isCorr = (String) co.get("isCorrect");
                    String choiceText = (String) co.get("choice");
                    if (isCorr.equals("T")) {
                        Choice newChoice = new Choice(true, choiceText);
                        corrChoice = newChoice;
                        choiceList.add(newChoice);                  
                    } else {
                        Choice newChoice = new Choice(false, choiceText);
                        choiceList.add(newChoice);
                    }
                }
                Question newQuestion =
                    new Question(metadata, question, topic, image, choiceList, corrChoice.choice);
                questionList.add(newQuestion);
                if (topics.containsKey(newQuestion.getTopic())) {
                    topics.get(newQuestion.getTopic()).addAll(questionList);
                } else {
                    topics.put(newQuestion.getTopic(), questionList);
                }
                choiceList.clear();
            }

        } catch (IOException | ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
	 *@return an ObservableList of the topics
	 */
	public ObservableList<String> getTopics() {
		ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll(topics.keySet());
        java.util.Collections.sort(list);
        return list;
	}
}
