package com.feather.algebraback.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feather.algebraback.domain.Algorithm;
import com.feather.algebraback.domain.VO.AlgorithmUserVO;
import com.feather.algebraback.mapper.AlgorithmMapper;
import com.feather.algebraback.service.AlgorithmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author 惊鸿之羽
 */
@Slf4j
@Service
public class AlgorithmServiceImpl extends ServiceImpl<AlgorithmMapper, Algorithm> implements AlgorithmService {
    @Override
    public Page<AlgorithmUserVO> selectAlgorithmPage(Page<AlgorithmUserVO> algorithmUserPage) {
        return algorithmUserPage.setRecords(this.baseMapper.selectAlgorithmPage(algorithmUserPage));
    }
}
