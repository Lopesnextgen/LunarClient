package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.feature.rewind.mixin.Rewind3;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.colorsaturation.Nameplate2Impl;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class Rewindhandlers_2 extends com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.coordinates.GuiIterator {
   private UUID segmentId = null;
   private JsonArray inputTicks = new JsonArray();

   public Rewindhandlers_2(List<com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.coordinates.GuiIterator> var1) {
      super(var1);
   }

   @Override
   public void refresh(RewindHandlers var1) {
      boolean var2 = ThreadModuleDump63.method4().method90().method41();
      this.pushTickWindow("enabled", var2);
      this.pushTickWindow("tick", var2 ? ((Nameplate4)var1.method42().get()).getTick() : 0);

      try {
         this.buildInputTicks(var1, var2);
      } catch (IOException var4) {
         throw new RuntimeException(var4);
      }

      this.pushInputMetadata(var1, var2);
   }

   private void buildInputTicks(RewindHandlers var1, boolean var2) {
      if (!var2) {
         this.pushTickWindow("inputTicks", new JsonArray());
      } else {
         Rewind3 var3 = var1.method40().method35();
         if (var3 == null) {
            this.pushTickWindow("inputTicks", new JsonArray());
         } else if (this.segmentId == var3.method13().getId()) {
            this.pushTickWindow(var1);
         } else {
            this.segmentId = var3.method13().getId();
            JsonArray var4 = new JsonArray();
            JsonObject var5 = new JsonObject();
            JsonArray var6 = new JsonArray();
            var5.addProperty("tick", 0);
            var5.add("packets", var6);

            Rewind3 var7;
            Nameplate2 var8;
            for (var7 = new Rewind3(var3.getFile()); (var8 = var7.method2(false, true)) != null; var7.consume()) {
               if (var8 instanceof Nameplate2Impl var9) {
                  if (var5.get("tick").getAsInt() == 0 && var6.isEmpty()) {
                     var5.addProperty("tick", var9.getTick());
                  } else {
                     var4.add(var5);
                     var5 = new JsonObject();
                     var6 = new JsonArray();
                     var5.addProperty("tick", var9.getTick());
                     var5.add("packets", var6);
                  }
               }

               JsonObject var12 = new JsonObject();

               try {
                  var12.addProperty("type", var8.name());
                  var12.addProperty("data", var8.data());
                  var6.add(var12);
               } catch (Exception var11) {
               }
            }

            if (!var6.isEmpty()) {
               var4.add(var5);
            }

            var7.close();
            this.inputTicks = var4;
            this.pushTickWindow(var1);
         }
      }
   }

   private void pushTickWindow(RewindHandlers var1) {
      JsonArray var2 = new JsonArray();
      int var3 = ((Nameplate4)var1.method42().get()).getTick();
      int var4 = 0;

      for (int var5 = 0; var5 < this.inputTicks.size(); var5++) {
         JsonObject var6 = this.inputTicks.get(var5).getAsJsonObject();
         if (var6.get("tick").getAsInt() == var3) {
            var4 = var5;
            break;
         }
      }

      for (int var7 = Math.max(0, var4 - 20); var7 < Math.min(this.inputTicks.size(), var4 + 20); var7++) {
         var2.add(this.inputTicks.get(var7));
      }

      this.pushTickWindow("inputTicks", var2);
   }

   private void pushInputMetadata(RewindHandlers var1, boolean var2) {
      if (!var2) {
         this.pushTickWindow("inputMetadata", new JsonArray());
      } else {
         Rewind3 var3 = var1.method40().method35();
         if (var3 == null) {
            this.pushTickWindow("inputMetadata", new JsonArray());
         } else {
            this.pushTickWindow("inputMetadata", var1.method40().method29().toJson(var3.method13()));
         }
      }
   }
}
