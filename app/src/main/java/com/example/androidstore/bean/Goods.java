package com.example.androidstore.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author mascot
 * @date 2019/6/1 16:37
 */

public class Goods implements Serializable {

    private long id;
    private String name;

    private long brandId;

    private long categoryId;

    private long attributeId;

    private long parameterId;

    private String image;
    private List<Specifications> specificationsList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getBrandId() {
        return brandId;
    }

    public void setBrandId(long brandId) {
        this.brandId = brandId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(long attributeId) {
        this.attributeId = attributeId;
    }

    public long getParameterId() {
        return parameterId;
    }

    public void setParameterId(long parameterId) {
        this.parameterId = parameterId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }




    public List<Specifications> getSpecificationsList() {
        return specificationsList;
    }

    public void setSpecificationsList(List<Specifications> specificationsList) {
        this.specificationsList = specificationsList;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brandId=" + brandId +
                ", categoryId=" + categoryId +
                ", attributeId=" + attributeId +
                ", parameterId=" + parameterId +
                ", image='" + image + '\'' +
                ", specificationsList=" + specificationsList +
                '}';
    }
}

