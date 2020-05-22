package cn.sdut.entity;

import java.io.Serializable;

public class Alldata implements Serializable {
    private int num0;//0分人数
    private int num50;//50分人数
    private int num70;//70分人数
    private int num100;//100分人数
    private int pid;//题号

    public int getNum0() {
        return num0;
    }

    public void setNum0(int num0) {
        this.num0 = num0;
    }

    public int getNum50() {
        return num50;
    }

    public void setNum50(int num50) {
        this.num50 = num50;
    }

    public int getNum70() {
        return num70;
    }

    public void setNum70(int num70) {
        this.num70 = num70;
    }

    public int getNum100() {
        return num100;
    }

    public void setNum100(int num100) {
        this.num100 = num100;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "Alldata{" +
                "num0=" + num0 +
                ", num50=" + num50 +
                ", num70=" + num70 +
                ", num100=" + num100 +
                ", pid=" + pid +
                '}';
    }
}
