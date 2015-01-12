package com.urmwsk.mightytoast;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * This is mighty toast.
 */
public class MightyToastView extends LinearLayout {

	private static final long ANIMATION_DURATION = 500;

	private TextView messageView;
	private TextView dismissView;
	private MightyToast currentToast;
	private MightyDismissListener dismissListener;

	private Handler handler = new Handler();
	private Timer dismissTimer = new Timer();

	public MightyToastView(Context context) {
		super(context);
		init(null, 0);
		inflate(context);
	}

	public MightyToastView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs, 0);
		inflate(context);
	}

	public MightyToastView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(attrs, defStyle);
		inflate(context);
	}

	private void inflate(Context context) {
		View view = inflate(context, R.layout.mighty_layout, this);
		messageView = (TextView) view.findViewById(R.id.mighty_msg);
		dismissView = (TextView) view.findViewById(R.id.dismiss_msg);
	}

	private void init(AttributeSet attrs, int defStyle) {
		//final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MightyToastView, defStyle, 0);
		setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (currentToast != null && currentToast.getType() == MightyType.TAP_TO_DISMISS) {
					dismiss();
				}
			}
		});
	}

	//<API START>
	public void show(String message, MightyType type, MightyStyle style) {
		MightyToast toast = new MightyToast();
		toast.setMessage(message);
		toast.setType(type);
		toast.setStyle(style);
		toast.setColor(style.whatColor(getResources()));
		show(toast);
	}

	public void show(final MightyToast toast) {
		ScaleAnimation anim = new ScaleAnimation(1, 1, 0, 1);
		anim.setDuration(ANIMATION_DURATION);
		startAnimation(anim);
		currentToast = toast;
		this.setBackgroundColor(toast.getColor());
		messageView.setText(toast.getMessage());
		messageView.setVisibility(View.VISIBLE);
		if (toast.getType() == MightyType.TAP_TO_DISMISS) {
			dismissView.setVisibility(View.VISIBLE);
		} else {
			dismissTimer.schedule(new DismissTask(), toast.getType().getDuration());
		}
	}

	public void dismiss() {
		currentToast = null;
		ScaleAnimation anim = new ScaleAnimation(1, 1, 1, 0);
		anim.setDuration(ANIMATION_DURATION);
		anim.setAnimationListener(new Animation.AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				messageView.setVisibility(GONE);
				dismissView.setVisibility(GONE);
				if (dismissListener != null) {
					dismissListener.onToastDismiss();
				}
			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}
		});
		startAnimation(anim);
	}

	public void registerDismissListener(MightyDismissListener listener) {
		this.dismissListener = listener;
	}

	//<API END>

	private class DismissTask extends TimerTask {

		@Override
		public void run() {
			handler.post(new Runnable() {
				@Override
				public void run() {
					dismiss();
				}
			});
		}
	}
}
