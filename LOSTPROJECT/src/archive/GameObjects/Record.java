package GameObjects;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Author: Onur Sönmez
 * Github: @sonmezonur
 */
public class Record extends GameObject {

    private String accomplishedStoryEvent,realTimeFormatted;
    private int accomplishmentDay;
    private boolean isAccomplished;
    private long playerId;

    //For Database Querying...
    public static final String ACCOMPLISHED_STORY_EVENT = "accomplishedStoryEvent";
    public static final String DESCRIPTION = "description"; //make updatable elements as enum
    public static final String ACCOMPLISHED_GAME_TIME = "accomplishedGameTime";
    public static final String IS_ACCOMPLISHED = "isAccomplished";
    public static final String REAL_TIME = "realTime";

    private Record(Builder builder){

        onCloud = builder.onCloud;
        objectName = builder.acquiredBy;
        playerId = builder.acquiredById;//comes from parent class

        accomplishmentDay = builder.accomplishmentDay;
        accomplishedStoryEvent = builder.accomplishedStoryEvent;
        description = builder.description;
        isAccomplished = builder.isAccomplished;
        realTimeFormatted = builder.realTimeFormatted;
    }

   /*
    * Builder pattern...
    */

    public static class Builder{
        private String acquiredBy;
        private String description;
        private String accomplishedStoryEvent;
        private String realTimeFormatted;
        private int accomplishmentDay;
        private long acquiredById;
        private boolean isAccomplished,onCloud;

        public Builder onCloud(boolean onCloud){
            this.onCloud = onCloud;
            return this;
        }


        public Builder acquiredBy(String acquiredBy){
            this.acquiredBy = acquiredBy;
            return this;
        }

        public Builder acquiredById(long acquiredById){
            this.acquiredById = acquiredById;
            return this;
        }

        public Builder description(String description){
            this.description = description;
            return this;
        }

        public Builder accomplishedStoryEvent(String accomplishedStoryEvent){
            this.accomplishedStoryEvent = accomplishedStoryEvent;
            return this;
        }

        public Builder accomplishmentDay(int accomplishmentDay){
            this.accomplishmentDay = accomplishmentDay;
            return this;
        }

        public Builder isAccomplished(boolean isAccomplished){
            this.isAccomplished = isAccomplished;
            return this;
        }

        private void formatDate(){
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            realTimeFormatted = dateFormat.format(date);
        }

        public Record build(){
            formatDate();
            return new Record(this);
        }
    }

    public int getAccomplishmentDay(){ return accomplishmentDay;}
    public String getDescription(){ return description;}
    public String getAccomplishedStoryEvent(){ return accomplishedStoryEvent;}
    public boolean isAccomplished(){return isAccomplished;}
    public String getRealTimeFormatted(){return realTimeFormatted;}
    public long getPlayerId(){return playerId;}
}
