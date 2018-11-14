package com.app_2sis.e_voluciona.servifumi_tecnicos.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.app_2sis.e_voluciona.servifumi_tecnicos.R;
import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Constant;
import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Utileria;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.adapter.AccesorioBeanAdapter;

import java.util.ArrayList;
import java.util.List;

public class AccesorioAdapter extends RecyclerView.Adapter<AccesorioAdapter.AccesorioHolder> {
    private int position;
    private ArrayList<AccesorioBeanAdapter> accesorioBeanAdapterArrayList;
    private Context context;
    private int comportamientoAdapter;

    public AccesorioAdapter(Context context) {
        this.context = context;
        accesorioBeanAdapterArrayList = new ArrayList<>();
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
    public AccesorioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(
                R.layout.item_list_accesorio_constancia, parent, false);
        return new AccesorioHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final AccesorioHolder holder, int position) {
        final AccesorioBeanAdapter currentAccesorio = accesorioBeanAdapterArrayList.get(position);
        holder.setDescripcion(currentAccesorio.getText());
        holder.setCantidad(currentAccesorio.getCantidad());
        holder.setCondicion(Utileria.getAccesorioCondicionNombre(currentAccesorio.getCondicionID()));
        holder.setAccesorioID(currentAccesorio.getAccesorioID());
        holder.setIsRecyclable(false);  //Para que no se limpien las selecciones

        holder.etCantidad.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                currentAccesorio.setCantidad(arg0.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {
            }
        });

        holder.spCondicion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                currentAccesorio.setCondicionID(Utileria.getAccesorioCondicionID(holder.spCondicion.getSelectedItem().toString()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        if (comportamientoAdapter == Constant.COMPORTAMIENTO_ACTIVITY_VIEW) {
            holder.etCantidad.setEnabled(false);
            holder.spCondicion.setEnabled(false);
        }
    }

    @Override
    public int getItemCount() {
        return accesorioBeanAdapterArrayList != null ? accesorioBeanAdapterArrayList.size() : 0;
    }

    public void addAll(@NonNull List<AccesorioBeanAdapter> accesorioList) {
        this.accesorioBeanAdapterArrayList.addAll(accesorioList);
        notifyDataSetChanged();
    }

    public void deleteAll() {
        accesorioBeanAdapterArrayList.clear();
    }

    /**
     * Clase Accesorio Holder
     */
    public class AccesorioHolder extends RecyclerView.ViewHolder {
        private String accesorioID;
        private TextView tvDescripcion;
        private Spinner spCondicion;
        private EditText etCantidad;

        public void setAccesorioID(String accesorioID) {
            this.accesorioID = accesorioID;
        }

        public void setDescripcion(String descripcion) {
            this.tvDescripcion.setText(descripcion);
        }

        public void setCondicion(String text) {
            this.spCondicion.setSelection(Utileria.getPositionSpinner(
                    Utileria.getAccesorioCondicionID(), Utileria.getAccesorioCondicionID(text)));
        }

        public void setCantidad(String cantidad) {
            this.etCantidad.setText(cantidad);
        }


        public AccesorioHolder(View itemView) {
            super(itemView);
            tvDescripcion = itemView.findViewById(R.id.tv_list_accesorio_descripcion);
            spCondicion = itemView.findViewById(R.id.sp_list_accesorio_condicion);
            etCantidad = itemView.findViewById(R.id.et_list_accesorio_cantidad);

            spCondicion.setAdapter(new ArrayAdapter<>(
                    context, R.layout.item_spinner, Utileria.getAccesorioCondicion()));
        }
    }
}
