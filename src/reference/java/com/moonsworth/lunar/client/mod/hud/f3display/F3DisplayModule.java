package com.moonsworth.lunar.client.mod.hud.f3display;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.MathHelperBridge;
import com.moonsworth.lunar.client.framework.ReducedDebugInfoNotifier;
import com.moonsworth.lunar.client.ui.HoverAnimation;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework4;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudElementBase;
import com.moonsworth.lunar.client.framework.feature.f3display.F3display$Data2;
import com.moonsworth.lunar.client.framework.feature.f3display.F3display$Data3;
import com.moonsworth.lunar.client.framework.feature.f3display.F3display$Data4;
import com.moonsworth.lunar.client.framework.feature.f3display.F3display2;
import com.moonsworth.lunar.client.framework.feature.f3display.F3display_3;
import com.moonsworth.lunar.client.framework.feature.f3display.Gui2Extension;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudBaseRenderEvent;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.MultiSelectOption;
import com.moonsworth.lunar.client.config.option.TextOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.OptionSupplier;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.awt.Color;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public class F3DisplayModule extends AbstractFeature {
   private final F3DisplayModule.Data element = new F3DisplayModule.Data(0.0F, 0.0F, HudAnchor.TOP_LEFT);
   protected final TextOption field9 = (TextOption)((TextOption.Data)OptionFactory.method12("name")
         .method2("New Module"))
      .method31();
   protected final ToggleOption field10 = (ToggleOption)OptionFactory.method7("alwaysShow").method31();
   protected final MultiSelectOption field11 = (MultiSelectOption)OptionFactory.method27("elements").method3(Gui2Extension.ids()).method6(var0 -> {
      Gui2Extension var1x = F3Display.extensionById.get(var0);
      return var1x == null ? var0 : var1x.getDisplay();
   }).method5(var1x -> this.element.method20()).method31();
   protected final ToggleOption field12 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("autoAlign")
         .method4(true))
      .method31();
   protected final EnumOption<com.moonsworth.lunar.client.ui.hud.row.Gui2Extension> field13 = (EnumOption<com.moonsworth.lunar.client.ui.hud.row.Gui2Extension>)OptionFactory.method10(
         "alignment", com.moonsworth.lunar.client.ui.hud.row.Gui2Extension.LEFT
      )
      .method31();
   protected final ToggleOption field14 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("overrideDisplayOptions")
         .method4(true))
      .method31();
   protected final ToggleOption field15 = (ToggleOption)OptionFactory.method7("showClientBrand").method31();
   protected final ToggleOption field16 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("showWorldName")
         .method4(true))
      .method31();
   protected final ToggleOption field17 = (ToggleOption)OptionFactory.method7("showRegionFile").method31();
   protected final ToggleOption field18 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("showProperties")
         .method4(true))
      .method31();
   protected final ToggleOption field19 = (ToggleOption)OptionFactory.method7("showTags").method31();
   protected final ToggleOption showFluidProperties = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("showProperties")
         .method4(true))
      .method31();
   protected final ToggleOption showFluidTags = (ToggleOption)OptionFactory.method7("showTags").method31();
   protected final ToggleOption showEmpty = (ToggleOption)OptionFactory.method7("showEmpty").method31();
   protected final ToggleOption showSimulationDistance = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("showSimulationDistance")
         .method4(true))
      .method31();
   protected final ToggleOption showMood = (ToggleOption)OptionFactory.method7("showMood").method31();
   protected final ToggleOption showAllocationRate = (ToggleOption)OptionFactory.method7("showAllocationRate").method31();
   protected final ToggleOption showGlobalPercent = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("showGlobalPercent")
         .method4(true))
      .method31();
   protected final ToggleOption useCustomColor = (ToggleOption)OptionFactory.method7("useCustomColor").method31();
   protected final ToggleOption alignToMaxRam = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("alignToMaxRam")
         .method4(true))
      .method31();
   protected final ToggleOption showLunarResources = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("showLunarResources")
         .method4(true))
      .method31();
   protected final ToggleOption showLunarTurbo = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("showLunarTurbo")
         .method4(true))
      .method31();
   protected final ToggleOption showLunarJIT = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("showLunarJIT")
         .method4(true))
      .method31();
   private final F3Display.Data ownDisplayOptions = new F3Display.Data();
   private final F3Display.Data globalDisplayOptions;

   protected F3DisplayModule(F3Display var1) {
      super(true);
      this.globalDisplayOptions = var1.globalDisplayOptions;
      this.create(Framework.field16, Framework4.method5(null, var1));
      this.create(Framework.field1, this.element);
   }

   public F3Display.Data getDisplayOptions() {
      return this.overrideDisplayOptions.get() ? this.ownDisplayOptions : this.globalDisplayOptions;
   }

   @Override
   public void registerOptions(RootSettingsAssembler var1) {
      var1.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.name, this.autoAlign, this.alwaysShow});
      ((SettingsSectionImpl)var1.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.alignment})).method2(this.autoAlign::get);
      var1.method3(this.overrideDisplayOptions, this.ownDisplayOptions::method1);
      var1.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new OptionSupplier[]{this.elements, OptionFactory.method14("remove").method4(() -> {
         F3Display var1x = ((Framework4)this.create(Framework.field16)).method1();
         var1x.reset(this);
      })});
      this.alignment.CICORRHIOIIOORRRICCORIOIOCIHII(var1x -> this.element.method22());
      this.autoAlign.CICORRHIOIIOORRRICCORIOIOCIHII(var1x -> this.element.method22());
      this.registerExtensionOptions(Gui2Extension.MINECRAFT_VERSION, var1, var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.showClientBrand}));
      this.registerExtensionOptions(Gui2Extension.POSITION, var1, var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.showWorldName}));
      this.registerExtensionOptions(Gui2Extension.CHUNK_POSITION, var1, var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.showRegionFile}));
      this.registerExtensionOptions(Gui2Extension.TARGET_BLOCK, var1, var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.showBlockProperties, this.showBlockTags}));
      this.registerExtensionOptions(
         Gui2Extension.TARGET_FLUID, var1, var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.showFluidProperties, this.showFluidTags, this.showEmpty})
      );
      this.registerExtensionOptions(Gui2Extension.RENDER_DISTANCE, var1, var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.showSimulationDistance}));
      this.registerExtensionOptions(Gui2Extension.SOUNDS, var1, var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.showMood}));
      this.registerExtensionOptions(Gui2Extension.MEMORY_USAGE, var1, var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.showAllocationRate}));
      this.registerExtensionOptions(Gui2Extension.PIE_CHART, var1, var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.showGlobalPercent, this.useCustomColor}));
      this.registerExtensionOptions(Gui2Extension.RAM_CHART, var1, var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.alignToMaxRam}));
      this.registerExtensionOptions(
         Gui2Extension.LUNAR_DEBUG, var1, var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.showLunarResources, this.showLunarTurbo, this.showLunarJIT})
      );
   }

   private void registerExtensionOptions(Gui2Extension var1, RootSettingsAssembler var2, Consumer<RootSettingsAssembler.Data> var3) {
      ((SettingsSectionImpl)var2.method3(var1::getDisplay, var3)).method2(() -> !this.element.field10.contains(var1));
   }

   @Override
   public String getId() {
      throw new IllegalStateException("F3DisplayModule must be created using F3DisplayModule.create()!");
   }

   @Override
   protected ModDetails createDetails() {
      return ModDetails.method7().method4(this.name::get).method8().method11(this);
   }

   @Override
   public void load(JsonObject var1) {
      super.load(var1);
      this.element.method20();
      this.element.method22();
   }

   @Override
   public void registerExtensionOptions(boolean var1) {
      if (!var1) {
         this.element.field14 = false;
      }
   }

   public static F3DisplayModule create(F3Display var0) {
      String var1 = "F3_MODULE_" + System.currentTimeMillis();
      F3DisplayModule var2 = create(var0, var1);
      float var3 = new Random(var1.hashCode()).nextFloat(1.0F);
      Color var4 = Color.getHSBColor(var3, 0.8F, 0.85F);
      Color var5 = Color.getHSBColor(var3 + 0.03F, 0.5F, 1.0F);
      var2.field32.field4.method1(Integer.valueOf(var4.getRGB()));
      var2.field32.field5.method1(Integer.valueOf(var5.getRGB()));
      return var2;
   }

   public static F3DisplayModule create(F3Display var0, final String var1) {
      return new F3DisplayModule(var0) {
         @Override
         public String getId() {
            return var1;
         }
      };
   }

   public boolean isElementEnabled(Gui2Extension var1) {
      return this.element.field10.contains(var1) && this.element.field13;
   }

   @Generated
   public F3DisplayModule.Data getElement() {
      return this.element;
   }

   @Generated
   public ToggleOption getShowBlockProperties() {
      return this.showBlockProperties;
   }

   @Generated
   public ToggleOption getShowBlockTags() {
      return this.showBlockTags;
   }

   @Generated
   public ToggleOption getShowFluidProperties() {
      return this.showFluidProperties;
   }

   @Generated
   public ToggleOption getShowFluidTags() {
      return this.showFluidTags;
   }

   @Generated
   public ToggleOption getShowEmpty() {
      return this.showEmpty;
   }

   @Generated
   public ToggleOption getShowGlobalPercent() {
      return this.showGlobalPercent;
   }

   public class Data extends HudElementBase {
      private com.moonsworth.lunar.client.ui.hud.row.Gui2Extension field9 = com.moonsworth.lunar.client.ui.hud.row.Gui2Extension.LEFT;
      private Set<Gui2Extension> field10 = EnumSet.noneOf(Gui2Extension.class);
      private final HoverAnimation field11 = new HoverAnimation(0L);
      private float field12 = 0.0F;
      private boolean field13 = false;
      private boolean field14 = false;
      private boolean field15 = false;
      private F3display2 field16;
      private boolean dirty = false;
      private float field17 = 1.0F;
      private double field18 = 0.0;
      private double field19 = 0.0;

      public Data(float var2, float var3, @NotNull HudAnchor var4) {
         super(var2, var3, var4);
      }

      @Override
      public void setAnchor(@NotNull HudAnchor var1) {
         super.setAnchor(var1);
         this.getShowGlobalPercent();
      }

      @Override
      public void resetPosition() {
      }

      @Override
      public void registerExtensionOptions(HudBaseRenderEvent var1, float var2, float var3, boolean var4) {
      }

      public boolean prepareRender(HudBaseRenderEvent var1) {
         if (!this.overrideDisplayOptions) {
            return false;
         }

         this.showRegionFile = this.getScale();
         this.showBlockProperties = var1.method3().method10();
         this.showBlockTags = var1.method3().method11();
         this.setAnchor(var1.method3().method5());
         float var2 = this.ICRIHRIORRCRCOOCCCHHRIRICCHHII();
         float var3 = this.RIIIOCHHCIHOIOROOOHRIRICCCCHHC();
         float var4 = var2;
         float var5 = var3;
         if (this.elements.CRCCOHOHCCRHRIHCROCCRICIRRICRH()) {
            double var6 = this.elements.HRIOICORHOROOICOOHIOIOCOHRROHR();
            HudAnchor var8 = this.IIRHROCCOCCRIOCCIHRHRRCHICOHIH();
            if (var8.getHorizontal() == com.moonsworth.lunar.client.ui.hud.HudPlacement.MIDDLE) {
               if (var8.getVertical() == com.moonsworth.lunar.client.ui.hud.HudPlacement.TOP) {
                  var5 = -this.autoAlign;
               } else {
                  var5 = (float)this.showBlockTags / this.getScale();
               }

               var5 = (float)MathHelperBridge.lerp(var6, var5, var3);
            } else {
               if (var8.getHorizontal() == com.moonsworth.lunar.client.ui.hud.HudPlacement.LEFT) {
                  var4 = -this.autoAlign;
               } else {
                  var4 = (float)this.showBlockProperties / this.getScale();
               }

               var4 = (float)MathHelperBridge.lerp(var6, var4, var2);
            }
         } else if (!this.getElement(this.alignment, this.showClientBrand)) {
            return false;
         }

         MixinHelper_4 var11 = var1.method2();
         this.showWorldName = new F3display2(var11, F3DisplayModule.this, var4, var5, this.showClientBrand);
         return true;
      }

      public void renderContent() {
         if (this.showWorldName != null) {
            F3display$Data3 var1 = F3display_3.method10(this.showWorldName);
            this.getShowFluidProperties();

            try {
               this.renderLines(var1);
            } finally {
               this.pop();
            }
         }
      }

      public void getShowBlockProperties() {
         if (this.showWorldName != null) {
            this.showWorldName.method11();
            F3display$Data2 var1 = F3display_3.method8(this.showWorldName);
            this.getShowFluidProperties();

            try {
               this.renderLines(var1);
            } finally {
               this.pop();
            }
         }
      }

      public void getShowBlockTags() {
         if (this.showWorldName != null) {
            this.method58(this.showWorldName.method10(), this.showWorldName.method9());
         }

         this.showWorldName = null;
      }

      private void getShowFluidProperties() {
         MixinHelper_4 var1 = this.showWorldName.method13();
         var1.push();
         var1.scale(this.showRegionFile, this.showRegionFile, 1.0F);
      }

      private void pop() {
         this.showWorldName.method13().pop();
      }

      private void renderLines(F3display_3 var1) {
         boolean var2 = var1.method7();
         boolean var3 = ReducedDebugInfoNotifier.method1();

         for (Gui2Extension var5 : this.alwaysShow) {
            if (!var5.isAllowed(var3, var2)) {
               if (var2) {
                  var1.method2(var5.getDisplay() + ": ", "Reduced");
               }
            } else {
               BiConsumer var6 = F3Display.extensionRenderers.get(var5);
               if (var6 != null) {
                  var6.accept(var1, F3DisplayModule.this);
               }
            }
         }
      }

      protected void measureLines(boolean var1) {
         F3display$Data4 var2 = F3display_3.method9(var1);

         for (Gui2Extension var4 : this.alwaysShow) {
            BiConsumer var5 = F3Display.extensionRenderers.get(var4);
            if (var5 != null) {
               var5.accept(var2, F3DisplayModule.this);
            }
         }

         this.method58(var2.getWidth(), var2.getHeight());
      }

      @Override
      public boolean method31() {
         return false;
      }

      @Override
      public boolean method30() {
         boolean var1 = ThreadModuleDump63.method4().method40().method95().field17.get();
         if (!var1) {
            return true;
         } else {
            return F3DisplayModule.this.alwaysShow.get() ? true : ThreadModuleDump63.method3().bridge$getGameSettings().bridge$showDebugInfo();
         }
      }

      @Override
      public boolean prepareRender(boolean var1) {
         boolean var2 = ThreadModuleDump63.method3().bridge$getGameSettings().bridge$showDebugInfo() || F3DisplayModule.this.alwaysShow.get();
         if (this.alignment != var2) {
            this.alignment = var2;
            this.dirty = true;
            this.elements.stop();
            F3Display var3 = ThreadModuleDump63.method4().method40().method95();
            double var4 = this.alignment ? var3.enterAnimationDuration.get() : var3.exitAnimationDuration.get();
            if (var4 > 0.0) {
               this.elements.setDurationMs((long)(var4 * 1000.0));
               this.elements.ICRIOHCOCRHIHHHOOIIHHOIRRIHIHC(!this.alignment);
               this.elements.start();
               this.measureLines(var1);
               if (this.IIRHROCCOCCRIOCCIHRHRRCHICOHIH().getHorizontal() == com.moonsworth.lunar.client.ui.hud.HudPlacement.MIDDLE) {
                  this.autoAlign = this.getHeight();
               } else {
                  this.autoAlign = this.getWidth();
               }
            }
         }

         this.showWorldName = null;
         this.overrideDisplayOptions = this.getElement(var2, var1);
         this.showClientBrand = var1;
         return false;
      }

      private boolean getElement(boolean var1, boolean var2) {
         if (var1 || this.elements.CRCCOHOHCCRHRIHCROCCRICIRRICRH()) {
            return true;
         } else if (var2) {
            boolean var3 = ThreadModuleDump63.method4().method40().method95().field17.get();
            return !var3;
         } else {
            return false;
         }
      }

      public boolean getShowFluidTags() {
         boolean var1 = this.dirty;
         this.dirty = false;
         return var1;
      }

      public void createDetails() {
         EnumSet var1 = EnumSet.noneOf(Gui2Extension.class);
         var1.addAll(F3DisplayModule.this.elements.get().stream().map(Gui2Extension::getFromId).filter(Objects::nonNull).toList());
         this.alwaysShow = var1;
         ThreadModuleDump63.method4().method40().method95().method14();
      }

      public void getShowGlobalPercent() {
         if (F3DisplayModule.this.autoAlign.get()) {
            com.moonsworth.lunar.client.ui.hud.HudPlacement var1 = this.IIRHROCCOCCRIOCCIHRHRRCHICOHIH().getHorizontal();
            if (var1 == com.moonsworth.lunar.client.ui.hud.HudPlacement.RIGHT) {
               this.name = com.moonsworth.lunar.client.ui.hud.row.Gui2Extension.RIGHT;
            } else if (var1 == com.moonsworth.lunar.client.ui.hud.HudPlacement.MIDDLE) {
               this.name = com.moonsworth.lunar.client.ui.hud.row.Gui2Extension.CENTER;
            } else {
               this.name = com.moonsworth.lunar.client.ui.hud.row.Gui2Extension.LEFT;
            }
         } else {
            this.name = (com.moonsworth.lunar.client.ui.hud.row.Gui2Extension)F3DisplayModule.this.alignment.get();
         }
      }

      @Generated
      public com.moonsworth.lunar.client.ui.hud.row.Gui2Extension getAlignment() {
         return this.name;
      }
   }
}
