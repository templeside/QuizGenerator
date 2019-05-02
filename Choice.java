package application;
/**
  *This is the Choice class.
  */
public class Choice {

    public boolean isCorrect;
    public String choice;
    
    /**
	 *This is the constructor for the class.
	 */
    public Choice(boolean correctAnswer, String newChoice) {
        this.isCorrect = correctAnswer;
        this.choice = newChoice;
    }
    
    /**
	 *@return the choice.
	 */
    public String getChoice() {
        return this.choice;
    }
    
    /**
	 *@return true if the choice is correct, false if the choice is incorrect
	 */
    public boolean getIsCorrect() {
        return this.isCorrect;
    }
}
