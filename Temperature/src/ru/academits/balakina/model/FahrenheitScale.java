package ru.academits.balakina.model;

public class FahrenheitScale implements TemperatureScale {
    @Override
    public double convertToCelsius(double temperature) {
        return (temperature - 32) / 1.8;
    }

    @Override
    public double convertFromCelsius(double temperature) {
        return (temperature * 1.8 + 32);
    }

    @Override
    public String toString() {
        return "град. Фаренгейта (F)";
    }
}
