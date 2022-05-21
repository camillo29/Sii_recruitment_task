package com.sii.sii_recruitment_task.Requests;

public class ChangeMailRequest {
    String oldMail;
    String newMail;

    public String getOldMail() {
        return oldMail;
    }

    public void setOldMail(String oldMail) {
        this.oldMail = oldMail;
    }

    public String getNewMail() {
        return newMail;
    }

    public void setNewMail(String newMail) {
        this.newMail = newMail;
    }
}
