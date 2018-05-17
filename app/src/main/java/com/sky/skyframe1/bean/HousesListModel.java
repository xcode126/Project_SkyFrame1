package com.sky.skyframe1.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 主页数据Model
 *
 * @author WebUser
 */
public class HousesListModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private int house_id;
    private String house_name;
    private List<String> house_featured;
    private String house_min_area;
    private String house_max_area;
    private String house_address;
    private String house_average_price;
    private String house_thumb_src;

    public int getHouse_id() {
        return house_id;
    }

    public void setHouse_id(int house_id) {
        this.house_id = house_id;
    }

    public String getHouse_name() {
        return house_name;
    }

    public void setHouse_name(String house_name) {
        this.house_name = house_name;
    }

    public List<String> getHouse_featured() {
        return house_featured;
    }

    public void setHouse_featured(List<String> house_featured) {
        this.house_featured = house_featured;
    }

    public String getHouse_min_area() {
        return house_min_area;
    }

    public void setHouse_min_area(String house_min_area) {
        this.house_min_area = house_min_area;
    }

    public String getHouse_max_area() {
        return house_max_area;
    }

    public void setHouse_max_area(String house_max_area) {
        this.house_max_area = house_max_area;
    }

    public String getHouse_address() {
        return house_address;
    }

    public void setHouse_address(String house_address) {
        this.house_address = house_address;
    }

    public String getHouse_average_price() {
        return house_average_price;
    }

    public void setHouse_average_price(String house_average_price) {
        this.house_average_price = house_average_price;
    }

    public String getHouse_thumb_src() {
        return house_thumb_src;
    }

    public void setHouse_thumb_src(String house_thumb_src) {
        this.house_thumb_src = house_thumb_src;
    }
}
