package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.highlight;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.feature.rewind.Rewind2_3;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight_3;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight_4;
import com.moonsworth.lunar.client.framework.feature.rewind.mixin.Rewind;
import com.moonsworth.lunar.client.framework.feature.rewind.mixin.Rewind3;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.Rewindhandlers2Impl;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.coordinates.GuiIterator;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.Map.Entry;
import org.apache.commons.lang3.mutable.MutableObject;

public class Highlight_2 extends GuiIterator {
   public Highlight_2(List<GuiIterator> var1) {
      super(var1);
   }

   @Override
   public void method1(RewindHandlers var1) {
      Rewind2_3 var2 = var1.method40();
      Highlight_4 var3 = var2.method43().method5();
      Rewind var4 = var2.method43().method6();
      Highlight_3 var5 = var2.method37();
      JsonArray var6 = new JsonArray();
      Highlight var7 = var2.method44();
      var7.method1();

      for (Entry var9 : var3.method7()) {
         JsonObject var10 = new JsonObject();
         var10.addProperty("id", ((UUID)var9.getKey()).toString());
         var10.addProperty("name", (String)var9.getValue());
         var10.addProperty("type", "folder");
         UUID var11 = var3.method9((UUID)var9.getKey());
         if (var11 != null) {
            var10.addProperty("parent", var11.toString());
         }

         var6.add(var10);
      }

      for (Entry var19 : var3.entrySet()) {
         if (!var7.method5().contains(((File)var19.getValue()).getName())) {
            JsonObject var21 = new JsonObject();
            var21.addProperty("id", ((UUID)var19.getKey()).toString());
            File var23 = (File)var19.getValue();
            var21.addProperty("name", var3.method11((UUID)var19.getKey(), var23));
            UUID var12 = var3.method9((UUID)var19.getKey());
            if (var12 != null) {
               var21.addProperty("parent", var12.toString());
            }

            MutableObject var13 = new MutableObject("media");
            if (var23.isDirectory()) {
               var13.setValue("folder");
            }

            try {
               if (var4.method5((UUID)var19.getKey())) {
                  String var14 = var2.method41().method3((UUID)var19.getKey(), 0);
                  if (var14 != null) {
                     var21.addProperty("thumbnail", var14);
                  }
               }
            } catch (IOException var16) {
               throw new RuntimeException(var16);
            }

            JsonObject var27 = new JsonObject();
            var27.addProperty("max", 10000);
            if (var5 != null && var23.isFile()) {
               var3.method17(var23, () -> {
                  try {
                     Rewind3 var6x = var4.method4((UUID)var19.getKey());
                     if (var6x != null) {
                        var13.setValue("gameplay");
                        var27.addProperty("max", var6x.method13().method1());
                        String var7x = var2.method41().method3((UUID)var19.getKey(), 0);
                        if (var7x != null) {
                           var21.addProperty("thumbnail", var7x);
                           var27.addProperty("thumbnail", var7x);
                        }
                     }
                  } catch (Exception var8) {
                  }
               }, () -> {
                  try {
                     Rewindhandlers2Impl var5x = new Rewindhandlers2Impl(var2.method32(), "media://" + var19.getKey(), var2.method42(), var3, var2.method44());
                     if (var5x.isValid()) {
                        var27.addProperty("max", var5x.getDuration());
                     }

                     var13.setValue("audio");
                  } catch (Exception var6x) {
                     var6x.printStackTrace();
                  }
               }, () -> {
                  var13.setValue("image");
                  var21.addProperty("thumbnail", Highlight_4.method15(var23));
                  var27.addProperty("thumbnail", Highlight_4.method15(var23));
               });
               var27.addProperty("id", ((UUID)var19.getKey()).toString());
               var27.addProperty("type", (String)var13.getValue());
               var27.addProperty("min", 0);
               var21.addProperty("type", ((String)var13.getValue()).equals("gameplay") ? "media" : (String)var13.getValue());
               var21.add("layer", var27);
            }

            var6.add(var21);
         }
      }

      this.method3("media", var6);
      JsonArray var18 = new JsonArray();
      if (var7.method2()) {
         for (Highlight2 var22 : var7.method4()) {
            UUID var24 = var3.method2(new File(var2.method32(), var22.name()));
            JsonObject var25 = new JsonObject();
            var25.addProperty("id", var24.toString());
            var25.addProperty("name", var22.name());

            Rewindhandlers2Impl var26;
            try {
               var26 = new Rewindhandlers2Impl(var2.method32(), "media://" + var24, var2.method42(), var3, var2.method44());
            } catch (IOException var15) {
               throw new RuntimeException(var15);
            }

            JsonObject var28 = new JsonObject();
            var28.addProperty("id", var24.toString());
            var28.addProperty("type", "audio");
            var28.addProperty("min", 0);
            var28.addProperty("max", var26.isValid() ? var26.getDuration() : 10000L);
            var25.addProperty("type", "audio");
            var25.addProperty("thumbnail", var22.method2());
            var25.add("layer", var28);
            var18.add(var25);
         }
      }

      this.method3("provided", var18);
   }
}
