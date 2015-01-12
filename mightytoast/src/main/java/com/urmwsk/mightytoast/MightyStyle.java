package com.urmwsk.mightytoast;

import android.content.res.Resources;

public enum MightyStyle {
	INFO, ALERT, SUCCESS;

	public int whatColor(Resources resources) {
		switch (this) {
			case ALERT:
				return resources.getColor(R.color.default_alert);
			case SUCCESS:
				return resources.getColor(R.color.default_success);
			case INFO:
				return resources.getColor(R.color.default_info);
		}
		return 0;
	}
}
