package DatabaseManagement;

import com.google.cloud.datastore.*;
import java.util.ArrayList;
import GameObjectsManagement.ObjectManagement.*;

/**
 * Created by onursonmez 
 */
public class RecordDatastore extends ParentDatastore implements CloudStorageDao<Record> {

    private KeyFactory keyFactory;

    public RecordDatastore(){
        super();
        keyFactory = datastore.newKeyFactory().setKind("Record");//CREATING UNIVERSAL RECORD TABLE!
    }


    public Record entityToRecord(Entity entity){

        return new Record.Builder()
                .acquiredBy(entity.getString(Constants.OBJECT_NAME))
                .acquiredById(entity.getLong(Constants.OBJECT_ID))
                .accomplishedStoryEvent(entity.getString(Constants.ACCOMPLISHED_STORY_EVENT))
                .description(entity.getString(Constants.DESCRIPTION))
                .accomplishmentDay((int)entity.getLong(Constants.ACCOMPLISHED_GAME_TIME))
                .isAccomplished(entity.getBoolean(Constants.IS_ACCOMPLISHED))
                .build();
    }


    public long create(Record record){

        IncompleteKey key = keyFactory.newKey();//assign new key for storing in the cloud

        FullEntity<IncompleteKey> incScoreEntity = Entity.newBuilder(key)
                .set(Constants.OBJECT_NAME,record.getName())
                .set(Constants.OBJECT_ID,record.getId())
                .set(Constants.IS_ACCOMPLISHED,record.isAccomplished())
                .set(Constants.DESCRIPTION,record.getDescription())
                .set(Constants.ACCOMPLISHED_STORY_EVENT,record.getAccomplishedStoryEvent())
                .set(Constants.ACCOMPLISHED_GAME_TIME,record.getAccomplishmentDay())
                .set(Constants.REAL_TIME, record.getRealTimeFormatted())
                .build();

        Entity entity = datastore.add(incScoreEntity);//now adding completed entity into datastore
        return entity.getKey().getId();//also returns the id of created entity

    }

    public Record read(long id){
        Entity entity = datastore.get(keyFactory.newKey(id));//getting entity from the GAE
        return entityToRecord(entity);
    }

    public void update(Record record){
        Key key = keyFactory.newKey(record.getCloudId());
        Entity entity = Entity.newBuilder(key)
                .set(Constants.OBJECT_NAME,record.getName())
                .set(Constants.DESCRIPTION,record.getId())
                .set(Constants.ACCOMPLISHED_STORY_EVENT,record.getAccomplishedStoryEvent())
                .set(Constants.OBJECT_ID,record.getId())
                .set(Constants.IS_ACCOMPLISHED,record.isAccomplished())
                .set(Constants.ACCOMPLISHED_GAME_TIME,record.getAccomplishmentDay())
                .set(Constants.REAL_TIME,record.getRealTimeFormatted())
                .build();
        datastore.update(entity);//update existed one!

    }

    public void delete(long id){
        datastore.delete(keyFactory.newKey(id));//search for entity object which defined key
    }

    public ArrayList<Record> listContent(){

        //query entities
        Query<Entity>  query = Query.newEntityQueryBuilder()
                .setKind("Record")
                .setLimit(10)
                .setStartCursor(null)
                .setOrderBy(StructuredQuery.OrderBy.desc(Constants.ACCOMPLISHED_GAME_TIME))//fix this one
                .build();

        //Running query and searching matching pairs!

        QueryResults<Entity> queryResults = datastore.run(query);//running query
        ArrayList<Record> recordList = new ArrayList<>();

        //converts all entities to the root object
        while(queryResults.hasNext()){
            recordList.add(entityToRecord(queryResults.next()));
        }
        return recordList;
    }
}
