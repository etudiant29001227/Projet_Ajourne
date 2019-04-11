package com.example.projet_ajourn;

public class Score {
    private final long id;
    private String name, result;
    private float grade;

    public Score(long id, String name, float grade){
        this.id = id;
        this.name = name;
        this.grade = grade;
        if(grade<10){
            result = "AJR";
        }else{
            result = "ADM";
        }
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getResult() { return result; }

    public float getGrade() {
        return grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setResult(String result) { this.result = result; }

    public void setGrade(float grade) {
        this.grade = grade;
    }
}
