package com.example.findme;

public class Position {
    int id;
    String numero,longitude,latitude;

    public Position(int id, String numero, String longitude, String latitude) {
        this.id = id;
        this.numero = numero;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                '}';
    }
}
