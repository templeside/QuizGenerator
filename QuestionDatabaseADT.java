package application;

import java.io.File;
import java.util.List;
import javafx.collections.ObservableList;

interface QuestionDatabaseADT {

    /**
      *This method adds a new question.
      *@param topic is the topic of the Question
      *@param q is the Question being added
      */
    public static void addQuestion(String topic, Question q) {
        
    }

    /**
      *This method saves questions to a JSON file.
      *@param file is the file that the questions are saved to.
      */
    public static void saveQuestionsToJSON(File file) {

    }

    /**
      *This method returns a list of Questions given their topic.
      *@param topic is the topic of the Questions
      */
    public static List<Question> getQuestions(String topic) {
        return null;

    }

    /**
      *This method loads Questions from a JSON file.
      *@param file is the file that Questions are being loaded from
      */
    public static void loadQuestionsFromJSON(File file) {

    }

    /**
      *@return an ObservableList of the topics
      */
    public static ObservableList<String> getTopics() {
        return null;

    }
}
