package com.ruoyi.activiti.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.activiti.domain.ActReModel;
import com.ruoyi.activiti.service.IActReModelService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;



/**
 * 流程模型Controller
 * 
 * @author yangyouwang
 * @date 2020-07-24
 */
@Controller
@RequestMapping("/activiti/model")
public class ActReModelController extends BaseController
{
    private String prefix = "activiti/model";

    @Autowired
    private IActReModelService actReModelService;

    @RequiresPermissions("activiti:model:view")
    @GetMapping()
    public String model()
    {
        return prefix + "/model";
    }

    /**
     * 查询流程模型列表
     */
    @RequiresPermissions("activiti:model:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ActReModel actReModel)
    {
        startPage();
        List<ActReModel> list = actReModelService.selectActReModelList(actReModel);
        return getDataTable(list);
    }

    /**
     * 新增流程模型
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存流程模型
     */
    @RequiresPermissions("activiti:model:add")
    @Log(title = "流程模型", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ActReModel actReModel)  {
        String id = actReModelService.insertActReModel(actReModel);
        return AjaxResult.success(id);
    }

    /**
     * 修改流程模型
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        ActReModel actReModel = actReModelService.selectActReModelById(id);
        mmap.put("actReModel", actReModel);
        return prefix + "/edit";
    }

    /**
     * 设计流程模型
     */
    @GetMapping("/design/{id}")
    public String design(@PathVariable("id") String id)
    {
        return "redirect:/modeler.html?modelId=" + id;
    }


    /**
     * 部署流程模型
     */
    @GetMapping("/deploy/{id}")
    @ResponseBody
    public AjaxResult deploy(@PathVariable("id") String id)
    {
       return AjaxResult.success(actReModelService.deploy(id));
    }


    /**
     * 修改保存流程模型
     */
    @RequiresPermissions("activiti:model:edit")
    @Log(title = "流程模型", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ActReModel actReModel)
    {
        return toAjax(actReModelService.updateActReModel(actReModel));
    }

    /**
     * 删除流程模型
     */
    @RequiresPermissions("activiti:model:remove")
    @Log(title = "流程模型", businessType = BusinessType.DELETE)
    @PostMapping( "/remove/{id}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("id") String id)
    {
        actReModelService.deleteActReModelById(id);
        return AjaxResult.success();
    }
}
