package application;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class QuestionDatabase implements QuestionDatabaseADT {

    private Map<String, List<Question>> topics;

    public QuestionDatabase() {
        topics = new HashMap<String, List<Question>>();
    }

    public void addQuestion(Question q) {
        List<Question> addQuestionList = new ArrayList<Question>();
        addQuestionList.add(q);
        if (topics.containsKey(q.getTopic())) {
            topics.get(q.getTopic()).addAll(addQuestionList);
        } else {
            topics.put(q.getTopic(), addQuestionList);
        }

    }

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

    public void saveQuestionsToJSON(File file) {

        
    }

    public List<Question> getQuestions(String topic) {
        List<Question> questionList = new ArrayList<Question>();
        questionList = topics.get(topic);
        return questionList;

    }

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

    public ObservableList<String> getTopics() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll(topics.keySet());
        java.util.Collections.sort(list);
        return list;
    }
}
