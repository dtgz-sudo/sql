package cn.sdut.domain;

import java.util.Date;
import java.util.List;

public class Category {
    private Integer cid;

    private String categoryname;

    private Date starttime;

    private Date endtime;
private List<Problem> problemsList;

    @Override
    public String toString() {
        return "Category{" +
                "cid=" + cid +
                ", categoryname='" + categoryname + '\'' +
                ", starttime=" + starttime +
                ", endtime=" + endtime +
                ", problemsList=" + problemsList +
                '}';
    }

    public List<Problem> getProblemsList() {
        return problemsList;
    }

    public void setProblemsList(List<Problem> problemsList) {
        this.problemsList = problemsList;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname == null ? null : categoryname.trim();
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }
}