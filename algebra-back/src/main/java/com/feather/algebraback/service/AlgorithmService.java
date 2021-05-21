package com.feather.algebraback.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.feather.algebraback.domain.Algorithm;
import com.feather.algebraback.domain.VO.AlgorithmUserVO;

/**
 * @author 惊鸿之羽
 */
public interface AlgorithmService extends IService<Algorithm> {

    /**
     * 查询分页算法文件
     *
     * @param objectPage
     * @return
     */
    Page<AlgorithmUserVO> selectAlgorithmPage(Page<AlgorithmUserVO> objectPage);
}
