package com.tydic.app.homedemo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.FragmentManager;

public class MainApplication extends Application {

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}

	public static int FRAGMENT_TAG = 0;
	public static FragmentManager homeFragmentManager;
	private static List<Activity> exitActivity = new ArrayList<Activity>();

	public static void addActivity(Activity activity) {
		exitActivity.add(activity);
	}

	public static void clearActivity() {
		for (Activity activity : exitActivity) {
			if (activity != null)
				activity.finish();
		}
	}
}
