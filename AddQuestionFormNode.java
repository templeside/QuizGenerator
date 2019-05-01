package application;

import java.io.FileReader;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class AddQuestionFormNode {
    
    private List<TextField> choiceTexts;
    private List<ToggleGroup> choiceGroups;
    private VBox form;
    
    public AddQuestionFormNode() {       
        choiceTexts = null;
        choiceGroups = null;
        form = null;
    }
    
    public TextField getMetadata() {
        return null;
        
    }
    
    public TextField getQuestion() {
        return null;
               
    }
    
    public TextField getTopic() {
        return null;
        
    }

    public TextField getImage() {
        return null;
        
    }
    
    public List<TextField> getChoiceTexts() {
        return choiceTexts;
        
    }
    
    public VBox getNode() {
        return form;
        
    }
    
    public List<ToggleGroup> getChoiceGroups() {
        return choiceGroups;
        
    }
}
