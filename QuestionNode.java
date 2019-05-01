package application;

import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class QuestionNode {
    
    private VBox node;
    private ToggleGroup choices;
    
    public QuestionNode(Question q) {
        Image image = new Image(q.getImage());
        ImageView imageView = new ImageView(image);
        Label questionLb = new Label(q.getQuestion());
        choices = (ToggleGroup) q.getChoices();
        
        node.getChildren().addAll(imageView, questionLb);
    }
    
    public VBox getNode() {
        return this.node;
    }
    
    public ToggleGroup getChoices() {
        return this.choices;
    }
}
