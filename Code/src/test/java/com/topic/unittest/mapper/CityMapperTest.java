package com.topic.unittest.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import com.topic.unittest.domain.City;

@MybatisTest
class CityMapperTest {

    @Autowired
    private CityMapper cityMapper;

    @Test
    void findByStateTest() {
        City city = cityMapper.findByState("CA");
        assertThat(city.getId()).isEqualTo(1);
        assertThat(city.getName()).isEqualTo("San Francisco");
        assertThat(city.getState()).isEqualTo("CA");
        assertThat(city.getCountry()).isEqualTo("US");

    }

}