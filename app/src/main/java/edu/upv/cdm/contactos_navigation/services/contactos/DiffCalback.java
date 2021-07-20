package edu.upv.cdm.contactos_navigation.services.contactos;

import androidx.recyclerview.widget.DiffUtil;

import java.util.ArrayList;
import java.util.List;

import edu.upv.cdm.contactos_navigation.models.Contacto;

public class DiffCalback extends DiffUtil.Callback {


  /**
   * Clase que contiene la definición de la lógica para calcular la diferecia del listado de
   * contactos.
   */
public DiffCalback(List<Contacto> oldContactos, List<Contacto> newContactos) {
  this.oldContactos = oldContactos != null ? oldContactos : new ArrayList<Contacto>();
  this.newContactos = newContactos != null ? newContactos : new ArrayList<Contacto>();
  }

private List<Contacto> oldContactos;
private List<Contacto> newContactos;

@Override
public int getOldListSize() { return oldContactos.size(); }

@Override
public int getNewListSize() { return newContactos.size(); }

@Override
public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
  return oldContactos.get(oldItemPosition).getId() == newContactos.get(newItemPosition).getId();
  }

@Override
public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
  Contacto o = oldContactos.get(oldItemPosition);
  Contacto n = newContactos.get(newItemPosition);
  boolean mismoNombre = o.getNombre().equals(n.getNombre());
  boolean mismoTelefono = o.getTelefono().equals(n.getTelefono());
  boolean mismoEmail = o.getEmail().equals(n.getEmail());
  return mismoNombre && mismoTelefono && mismoEmail;
  }

  }
