package com.tydic.app.homedemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.SimpleAdapter;

public class HomePageFragment extends Fragment {
	
	private Context mContext;
	private View contentView;
	private ArrayList<View> dots;
	private ViewPager mViewPager;
	private MyViewPagerAdapter pagerAdapter;
	private View view1, view2, view3;
	private int oldPosition = 0;// 记录上一次点的位置
	private int currentItem; // 当前页面
	private List<View> viewList = new ArrayList<View>();// 把需要滑动的页卡添加到这个list中
	private GridView mGridView;
	private SimpleAdapter gridAdapter;
	private List<Map<String, String>> gridData;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		mContext = getActivity();
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		contentView = inflater.inflate(R.layout.fragment_home, null);
		initViews();	
		initDatas();
		return contentView;
	}

	private void initViews() {
		dots = new ArrayList<View>();
		dots.add(contentView.findViewById(R.id.dot_1));
		dots.add(contentView.findViewById(R.id.dot_2));
		dots.add(contentView.findViewById(R.id.dot_3));
		// 得到viewPager的布局
		LayoutInflater lf = LayoutInflater.from(mContext);
		view1 = lf.inflate(R.layout.viewpager_item1, null);
		view2 = lf.inflate(R.layout.viewpager_item2, null);
		view3 = lf.inflate(R.layout.viewpager_item3, null);
		viewList.add(view1);
		viewList.add(view2);
		viewList.add(view3);
		// 找到点击进入那个按钮
		mViewPager = (ViewPager) contentView.findViewById(R.id.home_viewPager);
		mGridView = (GridView) contentView.findViewById(R.id.home_gridView);
		pagerAdapter = new MyViewPagerAdapter(viewList);
		mViewPager.setAdapter(pagerAdapter);
		dots.get(0).setBackgroundResource(R.drawable.dot_focused);
		mViewPager.addOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub

				dots.get(oldPosition).setBackgroundResource(
						R.drawable.dot_normal);
				dots.get(position)
						.setBackgroundResource(R.drawable.dot_focused);

				oldPosition = position;
				currentItem = position;
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}
	
	private void initDatas() {
		String datas[]  = {"列表类", "图片类", "筛选", "购物车", "分享类", "二维码", "定位类", "地图类", 
				"图表类", "消息类", "详情类", "拍照摄像", "消息提示", "table+tab", "表单", ""};
		gridData = new ArrayList<Map<String,String>>();
		for (int i = 0; i < datas.length; i++) {
			Map<String, String> item = new HashMap<String, String>();
			item.put("name", datas[i]);
			gridData.add(item);
		}
		gridAdapter = new SimpleAdapter(mContext, gridData, R.layout.home_grid_item, new String[]{"name"}, new int[]{R.id.item_text});
		mGridView.setAdapter(gridAdapter);
	}
}
