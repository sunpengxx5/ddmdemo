package com.huawei.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huawei.Utils.CommonUtils;
import com.huawei.controller.DdmController;
import com.huawei.dao.Sbtest1Dao;
import com.huawei.projo.Sbtest1;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DdmService {

    private static Logger log = Logger.getLogger(DdmService.class);

    @Autowired
    private Sbtest1Dao sbtest1Service;

    public String syntheticTest(){
        String result = "";
        JSONObject jsonObject = new JSONObject();
        try {
            result = sbtest1Service.syntheticTest();
            jsonObject.put("errCode",CommonUtils.NOMAL_CODE);
            jsonObject.put("result(ms)",result);
        }catch (DuplicateKeyException e){
            jsonObject.put("errCode",CommonUtils.NOMAL_CODE);
            jsonObject.put("result(ms)",e);
            log.error(e);
        }catch (Exception e){
            jsonObject.put("errCode",CommonUtils.ERROR_CODE);
            jsonObject.put("errMsg",e.getCause() + ":" + e.getMessage());
            log.error(e);
        }
        return jsonObject.toJSONString();
    }


    public String singleSelect(String sql){
        JSONObject jsonObject = new JSONObject();
        try {
            Sbtest1 sbtest1 = sbtest1Service.singleSelect(sql);

            jsonObject.put("errCode",CommonUtils.NOMAL_CODE);
            JSONObject bodyJson = JSONObject.parseObject(sbtest1.toString());
            JSONArray jsonArray = new JSONArray();
            jsonArray.add(bodyJson);
            jsonObject.put("bodyArray",jsonArray);

        }catch (Exception e){
            jsonObject.put("errCode",CommonUtils.ERROR_CODE);
            jsonObject.put("errMsg",e.getCause() + ":" + e.getMessage());
            log.error(e);
        }
        return jsonObject.toJSONString();
    }

    public String bathSelect(String sql){
        JSONObject jsonObject = new JSONObject();
        try {
            List<Sbtest1> list = sbtest1Service.bathSelect(sql);
            jsonObject.put("errCode",CommonUtils.NOMAL_CODE);
            JSONArray jsonArray = JSONArray.parseArray(list.toString());
            jsonObject.put("body",jsonArray);

        }catch (Exception e){
            jsonObject.put("errCode",CommonUtils.ERROR_CODE);
            jsonObject.put("errMsg",e.getCause() + ":" + e.getMessage());
            log.error(e);
        }
        return jsonObject.toJSONString();
    }

    public String write(String sql){

        JSONObject jsonObject = new JSONObject();
        try {
            int result = sbtest1Service.update(sql);
            jsonObject.put("errCode",CommonUtils.NOMAL_CODE);
            jsonObject.put("result",result);

        }catch (Exception e){
            jsonObject.put("errCode",CommonUtils.ERROR_CODE);
            jsonObject.put("errMsg",e.getCause() + ":" + e.getMessage());
            log.error(e);
        }
        return jsonObject.toJSONString();
    }


    public String transaction(){

        JSONObject jsonObject = new JSONObject();
        try {
            String result = sbtest1Service.transaction();
            jsonObject.put("errCode",CommonUtils.NOMAL_CODE);
            jsonObject.put("result",result);

        }catch (Exception e){
            jsonObject.put("errCode",CommonUtils.ERROR_CODE);
            jsonObject.put("errMsg",e.getCause() + ":" + e.getMessage());
            log.error(e);
        }
        return jsonObject.toJSONString();
    }

}
