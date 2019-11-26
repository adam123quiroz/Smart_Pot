package com.example.smartpot.thread;

public class DataSetIoT {
    private double[][] data;

    public DataSetIoT(double[][] data) {
        this.data = data;
    }

    public double[][] getData() {
        return data;
    }

    public void setData(double[][] data) {
        this.data = data;
    }

    public double getDataSet(int row) {
        int size = data[row].length;
        int number = (int)(Math.random()*size);
        return data[row][number];
    }
}
