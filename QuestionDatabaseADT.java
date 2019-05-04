//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION ////////////////////////
// Title:           ATEAM PROJECT                                               //
// Files:           AddQuestionFormNode, Choice, Main, QuestionNode             //
//                  Question, QuestionDatabase, QuestionDatabaseADT             //  
// Course:          CS 400, Spring 2018                                         //
// Author:          ATEAM106 (Andrew Lutkus, Chanwoong Jhon,                    //
//                            Elaheh Jabbarifard, Chaiyeen Oh, Sara Haines)     //
// Email:           alutkus@wisc.edu, cjhon@wisc.edu,                           //
//                  jabbarifard@wisc.edu, coh26@wisc.edu, schaines@wisc.edu     //
// Lecturer's Name: Andrew Kuemmel, Deb Deppeler                                //
// Due Date : 5/2/2019 10pm                                                     //
//                                                                              //
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:            //
//   _X_ Write-up states that pair programming is allowed for this assignment.  //
//   _X_ We have both read and understand the course Pair Programming Policy.   //
//   _X_ We have registered our team prior to the team registration deadline.   //
//                                                                              //
///////////////////////////// CREDIT OUTSIDE HELP ////////////////////////////////
//                                                                              //
// Students who get help from sources other than their partner must fully       //
// acknowledge and credit those sources of help here.  Instructors and TAs do   //
// not need to be credited here, but tutors, friends, relatives, room mates,    //
// strangers, and others do.  If you received no outside help from either type  //
//  of source, then please explicitly indicate NONE.                            //
//                                                                              //
// Persons:         X                                                           //
// Online Sources:  X                                                           //
//                                                                              //
/////////////////////////////// 80 COLUMNS WIDE //////////////////////////////////
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
