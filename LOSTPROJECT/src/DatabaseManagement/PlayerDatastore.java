package DatabaseManagement;

import com.google.cloud.datastore.*;

import java.util.Arrays;
import java.util.List;

/**
 * Author:Onur Sönmez
 */
public class PlayerDatastore extends ParentDatastore{

    private KeyFactory playerKeyFactory;
    private static ItemDatastore itemDatastore;
    private static PlayerStat playerStat;

    public PlayerDatastore(String uniqueId){
        super();
        playerKeyFactory = datastore.newKeyFactory().setKind(uniqueId + "/Stat");
        itemDatastore = new ItemDatastore(uniqueId + "/Inventory",datastore);
        playerStat = new PlayerStat();
    }

    public void writeDataIntoPlayer(GameObject gameObject, WriteAction writeAction){
        writeAction.writeDataIntoCloud(gameObject);
    }

    public GameObject readDataFromPlayer(long id, ReadAction readAction){
        return readAction.readDataInCloud(id);
    }

    public void clearDataInPlayeer(long id, DeleteAction deleteAction){
        deleteAction.clearDataInCloud(id);
    }

    public List<Item> listItemsInPlayerInventory(){
        return itemDatastore.listContent();
    }


    private class PlayerStat implements CloudStorageDao<Player>{
        private Player entityToPlayer(Entity entity){
            //insert here
            return null;
        }

        public long create(Player obj){
            IncompleteKey key = playerKeyFactory.newKey();//assign new key for storing in the cloud

            FullEntity<IncompleteKey> incItemEntity = Entity.newBuilder(key)
                    //.set(Player.OBJECT_NAME,player.getName())
                    .build();

            //insert object mapping here!

            Entity entity = datastore.add(incItemEntity);
            return entity.getKey().getId();
        }

        public Player read(long id){
            Entity entity = datastore.get(playerKeyFactory.newKey(id));//getting entity from the GAE
            return entityToPlayer(entity);
        }

        public void update(Player obj){

            Key key = playerKeyFactory.newKey(0);//insert item key here

            Entity entity = Entity.newBuilder(key)
                    .build();

            datastore.update(entity);
        }

        public void delete(long id){
            datastore.delete(playerKeyFactory.newKey(id));
        }


        public Result<Player> listContent(String cursor){

            return null;
        }

        public List<Player> listContent(){
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
        WRITE_PLAYER{
            void writeDataIntoCloud(GameObject gameObject){
                if(gameObject.isOnCloud()){
                    playerStat.update((Player)gameObject);
                }
                else{
                    playerStat.create((Player)gameObject);
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

        READ_PLAYER{
            GameObject readDataInCloud(long id){
                return playerStat.read(id);
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
        DELETE_PLAYER{
            void clearDataInCloud(long id){
                playerStat.delete(id);
            }
        };
        abstract void clearDataInCloud(long id);
    }



}
