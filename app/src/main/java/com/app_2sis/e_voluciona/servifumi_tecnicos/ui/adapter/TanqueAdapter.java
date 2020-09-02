package com.app_2sis.e_voluciona.servifumi_tecnicos.ui.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.app_2sis.e_voluciona.servifumi_tecnicos.R;
import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Constant;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.adapter.TanqueBeanAdapter;
import com.rey.material.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;

public class TanqueAdapter extends RecyclerView.Adapter<TanqueAdapter.TanqueHolder> {
    private int position;
    private ArrayList<TanqueBeanAdapter> tanqueBeanAdapterArrayList;
    private Context context;
    private int comportamientoAdapter;

    public TanqueAdapter(Context context) {
        this.context = context;
        tanqueBeanAdapterArrayList = new ArrayList<>();
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
    public TanqueHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(
                R.layout.item_list_tanques_cantidad, parent, false);
        return new TanqueHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final TanqueHolder holder, int position) {
        final TanqueBeanAdapter currentTanque = tanqueBeanAdapterArrayList.get(position);
        holder.setChecked(currentTanque.isCheck());
        holder.setDescripcion(currentTanque.getText());
        holder.setCantidad(currentTanque.getCantidad());
        holder.setTanqueID(currentTanque.getTanqueID());
        holder.setIsRecyclable(false);  //Para que no se limpien las selecciones

        holder.chkIsCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                currentTanque.setCheck(isChecked);
                if (isChecked)
                    holder.setCantidad("1");
                else
                    holder.setCantidad("");
            }
        });

        holder.etCantidad.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                currentTanque.setCantidad(arg0.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {
            }
        });

        if (comportamientoAdapter == Constant.COMPORTAMIENTO_ACTIVITY_VIEW) {
            holder.chkIsCheck.setEnabled(false);
            holder.etCantidad.setEnabled(false);
        }
    }

    @Override
    public int getItemCount() {
        return tanqueBeanAdapterArrayList != null ? tanqueBeanAdapterArrayList.size() : 0;
    }

    public void addAll(@NonNull List<TanqueBeanAdapter> tanqueList) {
        this.tanqueBeanAdapterArrayList.addAll(tanqueList);
        notifyDataSetChanged();
    }

    public void deleteAll() {
        tanqueBeanAdapterArrayList.clear();
    }

    /**
     * Clase Tanque Holder
     */
    public class TanqueHolder extends RecyclerView.ViewHolder {
        private String tanqueID;
        private CheckBox chkIsCheck;
        private TextView tvDescripcion;
        private EditText etCantidad;

        public void setTanqueID(String tanqueID) {
            this.tanqueID = tanqueID;
        }

        public void setDescripcion(String descripcion) {
            this.tvDescripcion.setText(descripcion);
        }

        public void setCantidad(String cantidad) {
            this.etCantidad.setText(cantidad);
        }

        public void setChecked(boolean isCheck) {
            this.chkIsCheck.setCheckedImmediately(isCheck);
        }


        public TanqueHolder(View itemView) {
            super(itemView);
            chkIsCheck = itemView.findViewById(R.id.chk_list_tanque);
            tvDescripcion = itemView.findViewById(R.id.tv_list_tanque_descripcion);
            etCantidad = itemView.findViewById(R.id.et_list_tanque_cantidad);
        }
    }
}
