package edu.upv.cdm.contactos_navigation.services.secretos;

import androidx.recyclerview.widget.DiffUtil;

import java.util.ArrayList;
import java.util.List;

import edu.upv.cdm.contactos_navigation.models.Contacto;
import edu.upv.cdm.contactos_navigation.models.SecretoModel;

public class DiffCalback extends DiffUtil.Callback {
  private List<SecretoModel> oldContactos;
  private List<SecretoModel> newContactos;

  /**
   * Clase que contiene la definición de la lógica para calcular la diferecia del listado de
   * contactos.
   */
public DiffCalback(List<SecretoModel> oldContactos, List<SecretoModel> newContactos) {
  this.oldContactos = oldContactos != null ? oldContactos : new ArrayList<SecretoModel>();
  this.newContactos = newContactos != null ? newContactos : new ArrayList<SecretoModel>();
  }

@Override
public int getOldListSize() { return oldContactos.size(); }

@Override
public int getNewListSize() { return newContactos.size(); }

@Override
public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
  return oldContactos.get(oldItemPosition).getSecreto() == newContactos.get(newItemPosition).getSecreto();
  }

@Override
public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
  SecretoModel o = oldContactos.get(oldItemPosition);
  SecretoModel n = newContactos.get(newItemPosition);
  boolean mismoNombre = o.getNombre().equals(n.getNombre());
  boolean mismoTelefono = o.getDescripcion().equals(n.getDescripcion());
  boolean mismoEmail = o.getSecreto().equals(n.getSecreto());
  return mismoNombre && mismoTelefono && mismoEmail;
  }

  }
