package DatabaseManagement;
import com.google.cloud.datastore.*;

import java.util.List;

/**
 * Created by onursonmez
 */
public class AreaDatastore extends ParentDatastore{

    //avoiding both methods have same erasure!

    private static ItemDatastore itemDatastore;
    private static CharacterDatastore characterDatastore;
    private KeyFactory characterKeyFactory;


    public AreaDatastore(String uniqueId,String areaName){
        super();
        String key = uniqueId + "/" + areaName;
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

        private Character entityToItem(Entity entity){
            return null;//insert
        }
        //insert here


        public long create(Character character){
            IncompleteKey key = characterKeyFactory.newKey();//assign new key for storing in the cloud

            FullEntity<IncompleteKey> incItemEntity = Entity.newBuilder(key)
                    .build();

            //insert object mapping here!

            Entity entity = datastore.add(incItemEntity);
            return entity.getKey().getId();
        }

        public Character read(long id){
            Entity entity = datastore.get(characterKeyFactory.newKey(id));//getting entity from the GAE
            return entityToItem(entity);
        }


        public void update(Character obj){
            Key key = characterKeyFactory.newKey(0);//insert item key here

            Entity entity = Entity.newBuilder(key)
                    .build();

            datastore.update(entity);
        }

        public void delete(long id){
            datastore.delete(characterKeyFactory.newKey(id));
        }

        public List<Character> listContent(){
            return null;
        }

    }

    public enum WriteAction{
        WRITE_ITEM{
            void writeDataIntoCloud(GameObject gameObject){
                if(gameObject.isOnCloud()){
                    itemDatastore.update((Item)gameObject);
                }
                else{
                    itemDatastore.create((Item)gameObject);
                }
            }
        },
        WRITE_CHARACTER{
            void writeDataIntoCloud(GameObject gameObject){
                if(gameObject.isOnCloud()){
                    characterDatastore.update((Character)gameObject);
                }
                else{
                    characterDatastore.create((Character)gameObject);
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
