package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.feature.rewind.Fishing2Loader;
import com.moonsworth.lunar.client.framework.feature.rewind.Rewind2_3;
import com.moonsworth.lunar.client.framework.feature.rewind.RewindImpl;
import com.moonsworth.lunar.client.framework.feature.rewind.Rewind_2;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.GuiImpl;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight_3;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight_4;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Fishing2Iterator;
import com.moonsworth.lunar.client.config.option.OptionDataProvider;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import java.io.File;
import java.util.List;
import java.util.Map.Entry;
import org.apache.commons.lang3.Range;

public class Rewindhandlers_5 extends com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.coordinates.GuiIterator {
   public Rewindhandlers_5(List<com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.coordinates.GuiIterator> var1) {
      super(var1);
   }

   @Override
   public void refresh(RewindHandlers var1) {
      Rewind2_3 var2 = var1.method40();
      Highlight_3 var3 = var2.method37();
      if (var3 != null) {
         JsonArray var4 = new JsonArray();

         for (GuiImpl var6 : var3.method11().method2()) {
            if (var6.isEnabled()) {
               Entry var7 = var6.CCOIHCHRIHICROIOOCRRRHORHIRIOO(var3.method15());
               if (var7 != null) {
                  Rewind_2 var8 = (Rewind_2)var7.getValue();
                  if (!var8.type().equals("effect")) {
                     JsonObject var9 = new JsonObject();
                     var9.addProperty("type", var8.type());
                     var9.addProperty("id", var8.getId().toString());
                     if (var8 instanceof RewindImpl var10) {
                        Highlight_4 var11 = var2.method43().method5();
                        File var12 = var11.method6(var10.method4());
                        if (var12 != null) {
                           var9.addProperty("source", Highlight_4.method15(var12));
                           if (var12.getName().toLowerCase().endsWith(".gif")) {
                              int var13 = (Integer)((Range)var7.getKey()).getMinimum();
                              int var14 = var3.method15() - var13;
                              var9.addProperty("ms", (long)(var14 * var3.method9()));
                           }
                        } else {
                           var9.addProperty("source", "");
                        }
                     }

                     for (Entry var18 : var8.method18().entrySet()) {
                        Fishing2Iterator var19 = (Fishing2Iterator)var18.getValue();
                        JsonObject var20 = new JsonObject();

                        for (Entry var15 : var19.method12().entrySet()) {
                           OptionDataProvider var16 = (OptionDataProvider)((Fishing2Loader)var15.getValue()).getOption().HIRHCCHIRHRORIICOIHIHCICOIRHHC(OptionTraits.field10);
                           if (var16 != null) {
                              var20.add((String)var15.getKey(), var16.provide());
                           }
                        }

                        var9.add((String)var18.getKey(), var20);
                     }

                     var4.add(var9);
                  }
               }
            }
         }

         this.method3("effects", var4);
      }
   }
}
