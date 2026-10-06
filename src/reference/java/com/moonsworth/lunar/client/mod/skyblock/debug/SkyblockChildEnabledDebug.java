package com.moonsworth.lunar.client.mod.skyblock.debug;

import com.google.gson.reflect.TypeToken;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.TextHudComponent;
import com.moonsworth.lunar.client.ui.hud.ScrollableHudComponent;
import com.moonsworth.lunar.client.ui.hud.HudComponentGroup;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Base;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WidgetFactory;
import com.moonsworth.lunar.client.config.option.EnabledOption;
import com.moonsworth.lunar.client.config.option.DefaultedBooleanOption;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.OptionProvider;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.ConstantName;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.io.ClipboardUtils;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockChildEnabledDebug extends AbstractFeature {
   private final ToggleOption field8 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("preMigration").method4(true))
      .method31();
   private final HudComponentGroup field9 = new HudComponentGroup(true);
   private final Map<String, Boolean> field10 = new LinkedHashMap<>();

   public SkyblockChildEnabledDebug(SkyblockDebugMod skyblockdebugmod1) {
      super(false);
      this.method4(ModTraits.field16, ChildModBinding.method3(skyblockdebugmod1));
      this.method4(ModTraits.field1, this.method13());
      this.handle(EventSecond.class, this::method5);
   }

   @ConstantName
   public String getId() {
      return "SKYBLOCK_CHILD_ENABLED_DEBUG";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field8});
      lightingextension231.method9(new OptionProvider[]{OptionFactory.method14("copyStateToClipboard").method4(this::method15)});
      lightingextension231.method9(new OptionProvider[]{OptionFactory.method14("saveBefore").method4(this::method16)});
      lightingextension231.method9(new OptionProvider[]{OptionFactory.method14("saveAfter").method4(this::method17)});
      lightingextension231.method9(new OptionProvider[]{OptionFactory.method14("compareAndLog").method4(this::method19)});
   }

   private MixinCore9Extension method13() {
      return MixinCore9Base.method9(0.0F, 0.0F, HudAnchor.TOP_LEFT, false, WidgetFactory.withBackground(new ScrollableHudComponent(this.field9).method1(300.0F).method3(true)));
   }

   private void method14() {
      Skyblock skyblock1 = Ref.method4().method40().method82();
      this.field10.clear();
      skyblock1.method3(ModTraits.field5).ifPresent(arg1x -> {
         for (Framework7Extension framework7extension3 : arg1x.getChildren()) {
            ModDetails framework84 = (ModDetails)framework7extension3.method3(ModTraits.field13).orElse(null);
            ModEnabledState framework35 = (ModEnabledState)framework7extension3.method1(ModTraits.field6);
            String text6 = framework84 != null ? framework84.getName() : framework7extension3.getId();
            if ((Boolean)this.field8.get() && this.method4(framework35)) {
               ClientOption lightingextension7 = (ClientOption)framework35.method1().orElseThrow();
               this.field10.put(lightingextension7.getName(), (Boolean)lightingextension7.get());
            } else {
               this.field10.put(text6, framework7extension3.isEnabled());
            }
         }
      });
   }

   private boolean method4(ModEnabledState framework31) {
      return framework31 instanceof EnabledOption && !(framework31 instanceof DefaultedBooleanOption);
   }

   private void method5(EventSecond highlightimpl41) {
      if (this.method2(ModTraits.field1)) {
         this.method14();
         this.field9.method8();
         this.field10
            .forEach(
               (arg1x, arg2) -> {
                  Component component3 = ((TextComponent)Component.text(arg1x).append(Component.text(" -> ").color(NamedTextColor.GRAY)))
                     .append(Component.text(arg2).color(arg2 ? NamedTextColor.GREEN : NamedTextColor.RED));
                  this.field9.method5(new TextHudComponent(component3));
               }
            );
      }
   }

   private void method15() {
      this.method14();
      StringBuilder builder1 = new StringBuilder();
      builder1.append("{\n");
      int number2 = this.field10.size();
      int number3 = 0;

      for (Entry entry5 : this.field10.entrySet()) {
         builder1.append("  \"").append((String)entry5.getKey()).append("\": ").append(entry5.getValue());
         if (++number3 < number2) {
            builder1.append(",");
         }

         builder1.append("\n");
      }

      builder1.append("}\n");
      ClipboardUtils.method2(builder1.toString());
   }

   private void method16() {
      this.method14();
      Skyblock skyblock1 = Ref.method4().method40().method82();
      skyblock1.method15().method5("beforeChildDebug.json", this.field10);
      Ref.method4().method69().method2("Saved!", "Probably, we're not error handling");
   }

   private void method17() {
      this.method14();
      Skyblock skyblock1 = Ref.method4().method40().method82();
      skyblock1.method15().method5("afterChildDebug.json", this.field10);
      Ref.method4().method69().method2("Saved!", "Probably, we're not error handling");
   }

   private void method19() {
      Skyblock skyblock1 = Ref.method4().method40().method82();
      Type type2 = (new TypeToken<LinkedHashMap<String, Boolean>>() {}).getType();
      Map map3 = (Map)skyblock1.method15().method6("beforeChildDebug.json", type2);
      Map map4 = (Map)skyblock1.method15().method6("afterChildDebug.json", type2);
      if (map3 != null && map4 != null) {
         StringBuilder builder5 = new StringBuilder();
         builder5.append("--- SkyBlock Child Enabled Comparison ---\n");

         for (String text7 : map4.keySet()) {
            if (!map3.containsKey(text7)) {
               builder5.append("[NEW] ").append(text7).append(": ").append(map4.get(text7)).append("\n");
            }
         }

         for (String text14 : map3.keySet()) {
            if (!map4.containsKey(text14)) {
               builder5.append("[REMOVED] ").append(text14).append(": ").append(map3.get(text14)).append("\n");
            }
         }

         for (Entry entry15 : map3.entrySet()) {
            String text8 = (String)entry15.getKey();
            if (map4.containsKey(text8)) {
               boolean flag9 = (Boolean)entry15.getValue();
               boolean flag10 = (Boolean)map4.get(text8);
               if (flag9 != flag10) {
                  builder5.append("[VALUE MISMATCH] ").append(text8).append(": ").append(flag9).append(" -> ").append(flag10).append("\n");
               }
            }
         }

         String text13 = builder5.toString();
         System.out.println(text13);
         ClipboardUtils.method2(text13);
         Ref.method4().method69().method2("Comparison complete!", "Result copied to clipboard and sent to logs");
      } else {
         Ref.method4().method69().method2("Error", "Could not find before/after JSON files.");
      }
   }
}
