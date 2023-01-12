package es.ulpgc.aemet;

import com.google.gson.JsonElement;

public class Filter {

    public JsonElement filter(JsonElement item){
        item.getAsJsonObject().remove("lon");
        item.getAsJsonObject().remove("lat");
        item.getAsJsonObject().remove("prec");
        item.getAsJsonObject().remove("alt");
        item.getAsJsonObject().remove("vmax");
        item.getAsJsonObject().remove("vv");
        item.getAsJsonObject().remove("dv");
        item.getAsJsonObject().remove("dmax");
        item.getAsJsonObject().remove("hr");
        item.getAsJsonObject().remove("rviento");
        item.getAsJsonObject().remove("pres");
        item.getAsJsonObject().remove("stdvv");
        item.getAsJsonObject().remove("ts");
        item.getAsJsonObject().remove("pres_nmar");
        item.getAsJsonObject().remove("tpr");
        item.getAsJsonObject().remove("stddv");
        item.getAsJsonObject().remove("inso");
        item.getAsJsonObject().remove("vis");
        item.getAsJsonObject().remove("tss5cm");
        item.getAsJsonObject().remove("psoltp");
        item.getAsJsonObject().remove("pliqt");
        item.getAsJsonObject().remove("pacutp");
        item.getAsJsonObject().remove("psolt");
        item.getAsJsonObject().remove("vmaxu");
        item.getAsJsonObject().remove("vvu");
        item.getAsJsonObject().remove("dvu");
        item.getAsJsonObject().remove("dmaxu");
        item.getAsJsonObject().remove("stdvvu");
        item.getAsJsonObject().remove("stddvu");
        item.getAsJsonObject().remove("inso");
        item.getAsJsonObject().remove("tss20cm");
        item.getAsJsonObject().remove("tss5cm");
        item.getAsJsonObject().remove("geo700");
        item.getAsJsonObject().remove("geo850");
        item.getAsJsonObject().remove("geo925");
        item.getAsJsonObject().remove("rviento");
        item.getAsJsonObject().remove("nieve");
        return item;
    }
}
