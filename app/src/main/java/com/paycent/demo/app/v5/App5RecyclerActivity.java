package com.paycent.demo.app.v5;

import android.app.Fragment;

import com.paycent.demo.app.ui.AbstractOrderActivity;
import com.paycent.demo.app.ui.OrderReqRecyclerFragment;

public class App5RecyclerActivity extends AbstractOrderActivity {

	@Override
	protected Fragment createInitFragment() {
		return new OrderReqRecyclerFragment();
	}

}
