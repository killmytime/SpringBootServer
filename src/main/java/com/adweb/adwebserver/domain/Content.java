package com.adweb.adwebserver.domain;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import io.swagger.models.auth.In;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;
@TypeDef(name = "json", typeClass = JsonStringType.class)
@Component
@Entity
public class Content {
    private Integer generateId;
    private String contentId;
    private String contentName;
    private JSONArray dialog;
    private JSONObject question;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "generateID")
    public Integer getGenerateId(){return generateId;}

    public void setGenerateId(Integer generateId){this.generateId=generateId;}

    @Basic
    @Column(name = "contentID")
    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
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
        return contentId.equals(content.contentId) &&
                Objects.equals(contentName, content.contentName) &&
                Objects.equals(dialog, content.dialog) &&
                Objects.equals(question, content.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contentId, contentName, dialog, question);
    }

    @Override
    public String toString() {
        return "Content{" +
                "contentId='" + contentId + '\'' +
                ", contentName='" + contentName + '\'' +
                ", dialog=" + dialog +
                ", question=" + question +
                '}';
    }
}
