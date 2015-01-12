package com.urmwsk.mightytoastsample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.urmwsk.mightytoast.MightyDismissListener;
import com.urmwsk.mightytoast.MightyStyle;
import com.urmwsk.mightytoast.MightyToastView;
import com.urmwsk.mightytoast.MightyType;

public class SampleActivity extends ActionBarActivity {

	MightyStyle[] styles = {MightyStyle.ALERT, MightyStyle.INFO, MightyStyle.SUCCESS};
	int styleterator = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sample);
		final MightyToastView toastView = (MightyToastView) findViewById(R.id.toast);
		toastView.show("This is example toast message", MightyType.TAP_TO_DISMISS, MightyStyle.SUCCESS);
		toastView.registerDismissListener(new MightyDismissListener() {
			@Override
			public void onToastDismiss() {
				toastView.show("This is example toast message " + String.valueOf(styleterator + 1), MightyType.DURATION_SHORT, styles[styleterator % 3]);
				styleterator++;
			}
		});
	}
}
