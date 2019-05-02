package application;

import java.util.List;

/**
  *This is the Question class.
  */
public class Question {
    private String metadata;
    private String question;
    private String topic;
    private String image;
    private List<Choice> choices;
    private String answer;

    /**
      *This is the constructor for the class.
      */
    public Question(String newMetadata, String newQuestion, String newTopic, String newImage,
        List<Choice> newChoices, String newAnswer) {
        this.metadata = newMetadata;
        this.question = newQuestion;
        this.topic = newTopic;
        this.image = newImage;
        this.choices = newChoices;
        this.answer = newAnswer;
    }

    /**
      *@return the question
      */
    public String getQuestion() {
        return this.question;
    }

    /**
      *@return the choices for the Question
      */
    public List<Choice> getChoices() {
        return this.choices;
    }

    /**
      *@return the answer for the Question
      */
    public String getAnswer() {
        return this.answer;
    }

    /**
      *@return the metadata for the Question
      */
    public String getMetadata() {
        return this.metadata;
    }

    /**
      *@return the topic for the Question
      */
    public String getTopic() {
        return this.topic;
    }

    /**
      *@return the image for the Question
      */
    public String getImage() {
        return this.image;
    }
}
