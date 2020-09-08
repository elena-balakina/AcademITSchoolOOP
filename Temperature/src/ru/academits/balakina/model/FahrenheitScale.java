package ru.academits.balakina.model;

public class FahrenheitScale implements TemperatureScale {
    @Override
    public double convertToCelsius(double inputValue) {
        return Math.round(((inputValue - 32) / 1.8) * 100.0) / 100.0;
    }

    @Override
    public double convertFromCelsius(double inputValue) {
        return Math.round((inputValue * 1.8 + 32) * 100.0) / 100.0;
    }

    @Override
    public String toString(){
        return "град. Фаренгейта (F)";
    }
}
