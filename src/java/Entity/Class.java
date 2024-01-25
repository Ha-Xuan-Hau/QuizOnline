/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author admin
 */
public class Class {
    int ClassId;
    String ClassName;
    int TeacherAccountId;
    String CreateDate;

    public Class() {
    }

    public Class(int ClassId, String ClassName, int TeacherAccountId, String CreateDate) {
        this.ClassId = ClassId;
        this.ClassName = ClassName;
        this.TeacherAccountId = TeacherAccountId;
        this.CreateDate = CreateDate;
    }

    public Class(String ClassName, int TeacherAccountId, String CreateDate) {
        this.ClassName = ClassName;
        this.TeacherAccountId = TeacherAccountId;
        this.CreateDate = CreateDate;
    }
    

    public int getClassId() {
        return ClassId;
    }

    public void setClassId(int ClassId) {
        this.ClassId = ClassId;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String ClassName) {
        this.ClassName = ClassName;
    }

    public int getTeacherAccountId() {
        return TeacherAccountId;
    }

    public void setTeacherAccountId(int TeacherAccountId) {
        this.TeacherAccountId = TeacherAccountId;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String CreateDate) {
        this.CreateDate = CreateDate;
    }

    @Override
    public String toString() {
        return "Class{" + "ClassId=" + ClassId + ", ClassName=" + ClassName + ", TeacherAccountId=" + TeacherAccountId + ", CreateDate=" + CreateDate + '}';
    }
    
}