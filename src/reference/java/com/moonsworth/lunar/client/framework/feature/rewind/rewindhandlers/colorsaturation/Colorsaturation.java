package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.colorsaturation;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.rewind.Fishing2Loader;
import com.moonsworth.lunar.client.framework.feature.rewind.Rewind2_3;
import com.moonsworth.lunar.client.framework.feature.rewind.RewindIterator;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight_3;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Fishing2Iterator;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Fishing2Iterator2;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate3;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate_2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.coordinates.Coordinates;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.coordinates.Coordinates4;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.coordinates.GuiIterator;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.OptionDataProvider;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.util.ThreadModuleDump73Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import lombok.Generated;
import org.apache.commons.lang3.Range;

public class Colorsaturation extends GuiIterator {
   private final EnumOption<ThreadModuleDump73Type> interpolationOption = (EnumOption<ThreadModuleDump73Type>)OptionFactory.method10(
         "interpolation", ThreadModuleDump73Type.LINEAR
      )
      .method31();
   private final ToggleOption easeInOption = (ToggleOption)OptionFactory.method7("easeIn").method31();
   private final ToggleOption easeOutOption = (ToggleOption)OptionFactory.method7("easeOut").method31();
   private final List<ClientOption<?>> options = List.of(this.interpolationOption, this.easeInOption, this.easeOutOption);

   public Colorsaturation(List<GuiIterator> var1) {
      super(var1);
   }

   @Override
   public void refresh(RewindHandlers var1) {
      if (!Coordinates.isMultiSelectEnabled()) {
         Rewind2_3 var2 = var1.method40();
         Coordinates4 var3 = Coordinates.getSelectedKeyframe();
         RewindIterator var4 = Coordinates.getSelectedLayer();
         Nameplate2 var5 = var2.method40();
         if (var4 != null) {
            JsonArray var6 = new JsonArray();

            for (Entry var8 : var2.method38().method4().entrySet()) {
               if (((List)var8.getValue()).isEmpty() || ((List)var8.getValue()).contains(var4.type())) {
                  JsonObject var9 = new JsonObject();
                  var9.addProperty("type", (String)var8.getKey());
                  var9.addProperty("name", Client.method109().method67().method2("rewind", (String)var8.getKey(), new Object[0]));
                  var9.addProperty("hidden", ((List)var8.getValue()).isEmpty());
                  var6.add(var9);
               }
            }

            this.buildKeyframeJson("availableProperties", var6);
            this.buildKeyframeJson("showMods", var4.type().equals("gameplay") || var4.type().equals("effect"));
         } else {
            this.buildKeyframeJson("availableProperties", new JsonArray());
            this.buildKeyframeJson("showMods", false);
         }

         JsonArray var10 = new JsonArray();
         if (var3 != null) {
            var10.add(this.buildPropertyJson(var1, var3.method1(), var4, List.of(var3.method2()), null, false));
            Fishing2Loader.Data var11 = var3.method2().method27().get(var3.method3());
            if (var11 != null) {
               this.interpolationOption.method7(OptionTraits.field1);
               this.easeInOption.method7(OptionTraits.field1);
               this.easeOutOption.method7(OptionTraits.field1);
               this.interpolationOption.HIRIHCROOIRIORCCOIRRCRHOHCCRRO(var11.method2());
               this.interpolationOption.OIRHOOIICOCIOOHICRRRICORIHHIHC(var11.method2());
               this.easeInOption.method6(var11.method3());
               this.easeInOption.method10(var11.method3());
               this.easeOutOption.method6(var11.method4());
               this.easeOutOption.method10(var11.method4());
               this.interpolationOption.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var2x -> {
                  ThreadModuleDump73Type var3x = var11.method2();
                  var5.method4(() -> var11.method5(var3x), () -> var11.method5(var2x));
                  var11.method5(var2x);
               });
               this.easeInOption.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var2x -> {
                  boolean var3x = var11.method3();
                  var5.method4(() -> var11.method6(var3x), () -> var11.method6(var2x));
                  var11.method6(var2x);
               });
               this.easeOutOption.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var2x -> {
                  boolean var3x = var11.method4();
                  var5.method4(() -> var11.method7(var3x), () -> var11.method7(var2x));
                  var11.method7(var2x);
               });
               var10.add(this.buildKeyframeJson(var11.method2().isSupportsInOut() ? this.options : List.of(this.interpolationOption)));
            }

            this.buildKeyframeJson("hideKeyframeControls", true);
         } else if (var4 != null) {
            Range var12 = Coordinates.getLayerRange(var4.getId());

            for (Fishing2Iterator var14 : var4.method18().values()) {
               var10.add(this.buildPropertyJson(var1, var14, var4, var14.method12().values(), var12, false));
            }

            this.buildKeyframeJson("hideKeyframeControls", false);
         }

         this.buildKeyframeJson("properties", var10);
         this.buildKeyframeJson("canUndo", var2.method40().canUndo());
         this.buildKeyframeJson("canRedo", var2.method40().canRedo());
      }
   }

   private JsonObject buildPropertyJson(
      RewindHandlers var1, Fishing2Iterator var2, RewindIterator<?> var3, Collection<Fishing2Loader<?, ?>> var4, Range<Integer> var5, boolean var6
   ) {
      Highlight_3 var7 = ((Nameplate4)var1.method42().get()).method4();
      if (var7 == null) {
         return new JsonObject();
      }

      int var8 = var7.method15();
      int var9 = var8 - (var5 == null ? 0 : (Integer)var5.getMinimum());
      JsonObject var10 = new JsonObject();
      JsonArray var11 = new JsonArray();
      ArrayList var12 = new ArrayList();

      for (Fishing2Loader var14 : var4) {
         ClientOption var15 = var14.getOption();
         if (!var15.isHidden() && !var12.contains(var15.getId())) {
            OptionDataProvider var16 = (OptionDataProvider)var15.method1(OptionTraits.field10);
            if (var16 != null && var16.provide() instanceof JsonObject var17) {
               var17.addProperty("canReset", var14.method4());
               var17.addProperty("showInTimeline", var14.method28());
               var17.addProperty("previousKeyframe", var14.method13(var9) != null);
               var17.addProperty("hasKeyframe", var14.method9(var9));
               var17.addProperty("nextKeyframe", var14.method14(var9) != null);
               JsonArray var33 = new JsonArray();

               for (Integer var20 : var14.method27().keySet()) {
                  var33.add(var20);
                  if (Coordinates.getSelectedKeyframe() != null && Coordinates.getSelectedKeyframe().method2() == var14 && Coordinates.getSelectedKeyframe().method3() == var20) {
                     var14.method30(null);
                     Object var21 = var14.method21(var15);
                     var14.method22(var15, ((Fishing2Loader.Data)var14.method27().get(var20)).getValue());
                     JsonElement var22 = ((JsonObject)var16.provide()).get("value");
                     if (var22.isJsonArray()) {
                        var17.add("value", var22);
                     } else {
                        var17.addProperty("value", var22.getAsString());
                     }

                     var14.method22(var15, var21);
                  }
               }

               var17.add("values", var33);
               var11.add(var17);
            }
         } else {
            var12.addAll(var15.getChildren().stream().map(ClientOption::getId).toList());
         }
      }

      var10.addProperty("type", var2.type());
      if (var2 instanceof Fishing2Iterator2 var23) {
         ModDetails var25 = (ModDetails)var23.getFeature().method1(Framework.field13);
         if (var25 != null) {
            var10.addProperty("name", AdventureTextBridge.stripColor(var25.getName()));
         }
      } else {
         var10.addProperty("name", var2.method9());
      }

      var10.addProperty("enabled", var2.isEnabled());
      var10.addProperty("extended", var2.isExtended());
      boolean var24 = (var2.method14() || var3 != null && !var3.method15().contains(var2.type())) && (!var6 || var2 instanceof Nameplate3);
      if (Coordinates.getSelectedKeyframe() != null) {
         var24 = Coordinates.getSelectedKeyframe().method1() == var2;
      }

      boolean var26 = var2.method15() || var3 != null && !var3.method15().contains(var2.type());
      var10.addProperty("canDelete", var24);
      var10.addProperty("canDisable", var26);
      var10.addProperty("dynamicType", var2.method16().toString());
      var10.add("keyframes", var11);
      if (var2 instanceof Nameplate3 var27) {
         JsonArray var29 = new JsonArray();

         for (Nameplate_2 var35 : var27.method3().apply(var1)) {
            if (var27.method2(var35.type())) {
               JsonObject var36 = new JsonObject();
               var36.addProperty("type", var35.type());
               var36.addProperty("name", var35.name());
               if (var35.method1() != null) {
                  var36.addProperty("head", var35.method1().toString());
               }

               var29.add(var36);
            }
         }

         var10.add("dynamicEntries", var29);
      }

      JsonArray var28 = new JsonArray();
      if (Coordinates.getSelectedKeyframe() == null) {
         for (Fishing2Iterator var32 : var2.method11().values()) {
            var28.add(this.buildPropertyJson(var1, var32, var3, var32.method12().values(), var5, true));
         }
      }

      var10.add("childProperties", var28);
      return var10;
   }

   private JsonObject buildKeyframeJson(Collection<ClientOption<?>> var1) {
      JsonObject var2 = new JsonObject();
      JsonArray var3 = new JsonArray();
      ArrayList var4 = new ArrayList();

      for (ClientOption var6 : var1) {
         if (!var6.isHidden() && !var4.contains(var6.getId())) {
            OptionDataProvider var7 = (OptionDataProvider)var6.method1(OptionTraits.field10);
            if (var7 != null && var7.provide() instanceof JsonObject var8) {
               var8.addProperty("canReset", !var6.isDefault());
               var8.addProperty("showInTimeline", false);
               var8.addProperty("previousKeyframe", false);
               var8.addProperty("hasKeyframe", false);
               var8.addProperty("nextKeyframe", false);
               var8.addProperty("value", ((JsonObject)var7.provide()).get("value").getAsString());
               var8.add("values", new JsonArray());
               var3.add(var8);
            }
         } else {
            var4.addAll(var6.getChildren().stream().map(ClientOption::getId).toList());
         }
      }

      var2.addProperty("type", "keyframe");
      var2.addProperty("name", Client.method109().method67().method2("rewind", "keyframe", new Object[0]));
      var2.addProperty("enabled", true);
      var2.addProperty("extended", true);
      var2.addProperty("canDelete", false);
      var2.addProperty("canDisable", false);
      var2.add("childProperties", new JsonArray());
      var2.add("keyframes", var3);
      return var2;
   }

   @Generated
   public List<ClientOption<?>> getOptions() {
      return this.options;
   }
}
