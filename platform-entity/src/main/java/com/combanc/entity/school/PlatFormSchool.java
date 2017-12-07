package com.combanc.entity.school;

import com.combanc.entity.common.PageDto;

import java.io.Serializable;

/**
 * 平台学校对象
 */
public class PlatFormSchool extends PageDto implements Serializable {

    private static final long serialVersionUID = 8219642183955403617L;

    private String id;//学校主键
    private String xxbm;//学校编码
    private String xxmc;//学校名称
    private String xxdd;//学校地址

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getXxbm() {
        return xxbm;
    }

    public void setXxbm(String xxbm) {
        this.xxbm = xxbm;
    }

    public String getXxmc() {
        return xxmc;
    }

    public void setXxmc(String xxmc) {
        this.xxmc = xxmc;
    }

    public String getXxdd() {
        return xxdd;
    }

    public void setXxdd(String xxdd) {
        this.xxdd = xxdd;
    }
}
