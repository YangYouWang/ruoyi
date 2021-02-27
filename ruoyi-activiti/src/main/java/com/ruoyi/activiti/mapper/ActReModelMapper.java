package com.ruoyi.activiti.mapper;

import com.ruoyi.activiti.domain.ActReModel;
import java.util.List;

/**
 * 流程模型Mapper接口
 * 
 * @author yangyouwang
 * @date 2020-07-24
 */
public interface ActReModelMapper 
{
    /**
     * 查询流程模型
     * 
     * @param id 流程模型ID
     * @return 流程模型
     */
    public ActReModel selectActReModelById(String id);

    /**
     * 查询流程模型列表
     * 
     * @param actReModel 流程模型
     * @return 流程模型集合
     */
    public List<ActReModel> selectActReModelList(ActReModel actReModel);

    /**
     * 修改流程模型
     * 
     * @param actReModel 流程模型
     * @return 结果
     */
    public int updateActReModel(ActReModel actReModel);
}
