package DatabaseManagement;

/**
 * Created by onursonmez on 28/04/2017.
 */
//Static Nested class requires less memory space!

import GameObjectsManagement.ItemManagement.*;
import GameObjectsManagement.ObjectManagement.Constants;


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

        return new Item((int)entity.getLong(Constants.OBJECT_ID),
                entity.getString(Constants.OBJECT_NAME),
                entity.getString(Constants.DESCRIPTION));
    }


    public long create(Item item){
        IncompleteKey key = itemKeyFactory.newKey();//assign new key for storing in the cloud

        FullEntity<IncompleteKey> incItemEntity = Entity.newBuilder(key)
                .set(Constants.OBJECT_NAME,item.getName())
                .set(Constants.OBJECT_ID,item.getId())
                .set(Constants.DESCRIPTION,item.getDescription())
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
                .set(Constants.OBJECT_NAME,item.getName())
                .set(Constants.OBJECT_ID,item.getId())
                .set(Constants.DESCRIPTION,item.getDescription())
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
        ArrayList<Item> itemList = new ArrayList<>();

        //converts all entities to the root object
        while(queryResults.hasNext()){
            itemList.add(entityToItem(queryResults.next()));
        }
        return itemList;
    }

}