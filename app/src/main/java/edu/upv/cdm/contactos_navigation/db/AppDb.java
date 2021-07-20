package edu.upv.cdm.contactos_navigation.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import edu.upv.cdm.contactos_navigation.models.Contacto;

@Database(entities={Contacto.class},version=1)
public abstract class AppDb extends RoomDatabase {
    public abstract ContactoDao contactoDao();
    public static final String DB_NAME="practica03_database";
    private static AppDb instance;
    public static void init(AppDb appDb){
        if(appDb == null || instance!=null) return;
        instance=appDb;
    }
    public static AppDb getInstance(){return instance;}
}
