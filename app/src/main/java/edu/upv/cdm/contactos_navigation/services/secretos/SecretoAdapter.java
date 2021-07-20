package edu.upv.cdm.contactos_navigation.services.secretos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.upv.cdm.contactos_navigation.R;
import edu.upv.cdm.contactos_navigation.models.SecretoModel;

/**
 * Definición del adaptador para los objetos de tipo Contacto al RecyclerView de contactos.
 */
public class SecretoAdapter extends RecyclerView.Adapter<SecretoAdapter.SecretoViewHolder> {
  private SecretoListener secretoListener;
  private List<SecretoModel> secretos = new ArrayList<>();

  public SecretoAdapter(SecretoListener secretoListener) {
    this.secretoListener = secretoListener;}

  public SecretoListener getSecretoListener() {
    return secretoListener;
  }

  public List<SecretoModel> getSecretos() {
    return secretos;
  }

  public final void setSecretos(List<SecretoModel> secretos) {
    // Guardamos la referencia a la antigua lista de contactos.
    List<SecretoModel> oldSecretos = this.secretos;

    // Establemos la nueva lista de contactos, validando que aunque sea null, tenga valor.
    this.secretos = secretos != null ? secretos : new ArrayList<SecretoModel>();

    // Si anteriormente no habia contactos, se notifica que todos los legistros son nuevos.
    if (oldSecretos.isEmpty()) {
      notifyItemRangeChanged(0, this.secretos.size());
      return;  // Exit function.
    }

    // Calculamos la diferencia entre la antigua lista de contactos y la nueva.
    DiffUtil.Callback clbk = new DiffCalback(oldSecretos, this.secretos);
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
  public SecretoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater
      .from(parent.getContext())
      .inflate(R.layout.rv_item_secreto, parent, false);
    SecretoViewHolder holder = new SecretoViewHolder(view, getSecretoListener());
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
  public void onBindViewHolder(@NonNull SecretoViewHolder holder, int position) {
    SecretoModel secreto = getSecretos().get(position);
    holder.setSecreto(secreto);
  }

  @Override
  public int getItemCount() {
    return getSecretos().size();
  }

  /**
   * Representa un objeto que va a contener el View actual del item del RecyclerView y que tiene
   * la lógica de desplegar el model Contacto y la interacción con este.
   */
  public static class SecretoViewHolder extends RecyclerView.ViewHolder {

    public SecretoViewHolder(@NonNull View itemView, SecretoListener secretoListener) {
      super(itemView);
      this.secretoListener = secretoListener;
      llContenedorPrincipal = itemView.findViewById(R.id.item_secreto_ll_contenedor_principal);
      //llContenedorPrincipal.setOnClickListener(this::item_click);
      tvNombre = itemView.findViewById(R.id.item_secreto_tv_nombre);
      tvDescripcion = itemView.findViewById(R.id.item_secreto_tv_descripcion);
      tvSecreto = itemView.findViewById(R.id.item_secreto_tv_secreto);
    }
    private SecretoModel secreto;
    private SecretoListener secretoListener;
    // Elementos del View.
    private LinearLayout llContenedorPrincipal;
    private TextView tvNombre;
    private TextView tvDescripcion;
    private TextView tvSecreto;

    public void setSecreto(SecretoModel secreto) {
      this.secreto = secreto;
      tvNombre.setText(secreto.getNombre());
      tvDescripcion.setText(secreto.getDescripcion());
      tvSecreto.setText(secreto.getSecreto());
    }

    public SecretoModel getSecreto() {
      return secreto;
    }

    private void item_click(View v) {
      secretoListener.onSecretoSeleccionado(secreto,v);
    }
  }
}
