package com.tydic.app.homedemo;

import com.tydic.app.homedemo.comm.Constants;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends BaseActivity implements OnClickListener {
	private Context mContext;
	private ActionBar mActionBar;
	private TextView openDrawLayout;
	private DrawerLayout mDrawerLayout;
	private FragmentManager mManager;
	private TextView tab0;
	private TextView tab1;
	private TextView tab2;
	private TextView tab3;
	private HomePageFragment homePageFragment;
	private Tab1Fragment tab1Fragment;
	private Tab2Fragment tab2Fragment;
	//侧滑栏是否打开
	private boolean isOpened = false;
	
	private long exitTime = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		mManager = getFragmentManager();
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
		initClick();
	}

	private void initViews() {
		openDrawLayout = (TextView) findViewById(R.id.draw_ic);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
		openDrawLayout.setOnClickListener(this);
		findViewById(R.id.bar_right).setOnClickListener(this);
		tab0 = (TextView) findViewById(R.id.tab0_tv);
		tab1 = (TextView) findViewById(R.id.tab1_tv);
		tab2 = (TextView) findViewById(R.id.tab2_tv);
		tab3 = (TextView) findViewById(R.id.tab3_tv);
		onClickChangeView(Constants.HOME);
		changTitleAndBottom(Constants.HOME);
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
				Log.i("debug", "onTouch : ");
				// TODO Auto-generated method stub
				return false;
			}
        });
        // 设置好参数之后再show
        popupWindow.showAsDropDown(view);
		
	}

	private void initClick() {
		tab0.setOnClickListener(this);
		tab1.setOnClickListener(this);
		tab2.setOnClickListener(this);
		tab3.setOnClickListener(this);
	}
	
	private void onClickChangeView(int tag) {
		android.app.FragmentTransaction baseInfoTransaction = mManager.beginTransaction();
		hideFragments(baseInfoTransaction);
		switch (tag) {
			case Constants.HOME:{
				if (homePageFragment == null) {
					homePageFragment = new HomePageFragment();
					baseInfoTransaction.add(R.id.fm_main, homePageFragment);
				} else {
					baseInfoTransaction.show(homePageFragment);
				}
				break;
			}
			case Constants.TAB1:{
				if (tab1Fragment == null) {
					tab1Fragment = new Tab1Fragment();
					baseInfoTransaction.add(R.id.fm_main, tab1Fragment);
				} else {
					baseInfoTransaction.show(tab1Fragment);
				}
				break;
			}
			case Constants.TAB2:{
				if (tab2Fragment == null) {
					tab2Fragment = new Tab2Fragment();
					baseInfoTransaction.add(R.id.fm_main, tab2Fragment);
				} else {
					baseInfoTransaction.show(tab2Fragment);
				}
				break;
			}
			case Constants.TAB3:{
				//TODO 弹框
				break;
			}
		}
		baseInfoTransaction.commit();
	}

	/**
	 * 将所有的Fragment都置为隐藏状态。
	 * 
	 * @param transaction
	 *            用于对Fragment执行操作的事务
	 */
	private void hideFragments(FragmentTransaction transaction) {

		if (homePageFragment != null) {
			transaction.hide(homePageFragment);
		}
		if (tab1Fragment != null) {
			transaction.hide(tab1Fragment);
		}
		if (tab2Fragment != null) {
			transaction.hide(tab2Fragment);
		}
	}

	/**
	 * 点击底部按钮，改变图标样式和标题
	 * @param whichFragment 选中页面
	 */
	private void changTitleAndBottom(int whichFragment) {
		MainApplication.FRAGMENT_TAG = whichFragment;
		tab0.setTextColor(Color.DKGRAY);
		tab1.setTextColor(Color.DKGRAY);
		tab2.setTextColor(Color.DKGRAY);
		tab3.setTextColor(Color.DKGRAY);
		switch (whichFragment) {
		case Constants.HOME: {
			tab0.setTextColor(Color.parseColor("#7CB342"));
			break;
		}
		case Constants.TAB1: {
			tab1.setTextColor(Color.parseColor("#7CB342"));
			break;
		}
		case Constants.TAB2: {
			tab2.setTextColor(Color.parseColor("#7CB342"));
			break;
		}
		case Constants.TAB3: {
			tab3.setTextColor(Color.parseColor("#7CB342"));
			break;
		}
		default:
			break;
		}
	}
	
	 /**
     * 监听返回--是否退出程序
     */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if (System.currentTimeMillis() - exitTime > 2 * 1000) {
				Toast.makeText(getApplicationContext(),
						getResources().getString(R.string.exti_system_hint),
						Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();
			} else {
				exitApp();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	public void onClick(View v) {
		if (v == null) {
			return;
		}
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
		case R.id.tab0_tv: {

			if (MainApplication.FRAGMENT_TAG != Constants.HOME) {
				onClickChangeView(Constants.HOME);
				changTitleAndBottom(Constants.HOME);
			}
			break;
		}
		case R.id.tab1_tv: {
			if (MainApplication.FRAGMENT_TAG != Constants.TAB1) {
				onClickChangeView(Constants.TAB1);
				changTitleAndBottom(Constants.TAB1);
			}
			break;
		}
		case R.id.tab2_tv: {
			if (MainApplication.FRAGMENT_TAG != Constants.TAB2) {
				onClickChangeView(Constants.TAB2);
				changTitleAndBottom(Constants.TAB2);
			}
			break;
		}
		case R.id.tab3_tv: {
			if (MainApplication.FRAGMENT_TAG != Constants.TAB3) {
				onClickChangeView(Constants.TAB3);
				changTitleAndBottom(Constants.TAB3);
			}
			break;
		}
		default:
			break;
		}
		
	}
}
