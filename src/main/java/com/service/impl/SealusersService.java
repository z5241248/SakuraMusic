package com.service.impl;


import com.dao.ISealusersDao;
import com.entity.Sealusers;
import com.entity.Users;
import com.service.ISealusersService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Transactional
@MapperScan(basePackages = "com.dao")
@Service("sealusersService")
public class SealusersService implements ISealusersService {

    @Autowired
    private ISealusersDao isd;

    @Override
    public Integer insert(HashMap mp) {
        return isd.insert(mp);
    }

    @Override
    public Integer deleteByUserid(Integer userid) {
        return isd.deleteByUserid(userid);
    }

    @Override
    public Sealusers select(Integer userid) {
        return isd.select(userid);
    }
}
