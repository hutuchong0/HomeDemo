package com.tydic.app.homedemo;

import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class HomeActivity extends Activity implements OnClickListener {
	private Context mContext;
	private ActionBar mActionBar;
	private TextView openDrawLayout;
	private DrawerLayout mDrawerLayout;
	//侧滑栏是否打开
	private boolean isOpened = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		mContext =this;
		mActionBar = getActionBar();
		mActionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		LayoutInflater inflator = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflator.inflate(R.layout.action_bar, new LinearLayout(this), false);
		ActionBar.LayoutParams layout = new ActionBar.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		mActionBar.setCustomView(v, layout);
		initViews();
	}

	private void initViews() {
		openDrawLayout = (TextView) findViewById(R.id.draw_ic);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		openDrawLayout.setOnClickListener(this);
		findViewById(R.id.bar_right).setOnClickListener(this);
	}

	private void showPopupWindow(View view) {
		// 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(mContext).inflate(
                R.layout.action_pop_window, null);
        final PopupWindow popupWindow = new PopupWindow(contentView,
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);

        popupWindow.setTouchable(true);

        popupWindow.setTouchInterceptor(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Log.i("mengdd", "onTouch : ");
				// TODO Auto-generated method stub
				return false;
			}
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
//        popupWindow.setBackgroundDrawable(getResources().getDrawable(
//                R.drawable.selectmenu_bg_downward));

        // 设置好参数之后再show
        popupWindow.showAsDropDown(view);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.draw_ic: {
			if (!isOpened) {
				mDrawerLayout.openDrawer(Gravity.START);
				isOpened = true;
			} else {
				isOpened = false;
				mDrawerLayout.closeDrawer(Gravity.START);
			}
			break;
		}
		case R.id.bar_right: {
			showPopupWindow(v);
			break;
		}
		default:
			break;
		}
		
	}
}
