package DatabaseManagement;

/**
 *  Author: Onur Sönmez
 *  Github: @sonmezonur
 *  Description: Facade database controller that splits all persistent data management operations in subsytems.
 */

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import GameObjectsManagement.ObjectManagement.*;
import GameObjectsManagement.ItemManagement.*;
import GameObjectsManagement.CharacterManagement.Character;

public class DatabaseManager {

    private static CloudStorageDao<Record> recordDAO;//access object for record datastore
    private static Map<String,AreaDatastore> areaDAOContainer;//access object for area datastore
    private static AreaDatastore areaDAO;
    private static PlayerDatastore playerDAO;//access object for player datastore
    private static String uniqueId;//unique id of user
    private static LocalStorageDao localStorageDao;//local access object

    private static final ExecutorService threadpool = Executors.newCachedThreadPool();
    // Applies future task for asynch CRUD operations. A large initial data will be written into cloud.
    // So system promises the data comes after will be written into cloud soon 

    private String workingArea;//All areas has their own storage so system applies seperate buckets for data
    private ConnectionStatus lastConnectionStatus;//internet connection status


    public DatabaseManager(){
        lastConnectionStatus = ConnectionStatus.CONNECTED;//initial
        recordDAO = new RecordDatastore();//dynamic bindig for RecordDatastore
        playerDAO = new PlayerDatastore(uniqueId);//player id
        areaDAOContainer = new HashMap<>();
        areaDAO = null;
        localStorageDao = new LocalStorageDao();
    }

    public void setWorkingArea(String areaName){
        workingArea = areaName;
        areaDAO = areaDAOContainer.get(areaName);
    }//set current working area

    public void setAreasInCloud(String areaName){
        areaDAOContainer.put(areaName,new AreaDatastore(uniqueId,areaName));
    }


    /**
     *  Writing data for all gameobject types. Instead of seperate write operations, uses enum
     *  @param GameObject any kind of object
     *  @param WriteAction enum type that processes data for all types, controller just direct which type that will be processed
     */

    public void processData(GameObject gameObject, WriteAction writeAction){

        writeAction.writeDataIntoCloud(gameObject);
    }
    /**
    *  Reading data for all gameobject types. 
    *  @param long Id of object that will be readed
    *  @param ReadAction enum type
    *  @return GameObject
    */

    public GameObject readData(long id, ReadAction readAction){

        return readAction.readDataInCloud(id);
    }
    /**
     *  Reading data for all gameobject types.
     *  @param long Id of object that will be readed
     *  @param DeleteAction enum type
     */

    public void clearData(long id, DeleteAction deleteAction){

        deleteAction.clearDataInCloud(id);
    }
    /**
     *  Listing all characters according to the phase of game, if it is new game it will read local data, otherwise cloud data
     *  @param boolean isNewGame
     *  @return List<Character>
     */

    public List<Character> listCharacters(boolean isNewGame){

        List<Character> characterList;

        if(isNewGame){
            characterList= localStorageDao.parseJSONFiles(workingArea).getCharacterList();
/*
            characterList.forEach(e->{
                threadpool.submit(()->{//assigns future task for cloud processing!
                    AreaDatastore.WriteAction.WRITE_CHARACTER.writeDataIntoCloud(e);
                });
            });*/
        }
        else{
            characterList = areaDAO.listCharactersInArea();
        }
        return characterList;
    }

    /**
     *  Listing all items in Area according to the phase of game.
     *  @param boolean isNewGame
     *  @return List<Item>
     */

    public List<Item> listItems(boolean isNewGame){

        ArrayList<Item> itemList;

        if(isNewGame){
            itemList= localStorageDao.parseJSONFiles(workingArea).getItemList();
/*
            itemList.forEach(e->{
                threadpool.submit(()->{//Assigns future task for cloud processing, it will not wait until this line execute!
                    AreaDatastore.WriteAction.WRITE_ITEM.writeDataIntoCloud(e);
                });
            });*/
        }
        else{
            itemList = new ArrayList<>();
            List<Item> cloudList = areaDAO.listItemsInArea();//CLOUD DATA

            cloudList.forEach(e-> {
                itemList.add((Item)localStorageDao.readJSONElement(e.getName(),Constants.ITEM_ROOT + e.getType()));
            });
        }
        return itemList;
    }

    /**
     *  Reads json object from local
     *  @param objectName
     *  @param type Type of object
     *  @return Item
     */

    public Item readItem(String objectName,String type){

        return (Item)localStorageDao.readJSONElement(objectName,Constants.ITEM_ROOT + type);

    }

    /**
     *  Listing universal record table from cloud storaget
     *  @return List<Record>
     */

    public List<Record> listRecords(){

        return recordDAO.listContent();
    }

    /**
     *  Checks connection status
     *  @return ConnectionStatus (Recent)
     */
    private ConnectionStatus getConnectionStatus(){

        ConnectionStatus currentConnectionStatus;

        Socket socket = new Socket();
        InetSocketAddress address = new InetSocketAddress("cloud.google.com",80);

        try{
            socket.connect(address,3000);//set timeout
            currentConnectionStatus = ConnectionStatus.CONNECTED;
        }
        catch (IOException e){
            currentConnectionStatus = ConnectionStatus.DISCONNECTED;
        }
        return currentConnectionStatus;
    }

    /**
     *  Enum types that operates all operations in different subsystems,
     *  They will dynamically bind to owner enum through abstract decleration
     *  Applies simple,readable and useful sytem...
     */

    public enum WriteAction{
        WRITE_RECORD{
            void writeDataIntoCloud(GameObject gameObject){

                if(gameObject.isOnCloud()){
                    recordDAO.update((Record)gameObject);
                }
                else{
                    long cloudId = recordDAO.create((Record) gameObject);
                    gameObject.setOnCloud(true);
                    gameObject.setCloudId(cloudId);
                }
            }
        },
        WRITE_PLAYER{
            void writeDataIntoCloud(GameObject gameObject){
                playerDAO.writeDataIntoPlayer(gameObject, PlayerDatastore.WriteAction.WRITE_PLAYER);
            }
        },
        WRITE_ITEM_INTO_PLAYER{
            void writeDataIntoCloud(GameObject gameObject){
                playerDAO.writeDataIntoPlayer(gameObject,PlayerDatastore.WriteAction.WRITE_ITEM);
            }
        },
        WRITE_ITEM_INTO_AREA{
            void writeDataIntoCloud(GameObject gameObject){
                areaDAO.writeDataIntoArea(gameObject,AreaDatastore.WriteAction.WRITE_ITEM);
            }
        },
        WRITE_CHARACTER{
            void writeDataIntoCloud(GameObject gameObject){
                areaDAO.writeDataIntoArea(gameObject,AreaDatastore.WriteAction.WRITE_CHARACTER);
            }
        };
        abstract void writeDataIntoCloud(GameObject gameObject);
    }
    public enum ReadAction{
        READ_RECORD{
            GameObject readDataInCloud(long id){
                return recordDAO.read(id);
            }
        },
        READ_PLAYER{
            GameObject readDataInCloud(long id){
                return playerDAO.readDataFromPlayer(id,PlayerDatastore.ReadAction.READ_PLAYER);
            }
        },
        READ_CHARACTER{
            GameObject readDataInCloud(long id){
                return areaDAO.readDataFromArea(id,AreaDatastore.ReadAction.READ_CHARACTER);
            }
        },
        READ_ITEM_FROM_AREA{
            GameObject readDataInCloud(long id){
                return areaDAO.readDataFromArea(id,AreaDatastore.ReadAction.READ_ITEM);
            }
        },
        READ_ITEM_FROM_PLAYER{
            GameObject readDataInCloud(long id){
                return playerDAO.readDataFromPlayer(id,PlayerDatastore.ReadAction.READ_PLAYER);
            }
        };
        abstract GameObject readDataInCloud(long id);
    }

    public enum DeleteAction{
        DELETE_RECORD{
            void clearDataInCloud(long id){
                recordDAO.delete(id);
            }
        },
        DELETE_CHARACTER{
            void clearDataInCloud(long id){
                areaDAO.clearDataInArea(id,AreaDatastore.DeleteAction.DELETE_CHARACTER);
            }
        },
        DELETE_ITEM_FROM_AREA{
            void clearDataInCloud(long id){
                areaDAO.clearDataInArea(id,AreaDatastore.DeleteAction.DELETE_ITEM);
            }
        },

        DELETE_ITEM_FROM_PLAYER{
            void clearDataInCloud(long id){
                playerDAO.clearDataInPlayer(id,PlayerDatastore.DeleteAction.DELETE_PLAYER);
            }
        },
        DELETE_PLAYER{
            void clearDataInCloud(long id){
                playerDAO.clearDataInPlayer(id,PlayerDatastore.DeleteAction.DELETE_ITEM);
            }
        };
        abstract void clearDataInCloud(long id);
    }

    public boolean isConnectionAvailable(){
        return (getConnectionStatus() == ConnectionStatus.CONNECTED);
    }

    public enum ConnectionStatus{ CONNECTED, DISCONNECTED }

    /**
     *  Static user id initiliazer
     *  @param isNewGame
     */

    public static void initUserStaticId(boolean isNewGame){

        if(isNewGame){
            uniqueId = UUID.randomUUID().toString();
        }
        else{
            uniqueId = LocalStorageDao.getUserUniqueId();
        }
    }
    /**
     *  Crates user local id
     *  @param long id
     */

    public void createUserLocal(long id){ localStorageDao.recordUserUniqueId(uniqueId,id);}

    public void recordSettings(boolean isSoundActive, int panelSize){
        localStorageDao.recordSettings(isSoundActive,panelSize);
    }

    /**
     *  Static checker for existence of user! It should be applied before environment created
     *  @return boolean
     */
    public static boolean isUserExists(){
        return !localStorageDao.getUserUniqueId().equals(Constants.INVALID_USER);
    }

    /**
     *  Getting user id from util.prefs
     *  @return long
     */
    public long getUserId(){ return localStorageDao.getUserId();}
}


