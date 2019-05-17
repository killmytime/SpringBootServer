package com.adweb.adwebserver.domain;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.stereotype.Component;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;
@TypeDef(name = "json", typeClass = JsonStringType.class)
@Component
@Entity
public class Content {
    private int contentId;
    private String contentName;
    private JSONArray dialog;
    private JSONObject question;

    @Id
    @Column(name = "contentID")
    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    @Basic
    @Column(name = "content_name")
    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    @Basic
    @Type(type = "json")
    @Column(name = "dialog")
    public JSONArray getDialog() {
        return dialog;
    }

    public void setDialog(JSONArray dialog) {
        this.dialog = dialog;
    }

    @Basic
    @Type(type = "json")
    @Column(name = "question")
    public JSONObject getQuestion() {
        return question;
    }

    public void setQuestion(JSONObject question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Content content = (Content) o;
        return contentId == content.contentId &&
                Objects.equals(contentName, content.contentName) &&
                Objects.equals(dialog, content.dialog) &&
                Objects.equals(question, content.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contentId, contentName, dialog, question);
    }
}
