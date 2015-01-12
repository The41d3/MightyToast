package com.urmwsk.mightytoast;

public enum MightyType {
	TAP_TO_DISMISS(0), DURATION_LONG(5000), DURATION_SHORT(2000);

	private final long duration;

	MightyType(long duration) {
		this.duration = duration;
	}

	public long getDuration() {
		return duration;
	}
}
