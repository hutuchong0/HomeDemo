package com.tydic.app.homedemo;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

public class BaseActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MainApplication.addActivity(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	protected void showLongToast(String msg) {
		Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_LONG).show();
	}

	protected void showShortToast(String msg) {
		Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_SHORT).show();
	}

	protected void exitApp() {// APP程序退出
		finish();
		MainApplication.clearActivity();
		// 杀死该应用进程
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
	}
}
