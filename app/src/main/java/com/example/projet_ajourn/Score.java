package com.example.projet_ajourn;

public class Score {
    private final long id;
    private String name, result;
    private float mark;

    public Score(long id, String name, float mark){
        this.id = id;
        this.name = name;
        this.mark = mark;
        if(mark<10){
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

    public float getMark() {
        return mark;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setResult(String result) { this.result = result; }

    public void setMark(float mark) {
        this.mark = mark;
    }
}
