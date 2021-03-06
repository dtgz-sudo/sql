package cn.sdut.domain;

import java.util.Date;

public class Problem {
    private Integer pid;

    private String title;

    private String description;

    private String input;

    private String output;

    private String hint;

    private Date createdate;

    private String language;

    private Integer cid;

    private String exampleinput;

    private String exampleoutput;

    private String tablename;

    private Integer tid;

    private Integer hid;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint == null ? null : hint.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language == null ? null : language.trim();
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getExampleinput() {
        return exampleinput;
    }

    public void setExampleinput(String exampleinput) {
        this.exampleinput = exampleinput == null ? null : exampleinput.trim();
    }

    public String getExampleoutput() {
        return exampleoutput;
    }

    public void setExampleoutput(String exampleoutput) {
        this.exampleoutput = exampleoutput == null ? null : exampleoutput.trim();
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename == null ? null : tablename.trim();
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getHid() {
        return hid;
    }

    public void setHid(Integer hid) {
        this.hid = hid;
    }
}