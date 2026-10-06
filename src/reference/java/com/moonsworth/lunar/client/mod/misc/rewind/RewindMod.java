package com.moonsworth.lunar.client.mod.misc.rewind;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.GuiMultiplayerBridge;
import com.moonsworth.lunar.bridge.GuiConnectingBridge;
import com.moonsworth.lunar.bridge.AlcBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.GameOptionsBridge;
import com.moonsworth.lunar.client.ui.notification.NotificationType;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.config.FeatureFlag;
import com.moonsworth.lunar.client.framework.mod.ModChildren;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModLifecycle;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.OptionContainer;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.replay.export.ReplayClipDuration;
import com.moonsworth.lunar.client.framework.feature.rewind.RewindIterator2;
import com.moonsworth.lunar.client.replay.project.RewindPaths;
import com.moonsworth.lunar.client.framework.feature.rewind.mixin.Rewind2;
import com.moonsworth.lunar.client.replay.project.RewindThread;
import com.moonsworth.lunar.client.replay.export.ExportSettings;
import com.moonsworth.lunar.client.replay.recording.RewindAudioMetadata;
import com.moonsworth.lunar.client.replay.recording.RecorderState;
import com.moonsworth.lunar.client.replay.recording.AudioTrack;
import com.moonsworth.lunar.client.event.LunarEvent;
import com.moonsworth.lunar.client.event.render.EventRenderContainerSlot.EventRenderContainerSlotPost;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventGameDirectory;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
import com.moonsworth.lunar.client.event.mixin.gui.EventDisconnect;
import com.moonsworth.lunar.client.event.mixin.gui.EventServerPing;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderBossBar;
import com.moonsworth.lunar.client.event.mixin.holograms.EventClientShutdown;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.KeyBind;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.MultiSelectOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.DynamicDropdownOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data;
import com.moonsworth.lunar.client.driver.DriverRouteRegistry;
import com.moonsworth.lunar.client.driver.PhosphorIcon;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data4;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.network.websocket.AssetServerClient;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindRecordingIndicator;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.framework.OperatingSystem;
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

public class RewindMod extends AbstractFeature {
   private Map<String, String> field8 = new HashMap<>();
   private long field9 = 0L;
   private final ModifierKeybindOption field10 = (ModifierKeybindOption)((Data)((Data)((Data)OptionFactory.method18("recordKey")
               .HIIIOHRRROCICIOIORRRIRCRCHHIII(KeyBind.method3(KeyCode.KEY_R)))
            .HHRROIIHRRICIIHIIHICRHHRHOHHOO(PhosphorIcon.PI_VIDEO_RECORDING_STROKE))
         .OOOHICCHHHRHCORIRCRHOCROROIOCR(this))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ModifierKeybindOption field11 = (ModifierKeybindOption)((Data)((Data)OptionFactory.method18("pauseKey")
            .HHRROIIHRRICIIHIIHICRHHRHOHHOO(PhosphorIcon.PI_PAUSE_SQUARE_STROKE))
         .OOOHICCHHHRHCORIRCRHOCROROIOCR(this))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ModifierKeybindOption field12 = (ModifierKeybindOption)((Data)((Data)OptionFactory.method18("markerKey")
            .HHRROIIHRRICIIHIIHICRHHRHOHHOO(PhosphorIcon.PI_BOOKMARK_ADD_STROKE))
         .OOOHICCHHHRHCORIRCRHOCROROIOCR(this))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)OptionFactory.method7("autoRecordOnConnect").OHICCHCORCORRRHCHRCCIROCCHCCRC())
         .HHRROIIHRRICIIHIIHICRHHRHOHHOO(PhosphorIcon.PI_VIDEO_RECORDING_STROKE))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption field14 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)OptionFactory.method7("crossServerRecording").OHICCHCORCORRRHCHRCCIROCCHCCRC())
         .HHRROIIHRRICIIHIIHICRHHRHOHHOO(PhosphorIcon.PI_VIDEO_RECORDING_STROKE))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption field15 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)((ToggleOptionBuilder)OptionFactory.method7("pauseRecordingOnGamePause")
               .OHICCHCORCORRRHCHRCCIROCCHCCRC())
            .HHRROIIHRRICIIHIIHICRHHRHOHHOO(PhosphorIcon.PI_PAUSE_SQUARE_STROKE))
         .RIHRRCHRORHHOROCHHCCHHOIOIOHCR())
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption field16 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)((ToggleOptionBuilder)((ToggleOptionBuilder)OptionFactory.method7("recordingBackup")
                  .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
               .OHICCHCORCORRRHCHRCCIROCCHCCRC())
            .HHRROIIHRRICIIHIIHICRHHRHOHHOO(PhosphorIcon.PI_REMINDER_ANTICLOCKWISE_STROKE))
         .RIHRRCHRORHHOROCHHCCHHOIOIOHCR())
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption field17 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)OptionFactory.method7("shadowRewind").OHICCHCORCORRRHCHRCCIROCCHCCRC())
         .HHRROIIHRRICIIHIIHICRHHRHOHHOO(PhosphorIcon.PI_VIDEO_RECORDING_STROKE))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ModifierKeybindOption field18 = (ModifierKeybindOption)((Data)((Data)((Data)OptionFactory.method18("shadowRecordingKey")
               .HHRROIIHRRICIIHIIHICRHHRHOHHOO(PhosphorIcon.PI_DOUBLE_CHEVRON_LEFT_STROKE))
            .ORICHRORRORHORHOIHCRHOORCRRHOI(() -> !(Boolean)this.field17.get()))
         .OOOHICCHHHRHCORIRCRHOCROROIOCR(this))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final EnumOption<ReplayClipDuration> field19 = (EnumOption<ReplayClipDuration>)((com.moonsworth.lunar.client.config.option.EnumOption.Data)((com.moonsworth.lunar.client.config.option.EnumOption.Data)((com.moonsworth.lunar.client.config.option.EnumOption.Data)OptionFactory.method10(
                  "shadowRecordingTime", ReplayClipDuration.SECONDS_30
               )
               .ORICHRORRORHORHOIHCRHOORCRRHOI(() -> !(Boolean)this.field17.get()))
            .method11()
            .OHICCHCORCORRRHCHRCCIROCCHCCRC())
         .method10(PhosphorIcon.PI_DOUBLE_CHEVRON_LEFT_STROKE))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption field20 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)OptionFactory.method7("micRecording").OHICCHCORCORRRHCHRCCIROCCHCCRC())
         .HHRROIIHRRICIIHIIHICRHHRHOHHOO(PhosphorIcon.PI_MIC_MICROPHONE_STROKE))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final DynamicDropdownOption field21 = (DynamicDropdownOption)((com.moonsworth.lunar.client.config.option.DynamicDropdownOption.Data)((com.moonsworth.lunar.client.config.option.DynamicDropdownOption.Data)((com.moonsworth.lunar.client.config.option.DynamicDropdownOption.Data)OptionFactory.method19(
                  "micInput"
               )
               .HIIIOHRRROCICIOIORRRIRCRCHHIII("default"))
            .method4(this::method13)
            .method5(arg1 -> this.field8.getOrDefault(arg1, arg1))
            .OHICCHCORCORRRHCHRCCIROCCHCCRC())
         .HHRROIIHRRICIIHIIHICRHHRHOHHOO(PhosphorIcon.PI_MIC_MICROPHONE_STROKE))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption field22 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)OptionFactory.method7("systemAudioRecording").OHICCHCORCORRRHCHRCCIROCCHCCRC())
         .HHRROIIHRRICIIHIIHICRHHRHOHHOO(PhosphorIcon.PI_VOLUME_TWO_STROKE))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final RewindRecordingIndicator field23 = new RewindRecordingIndicator(this);
   private RewindRecorder field24 = null;
   private RewindHandlers field25 = null;
   private final Queue<Runnable> field26 = new LinkedList<>();

   public RewindMod() {
      super(true);
      this.CCHOHROIOHHCCCOCIIOIRCHRROHHRC(ModTraits.field6).<ClientOption>flatMap(ModEnabledState::method1).ifPresent(arg1 -> arg1.method8(arg1x -> {
         if (!arg1x && this.field24 != null) {
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
         if (this.field24 != null && this.field24.method17() != RecorderState.STOPPED) {
            if (this.field24.method17() == RecorderState.RECORDING) {
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
                  Ref.method4()
                     .method69()
                     .method6(NotificationType.SUCCESS, "RewindMod", Ref.method4().method67().method2("popups", "markerAdded", new Object[0]));
               }
            }
         );
      this.field18.method3(() -> {
         if (this.field24 != null) {
            this.field24.method14();
         }
      });
      this.field17.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1 -> {
         if (!arg1 && this.field24 != null && this.field24.method22()) {
            this.method11(false);
         }
      });
      this.handle(EventServerPing.class, this::method4);
      this.handle(EventTick.class, this::method5);
      this.handle(EventSecond.class, this::method6);
      this.handle(EventGameDirectory.class, this::method4);
      this.handle(EventDisconnect.class, this::method7);
      this.handle(EventClientShutdown.class, this::method8);
      this.handle(EventRenderContainerSlotPost.class, this::method3);
      this.handle(EventRenderBossBar.class, this::method18);

      try {
         this.method14();
      } catch (Exception exception2) {
         this.cleanup();
      }
   }

   private List<String> method13() {
      if (System.currentTimeMillis() - this.field9 > 5000L) {
         this.field9 = System.currentTimeMillis();
         AlcBridge bridge5_31 = Bridge.method65();
         String text2 = bridge5_31.method4(null, 785);
         if (text2 == null) {
            text2 = "Default";
         }

         HashMap map3 = new HashMap();
         map3.put("default", Ref.method4().method67().method2("settings", "defaultMic", new Object[]{text2.replace("OpenAL Soft on ", "")}));
         if (bridge5_31.method14(null, "ALC_ENUMERATION_EXT")) {
            for (String text5 : bridge5_31.method5(null, 784)) {
               map3.put(text5, text5.replace("OpenAL Soft on ", ""));
            }
         }

         this.field8 = map3;
      }

      return new ArrayList<>(this.field8.keySet());
   }

   private void method14() {
      for (File file4 : RewindPaths.field5.listFiles()) {
         if (file4.getName().startsWith("backup_")) {
            FileReader filereader6 = new FileReader(file4, StandardCharsets.UTF_8);

            JsonObject json5;
            try {
               json5 = (JsonObject)LunarConstants.field22.fromJson(filereader6, JsonObject.class);
            } catch (Throwable exception18) {
               try {
                  filereader6.close();
               } catch (Throwable exception17) {
                  exception18.addSuppressed(exception17);
               }

               throw exception18;
            }

            filereader6.close();
            long number19 = json5.get("duration").getAsLong();
            File file8 = new File(RewindPaths.field5, json5.get("rewind").getAsString());
            AudioTrack rewindhandlersnameplatecore_39;
            if (json5.has("micAudio")) {
               JsonObject json10 = json5.getAsJsonObject("audio");
               String text11 = json10.get("id").getAsString();
               rewindhandlersnameplatecore_39 = new AudioTrack(text11, new File(RewindPaths.field5, json10.get("file").getAsString()), false);
            } else {
               rewindhandlersnameplatecore_39 = null;
            }

            AudioTrack rewindhandlersnameplatecore_320;
            if (json5.has("loopbackAudio")) {
               JsonObject json21 = json5.getAsJsonObject("loopbackAudio");
               String text12 = json21.get("id").getAsString();
               rewindhandlersnameplatecore_320 = new AudioTrack(text12, new File(RewindPaths.field5, json21.get("file").getAsString()), true);
            } else {
               rewindhandlersnameplatecore_320 = null;
            }

            ArrayList list22 = new ArrayList();

            for (JsonElement element13 : json5.getAsJsonArray("packs").asList()) {
               list22.add(new File(element13.getAsString()));
            }

            ArrayList list24 = new ArrayList();

            for (JsonElement element14 : json5.getAsJsonArray("files").asList()) {
               list24.add(new File(element14.getAsString()));
            }

            Rewind2 rewind226 = (Rewind2)LunarConstants.field22.fromJson(json5.getAsJsonObject("metadata"), Rewind2.class);
            ArrayList list27 = new ArrayList();

            for (Entry entry16 : json5.getAsJsonObject("snapshots").entrySet()) {
               list27.add(
                  new RewindThread(
                     UUID.fromString((String)entry16.getKey()), new File(RewindPaths.field5, ((JsonElement)entry16.getValue()).getAsString()), rewind226.method13()
                  )
               );
            }

            RewindAudioMetadata rewindhandlersnameplatecore328;
            if (json5.has("micAudioMetadata")) {
               rewindhandlersnameplatecore328 = (RewindAudioMetadata)LunarConstants.field22
                  .fromJson(json5.getAsJsonObject("micAudioMetadata"), RewindAudioMetadata.class);
            } else {
               rewindhandlersnameplatecore328 = null;
            }

            RewindAudioMetadata rewindhandlersnameplatecore329;
            if (json5.has("loopbackAudioMetadata")) {
               rewindhandlersnameplatecore329 = (RewindAudioMetadata)LunarConstants.field22
                  .fromJson(json5.getAsJsonObject("loopbackAudioMetadata"), RewindAudioMetadata.class);
            } else {
               rewindhandlersnameplatecore329 = null;
            }

            rewind226.setDuration(Math.max(number19, rewind226.getDuration()));
            RewindIterator2.method11(null, file8, rewind226, list27, list22, rewindhandlersnameplatecore_39, rewindhandlersnameplatecore328, rewindhandlersnameplatecore_320, rewindhandlersnameplatecore329, file4, list24);
         }
      }

      this.cleanup();
   }

   private void cleanup() {
      if (RewindPaths.field5.isDirectory()) {
         for (File file4 : RewindPaths.field5.listFiles()) {
            if (file4.isFile()) {
               file4.delete();
            }
         }
      }
   }

   private void method3(EventRenderContainerSlotPost data31) {
      ModEnabledState framework32 = (ModEnabledState)this.field23.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field6);
      if (framework32 != null && !framework32.method1().isEmpty() && (Boolean)((ClientOption)framework32.method1().get()).get()) {
         GuiScreenBridge bridge5extension63 = Ref.method3().bridge$getCurrentScreen();
         if (!(bridge5extension63 instanceof GuiConnectingBridge)) {
            if (this.field24 == null || !this.field24.method22() || Ref.method8() != null) {
               if (this.field23.method2(bridge5extension63, this.field24) && Ref.method8() != null
                  || this.field24 != null && Ref.method8() == null) {
                  ModChildren alertextension4 = (ModChildren)this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field5);
                  if (alertextension4 != null) {
                     AbstractRenderContext bridgeextension_95 = data31.OCCRRRIHHOCOHOOOOIRROIORRCHIOR();

                     for (Framework7Extension framework7extension7 : alertextension4.getChildren()) {
                        MixinCore9Extension mixincore9extension8 = (MixinCore9Extension)framework7extension7.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field1);
                        if (mixincore9extension8 != null && mixincore9extension8.method5(true, framework7extension7)) {
                           com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2 data29 = new Data4(bridge5extension63.bridge$getWidth(), bridge5extension63.bridge$getHeight())
                              .OCRCCHICRRIROCIHCOROROHCIRCICO();
                           float value10 = LcuiScreen.getScale();
                           bridgeextension_95.push();
                           bridgeextension_95.method29(value10 * mixincore9extension8.getScale(), value10 * mixincore9extension8.getScale());
                           mixincore9extension8.method15(data29);
                           mixincore9extension8.method3(
                              new com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHud(bridgeextension_95, data31.HHHRIHCIOHCICRCCOCIRRROHIOHRIH(), data29),
                              mixincore9extension8.method1(),
                              mixincore9extension8.method2(),
                              true
                           );
                           bridgeextension_95.pop();
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private void method4(LunarEvent highlight1) {
      if (((Boolean)this.field13.get() || (Boolean)this.field17.get()) && this.field24 == null) {
         this.method9(!(Boolean)this.field13.get());
      }

      if ((Boolean)this.field14.get() && this.field24 != null && this.field24.method17() == RecorderState.PAUSED && this.field24.method19()) {
         this.method15();
      }
   }

   public void method5(EventTick highlightimpl21) {
      while (!this.field26.isEmpty()) {
         this.field26.poll().run();
      }

      if (this.field24 != null && Ref.method3().bridge$getCurrentScreen() instanceof GuiMultiplayerBridge && !this.field24.method16().method2()) {
         this.method12(true, true);
      }
   }

   private void method6(EventSecond highlightimpl41) {
      if (Ref.method8() != null && Ref.method7() != null && this.field24 == null && (Boolean)this.field17.get()) {
         this.method9(true);
      }
   }

   private void method7(EventDisconnect highlightimpl111) {
      if (!highlightimpl111.method3()) {
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

   private void method8(EventClientShutdown highlightimpl1) {
      this.method11(true);
   }

   private void method9(boolean flag1) {
      if (FeatureFlag.REWIND.isEnabled()) {
         if (!this.method19() && (!this.isRecording() || this.field24.method22())) {
            if (this.field24 != null) {
               this.method12(false, this.field24.method16().method4() < 5000L);
            }

            try {
               this.field24 = new RewindRecorder(this, flag1, (Boolean)this.field20.get(), (Boolean)this.field22.get());
               ModLifecycle framework10extension2 = (ModLifecycle)this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field12);
               if (framework10extension2 != null) {
                  framework10extension2.method4(this.field24);
               }
            } catch (Exception exception3) {
               if (this.field24 != null) {
                  this.field24.method13(false, true);
               }

               Ref.method4().method69().method6(NotificationType.ERROR, "RewindMod", "Cannot start recording: " + exception3.getMessage());
               CrashReporter.method5(exception3, "RewindMod");
            }
         }
      }
   }

   private void method15() {
      try {
         this.field24.resume();
      } catch (Exception exception2) {
         Ref.method4().method69().method6(NotificationType.ERROR, "RewindMod", "Cannot resume recording: " + exception2.getMessage());
         throw new RuntimeException(exception2);
      }
   }

   private void method11(boolean flag1) {
      this.method12(flag1, false);
   }

   private void method12(boolean flag1, boolean flag2) {
      if (this.field24 != null) {
         try {
            this.field24.method13(flag1, flag2);
         } catch (Exception exception4) {
            Ref.method4().method69().method6(NotificationType.ERROR, "RewindMod", "Error when stopping recording: " + exception4.getMessage());
            throw new RuntimeException(exception4);
         }

         ModLifecycle framework10extension3 = (ModLifecycle)this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field12);
         if (framework10extension3 != null) {
            framework10extension3.method5(this.field24);
         }

         this.field24 = null;
      }
   }

   public void method13(File file1, String text2, ExportSettings rewindhandlersnameplate3, boolean flag4) {
      if (this.method19()) {
         Ref.method4().method69().method6(NotificationType.ERROR, "RewindMod", "Already replaying");
      } else {
         try {
            Ref.method4().method41().close();
            Ref.method4().method40().OCCIOHICOOHRIIIOOHRCRHOIHOOCHH(true);
            Ref.method3().bridge$getGameSettings().bridge$saveOptions();
            this.field25 = new RewindHandlers(file1, text2, rewindhandlersnameplate3, flag4);
            this.field25.method3(0L);
            ModLifecycle framework10extension5 = (ModLifecycle)this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field12);
            if (framework10extension5 != null) {
               framework10extension5.method4(this.field25);
            }

            AssetServerClient.method75();
         } catch (Exception exception6) {
            this.method16();
            Ref.method4().method69().method6(NotificationType.ERROR, "RewindMod", "Cannot load recording: " + exception6.getMessage());
            CrashReporter.method5(exception6, "RewindMod");
         }
      }
   }

   public void method16() {
      if (this.field25 != null && !this.field25.isReloading()) {
         try {
            DriverViewportLegacy.method50().method16(DriverRouteRegistry.field3);
            this.field25.stop();
         } catch (Exception exception4) {
            Ref.method4().method69().method8("RewindMod", "Error when closing RewindMod: " + exception4.getMessage());
            throw new RuntimeException(exception4);
         }

         ModLifecycle framework10extension1 = (ModLifecycle)this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field12);
         if (framework10extension1 != null) {
            framework10extension1.method5(this.field25);
         }

         try {
            this.method17();
         } catch (Exception exception3) {
            exception3.printStackTrace();
         }

         this.field25 = null;
      }
   }

   private void method17() {
      try {
         for (Framework7Extension framework7extension2 : Ref.method4().method40().IIORHHIRHIORHRCCCOICCRCHRRCCRH()) {
            OptionContainer framework53 = (OptionContainer)framework7extension2.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field14);
            if (framework53 != null) {
               for (ClientOption lightingextension6 : framework53.method2()) {
                  if (lightingextension6 instanceof MultiSelectOption) {
                     lightingextension6.reset();
                  }
               }
            }
         }
      } catch (Exception exception10) {
      }

      try {
         GameOptionsBridge mixinhelper2_811 = Ref.method3().bridge$getGameSettings();
         mixinhelper2_811.bridge$loadOptions();
         mixinhelper2_811.bridge$setHideGui(false);
         mixinhelper2_811.bridge$setThirdPersonView(0);
      } catch (Exception exception9) {
      }

      try {
         Ref.method4().method41().init();
      } catch (Exception exception8) {
      }

      try {
         Ref.method4().method40().HHIHOOIHCOHOIRORCHICOCHCORROCR();
      } catch (Exception exception7) {
      }
   }

   public boolean isRecording() {
      return this.field24 != null && this.field24.isRecording();
   }

   public boolean method19() {
      return this.field25 != null;
   }

   public boolean method17(Function<RewindHandlers, Boolean> function1) {
      return !this.method19() ? false : (Boolean)function1.apply(this.field25);
   }

   private void method18(EventRenderBossBar highlightimpl101) {
      if (this.method17(arg0 -> !arg0.method53().method19())) {
         highlightimpl101.setCancelled(true);
      }
   }

   public String getId() {
      return "REWIND";
   }

   protected List<Framework7Extension> method9() {
      return List.of(this.field23);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         "recording",
         PhosphorIcon.PI_VIDEO_RECORDING_SOLID,
         arg1x -> arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
            new ClientOption[]{this.field10, this.field11, this.field12, this.field13, this.field14, this.field15, this.field16}
         )
      );
      lightingextension231.method7(
         "shadowRewindCategory",
         PhosphorIcon.PI_DOUBLE_CHEVRON_LEFT_SOLID,
         arg1x -> arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
            this.field17, arg1xx -> arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field18, this.field19})
         )
      );
      lightingextension231.method7(
         "audio",
         PhosphorIcon.PI_VOLUME_TWO_SOLID,
         arg1x -> {
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field20, arg1xx -> arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field21}));
            arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field22})
               .method3(() -> OperatingSystem.isMacos() && OperatingSystem.getMacOsMajorVersion() < 13);
         }
      );
   }

   public boolean method7() {
      return false;
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field3}).method11(this);
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
   public EnumOption<ReplayClipDuration> method29() {
      return this.field19;
   }

   @Generated
   public DynamicDropdownOption method30() {
      return this.field21;
   }

   @Generated
   public RewindRecorder method34() {
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
