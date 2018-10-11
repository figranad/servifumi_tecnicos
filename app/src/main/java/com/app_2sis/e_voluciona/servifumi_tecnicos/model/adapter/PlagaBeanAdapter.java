package com.app_2sis.e_voluciona.servifumi_tecnicos.model.adapter;

public class PlagaBeanAdapter {
    private boolean isCheck;
    private String text;
    private String plagaID;

    public PlagaBeanAdapter() {
    }

    public PlagaBeanAdapter(String text, String plagaID) {
        this.isCheck = false;
        this.text = text;
        this.plagaID = plagaID;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPlagaID() {
        return plagaID;
    }

    public void setPlagaID(String plagaID) {
        this.plagaID = plagaID;
    }
}
