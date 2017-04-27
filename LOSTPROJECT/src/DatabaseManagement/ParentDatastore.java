package DatabaseManagement;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.KeyFactory;

/**
 * Created by onursonmez
 */
public abstract class ParentDatastore {

    protected Datastore datastore;

    public ParentDatastore(){
        datastore = DatastoreOptions.getDefaultInstance().getService();//accessing google cloud service
    }
}
