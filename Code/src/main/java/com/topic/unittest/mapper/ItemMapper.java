package com.topic.unittest.mapper;


import com.topic.unittest.domain.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ItemMapper {

    @Select("select * from item")
    List<Item> findAll();
}
