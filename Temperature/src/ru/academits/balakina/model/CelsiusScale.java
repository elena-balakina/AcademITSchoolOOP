package ru.academits.balakina.model;

public class CelsiusScale implements TemperatureScale{
    @Override
    public double convertToCelsius(double inputValue) {
        return inputValue;
    }

    @Override
    public double convertFromCelsius(double inputValue) {
        return inputValue;
    }

    @Override
    public String toString(){
        return "град. Цельсия (С)";
    }
}
