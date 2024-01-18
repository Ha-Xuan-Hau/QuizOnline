/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author Asus
 */
public class QuestionSet {
    private int SetId;
    private int UserAccountId;
    private int SubjectId;
    private int QuesId;
    private int SetVote;

    public QuestionSet() {
    }

    public QuestionSet(int SetId, int UserAccountId, int SubjectId, int QuesId, int SetVote) {
        this.SetId = SetId;
        this.UserAccountId = UserAccountId;
        this.SubjectId = SubjectId;
        this.QuesId = QuesId;
        this.SetVote = SetVote;
    }

    public int getSetId() {
        return SetId;
    }

    public void setSetId(int SetId) {
        this.SetId = SetId;
    }

    public int getUserAccountId() {
        return UserAccountId;
    }

    public void setUserAccountId(int UserAccountId) {
        this.UserAccountId = UserAccountId;
    }

    public int getSubjectId() {
        return SubjectId;
    }

    public void setSubjectId(int SubjectId) {
        this.SubjectId = SubjectId;
    }

    public int getQuesId() {
        return QuesId;
    }

    public void setQuesId(int QuesId) {
        this.QuesId = QuesId;
    }

    public int getSetVote() {
        return SetVote;
    }

    public void setSetVote(int SetVote) {
        this.SetVote = SetVote;
    }

    @Override
    public String toString() {
        return "questionSet{" + "SetId=" + SetId + ", UserAccountId=" + UserAccountId + ", SubjectId=" + SubjectId + ", QuesId=" + QuesId + ", SetVote=" + SetVote + '}';
    }
    
    
}
