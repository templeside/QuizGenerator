package application;

import java.io.File;
import java.util.List;
import javafx.collections.ObservableList;

interface QuestionDatabaseADT {

    public static void addQuestion(String name, Question q) {
        
    }

    public static void saveQuestionsToJSON(File file) {

    }

    public static List<Question> getQuestions(String topic) {
        return null;

    }

    public static void loadQuestionsFromJSON(File file) {

    }

    public static ObservableList<String> getTopics() {
        return null;

    }
}
