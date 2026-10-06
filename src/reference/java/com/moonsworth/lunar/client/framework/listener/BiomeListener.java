package com.moonsworth.lunar.client.framework.listener;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.world.Biome;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;
import org.apache.commons.lang3.text.WordUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.framework.listener.DynamicListener;

public class BiomeListener extends DynamicListener {
   @NotNull
   private BiomeListener.BiomeDisplay field7 = new BiomeListener.BiomeDisplay(Biome.PLAINS, null);

   public BiomeListener() {
      this.handle(EventTick.class, arg1 -> {
         Bridge5Extension_5 bridge5extension_52 = Ref.method7();
         if (bridge5extension_52 != null) {
            Biome itemcountertype_23 = Biome.fromBiomeBridgeOrNull(bridge5extension_52.method6());
            if (itemcountertype_23 == null) {
               String[] items4 = bridge5extension_52.method6().bridge$getBiomeName().split(":", 2);
               String text5 = items4.length == 2 ? "biome." + items4[0] + "." + items4[1] : "biome.minecraft." + items4[0];
               String text6 = Bridge.method8().method71(text5);
               if (text6.contains(":")) {
                  this.field7 = new BiomeListener.BiomeDisplay(null, WordUtils.capitalizeFully(text6.substring(text6.indexOf(58) + 1).replace('_', ' ')));
               } else {
                  this.field7 = new BiomeListener.BiomeDisplay(null, text6);
               }
            } else {
               this.field7 = new BiomeListener.BiomeDisplay(itemcountertype_23, null);
            }
         }
      });
   }

   @NotNull
   @Generated
   public BiomeListener.BiomeDisplay method5() {
      return this.field7;
   }

   public class BiomeDisplay {
      @Nullable
      private final Biome field1;
      private final String field2;

      public BiomeDisplay(@Nullable Biome itemcountertype_21, String text2) {
         this.field1 = itemcountertype_21;
         this.field2 = text2;
      }

      public String name() {
         return this.field1 != null ? this.field1.getDisplayName() : this.field2;
      }

      public int method1() {
         return this.field1 != null ? this.field1.getColor() : -1;
      }

      @Nullable
      public Biome method2() {
         return this.field1;
      }
   }
}
