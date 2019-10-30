package com.project.xeroserver.Model;

public class Sender {
    public String to;
    public Notification notification;

    public Sender(String to, Notification not) {
        this.to = to;
        this.notification = not;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }
}
