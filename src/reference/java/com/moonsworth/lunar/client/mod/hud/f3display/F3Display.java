package com.moonsworth.lunar.client.mod.hud.f3display;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.config.ModsSettings;
import com.moonsworth.lunar.client.framework.mod.AlertExtension;
import com.moonsworth.lunar.client.framework.mod.Calculator2Handler;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework6;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.framework.feature.f3display.F3display;
import com.moonsworth.lunar.client.framework.feature.f3display.F3display_3;
import com.moonsworth.lunar.client.framework.feature.f3display.Gui2Extension;
import com.moonsworth.lunar.client.framework.feature.f3display.mixin.F3displayImpl;
import com.moonsworth.lunar.client.framework.feature.f3display.mixin.F3displayImpl2;
import com.moonsworth.lunar.client.framework.feature.f3display.mixin.F3displayImpl3;
import com.moonsworth.lunar.client.framework.feature.f3display.mixin.F3displayImpl4;
import com.moonsworth.lunar.client.framework.feature.f3display.mixin.F3displayImpl5;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudBaseRenderEvent;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudLegacy;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.DoubleOption;
import com.moonsworth.lunar.client.config.option.OptionSupplier;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump65;
import com.moonsworth.lunar.client.util.Util_2;
import com.moonsworth.lunar.client.util.click.Click5;
import java.awt.Color;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import org.jetbrains.annotations.Nullable;

public class F3Display extends AbstractFeature {
   public static final F3displayImpl5 pingChartDisplay = new F3displayImpl5();
   public static final F3displayImpl4 ramChartDisplay = new F3displayImpl4();
   public static final F3displayImpl2 fpsChartDisplay = new F3displayImpl2();
   public static final F3displayImpl3 gpuChartDisplay = new F3displayImpl3();
   public static final F3displayImpl bandwidthChartDisplay = new F3displayImpl();
   public static final Map<Gui2Extension, BiConsumer<F3display_3, @Nullable F3DisplayModule>> extensionRenderers;
   protected static final Map<String, Gui2Extension> extensionById = new HashMap<>();
   protected final DoubleOption enterAnimationDuration = (DoubleOption)((DoubleOption.Data)((DoubleOption.Data)OptionFactory.method1(
               "enterAnimationDuration"
            )
            .OIRHOOIICOCIOOHICRRRICORIHHIHC(0.25))
         .method8(0.0, 1.0))
      .method31();
   protected final DoubleOption exitAnimationDuration = (DoubleOption)((DoubleOption.Data)((DoubleOption.Data)OptionFactory.method1(
               "exitAnimationDuration"
            )
            .OIRHOOIICOCIOOHICRRRICORIHHIHC(0.05))
         .method8(0.0, 1.0))
      .method31();
   protected final ToggleOption onlyMoversInF3 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("onlyMoversInF3")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   protected final F3Display.Data globalDisplayOptions = new F3Display.Data();
   private final Map<String, F3DisplayModule> modules = new LinkedHashMap<>();
   protected boolean pieChartVisible = false;
   protected boolean pingChartVisible = false;
   protected boolean ramChartVisible = false;
   protected boolean fpsChartVisible = false;
   protected boolean gpuChartVisible = false;

   public F3Display() {
      super(false);
      this.method18(Framework.field11, Framework6.method3().method2(false));
      this.method18(EventRenderHudLegacy.Data.class, var1 -> {
         if (this.fpsChartVisible) {
            field10.method4();
         }

         this.registerOptions(var1);
      }, 99);
   }

   @Override
   public void registerOptions(RootSettingsAssembler var1) {
      var1.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.enterAnimationDuration, this.exitAnimationDuration, this.onlyMoversInF3});
      var1.method1("globalDisplayOptions", this.globalDisplayOptions::method1);
      var1.method1(
         "modules", var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new OptionSupplier[]{OptionFactory.method14("addModule").method4(() -> {
            this.setEnabled(F3DisplayModule.create(this));
            LcuiScreen.method145();
         })})
      );
   }

   private void registerOptions(HudBaseRenderEvent var1) {
      boolean var2 = false;
      boolean var3 = false;

      for (F3DisplayModule var5 : this.modules.values()) {
         if (var5.isEnabled()) {
            var2 |= var5.method14().method4(var1);
            var5.method14().method5();
            var3 |= var5.method14().method19();
         }
      }

      if (var3) {
         this.updateChartVisibility();
      }

      if (!var2) {
         F3display.method29();
      } else {
         for (F3DisplayModule var7 : this.modules.values()) {
            var7.method14().method15();
            var7.method14().method16();
         }

         F3display.field1.clear();
      }
   }

   private void setEnabled(F3DisplayModule var1) {
      if (this.modules.putIfAbsent(var1.getId(), var1) == null) {
         ((AlertExtension)this.createDefaultModules(Framework.field5, var0 -> AlertExtension.method3())).method2(var1);
      }
   }

   public void reset(F3DisplayModule var1) {
      this.modules.remove(var1.getId());
      this.CCHOHROIOHHCCCOCIIOIRCHRROHHRC(Framework.field5).ifPresent(var2 -> {
         var2.HRORICORIHHHRICRIRCIIOHCRIRRHI(var1);
         if (var2.getChildren().isEmpty()) {
            this.method18(Framework.field5);
         }
      });
      LcuiScreen.method145();
      this.updateChartVisibility();
   }

   private void clearModules() {
      this.method18(Framework.field5);
      this.modules.clear();
      this.method5();
      this.updateChartVisibility();
   }

   protected void updateChartVisibility() {
      boolean var1 = false;
      boolean var2 = false;
      boolean var3 = false;
      boolean var4 = false;
      boolean var5 = false;

      for (F3DisplayModule var7 : this.modules.values()) {
         if (var7.method8(Gui2Extension.PIE_CHART)) {
            var1 = true;
         }

         if (var7.method8(Gui2Extension.PING_CHART)) {
            var2 = true;
         }

         if (var7.method8(Gui2Extension.RAM_CHART)) {
            var3 = true;
         }

         if (var7.method8(Gui2Extension.FPS_CHART)) {
            var4 = true;
         }

         if (var7.method8(Gui2Extension.GPU_CHART)) {
            var5 = true;
         }
      }

      if (var4 && !this.fpsChartVisible) {
         field10.method3();
      }

      this.ramChartVisible = var3;
      this.pieChartVisible = var1;
      this.pingChartVisible = var2;
      this.fpsChartVisible = var4;
      this.gpuChartVisible = var5;
   }

   public boolean isPieChartVisible() {
      return this.isEnabled() && this.pieChartVisible;
   }

   public boolean isPingChartVisible() {
      return ThreadModuleDump63.MC_VERSION >= 19 && this.isEnabled() && this.pingChartVisible;
   }

   public boolean isGpuChartVisible() {
      return this.isEnabled() && this.gpuChartVisible;
   }

   @Override
   public String getId() {
      return "F3_DISPLAY";
   }

   @Override
   public void save(JsonObject var1) {
      super.save(var1);
      if (!this.modules.isEmpty()) {
         JsonArray var2 = new JsonArray();

         for (F3DisplayModule var4 : this.modules.values()) {
            var2.add(var4.getId());
         }

         var1.add("modules", var2);
      }

      var1.addProperty("addedDefaultModules", true);
   }

   @Override
   public void load(JsonObject var1) {
      if (!this.modules.isEmpty()) {
         this.clearModules();
      }

      JsonElement var2 = var1.get("modules");
      if (var2 != null && var2.isJsonArray()) {
         for (JsonElement var5 : var2.getAsJsonArray()) {
            String var6 = var5.getAsString();
            F3DisplayModule var7 = F3DisplayModule.create(this, var6);
            this.setEnabled(var7);
         }
      }

      super.load(var1);
      if (!var1.has("addedDefaultModules") && this.modules.isEmpty()) {
         this.createDefaultModules().forEach(this::setEnabled);
      }

      this.updateChartVisibility();
   }

   @Override
   public void reset() {
      super.reset();
      this.clearModules();
      this.createDefaultModules().forEach(this::setEnabled);
      LcuiScreen.method145();
   }

   @Override
   protected ModDetails createDetails() {
      return ModDetails.method7().method1(Calculator2Handler.field3, Calculator2Handler.field4).method3("TreyRuffy", "cominixo").method11(this);
   }

   @Override
   public void setEnabled(boolean var1) {
      if (!var1) {
         F3display.method29();
      }
   }

   private List<F3DisplayModule> createDefaultModules() {
      Gui2Extension[] var1 = new Gui2Extension[]{Gui2Extension.MINECRAFT_VERSION, Gui2Extension.FPS, Gui2Extension.SERVER_INFO};
      Gui2Extension[] var2 = new Gui2Extension[]{Gui2Extension.BIOME, Gui2Extension.POSITION, Gui2Extension.LIGHT_LEVEL, Gui2Extension.CHUNK_POSITION};
      Gui2Extension[] var3 = new Gui2Extension[]{
         Gui2Extension.ENTITIES, Gui2Extension.SOUNDS, Gui2Extension.PARTICLES, Gui2Extension.DAY, Gui2Extension.RENDER_DISTANCE
      };
      Gui2Extension[] var4 = new Gui2Extension[]{Gui2Extension.GPU_UTILIZATION, Gui2Extension.MEMORY_USAGE, Gui2Extension.HARDWARE_INFO};
      Gui2Extension[] var5 = new Gui2Extension[]{Gui2Extension.TARGET_BLOCK, Gui2Extension.TARGET_FLUID, Gui2Extension.TARGET_ENTITY};
      Gui2Extension[] var6 = new Gui2Extension[]{Gui2Extension.PIE_CHART};
      byte var7 = 9;
      float var8 = 0.0F;
      F3DisplayModule var9 = this.isPieChartVisible("game_info", "Game Info", var1, -1504700, 0.0F, var8, HudAnchor.TOP_LEFT);
      var8 += 27 + var7;
      F3DisplayModule var10 = this.isPieChartVisible("player_info", "Player Info", var2, -14036200, 0.0F, var8, HudAnchor.TOP_LEFT);
      var8 += 72 + var7;
      F3DisplayModule var11 = this.isPieChartVisible("world_info", "World Info", var3, -16742145, 0.0F, var8, HudAnchor.TOP_LEFT);
      var8 = 0.0F;
      F3DisplayModule var12 = this.isPieChartVisible("system_specs", "System Specs", var4, -7263271, 0.0F, var8, HudAnchor.TOP_RIGHT);
      var8 += 72 + var7;
      F3DisplayModule var13 = this.isPieChartVisible("target_info", "Target Info", var5, -284416, 0.0F, var8, HudAnchor.TOP_RIGHT);
      float var14 = 0.5F;
      F3DisplayModule var15 = this.isPieChartVisible("pie_chart", "Pie Chart", var6, -9078785, 0.0F, -135.0F * var14, HudAnchor.BOTTOM_RIGHT);
      var15.getElement().setScale(var14);
      return List.of(var9, var10, var11, var12, var13, var15);
   }

   private F3DisplayModule isPieChartVisible(String var1, String var2, Gui2Extension[] var3, int var4, float var5, float var6, HudAnchor var7) {
      F3DisplayModule var8 = F3DisplayModule.create(this, "default_" + var1);
      var8.name.OIRHOOIICOCIOOHICRRRICORIHHIHC(var2);
      var8.field11.method1(Arrays.stream(var3).map(Gui2Extension::id).collect(Collectors.toSet()));
      var8.getElement().method20();
      var8.getDisplayOptions().field2.method10(true);
      var8.getDisplayOptions().field4.method1(Integer.valueOf(var4));
      var8.getDisplayOptions().method2();
      F3DisplayModule.Data var9 = var8.getElement();
      var9.method27(var7);
      var9.method1(var5, var6);
      return var8;
   }

   public static boolean shouldShowVanillaPieChart() {
      Client var0 = ThreadModuleDump63.method4();
      if (var0 == null) {
         return false;
      } else {
         ModsSettings var1 = var0.method40();
         if (var1 == null) {
            return false;
         } else {
            return var1.method95() == null ? false : var1.method95().method15();
         }
      }
   }

   static {
      for (Gui2Extension var3 : Gui2Extension.values()) {
         field14.put(var3.id(), var3);
      }

      field13 = ThreadModuleDump65.make(new EnumMap<>(Gui2Extension.class), var0 -> {
         var0.put(Gui2Extension.FPS, (var0x, var1) -> var0x.method2("FPS: ", F3display.method2()));
         var0.put(Gui2Extension.MINECRAFT_VERSION, F3display::method1);
         var0.put(Gui2Extension.BIOME, (var0x, var1) -> var0x.method2("Biome: ", F3display.method3()));
         var0.put(Gui2Extension.ENTITIES, (var0x, var1) -> var0x.method2("Entities: ", F3display.method20()));
         var0.put(Gui2Extension.CHUNKS, (var0x, var1) -> var0x.method2("Chunks: ", F3display.method21()));
         var0.put(Gui2Extension.PARTICLES, (var0x, var1) -> var0x.method2("Particles: ", F3display.method22()));
         var0.put(Gui2Extension.DAY, (var0x, var1) -> var0x.method2("Day: ", F3display.method19()));
         var0.put(Gui2Extension.LIGHT_LEVEL, (var0x, var1) -> var0x.method2("Client Light: ", F3display.method18()));
         var0.put(Gui2Extension.SOUNDS, F3display::method24);
         var0.put(Gui2Extension.BANDWIDTH, (var0x, var1) -> {
            field12.method3();
            var0x.method2("Bandwidth: ", F3display.method27());
         });
         var0.put(Gui2Extension.SERVER_INFO, (var0x, var1) -> var0x.method2("Server: ", F3display.method25()));
         var0.put(Gui2Extension.POST_EFFECT, (var0x, var1) -> {
            String var2 = F3display.method26();
            if (var2 == null && var0x.method7()) {
               var2 = "none";
            }

            var0x.method2("Post: ", var2);
         });
         var0.put(Gui2Extension.POSITION, (var0x, var1) -> {
            var0x.method2("XYZ: ", F3display.method4());
            var0x.method2("Block: ", F3display.method5());
            var0x.method2("Facing: ", F3display.method10());
            if (var1 == null || var1.field16.get()) {
               var0x.method2("World: ", F3display.method11());
            }
         });
         var0.put(Gui2Extension.CHUNK_POSITION, (var0x, var1) -> {
            String var2 = F3display.method6();
            if (var1 == null || var1.field17.get()) {
               String var3x = F3display.method7();
               if (var3x != null) {
                  var2 = var2 + " " + var3x;
               }
            }

            var0x.method2("Chunk: ", var2);
            var0x.method2("Section Relative: ", F3display.method8());
         });
         var0.put(Gui2Extension.TARGET_BLOCK, F3display.field1::method1);
         var0.put(Gui2Extension.TARGET_FLUID, F3display.field1::method2);
         var0.put(Gui2Extension.TARGET_ENTITY, F3display::method9);
         var0.put(Gui2Extension.RENDER_DISTANCE, F3display::method23);
         var0.put(Gui2Extension.GPU_UTILIZATION, (var0x, var1) -> {
            if (ThreadModuleDump63.MC_VERSION >= 12) {
               var0x.method2("GPU: ", F3display.method14());
            } else {
               var0x.method6("GPU: ", "1.19");
            }
         });
         var0.put(Gui2Extension.MEMORY_USAGE, F3display::method15);
         var0.put(Gui2Extension.HARDWARE_INFO, F3display::method17);
         var0.put(Gui2Extension.LUNAR_DEBUG, (var0x, var1) -> {
            if (var1 == null || var1.field29.get()) {
               Util_2.field1.method2(var0x::method2);
            }

            if (var1 == null || var1.field30.get()) {
               if (ThreadModuleDump63.MC_VERSION >= 8) {
                  ThreadModuleDump63.method4().method89().method12(var0x::method2);
               } else {
                  var0x.method6("[LC Turbo Entities]: ", "1.17");
               }
            }

            if (var1 == null || var1.field31.get()) {
               ThreadModuleDump63.method4().method96().method7().method7(var0x::method2);
            }

            var0x.method2("Textures: ", String.valueOf(Click5.method10().method7()));
            var0x.method2("Buffers: ", String.valueOf(Click5.method10().method8()));
         });
         var0.put(Gui2Extension.PIE_CHART, (var0x, var1) -> {
            List var2 = F3display.method28();
            var0x.method4(var1 != null && var1.field27.get(), var2);
         });
         var0.put(Gui2Extension.PING_CHART, (var0x, var1) -> var0x.method5(field8));
         var0.put(Gui2Extension.RAM_CHART, (var0x, var1) -> {
            field9.method3();
            field9.method4(var1 != null && var1.field28.get());
            var0x.method5(field9);
         });
         var0.put(Gui2Extension.FPS_CHART, (var0x, var1) -> var0x.method5(field10));
         var0.put(Gui2Extension.GPU_CHART, (var0x, var1) -> {
            field11.method3();
            var0x.method5(field11);
         });
         var0.put(Gui2Extension.BANDWIDTH_CHART, (var0x, var1) -> {
            field12.method3();
            var0x.method5(field12);
         });
      });
   }

   public static class Data {
      public final ToggleOption textShadow = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("textShadow")
            .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
         .method31();
      public final ToggleOption background = (ToggleOption)OptionFactory.method7("background").method31();
      public final ColorOption backgroundColor = (ColorOption)((ColorOption.Data)OptionFactory.method8("backgroundColor")
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1426063360))
         .method31();
      public final ColorOption labelColor = (ColorOption)((ColorOption.Data)OptionFactory.method8("labelColor")
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
         .method31();
      public final ColorOption valueColor = (ColorOption)((ColorOption.Data)OptionFactory.method8("valueColor")
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
         .method31();

      public void save(RootSettingsAssembler.Data var1) {
         var1.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.textShadow, this.background});
         var1.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.backgroundColor}).method3(() -> !this.background.get());
         var1.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.labelColor, this.valueColor});
         var1.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new OptionSupplier[]{OptionFactory.method14("matchColors").method4(this::registerOptions)});
      }

      private void registerOptions() {
         int var1 = this.labelColor.method13();
         Color var2 = new Color(var1);
         float[] var3 = Color.RGBtoHSB(var2.getRed(), var2.getGreen(), var2.getBlue(), null);
         var3[0] += 0.03F;
         var3[1] *= 0.625F;
         var3[2] *= 1.176F;
         if (var3[2] > 1.0F) {
            var3[2] = 1.0F;
         }

         Color var4 = Color.getHSBColor(var3[0], var3[1], var3[2]);
         this.valueColor.method1(Integer.valueOf(var4.getRGB()));
      }
   }
}
