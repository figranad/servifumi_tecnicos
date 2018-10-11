package com.app_2sis.e_voluciona.servifumi_tecnicos.ui.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app_2sis.e_voluciona.servifumi_tecnicos.R;
import com.app_2sis.e_voluciona.servifumi_tecnicos.activity.ConstanciaFumiFormularioActivity;
import com.app_2sis.e_voluciona.servifumi_tecnicos.activity.MainActivity;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.ConstanciaFumiActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Constant;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.adapter.ConstanciaFumiBeanAdapter;

import java.util.ArrayList;
import java.util.List;

public class ConstanciaFumiAdapter extends RecyclerView.Adapter<ConstanciaFumiAdapter.ConstanciaFumiHolder> {
    private int position;
    private boolean isSincronizado;
    private ArrayList<ConstanciaFumiBeanAdapter> constanciaFumiArrayList;
    private Context context;

    public ConstanciaFumiAdapter(Context context) {
        this.context = context;
        constanciaFumiArrayList = new ArrayList<>();
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isSincronizado() {
        return isSincronizado;
    }

    public void setSincronizado(boolean sincronizado) {
        isSincronizado = sincronizado;
    }

    @NonNull
    @Override
    public ConstanciaFumiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(
                R.layout.item_list_constancia_fumi, parent, false);
        return new ConstanciaFumiHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ConstanciaFumiHolder holder, int position) {
        final ConstanciaFumiBeanAdapter currentConstancia = constanciaFumiArrayList.get(position);
        holder.setTitulo(currentConstancia.getCliente());
        holder.setDineroRecibido(currentConstancia.getDineroRecibido());
        holder.setObservaciones(currentConstancia.getObservaciones());
        holder.setColorSincronizado(currentConstancia.getSincronizado());
        holder.setConstanciaFumiID(currentConstancia.getConstanciaFumiID());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setPosition(holder.getAdapterPosition());
                setSincronizado(currentConstancia.getSincronizado().equals(Constant.SI));
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return constanciaFumiArrayList != null ? constanciaFumiArrayList.size() : 0;
    }

    public void addAll(@NonNull List<ConstanciaFumiBeanAdapter> constanciaFumiList) {
        this.constanciaFumiArrayList.addAll(constanciaFumiList);
        notifyDataSetChanged();
    }

    public void deleteAll() {
        constanciaFumiArrayList.clear();
    }

    /**
     * Clase ConstanciaFumi Holder
     */
    public class ConstanciaFumiHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnCreateContextMenuListener {
        private String constanciaFumiID;
        private CardView cvConstanciaFumi;
        private TextView titulo, dineroRecibido, observaciones;

        //<editor-fold desc="Setters">


        public void setConstanciaFumiID(String constanciaFumiID) {
            this.constanciaFumiID = constanciaFumiID;
        }

        public void setTitulo(String titulo) {
            this.titulo.setText(titulo);
        }

        public void setDineroRecibido(String dineroRecibido) {
            this.dineroRecibido.setText("Dinero Recibido: $" + (dineroRecibido.isEmpty() ? "0" : dineroRecibido));
        }

        public void setObservaciones(String observaciones) {
            this.observaciones.setText(observaciones);
        }

        private void setColorYaSincronizado() {
            this.cvConstanciaFumi.setCardBackgroundColor(context.getResources().getColor(R.color.colorFondoEnviado));
            setTextViewColorSincronizado(true);
        }

        private void setColorNoSincronizado() {
            this.cvConstanciaFumi.setCardBackgroundColor(context.getResources().getColor(R.color.colorFondoNoEnviado));
            setTextViewColorSincronizado(false);
        }

        private void setTextViewColorSincronizado(boolean sincronizado) {
            int color;
            if (sincronizado) {
                color = context.getResources().getColor(R.color.colorTextoEnviado);
            } else {
                color = context.getResources().getColor(R.color.colorTextoNoEnviado);
            }
            titulo.setTextColor(color);
            dineroRecibido.setTextColor(color);
            observaciones.setTextColor(color);
        }

        private void setColorSincronizado(String sincronizado) {
            if (sincronizado.equals(Constant.SI)) {
                setColorYaSincronizado();
            } else {
                setColorNoSincronizado();
            }
        }

        //</editor-fold>

        public ConstanciaFumiHolder(View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.tv_constancia_fumi_titulo);
            dineroRecibido = itemView.findViewById(R.id.tv_constancia_fumi_dinero_recibido);
            observaciones = itemView.findViewById(R.id.tv_constancia_fumi_observaciones);
            cvConstanciaFumi = itemView.findViewById(R.id.cv_constancia_fumi);
            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent i = new Intent(v.getContext(), ConstanciaFumiFormularioActivity.class);
            i.putExtra("constanciaFumiID_bd", constanciaFumiID);
            v.getContext().startActivity(i);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            menu.setHeaderTitle("Selecciona la Accion");
            MenuItem lanzar = menu.add(Menu.NONE, 1, 1, "Eliminar");

            lanzar.setOnMenuItemClickListener(onEditMenu);
        }

        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case 1: //ELIMINAR
                        new AlertDialog.Builder(context)
                                .setIcon(R.mipmap.ic_advertencia)
                                .setTitle("¿Eliminar Registro?")
                                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        eliminarYRefresh(position);
                                    }
                                })
                                .setNegativeButton("No", null)
                                .show();
                        break;
                }
                return false;
            }
        };

        private void eliminarYRefresh(int position) {
            ConstanciaFumiBeanAdapter curentConstanciaFumi = constanciaFumiArrayList.get(position);
            constanciaFumiArrayList.remove(position);
            new ConstanciaFumiActiveRecord(context).deleteConstanciaFumiFull(curentConstanciaFumi.getConstanciaFumiID());
            notifyDataSetChanged();
        }
    }
}
