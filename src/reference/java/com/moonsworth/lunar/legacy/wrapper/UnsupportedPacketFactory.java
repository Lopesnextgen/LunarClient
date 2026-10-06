package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge3_21;
import com.moonsworth.lunar.bridge.MixinHelper2_7;
import com.moonsworth.lunar.bridge.MixinHelper_19;
import java.util.List;

public class UnsupportedPacketFactory extends AbstractRewindPacketBuilder implements MixinHelper2_7 {
   public UnsupportedPacketFactory(List<MixinHelper_19> list) {
      super(null, list);
   }

   public Bridge3_21 method2() {
      return null;
   }
}
