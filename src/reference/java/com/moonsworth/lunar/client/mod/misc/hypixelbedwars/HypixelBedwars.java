package com.moonsworth.lunar.client.mod.misc.hypixelbedwars;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.hypixelbedwars.TitlesHandler;
import com.moonsworth.lunar.client.framework.feature.hypixelbedwars.mixin.BedwarsTeamColorMapper;
import com.moonsworth.lunar.client.framework.feature.titles.Titles;
import com.moonsworth.lunar.client.framework.listener.HypixelLocationListener;
import com.moonsworth.lunar.client.framework.listener.HypixelLocation;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.EventPlaySound;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.gui.EventServerBrand;
import com.moonsworth.lunar.client.event.mixin.gui.EventLocationChange;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderArmor;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.network.server.KeystrokesType;
import com.moonsworth.lunar.client.config.option.OptionEnumValue;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.OptionProvider;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.mod.misc.hypixelbedwars.HypixelBedwarsResourceCounter;
import com.moonsworth.lunar.client.mod.misc.hypixelbedwars.HypixelBedwarsStats;
import com.moonsworth.lunar.client.mod.misc.hypixelbedwars.HypixelBedwarsTeamDisplay;
import com.moonsworth.lunar.client.mod.misc.hypixelbedwars.HypixelBedwarsTimers;
import com.moonsworth.lunar.client.mod.misc.hypixelbedwars.HypixelBedwarsUpgradeDisplay;
import com.moonsworth.lunar.client.util.net.ServerUtils;
import com.moonsworth.lunar.client.config.option.ConfigRangeBuilder;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.Ref;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import lombok.Generated;
import net.kyori.adventure.title.Title;

public class HypixelBedwars extends AbstractFeature {
   private JsonObject field8;
   private boolean field9;
   private final ToggleOption field10 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("coloredBeds").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field11 = (ToggleOption)OptionFactory.method7("enablePracticeColor").method31();
   private final EnumOption<HypixelBedwars.Type> field12 = (EnumOption<HypixelBedwars.Type>)OptionFactory.method10(
         "practiceBedColor", HypixelBedwars.Type.WHITE
      )
      .method31();
   private final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("bwHardcoreHearts").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field14 = (ToggleOption)OptionFactory.method7("bwHideFoodBar").method31();
   private final ToggleOption field15 = (ToggleOption)OptionFactory.method7("bwHideArmorBar").method31();
   private final ToggleOption field16 = (ToggleOption)OptionFactory.method7("customTrapAlert").method31();
   private final ToggleOption field17 = (ToggleOption)OptionFactory.method7("muteAlertSound").method31();
   private final ColorOption field18 = (ColorOption)((Data)OptionFactory.method8("alertPrimaryColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private final ColorOption field19 = (ColorOption)((Data)OptionFactory.method8("alertSubColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private final HypixelBedwarsStats field20 = new HypixelBedwarsStats(this);
   private final HypixelBedwarsResourceCounter field21 = new HypixelBedwarsResourceCounter(this);
   private final HypixelBedwarsUpgradeDisplay field22 = new HypixelBedwarsUpgradeDisplay(this);
   private final HypixelBedwarsTeamDisplay field23 = new HypixelBedwarsTeamDisplay(this);
   private final HypixelBedwarsTimers field24 = new HypixelBedwarsTimers(this);
   private boolean field25;
   private boolean field26;
   private final BedwarsTeamColorMapper field27 = new BedwarsTeamColorMapper(this);
   private boolean field28;
   private boolean field29 = false;
   private long field30 = -1L;
   private final Titles field31 = new TitlesHandler(this);
   private boolean field32 = false;
   private boolean field33 = false;
   private HypixelBedwars.Type field34 = null;

   protected boolean method24(String text1) {
      Set set2 = Set.of(
         "HYPIXEL_BEDWARS_HEIGHT_LIMIT_CHILD",
         "HYPIXEL_BEDWARS_RESOURCE_COUNTER_CHILD",
         "HYPIXEL_BEDWARS_UPGRADE_DISPLAY_CHILD",
         "HYPIXEL_BEDWARS_TEAM_DISPLAY_CHILD"
      );
      return set2.contains(text1);
   }

   public HypixelBedwars() {
      super(true);
      this.method36(ModTraits.field18, ConfigRangeBuilder::method16);
      LunarEventBus.method29().method2(EventServerBrand.class, this::method13);
      LunarEventBus.method29().method2(EventLocationChange.class, this::method14);
      LunarEventBus.method29().method2(EventTick.class, this::method10);
      this.handle(EventPlaySound.class, this::method11);
      this.handle(com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHud.Focused.class, this::method9);
      this.handle(com.moonsworth.lunar.client.event.mixin.highlight.EventRenderVanillaHud.class, this::method2);
      this.handle(EventRenderArmor.class, this::method3);
      this.method16();
   }

   private void method2(com.moonsworth.lunar.client.event.mixin.highlight.EventRenderVanillaHud highlightimpl21) {
      if ((Boolean)this.field14.get() && this.method21()) {
         highlightimpl21.setCancelled(true);
      }
   }

   private void method3(EventRenderArmor highlightimpl281) {
      if ((Boolean)this.field15.get() && this.method21()) {
         highlightimpl281.setCancelled(true);
      }
   }

   public void load(JsonObject json1) {
      super.load(json1);
      Ref.method4().method40().method35().method15().method1(this.field31);
   }

   public String getId() {
      return "HYPIXEL_BEDWARS";
   }

   protected List<Framework7Extension> method9() {
      return ImmutableList.of(this.field20, this.field21, this.field22, this.field23, this.field24);
   }

   public boolean method13() {
      return this.isEnabled() && (Boolean)this.field13.get() && this.field20.getUpdater().method14();
   }

   public void method14() {
      this.field30 = Ref.method3().bridge$getSystemTime();
      this.field29 = true;
   }

   public void method3(boolean flag1) {
      if (flag1 && !this.field9) {
         new Thread(() -> {
            Path path1x = LunarConstants.field12.resolve("hypixel/bedwars.json");
            if (path1x.toFile().exists()) {
               try {
                  String text2 = Files.readString(path1x);
                  JsonObject json3 = (JsonObject)LunarConstants.field22.fromJson(text2, JsonObject.class);
                  this.field8 = json3.getAsJsonObject("bed_locations");
               } catch (Exception exception4) {
                  CrashReporter.method5(exception4, "Load BedWars Data");
               }
            }
         }).start();
         this.field9 = true;
      }

      this.method16();
   }

   public boolean method15() {
      if (this.isEnabled() && (Boolean)this.field16.get() && this.method21()) {
         if (this.field18.method13() == -1 && this.field19.method13() == -1) {
            return false;
         }

         com.moonsworth.lunar.client.mod.render.titles.Titles titles1 = Ref.method4().method40().method35();
         if (titles1.isEnabled()) {
            return false;
         }

         Title title2 = this.mc.bridge$getGuiIngame().bridge$getTitle();
         if (title2 == null) {
            return false;
         }

         Integer number3 = this.field31.method1(title2.title(), null);
         Integer number4 = this.field31.method1(null, title2.subtitle());
         return number3 != null || number4 != null;
      } else {
         return false;
      }
   }

   private void method9(com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHud.Focused data1) {
      if (this.method15()) {
         Ref.method4()
            .method40()
            .method35()
            .method15()
            .method2(
               data1.method2(),
               this.mc.bridge$getGuiIngame().bridge$getTitle(),
               this.mc.bridge$getGuiIngame().bridge$titlesTimer(),
               0.0F,
               0.0F,
               data1.method3().method12(),
               data1.method3().method13(),
               false,
               true,
               true
            );
      }
   }

   private void method10(EventTick highlightimpl21) {
      if (this.field29) {
         if (Ref.method3().bridge$getSystemTime() - this.field30 < 6500L) {
            return;
         }

         Title title2 = this.mc.bridge$getGuiIngame().bridge$getTitle();
         if (title2 == null) {
            this.field29 = false;
         } else {
            String text3 = TextBridge.getTextContent(title2.title());
            if (text3.trim().isEmpty() || !text3.equals("ALARM!!!") && !text3.equals("TRAP TRIGGERED!")) {
               this.field29 = false;
            }
         }
      }
   }

   private void method11(EventPlaySound highlightimpl131) {
      if (this.field29 && (Boolean)this.field16.get() && (Boolean)this.field17.get()) {
         String text2 = highlightimpl131.getPath();
         if ((Ref.MC_VERSION > 1 || !text2.equals("note.pling"))
            && (Ref.MC_VERSION <= 1 || !text2.equals("block.note_block.pling"))) {
            if (Ref.MC_VERSION <= 1 && text2.equals("mob.endermen.portal")
               || Ref.MC_VERSION > 1 && text2.equals("entity.enderman.teleport")) {
               highlightimpl131.setCancelled(true);
            }
         } else {
            highlightimpl131.setCancelled(true);
         }
      }
   }

   public void method16() {
      boolean flag1 = this.isEnabled();
      boolean flag2 = flag1 && (Boolean)this.field10.get();
      boolean flag3 = flag2 && (Boolean)this.field11.get();
      HypixelBedwars.Type type4 = flag3 ? (HypixelBedwars.Type)this.field12.get() : null;
      if (flag2 != this.field32 || flag3 != this.field33 || type4 != this.field34) {
         this.method19();
         this.field27.method1();
      }

      this.field32 = flag2;
      this.field33 = flag3;
      this.field34 = type4;
   }

   private void method13(EventServerBrand highlightimpl121) {
      boolean flag2 = ServerUtils.isClientBrand(KeystrokesType.HYPIXEL.getBrand());
      if (this.field28 != flag2) {
         this.field28 = flag2;
         if (!this.field28) {
            this.field25 = false;
            this.field26 = false;
         }

         if (this.isEnabled()) {
            this.field27.method1();
            this.field20.onLocationUpdate(null);
            this.field21.setActive(null);
         }
      }
   }

   private void method14(EventLocationChange highlightimpl201) {
      HypixelLocation rewindhandlers22 = highlightimpl201.method2();
      if (rewindhandlers22 != null) {
         this.field25 = rewindhandlers22.field2.equalsIgnoreCase("BEDWARS");
         this.field26 = rewindhandlers22.field1.contains("lobby");
      } else {
         this.field25 = this.field26 = false;
      }

      if (this.isEnabled()) {
         if (rewindhandlers22 == null) {
            this.field27.method1();
            this.field20.onLocationUpdate(null);
            this.field21.setActive(null);
         } else {
            boolean flag3 = this.field27.method5();
            int[] items4 = this.field27.method6();
            this.field27.method1();
            this.field27.method4();
            boolean flag5 = this.field27.method8() && (flag3 != this.field27.method5() || !Arrays.equals(items4, this.field27.method6()));
            if (flag5) {
               this.method19();
            }

            this.field20.onLocationUpdate(rewindhandlers22);
            this.field21.setActive(rewindhandlers22);
         }
      }
   }

   public boolean method17() {
      if (!(Boolean)this.field11.get()) {
         return false;
      }

      HypixelLocation rewindhandlers21 = HypixelLocationListener.field7.method7();
      return "BEDWARS_PRACTICE".equals(rewindhandlers21.field3);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new OptionProvider[]{OptionFactory.method14("resetSession").method4(this.field20::resetSession)});
      lightingextension231.method1(
         "bedwarsOptions",
         arg1x -> {
            arg1x.method7(
                  this.field10,
                  arg1xx -> arg1xx.method7(
                     this.field11, arg1xxx -> arg1xxx.method9(new ClientOption[]{this.field12})
                  )
               )
               .method2(new int[]{1});
            arg1x.method9(new ClientOption[]{this.field13, this.field14, this.field15});
            arg1x.method7(
               this.field16, arg1xx -> arg1xx.method9(new ClientOption[]{this.field17, this.field18, this.field19})
            );
         }
      );
      this.field10.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1x -> this.method16());
      this.field11.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1x -> this.method16());
      this.field12.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1x -> this.method16());
   }

   protected ModDetails method20() {
      return ModDetails.method7()
         .method1(new ModCategory[]{ModCategory.field5})
         .method3(new String[]{"KAD7 (Stats)", "Pinkulu (Height)", "Moulberry (Beds)", "Sk1er (Hardcore)"})
         .method11(this);
   }

   private void method19() {
      Ref.method3().bridge$submit(() -> Ref.method3().bridge$getLevelRenderer().bridge$reloadChunks());
   }

   public boolean method21() {
      return this.method35() && !this.method36();
   }

   @Generated
   public JsonObject method22() {
      return this.field8;
   }

   @Generated
   public void method21(JsonObject json1) {
      this.field8 = json1;
   }

   @Generated
   public boolean method23() {
      return this.field9;
   }

   @Generated
   public ToggleOption method24() {
      return this.field10;
   }

   @Generated
   public EnumOption<HypixelBedwars.Type> method25() {
      return this.field12;
   }

   @Generated
   public ToggleOption method26() {
      return this.field13;
   }

   @Generated
   public ToggleOption method27() {
      return this.field14;
   }

   @Generated
   public ToggleOption method28() {
      return this.field15;
   }

   @Generated
   public ToggleOption method29() {
      return this.field16;
   }

   @Generated
   public ColorOption method30() {
      return this.field18;
   }

   @Generated
   public ColorOption method34() {
      return this.field19;
   }

   @Generated
   public boolean method35() {
      return this.field25;
   }

   @Generated
   public boolean method36() {
      return this.field26;
   }

   @Generated
   public BedwarsTeamColorMapper method37() {
      return this.field27;
   }

   @Generated
   public boolean method38() {
      return this.field28;
   }

   public enum Type implements OptionEnumValue {
      BLUE("blue"),
      CYAN("cyan"),
      GRAY("gray"),
      GREEN("green"),
      PINK("pink"),
      RED("red"),
      WHITE("white"),
      YELLOW("yellow");

      private final String id;

      public String id() {
         return this.id;
      }

      @Override
      public String toString() {
         return this.method21(this.id, new Object[0]);
      }

      @Generated
      Type(String text3) {
         this.id = text3;
      }
   }
}
