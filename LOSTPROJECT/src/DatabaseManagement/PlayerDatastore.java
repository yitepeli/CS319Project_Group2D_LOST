package DatabaseManagement;

import com.google.cloud.datastore.*;
import java.util.List;


import GameObjectsManagement.ObjectManagement.*;
import GameObjectsManagement.ItemManagement.*;
import GameObjectsManagement.CharacterManagement.*;

/**
 * Author:Onur Sönmez
 */
public class PlayerDatastore extends ParentDatastore{

    private KeyFactory playerKeyFactory;
    private static CloudStorageDao<Item> itemDatastore;
    private static CloudStorageDao<Player> playerStat;

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

    public void clearDataInPlayer(long id, DeleteAction deleteAction){
        deleteAction.clearDataInCloud(id);
    }

    public List<Item> listItemsInPlayerInventory(){
        return itemDatastore.listContent();
    }


    private class PlayerStat implements CloudStorageDao<Player>{
        private Player entityToPlayer(Entity entity){

            return new Player.Builder()
                    .userName(entity.getString(Constants.OBJECT_NAME))
                    .currentArea(entity.getString(Constants.CURRENT_AREA))
                    .defense((int)entity.getLong(Constants.DEFENSE))
                    .attack((int)entity.getLong(Constants.ATTACK))
                    .energy((int)entity.getLong(Constants.ENERGY))
                    .health((int)entity.getLong(Constants.HEALTH))
                    .hunger((int)entity.getLong(Constants.HUNGER))
                    .thirst((int)entity.getLong(Constants.THIRST))
                    .build();
        }

        public long create(Player player){
            IncompleteKey key = playerKeyFactory.newKey();//assign new key for storing in the cloud

            FullEntity<IncompleteKey> incItemEntity = Entity.newBuilder(key)
                    .set(Constants.OBJECT_NAME,player.getName())
                    .set(Constants.CURRENT_AREA, player.getCurrentPositionOfUser())
                    .set(Constants.DEFENSE,player.getDefense())
                    .set(Constants.ATTACK,player.getAttack())
                    .set(Constants.ENERGY,player.getEnergy())
                    .set(Constants.HEALTH,player.getHealth())
                    .set(Constants.HUNGER,player.getHunger())
                    .set(Constants.THIRST,player.getThirst())
                    .build();

            //insert object mapping here!

            Entity entity = datastore.add(incItemEntity);
            return entity.getKey().getId();
        }

        public Player read(long id){
            Entity entity = datastore.get(playerKeyFactory.newKey(id));//getting entity from the GAE
            return entityToPlayer(entity);
        }

        public void update(Player player){

            Key key = playerKeyFactory.newKey(player.getCloudId());//insert item key here

            Entity entity = Entity.newBuilder(key)
                    .set(Constants.OBJECT_NAME,player.getName())
                    .set(Constants.CURRENT_AREA, player.getCurrentPositionOfUser())
                    .set(Constants.DEFENSE,player.getDefense())
                    .set(Constants.ATTACK,player.getAttack())
                    .set(Constants.ENERGY,player.getEnergy())
                    .set(Constants.HEALTH,player.getHealth())
                    .set(Constants.HUNGER,player.getHunger())
                    .set(Constants.THIRST,player.getThirst())
                    .build();

            datastore.update(entity);
        }

        public void delete(long id){
            datastore.delete(playerKeyFactory.newKey(id));
        }


        public List<Player> listContent(){
            return null;//no player list enabled in this point!
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
                    gameObject.setCloudId(cloudId);
                    gameObject.setOnCloud(true);
                }
            }
        },
        WRITE_PLAYER{
            void writeDataIntoCloud(GameObject gameObject){
                if(gameObject.isOnCloud()){
                    playerStat.update((Player)gameObject);
                }
                else{
                    long cloudId = playerStat.create((Player)gameObject);
                    gameObject.setCloudId(cloudId);
                    gameObject.setOnCloud(true);
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



