package com.philos.kamusmedis;

/**
 * Created by PHILOS on 5/28/2017.
 */

public class KamusObject {
    private String kata;
    private String definisi;

    public KamusObject(String kata, String definisi) {
        this.kata = kata;
        this.definisi = definisi;
    }

    public String getKata() {
        return kata;
    }

    public void setKata(String kata) {
        this.kata = kata;
    }

    public String getDefinisi() {
        return definisi;
    }

    public void setDefinisi(String definisi) {
        this.definisi = definisi;
    }
}
