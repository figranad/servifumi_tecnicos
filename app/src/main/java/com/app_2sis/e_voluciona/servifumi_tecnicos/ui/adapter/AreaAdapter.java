package com.app_2sis.e_voluciona.servifumi_tecnicos.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app_2sis.e_voluciona.servifumi_tecnicos.R;
import com.app_2sis.e_voluciona.servifumi_tecnicos.activity.ConstanciaFumiFormularioActivity;

import java.util.ArrayList;
import java.util.List;

public class AreaAdapter extends RecyclerView.Adapter<AreaAdapter.AreaHolder> {
    //En base a la posicion de la lista con descripcion voy a gestionar la lista de ids en el activity
    //si elimmino de uno debo hacerlo en la otra para preservar la integridad
    private int position;
    private ArrayList<String> areasArrayList;
    private ConstanciaFumiFormularioActivity activity;
    private Context context;
    private boolean hideDelete;

    public AreaAdapter(ConstanciaFumiFormularioActivity activity) {
        this.activity = activity;
        context = activity.getApplicationContext();
        areasArrayList = new ArrayList<>();
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean hideDelete() {
        return hideDelete;
    }

    public void hideDelete(boolean hideDelete) {
        this.hideDelete = hideDelete;
    }

    @Override
    public AreaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_list_text_icon, parent, false);
        return new AreaHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final AreaHolder holder, final int position) {
        String area = areasArrayList.get(position);
        holder.setDescripcion(area);
        holder.descripcion.setTag(position);
        holder.setIsRecyclable(false);  //Para que no se limpien las selecciones
    }

    @Override
    public int getItemCount() {
        return areasArrayList != null ? areasArrayList.size() : 0;
    }

    public void addAll(@NonNull List<String> areaList) {
        this.areasArrayList.addAll(areaList);
        notifyDataSetChanged();
    }

    public void add(String area) {
        this.areasArrayList.add(area);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        this.areasArrayList.remove(position);
        notifyDataSetChanged();
    }

    public void deleteAll() {
        areasArrayList.clear();
    }

    /**
     * Clase Cliente Holder
     */
    public class AreaHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView descripcion;
        private ImageView ivEliminar;

        public void setDescripcion(String descripcion) {
            this.descripcion.setText(descripcion);
        }

        public AreaHolder(View itemView) {
            super(itemView);
            descripcion = itemView.findViewById(R.id.tv_list_descripcion);
            ivEliminar = itemView.findViewById(R.id.iv_list_delete);
            descripcion.setOnClickListener(this);
            ivEliminar.setOnClickListener(this);
            if (hideDelete)
                ivEliminar.setVisibility(View.GONE);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.tv_list_descripcion:
                    showInUI();
                    break;
                case R.id.iv_list_delete:
                    deleteItem();
                    break;
            }
        }

        private void showInUI() {
            if (activity != null) {
                activity.loadInfoAreasDesdeAdapter((Integer) descripcion.getTag());
            }
        }

        private void deleteItem() {
            if (activity != null) {
                activity.deleteInfoAreasDesdeAdapter((Integer) descripcion.getTag());
            }
        }
    }
}
