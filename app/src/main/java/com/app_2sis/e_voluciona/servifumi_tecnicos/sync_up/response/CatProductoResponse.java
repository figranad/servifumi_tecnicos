package com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.response;

import com.app_2sis.e_voluciona.servifumi_tecnicos.model.CatProducto;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CatProductoResponse {
    @SerializedName("productos")
    CatProductoResponse.ProductoResult productoResult;

    public ArrayList<CatProducto> getProductos(){
        return productoResult.productos;
    }

    private class ProductoResult {
        @SerializedName("producto")
        ArrayList<CatProducto> productos;
    }
}
