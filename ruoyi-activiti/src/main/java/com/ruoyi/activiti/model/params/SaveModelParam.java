package com.ruoyi.activiti.model.params;

/**
 * @author yangyouwang
 * @title: SaveModelParam
 * @projectName ruoyi
 * @description: 请求参数
 * @date 2020/7/25下午2:16
 */
public class SaveModelParam {

    private String name;

    private String description;

    private String json_xml;

    private String svg_xml;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJson_xml() {
        return json_xml;
    }

    public void setJson_xml(String json_xml) {
        this.json_xml = json_xml;
    }

    public String getSvg_xml() {
        return svg_xml;
    }

    public void setSvg_xml(String svg_xml) {
        this.svg_xml = svg_xml;
    }
}
