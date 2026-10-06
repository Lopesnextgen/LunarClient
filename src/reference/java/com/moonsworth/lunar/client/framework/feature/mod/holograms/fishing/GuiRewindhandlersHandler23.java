package com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.lotusfish.Lotusfish3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.lotusfish.mixin.Lotusfish;
import com.moonsworth.lunar.client.event.mixin.gui.TabListUpdateEvent;
import java.util.HashMap;
import java.util.Map;
import lombok.Generated;

public class GuiRewindhandlersHandler23 extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private final com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler24 field7 = (com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler24)this.method3(
      com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler24.class
   );
   private final Map<String, Lotusfish3> field8 = new HashMap<>();

   public GuiRewindhandlersHandler23() {
      this.method11(TabListUpdateEvent.class, this::method1, 190);
   }

   private void method1(TabListUpdateEvent var1) {
      this.field8.clear();
      ImmutableList var2 = this.field7.method6();
      int var3 = 0;

      while (var3 < var2.size()) {
         String var4 = (String)var2.get(var3);
         String var5 = var4.contains(":") ? var4.substring(0, var4.indexOf(58)) : var4;
         Lotusfish var6 = com.moonsworth.lunar.client.framework.feature.mod.fishing.lotusfish.Lotusfish.method2(var5);
         if (var6 != null) {
            Lotusfish3 var7 = Lotusfish3.method1(this.field7, var3);
            this.field8.put(var6.id(), var7);
            var3 += var7.method3().size();
         } else {
            var3++;
         }
      }
   }

   protected void onEnable() {
      this.method1(null);
   }

   @Generated
   public com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler24 method5() {
      return this.field7;
   }

   @Generated
   public Map<String, Lotusfish3> method6() {
      return this.field8;
   }
}
