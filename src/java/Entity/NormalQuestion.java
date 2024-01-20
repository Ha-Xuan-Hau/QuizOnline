/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author phamg
 */
public class NormalQuestion {
    private int quesId;
    private String content;

    public NormalQuestion() {
    }

    public NormalQuestion(int quesId, String content) {
        this.quesId = quesId;
        this.content = content;
    }

    public NormalQuestion(String content) {
        this.content = content;
    }

    public int getQuesId() {
        return quesId;
    }

    public String getContent() {
        return content;
    }

    public void setQuesId(int quesId) {
        this.quesId = quesId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "NormalQuestion{" + "quesId=" + quesId + ", content=" + content + '}';
    }
    
}
