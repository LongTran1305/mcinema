package com.projects4.mcinema.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FAQs {
    private int faqsid;
    private  String question;
    private String answer;

    public FAQs() {
    }

    public FAQs(int faqsid, String question, String answer) {
        this.faqsid = faqsid;
        this.question = question;
        this.answer = answer;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getFaqsid() {
        return faqsid;
    }

    public void setFaqsid(int faqsid) {
        this.faqsid = faqsid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
