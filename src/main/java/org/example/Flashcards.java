package org.example;

import javax.swing.*;
import java.util.*;

public class Flashcards {
    static LinkedHashMap<String, String> flashcardsMap = new LinkedHashMap<>();
    static Iterator<Map.Entry<String, String>> iterator;
    static public Integer score = 0;
    static public Integer maxScore = 0;
    static public Integer numCards = 0;


    public static Integer getScore(){
        return score;
    }

    public static void setScore(String val) {
        if (Objects.equals(val, "plus")) score += 1;
        else if (Objects.equals(val, "minus")){
            if (score - 1 >= 0) score -= 1;
        }
        else if (Objects.equals(val, "zero")) score = 0;
    }

    public static void  addFlashcard(String question, String answer){
        flashcardsMap.put(question, answer);
        numCards += 1;
    }

    public static String[] nextCard(){
        if(iterator == null){
            iterator = flashcardsMap.entrySet().iterator();
        }

        if(iterator.hasNext()) {
            String[] flashcard = new String[2];
            Map.Entry<String, String> entry = iterator.next();

            flashcard[0] = entry.getKey();
            flashcard[1] = entry.getValue();
            return flashcard;
        }else{
            return null;
        }
    }

    public static Boolean isItFinal(){
        return !iterator.hasNext();
    }

    public static void resetIterator(){
        iterator = null;
    }

    public static void deleteCards(){
        flashcardsMap = new LinkedHashMap<>();
        iterator = null;
        numCards = 0;
    }

}
