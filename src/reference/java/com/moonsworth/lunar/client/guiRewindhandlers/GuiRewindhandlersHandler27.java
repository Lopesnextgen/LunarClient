package com.moonsworth.lunar.client.guiRewindhandlers;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.itemcounter.ItemcounterType_2;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;
import org.apache.commons.lang3.text.WordUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.framework.listener.DynamicListener;

public class GuiRewindhandlersHandler27 extends DynamicListener {
   @NotNull
   private GuiRewindhandlersHandler27.Data3 field7 = new GuiRewindhandlersHandler27.Data3(ItemcounterType_2.PLAINS, null);

   public GuiRewindhandlersHandler27() {
      this.handle(EventClientTick.class, var1 -> {
         Bridge5Extension_5 var2 = ThreadModuleDump63.method7();
         if (var2 != null) {
            ItemcounterType_2 var3 = ItemcounterType_2.fromBiomeBridgeOrNull(var2.method6());
            if (var3 == null) {
               String[] var4 = var2.method6().bridge$getBiomeName().split(":", 2);
               String var5 = var4.length == 2 ? "biome." + var4[0] + "." + var4[1] : "biome.minecraft." + var4[0];
               String var6 = Bridge.method8().method71(var5);
               if (var6.contains(":")) {
                  this.field7 = new GuiRewindhandlersHandler27.Data3(null, WordUtils.capitalizeFully(var6.substring(var6.indexOf(58) + 1).replace('_', ' ')));
               } else {
                  this.field7 = new GuiRewindhandlersHandler27.Data3(null, var6);
               }
            } else {
               this.field7 = new GuiRewindhandlersHandler27.Data3(var3, null);
            }
         }
      });
   }

   @NotNull
   @Generated
   public GuiRewindhandlersHandler27.Data3 method5() {
      return this.field7;
   }

   public class Data3 {
      @Nullable
      private final ItemcounterType_2 field1;
      private final String field2;

      public Data3(@Nullable ItemcounterType_2 var1, String var2) {
         this.field1 = var1;
         this.field2 = var2;
      }

      public String name() {
         return this.field1 != null ? this.field1.getDisplayName() : this.field2;
      }

      public int method1() {
         return this.field1 != null ? this.field1.getColor() : -1;
      }

      @Nullable
      public ItemcounterType_2 method2() {
         return this.field1;
      }
   }
}
