package DatabaseManagement;

/**
 * Created by onursonmez on 28/04/2017.
 */
//Static Nested class requires less memory space!

import com.google.cloud.datastore.*;

import java.util.ArrayList;
import java.util.List;

public class ItemDatastore implements CloudStorageDao<Item>{

    private Datastore datastore;
    private KeyFactory itemKeyFactory;
    private String key;

    public ItemDatastore(String key, Datastore datastore){
        this.key = key;
        this.datastore = datastore;
        itemKeyFactory = datastore.newKeyFactory().setKind(key);
    }

    private Item entityToItem(Entity entity){

        return null;//will be updated!
    }


    public long create(Item item){
        IncompleteKey key = itemKeyFactory.newKey();//assign new key for storing in the cloud

        FullEntity<IncompleteKey> incItemEntity = Entity.newBuilder(key)
                .set(Item.OBJECT_NAME,item.getObjectName())
                .set(Item.OBJECT_ID,item.getId())
                .set(Item.VISIBLE,item.isVisible())
                .build();

        //insert object mapping here!

        Entity entity = datastore.add(incItemEntity);
        return entity.getKey().getId();

    }

    public Item read(long id){
        Entity entity = datastore.get(itemKeyFactory.newKey(id));//getting entity from the GAE
        return entityToItem(entity);
    }


    public void update(Item item){
        Key key = itemKeyFactory.newKey(item.getCloudId());//insert item key here

        Entity entity = Entity.newBuilder(key)
                .set(Item.OBJECT_NAME,item.getObjectName())
                .set(Item.OBJECT_ID,item.getId())
                .set(Item.VISIBLE,item.isVisible())
                .build();

        datastore.update(entity);
    }

    public void delete(long id){
        datastore.delete(itemKeyFactory.newKey(id));
    }

    public List<Item> listContent(){
        //query entities
        Query<Entity>  query = Query.newEntityQueryBuilder()
                .setKind(key)
                .setStartCursor(null)
                .build();

        //Running query and searching matching pairs!

        QueryResults<Entity> queryResults = datastore.run(query);//running query
        ArrayList<Item> recordList = new ArrayList<>();

        //converts all entities to the root object
        while(queryResults.hasNext()){
            recordList.add(entityToItem(queryResults.next()));
        }
        return recordList;
    }

}