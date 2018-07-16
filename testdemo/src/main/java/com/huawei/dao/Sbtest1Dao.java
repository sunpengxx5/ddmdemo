package com.huawei.dao;

import com.huawei.projo.Sbtest1;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Repository
public class Sbtest1Dao {
    private Random random =new Random();
    private static int maxId = 100000;
    @Resource
    private NamedParameterJdbcTemplate namedjdbctemp;

//    @Transactional
    public Sbtest1 querySbtest1ById(String id){
        String sql = "select * from sbtest1 where id =:id";
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        return namedjdbctemp.queryForObject(sql, map,new BeanPropertyRowMapper<Sbtest1>(Sbtest1.class));
    }

//    @Transactional
    public Sbtest1 singleSelect(String sql){
        Map<String, Object> map = new HashMap<>();
        Sbtest1 sbtest1 = null;
        try {
            sbtest1 = namedjdbctemp.queryForObject(sql, map,new BeanPropertyRowMapper<Sbtest1>(Sbtest1.class));
        }catch (EmptyResultDataAccessException e){
            sbtest1 = new Sbtest1();
        }
        return sbtest1;
    }

//    @Transactional
    public List<Sbtest1> bathSelect(String sql){
        Map<String, Object> map = new HashMap<>();
        List<Sbtest1> list =namedjdbctemp.query(sql,map,new BeanPropertyRowMapper<Sbtest1>(Sbtest1.class));
        return list;
    }

//    @Transactional
    public int update(String sql){
        return namedjdbctemp.update(sql, new HashMap<String,String>());
    }

//    @Transactional
    public String syntheticTest(){

        String result = "";

        int id = random.nextInt(maxId);

        result += singleSelectWithDelay("select * from sbtest1 where id=" + id);

        result = result + " " + singleSelectWithDelay("select c from sbtest1 where id=" + id);

        result = result + " " + bathSelectWithDelay("select c from sbtest1 where id between " + id + " and " + (id + 5));

        result = result + " " + bathSelectWithDelay("select k from sbtest1 where id between " + id + " and " + (id + 5));

        result = result + " " + bathSelectWithDelay("select sum(k) from sbtest1 where id between " + id + " and " + (id + 5));

        result = result + " " + bathSelectWithDelay("select c from sbtest1 where id between " + id + " and " + (id + 5) + " order by c");

        result = result + " " + bathSelectWithDelay("select distinct c from sbtest1 where id between  " + id + " and " + (id + 5) +" order by c");

        //write
        result = result + " " + updateWithDelay("update sbtest1  set c='" + UUID.randomUUID().toString() + "' where id=" + id);

        long insertId = System.nanoTime();

        result = result + " " + updateWithDelay("insert into  sbtest1 (id, k, c, pad) values(" + insertId + ",234,'test','test')");

        result = result + " " + updateWithDelay("delete from  sbtest1  where id=" + insertId);

        return result;
    }

    @Transactional
    public String transaction(){
        String result = "";
        long id = 100000;
//                System.nanoTime();
        result = result + update("insert into  sbtest1 (id, k, c, pad) values(" + id + ",234,'test','test')");
        int x = 8/0;
        result = result + " " + update("update sbtest1  set c='" + UUID.randomUUID().toString() + "' where id=" + id);
//        updateWithDelay("delete from  sbtest1  where id=" + insertId);
        return result;
    }

    private long singleSelectWithDelay(String sql){
        long start = System.currentTimeMillis();
        singleSelect(sql);
        long end = System.currentTimeMillis();
        return end - start;
    }

    private long bathSelectWithDelay(String sql){
        long start = System.currentTimeMillis();
        bathSelect(sql);
        long end = System.currentTimeMillis();
        return end - start;
    }

    private long updateWithDelay(String sql){
        long start = System.currentTimeMillis();
        update(sql);
        long end = System.currentTimeMillis();
        return end - start;
    }

}
