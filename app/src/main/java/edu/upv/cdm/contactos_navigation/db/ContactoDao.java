package edu.upv.cdm.contactos_navigation.db;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import edu.upv.cdm.contactos_navigation.models.Contacto;

@Dao
public interface ContactoDao {
    @Query("SELECT id, nombre, telefono, email FROM contactos ORDER BY nombre")
    List<Contacto> getAll();

    @Query("SELECT id, nombre, telefono, email FROM contactos WHERE id IN (:ids) ORDER BY nombre")
    List<Contacto> getByIdIn(long[] ids);

    @Query("SELECT id, nombre, telefono, email FROM contactos WHERE id=:id")
    Contacto getByd(long id);

    @Insert
    long insert(Contacto contacto);

    @Insert
    long[] insert(Contacto... contactos);



    @Update
    void update(Contacto contacto);

    @Delete
    void delete(Contacto contacto);
}
