package com.cloume.restapi.initializr.controller;

import com.cloume.commons.beanutils.Updater;
import com.cloume.commons.rest.response.PagingRestResponse;
import com.cloume.commons.rest.response.RestResponse;
import com.cloume.commons.verify.Verifier;
import com.cloume.restapi.initializr.model.BaseUser;
import com.cloume.restapi.initializr.model.Paging;
import com.cloume.restapi.initializr.service.IBaseUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author yxiao
 * @date
 * @description 用户管理相关接口
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private IBaseUserService baseUserService;

    /**
     * 根据id获取用户详情
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public RestResponse<BaseUser> getById(@PathVariable String id) {
        BaseUser baseUser = baseUserService.find(id);
        if (baseUser == null) {
            return RestResponse.bad(-1, String.format("用户未找到：%s", id));
        }

        return RestResponse.good(baseUser);
    }

    /**
     * 创建用户
     * @param body
     * @return
     */
    @PutMapping
    public RestResponse<String> createUser(@RequestBody Map<String, Object> body) {
        if (!new Verifier()
                .rule("name", true, check -> StringUtils.isNotEmpty(body.get("name").toString()))
                .rule("type")
                .result((r, s) -> {
                }).verify(body)
        ) {
            return RestResponse.bad(-1, "方法参数不完整");
        }

        BaseUser baseUser = Updater.wrap(new BaseUser()).update(body);
        baseUserService.save(baseUser);

        return RestResponse.good("ok");
    }

    /**
     * 修改用户
     * @param body
     * @return
     */
    @PostMapping("/{id}")
    public RestResponse<String> updateUser(@PathVariable String id, @RequestBody Map<String, Object> body) {
        if (!new Verifier()
                .rule("name", true, check -> StringUtils.isNotEmpty(body.get("name").toString()))
                .rule("type")
                .result((r, s) -> {
                }).verify(body)
        ) {
            return RestResponse.bad(-1, "方法参数不完整");
        }

        BaseUser baseUser = baseUserService.find(id);
        if (baseUser == null) {
            return RestResponse.bad(-2, String.format("用户不存在：%s", id));
        }

        baseUser = Updater.wrap(baseUser).update(body);
        baseUserService.save(baseUser);

        return RestResponse.good("ok");
    }

    /**
     * 删除用户
     * @return
     */
    @DeleteMapping("/{id}")
    public RestResponse<String> deleteUser(@PathVariable String id) {
        BaseUser baseUser = baseUserService.find(id);
        if (baseUser == null) {
            return RestResponse.bad(-2, String.format("用户不存在：%s", id));
        }

        baseUserService.delete(baseUser);

        return RestResponse.good("ok");
    }

    /**
     * 分页查询用户列表
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list")
    public PagingRestResponse<BaseUser> listUser(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                                       @RequestParam(value = "size", defaultValue = "10", required = false) int size ) {
        Paging<List<BaseUser>> paging = baseUserService.list(page, size);

        return PagingRestResponse.good(paging.getData(), paging.getCount());
    }
}
