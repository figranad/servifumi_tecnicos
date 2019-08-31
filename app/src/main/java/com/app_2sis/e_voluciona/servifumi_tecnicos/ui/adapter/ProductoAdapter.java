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
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.adapter.ProductoBeanAdapter;
import com.rey.material.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoHolder> {
    private int position;
    private ArrayList<ProductoBeanAdapter> productoBeanAdapterArrayList;
    private Context context;
    private int comportamientoAdapter;

    public ProductoAdapter(Context context) {
        this.context = context;
        productoBeanAdapterArrayList = new ArrayList<>();
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
    public ProductoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(
                R.layout.item_list_producto_cantidad, parent, false);
        return new ProductoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductoHolder holder, int position) {
        final ProductoBeanAdapter currentProducto = productoBeanAdapterArrayList.get(position);
        holder.setChecked(currentProducto.isCheck());
        holder.setDescripcion(currentProducto.getText());
        holder.setCantidad(currentProducto.getCantidad());
        holder.setProductoID(currentProducto.getProductoID());
        holder.setIsRecyclable(false);  //Para que no se limpien las selecciones

        holder.chkIsCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                currentProducto.setCheck(isChecked);
                if (isChecked)
                    holder.setCantidad("1");
                else
                    holder.setCantidad("");
            }
        });

        holder.etCantidad.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                currentProducto.setCantidad(arg0.toString());
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
        return productoBeanAdapterArrayList != null ? productoBeanAdapterArrayList.size() : 0;
    }

    public void addAll(@NonNull List<ProductoBeanAdapter> productoList) {
        this.productoBeanAdapterArrayList.addAll(productoList);
        notifyDataSetChanged();
    }

    public void deleteAll() {
        productoBeanAdapterArrayList.clear();
    }

    /**
     * Clase Producto Holder
     */
    public class ProductoHolder extends RecyclerView.ViewHolder {
        private String productoID;
        private CheckBox chkIsCheck;
        private TextView tvDescripcion;
        private EditText etCantidad;

        public void setProductoID(String productoID) {
            this.productoID = productoID;
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


        public ProductoHolder(View itemView) {
            super(itemView);
            chkIsCheck = itemView.findViewById(R.id.chk_list_producto);
            tvDescripcion = itemView.findViewById(R.id.tv_list_producto_descripcion);
            etCantidad = itemView.findViewById(R.id.et_list_producto_cantidad);
        }
    }
}