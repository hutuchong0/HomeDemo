package com.tydic.app.homedemo;

import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 *@ClassName:MyGridAdapter
 *@Description:首页 GridView适配器
 *@Author:chenjunyong@tydic.com
 *@Since:1.6
 *@date 2016-3-11 上午10:22:08
 *@Version:1.0
 *Copyright 天源迪科合肥大数据中心 Corporation 2016   
 * 版权所有
 */
public class MyGridAdapter extends BaseAdapter {
	private List<Map<String, String>> dataList;
	private Context mContext;
	
	public MyGridAdapter(Context mContext, List<Map<String, String>> dataList) {
		this.dataList = dataList;
		this.mContext = mContext;
	}
	@Override
	public int getCount() {
		return dataList != null ? dataList.size() : 0;
	}

	@Override
	public Object getItem(int position) {
		return dataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHandle viewHandle = null;
		if (convertView == null) {
			viewHandle = new ViewHandle();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.home_grid_item, null);
			viewHandle.textView = (TextView) convertView
					.findViewById(R.id.item_text);
			convertView.setTag(viewHandle);
		} else {
			viewHandle = (ViewHandle) convertView.getTag();
		}
		
		
		return convertView;
	}
	/**
	 * 
	 * @Title: notifyDataChange
	 * @Description:更新数据
	 * @param data 数据源
	 * @return void 返回类型
	 * @throws
	 */
	public void notifyDataChange(List<Map<String, String>> data) {
		dataList = data;
		notifyDataSetChanged();
	}
	
	class ViewHandle {
		public TextView textView;
	}
}
