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
import com.app_2sis.e_voluciona.servifumi_tecnicos.activity.ConstanciaPlataFormularioActivity;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.ConstanciaPlataActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Constant;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.adapter.ConstanciaPlataBeanAdapter;

import java.util.ArrayList;
import java.util.List;

public class ConstanciaPlataAdapter extends RecyclerView.Adapter<ConstanciaPlataAdapter.ConstanciaPlataHolder> {
    private int position;
    private boolean isSincronizado;
    private ArrayList<ConstanciaPlataBeanAdapter> constanciaPlataArrayList;
    private Context context;

    public ConstanciaPlataAdapter(Context context) {
        this.context = context;
        constanciaPlataArrayList = new ArrayList<>();
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
    public ConstanciaPlataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(
                R.layout.item_list_constancia_plata, parent, false);
        return new ConstanciaPlataHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ConstanciaPlataHolder holder, int position) {
        final ConstanciaPlataBeanAdapter currentConstancia = constanciaPlataArrayList.get(position);
        holder.setTitulo(currentConstancia.getCliente());
        holder.setDineroRecibido(currentConstancia.getDineroRecibido());
        holder.setTanques(currentConstancia.getTanques());
        holder.setColorSincronizado(currentConstancia.getSincronizado());
        holder.setConstanciaPlataID(currentConstancia.getConstanciaPlataID());

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
        return constanciaPlataArrayList != null ? constanciaPlataArrayList.size() : 0;
    }

    public void addAll(@NonNull List<ConstanciaPlataBeanAdapter> constanciaPlataList) {
        this.constanciaPlataArrayList.addAll(constanciaPlataList);
        notifyDataSetChanged();
    }

    public void deleteAll() {
        constanciaPlataArrayList.clear();
    }

    /**
     * Clase ConstanciaPlata Holder
     */
    public class ConstanciaPlataHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnCreateContextMenuListener {
        private String constanciaPlataID;
        private CardView cvConstanciaPlata;
        private TextView titulo, dineroRecibido, tanques;

        //<editor-fold desc="Setters">


        public void setConstanciaPlataID(String constanciaPlataID) {
            this.constanciaPlataID = constanciaPlataID;
        }

        public void setTitulo(String titulo) {
            this.titulo.setText(titulo);
        }

        public void setDineroRecibido(String dineroRecibido) {
            this.dineroRecibido.setText("Dinero Recibido: $" + (dineroRecibido.isEmpty() ? "0" : dineroRecibido));
        }

        public void setTanques(String tanques) {
            this.tanques.setText(tanques);
        }

        private void setColorYaSincronizado() {
            this.cvConstanciaPlata.setCardBackgroundColor(context.getResources().getColor(R.color.colorFondoEnviado));
            setTextViewColorSincronizado(true);
        }

        private void setColorNoSincronizado() {
            this.cvConstanciaPlata.setCardBackgroundColor(context.getResources().getColor(R.color.colorFondoNoEnviado));
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
            tanques.setTextColor(color);
        }

        private void setColorSincronizado(String sincronizado) {
            if (sincronizado.equals(Constant.SI)) {
                setColorYaSincronizado();
            } else {
                setColorNoSincronizado();
            }
        }

        //</editor-fold>

        public ConstanciaPlataHolder(View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.tv_constancia_plata_titulo);
            dineroRecibido = itemView.findViewById(R.id.tv_constancia_plata_dinero_recibido);
            tanques = itemView.findViewById(R.id.tv_constancia_plata_tanques);
            cvConstanciaPlata = itemView.findViewById(R.id.cv_constancia_plata);
            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent i = new Intent(v.getContext(), ConstanciaPlataFormularioActivity.class);
            i.putExtra("constanciaPlataID_bd", constanciaPlataID);
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
            ConstanciaPlataBeanAdapter curentConstanciaPlata = constanciaPlataArrayList.get(position);
            constanciaPlataArrayList.remove(position);
            new ConstanciaPlataActiveRecord(context).deleteConstanciaPlataFull(curentConstanciaPlata.getConstanciaPlataID());
            notifyDataSetChanged();
        }
    }
}
