package com.mpchart.charting.interfaces.dataprovider;

import com.mpchart.charting.data.BubbleData;

public interface BubbleDataProvider extends BarLineScatterCandleBubbleDataProvider {

    BubbleData getBubbleData();
}
