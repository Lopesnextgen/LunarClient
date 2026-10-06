package com.moonsworth.lunar.client.mod.skyblock.dungeontimer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.config.option.NamedColorOption;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework11;
import com.moonsworth.lunar.client.framework.mod.Framework2;
import com.moonsworth.lunar.client.framework.mod.Framework4;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.MixinCore2;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.framework.feature.mod.Module;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.Fishing2_2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click12;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms11;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms4_4;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.Nameplate;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.HighlightType;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.HighlightType2;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.GuiRewindhandlersHandler22_2;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.mixin.GuiRewindhandlersHandler2;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.HighlightBase5;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.Rewindhandlers$Data10;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorldLifecycle.EventWorldChanged;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.OptionSupplier;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.util.ThreadModuleDump11;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;

public class SkyblockDungeonTimer extends AbstractFeature {
   private final GuiRewindhandlersHandler22_2 field8 = (GuiRewindhandlersHandler22_2)this.method19(GuiRewindhandlersHandler22_2.class);
   private final GuiRewindhandlersHandler2 field9 = (GuiRewindhandlersHandler2)this.method19(GuiRewindhandlersHandler2.class);
   private static final File field10 = new File(ThreadModuleDump48.field25 + File.separator + "skyblock_times.json");
   private static final Pattern field11 = Pattern.compile("^ +> EXTRA STATS <$");
   private static final Pattern field12 = Pattern.compile("^ +DEFEAT$");
   private final ToggleOption field13 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("dungeonTimer")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field14 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("kuudraTimer")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final IntegerOption field15 = (IntegerOption)((IntegerOption.Data)((IntegerOption.Data)OptionFactory.method4(
               "dungeonTimerDecimals"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(2))
         .method7(0, 3))
      .method31();
   private final ToggleOption field16 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("dungeonTimerInChat")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field17 = (ToggleOption)OptionFactory.method7("dungeonTimerShowDifference").method31();
   private final ToggleOption field18 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("skyblockShowSplitTickTime")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final EnumOption<NamedColorOption> field19 = (EnumOption<NamedColorOption>)OptionFactory.method10(
         "dungeonTimerCurrentPaceColor", NamedColorOption.GOLD
      )
      .method31();
   private final EnumOption<NamedColorOption> field20 = (EnumOption<NamedColorOption>)OptionFactory.method10(
         "dungeonTimerSumOfBestColor", NamedColorOption.GOLD
      )
      .method31();
   private final EnumOption<NamedColorOption> field21 = (EnumOption<NamedColorOption>)OptionFactory.method10("dungeonTimerColor", NamedColorOption.GRAY)
      .method31();
   private final EnumOption<NamedColorOption> field22 = (EnumOption<NamedColorOption>)OptionFactory.method10(
         "dungeonTimerBeatPBColor", NamedColorOption.GREEN
      )
      .method31();
   private final EnumOption<NamedColorOption> field23 = (EnumOption<NamedColorOption>)OptionFactory.method10(
         "dungeonTimerDidNotBeatPBColor", NamedColorOption.RED
      )
      .method31();
   private final EnumOption<NamedColorOption> field24 = (EnumOption<NamedColorOption>)OptionFactory.method10(
         "dungeonTimerBeatGoldColor", NamedColorOption.GOLD
      )
      .method31();
   private final List<Holograms4_4> field25 = new ArrayList<>();
   private List<Holograms4_4> field26;
   private int field27;

   public SkyblockDungeonTimer(Skyblock var1) {
      super(false);
      this.method32(Framework.field16, Framework4.method3(var1));
      this.method32(Framework.field1, Nameplate.method4(new SkyblockDungeonTimer.Data()));
      this.method32(Framework.field17, Framework2.method2(SettingsPage.TIMERS));
      this.method32(Framework.field19, Framework11.method1(this, Click3::hasIsland));
      this.ORCHOHHCOHCORRICRIHCHHRORHHCHH(this::method16);
      this.OHROCHICOIOICHOCRROORRCIIICIHO(this::onDisable);
      this.handle(Rewindhandlers$Data10.class, this::method1);
      this.handle(HighlightBase5.Data2.class, this::method2);
      this.handle(com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy.Data.class, this::method3);
      this.handle(EventClientTick.class, this::method4);
      this.handle(EventWorldChanged.class, this::method5);
      this.handle(com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.HighlightImpl2.class, var1x -> this.method16());
   }

   private void method1(Rewindhandlers$Data10 var1) {
      if (this.field13.get()) {
         HighlightType var2 = this.field8.method6();
         Map var3 = this.method17();
         if (var3 != null) {
            List var4 = (List)var3.get(var2);
            if (var4 != null) {
               for (Holograms4_4 var6 : var4) {
                  var6.reset();
               }

               this.field27 = 0;
               this.field26 = var4;
            }
         }
      }
   }

   private void method2(HighlightBase5.Data2 var1) {
      if (this.field14.get()) {
         HighlightType2 var2 = this.field9.method6();
         Map var3 = this.method19();
         if (var3 != null) {
            List var4 = (List)var3.get(var2);
            if (var4 != null) {
               for (Holograms4_4 var6 : var4) {
                  var6.reset();
               }

               this.field27 = 0;
               this.field26 = var4;
            }
         }
      }
   }

   private void method3(com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy.Data var1) {
      if (this.field26 != null) {
         String var2 = AdventureChatFormatting.getTextWithoutFormattingCodes(var1.OROIIOCCOORRCRCIIHHOCCCRHICRCC());
         if (var2.equals("Starting in 1 second.")) {
            this.field26.get(0).start(1000);
         } else if (var2.equals("[NPC] Elle: Okay adventurers, I will go and fish up Kuudra!")) {
            this.field26.get(0).start();
         }

         if (this.field27 < this.field26.size()) {
            if (!field11.matcher(var2).matches() && !field12.matcher(var2).matches()) {
               Holograms4_4 var3 = this.field26.get(this.field27);
               Matcher var4 = var3.method7().matcher(var2);
               if (var4.matches()) {
                  this.method13();
               }
            } else {
               this.field26.get(this.field27).stop();
            }
         }
      }
   }

   private void method4(EventClientTick var1) {
      for (Holograms4_4 var3 : this.field25) {
         Fishing2_2.sendMessage(var3.method4(this));
      }

      this.field25.clear();
   }

   private void onDisable() {
      if (this.field26 != null) {
         for (Holograms4_4 var2 : this.field26) {
            var2.method5(var2.method9());
         }

         this.field26 = null;
      }
   }

   private void method5(EventWorldChanged var1) {
      this.onDisable();
   }

   private void method13() {
      Holograms4_4 var1 = this.field26.get(this.field27);
      var1.stop();
      double var2 = var1.method1();
      if (var1.method10() < 0.0 || var2 < var1.method10()) {
         var1.method16(var2);
         this.method15();
      }

      if (this.field16.get() && this.field27 != this.field26.size() - 1) {
         this.field25.add(var1);
      }

      this.field27++;
      if (this.field27 >= this.field26.size()) {
         this.field25.addAll(this.field26);
         double var4 = 0.0;
         double var6 = 0.0;

         for (Holograms4_4 var9 : this.field26) {
            if (var9.method9() < 0.0) {
               this.method14();
               return;
            }

            var4 += var9.method9();
            var6 += var9.method1();
         }

         if (var6 < var4) {
            this.method14();
         }
      } else {
         this.field26.get(this.field27).start();
      }
   }

   private void method14() {
      for (Holograms4_4 var2 : this.field26) {
         var2.method15(var2.method1());
      }

      this.method15();
   }

   private void method15() {
      Map var1 = this.method17();
      Map var2 = this.method19();
      if (var1 != null && var2 != null) {
         try {
            Object var3;
            if (field10.exists()) {
               try {
                  var3 = JsonParser.parseReader(new FileReader(field10));
               } catch (JsonParseException var16) {
                  var3 = new JsonObject();
               }
            } else {
               var3 = new JsonObject();
            }

            if (!var3.isJsonObject()) {
               return;
            }

            JsonObject var4 = var3.getAsJsonObject();
            JsonObject var5 = new JsonObject();
            JsonObject var6 = new JsonObject();

            for (Entry var8 : var1.entrySet()) {
               JsonObject var9 = new JsonObject();
               List var10 = (List)var8.getValue();

               for (int var11 = 0; var11 < var10.size(); var11++) {
                  Holograms4_4 var12 = (Holograms4_4)var10.get(var11);
                  JsonObject var13 = new JsonObject();
                  var13.addProperty("pb", var12.method9());
                  var13.addProperty("gold", var12.method10());
                  var9.add(String.valueOf(var11), var13);
               }

               var5.add(((HighlightType)var8.getKey()).name(), var9);
            }

            for (Entry var20 : var2.entrySet()) {
               JsonObject var21 = new JsonObject();
               List var22 = (List)var20.getValue();

               for (int var23 = 0; var23 < var22.size(); var23++) {
                  Holograms4_4 var24 = (Holograms4_4)var22.get(var23);
                  JsonObject var25 = new JsonObject();
                  var25.addProperty("pb", var24.method9());
                  var25.addProperty("gold", var24.method10());
                  var21.add(String.valueOf(var23), var25);
               }

               var6.add(((HighlightType2)var20.getKey()).name(), var21);
            }

            var4.add("catacombs", var5);
            var4.add("kuudra", var6);

            try (FileWriter var19 = new FileWriter(field10)) {
               ThreadModuleDump48.field22.toJson(var4, var19);
            }
         } catch (IOException var17) {
            Inventorymod2.method5(var17, "Saving SkyBlock Timers");
         }
      }
   }

   private void method16() {
      Map var1 = this.method17();
      Map var2 = this.method19();
      if (var1 != null && var2 != null) {
         if (field10.exists()) {
            try {
               JsonElement var3 = JsonParser.parseReader(new FileReader(field10));
               if (!var3.isJsonObject()) {
                  return;
               }

               JsonObject var4 = var3.getAsJsonObject();
               JsonObject var5 = var4.getAsJsonObject("catacombs");
               JsonObject var6 = var4.getAsJsonObject("kuudra");

               for (Entry var8 : var1.entrySet()) {
                  String var9 = ((HighlightType)var8.getKey()).name();
                  if (var5.has(var9)) {
                     JsonObject var10 = var5.getAsJsonObject(var9);
                     List var11 = (List)var8.getValue();

                     for (int var12 = 0; var12 < var11.size(); var12++) {
                        String var13 = String.valueOf(var12);
                        if (var10.has(var13)) {
                           JsonObject var14 = var10.getAsJsonObject(var13);
                           Holograms4_4 var15 = (Holograms4_4)var11.get(var12);
                           var15.method5(var14.get("pb").getAsDouble());
                           var15.method16(var14.get("gold").getAsDouble());
                        }
                     }
                  }
               }

               for (Entry var18 : var2.entrySet()) {
                  String var19 = ((HighlightType2)var18.getKey()).name();
                  if (var6.has(var19)) {
                     JsonObject var20 = var6.getAsJsonObject(var19);
                     List var21 = (List)var18.getValue();

                     for (int var22 = 0; var22 < var21.size(); var22++) {
                        String var23 = String.valueOf(var22);
                        if (var20.has(var23)) {
                           JsonObject var24 = var20.getAsJsonObject(var23);
                           Holograms4_4 var25 = (Holograms4_4)var21.get(var22);
                           var25.method5(var24.get("pb").getAsDouble());
                           var25.method16(var24.get("gold").getAsDouble());
                        }
                     }
                  }
               }
            } catch (IOException var16) {
               Inventorymod2.method5(var16, "Loading SkyBlock Timers");
            }
         }
      }
   }

   @Nullable
   private Map<HighlightType, List<Holograms4_4>> method17() {
      Module var1 = ThreadModuleDump63.method4().method40().method82().method15();
      if (!var1.method8()) {
         return null;
      }

      Holograms11 var2 = var1.method14();
      return var2 == null ? null : var2.method1();
   }

   @Nullable
   private Map<HighlightType2, List<Holograms4_4>> method19() {
      Module var1 = ThreadModuleDump63.method4().method40().method82().method15();
      if (!var1.method8()) {
         return null;
      }

      Holograms11 var2 = var1.method14();
      return var2 == null ? null : var2.method2();
   }

   @Override
   public String getId() {
      return "SKYBLOCK_DUNGEON_TIMER";
   }

   @Override
   public void method2(RootSettingsAssembler var1) {
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(
         SettingsPage.GENERAL,
         var1x -> var1x.method2(
            new OptionSupplier[]{
               this.field13,
               this.field14,
               this.field15,
               this.field16,
               this.field17,
               this.field19,
               this.field20,
               this.field21,
               this.field22,
               this.field23,
               this.field24,
               this.field18,
               OptionFactory.method14("skyblockSplitsResetPBs").method4(() -> {
                  Map var1xx = this.method17();
                  if (var1xx != null) {
                     for (Entry var3 : var1xx.entrySet()) {
                        ((List)var3.getValue()).forEach(var0 -> {
                           var0.method5(-1.0);
                           var0.method16(-1.0);
                        });
                     }
                  }

                  Map var5 = this.method19();
                  if (var5 != null) {
                     for (Entry var4 : var5.entrySet()) {
                        ((List)var4.getValue()).forEach(var0 -> {
                           var0.method5(-1.0);
                           var0.method16(-1.0);
                        });
                     }
                  }

                  this.method15();
               })
            }
         )
      );
   }

   @Generated
   public GuiRewindhandlersHandler22_2 method21() {
      return this.field8;
   }

   @Generated
   public GuiRewindhandlersHandler2 method22() {
      return this.field9;
   }

   @Generated
   public ToggleOption method23() {
      return this.field13;
   }

   @Generated
   public ToggleOption method24() {
      return this.field14;
   }

   @Generated
   public IntegerOption method25() {
      return this.field15;
   }

   @Generated
   public ToggleOption method26() {
      return this.field16;
   }

   @Generated
   public ToggleOption method27() {
      return this.field17;
   }

   @Generated
   public ToggleOption method28() {
      return this.field18;
   }

   @Generated
   public EnumOption<NamedColorOption> method29() {
      return this.field19;
   }

   @Generated
   public EnumOption<NamedColorOption> method30() {
      return this.field20;
   }

   @Generated
   public EnumOption<NamedColorOption> method34() {
      return this.field21;
   }

   @Generated
   public EnumOption<NamedColorOption> method35() {
      return this.field22;
   }

   @Generated
   public EnumOption<NamedColorOption> method36() {
      return this.field23;
   }

   @Generated
   public EnumOption<NamedColorOption> method37() {
      return this.field24;
   }

   @Generated
   public List<Holograms4_4> method38() {
      return this.field25;
   }

   @Generated
   public List<Holograms4_4> method39() {
      return this.field26;
   }

   @Generated
   public int method40() {
      return this.field27;
   }

   private class Data extends TypedHudRenderer<List<TextComponent>> {
      private static final double field31 = 6.4;
      private List<Holograms4_4> field32;

      public Data() {
         super(0.0F, 0.0F, HudAnchor.TOP_LEFT, true);
      }

      @Override
      public MixinCore2 method15() {
         return MixinCore2.method1(20, 30, 60, 20, 80, 200);
      }

      @Nullable
      public List<TextComponent> method2(boolean var1) {
         if (var1) {
            return this.method3(this.method24());
         } else {
            return SkyblockDungeonTimer.this.field26 == null ? null : this.method3(SkyblockDungeonTimer.this.field26);
         }
      }

      private List<TextComponent> method3(List<Holograms4_4> var1) {
         ArrayList var2 = new ArrayList();

         for (Holograms4_4 var4 : var1) {
            var2.add(var4.method4(SkyblockDungeonTimer.this));
         }

         if (SkyblockDungeonTimer.this.field19.get() != NamedColorOption.OFF) {
            TextComponent var5 = this.method9(var1);
            if (var5 != null) {
               var2.add(var5);
            }
         }

         if (SkyblockDungeonTimer.this.field20.get() != NamedColorOption.OFF) {
            TextComponent var6 = this.method10(var1);
            if (var6 != null) {
               var2.add(var6);
            }
         }

         return var2;
      }

      private List<Holograms4_4> method24() {
         if (this.field32 == null) {
            ArrayList var1 = new ArrayList();
            var1.add(this.method5(SkyblockDungeonTimer.this.method33("bloodOpen", new Object[0]), NamedTextColor.RED, 29.87, 11.2, 19.5, false));
            var1.add(this.method5(SkyblockDungeonTimer.this.method33("bloodClear", new Object[0]), NamedTextColor.RED, 55.14, 52.0, 53.0, false));
            var1.add(this.method5(SkyblockDungeonTimer.this.method33("professorEntry", new Object[0]), NamedTextColor.AQUA, 3.25, 3.3, 3.5, false));
            var1.add(this.method5(SkyblockDungeonTimer.this.method33("guardians", new Object[0]), NamedTextColor.AQUA, 22.3, 20.4, 21.5, false));
            var1.add(this.method5(SkyblockDungeonTimer.this.method33("professorPhaseOne", new Object[0]), NamedTextColor.AQUA, 12.05, 10.93, 11.0, false));
            var1.add(this.method5(SkyblockDungeonTimer.this.method33("professorPhaseTwo", new Object[0]), NamedTextColor.AQUA, 6.4, 21.0, 22.0, true));
            this.field32 = var1;
         }

         this.field32.get(this.field32.size() - 1).setStartTime(ThreadModuleDump63.method3().bridge$getSystemTime() - 6400L);
         return this.field32;
      }

      private Holograms4_4 method5(String var1, NamedTextColor var2, double var3, double var5, double var7, boolean var9) {
         Holograms4_4 var10 = new Holograms4_4((TextComponent)Component.text(var1).color(var2), ".*");
         var10.method16(var5);
         var10.method5(var7);
         if (var9) {
            var10.setStartTime(ThreadModuleDump63.method3().bridge$getSystemTime() - (long)(var3 * 1000.0));
         } else {
            var10.setStartTime(0L);
            var10.method14((long)(var3 * 1000.0));
         }

         return var10;
      }

      @Override
      protected com.moonsworth.lunar.client.ui.hud.row.Gui2Extension method16() {
         return com.moonsworth.lunar.client.ui.hud.row.Gui2Extension.LEFT;
      }

      @Override
      protected boolean method20() {
         return false;
      }

      @Override
      protected boolean method22() {
         return false;
      }

      private TextComponent method9(List<Holograms4_4> var1) {
         if (var1 == null) {
            return null;
         }

         double var2 = 0.0;

         for (Holograms4_4 var5 : var1) {
            if (var5.method9() < 0.0) {
               return null;
            }

            if (var5.method8() == -1L && !(var5.method1() > var5.method9())) {
               var2 += var5.method9();
            } else {
               var2 += var5.method1();
            }
         }

         return (TextComponent)((TextComponent)((TextComponent)((TextComponent)Component.empty().style(Click12.getStyle(SkyblockDungeonTimer.this.field19)))
                  .append(Component.text(SkyblockDungeonTimer.this.method33("currentPace", new Object[0]))))
               .append(Component.text(": ")))
            .append(Component.text(ThreadModuleDump11.formatDuration((long)var2 * 1000L)));
      }

      private TextComponent method10(List<Holograms4_4> var1) {
         if (var1 == null) {
            return null;
         }

         double var2 = 0.0;

         for (Holograms4_4 var5 : var1) {
            if (var5.method10() < 0.0) {
               return null;
            }

            var2 += var5.method10();
         }

         return (TextComponent)((TextComponent)((TextComponent)((TextComponent)Component.empty().style(Click12.getStyle(SkyblockDungeonTimer.this.field20)))
                  .append(Component.text(SkyblockDungeonTimer.this.method33("sumOfBest", new Object[0]))))
               .append(Component.text(": ")))
            .append(Component.text(ThreadModuleDump11.formatDuration((long)var2 * 1000L)));
      }
   }
}
