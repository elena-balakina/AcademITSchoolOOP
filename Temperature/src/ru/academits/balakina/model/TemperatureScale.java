package ru.academits.balakina.model;

public interface TemperatureScale {
    double convertToCelsius(double inputValue);

    double convertFromCelsius(double inputValue);
}
