package com.imooc;

import com.imooc.entity.Users;
import com.imooc.mapper.UsersMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
	@Resource
	UsersMapper usersMapper;
	@Test
	public void contextLoads(){

	}
	@Test
	public void testSql(){
		Users users=new Users();
		users.setUsername("John")
				.setId("1")
				.setPassword("123456")
				.setCid("123")
				.setFaceImage("wanzhs")
				.setFaceImageBig("peace")
				.setNickname("Peter")
				.setQrcode("qrcode");
		usersMapper.insert(users);
		List<Users> list=usersMapper.selectList(null);
		list.forEach(item->{
			System.out.println(item);
		});
	}
}
