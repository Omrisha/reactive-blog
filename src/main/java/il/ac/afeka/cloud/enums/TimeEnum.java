package il.ac.afeka.cloud.enums;

public enum TimeEnum {
	lastDay(1000 * 60 * 60 * 24),
	lastWeek(1000 * 60 * 60 * 24 * 7),
	lastMonth(1000 * 60 * 60 * 24 * 30);
	
	private final long millisecs;
	
	private TimeEnum(long millisecs) {
		this.millisecs = millisecs;
	}
	
	public long millisecs() {
		return this.millisecs;
	}
}
