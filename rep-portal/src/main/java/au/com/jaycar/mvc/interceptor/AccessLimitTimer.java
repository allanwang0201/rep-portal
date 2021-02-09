package au.com.jaycar.mvc.interceptor;

import java.util.Timer;

public class AccessLimitTimer {
    Timer timer = new Timer();
    ResetTask task = new ResetTask();
    public Timer getTimer() {
		return timer;
	}
	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	public ResetTask getTask() {
		return task;
	}
	public void setTask(ResetTask task) {
		this.task = task;
	}
	public AccessLimitTimer(long seconds) {
        timer.scheduleAtFixedRate(task, 0, seconds*1000);
    }
}
