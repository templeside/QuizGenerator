package application;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.collections.ObservableList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class QuestionDatabase implements QuestionDatabaseADT {

    private Map<String, List<Question>> topics;

    public QuestionDatabase() {
        topics = null;
    }

    public void addQuestion() {

    }

    public int getNumQuestions() {
        return 0;
    }

    public void saveQuestionsToJSON(File file) {

    }

    public List<Question> getQuestions(String topic) {
        return null;

    }

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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public ObservableList<String> getTopics() {
        return null;

    }
}
