package ru.academits.balakina.model;

public class KelvinScale implements TemperatureScale{
    @Override
    public double convertToCelsius(double inputValue) {
        return Math.round((inputValue - 273.15) * 100.0) / 100.0;
    }

    @Override
    public double convertFromCelsius(double inputValue) {
        return Math.round((inputValue + 273.15) * 100.0) / 100.0;
    }

    @Override
    public String toString(){
        return "град. Кельвина (К)";
    }
}
