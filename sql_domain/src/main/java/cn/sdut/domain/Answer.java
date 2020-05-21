package cn.sdut.domain;

import java.io.Serializable;
import java.util.Date;

public class Answer implements Serializable {
    @Override
    public String toString() {
        return "Answer{" +
                "aid=" + aid +
                ", pid=" + pid +
                ", sid=" + sid +
                ", language='" + language + '\'' +
                ", input='" + input + '\'' +
                ", output='" + output + '\'' +
                ", score=" + score +
                ", createdate=" + createdate +
                '}';
    }

    private Integer aid;

    private Integer pid;

    private Integer sid;

    private String language;

    private String input;

    private String output;

    private Double score;

    private Date createdate;

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language == null ? null : language.trim();
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input == null ? null : input.trim();
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output == null ? null : output.trim();
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}