package com.app_2sis.e_voluciona.servifumi_tecnicos.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app_2sis.e_voluciona.servifumi_tecnicos.R;
import com.app_2sis.e_voluciona.servifumi_tecnicos.activity.MainActivity;
import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Constant;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.Programacion;

import java.util.ArrayList;
import java.util.List;

public class ProgramacionCabeceraAdapter extends RecyclerView.Adapter<ProgramacionCabeceraAdapter.ProgramacionCabeceraHolder>{
    private int position;
    private boolean isRealizado;
    private ArrayList<Programacion> programacionArrayList;
    private Context context;

    public ProgramacionCabeceraAdapter(Context context) {
        this.context = context;
        programacionArrayList = new ArrayList<>();
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isRealizado() {
        return isRealizado;
    }

    public void setRealizado(boolean realizado) {
        isRealizado = realizado;
    }

    @Override
    public ProgramacionCabeceraHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(
                R.layout.item_list_programacion_cabecera, parent, false);
        return new ProgramacionCabeceraHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProgramacionCabeceraHolder holder, int position) {
        final Programacion currentProgramacion = programacionArrayList.get(position);
        holder.setTitulo(currentProgramacion.getTitulo());
        holder.setCuando(currentProgramacion.getCuando());
        holder.setLugar(currentProgramacion.getLugar());
        holder.setColorRealizado(currentProgramacion.getRealizado());
        holder.setProgramacionID(currentProgramacion.getId()+"");
    }

    @Override
    public int getItemCount() {
        return programacionArrayList != null ? programacionArrayList.size() : 0;
    }

    public void addAll(@NonNull List<Programacion> programacionList){
        this.programacionArrayList.addAll(programacionList);
        notifyDataSetChanged();
    }

    public void deleteAll(){
        programacionArrayList.clear();
    }

    /**
     * Clase ProgramacionCabecera Holder
     */
    public class ProgramacionCabeceraHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        private TextView titulo, cuando, lugar;
        private CardView cvProgramacion;
        private String programacionID;
        
        public void setTitulo(String titulo){
            this.titulo.setText(titulo);
        }
        
        public void setCuando(String cuando){
            this.cuando.setText(cuando);
        }
        
        public void setLugar(String lugar){
            this.lugar.setText(lugar);
        }

        public void setProgramacionID(String programacionID) {
            this.programacionID = programacionID;
        }

        public ProgramacionCabeceraHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            titulo = itemView.findViewById(R.id.tv_programacion_titulo);
            cuando = itemView.findViewById(R.id.tv_programacion_cuando);
            lugar = itemView.findViewById(R.id.tv_programacion_lugar);
            cvProgramacion = itemView.findViewById(R.id.cv_programacion);
        }

        private void setColorYaRealizado(){
            this.cvProgramacion.setCardBackgroundColor(context.getResources().getColor(R.color.colorSuccess));
        }

        private void setColorNoRealizado(){
            this.cvProgramacion.setCardBackgroundColor(context.getResources().getColor(R.color.colorGrisOscuro));
        }

        public void setColorRealizado(String realizado){
            if (realizado.equals(Constant.SI)){
                setColorYaRealizado();
            } else {
                setColorNoRealizado();
            }
        }

        @Override
        public void onClick(View v) {
            Intent i = new Intent(v.getContext(), MainActivity.class); // TODO: 06/09/2018 cambiar por el detalle
            i.putExtra("programacionID_bd", programacionID);
            v.getContext().startActivity(i);
        }
    }
}
