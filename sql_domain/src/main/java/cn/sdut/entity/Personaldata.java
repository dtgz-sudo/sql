package cn.sdut.entity;

import java.io.Serializable;

public class Personaldata implements Serializable {
    private double score;
    private int pid;

    @Override
    public String toString() {
        return "Personaldata{" +
                "score=" + score +
                ", pid=" + pid +
                '}';
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
}
