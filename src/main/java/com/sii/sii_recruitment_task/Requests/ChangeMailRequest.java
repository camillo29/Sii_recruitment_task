package com.sii.sii_recruitment_task.Requests;

public class ChangeMailRequest {
    private final String oldMail;
    private final String newMail;

    public ChangeMailRequest(String oldMail, String newMail) {
        this.oldMail = oldMail;
        this.newMail = newMail;
    }

    public String getOldMail() {
        return oldMail;
    }

    public String getNewMail() {
        return newMail;
    }

}
