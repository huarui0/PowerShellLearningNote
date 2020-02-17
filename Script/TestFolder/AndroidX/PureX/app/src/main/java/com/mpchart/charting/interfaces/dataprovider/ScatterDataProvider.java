package com.mpchart.charting.interfaces.dataprovider;

import com.mpchart.charting.data.ScatterData;

public interface ScatterDataProvider extends BarLineScatterCandleBubbleDataProvider {

    ScatterData getScatterData();
}
