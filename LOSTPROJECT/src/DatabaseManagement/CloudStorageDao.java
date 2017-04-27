
package DatabaseManagement;
import com.google.cloud.datastore.KeyFactory;

import java.util.List;

/**
 * Created by onursonmez on 22/04/2017.
 */
public interface CloudStorageDao<T> {

    long create(T obj);

    T read(long id);

    void update(T obj);

    void delete(long id);

    List<T> listContent();
}
