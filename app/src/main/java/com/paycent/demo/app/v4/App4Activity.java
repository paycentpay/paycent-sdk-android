package com.paycent.demo.app.v4;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.paycent.demo.app.R;
import com.paycent.demo.app.model.Configuration;
import com.paycent.demo.app.model.OrderFactory;
import com.paycent.demo.app.ui.SimpleV4Fragment;
import com.paycent.android.sdk.SdkPayRequest;
import com.paycent.android.sdk.SdkPayResult;
import com.paycent.android.sdk.SdkPayTask;
import com.paycent.android.sdk.SdkPayTaskFactory;

public class App4Activity extends Activity {

	protected Fragment createInitFragment() {

		return new SimpleV4Fragment();
	}

	protected int getLayoutResId() {
		return R.layout.activity_fragment;
	}

	protected int getFragmentResId() {
		return R.id.fragmentContainer;
	}

	String TAG = this.getClass().getSimpleName();

	OrderFactory orderFactory;

	Configuration configuration;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		orderFactory = new OrderFactory();

		configuration = new Configuration(this);

		super.onCreate(savedInstanceState);

		setContentView(getLayoutResId());

		FragmentManager fm = getFragmentManager();
		Fragment fragment = fm.findFragmentById(getFragmentResId());

		if (fragment == null) {
			fragment = createInitFragment();
			fm.beginTransaction()
					.add(getFragmentResId(), fragment)
					.commit();
		}
	}

	private Handler mHandler = new Handler() {


		public void handleMessage(Message msg) {

			SdkPayResult result = (SdkPayResult) msg.obj;

			Log.d(TAG, result.toString() );
		}
	};

	public void pay() {


		final SdkPayRequest req = configuration.createSdkRequest();

		req.setOrderNo(String.valueOf(System.currentTimeMillis()));
		req.setAmount("5000");
		req.setCurrency("840");
		req.setProductName("SDK Game Card");
		req.setMp("");
		req.setOrderPeriod("30");
		req.setPayerLoginName("");

		SdkPayTask sdkTask = SdkPayTaskFactory.getInstance(this);

		sdkTask.pay(req, mHandler);
	}

}
