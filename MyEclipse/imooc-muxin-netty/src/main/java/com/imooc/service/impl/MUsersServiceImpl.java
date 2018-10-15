package com.imooc.service.impl;

import com.imooc.entity.Users;
import com.imooc.mapper.UsersMapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.service.IMUsersService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wanzhongsu
 * @since 2018-10-12
 */
@Service("mUsersService")
public class MUsersServiceImpl extends ServiceImpl<UsersMapper,Users> implements IMUsersService {

}
