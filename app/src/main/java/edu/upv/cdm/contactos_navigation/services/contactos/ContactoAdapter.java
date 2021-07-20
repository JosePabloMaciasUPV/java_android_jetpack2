package edu.upv.cdm.contactos_navigation.services.contactos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import edu.upv.cdm.contactos_navigation.R;
import edu.upv.cdm.contactos_navigation.models.Contacto;

/**
 * Definición del adaptador para los objetos de tipo Contacto al RecyclerView de contactos.
 */
public class ContactoAdapter extends RecyclerView.Adapter<ContactoAdapter.ContactoViewHolder> {
  private ContactosListener contactosListener;
  private List<Contacto> contactos = new ArrayList<>();

  /**
   * Inicializa una nueva instancia de ContactoAdapter, mandandole un objeto ContactosListener
   * que definirá la interacción con items del RecyclerView.
   *
   * @param contactosListener Implementación de ContactosListener que contiene la interacción
   *                          con los elementos
   */
  public ContactoAdapter(ContactosListener contactosListener) {
    this.contactosListener = contactosListener;

  }

  public ContactosListener getContactosListener() {
    return contactosListener;
  }

  public List<Contacto> getContactos() {
    return contactos;
  }

  public final void setContactos(List<Contacto> contactos) {

    // Guardamos la referencia a la antigua lista de contactos.
    List<Contacto> oldContactos = this.contactos;

    // Establemos la nueva lista de contactos, validando que aunque sea null, tenga valor.
    this.contactos = contactos != null ? contactos : new ArrayList<Contacto>();

    // Si anteriormente no habia contactos, se notifica que todos los legistros son nuevos.
    if (oldContactos.isEmpty()) {
      notifyItemRangeChanged(0, this.contactos.size());
      return;  // Exit function.
    }

    // Calculamos la diferencia entre la antigua lista de contactos y la nueva.
    DiffUtil.Callback clbk = new DiffCalback(oldContactos, this.contactos);
    DiffUtil.calculateDiff(clbk).dispatchUpdatesTo(this);

  }

  /**
   * Realiza la creación de un objeto ViewHolder correspondiente a algún item del RecyclerView.
   *
   * @param parent   El objeto ViewGroup que representa el parent.
   * @param viewType El viewType del layout del item.
   * @return Objeto ViewHolder creado.
   */
  @NonNull
  @Override
  public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater
      .from(parent.getContext())
      .inflate(R.layout.rv_item_contacto, parent, false);
    ContactoViewHolder holder = new ContactoViewHolder(view, getContactosListener());
    return holder;
  }

  /**
   * Contiene la asignación de un objeto ViewHolder con el objeto model de contacto. Esto porque
   * no se crea un ViewHolder para cada elemento Contacto, sino que se crean los ViewHolders
   * respecto a los elementos que se van mostrando y conforme se desplaza en el RecyclerView,
   * estos objetos ViewHolder son "reciclados" para mostrar otros elementos.
   *
   * @param holder
   * @param position
   */
  @Override
  public void onBindViewHolder(@NonNull ContactoViewHolder holder, int position) {
    Contacto contacto = getContactos().get(position);
    holder.setContacto(contacto);
  }

  @Override
  public int getItemCount() {
    return getContactos().size();
  }

  /**
   * Representa un objeto que va a contener el View actual del item del RecyclerView y que tiene
   * la lógica de desplegar el model Contacto y la interacción con este.
   */
  public static class ContactoViewHolder extends RecyclerView.ViewHolder {

    public ContactoViewHolder(@NonNull View itemView, ContactosListener contactosListener) {
      super(itemView);
      this.contactosListener = contactosListener;
      llContenedorPrincipal = itemView.findViewById(R.id.item_contacto_ll_contenedor_principal);
      llContenedorPrincipal.setOnClickListener(this::item_click);
      tvNombre = itemView.findViewById(R.id.item_contacto_tv_nombre);
      tvTelefono = itemView.findViewById(R.id.item_contacto_tv_telefono);
      tvEmail = itemView.findViewById(R.id.item_contacto_tv_email);
    }

    private Contacto contacto;
    private ContactosListener contactosListener;

    // Elementos del View.
    private LinearLayout llContenedorPrincipal;
    private TextView tvNombre;
    private TextView tvTelefono;
    private TextView tvEmail;

    public void setContacto(Contacto contacto) {
      this.contacto = contacto;
      tvNombre.setText(contacto.getNombre());
      tvTelefono.setText(contacto.getTelefono());
      tvEmail.setText(contacto.getEmail());
    }

    public Contacto getContacto() {
      return contacto;
    }

    private void item_click(View v) {
      contactosListener.onContactoSeleccionado(contacto,v);
    }


  }
}
