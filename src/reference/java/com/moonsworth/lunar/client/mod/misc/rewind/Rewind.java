package com.moonsworth.lunar.client.mod.misc.rewind;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.Bridge5Extension65;
import com.moonsworth.lunar.bridge.Bridge5Extension69;
import com.moonsworth.lunar.bridge.Bridge5_3;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.GameOptionsBridge;
import com.moonsworth.lunar.client.ui.notification.NotificationType;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.config.FeatureFlag;
import com.moonsworth.lunar.client.framework.mod.AlertExtension;
import com.moonsworth.lunar.client.framework.mod.Calculator2Handler;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework10Extension;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.Framework5;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.framework.feature.rewind.Gui2Extension;
import com.moonsworth.lunar.client.framework.feature.rewind.RewindIterator2;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui;
import com.moonsworth.lunar.client.framework.feature.rewind.mixin.Rewind2;
import com.moonsworth.lunar.client.framework.feature.rewind.mixin.RewindThread;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate.RewindhandlersNameplate;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.RewindhandlersNameplateCore3;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.RewindhandlersNameplateCoreType;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.RewindhandlersNameplateCore_3;
import com.moonsworth.lunar.client.highlight.Highlight;
import com.moonsworth.lunar.client.event.render.ContainerSlotRenderEvent.ContainerSlotPostEvent;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventRunDirectory;
import com.moonsworth.lunar.client.event.mixin.fishing.EventEverySecond;
import com.moonsworth.lunar.client.event.mixin.gui.DisconnectEvent;
import com.moonsworth.lunar.client.event.mixin.gui.ServerPingEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.BossBarRenderEvent;
import com.moonsworth.lunar.client.event.mixin.holograms.ClientShutdownEvent;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.KeyCombo;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.MultiSelectOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.DynamicDropdownOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data;
import com.moonsworth.lunar.client.driver.DriverRouteRegistryLegacy;
import com.moonsworth.lunar.client.driver.PhosphorIconLegacy;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data4;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.mixin.EntityRenderer4;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindRecordingIndicator;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDumpType2;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.function.Function;
import lombok.Generated;

public class Rewind extends AbstractFeature {
   private Map<String, String> field8 = new HashMap<>();
   private long field9 = 0L;
   private final ModifierKeybindOption field10 = (ModifierKeybindOption)((Data)((Data)((Data)OptionFactory.method18("recordKey")
               .method2(KeyCombo.method3(KeyCode.KEY_R)))
            .method15(PhosphorIconLegacy.PI_VIDEO_RECORDING_STROKE))
         .method18(this))
      .method31();
   private final ModifierKeybindOption field11 = (ModifierKeybindOption)((Data)((Data)OptionFactory.method18("pauseKey")
            .method15(PhosphorIconLegacy.PI_PAUSE_SQUARE_STROKE))
         .method18(this))
      .method31();
   private final ModifierKeybindOption field12 = (ModifierKeybindOption)((Data)((Data)OptionFactory.method18("markerKey")
            .method15(PhosphorIconLegacy.PI_BOOKMARK_ADD_STROKE))
         .method18(this))
      .method31();
   private final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)OptionFactory.method7("autoRecordOnConnect").OHICCHCORCORRRHCHRCCIROCCHCCRC())
         .method15(PhosphorIconLegacy.PI_VIDEO_RECORDING_STROKE))
      .method31();
   private final ToggleOption field14 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)OptionFactory.method7("crossServerRecording").OHICCHCORCORRRHCHRCCIROCCHCCRC())
         .method15(PhosphorIconLegacy.PI_VIDEO_RECORDING_STROKE))
      .method31();
   private final ToggleOption field15 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)((ToggleOptionBuilder)OptionFactory.method7("pauseRecordingOnGamePause")
               .OHICCHCORCORRRHCHRCCIROCCHCCRC())
            .method15(PhosphorIconLegacy.PI_PAUSE_SQUARE_STROKE))
         .RIHRRCHRORHHOROCHHCCHHOIOIOHCR())
      .method31();
   private final ToggleOption field16 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)((ToggleOptionBuilder)((ToggleOptionBuilder)OptionFactory.method7("recordingBackup")
                  .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
               .OHICCHCORCORRRHCHRCCIROCCHCCRC())
            .method15(PhosphorIconLegacy.PI_REMINDER_ANTICLOCKWISE_STROKE))
         .RIHRRCHRORHHOROCHHCCHHOIOIOHCR())
      .method31();
   private final ToggleOption field17 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)OptionFactory.method7("shadowRewind").OHICCHCORCORRRHCHRCCIROCCHCCRC())
         .method15(PhosphorIconLegacy.PI_VIDEO_RECORDING_STROKE))
      .method31();
   private final ModifierKeybindOption field18 = (ModifierKeybindOption)((Data)((Data)((Data)OptionFactory.method18("shadowRecordingKey")
               .method15(PhosphorIconLegacy.PI_DOUBLE_CHEVRON_LEFT_STROKE))
            .method17(() -> !(Boolean)this.field17.get()))
         .method18(this))
      .method31();
   private final EnumOption<Gui2Extension> field19 = (EnumOption<Gui2Extension>)((com.moonsworth.lunar.client.config.option.EnumOption.Data)((com.moonsworth.lunar.client.config.option.EnumOption.Data)((com.moonsworth.lunar.client.config.option.EnumOption.Data)OptionFactory.method10(
                  "shadowRecordingTime", Gui2Extension.SECONDS_30
               )
               .method17(() -> !(Boolean)this.field17.get()))
            .method11()
            .OHICCHCORCORRRHCHRCCIROCCHCCRC())
         .method10(PhosphorIconLegacy.PI_DOUBLE_CHEVRON_LEFT_STROKE))
      .method31();
   private final ToggleOption field20 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)OptionFactory.method7("micRecording").OHICCHCORCORRRHCHRCCIROCCHCCRC())
         .method15(PhosphorIconLegacy.PI_MIC_MICROPHONE_STROKE))
      .method31();
   private final DynamicDropdownOption field21 = (DynamicDropdownOption)((com.moonsworth.lunar.client.config.option.DynamicDropdownOption.Data)((com.moonsworth.lunar.client.config.option.DynamicDropdownOption.Data)((com.moonsworth.lunar.client.config.option.DynamicDropdownOption.Data)OptionFactory.method19(
                  "micInput"
               )
               .method2("default"))
            .method4(this::method13)
            .method5(var1 -> this.field8.getOrDefault(var1, var1))
            .OHICCHCORCORRRHCHRCCIROCCHCCRC())
         .method15(PhosphorIconLegacy.PI_MIC_MICROPHONE_STROKE))
      .method31();
   private final ToggleOption field22 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)OptionFactory.method7("systemAudioRecording").OHICCHCORCORRRHCHRCCIROCCHCCRC())
         .method15(PhosphorIconLegacy.PI_VOLUME_TWO_STROKE))
      .method31();
   private final RewindRecordingIndicator field23 = new RewindRecordingIndicator(this);
   private RewindHandlers5 field24 = null;
   private RewindHandlers field25 = null;
   private final Queue<Runnable> field26 = new LinkedList<>();

   public Rewind() {
      super(true);
      this.CCHOHROIOHHCCCOCIIOIRCHRROHHRC(Framework.field6).<ClientOption>flatMap(ModEnabledState::method1).ifPresent(var1 -> var1.method8(var1x -> {
         if (!var1x && this.field24 != null) {
            this.method12(true, this.field24.method16().method4() < 5000L);
         }
      }));
      this.field10.method3(() -> {
         if (this.field24 != null && !this.field24.method22()) {
            this.method11(false);
         } else {
            this.method9(false);
         }
      });
      this.field11.method3(() -> {
         if (this.field24 != null && this.field24.method17() != RewindhandlersNameplateCoreType.STOPPED) {
            if (this.field24.method17() == RewindhandlersNameplateCoreType.RECORDING) {
               this.field24.method11(false);
            } else {
               this.method15();
            }
         }
      });
      this.field12
         .RCOHRRRHRIOORHCRORIIHCCORRCRRC(
            () -> {
               if (this.isRecording()) {
                  this.field24.method16().mark();
                  ThreadModuleDump63.method4()
                     .method69()
                     .method6(NotificationType.SUCCESS, "Rewind", ThreadModuleDump63.method4().method67().method2("popups", "markerAdded"));
               }
            }
         );
      this.field18.method3(() -> {
         if (this.field24 != null) {
            this.field24.method14();
         }
      });
      this.field17.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var1 -> {
         if (!var1 && this.field24 != null && this.field24.method22()) {
            this.method11(false);
         }
      });
      this.handle(ServerPingEvent.class, this::method4);
      this.handle(EventClientTick.class, this::method5);
      this.handle(EventEverySecond.class, this::method6);
      this.handle(EventRunDirectory.class, this::method4);
      this.handle(DisconnectEvent.class, this::method7);
      this.handle(ClientShutdownEvent.class, this::method8);
      this.handle(ContainerSlotPostEvent.class, this::method3);
      this.handle(BossBarRenderEvent.class, this::method18);

      try {
         this.method14();
      } catch (Exception var2) {
         this.cleanup();
      }
   }

   private List<String> method13() {
      if (System.currentTimeMillis() - this.field9 > 5000L) {
         this.field9 = System.currentTimeMillis();
         Bridge5_3 var1 = Bridge.method65();
         String var2 = var1.method4(null, 785);
         if (var2 == null) {
            var2 = "Default";
         }

         HashMap var3 = new HashMap();
         var3.put("default", ThreadModuleDump63.method4().method67().method2("settings", "defaultMic", var2.replace("OpenAL Soft on ", "")));
         if (var1.method14(null, "ALC_ENUMERATION_EXT")) {
            for (String var5 : var1.method5(null, 784)) {
               var3.put(var5, var5.replace("OpenAL Soft on ", ""));
            }
         }

         this.field8 = var3;
      }

      return new ArrayList<>(this.field8.keySet());
   }

   private void method14() {
      for (File var4 : Gui.field5.listFiles()) {
         if (var4.getName().startsWith("backup_")) {
            FileReader var6 = new FileReader(var4, StandardCharsets.UTF_8);

            JsonObject var5;
            try {
               var5 = (JsonObject)ThreadModuleDump48.field22.fromJson(var6, JsonObject.class);
            } catch (Throwable var18) {
               try {
                  var6.close();
               } catch (Throwable var17) {
                  var18.addSuppressed(var17);
               }

               throw var18;
            }

            var6.close();
            long var19 = var5.get("duration").getAsLong();
            File var8 = new File(Gui.field5, var5.get("rewind").getAsString());
            RewindhandlersNameplateCore_3 var9;
            if (var5.has("micAudio")) {
               JsonObject var10 = var5.getAsJsonObject("audio");
               String var11 = var10.get("id").getAsString();
               var9 = new RewindhandlersNameplateCore_3(var11, new File(Gui.field5, var10.get("file").getAsString()), false);
            } else {
               var9 = null;
            }

            RewindhandlersNameplateCore_3 var20;
            if (var5.has("loopbackAudio")) {
               JsonObject var21 = var5.getAsJsonObject("loopbackAudio");
               String var12 = var21.get("id").getAsString();
               var20 = new RewindhandlersNameplateCore_3(var12, new File(Gui.field5, var21.get("file").getAsString()), true);
            } else {
               var20 = null;
            }

            ArrayList var22 = new ArrayList();

            for (JsonElement var13 : var5.getAsJsonArray("packs").asList()) {
               var22.add(new File(var13.getAsString()));
            }

            ArrayList var24 = new ArrayList();

            for (JsonElement var14 : var5.getAsJsonArray("files").asList()) {
               var24.add(new File(var14.getAsString()));
            }

            Rewind2 var26 = (Rewind2)ThreadModuleDump48.field22.fromJson(var5.getAsJsonObject("metadata"), Rewind2.class);
            ArrayList var27 = new ArrayList();

            for (Entry var16 : var5.getAsJsonObject("snapshots").entrySet()) {
               var27.add(
                  new RewindThread(
                     UUID.fromString((String)var16.getKey()), new File(Gui.field5, ((JsonElement)var16.getValue()).getAsString()), var26.method13()
                  )
               );
            }

            RewindhandlersNameplateCore3 var28;
            if (var5.has("micAudioMetadata")) {
               var28 = (RewindhandlersNameplateCore3)ThreadModuleDump48.field22
                  .fromJson(var5.getAsJsonObject("micAudioMetadata"), RewindhandlersNameplateCore3.class);
            } else {
               var28 = null;
            }

            RewindhandlersNameplateCore3 var29;
            if (var5.has("loopbackAudioMetadata")) {
               var29 = (RewindhandlersNameplateCore3)ThreadModuleDump48.field22
                  .fromJson(var5.getAsJsonObject("loopbackAudioMetadata"), RewindhandlersNameplateCore3.class);
            } else {
               var29 = null;
            }

            var26.setDuration(Math.max(var19, var26.getDuration()));
            RewindIterator2.method11(null, var8, var26, var27, var22, var9, var28, var20, var29, var4, var24);
         }
      }

      this.cleanup();
   }

   private void cleanup() {
      if (Gui.field5.isDirectory()) {
         for (File var4 : Gui.field5.listFiles()) {
            if (var4.isFile()) {
               var4.delete();
            }
         }
      }
   }

   private void method3(ContainerSlotPostEvent var1) {
      ModEnabledState var2 = (ModEnabledState)this.field23.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field6);
      if (var2 != null && !var2.method1().isEmpty() && (Boolean)((ClientOption)var2.method1().get()).get()) {
         Bridge5Extension6 var3 = ThreadModuleDump63.method3().bridge$getCurrentScreen();
         if (!(var3 instanceof Bridge5Extension69)) {
            if (this.field24 == null || !this.field24.method22() || ThreadModuleDump63.method8() != null) {
               if (this.field23.method2(var3, this.field24) && ThreadModuleDump63.method8() != null
                  || this.field24 != null && ThreadModuleDump63.method8() == null) {
                  AlertExtension var4 = (AlertExtension)this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field5);
                  if (var4 != null) {
                     AbstractRenderContext var5 = var1.OCCRRRIHHOCOHOOOOIRROIORRCHIOR();

                     for (Framework7Extension var7 : var4.getChildren()) {
                        MixinCore9Extension var8 = (MixinCore9Extension)var7.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field1);
                        if (var8 != null && var8.method5(true, var7)) {
                           com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2 var9 = new Data4(var3.bridge$getWidth(), var3.bridge$getHeight())
                              .OCRCCHICRRIROCIHCOROROHCIRCICO();
                           float var10 = LcuiScreen.getScale();
                           var5.push();
                           var5.method29(var10 * var8.getScale(), var10 * var8.getScale());
                           var8.method15(var9);
                           var8.method3(
                              new com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudLegacy(var5, var1.HHHRIHCIOHCICRCCOCIRRROHIOHRIH(), var9),
                              var8.ICRIHRIORRCRCOOCCCHHRIRICCHHII(),
                              var8.RIIIOCHHCIHOIOROOOHRIRICCCCHHC(),
                              true
                           );
                           var5.pop();
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private void method4(Highlight var1) {
      if (((Boolean)this.field13.get() || (Boolean)this.field17.get()) && this.field24 == null) {
         this.method9(!(Boolean)this.field13.get());
      }

      if ((Boolean)this.field14.get() && this.field24 != null && this.field24.method17() == RewindhandlersNameplateCoreType.PAUSED && this.field24.method19()) {
         this.method15();
      }
   }

   public void method5(EventClientTick var1) {
      while (!this.field26.isEmpty()) {
         this.field26.poll().run();
      }

      if (this.field24 != null && ThreadModuleDump63.method3().bridge$getCurrentScreen() instanceof Bridge5Extension65 && !this.field24.method16().method2()) {
         this.method12(true, true);
      }
   }

   private void method6(EventEverySecond var1) {
      if (ThreadModuleDump63.method8() != null && ThreadModuleDump63.method7() != null && this.field24 == null && (Boolean)this.field17.get()) {
         this.method9(true);
      }
   }

   private void method7(DisconnectEvent var1) {
      if (!var1.method3()) {
         this.method16();
         if ((Boolean)this.field14.get() && this.field24 != null && !this.field24.method22()) {
            if (this.isRecording()) {
               this.field24.method11(true);
            }
         } else if (this.field24 != null) {
            this.method12(false, this.field24.method16().method4() < 5000L);
         }
      }
   }

   private void method8(ClientShutdownEvent var1) {
      this.method11(true);
   }

   private void method9(boolean var1) {
      if (FeatureFlag.REWIND.isEnabled()) {
         if (!this.method19() && (!this.isRecording() || this.field24.method22())) {
            if (this.field24 != null) {
               this.method12(false, this.field24.method16().method4() < 5000L);
            }

            try {
               this.field24 = new RewindHandlers5(this, var1, (Boolean)this.field20.get(), (Boolean)this.field22.get());
               Framework10Extension var2 = (Framework10Extension)this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field12);
               if (var2 != null) {
                  var2.method4(this.field24);
               }
            } catch (Exception var3) {
               if (this.field24 != null) {
                  this.field24.method13(false, true);
               }

               ThreadModuleDump63.method4().method69().method6(NotificationType.ERROR, "Rewind", "Cannot start recording: " + var3.getMessage());
               Inventorymod2.method5(var3, "Rewind");
            }
         }
      }
   }

   private void method15() {
      try {
         this.field24.resume();
      } catch (Exception var2) {
         ThreadModuleDump63.method4().method69().method6(NotificationType.ERROR, "Rewind", "Cannot resume recording: " + var2.getMessage());
         throw new RuntimeException(var2);
      }
   }

   private void method11(boolean var1) {
      this.method12(var1, false);
   }

   private void method12(boolean var1, boolean var2) {
      if (this.field24 != null) {
         try {
            this.field24.method13(var1, var2);
         } catch (Exception var4) {
            ThreadModuleDump63.method4().method69().method6(NotificationType.ERROR, "Rewind", "Error when stopping recording: " + var4.getMessage());
            throw new RuntimeException(var4);
         }

         Framework10Extension var3 = (Framework10Extension)this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field12);
         if (var3 != null) {
            var3.method5(this.field24);
         }

         this.field24 = null;
      }
   }

   public void method13(File var1, String var2, RewindhandlersNameplate var3, boolean var4) {
      if (this.method19()) {
         ThreadModuleDump63.method4().method69().method6(NotificationType.ERROR, "Rewind", "Already replaying");
      } else {
         try {
            ThreadModuleDump63.method4().method41().close();
            ThreadModuleDump63.method4().method40().method7(true);
            ThreadModuleDump63.method3().bridge$getGameSettings().bridge$saveOptions();
            this.field25 = new RewindHandlers(var1, var2, var3, var4);
            this.field25.method3(0L);
            Framework10Extension var5 = (Framework10Extension)this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field12);
            if (var5 != null) {
               var5.method4(this.field25);
            }

            EntityRenderer4.method75();
         } catch (Exception var6) {
            this.method16();
            ThreadModuleDump63.method4().method69().method6(NotificationType.ERROR, "Rewind", "Cannot load recording: " + var6.getMessage());
            Inventorymod2.method5(var6, "Rewind");
         }
      }
   }

   public void method16() {
      if (this.field25 != null && !this.field25.isReloading()) {
         try {
            DriverViewportLegacy.method50().method16(DriverRouteRegistryLegacy.field3);
            this.field25.stop();
         } catch (Exception var4) {
            ThreadModuleDump63.method4().method69().method8("Rewind", "Error when closing Rewind: " + var4.getMessage());
            throw new RuntimeException(var4);
         }

         Framework10Extension var1 = (Framework10Extension)this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field12);
         if (var1 != null) {
            var1.method5(this.field25);
         }

         try {
            this.method17();
         } catch (Exception var3) {
            var3.printStackTrace();
         }

         this.field25 = null;
      }
   }

   private void method17() {
      try {
         for (Framework7Extension var2 : ThreadModuleDump63.method4().method40().method1()) {
            Framework5 var3 = (Framework5)var2.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field14);
            if (var3 != null) {
               for (ClientOption var6 : var3.method2()) {
                  if (var6 instanceof MultiSelectOption) {
                     var6.reset();
                  }
               }
            }
         }
      } catch (Exception var10) {
      }

      try {
         GameOptionsBridge var11 = ThreadModuleDump63.method3().bridge$getGameSettings();
         var11.bridge$loadOptions();
         var11.bridge$setHideGui(false);
         var11.bridge$setThirdPersonView(0);
      } catch (Exception var9) {
      }

      try {
         ThreadModuleDump63.method4().method41().init();
      } catch (Exception var8) {
      }

      try {
         ThreadModuleDump63.method4().method40().HHIHOOIHCOHOIRORCHICOCHCORROCR();
      } catch (Exception var7) {
      }
   }

   public boolean isRecording() {
      return this.field24 != null && this.field24.isRecording();
   }

   public boolean method19() {
      return this.field25 != null;
   }

   public boolean method17(Function<RewindHandlers, Boolean> var1) {
      return !this.method19() ? false : (Boolean)var1.apply(this.field25);
   }

   private void method18(BossBarRenderEvent var1) {
      if (this.method17(var0 -> !var0.method53().method19())) {
         var1.setCancelled(true);
      }
   }

   public String getId() {
      return "REWIND";
   }

   protected List<Framework7Extension> method9() {
      return List.of(this.field23);
   }

   public void method2(RootSettingsAssembler var1) {
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(
         "recording",
         PhosphorIconLegacy.PI_VIDEO_RECORDING_SOLID,
         var1x -> var1x.method9(
            new ClientOption[]{this.field10, this.field11, this.field12, this.field13, this.field14, this.field15, this.field16}
         )
      );
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(
         "shadowRewindCategory",
         PhosphorIconLegacy.PI_DOUBLE_CHEVRON_LEFT_SOLID,
         var1x -> var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
            this.field17, var1xx -> var1xx.method9(new ClientOption[]{this.field18, this.field19})
         )
      );
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(
         "audio",
         PhosphorIconLegacy.PI_VOLUME_TWO_SOLID,
         var1x -> {
            var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field20, var1xx -> var1xx.method9(new ClientOption[]{this.field21}));
            var1x.method9(new ClientOption[]{this.field22})
               .method3(() -> ThreadModuleDumpType2.isMacos() && ThreadModuleDumpType2.getMacOsMajorVersion() < 13);
         }
      );
   }

   public boolean method7() {
      return false;
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new Calculator2Handler[]{Calculator2Handler.field3}).method11(this);
   }

   @Generated
   public ModifierKeybindOption method23() {
      return this.field10;
   }

   @Generated
   public ModifierKeybindOption method24() {
      return this.field11;
   }

   @Generated
   public ModifierKeybindOption method25() {
      return this.field12;
   }

   @Generated
   public ToggleOption method26() {
      return this.field15;
   }

   @Generated
   public ToggleOption method27() {
      return this.field16;
   }

   @Generated
   public ModifierKeybindOption method28() {
      return this.field18;
   }

   @Generated
   public EnumOption<Gui2Extension> method29() {
      return this.field19;
   }

   @Generated
   public DynamicDropdownOption method30() {
      return this.field21;
   }

   @Generated
   public RewindHandlers5 method34() {
      return this.field24;
   }

   @Generated
   public RewindHandlers method35() {
      return this.field25;
   }

   @Generated
   public Queue<Runnable> method36() {
      return this.field26;
   }
}
