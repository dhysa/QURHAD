package com.android.qurhad.quranAyat;

/**
 * Created by Ayyu Andhysa on 8/26/2016.
 */
public class QuranAyat_Item {
    private String id_surat;
    private String aya;
    private String text_quran;
    private String text_indo;
    private String nama; //kolom nama pada tabel nama_surat

    public String getId_surat() {
        return id_surat;
    }

    public void setId_surat(String id_surat) {
        this.id_surat = id_surat;
    }

    public String getAya() {
        return aya;
    }

    public void setAya(String aya) {
        this.aya = aya;
    }

    public String getText_quran() {
        return text_quran;
    }

    public void setText_quran(String text_quran) {
        this.text_quran = text_quran;
    }

    public String getText_indo() {
        return text_indo;
    }

    public void setText_indo(String text_indo) {
        this.text_indo = text_indo;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

}
