package com.tydic.app.homedemo;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class HomeActivity extends Activity {

	private ActionBar mActionBar;
	private TextView openDrawLayout;
	private DrawerLayout mDrawerLayout; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		mActionBar = getActionBar();
		mActionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		
//		Drawable drawable = getResources().getDrawable(R.drawable.actionbar_bg);
//		mActionBar.setBackgroundDrawable(drawable);
		mActionBar.setCustomView(R.layout.action_bar);
		mActionBar.setDisplayShowCustomEnabled(true); 

		initViews();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.actionbar_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void initViews() {
		openDrawLayout = (TextView) findViewById(R.id.draw_ic);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
	}
}
