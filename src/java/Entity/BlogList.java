/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author Asus
 */
public class BlogList {
    private int SubjectId;
    private String Title;
    private String Username;
    private int SetVote;

    public BlogList() {
    }

    public BlogList(int SubjectId, String Title, String Username, int SetVote) {
        this.SubjectId = SubjectId;
        this.Title = Title;
        this.Username = Username;
        this.SetVote = SetVote;
    }

    public int getSubjectId() {
        return SubjectId;
    }

    public void setSubjectId(int SubjectId) {
        this.SubjectId = SubjectId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public int getSetVote() {
        return SetVote;
    }

    public void setSetVote(int SetVote) {
        this.SetVote = SetVote;
    }

    @Override
    public String toString() {
        return "BlogList{" + "SubjectId=" + SubjectId + ", Title=" + Title + ", Username=" + Username + ", SetVote=" + SetVote + '}';
    }

    

   
    
    
    
}
