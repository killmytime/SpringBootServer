package com.adweb.adwebserver.domain;

import com.alibaba.fastjson.JSONArray;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;
@Component
@Entity
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class Directory {
    private int directoryId;
    private JSONArray list;

    @Id
    @Column(name = "DirectoryID")
    public int getDirectoryId() {
        return directoryId;
    }

    public void setDirectoryId(int directoryId) {
        this.directoryId = directoryId;
    }
    @Type(type = "json")
    @Column(name = "List")
    public JSONArray getList() {
        return list;
    }

    public void setList(JSONArray list) {
        this.list = list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Directory directory = (Directory) o;
        return directoryId == directory.directoryId &&
                Objects.equals(list, directory.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(directoryId, list);
    }
}
