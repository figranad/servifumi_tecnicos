package com.app_2sis.e_voluciona.servifumi_tecnicos.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.app_2sis.e_voluciona.servifumi_tecnicos.R;
import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Constant;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.adapter.PlagaBeanAdapter;
import com.rey.material.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;

public class PlagaAdapter extends RecyclerView.Adapter<PlagaAdapter.PlagaHolder> {
    private int position;
    private ArrayList<PlagaBeanAdapter> plagaBeanAdapterArrayList;
    private Context context;
    private int comportamientoAdapter;

    public PlagaAdapter(Context context) {
        this.context = context;
        plagaBeanAdapterArrayList = new ArrayList<>();
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setComportamientoAdapter(int comportamiento) {
        this.comportamientoAdapter = comportamiento;
    }

    @NonNull
    @Override
    public PlagaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(
                R.layout.item_list_plagas, parent, false);
        return new PlagaHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final PlagaHolder holder, int position) {
        final PlagaBeanAdapter currentPlaga = plagaBeanAdapterArrayList.get(position);
        holder.setChecked(currentPlaga.isCheck());
        holder.setDescripcion(currentPlaga.getText());
        holder.setPlagaID(currentPlaga.getPlagaID());
        holder.setIsRecyclable(false);  //Para que no se limpien las selecciones

        holder.chkIsCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                currentPlaga.setCheck(isChecked);
            }
        });

        if (comportamientoAdapter == Constant.COMPORTAMIENTO_ACTIVITY_VIEW) {
            holder.chkIsCheck.setEnabled(false);
        }
    }

    @Override
    public int getItemCount() {
        return plagaBeanAdapterArrayList != null ? plagaBeanAdapterArrayList.size() : 0;
    }

    public void addAll(@NonNull List<PlagaBeanAdapter> plagaList) {
        this.plagaBeanAdapterArrayList.addAll(plagaList);
        notifyDataSetChanged();
    }

    public void deleteAll() {
        plagaBeanAdapterArrayList.clear();
    }

    /**
     * Clase Plaga Holder
     */
    public class PlagaHolder extends RecyclerView.ViewHolder {
        private String plagaID;
        private CheckBox chkIsCheck;
        private TextView tvDescripcion;

        public void setPlagaID(String plagaID) {
            this.plagaID = plagaID;
        }

        public void setDescripcion(String descripcion) {
            this.tvDescripcion.setText(descripcion);
        }

        public void setChecked(boolean isCheck) {
            this.chkIsCheck.setCheckedImmediately(isCheck);
        }


        public PlagaHolder(View itemView) {
            super(itemView);
            chkIsCheck = itemView.findViewById(R.id.chk_list_plaga);
            tvDescripcion = itemView.findViewById(R.id.tv_list_plaga_descripcion);
        }
    }
}