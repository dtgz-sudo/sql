package cn.sdut.domain;

import java.io.Serializable;

public class Category  implements Serializable {
    private Integer cid;

    private String categoryname;

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

    @Override
    public String toString() {
        return "Category{" +
                "cid=" + cid +
                ", categoryname='" + categoryname + '\'' +
                '}';
    }
}