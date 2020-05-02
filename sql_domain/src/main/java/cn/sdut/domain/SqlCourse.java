package cn.sdut.domain;

import java.io.Serializable;

public class SqlCourse implements Serializable {
    private Integer cno;

    private String cname;

    public Integer getCno() {
        return cno;
    }

    public void setCno(Integer cno) {
        this.cno = cno;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }
}