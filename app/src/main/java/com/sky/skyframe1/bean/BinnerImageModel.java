package com.sky.skyframe1.bean;

import java.io.Serializable;

/***
 * 轮播图片Model
 * 
 * @author WebUser
 * 
 */
public class BinnerImageModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private int image_id;
	private String image_source_url;

	public int getImage_id() {
		return image_id;
	}

	public void setImage_id(int image_id) {
		this.image_id = image_id;
	}

	public String getImage_source_url() {
		return image_source_url;
	}

	public void setImage_source_url(String image_source_url) {
		this.image_source_url = image_source_url;
	}

}
