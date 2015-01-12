package com.urmwsk.mightytoast;

public class MightyToast {

	private MightyStyle style = MightyStyle.INFO;
	private MightyType type = MightyType.TAP_TO_DISMISS;
	private String message;
	private int color;

	public MightyStyle getStyle() {
		return style;
	}

	public void setStyle(MightyStyle style) {
		this.style = style;
	}

	public MightyType getType() {
		return type;
	}

	public void setType(MightyType type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getColor() {
		return color;
	}
}
