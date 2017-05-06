package GameObjectsManagement.ObjectManagement;

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
    private double gameTime;
    private boolean isAccomplished;
    private long playerId;

    private Record(Builder builder){

        onCloud = builder.onCloud;
        objectName = builder.acquiredBy;
        playerId = builder.acquiredById;//comes from parent class
        gameTime = builder.gameTime;
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
        private double gameTime;
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

        public Builder gameTime(double accomplishmentDay){
            this.gameTime = accomplishmentDay;
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

    public double getAccomplishmentDay(){ return gameTime;}
    public String getDescription(){ return description;}
    public String getAccomplishedStoryEvent(){ return accomplishedStoryEvent;}
    public boolean isAccomplished(){return isAccomplished;}
    public String getRealTimeFormatted(){return realTimeFormatted;}
    public long getPlayerId(){return playerId;}
}
