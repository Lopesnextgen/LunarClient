package com.moonsworth.lunar.client.replay.gui;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.replay.timeline.KeyframeProperty;
import com.moonsworth.lunar.client.replay.project.ReplayProjectManager;
import com.moonsworth.lunar.client.replay.timeline.RewindIterator;
import com.moonsworth.lunar.client.replay.timeline.ReplayTimeline;
import com.moonsworth.lunar.client.replay.timeline.UndoRedoManager;
import com.moonsworth.lunar.client.replay.timeline.PropertyGroup;
import com.moonsworth.lunar.client.replay.timeline.ModPropertyGroup;
import com.moonsworth.lunar.client.replay.timeline.DynamicCategory;
import com.moonsworth.lunar.client.replay.timeline.SettingOption;
import com.moonsworth.lunar.client.replay.gui.RewindEditorContext;
import com.moonsworth.lunar.client.replay.gui.SelectedKeyframe;
import com.moonsworth.lunar.client.replay.gui.RewindPropertyProvider;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.OptionDataProvider;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.config.option.InterpolationMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import lombok.Generated;
import org.apache.commons.lang3.Range;

public class RewindPropertiesPanel extends RewindPropertyProvider {
   private final EnumOption<InterpolationMode> field6 = (EnumOption<InterpolationMode>)OptionFactory.method10(
         "interpolation", InterpolationMode.LINEAR
      )
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption field7 = (ToggleOption)OptionFactory.method7("easeIn").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption field8 = (ToggleOption)OptionFactory.method7("easeOut").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final List<ClientOption<?>> options = List.of(this.field6, this.field7, this.field8);

   public RewindPropertiesPanel(List<RewindPropertyProvider> list1) {
      super(list1);
   }

   @Override
   public void method1(RewindHandlers rewindhandlers1) {
      if (!RewindEditorContext.isMultiSelectEnabled()) {
         ReplayProjectManager rewind2_32 = rewindhandlers1.method40();
         SelectedKeyframe coordinates43 = RewindEditorContext.getSelectedKeyframe();
         RewindIterator rewinditerator4 = RewindEditorContext.getSelectedLayer();
         UndoRedoManager nameplate25 = rewind2_32.method40();
         if (rewinditerator4 != null) {
            JsonArray array6 = new JsonArray();

            for (Entry entry8 : rewind2_32.method38().method4().entrySet()) {
               if (((List)entry8.getValue()).isEmpty() || ((List)entry8.getValue()).contains(rewinditerator4.type())) {
                  JsonObject json9 = new JsonObject();
                  json9.addProperty("type", (String)entry8.getKey());
                  json9.addProperty("name", Client.method109().method67().method2("rewind", (String)entry8.getKey(), new Object[0]));
                  json9.addProperty("hidden", ((List)entry8.getValue()).isEmpty());
                  array6.add(json9);
               }
            }

            this.method3("availableProperties", array6);
            this.method3("showMods", rewinditerator4.type().equals("gameplay") || rewinditerator4.type().equals("effect"));
         } else {
            this.method3("availableProperties", new JsonArray());
            this.method3("showMods", false);
         }

         JsonArray array10 = new JsonArray();
         if (coordinates43 != null) {
            array10.add(this.method2(rewindhandlers1, coordinates43.method1(), rewinditerator4, List.of(coordinates43.method2()), null, false));
            KeyframeProperty.Keyframe data11 = coordinates43.method2().method27().get(coordinates43.method3());
            if (data11 != null) {
               this.field6.HORHROIOIOICIRHIOCOICHHHIHCIIO(OptionTraits.field1);
               this.field7.HORHROIOIOICIRHIOCOICHHHIHCIIO(OptionTraits.field1);
               this.field8.HORHROIOIOICIRHIOCOICHHHIHCIIO(OptionTraits.field1);
               this.field6.HIRIHCROOIRIORCCOIRRCRHOHCCRRO(data11.method2());
               this.field6.OIRHOOIICOCIOOHICRRRICORIHHIHC(data11.method2());
               this.field7.method6(data11.method3());
               this.field7.method10(data11.method3());
               this.field8.method6(data11.method4());
               this.field8.method10(data11.method4());
               this.field6.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg2x -> {
                  InterpolationMode threadmoduledump73type3x = data11.method2();
                  nameplate25.method4(() -> data11.method5(threadmoduledump73type3x), () -> data11.method5(arg2x));
                  data11.method5(arg2x);
               });
               this.field7.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg2x -> {
                  boolean flag3x = data11.method3();
                  nameplate25.method4(() -> data11.method6(flag3x), () -> data11.method6(arg2x));
                  data11.method6(arg2x);
               });
               this.field8.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg2x -> {
                  boolean flag3x = data11.method4();
                  nameplate25.method4(() -> data11.method7(flag3x), () -> data11.method7(arg2x));
                  data11.method7(arg2x);
               });
               array10.add(this.method3(data11.method2().isSupportsInOut() ? this.options : List.of(this.field6)));
            }

            this.method3("hideKeyframeControls", true);
         } else if (rewinditerator4 != null) {
            Range range12 = RewindEditorContext.getLayerRange(rewinditerator4.getId());

            for (PropertyGroup fishing2iterator14 : rewinditerator4.method18().values()) {
               array10.add(this.method2(rewindhandlers1, fishing2iterator14, rewinditerator4, fishing2iterator14.method12().values(), range12, false));
            }

            this.method3("hideKeyframeControls", false);
         }

         this.method3("properties", array10);
         this.method3("canUndo", rewind2_32.method40().canUndo());
         this.method3("canRedo", rewind2_32.method40().canRedo());
      }
   }

   private JsonObject method2(
      RewindHandlers rewindhandlers1, PropertyGroup fishing2iterator2, RewindIterator<?> rewinditerator3, Collection<KeyframeProperty<?, ?>> list4, Range<Integer> range5, boolean flag6
   ) {
      ReplayTimeline highlight_37 = ((ReplayContext)rewindhandlers1.method42().get()).method4();
      if (highlight_37 == null) {
         return new JsonObject();
      }

      int number8 = highlight_37.method15();
      int number9 = number8 - (range5 == null ? 0 : (Integer)range5.getMinimum());
      JsonObject json10 = new JsonObject();
      JsonArray array11 = new JsonArray();
      ArrayList list12 = new ArrayList();

      for (KeyframeProperty fishing2loader14 : list4) {
         ClientOption lightingextension15 = fishing2loader14.getOption();
         if (!lightingextension15.isHidden() && !list12.contains(lightingextension15.getId())) {
            OptionDataProvider guiextension16 = (OptionDataProvider)lightingextension15.HIRHCCHIRHRORIICOIHIHCICOIRHHC(OptionTraits.field10);
            if (guiextension16 != null && guiextension16.provide() instanceof JsonObject json17) {
               json17.addProperty("canReset", fishing2loader14.method4());
               json17.addProperty("showInTimeline", fishing2loader14.method28());
               json17.addProperty("previousKeyframe", fishing2loader14.method13(number9) != null);
               json17.addProperty("hasKeyframe", fishing2loader14.method9(number9));
               json17.addProperty("nextKeyframe", fishing2loader14.method14(number9) != null);
               JsonArray array33 = new JsonArray();

               for (Integer index20 : fishing2loader14.method27().keySet()) {
                  array33.add(index20);
                  if (RewindEditorContext.getSelectedKeyframe() != null && RewindEditorContext.getSelectedKeyframe().method2() == fishing2loader14 && RewindEditorContext.getSelectedKeyframe().method3() == index20) {
                     fishing2loader14.method30(null);
                     Object obj21 = fishing2loader14.method21(lightingextension15);
                     fishing2loader14.method22(lightingextension15, ((KeyframeProperty.Keyframe)fishing2loader14.method27().get(index20)).getValue());
                     JsonElement element22 = ((JsonObject)guiextension16.provide()).get("value");
                     if (element22.isJsonArray()) {
                        json17.add("value", element22);
                     } else {
                        json17.addProperty("value", element22.getAsString());
                     }

                     fishing2loader14.method22(lightingextension15, obj21);
                  }
               }

               json17.add("values", array33);
               array11.add(json17);
            }
         } else {
            list12.addAll(lightingextension15.getChildren().stream().map(ClientOption::getId).toList());
         }
      }

      json10.addProperty("type", fishing2iterator2.type());
      if (fishing2iterator2 instanceof ModPropertyGroup fishing2iterator223) {
         ModDetails framework825 = (ModDetails)fishing2iterator223.getFeature().HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field13);
         if (framework825 != null) {
            json10.addProperty("name", TextBridge.stripColor(framework825.getName()));
         }
      } else {
         json10.addProperty("name", fishing2iterator2.method9());
      }

      json10.addProperty("enabled", fishing2iterator2.isEnabled());
      json10.addProperty("extended", fishing2iterator2.isExtended());
      boolean flag24 = (fishing2iterator2.method14() || rewinditerator3 != null && !rewinditerator3.method15().contains(fishing2iterator2.type())) && (!flag6 || fishing2iterator2 instanceof DynamicCategory);
      if (RewindEditorContext.getSelectedKeyframe() != null) {
         flag24 = RewindEditorContext.getSelectedKeyframe().method1() == fishing2iterator2;
      }

      boolean flag26 = fishing2iterator2.method15() || rewinditerator3 != null && !rewinditerator3.method15().contains(fishing2iterator2.type());
      json10.addProperty("canDelete", flag24);
      json10.addProperty("canDisable", flag26);
      json10.addProperty("dynamicType", fishing2iterator2.method16().toString());
      json10.add("keyframes", array11);
      if (fishing2iterator2 instanceof DynamicCategory nameplate327) {
         JsonArray array29 = new JsonArray();

         for (SettingOption nameplate_235 : nameplate327.method3().apply(rewindhandlers1)) {
            if (nameplate327.method2(nameplate_235.type())) {
               JsonObject json36 = new JsonObject();
               json36.addProperty("type", nameplate_235.type());
               json36.addProperty("name", nameplate_235.name());
               if (nameplate_235.method1() != null) {
                  json36.addProperty("head", nameplate_235.method1().toString());
               }

               array29.add(json36);
            }
         }

         json10.add("dynamicEntries", array29);
      }

      JsonArray array28 = new JsonArray();
      if (RewindEditorContext.getSelectedKeyframe() == null) {
         for (PropertyGroup fishing2iterator32 : fishing2iterator2.method11().values()) {
            array28.add(this.method2(rewindhandlers1, fishing2iterator32, rewinditerator3, fishing2iterator32.method12().values(), range5, true));
         }
      }

      json10.add("childProperties", array28);
      return json10;
   }

   private JsonObject method3(Collection<ClientOption<?>> list1) {
      JsonObject json2 = new JsonObject();
      JsonArray array3 = new JsonArray();
      ArrayList list4 = new ArrayList();

      for (ClientOption lightingextension6 : list1) {
         if (!lightingextension6.isHidden() && !list4.contains(lightingextension6.getId())) {
            OptionDataProvider guiextension7 = (OptionDataProvider)lightingextension6.HIRHCCHIRHRORIICOIHIHCICOIRHHC(OptionTraits.field10);
            if (guiextension7 != null && guiextension7.provide() instanceof JsonObject json8) {
               json8.addProperty("canReset", !lightingextension6.isDefault());
               json8.addProperty("showInTimeline", false);
               json8.addProperty("previousKeyframe", false);
               json8.addProperty("hasKeyframe", false);
               json8.addProperty("nextKeyframe", false);
               json8.addProperty("value", ((JsonObject)guiextension7.provide()).get("value").getAsString());
               json8.add("values", new JsonArray());
               array3.add(json8);
            }
         } else {
            list4.addAll(lightingextension6.getChildren().stream().map(ClientOption::getId).toList());
         }
      }

      json2.addProperty("type", "keyframe");
      json2.addProperty("name", Client.method109().method67().method2("rewind", "keyframe", new Object[0]));
      json2.addProperty("enabled", true);
      json2.addProperty("extended", true);
      json2.addProperty("canDelete", false);
      json2.addProperty("canDisable", false);
      json2.add("childProperties", new JsonArray());
      json2.add("keyframes", array3);
      return json2;
   }

   @Generated
   public List<ClientOption<?>> getOptions() {
      return this.options;
   }
}
