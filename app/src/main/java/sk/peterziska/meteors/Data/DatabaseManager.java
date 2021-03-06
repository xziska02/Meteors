package sk.peterziska.meteors.Data;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class DatabaseManager {

    private static DatabaseManager instance;
    private Realm realm;

    private DatabaseManager() {
        realm = Realm.getDefaultInstance();
    }

    public static DatabaseManager getInstance( ) {
        if (instance == null){
            instance = new DatabaseManager();
        }
        return instance;
    }

    public void saveToDatabase(final List<Meteor> meteors) {
        realm.beginTransaction();
        realm.insertOrUpdate(meteors);
        realm.commitTransaction();
    }

    public boolean isDatabaseEmpty(){
        return realm.isEmpty();
    }

    public RealmResults<Meteor> getMeteors(){
        return realm.where(Meteor.class).
                findAll().sort("mass", Sort.DESCENDING);
    }

    public void realmClose(){
        instance = null;
        realm.close();
    }
}
