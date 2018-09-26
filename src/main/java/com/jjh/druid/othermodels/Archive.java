package com.jjh.druid.othermodels;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "ARCHIVE")
public class Archive {

    @Id
    private String ID = UUID.randomUUID().toString();
    @Column
    private String INFO;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getINFO() {
        return INFO;
    }

    public void setINFO(String INFO) {
        this.INFO = INFO;
    }

}
