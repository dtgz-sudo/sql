package cn.sdut.domain;

import java.io.Serializable;
import java.util.Date;

public class Answer implements Serializable {


    private Integer aid;

    private Integer pid;

    private Integer sid;
    private Integer  tid;

    private String language;

    private String input;

    private String output;

    private Double score;

    private Date createdate;
    private Problem problem;

    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

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

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    @Override
    public String   toString() {
        return "Answer{" +
                "aid=" + aid +
                ", pid=" + pid +
                ", sid=" + sid +
                ", tid=" + tid +
                ", language='" + language + '\'' +
                ", input='" + input + '\'' +
                ", output='" + output + '\'' +
                ", score=" + score +
                ", createdate=" + createdate +
                ", problem=" + problem +
                ", comment='" + comment + '\'' +
                '}';
    }
}