package com.mpchart.charting.interfaces.dataprovider;

import com.mpchart.charting.components.YAxis;
import com.mpchart.charting.data.LineData;

public interface LineDataProvider extends BarLineScatterCandleBubbleDataProvider {

    LineData getLineData();

    YAxis getAxis(YAxis.AxisDependency dependency);
}
