/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author admin
 */
public class Content {
    private String QuizContent;
    private String QuizAnswer;

    public Content() {
    }

    public Content(String QuizContent, String QuizAnswer) {
        this.QuizContent = QuizContent;
        this.QuizAnswer = QuizAnswer;
    }

    public String getQuizContent() {
        return QuizContent;
    }

    public void setQuizContent(String QuizContent) {
        this.QuizContent = QuizContent;
    }

    public String getQuizAnswer() {
        return QuizAnswer;
    }

    public void setQuizAnswer(String QuizAnswer) {
        this.QuizAnswer = QuizAnswer;
    }

    @Override
    public String toString() {
        return "Content{" + "QuizContent=" + QuizContent + ", QuizAnswer=" + QuizAnswer + '}';
    }

    
}
