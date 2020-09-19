package ru.academits.balakina.model;

public class CelsiusScale implements TemperatureScale {
    @Override
    public double convertToCelsius(double temperature) {
        return temperature;
    }

    @Override
    public double convertFromCelsius(double temperature) {
        return temperature;
    }

    @Override
    public String toString() {
        return "град. Цельсия (С)";
    }
}
