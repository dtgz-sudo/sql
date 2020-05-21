package cn.sdut.domain;

import java.util.Date;
import java.util.List;


public class Category {


    private Integer cid;

    private String categoryname;

    private Date starttime;

    private Date endtime;
    private List<Problem> problemList;

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
        this.categoryname = categoryname;
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

    public List<Problem> getProblemList() {
        return problemList;
    }

    public void setProblemList(List<Problem> problemList) {
        this.problemList = problemList;
    }

    @Override
    public String toString() {
        return "Category{" +
                "cid=" + cid +
                ", categoryname='" + categoryname + '\'' +
                ", starttime=" + starttime +
                ", endtime=" + endtime +
                ", problemList=" + problemList +
                '}';
    }
}