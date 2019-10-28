package com.dao;

import com.entity.Sealusers;
import com.entity.Users;

import java.util.HashMap;
import java.util.List;

public interface ISealusersDao {

    Integer insert(HashMap mp);

    Integer deleteByUserid(Integer userid);

    Sealusers select(Integer userid);
}
