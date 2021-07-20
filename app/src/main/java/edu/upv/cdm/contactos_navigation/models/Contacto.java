package edu.upv.cdm.contactos_navigation.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="contactos")
public class Contacto implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name="nombre")
    private String nombre;
    @ColumnInfo(name="telefono")
    private String telefono;
    @ColumnInfo(name="email")
    private String email;
    public Contacto(){}
    public Contacto(Parcel parcel){
        id=parcel.readLong();
        nombre=parcel.readString();
        email=parcel.readString();
        telefono=parcel.readString();
    }

    public static final Creator<Contacto> CREATOR = new Creator<Contacto>() {
    @Override
    public Contacto createFromParcel(Parcel source) {
      return new Contacto(source);
    }

    @Override
    public Contacto[] newArray(int size) {
      return new Contacto[size];
    }
  };
    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeLong(getId());
    dest.writeString(getNombre());
    dest.writeString(getEmail());
    dest.writeString(getTelefono());
  }
}
