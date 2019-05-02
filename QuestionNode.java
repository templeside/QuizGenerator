package application;

import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class QuestionNode {
    
    private VBox node;
    private ToggleGroup choices;
    private ImageView imageView;
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
    
    public VBox getNode() {
        return this.node;
    }
    
    public ToggleGroup getChoices() {
        return this.choices;
    }
}
