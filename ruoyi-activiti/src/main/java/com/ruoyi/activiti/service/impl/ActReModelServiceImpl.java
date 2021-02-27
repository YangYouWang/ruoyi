package com.ruoyi.activiti.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;

import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.activiti.mapper.ActReModelMapper;
import com.ruoyi.activiti.domain.ActReModel;
import com.ruoyi.activiti.service.IActReModelService;

import javax.annotation.Resource;

/**
 * 流程模型Service业务层处理
 * 
 * @author yangyouwang
 * @date 2020-07-24
 */
@Service
public class ActReModelServiceImpl implements IActReModelService 
{

    private static final Logger log = Logger.getLogger(IActReModelService.class.getName());


    @Resource
    private ActReModelMapper actReModelMapper;

    @Autowired
    private RepositoryService repositoryService;


    /**
     * 查询流程模型
     * 
     * @param id 流程模型ID
     * @return 流程模型
     */
    @Override
    public ActReModel selectActReModelById(String id)
    {
        return actReModelMapper.selectActReModelById(id);
    }

    /**
     * 查询流程模型列表
     * 
     * @param actReModel 流程模型
     * @return 流程模型
     */
    @Override
    public List<ActReModel> selectActReModelList(ActReModel actReModel)
    {
        return actReModelMapper.selectActReModelList(actReModel);
    }

    /**
     * 新增流程模型
     * 
     * @param actReModel 流程模型
     */
    @Override
    public String insertActReModel(ActReModel actReModel)
    {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode editorNode = objectMapper.createObjectNode();
            editorNode.put("id", "canvas");
            editorNode.put("resourceId", "canvas");
            ObjectNode stencilSetNode = objectMapper.createObjectNode();
            stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
            editorNode.put("stencilset", stencilSetNode);
            Model modelData = repositoryService.newModel();

            ObjectNode modelObjectNode = objectMapper.createObjectNode();
            modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, actReModel.getName());
            modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
            String description = StringUtils.defaultString(actReModel.getDescription());
            modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
            modelData.setMetaInfo(modelObjectNode.toString());
            modelData.setName(actReModel.getName());
            modelData.setKey(StringUtils.defaultString(actReModel.getKey()));

            repositoryService.saveModel(modelData);
            repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));
            return modelData.getId();
        } catch (Exception e) {
            log.info(e.toString());
        }
        return null;
    }

    /**
     * 修改流程模型
     * 
     * @param actReModel 流程模型
     * @return 结果
     */
    @Override
    public int updateActReModel(ActReModel actReModel)
    {
        actReModel.setUpdateTime(DateUtils.getNowDate());
        return actReModelMapper.updateActReModel(actReModel);
    }

    /**
     * 删除流程模型信息
     * 
     * @param id 流程模型ID
     */
    @Override
    public void deleteActReModelById(String id)
    {
        // 删除模型
        repositoryService.deleteModel(id);
    }

    /**
     * 部署流程图
     * @param id 流程模型ID
     */
    @Override
    public String deploy(String id) {
        try {
            // 先通过modelId去查找Model记录
            Model modelData = repositoryService.getModel(id);
            ObjectNode objectNode = (ObjectNode) new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));

            BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(objectNode);
            byte[] bpmnXmlBytes = new BpmnXMLConverter().convertToXML(model);
            // 部署
            String processName = modelData.getName() + ".bpmn20.xml";
            DeploymentBuilder db = repositoryService.createDeployment().name(modelData.getName());
            Deployment deployment = db.addString(processName, new String(bpmnXmlBytes, "utf-8")).deploy();
            // 保存模型
            ProcessDefinition processDefinition =  repositoryService.createProcessDefinitionQuery()
                    .deploymentId(deployment.getId()).singleResult();
            modelData.setDeploymentId(deployment.getId());
            modelData.setKey(processDefinition.getKey());
            repositoryService.saveModel(modelData);
            return deployment.getId();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
