package DatabaseManagement;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import GameObjectsManagement.ObjectManagement.*;
import GameObjectsManagement.ItemManagement.*;
import GameObjectsManagement.CharacterManagement.Character;
import GameObjectsManagement.CharacterManagement.*;

/**
 * Created by onursonmez on 22/04/2017.
 */
public class DatabaseManager {

    private static CloudStorageDao<Record> recordDAO;//access object for record datastore
    private static Map<String,AreaDatastore> areaDAOContainer;//access object for area datastore
    private static AreaDatastore areaDAO;
    private static PlayerDatastore playerDAO;//access object for player datastore
    private static String uniqueId;
    private static LocalStorageDao localStorageDao;

    private static final ExecutorService threadpool = Executors.newCachedThreadPool();

    private String workingArea;
    private ConnectionStatus lastConnectionStatus;


    public DatabaseManager(){
        lastConnectionStatus = ConnectionStatus.CONNECTED;//initial
        recordDAO = new RecordDatastore();
        playerDAO = new PlayerDatastore(uniqueId);
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

    public void processData(GameObject gameObject, WriteAction writeAction){

        writeAction.writeDataIntoCloud(gameObject);
    }

    public GameObject readData(long id, ReadAction readAction){
        return readAction.readDataInCloud(id);

    }

    public void clearData(long id, DeleteAction deleteAction){

        deleteAction.clearDataInCloud(id);

    }

    public List<Character> listCharacters(boolean isNewGame){

        List<Character> characterList;

        if(isNewGame){
            characterList= localStorageDao.parseJSONFiles(workingArea).getCharacterList();

            characterList.forEach(e->{
                threadpool.submit(()->{
                    AreaDatastore.WriteAction.WRITE_CHARACTER.writeDataIntoCloud(e);
                });
            });
        }
        else{
            characterList = areaDAO.listCharactersInArea();
        }
        return characterList;
    }


    public List<Item> listItems(boolean isNewGame){

        ArrayList<Item> itemList;

        if(isNewGame){
            itemList= localStorageDao.parseJSONFiles(workingArea).getItemList();

            itemList.forEach(e->{
                threadpool.submit(()->{
                    AreaDatastore.WriteAction.WRITE_CHARACTER.writeDataIntoCloud(e);
                });
            });
        }
        else{
            itemList = new ArrayList<>();
            List<Item> cloudList = areaDAO.listItemsInArea();

            cloudList.forEach(e-> {
                System.out.println("Obaaa");
                itemList.add((Item)localStorageDao.readJSONElement(e.getName(),localStorageDao.typeToClass(Constants.ITEM_ROOT + e.getType())));
            });
        }
        return itemList;
    }

    public List<Record> listRecords(){

        return recordDAO.listContent();
    }

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

    public static void initUserStaticId(){

        String userId = localStorageDao.getUserUniqueId();

        switch (userId){
            case Constants.INVALID_USER:
                uniqueId =  UUID.randomUUID().toString();
                break;//user does not exists

            default:
                uniqueId = userId;
                break;//user exist
        }
    }

    public void createUserLocal(long id){ localStorageDao.recordUserUniqueId(uniqueId,id);}

    public static boolean isUserExists(){
        return !localStorageDao.getUserUniqueId().equals(Constants.INVALID_USER);
    }

    public long getUserId(){ return localStorageDao.getUserId();}
}


