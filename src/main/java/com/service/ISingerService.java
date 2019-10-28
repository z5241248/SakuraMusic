package com.service;

import com.entity.Singer;
import java.util.List;

public interface ISingerService
{
    List<Singer> selectRandSinger();

    List<Singer> selectByName(String name);

    Singer selectByPrimaryKey(Integer id);

    Singer selectByName2(String name);
}
