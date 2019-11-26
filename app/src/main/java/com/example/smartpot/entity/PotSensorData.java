package com.example.smartpot.entity;

public class PotSensorData {
    private double ambientTemperature;
    private double light;
    private double earthHumidity;
    private double ambientHumidity;

    public static double[][] data = {
            {10.54, 11.76, 12.953, 13.543, 14.858, 15.758, 16.955, 17.994},
            {1, 0},
            {20.44, 21.949, 22.939, 25.0949, 26.9393, 29.67, 31.383, 32.393, 35.983, 38.0054},
            {18.234, 20.241, 23.4874, 26.123, 28.248, 30.832, 33.913, 34.1239, 36.0210, 39.1235},
    };

    public PotSensorData(double ambientTemperature, double light, double earthHumidity, double ambientHumidity) {
        this.ambientTemperature = ambientTemperature;
        this.light = light;
        this.earthHumidity = earthHumidity;
        this.ambientHumidity = ambientHumidity;
    }

    public PotSensorData() {
    }

    public double getAmbientTemperature() {
        return ambientTemperature;
    }

    public void setAmbientTemperature(double ambientTemperature) {
        this.ambientTemperature = ambientTemperature;
    }

    public double getLight() {
        return light;
    }

    public void setLight(double light) {
        this.light = light;
    }

    public double getEarthHumidity() {
        return earthHumidity;
    }

    public void setEarthHumidity(double earthHumidity) {
        this.earthHumidity = earthHumidity;
    }

    public double getAmbientHumidity() {
        return ambientHumidity;
    }

    public void setAmbientHumidity(double ambientHumidity) {
        this.ambientHumidity = ambientHumidity;
    }

    @Override
    public String toString() {
        return "PotSensorData{" +
                "ambientTemperature=" + ambientTemperature +
                ", light=" + light +
                ", earthHumidity=" + earthHumidity +
                ", ambientHumidity=" + ambientHumidity +
                '}';
    }
}
