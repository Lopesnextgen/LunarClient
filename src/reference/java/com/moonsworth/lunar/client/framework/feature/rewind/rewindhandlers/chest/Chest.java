package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.chest;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.rewind.Fishing2Loader;
import com.moonsworth.lunar.client.framework.feature.rewind.RewindIterator;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui_2;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight_3;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Fishing2Iterator;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Fishing2Iterator2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.coordinates.Coordinates;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.coordinates.GuiIterator;
import com.moonsworth.lunar.client.config.option.OptionDataProvider;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.apache.commons.lang3.Range;

public class Chest extends GuiIterator {
   public Chest(List<GuiIterator> var1) {
      super(var1);
   }

   private JsonArray method1(Collection<UUID> var1) {
      JsonArray var2 = new JsonArray();

      for (UUID var4 : var1) {
         var2.add(var4.toString());
      }

      return var2;
   }

   @Override
   public void method1(RewindHandlers var1) {
      Highlight_3 var2 = var1.method40().method37();
      this.addPropertyJson("selectedLayer", Coordinates.getSelectedLayer() == null ? "" : Coordinates.getSelectedLayer().getId().toString());
      this.addPropertyJson("additionalSelectedLayers", this.refresh(Coordinates.getAdditionalSelectedLayers().stream().map(RewindIterator::getId).toList()));
      if (var2 != null) {
         HashSet var3 = new HashSet<>(Coordinates.isLinkSelectionEnabled() ? var2.method11().method6().method4(Coordinates.getSelectedLayer()) : Collections.emptySet());

         for (RewindIterator var5 : Coordinates.getAdditionalSelectedLayers()) {
            var3.addAll(var2.method11().method6().method4(var5));
         }

         this.addPropertyJson("links", this.refresh(var3));
         JsonObject var20 = new JsonObject();
         JsonArray var21 = new JsonArray();

         for (Gui_2 var7 : var2.method11()) {
            JsonObject var8 = new JsonObject();
            JsonArray var9 = new JsonArray();
            HashMap var10 = new HashMap();
            HashMap var11 = new HashMap();

            for (Range var13 : var7.method5().method11().keySet()) {
               RewindIterator var14 = (RewindIterator)var7.method5().method11().get(var13);
               JsonObject var15 = (JsonObject)var14.method16(var2, var13, var1, () -> this.refresh(var1));
               JsonArray var16 = new JsonArray();

               for (Fishing2Iterator var18 : var14.method18().values()) {
                  addPropertyJson(var18, var2, var10, var11, var16, "");
               }

               var15.add("keyframes", var16);
               var9.add(var15);
            }

            JsonArray var22 = new JsonArray();

            for (JsonObject var24 : var10.values()) {
               Set var25 = (Set)var11.get(var24.get("id").getAsString());
               if (var25 != null) {
                  JsonArray var26 = new JsonArray();

                  for (String var28 : var25) {
                     JsonObject var19 = new JsonObject();
                     var19.addProperty("name", var28);
                     var26.add(var19);
                  }

                  var24.add("keyframes", var26);
               }

               var22.add(var24);
            }

            var8.addProperty("id", String.valueOf(var7.getId()));
            var8.addProperty("type", var7.getType());
            var8.add("layers", var9);
            var8.add("properties", var22);
            var8.addProperty("showKeyframes", var7.method6());
            var8.addProperty("enabled", var7.isEnabled());
            var21.add(var8);
         }

         var20.addProperty("id", var2.getId().toString());
         var20.addProperty("name", var2.getName());
         var20.add("tracks", var21);
         var20.addProperty("framerate", var2.method13().getFps());
         var20.addProperty("frameTime", var2.method9());
         var20.addProperty("viewportWidth", var2.method13().getWidth());
         var20.addProperty("viewportHeight", var2.method13().getHeight());
         var20.addProperty("durationFrame", var2.method3() + 3600);
         var20.addProperty("actualDurationFrame", var2.method3());
         this.addPropertyJson("timeline", var20);
      } else {
         this.addPropertyJson("timeline", null);
      }
   }

   private static void addPropertyJson(
      Fishing2Iterator var0, Highlight_3 var1, Map<String, JsonObject> var2, Map<String, Set<String>> var3, JsonArray var4, String var5
   ) {
      String var6 = var5.isEmpty() ? var0.type() : var5 + "/" + var0.type();
      String var7 = var0.method9();
      if (var0 instanceof Fishing2Iterator2 var8) {
         ModDetails var9 = (ModDetails)var8.getFeature().method1(Framework.field13);
         if (var9 != null) {
            var7 = var9.getName();
         }
      }

      JsonArray var16 = new JsonArray();

      for (Fishing2Loader var10 : var0.method12().values()) {
         OptionDataProvider var11 = (OptionDataProvider)var10.getOption().method1(OptionTraits.field10);
         if (var11 != null
            && var10.method28()
            && !var10.method27().isEmpty()
            && (var10.method27().size() != 1 || !var10.method27().containsKey(Integer.MIN_VALUE))) {
            for (Integer var13 : var10.method27().keySet()) {
               if (var11.provide() instanceof JsonObject var14) {
                  var14.addProperty("frame", var13);
                  var14.addProperty("milliseconds", var13.intValue() * var1.method9());
                  var14.addProperty("selected", Coordinates.isKeyframeSelected(var10, var13));
                  var16.add(var14);
               }
            }

            JsonObject var21 = var2.getOrDefault(var6, new JsonObject());
            var21.addProperty("id", var6);
            var21.addProperty("name", var7);
            if (var11.provide() instanceof JsonObject var23) {
               var3.computeIfAbsent(var6, var0x -> new HashSet<>()).add(var23.get("name").getAsString());
            }

            var2.put(var6, var21);
         }
      }

      JsonObject var18 = new JsonObject();
      var18.addProperty("propertyId", var6);
      var18.addProperty("propertyName", var7);
      var18.add("keyframes", var16);
      var4.add(var18);
      String var19 = var5.isEmpty() ? var0.type() : var5 + "/" + var0.type();

      for (Fishing2Iterator var22 : var0.method11().values()) {
         addPropertyJson(var22, var1, var2, var3, var4, var19);
      }
   }
}
