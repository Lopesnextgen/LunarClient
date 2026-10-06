package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.tileentity.BeamSegmentBridge;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.ichor.VersionGate;
import java.awt.Color;
import java.util.List;

public interface TileEntityBeaconRendererBridge {
   void bridge$renderBeacon(Itemcounter6 itemcounter61, double value2, double value4, double value6, double value8, double value10, Color color12, double value13);

   @VersionGate(min = 1)
   void bridge$renderBeacon(Itemcounter6 itemcounter61, double value2, double value4, double value6, double value8, double value10, List<BeamSegmentBridge> list12, double value13);
}
