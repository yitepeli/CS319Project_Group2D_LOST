package DatabaseManagement;
import com.google.cloud.datastore.*;

import java.util.ArrayList;
import java.util.List;

import GameObjectsManagement.ItemManagement.*;
import GameObjectsManagement.ObjectManagement.*;
import GameObjectsManagement.CharacterManagement.Character;
/**
 * Created by onursonmez
 */
public class AreaDatastore extends ParentDatastore{

    //avoiding both methods have same erasure!

    private static CloudStorageDao<Item> itemDatastore;
    private static CloudStorageDao<Character> characterDatastore;
    private KeyFactory characterKeyFactory;
    private String key;


    public AreaDatastore(String uniqueId,String areaName){
        super();
        key = uniqueId + "/" + areaName;
        characterKeyFactory = datastore.newKeyFactory().setKind(key + "/Character");
        itemDatastore = new ItemDatastore(key + "/Item",datastore);
        characterDatastore = new CharacterDatastore();
    }


    public void writeDataIntoArea(GameObject gameObject, WriteAction writeAction){
        writeAction.writeDataIntoCloud(gameObject);
    }

    public GameObject readDataFromArea(long id, ReadAction readAction){
        return readAction.readDataInCloud(id);
    }

    public void clearDataInArea(long id, DeleteAction deleteAction){
        deleteAction.clearDataInCloud(id);
    }

    public List<Item> listItemsInArea(){
        return itemDatastore.listContent();
    }

    public List<Character> listCharactersInArea(){
        return characterDatastore.listContent();
    }


    private class CharacterDatastore implements CloudStorageDao<Character>{

        private Character entityToCharacter(Entity entity){

            Character character = new Character((int)entity.getLong(Constants.OBJECT_ID),
                                  entity.getString(Constants.OBJECT_NAME),
                                 entity.getString(Constants.DESCRIPTION));

            character.setDefense((int)entity.getLong(Constants.DEFENSE));
            character.setHealth((int)entity.getLong(Constants.HEALTH));
            return character;
        }
        //insert here


        public long create(Character character){
            IncompleteKey key = characterKeyFactory.newKey();//assign new key for storing in the cloud

            FullEntity<IncompleteKey> incItemEntity = Entity.newBuilder(key)
                    .set(Constants.OBJECT_NAME,character.getName())
                    .set(Constants.OBJECT_ID,character.getId())
                    .set(Constants.HEALTH,character.getHealth())
                    .set(Constants.DEFENSE,character.getDefense())
                    .set(Constants.DESCRIPTION,character.getDefense())
                    .build();

            //insert object mapping here!

            Entity entity = datastore.add(incItemEntity);
            return entity.getKey().getId();
        }

        public Character read(long id){
            Entity entity = datastore.get(characterKeyFactory.newKey(id));//getting entity from the GAE
            return entityToCharacter(entity);
        }

        public void update(Character character){
            Key key = characterKeyFactory.newKey(character.getCloudId());//insert item key here

            Entity entity = Entity.newBuilder(key)
                    .set(Constants.OBJECT_NAME,character.getName())
                    .set(Constants.OBJECT_ID,character.getId())
                    .set(Constants.HEALTH,character.getHealth())
                    .set(Constants.DEFENSE,character.getDefense())
                    .set(Constants.DESCRIPTION,character.getDefense())
                    .build();

            datastore.update(entity);
        }

        public void delete(long id){
            datastore.delete(characterKeyFactory.newKey(id));
        }

        public List<Character> listContent(){
            //query entities
            Query<Entity>  query = Query.newEntityQueryBuilder()
                    .setKind(key + "/Character")
                    .setStartCursor(null)
                    .build();

            //Running query and searching matching pairs!
            QueryResults<Entity> queryResults = datastore.run(query);//running query
            ArrayList<Character> characterList = new ArrayList<>();

            //converts all entities to the root object
            while(queryResults.hasNext()){
                characterList.add(entityToCharacter(queryResults.next()));
            }
            return characterList;
        }
    }

    public enum WriteAction{
        WRITE_ITEM{
            void writeDataIntoCloud(GameObject gameObject){
                if(gameObject.isOnCloud()){
                    itemDatastore.update((Item)gameObject);
                }
                else{

                    long cloudId = itemDatastore.create((Item)gameObject);
                    gameObject.setOnCloud(true);
                    gameObject.setCloudId(cloudId);
                }
            }
        },
        WRITE_CHARACTER{
            void writeDataIntoCloud(GameObject gameObject){
                if(gameObject.isOnCloud()){
                    characterDatastore.update((Character)gameObject);
                }
                else{
                    long cloudId = characterDatastore.create((Character)gameObject);
                    gameObject.setOnCloud(true);
                    gameObject.setCloudId(cloudId);
                    System.out.println(cloudId + " CHARACTER ID");
                }
            }
        };
        abstract void writeDataIntoCloud(GameObject gameObject);
    }

    public enum ReadAction{
        READ_ITEM{
            GameObject readDataInCloud(long id){
                return itemDatastore.read(id);
            }
        },

        READ_CHARACTER{
            GameObject readDataInCloud(long id){
                return characterDatastore.read(id);
            }
        };
        abstract GameObject readDataInCloud(long id);
    }

    public enum DeleteAction{
        DELETE_ITEM{
            void clearDataInCloud(long id){
                itemDatastore.delete(id);
            }
        },
        DELETE_CHARACTER{
            void clearDataInCloud(long id){
                characterDatastore.delete(id);
            }
        };
        abstract void clearDataInCloud(long id);
    }
}
