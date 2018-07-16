package com.huawei.projo;

import com.alibaba.fastjson.JSONObject;

import javax.persistence.Entity;
import java.util.HashMap;

@Entity
public class Sbtest1 {
    private long id;
    private int k;
    private String c;
    private String pad;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getPad() {
        return pad;
    }

    public void setPad(String pad) {
        this.pad = pad;
    }

    @Override
    public String toString() {

        JSONObject jsonObject =new JSONObject();
        jsonObject.put("id",id+"");
        jsonObject.put("k",k+"");
        jsonObject.put("c",c);
        jsonObject.put("pad",pad);

        return jsonObject.toJSONString();
    }
}
