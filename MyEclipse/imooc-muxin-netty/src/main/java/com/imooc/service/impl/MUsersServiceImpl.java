package com.imooc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.imooc.entity.User;
import com.imooc.mapper.UsersMapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.service.IMUsersService;
import com.imooc.utils.MD5Utils;
import com.imooc.vo.UserVo;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wanzhongsu
 * @since 2018-10-12
 */
@Service("mUsersService")
public class MUsersServiceImpl extends ServiceImpl<UsersMapper, User> implements IMUsersService {
    @Resource
    UsersMapper usersMapper;
    @Autowired
    private Sid sid;
    @Override
    public boolean queryUsernameIsExists(String username) {
        User user=usersMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getUsername,username));
        if (ObjectUtils.isEmpty(user)){
            return false;
        }
        return true;
    }
    public boolean queryUsernameIsExists(String username,String password) {
        User user=usersMapper.selectOne(new QueryWrapper<User>().lambda()
                .eq(User::getUsername,username)
                .eq(User::getPassword, MD5Utils.getMD5Str(password)));
        if (ObjectUtils.isEmpty(user)){
            return false;
        }
        return true;
    }

    @Override
    public User saveUser(UserVo vo) {
        //TODO 为每个用户生成二维码
        User user=new User();
        BeanUtils.copyProperties(vo,user);
        String userId=sid.nextShort();
        user.setCid(userId);
        user.setQrcode("");
        usersMapper.insert(user);
        return user;
    }
}
