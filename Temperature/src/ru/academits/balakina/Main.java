package ru.academits.balakina;

import ru.academits.balakina.model.CelsiusScale;
import ru.academits.balakina.model.FahrenheitScale;
import ru.academits.balakina.model.KelvinScale;
import ru.academits.balakina.model.TemperatureScale;
import ru.academits.balakina.view.TemperatureConverterView;

public class Main {
    public static void main(String[] args) {
        TemperatureScale[] scales = new TemperatureScale[]{new CelsiusScale(), new KelvinScale(), new FahrenheitScale()};
        TemperatureConverterView view = new TemperatureConverterView(scales);
        view.startView();
    }
}
