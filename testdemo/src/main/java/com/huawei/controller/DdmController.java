package com.huawei.controller;

import com.alibaba.fastjson.JSONObject;
import com.huawei.Utils.CommonUtils;
import com.huawei.service.DdmService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class DdmController {

    private static Logger log = Logger.getLogger(DdmController.class);

    int count = 0;

    @Autowired
    private DdmService ddmService;

    @RequestMapping(value="v1/rest/test", method = RequestMethod.GET)
    @ResponseBody
    public int test(HttpServletRequest request){
        count ++;
        return count;
    }

    @RequestMapping(value="v1/rest/test2", method = RequestMethod.GET)
    @ResponseBody
    public int test2(HttpServletRequest request){
        count ++;
        return count;
    }

    @RequestMapping(value="v1/rest/testGet", method = RequestMethod.GET)
    @ResponseBody
    public String testGet(HttpServletRequest request){
        String username=request.getParameter("username");
        String password=request.getParameter("password");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("errCode", CommonUtils.NOMAL_CODE);
        jsonObject.put("method","Get");
        jsonObject.put("userName",username);
        jsonObject.put("password",password);

        return jsonObject.toJSONString();
    }

    @RequestMapping(value="v1/rest/testPost", method = RequestMethod.POST)
    @ResponseBody
    public String testPost(@RequestBody Map<String, Object> param){
        String username=param.get("userName").toString();
        String password=param.get("password").toString();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("errCode",CommonUtils.NOMAL_CODE);
        jsonObject.put("method","Post");
        jsonObject.put("userName",username);
        jsonObject.put("password",password);

        return jsonObject.toJSONString();
    }

    @RequestMapping(value="v1/rest/syntheticTest", method = RequestMethod.GET)
    @ResponseBody
    public String syntheticTest(){
        return ddmService.syntheticTest();
    }


    @RequestMapping(value="v1/rest/singleSelect", method = RequestMethod.POST)
    @ResponseBody
    public String singleSelect(@RequestBody Map<String, Object> param){

        String sql=param.get("sql").toString();

        return ddmService.singleSelect(sql);
    }


    @RequestMapping(value="v1/rest/bathSelect", method = RequestMethod.POST)
    @ResponseBody
    public String bathSelect(@RequestBody  Map<String, Object> param){

        String sql=param.get("sql").toString();

        return ddmService.bathSelect(sql);
    }

    @RequestMapping(value="v1/rest/write", method = RequestMethod.POST)
    @ResponseBody
    public String write(@RequestBody Map<String, Object> param){

        String sql=param.get("sql").toString();

        return ddmService.write(sql);
    }

    @RequestMapping(value="v1/rest/transaction", method = RequestMethod.GET)
    @ResponseBody
    public String transaction(){
//        return new DdmService().transaction(sbtest1Service);
        return  ddmService.transaction();
    }

}
