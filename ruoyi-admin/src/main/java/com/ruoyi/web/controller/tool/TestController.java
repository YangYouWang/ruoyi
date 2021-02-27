package com.ruoyi.web.controller.tool;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.domain.ThumbImageConfig;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.ruoyi.common.annotation.ApiVersion;
import com.ruoyi.common.annotation.PassToken;
import com.ruoyi.common.constant.ApiVersionConstant;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * swagger 用户测试方法
 * 
 * @author ruoyi
 */
@Api("用户信息管理")
@RestController
@RequestMapping("/api/{version}/test/user")
public class TestController extends BaseController
{
    private final static Map<Integer, UserEntity> users = new LinkedHashMap<Integer, UserEntity>();
    {
        users.put(1, new UserEntity(1, "admin", "admin123", "15888888888"));
        users.put(2, new UserEntity(2, "ry", "admin123", "15666666666"));
    }


    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    private ThumbImageConfig thumbImageConfig;


    @ApiOperation("获取用户列表")
    @GetMapping("/list")
    @PassToken
    @ApiVersion(value = ApiVersionConstant.API_V1,group = ApiVersionConstant.SWAGGER_API_V1)
    public AjaxResult userList()
    {
        List<UserEntity> userList = new ArrayList<UserEntity>(users.values());
        return AjaxResult.success(userList);
    }

    /**
     * 上传请求
     */
    @ApiOperation("上传请求")
    @PostMapping("/upload")
    @ResponseBody
    @PassToken
    @ApiVersion(value = ApiVersionConstant.API_V1,group = ApiVersionConstant.SWAGGER_API_V1)
    public AjaxResult uploadFile(@ApiParam(value = "文件", required = true) MultipartFile file) throws Exception
    {
        try
        {
            // 获取后缀名
            String originalFileName = file.getOriginalFilename();
            String suffix = originalFileName.substring(originalFileName.lastIndexOf("."));
            // 上传并保存图片，参数：1-上传的文件流 2-文件的大小 3-文件的后缀 4-可以不管他
            StorePath storePath = this.storageClient.uploadFile(
                file.getInputStream(), file.getSize(), suffix, null);
            AjaxResult ajax = AjaxResult.success();
            // 带分组的路径
            ajax.put("fullPath", storePath.getFullPath());
            // 不带分组的路径
            ajax.put("path", storePath.getPath());
            return ajax;
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    @ApiOperation("获取用户详细")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "int", paramType = "path")
    @GetMapping("/{userId}")
    @ApiVersion(value = ApiVersionConstant.API_V1,group = ApiVersionConstant.SWAGGER_API_V1)
    public AjaxResult getUser(@PathVariable Integer userId)
    {
        if (!users.isEmpty() && users.containsKey(userId))
        {
            return AjaxResult.success(users.get(userId));
        }
        else
        {
            return error("用户不存在");
        }
    }

    @ApiOperation("新增用户")
    @ApiImplicitParam(name = "userEntity", value = "新增用户信息", dataType = "UserEntity")
    @PostMapping("/save")
    @ApiVersion(value = ApiVersionConstant.API_V1,group = ApiVersionConstant.SWAGGER_API_V1)
    public AjaxResult save(UserEntity user)
    {
        if (StringUtils.isNull(user) || StringUtils.isNull(user.getUserId()))
        {
            return error("用户ID不能为空");
        }
        return AjaxResult.success(users.put(user.getUserId(), user));
    }

    @ApiOperation("更新用户")
    @ApiImplicitParam(name = "userEntity", value = "新增用户信息", dataType = "UserEntity")
    @PutMapping("/update")
    @ApiVersion(value = ApiVersionConstant.API_V1,group = ApiVersionConstant.SWAGGER_API_V1)
    public AjaxResult update(UserEntity user)
    {
        if (StringUtils.isNull(user) || StringUtils.isNull(user.getUserId()))
        {
            return error("用户ID不能为空");
        }
        if (users.isEmpty() || !users.containsKey(user.getUserId()))
        {
            return error("用户不存在");
        }
        users.remove(user.getUserId());
        return AjaxResult.success(users.put(user.getUserId(), user));
    }

    @ApiOperation("删除用户信息")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "int", paramType = "path")
    @DeleteMapping("/{userId}")
    @ApiVersion(value = ApiVersionConstant.API_V1,group = ApiVersionConstant.SWAGGER_API_V1)
    public AjaxResult delete(@PathVariable Integer userId)
    {
        if (!users.isEmpty() && users.containsKey(userId))
        {
            users.remove(userId);
            return success();
        }
        else
        {
            return error("用户不存在");
        }
    }
}

@ApiModel("用户实体")
class UserEntity
{
    @ApiModelProperty("用户ID")
    private Integer userId;

    @ApiModelProperty("用户名称")
    private String username;

    @ApiModelProperty("用户密码")
    private String password;

    @ApiModelProperty("用户手机")
    private String mobile;

    public UserEntity()
    {

    }

    public UserEntity(Integer userId, String username, String password, String mobile)
    {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.mobile = mobile;
    }

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getMobile()
    {
        return mobile;
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }
}
