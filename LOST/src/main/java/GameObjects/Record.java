
public class Record extends GameObject {

	private String accomplishedTime;
	private int accomplishmentDay;
	private String accomplishedStoryEvent;
	private boolean isAccomplished;
	private int currentGameTime;
	
	public String getAccomplishedTime(){
		
		return accomplishedTime;
	}
	
	public int getAccomplishmentDay(){
		
		return accomplishmentDay;
	}
	
	public String getAccomplishedStoryEvent(){
		
		return accomplishedStoryEvent;
	}
	
	public boolean isAccomplished(){
		
		return isAccomplished;
	}
	
	public int getCurrentGameTime(){
		
		return currentGameTime;
	}
	
	public void getAccomplishedTime(String accomplishedTime){
		
		this.accomplishedTime = accomplishedTime;
	}
	
	public void setAccomplishmentDay(int accomplishmentDay){
		
		this.accomplishmentDay = accomplishmentDay;
	}
	
	public void setAccomplishedStoryEvent(String accomplishedStoryEvent){
		
		this.accomplishedStoryEvent = accomplishedStoryEvent;
	}
	
	public void setIsAccomplished(boolean isAccomplished){
		
		this.isAccomplished = isAccomplished;
	}
	
	public void setCurrentGameTime(int currentGameTime){
		
		this.currentGameTime = currentGameTime;
	}
}
