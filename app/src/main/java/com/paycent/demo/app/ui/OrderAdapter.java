
package com.paycent.demo.app.ui;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.paycent.demo.app.R;
import com.paycent.demo.app.model.Order;

import java.util.List;

/**
 * Provide views to RecyclerView with data from orderList.
 */
class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private static final String TAG = "CustomAdapter";

    private List<Order> orderList;

	private Activity mActivity;

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView titleView, numView, amountView;

	    private final ImageView iconView;

        ViewHolder(View v) {

            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                }
            });
            titleView = (TextView) v.findViewById(R.id.order_list_title);
	        numView = (TextView) v.findViewById(R.id.order_list_num);
	        amountView = (TextView) v.findViewById(R.id.order_list_amount);
	        iconView = (ImageView) v.findViewById(R.id.order_list_icon);
        }

        TextView getTitleView() {
            return titleView;
        }
	    TextView getNumView() {
		    return numView;
	    }
	    TextView getAmountView() {
		    return amountView;
	    }

	    ImageView getIconView() {
		    return iconView;
	    }
    }

    OrderAdapter(Activity activity, List<Order> dataSet) {
	    mActivity = activity;
        orderList = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
	    // Create a new view.
	    View v = LayoutInflater.from(viewGroup.getContext())
				    .inflate(R.layout.list_item_order, viewGroup, false);

	    return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

	    Log.d(TAG, "Element " + position + " set.");

        // Get element from your dataset at this position and replace the contents of the view
        // with that element
	    Order o = orderList.get(position);

	    TextView textView = viewHolder.getTitleView();
	    textView.setText(o.getTitle() );

	    textView = viewHolder.getNumView();
	    textView.setText(o.getNumber() );

	    textView = viewHolder.getAmountView();
	    textView.setText(o.getAmount() );

	    ImageView iconView = viewHolder.getIconView();
	    int resourceId = mActivity.getResources().getIdentifier(o.getIconName(), "drawable", mActivity.getPackageName());
	    iconView.setImageResource(resourceId);
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }
}
