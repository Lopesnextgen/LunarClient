package com.moonsworth.lunar.client.config;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.Bridge3_8;
import com.moonsworth.lunar.bridge.Bridge4Extension_2;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.Bridge7_2;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.hitcolor.HitcolorExtension;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.client.config.FeatureFlag;
import com.moonsworth.lunar.client.heightlimit.Heightlimit;
import com.moonsworth.lunar.client.heightlimit.Heightlimit2;
import com.moonsworth.lunar.client.event.EventRegistrar;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.SettingsTreeAssembler;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.IntegerOption.Data;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.OptionDataProvider;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.driver.core.gui.Gui;
import com.moonsworth.lunar.client.driver.core.gui.JsonProviderLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.mod.render.overlay.OverlayMod;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.ichor.Annotation2;
import com.sun.management.HotSpotDiagnosticMXBean;
import java.lang.management.ManagementFactory;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.render.turbo.TurboEngineManager;

public class PerformanceSettings extends com.moonsworth.lunar.client.config.SettingsContainer implements Gui, JsonProviderLegacy, EventRegistrar {
   private final GuiIterator field2 = new GuiIterator();
   private final ToggleOption field3 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("renderRegions").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field4 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("fastNametags").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field5 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("turboEntityMode").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final IntegerOption field6 = (IntegerOption)((Data)((Data)OptionFactory.method4("turboEntityRate").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(2))
         .method7(2, 20))
      .method31();
   private final ToggleOption field7 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("turboBlockEntityMode").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final IntegerOption field8 = (IntegerOption)((Data)((Data)OptionFactory.method4("turboBlockEntityRate").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(2))
         .method7(2, 20))
      .method31();
   private final ToggleOption field9 = (ToggleOption)OptionFactory.method7("turboBlockEntityFar").method31();
   private final EnumOption<PerformanceSettings.Type> field10 = (EnumOption<PerformanceSettings.Type>)OptionFactory.method10(
         "lazyChunkLoading", PerformanceSettings.Type.MEDIUM
      )
      .method31();
   private final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("hudCaching").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final IntegerOption field12 = (IntegerOption)((Data)((Data)OptionFactory.method4("entities").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(48))
         .method7(16, 256))
      .method31();
   private final IntegerOption field13 = (IntegerOption)((Data)((Data)OptionFactory.method4("tileEntities").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(48))
         .method7(16, 256))
      .method31();
   private final ToggleOption field14 = (ToggleOption)OptionFactory.method7("particlePhysics").method31();
   private final ToggleOption field15 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("shouldLimitUnfocusedFps").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final IntegerOption field16 = (IntegerOption)((Data)((Data)OptionFactory.method4("limitFps").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(30))
         .method7(15, 300))
      .method31();
   private final IntegerOption field17 = (IntegerOption)((Data)((Data)OptionFactory.method4("menuFpsLimit").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(120))
         .method7(30, 500))
      .method31();
   private final ToggleOption field18 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("memorySavings").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field19 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("decoupledPresent").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field20 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("betterPipelining").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field21 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("noErrorContext").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field22 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("fasterPackLoading").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final Map<HitcolorExtension, Double> field23 = ThreadModuleDump63.MC_VERSION >= 6 ? null : new ConcurrentHashMap<>();
   private int field24;

   public PerformanceSettings() {
      if (ThreadModuleDump63.MC_VERSION < 6) {
         this.handle(EventClientTick.class, this::method6);
      }
   }

   @Override
   protected void method10(SettingsTreeAssembler var1) {
      ((SettingsSectionImpl)var1.method1("turboOptions", var1x -> {
         var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field5, var1xx -> var1xx.method9(new ClientOption[]{this.field6}));
         var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field7, var1xx -> var1xx.method9(new ClientOption[]{this.field8, this.field9}));
      })).method2(this::method37);
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(
         SettingsPage.GENERAL,
         var1x -> {
            ((SettingsSectionImpl)var1x.method9(new ClientOption[]{this.field19}).method2(Config.field40))
               .method2(() -> {
                  if (!Heightlimit2.method3().isSupported()) {
                     return true;
                  }

                  Bridge7_2 var0 = Bridge.method42().method85();
                  return !var0.field2.toLowerCase().contains("vulkan");
               });
            ((SettingsSectionImpl)var1x.method9(new ClientOption[]{this.field20}).method2(Config.field40))
               .method2(() -> !ThreadModuleDump63.method43());
            var1x.method9(new ClientOption[]{this.field22}).method6(35);
            var1x.method9(new ClientOption[]{this.field4}).method2(new int[]{1});
            if (!FeatureFlag.MEMORY_SAVINGS_DISABLED.isEnabled()) {
               var1x.method9(new ClientOption[]{this.field18}).OHROCHICOIOICHOCRROORRCIIICIHO(Config.field36);
            }

            ((SettingsSectionImpl)var1x.method9(new ClientOption[]{this.field3}).method3(new String[]{"optifine"}))
               .method6(1);
            var1x.method9(new ClientOption[]{this.field10}).method4(new String[]{"sodium"});
            var1x.method9(new ClientOption[]{this.field11, this.field15, this.field16, this.field17, this.field14});
            ((SettingsSectionImpl)var1x.method9(new ClientOption[]{this.field21}).HOCIIROHCHHIORICCRHIIRIIRCRCOR())
               .method2(() -> !ThreadModuleDump63.method43());
         }
      );
      var1.method1("entityOptions", var1x -> {
         var1x.method9(new ClientOption[]{this.field12}).OHHOOIIHIRCCCRCRRRCIICIHOOIRRH();
         var1x.method9(new ClientOption[]{this.field13});
      });
      this.field4.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var0 -> {
         if (!var0) {
            ThreadModuleDump63.method4().method98().onDisable();
         }
      });
      this.field3.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var0 -> {
         if (ThreadModuleDump63.MC_VERSION >= 1) {
            Bridge.method5().ifPresent(var1x -> {
               boolean var2 = var1x.getConfig().getRenderRegions();
               if (var2 != var0) {
                  var1x.getConfig().setRenderRegions(var0);
                  var1x.getConfig().updateLevelRenderer();
               }
            });
         }
      });
      if (!FeatureFlag.MEMORY_SAVINGS_DISABLED.isEnabled()) {
         this.field18.CICORRHIOIIOORRRICCORIOIOCIHII(var0 -> {
            HotSpotDiagnosticMXBean var1x = ManagementFactory.getPlatformMXBean(HotSpotDiagnosticMXBean.class);
            if (var0) {
               var1x.setVMOption("MinHeapFreeRatio", "30");
               var1x.setVMOption("MaxHeapFreeRatio", "40");
            } else {
               var1x.setVMOption("MaxHeapFreeRatio", "70");
               var1x.setVMOption("MinHeapFreeRatio", "40");
            }
         });
      }

      if (ThreadModuleDump63.MC_VERSION >= 8) {
         this.field5.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var1x -> {
            if (!this.method37()) {
               ThreadModuleDump63.method4().method89().method5(var1x);
            }
         });
         this.field7.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var1x -> {
            if (!this.method37()) {
               ThreadModuleDump63.method4().method89().method7(var1x);
            }
         });
         this.field6.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var1x -> {
            TurboEngineManager var2 = ThreadModuleDump63.method4().method89();
            if (!this.method37() && var2.method3()) {
               var2.method26().method17(var1x);
            }
         });
         this.field8.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var1x -> {
            TurboEngineManager var2 = ThreadModuleDump63.method4().method89();
            if (!this.method37() && var2.method6()) {
               var2.method27().method22(var1x);
            }
         });
         this.field9.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var1x -> {
            TurboEngineManager var2 = ThreadModuleDump63.method4().method89();
            if (!this.method37() && var2.method6()) {
               var2.method27().method21(!var1x);
               var2.clear();
            }
         });
      }

      this.field20.CICORRHIOIIOORRRICCORIOIOCIHII(Heightlimit::setEnabled);
   }

   @Annotation2(max = 5)
   public boolean method2(BridgeExtension var1) {
      if (var1 != ThreadModuleDump63.method7() && !(var1 instanceof Bridge6_10)) {
         if (Bridge.getMinecraftVersion() != Config.field1 || !(var1 instanceof Bridge3_8) && !(var1 instanceof Bridge4Extension_2)) {
            double var2 = ThreadModuleDump63.method3().bridge$getRenderViewEntity().method13(var1);
            float var4 = ((Integer)this.field12.get()).intValue();
            return !(var2 > var4 * var4);
         } else {
            return true;
         }
      } else {
         return true;
      }
   }

   @Annotation2(max = 5)
   public boolean method3(BridgeExtension var1, double var2, double var4, double var6) {
      if (var1 == null) {
         return true;
      }

      double var8 = var2 * var2 + var4 * var4 + var6 * var6;
      float var10 = ((Integer)this.field12.get()).intValue();
      return !(var8 > var10 * var10);
   }

   public boolean method4(@Nullable HitcolorExtension var1, Bridge3_23 var2) {
      OverlayMod var3 = ThreadModuleDump63.method4().method40().method84();
      if (var2 == Bridge.method34().method2() && var3.method41()) {
         return false;
      }

      if (var3.method40() && var2 != null && var2.bridge$isSkull()) {
         return false;
      }

      if (ThreadModuleDump63.MC_VERSION >= 6) {
         return true;
      }

      if (var1 == null) {
         return false;
      }

      Double var4 = this.field23.computeIfAbsent(var1, var0 -> {
         Vector3iBridge var1x = var0.bridge$getBlockPos();
         return ThreadModuleDump63.method3().bridge$getRenderViewEntity().method15(var1x.bridge$getX(), var1x.bridge$getY(), var1x.bridge$getZ());
      });
      float var5 = ((Integer)this.field13.get()).intValue();
      return !(var4 > var5 * var5);
   }

   public boolean method37() {
      return ThreadModuleDump63.MC_VERSION < 8 || Bridge.method5().isPresent() || !FeatureFlag.TURBO_ENGINE.isEnabled();
   }

   private void method6(EventClientTick var1) {
      if (this.field24 > 15) {
         this.field23.clear();
         this.field24 = 0;
      }

      this.field24++;
   }

   @Override
   public String method5() {
      return "performance.json";
   }

   @Nullable
   public JsonElement method128() {
      return this.provide();
   }

   public JsonElement provide() {
      JsonArray var1 = new JsonArray(this.method10().size());

      for (ClientOption var3 : this.method10()) {
         OptionDataProvider var4 = (OptionDataProvider)var3.HIRHCCHIRHRORIICOIHIHCICOIRHHC(OptionTraits.field10);
         if (var4 != null) {
            var1.add(var4.provide());
         }
      }

      return var1;
   }

   @Generated
   public ToggleOption method15() {
      return this.field3;
   }

   @Generated
   public ToggleOption method16() {
      return this.field4;
   }

   @Generated
   public ToggleOption method17() {
      return this.field5;
   }

   @Generated
   public IntegerOption method18() {
      return this.field6;
   }

   @Generated
   public ToggleOption method19() {
      return this.field7;
   }

   @Generated
   public IntegerOption method20() {
      return this.field8;
   }

   @Generated
   public ToggleOption method21() {
      return this.field9;
   }

   @Generated
   public EnumOption<PerformanceSettings.Type> method22() {
      return this.field10;
   }

   @Generated
   public ToggleOption method23() {
      return this.field11;
   }

   @Generated
   public IntegerOption method24() {
      return this.field12;
   }

   @Generated
   public IntegerOption method25() {
      return this.field13;
   }

   @Generated
   public ToggleOption method26() {
      return this.field14;
   }

   @Generated
   public ToggleOption method27() {
      return this.field15;
   }

   @Generated
   public IntegerOption method28() {
      return this.field16;
   }

   @Generated
   public IntegerOption method29() {
      return this.field17;
   }

   @Generated
   public ToggleOption method30() {
      return this.field18;
   }

   @Generated
   public ToggleOption method31() {
      return this.field19;
   }

   @Generated
   public ToggleOption method32() {
      return this.field20;
   }

   @Generated
   public ToggleOption method33() {
      return this.field21;
   }

   @Generated
   public ToggleOption method34() {
      return this.field22;
   }

   @Generated
   public Map<HitcolorExtension, Double> method35() {
      return this.field23;
   }

   @Generated
   public int getTicks() {
      return this.field24;
   }

   @Generated
   public GuiIterator method36() {
      return this.field2;
   }

   public enum Type implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
      NORMAL("offVanilla", 1, 2),
      HIGHEST("highest", 2, 6),
      HIGH("high", 5, 3),
      MEDIUM("medium", 10, 1),
      LOW("low", 15, 4),
      EXTREME_LOW("lowest", 30, 5);

      private final String id;
      private final int amount;
      private final int protoId;

      public String id() {
         return this.id;
      }

      @Override
      public String toString() {
         return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id, new Object[0]);
      }

      @Generated
      Type(String var3, int var4, int var5) {
         this.id = var3;
         this.amount = var4;
         this.protoId = var5;
      }

      @Generated
      public int getAmount() {
         return this.amount;
      }

      @Generated
      public int getProtoId() {
         return this.protoId;
      }
   }
}
