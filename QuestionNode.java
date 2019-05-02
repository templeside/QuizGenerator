package application;

import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
  *This is the QuestionNode class.
  */
public class QuestionNode {
    
    private VBox node;
    private ToggleGroup choices;
    
    /**
      *This is the constructor for the class.
      *@param q is the Question used to make the QuestionNode
      */
    public QuestionNode(Question q) {
        Image image = new Image(q.getImage());
        ImageView imageView = new ImageView(image);
        Label questionLb = new Label(q.getQuestion());
        choices = (ToggleGroup) q.getChoices();
        
        node.getChildren().addAll(imageView, questionLb);//adds image and question to node
    }
    
    /**
      *@return node
      */
    public VBox getNode() {
        return this.node;
    }
    
    /**
      *@return choices
      */
    public ToggleGroup getChoices() {
        return this.choices;
    }
}
