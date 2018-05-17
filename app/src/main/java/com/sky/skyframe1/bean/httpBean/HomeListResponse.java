package com.sky.skyframe1.bean.httpBean;

import com.sky.skyframe1.bean.BaseResponse;
import com.sky.skyframe1.bean.HousesListModel;

import java.util.List;


/**
 * 主页数据服务器返回Model
 * 
 * @author WebUser
 * 
 */
public class HomeListResponse extends BaseResponse {

	private static final long serialVersionUID = 1L;

	private List<HousesListModel> data;

	public List<HousesListModel> getData() {
		return data;
	}

	public void setData(List<HousesListModel> data) {
		this.data = data;
	}

}
