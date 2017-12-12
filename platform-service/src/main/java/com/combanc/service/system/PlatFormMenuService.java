package com.combanc.service.system;

import com.combanc.entity.common.BaseResultDto;
import com.combanc.entity.system.PlatFormMenu;

/**
 * 菜单接口
 *
 * @author leijie
 */
public interface PlatFormMenuService {

    BaseResultDto addMenu(PlatFormMenu platFormMenu);

    BaseResultDto editMenu(PlatFormMenu platFormMenu);

    BaseResultDto delMenu(String id);

    Integer menuListCount(PlatFormMenu platFormMenu);

    BaseResultDto menuList(PlatFormMenu platFormMenu);

    BaseResultDto rootMenuList(PlatFormMenu platFormMenu);

    BaseResultDto menuTree(PlatFormMenu platFormMenu);
}
