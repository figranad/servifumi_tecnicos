package com.app_2sis.e_voluciona.servifumi_tecnicos.ui.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app_2sis.e_voluciona.servifumi_tecnicos.R;
import com.app_2sis.e_voluciona.servifumi_tecnicos.activity.ConstanciaFumiFormularioActivity;

import java.util.ArrayList;
import java.util.List;

public class VehiculoAdapter extends RecyclerView.Adapter<VehiculoAdapter.VehiculoHolder> {
    //En base a la posicion de la lista con descripcion voy a gestionar la lista de ids en el activity
    //si elimmino de uno debo hacerlo en la otra para preservar la integridad
    private int position;
    private ArrayList<String> vehiculosArrayList;
    private ConstanciaFumiFormularioActivity activity;
    private Context context;
    private boolean hideDelete;

    public VehiculoAdapter(ConstanciaFumiFormularioActivity activity) {
        this.activity = activity;
        context = activity.getApplicationContext();
        vehiculosArrayList = new ArrayList<>();
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
    public VehiculoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_list_text_icon, parent, false);
        return new VehiculoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final VehiculoHolder holder, final int position) {
        String vehiculo = vehiculosArrayList.get(position);
        holder.setDescripcion(vehiculo);
        holder.descripcion.setTag(position);
    }

    @Override
    public int getItemCount() {
        return vehiculosArrayList != null ? vehiculosArrayList.size() : 0;
    }

    public void addAll(@NonNull List<String> vehiculoList) {
        this.vehiculosArrayList.addAll(vehiculoList);
        notifyDataSetChanged();
    }

    public void add(String vehiculo) {
        this.vehiculosArrayList.add(vehiculo);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        this.vehiculosArrayList.remove(position);
        notifyDataSetChanged();
    }

    public void deleteAll() {
        vehiculosArrayList.clear();
    }

    /**
     * Clase Cliente Holder
     */
    public class VehiculoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView descripcion;
        private ImageView ivEliminar;

        public void setDescripcion(String descripcion) {
            this.descripcion.setText(descripcion);
        }

        public VehiculoHolder(View itemView) {
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
                activity.loadInfoVehiculosDesdeAdapter((Integer) descripcion.getTag());
            }
        }

        private void deleteItem() {
            if (activity != null) {
                activity.deleteInfoVehiculosDesdeAdapter((Integer) descripcion.getTag());
            }
        }
    }
}
