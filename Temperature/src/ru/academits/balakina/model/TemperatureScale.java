package ru.academits.balakina.model;

public interface TemperatureScale {
    double convertToCelsius(double temperature);

    double convertFromCelsius(double temperature);
}

