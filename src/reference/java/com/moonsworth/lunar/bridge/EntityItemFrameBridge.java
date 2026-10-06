package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import java.util.OptionalInt;

public interface EntityItemFrameBridge extends MovementInputMarker {
   ItemStackBridge bridge$getItemStack();

   int bridge$getRotation();

   PacketBridge bridge$getMapPacket(Itemcounter6 itemcounter61, Bridge6_10 bridge6_102);

   default OptionalInt bridge$getFramedMapId() {
      return OptionalInt.empty();
   }

   default Vec3iBridge bridge$getHangingPosition() {
      throw new AbstractMethodErrorImpl();
   }
}
