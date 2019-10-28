package com.service;

import com.entity.Musictype;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

public interface IMusicTypeService
{
    List<Musictype> selectAllMusicType();

    List<Musictype> selectAllMusicType2();

    Musictype selectImageByMusicType(String musictype);

}
