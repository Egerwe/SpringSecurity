package com.Yao.mapper;

import com.Yao.domain.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * Author Yjw
 * 2023/1/27 21:36
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(Long id);
}
