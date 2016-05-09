package com.idyoshin.webscoketdemo.modules.longprocess.entity;

/**
 * Created by ilyadyoshin on 09.05.16.
 */
public class LongProcessStatus {

    private String id;
    private Long current;
    private Long target;

    public LongProcessStatus(String id, Long current, Long target) {
        this.id = id;
        this.current = current;
        this.target = target;
    }

    public String getId() {
        return id;
    }

    public Long getCurrent() {
        return current;
    }

    public Long getTarget() {
        return target;
    }

    @Override
    public String toString() {
        return "LongProcessStatus{" +
            "target=" + target +
            ", current=" + current +
            ", id='" + id + '\'' +
            '}';
    }
}
