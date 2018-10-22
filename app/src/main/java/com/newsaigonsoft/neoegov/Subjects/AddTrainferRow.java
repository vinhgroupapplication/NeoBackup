package com.newsaigonsoft.neoegov.Subjects;

import java.util.ArrayList;

/**
 * Created by Vinh on 8/28/2017.
 */

public class AddTrainferRow {
    String TrainferPeplo;
    String Time;
    String Content;
    ArrayList<AttachFile> AttackFile;

    public AddTrainferRow(String trainferPeplo, String time, String content, ArrayList<AttachFile> attackFile) {
        TrainferPeplo = trainferPeplo;
        Time = time;
        Content = content;
        AttackFile = attackFile;
    }

    public String getTrainferPeplo() {
        return TrainferPeplo;
    }

    public void setTrainferPeplo(String trainferPeplo) {
        TrainferPeplo = trainferPeplo;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public ArrayList<AttachFile> getAttackFile() {
        return AttackFile;
    }

    public void setAttackFile(ArrayList<AttachFile> attackFile) {
        AttackFile = attackFile;
    }
}
