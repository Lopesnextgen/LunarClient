package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import java.util.OptionalInt;

public interface ItemFrameEntityBridge extends BridgeExtension {
   ItemStackBridge bridge$getItemStack();

   int bridge$getRotation();

   Bridge3_21 bridge$getMapPacket(Itemcounter6 var1, Bridge6_10 var2);

   default OptionalInt bridge$getFramedMapId() {
      return OptionalInt.empty();
   }

   default Vector3iBridge bridge$getHangingPosition() {
      throw new AbstractMethodErrorImpl();
   }
}
