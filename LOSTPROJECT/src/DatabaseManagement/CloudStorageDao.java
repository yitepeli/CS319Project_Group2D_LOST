
package DatabaseManagement;

/**
 * Created by onursonmez on 22/04/2017.
 * Description: Generic interface which applies all object types to work on cloud.
 */
import java.util.List;

public interface CloudStorageDao<T> {

   /**
     * Creates game object in cloud
     * @param Object Any object type can enter 
     * @return long Returns cloud id of  object
     */

    long create(T obj);

    /**
     * Reads object from cloud
     * @param long  Id of object that will be retrieved from cloud
     * @return long Returns cloud id of  object
     */

    T read(long id);

    /**
     * Updates object in cloud
     * @param long  Id of object that will be updated
     */

    void update(T obj);

    /**
     * Delete object from cloud with defined key
     * @param long  Id of object that will be removed
     */

    void delete(long id);

    /**
     * List objects which is owned by spesific type
     * @return List of objects
     */

    List<T> listContent();
}
