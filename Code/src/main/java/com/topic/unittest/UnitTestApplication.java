package com.topic.unittest;

import com.topic.unittest.mapper.CityDao;
import com.topic.unittest.mapper.HotelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UnitTestApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(UnitTestApplication.class, args);
	}

	private final CityDao cityDao;

	private final HotelMapper hotelMapper;

	public UnitTestApplication(CityDao cityDao, HotelMapper hotelMapper) {
		this.cityDao = cityDao;
		this.hotelMapper = hotelMapper;
	}

	@Override
	@SuppressWarnings("squid:S106")
	public void run(String... args) {
		System.out.println(this.cityDao.selectCityById(1));
		System.out.println(this.hotelMapper.selectByCityId(1));
	}

}
