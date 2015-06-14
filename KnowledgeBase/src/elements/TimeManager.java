package elements;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class TimeManager
{
	private String currentTime;
	
	public TimeManager()
	{
		setCurrentTime(getCurrentTime());
	}
	
	public String getCurrentTime()
	{
		long time = System.currentTimeMillis();
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		currentTime = dayTime.format(new Date(time));
		
		return currentTime;
	}
	
	public String getMsgTime()
	{
		long time = System.currentTimeMillis();
		SimpleDateFormat dayTime = new SimpleDateFormat("yy-MM-dd-HH:mm");
		String curTime = dayTime.format(new Date(time));
		
		return curTime;
	}
	
	public void setCurrentTime(String currentTime)
	{
		this.currentTime = currentTime;
	}
}
