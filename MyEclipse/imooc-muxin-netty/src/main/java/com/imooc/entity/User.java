package com.imooc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wanzhongsu
 * @since 2018-10-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("users")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.NONE)
    private String id;
    @TableField("username")
    private String username;
    @TableField("password")
    private String password;
    @TableField("face_image")
    private String faceImage;
    @TableField("face_image_big")
    private String faceImageBig;
    @TableField("nickname")
    private String nickname;
    @TableField("qrcode")
    private String qrcode;
    @TableField("cid")
    private String cid;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
