package com.imooc.service;

import com.imooc.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.imooc.vo.UserVo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wanzhongsu
 * @since 2018-10-12
 */
public interface IMUsersService extends IService<User> {
    /**
     * 判断用户名是否存在
     *
     * @param username
     * @return
     */
    public boolean queryUsernameIsExists(String username);

    /**
     * 判断用户名和密码是否正确
     *
     * @param username
     * @param password
     * @return
     */
    public boolean queryUsernameIsExists(String username, String password);

    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    public User saveUser(UserVo user);
}
