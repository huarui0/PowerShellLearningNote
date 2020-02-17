package com.mpchart.charting.interfaces.dataprovider;

import com.mpchart.charting.components.YAxis.AxisDependency;
import com.mpchart.charting.data.BarLineScatterCandleBubbleData;
import com.mpchart.charting.utils.Transformer;

public interface BarLineScatterCandleBubbleDataProvider extends ChartInterface {

    Transformer getTransformer(AxisDependency axis);
    boolean isInverted(AxisDependency axis);
    
    float getLowestVisibleX();
    float getHighestVisibleX();

    BarLineScatterCandleBubbleData getData();
}
