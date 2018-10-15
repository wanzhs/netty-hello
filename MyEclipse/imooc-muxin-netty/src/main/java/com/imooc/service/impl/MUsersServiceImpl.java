package com.baomidou.service.impl;

import com.baomidou.entity.Users;
import com.baomidou.mapper.UsersMapper;
import com.baomidou.service.IMUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wanzhongsu
 * @since 2018-10-12
 */
@Service
public class MUsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IMUsersService {

}
