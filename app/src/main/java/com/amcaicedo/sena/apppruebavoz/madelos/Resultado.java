package com.amcaicedo.sena.apppruebavoz.madelos;


public class Resultado {
    private Float frecMax;
    private Float frecMin;
    private Float frecFund;

    private Float dbMax;
    private Float dbMin;
    private Float dbProm;

    private String fecha;
    private String img;

    public Resultado() {
    }

    public Resultado(Float frecMax, Float frecMin, Float frecFund, Float dbMax, Float dbMin, Float dbProm) {
        this.frecMax = frecMax;
        this.frecMin = frecMin;
        this.frecFund = frecFund;
        this.dbMax = dbMax;
        this.dbMin = dbMin;
        this.dbProm = dbProm;
    }

    public Resultado(Float frecMax, Float frecMin, Float frecFund, Float dbMax, Float dbMin, Float dbProm, String fecha, String img) {
        this.frecMax = frecMax;
        this.frecMin = frecMin;
        this.frecFund = frecFund;
        this.dbMax = dbMax;
        this.dbMin = dbMin;
        this.dbProm = dbProm;
        this.fecha = fecha;
        this.img = img;
    }

    //region Getter and Setter
    public Float getFrecMax() {
        return frecMax;
    }

    public void setFrecMax(Float frecMax) {
        this.frecMax = frecMax;
    }

    public Float getFrecMin() {
        return frecMin;
    }

    public void setFrecMin(Float frecMin) {
        this.frecMin = frecMin;
    }

    public Float getFrecFund() {
        return frecFund;
    }

    public void setFrecFund(Float frecFund) {
        this.frecFund = frecFund;
    }

    public Float getDbMax() {
        return dbMax;
    }

    public void setDbMax(Float dbMax) {
        this.dbMax = dbMax;
    }

    public Float getDbMin() {
        return dbMin;
    }

    public void setDbMin(Float dbMin) {
        this.dbMin = dbMin;
    }

    public Float getDbProm() {
        return dbProm;
    }

    public void setDbProm(Float dbProm) {
        this.dbProm = dbProm;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    //endregion
}
