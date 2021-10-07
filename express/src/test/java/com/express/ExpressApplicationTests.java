package com.express;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.express.mapper.IStationMapper;
import com.express.mapper.entity.Station;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
public class ExpressApplicationTests {

	@Autowired
	DataSource dataSource;

	@Test
	public void contextLoads() {
		System.out.println("凌："+dataSource.getClass());

		try {
			Connection conn = dataSource.getConnection();
			System.out.println(conn);
			conn.close();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	@Autowired
	private IStationMapper stationMapper;

}
