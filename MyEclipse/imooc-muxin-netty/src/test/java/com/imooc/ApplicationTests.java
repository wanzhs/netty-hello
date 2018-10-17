package com.imooc;

import com.imooc.entity.User;
import com.imooc.mapper.UsersMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
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
		User user =new User();
		user.setUsername("John")
				.setId("1")
				.setPassword("123456")
				.setCid("123")
				.setFaceImage("wanzhs")
				.setFaceImageBig("peace")
				.setNickname("Peter")
				.setQrcode("qrcode");
		usersMapper.insert(user);
		List<User> list=usersMapper.selectList(null);
		list.forEach(item->{
			System.out.println(item);
		});
	}
}
