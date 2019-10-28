package com.service;

import com.entity.Sealusers;
import com.entity.Users;

import java.util.HashMap;
import java.util.List;

public interface ISealusersService {

    Integer insert(HashMap mp);

    Integer deleteByUserid(Integer userid);

    Sealusers select(Integer userid);

}
