package com.moonsworth.lunar.client.framework.feature.rewind.nameplate;

import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.client.framework.feature.rewind.fishing.Fishing4;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.Range;

public class Nameplate2_2 extends Nameplate3 {
   public Nameplate2_2(com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2 var1, List<String> var2, String var3) {
      super(var1, var2, var3, var0 -> {
         ArrayList var1x = new ArrayList();
         if (ThreadModuleDump63.method8() != null) {
            for (Bridge6_10 var3x : ThreadModuleDump63.method8().bridge$getPlayerEntities()) {
               String var4 = AdventureTextBridge.stripColor(var3x.bridge$getName()).isBlank() ? var3x.bridge$getUniqueID().toString() : var3x.bridge$getName();
               var1x.add(new Nameplate_2(Fishing4.method2("player", var3x.bridge$getUniqueID().toString()), var4, var3x.bridge$getUniqueID()));
            }
         }

         return var1x;
      });
      this.method6(Fishing2Iterator.Type.CHILD_PROPERTIES);
   }

   @Override
   public Fishing2Iterator method3(
      com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2 var1, Range<Integer> var2, Range<Integer> var3
   ) {
      Nameplate2_2 var4 = new Nameplate2_2(var1, this.method10(), this.type());
      return this.method6(var4, var1, var2, var3);
   }
}
