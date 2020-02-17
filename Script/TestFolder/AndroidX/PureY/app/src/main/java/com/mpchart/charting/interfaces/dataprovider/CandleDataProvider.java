package com.mpchart.charting.interfaces.dataprovider;

import com.mpchart.charting.data.CandleData;

public interface CandleDataProvider extends BarLineScatterCandleBubbleDataProvider {

    CandleData getCandleData();
}
