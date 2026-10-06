package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge3_21;
import com.moonsworth.lunar.bridge.MixinHelper_19;
import java.util.List;

public class UnsupportedPacketListFactory extends AbstractRewindPacketBuilder implements com.moonsworth.lunar.bridge.MixinHelper19 {
   public UnsupportedPacketListFactory(List<MixinHelper_19> list) {
      super(null, list);
   }

   public List<Bridge3_21> method2() {
      return List.of();
   }
}
