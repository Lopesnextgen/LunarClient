package com.moonsworth.lunar.client.mod.render.lightoverlay;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge14_3;
import com.moonsworth.lunar.bridge.Bridge2_17;
import com.moonsworth.lunar.bridge.Bridge2_43;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.Heightlimit;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.framework.mod.Calculator2Handler;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.lightoverlay.Lightoverlay2;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorldTimeUpdate;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.event.mixin.highlight.SetupTerrainEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.ChunkReloadEvent;
import com.moonsworth.lunar.client.config.option.OptionEnumValue;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.OptionSupplier;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class LightOverlay extends AbstractFeature {
   private final IntegerOption renderRangeLimit = (IntegerOption)((IntegerOption.Data)((IntegerOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method4(
               "renderRangeLimit"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(2))
         .method7(1, 12))
      .method31();
   private final ModifierKeybindOption toggleKeybind = (ModifierKeybindOption)com.moonsworth.lunar.client.config.option.OptionFactory.method18("toggleKeybind")
      .method5(KeyCode.KEY_NONE)
      .method31();
   private final ToggleOption fastUpdates = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("fastUpdates")
      .method31();
   private final ToggleOption culling = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "culling"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption includeSkyLight = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "includeSkyLight"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption hideUnspawnableLight = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("hideUnspawnableLight")
      .method31();
   private final ToggleOption customLightThreshold = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("customLightThreshold")
      .method31();
   private final IntegerOption threshold = (IntegerOption)((IntegerOption.Data)((IntegerOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method4(
               "threshold"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(15))
         .method7(0, 15))
      .method31();
   protected final EnumOption<LightOverlay.Type> lightOverlayMode = (EnumOption<LightOverlay.Type>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "lightOverlayMode", LightOverlay.Type.CROSS
      )
      .method31();
   protected final ToggleOption showLightValue = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("showLightValue")
      .method31();
   protected final FloatOption crossThickness = (FloatOption)((FloatOption.Data)((FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "crossThickness"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(2.0F))
         .method8(0.5F, 10.0F))
      .method31();
   private final ToggleOption dynamicColor = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("lightOverlayDynamicColor")
      .method31();
   private final ColorOption brightColor = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "brightColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16711936))
      .method31();
   private final ColorOption darkColor = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "darkColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-65536))
      .method31();
   protected final ColorOption textColor = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "textColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   @Nullable
   private Lightoverlay2 state = null;
   private final AtomicBoolean chunkUpdateInProgress = new AtomicBoolean(false);
   private final AtomicBoolean blockScanInProgress = new AtomicBoolean(false);
   private final ExecutorService executor = Executors.newFixedThreadPool(
      2,
      new ThreadFactoryBuilder()
         .setDaemon(true)
         .setNameFormat("Light Overlay Executor %d")
         .setUncaughtExceptionHandler((var0, var1) -> Slayer.method9(var1, "Uncaught error on thread %s!", var0.getName()))
         .build()
   );
   private volatile boolean needsUpdate = true;
   private long lastUpdate = -1L;
   private boolean overlayEnabled = true;
   private long worldTime;
   private int skylightSubtract;

   public LightOverlay() {
      super(false);
      this.handle(EventWorldTimeUpdate.class, this::onWorldTime);
      this.method17(EventClientTick.class, this::method14);
      this.method17(ChunkReloadEvent.class, this::method15);
      this.method17(SetupTerrainEvent.class, this::method16);
      this.handle(com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent.class, this::onRenderNameplate);
      this.toggleKeybind.method3(() -> {
         this.overlayEnabled = !this.overlayEnabled;
         ThreadModuleDump63.method4().method40().OHOOCIIHRRIRCHOIIHHROORHIOIORC();
      });
   }

   public static boolean method13() {
      return ThreadModuleDump63.method4().method40().method92().isEnabled();
   }

   @Override
   public String getId() {
      return "LIGHT_OVERLAY";
   }

   @Override
   protected ModDetails method20() {
      return ModDetails.method7().method1(Calculator2Handler.field3).method11(this);
   }

   @Override
   public void method3(boolean var1) {
      if (this.state != null) {
         this.state = null;
         System.gc();
      }

      Bridge14_3 var2 = this.mc.bridge$getLevelRenderer();
      if (var2 != null) {
         var2.bridge$reloadChunks();
      }
   }

   @Override
   public void method2(RootSettingsAssembler var1) {
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(
         SettingsPage.GENERAL,
         var1x -> {
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
               new OptionSupplier[]{
                  this.toggleKeybind,
                  com.moonsworth.lunar.client.config.option.OptionFactory.method15("")
                     .method8(() -> this.method17(this.overlayEnabled ? "stateEnabled" : "stateDisabled", new Object[0]))
               }
            );
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.renderRangeLimit, this.fastUpdates, this.culling, this.includeSkyLight});
            var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.hideUnspawnableLight,
               var1xx -> var1xx.HORHROIOIOICIRHIOCOICHHHIHCIIO(
                  this.customLightThreshold, var1xxx -> var1xxx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.threshold})
               )
            );
         }
      );
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(
         SettingsPage.RENDER,
         var1x -> {
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.lightOverlayMode, this.showLightValue});
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.crossThickness}).method3(() -> this.lightOverlayMode.get() != LightOverlay.Type.CROSS);
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.textColor})
               .method3(() -> !this.showLightValue.get() || this.lightOverlayMode.get() == LightOverlay.Type.NONE);
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.brightColor, this.darkColor, this.dynamicColor});
         }
      );
   }

   @Override
   public void method1(JsonObject var1) {
      super.method1(var1);
      var1.addProperty("enabledToggle", this.overlayEnabled);
   }

   @Override
   public void load(JsonObject var1) {
      super.load(var1);
      JsonElement var2 = var1.get("enabledToggle");
      if (var2 != null && var2.isJsonPrimitive()) {
         this.overlayEnabled = var2.getAsBoolean();
      }
   }

   @Override
   protected boolean method23(String var1) {
      return "enabledToggle".equals(var1);
   }

   private void onWorldTime(EventWorldTimeUpdate var1) {
      this.worldTime = Math.abs(var1.getWorldTime());
   }

   private void method14() {
      Bridge5Extension_5 var1 = this.mc.bridge$getPlayer();
      Itemcounter6Extension var2 = this.mc.bridge$getWorld();
      Bridge14_3 var3 = this.mc.bridge$getLevelRenderer();
      if (var1 != null && var2 != null && var3 != null) {
         if (this.overlayEnabled) {
            if (this.state == null) {
               this.state = new Lightoverlay2();
            }

            if (ThreadModuleDump63.MC_VERSION > 35 && this.mc.bridge$getIntegratedServer() != null) {
               this.worldTime = var2.bridge$getDayTimeline();
            }

            if (!this.includeSkyLight.get()) {
               this.skylightSubtract = -1;
            } else {
               this.skylightSubtract = var1.bridge$getDimension() == 0 ? var2.bridge$calculateSkylightSubtract(this.worldTime) : 0;
            }

            if ((this.fastUpdates.get() || this.lastUpdate == -1L || System.currentTimeMillis() - this.lastUpdate >= 500L)
               && this.needsUpdate
               && this.chunkUpdateInProgress.compareAndSet(false, true)) {
               this.needsUpdate = false;
               Lightoverlay2 var4 = this.state;
               Heightlimit var5 = Bridge.method7().orElse(null);
               if (var5 != null) {
                  var4.method1(var5.bridge$getRenderChunks());
               } else {
                  var4.method1(var3.bridge$getRenderChunks());
               }

               this.executor.execute(() -> {
                  try {
                     var4.method2(this, var2);
                  } finally {
                     this.chunkUpdateInProgress.set(false);
                  }
               });
               this.lastUpdate = System.currentTimeMillis();
            }

            if ((this.fastUpdates.get() || EventClientTick.field1 % 2 == 0) && this.blockScanInProgress.compareAndSet(false, true)) {
               int var14 = var1.bridge$getBlockX();
               int var15 = var1.bridge$getBlockY();
               int var6 = var1.bridge$getBlockZ();
               boolean var7 = this.hideUnspawnableLight.get();
               boolean var8 = this.customLightThreshold.get();
               int var9 = this.threshold.get();
               int var10 = this.renderRangeLimit.get();
               int var11 = this.skylightSubtract;
               boolean var12 = this.culling.get();
               Lightoverlay2 var13 = this.state;
               this.executor.execute(() -> {
                  try {
                     var13.method3(var14, var15, var6, var2, var12 ? var3::bridge$isBlockVisible : null, var7, var8, var9, var10, var11);
                  } finally {
                     this.blockScanInProgress.set(false);
                  }
               });
            }
         }
      } else {
         if (this.state != null) {
            this.state = null;
            System.gc();
         }

         this.needsUpdate = true;
      }
   }

   private void method15() {
      this.state = null;
      this.needsUpdate = true;
   }

   private void method16() {
      if (this.chunkUpdateInProgress.get()) {
         this.needsUpdate = true;
      }
   }

   private void onRenderNameplate(com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent var1) {
      Bridge2_43 var2 = this.mc.bridge$getEntityRenderDispatcher();
      if (var2 != null && this.state != null && this.overlayEnabled) {
         this.state.method4(var1.method3(), var2, this);
      }
   }

   public boolean canMonstersSpawn(Horsestats20Extension2 var1, Bridge2_17 var2) {
      Itemcounter6Extension var3 = this.mc.bridge$getWorld();
      return var3 != null && this.method14(var2) ? var3.bridge$canMonstersSpawn(var1) : false;
   }

   public boolean method13(Horsestats20Extension2 var1, Bridge2_17 var2, Object var3) {
      Itemcounter6Extension var4 = this.mc.bridge$getWorld();
      return var4 != null && this.method14(var2) ? var4.bridge$canMonstersSpawn(var1, var3) : false;
   }

   private boolean method14(Bridge2_17 var1) {
      Bridge5Extension_5 var2 = this.mc.bridge$getPlayer();
      if (var2 == null) {
         return false;
      }

      Bridge3_23 var3 = var1.bridge$getBlock();
      return !var3.bridge$isSpawner() && !var3.bridge$isGlass();
   }

   protected int method15(byte var1, byte var2, int var3, float var4) {
      return !this.dynamicColor.get()
         ? (method16(var1, var2, var3) ? this.darkColor : this.brightColor).method14(var4)
         : ThreadModuleDump23.method51(this.darkColor.method14(var4), this.brightColor.method14(var4), method17(var1, var2, var3) / 15.0F);
   }

   protected static boolean method16(byte var0, byte var1, int var2) {
      return ThreadModuleDump63.MC_VERSION < 10 ? method17(var0, var1, var2) <= 7 : var0 == 0 && getSkylight(var1, var2) <= 7;
   }

   protected static int method17(byte var0, byte var1, int var2) {
      return Math.max(var0, getSkylight(var1, var2));
   }

   protected static int getSkylight(byte var0, int var1) {
      return var1 == -1 ? 0 : var0 - var1;
   }

   @Generated
   public void setNeedsUpdate(boolean var1) {
      this.needsUpdate = var1;
   }

   @Generated
   public int getSkylightSubtract() {
      return this.skylightSubtract;
   }

   protected enum Type implements OptionEnumValue {
      OVERLAY("overlay"),
      CROSS("cross"),
      NONE("none");

      private final String id;

      @Override
      public String id() {
         return this.id;
      }

      @Override
      public String toString() {
         return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id, new Object[0]);
      }

      @Generated
      Type(String var3) {
         this.id = var3;
      }
   }
}
