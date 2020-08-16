package com.lrh;

import com.lrh.dict.dao.SysConfigRepository;
import com.lrh.dict.model.SysConfig;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @auther lironghui
 * @date 2020/8/15
 */
public class SysConfigTest extends BaseJunitTest {
    @Autowired
    SysConfigRepository sysConfigRepository;
    @Test
    public void testSave(){
        SysConfig sysConfig=new SysConfig();
        sysConfig.setsKey("key2");
        sysConfig.setsKey("value");
        sysConfig.setsKey("desc");
        sysConfigRepository.save(sysConfig);
        System.out.println(sysConfig.getnId());
    }

    @Test
    public void testSaveBatch(){
       List<SysConfig> sysConfigList=new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            SysConfig sysConfig=new SysConfig();
            sysConfig.setsKey("key"+i);
            sysConfig.setsValue("value"+i);
            sysConfig.setsDesc("desc");
            sysConfigList.add(sysConfig);
        }
        sysConfigRepository.saveAll(sysConfigList);
    }
}
