/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.entities;

/**
 *
 * @author timoumi med hedi
 */
public class mail {
    String from ;
    String subject;
    String text;
    String seen;
    int i;
    public String getFrom() {
        return from;
    }

    @Override
    public String toString() {
        return "mail{"+i + "from=" + from + ", subject=" + subject + ", text=" + text + ", seen=" + seen + '}';
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSeen() {
        return seen;
    }

    public void setSeen(String seen) {
        this.seen = seen;
    }

    public mail(int i,String from, String subject, String text, String seen) {
        this.from = from;
        this.subject = subject;
        this.text = text;
        this.seen = seen;
        this.i=i;
    }
    
}