package application;

import java.util.List;

public class Question {
    private String metadata;
    private String question;
    private String topic;
    private String image;
    private List<Choice> choices;
    private String answer;

    public Question(String newMetadata, String newQuestion, String newTopic, String newImage,
        List<Choice> newChoices, String newAnswer) {
        this.metadata = newMetadata;
        this.question = newQuestion;
        this.topic = newTopic;
        this.image = newImage;
        this.choices = newChoices;
        this.answer = newAnswer;
    }

    public String getQuestion() {
        return this.question;
    }

    public List<Choice> getChoices() {
        return this.choices;
    }

    public String getAnswer() {
        return this.answer;
    }

    public String getMetadata() {
        return this.metadata;
    }

    public String getTopic() {
        return this.topic;
    }

    public String getImage() {
        return this.image;
    }
}
