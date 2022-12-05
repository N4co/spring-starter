package com.felixweb.projeto.resources.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;

import java.io.Serializable;
@ControllerAdvice
public class StandardErrors implements Serializable {
    static final long serialVersionUID = 1L;


    private Integer status;
    private String msg;
    private Long timeStamp;

    public StandardErrors() {
    }

    public StandardErrors(Integer status, String msg, Long timeStamp) {
        this.status = status;
        this.msg = msg;
        this.timeStamp = timeStamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
