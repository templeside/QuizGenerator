package application;

public class Choice {

    public boolean isCorrect;
    public String choice;
    
    public Choice(boolean correctAnswer, String newChoice) {
        this.isCorrect = correctAnswer;
        this.choice = newChoice;
    }
    
    public String getChoice() {
        return this.choice;
    }
    
    public boolean getIsCorrect() {
        return this.isCorrect;
    }
}
