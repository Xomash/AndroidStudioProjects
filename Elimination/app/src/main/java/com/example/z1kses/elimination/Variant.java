package com.example.z1kses.elimination;

import java.io.Serializable;
import java.security.Timestamp;

/**
 * Created by Z1kseS on 15.04.2016.
 */
public class Variant implements Serializable{

    private static final long serialVersionUID = 1L;

    private String imageSrc;
    private boolean right;
    private int taskId;
    private int variantId;

    private String reason;
    private String sender;

    private Timestamp dateTime;

    public Variant(String imageSrc, boolean right, int taskId, int variantId) {
        this.imageSrc = imageSrc;
        this.right = right;
        this.taskId = taskId;
        this.variantId = variantId;
    }


    public Timestamp getDateTime(){
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime){
        this.dateTime = dateTime;
    }

    public void setRight(boolean right){
        this.right = right;
    }

    public String getSender(){
        return sender;
    }

    public void setSender(String sender){
        this.sender = sender;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public int getVariantId() {
        return variantId;
    }

    public void setVariantId(int variantId){
        this.variantId = variantId;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public boolean isRight() {
        return right;
    }

}
