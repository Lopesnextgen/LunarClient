package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge3_21;
import com.moonsworth.lunar.bridge.MixinHelper_19;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import java.util.List;

public class UnsupportedWorldPacketFactory extends AbstractRewindPacketBuilder implements com.moonsworth.lunar.bridge.NoOpWorldPacketFactory {
   public UnsupportedWorldPacketFactory(List<MixinHelper_19> var1) {
      super(null, var1);
   }

   public Bridge3_21 method1(Itemcounter6 var1) {
      return null;
   }
}
