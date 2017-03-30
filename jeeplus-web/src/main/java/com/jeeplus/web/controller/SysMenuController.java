package com.jeeplus.web.controller;

import com.jeeplus.web.entities.system.SysMenuEntity;
import com.jeeplus.web.service.system.SysMenuService;
import com.jeeplus.web.commons.constant.Constant;
import com.jeeplus.web.util.PageUtils;
import com.jeeplus.web.commons.R;
import com.jeeplus.web.commons.exception.RRException;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统菜单
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2016-12-12 14:45.
 */
@Controller
@RequestMapping("/sys/menu")
public class SysMenuController extends AbstractController {

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 所有菜单列表
     */
    @RequestMapping("/list")
    @ResponseBody
    @RequiresPermissions("sys:menu:list")
    public R list(Integer page, Integer limit){
        Map<String, Object> map = new HashMap<>();
        map.put("offset", (page - 1) * limit);
        map.put("limit", limit);

        //查询列表数据

        List<SysMenuEntity> menuList = sysMenuService.queryList(map);
        int total = sysMenuService.queryTotal(map);

        PageUtils pageUtil = new PageUtils(menuList, total, limit, page);

        return R.ok().put("page", pageUtil);
    }

    /**
     * 选择菜单(添加、修改菜单)
     */
    @RequestMapping("/select")
    @ResponseBody
    @RequiresPermissions("sys:menu:select")
    public R select(){
        //查询列表数据
        List<SysMenuEntity> menuList = sysMenuService.queryNotButtonList();

        //添加顶级菜单
        SysMenuEntity root = new SysMenuEntity();
        root.setMenuId(0L);
        root.setName("一级菜单");
        root.setParentId(-1L);
        root.setOpen(true);
        menuList.add(root);

        return R.ok().put("menuList", menuList);
    }

    /**
     * 角色授权菜单
     */
    @RequestMapping("/perms")
    @ResponseBody
    @RequiresPermissions("sys:menu:perms")
    public R perms(){
        //查询列表数据
        List<SysMenuEntity> menuList = sysMenuService.queryList(new HashMap<>());

        return R.ok().put("menuList", menuList);
    }

    /**
     * 菜单信息
     */
    @RequestMapping("/info/{menuId}")
    @RequiresPermissions("sys:menu:info")
    public R info(@PathVariable("menuId") Long menuId){
        SysMenuEntity menu = sysMenuService.queryObject(menuId);
        return R.ok().put("menu", menu);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:menu:save")
    public R save(@RequestBody SysMenuEntity menu){
        //数据校验

        verifyForm(menu);

        sysMenuService.save(menu);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:menu:update")
    public R update(@RequestBody SysMenuEntity menu){
        //数据校验

        verifyForm(menu);

        sysMenuService.update(menu);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:menu:delete")
    public R delete(@RequestBody Long[] menuIds){
        for(Long menuId : menuIds){
            if(menuId.longValue() <= 28){
                return R.error("系统菜单，不能删除");
            }
        }
        sysMenuService.deleteBatch(menuIds);

        return R.ok();
    }

    /**
     * 用户菜单列表
     */
    @RequestMapping("/user")
    @ResponseBody
    public R user(){
        List<SysMenuEntity> menuList = sysMenuService.getUserMenuList(getUserId());

        return R.ok().put("menuList", menuList);
    }

    /**
     * 验证参数是否正确
     */
    private void verifyForm(SysMenuEntity menu){
        if(StringUtils.isBlank(menu.getName())){
            throw new RRException("菜单名称不能为空");
        }

        if(menu.getParentId() == null){
            throw new RRException("上级菜单不能为空");
        }

        //菜单
        if(menu.getType() == Constant.MenuType.MENU.getValue()){
            if(StringUtils.isBlank(menu.getUrl())){
                throw new RRException("菜单URL不能为空");
            }
        }

        //上级菜单类型
        int parentType = Constant.MenuType.CATALOG.getValue();
        if(menu.getParentId() != 0){
            SysMenuEntity parentMenu = sysMenuService.queryObject(menu.getParentId());
            parentType = parentMenu.getType();
        }

        //目录、菜单
        if(menu.getType() == Constant.MenuType.CATALOG.getValue() ||
                menu.getType() == Constant.MenuType.MENU.getValue()){
            if(parentType != Constant.MenuType.CATALOG.getValue()){
                throw new RRException("上级菜单只能为目录类型");
            }
            return ;
        }

        //按钮
        if(menu.getType() == Constant.MenuType.BUTTON.getValue()){
            if(parentType != Constant.MenuType.MENU.getValue()){
                throw new RRException("上级菜单只能为菜单类型");
            }
            return ;
        }
    }

}
