package com.example.apoorva.hospizone;

/**
 * Created by Apoorva on 16-Aug-17.
 */

public class DocListCard {

    String docName;
    String degree;
    String experience;

    public DocListCard(){

    }

    public DocListCard(String docName, String degree,String experience) {
        this.docName = docName;
        this.degree = degree;
        this.experience = experience;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String title) {
        this.docName = title;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

}
