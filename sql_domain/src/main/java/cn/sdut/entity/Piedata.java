package cn.sdut.entity;

import java.io.Serializable;

public class Piedata implements Serializable {
    private long allvalue;
    private long partvalue;
    private long notvalue;

    @Override
    public String toString() {
        return "Piedata{" +
                "allvalue=" + allvalue +
                ", partvalue=" + partvalue +
                ", notvalue=" + notvalue +
                '}';
    }

    public long getAllvalue() {
        return allvalue;
    }

    public void setAllvalue(long allvalue) {
        this.allvalue = allvalue;
    }

    public long getPartvalue() {
        return partvalue;
    }

    public void setPartvalue(long partvalue) {
        this.partvalue = partvalue;
    }

    public long getNotvalue() {
        return notvalue;
    }

    public void setNotvalue(long notvalue) {
        this.notvalue = notvalue;
    }
}
