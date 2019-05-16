package com.adweb.adwebserver.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class Directory {
    private int directoryId;
    private List<Object> list;

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
    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
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
