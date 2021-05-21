package com.feather.algebraback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feather.algebraback.domain.Algorithm;
import com.feather.algebraback.domain.VO.AlgorithmUserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 惊鸿之羽
 */
@Mapper
public interface AlgorithmMapper extends BaseMapper<Algorithm> {
    /**
     * 分页查询算法文件
     *
     * @param algorithmUserPage
     * @return
     */
    @Select("select file.*,user.username from file, user where file.user_id = user.user_id")
    List<AlgorithmUserVO> selectAlgorithmPage(Page<AlgorithmUserVO> algorithmUserPage);
}
