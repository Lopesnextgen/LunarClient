package com.moonsworth.lunar.legacy.mixin;

import com.google.gson.JsonPrimitive;
import com.moonsworth.lunar.bridge.Bridge5_6;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper2$Type;
import com.moonsworth.lunar.bridge.MixinHelper2$Type2;
import com.moonsworth.lunar.bridge.MixinHelper2$Type3;
import com.moonsworth.lunar.bridge.MixinHelper2$Type4;
import com.moonsworth.lunar.bridge.GameOptionsBridge;
import com.moonsworth.lunar.bridge.MixinHelper_15;
import com.moonsworth.lunar.client.framework.UnfocusedFpsLimiter;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.options.OptionsSavedEvent;
import com.moonsworth.lunar.client.config.migration.VanillaOptionsFile;
import com.moonsworth.lunar.client.config.migration.FovOptionMigration;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import com.moonsworth.lunar.legacy.Bridge2Handler;
import com.moonsworth.lunar.legacy.wrapper.GameSettingsAccess;
import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.settings.KeyBinding_v1_7;
import net.minecraft.client.settings.GameSettings.Options;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.nbt.NBTTagCompound;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameSettings.class)
public abstract class GameSettingsMixin implements GameOptionsBridge, GameSettingsAccess {
   @Shadow
   public int thirdPersonView;
   @Shadow
   public int renderDistanceChunks;
   @Shadow
   public float gammaSetting;
   @Shadow
   public boolean showDebugInfo;
   @Shadow
   public boolean hideGUI;
   @Shadow
   public boolean fancyGraphics;
   @Shadow
   public float chatScale;
   @Shadow
   public boolean enableVsync;
   @Shadow
   public int limitFramerate;
   @Shadow
   public Minecraft mc;
   @Shadow
   public boolean smoothCamera;
   @Shadow
   public File optionsFile;
   @Shadow
   public boolean useVbo;
   @Shadow
   public int ambientOcclusion;
   @Shadow
   public boolean entityShadows;
   @Shadow
   public boolean clouds$v1_7;
   @Shadow
   public int clouds;
   @Shadow
   public int particleSetting;
   @Shadow
   public float fovSetting;
   @Shadow
   public KeyBinding keyBindScreenshot;
   @Shadow
   public KeyBinding_v1_7 keyBindScreenshot$v1_7;
   @Shadow
   public KeyBinding keyBindForward;
   @Shadow
   public KeyBinding_v1_7 keyBindForward$v1_7;
   @Shadow
   public KeyBinding keyBindLeft;
   @Shadow
   public KeyBinding_v1_7 keyBindLeft$v1_7;
   @Shadow
   public KeyBinding keyBindBack;
   @Shadow
   public KeyBinding_v1_7 keyBindBack$v1_7;
   @Shadow
   public KeyBinding keyBindRight;
   @Shadow
   public KeyBinding_v1_7 keyBindRight$v1_7;
   @Shadow
   public KeyBinding keyBindJump;
   @Shadow
   public KeyBinding_v1_7 keyBindJump$v1_7;
   @Shadow
   public KeyBinding keyBindAttack;
   @Shadow
   public KeyBinding_v1_7 keyBindAttack$v1_7;
   @Shadow
   public KeyBinding keyBindUseItem;
   @Shadow
   public KeyBinding_v1_7 keyBindUseItem$v1_7;
   @Shadow
   public KeyBinding keyBindSprint;
   @Shadow
   public KeyBinding_v1_7 keyBindSprint$v1_7;
   @Shadow
   public KeyBinding keyBindSneak;
   @Shadow
   public KeyBinding_v1_7 keyBindSneak$v1_7;
   @Shadow
   public KeyBinding keyBindPlayerList;
   @Shadow
   public KeyBinding_v1_7 keyBindPlayerList$v1_7;
   @Shadow
   public KeyBinding keyBindTogglePerspective;
   @Shadow
   public KeyBinding_v1_7 keyBindTogglePerspective$v1_7;
   @Shadow
   public KeyBinding[] keyBindings;
   @Shadow
   public KeyBinding_v1_7[] keyBindings$v1_7;
   @Shadow
   public boolean forceUnicodeFont;
   @Shadow
   public int guiScale;
   @Shadow
   public KeyBinding_v1_7 keyBindPickBlock$v1_7;
   @Shadow
   public KeyBinding keyBindPickBlock;
   @Shadow
   public List resourcePacks$v1_7;
   @Shadow
   public List<String> resourcePacks;
   @Shadow
   public KeyBinding_v1_7 keyBindStreamCommercials$v1_7;
   @Shadow
   public KeyBinding keyBindStreamToggleMic;
   @Shadow
   public KeyBinding keyBindStreamStartStop;
   @Shadow
   public KeyBinding keyBindStreamPauseUnpause;
   @Shadow
   public KeyBinding_v1_7 keyBindStreamStartStop$v1_7;
   @Shadow
   public KeyBinding_v1_7 keyBindStreamPauseUnpause$v1_7;
   @Shadow
   public KeyBinding_v1_7 keyBindStreamToggleMic$v1_7;
   @Shadow
   public boolean chatLinks;
   @Shadow
   public boolean chatLinksPrompt;
   @Shadow
   public KeyBinding keyBindDrop;
   @Shadow
   public KeyBinding_v1_7 keyBindDrop$v1_7;
   @Shadow
   public boolean showSubtitles$v1_12;
   @Final
   @Shadow
   public Set<EnumPlayerModelParts> setModelParts;
   @Shadow
   public boolean advancedItemTooltips;
   @Shadow
   public int attackIndicator$v1_12;
   @Unique
   private boolean lunar$gammaOverridden;
   @Unique
   private float lunar$savedGamma;
   @Unique
   private float originalOptiFineChunkLoading;

   @Shadow
   public abstract void setOptionFloatValue(Options var1, float var2);

   @Shadow
   public abstract Set<EnumPlayerModelParts> getModelParts();

   @Shadow
   public abstract void saveOptions();

   @Shadow
   public abstract void loadOptions();

   @Shadow
   public abstract boolean isUsingNativeTransport();

   @Shadow
   public abstract float getSoundLevel(SoundCategory var1);

   @Override
   public void bridge$loadOptions() {
      this.loadOptions();
   }

   @Override
   public void bridge$saveOptions() {
      this.saveOptions();
   }

   @Override
   public void bridge$setThirdPersonView(int var1) {
      this.thirdPersonView = var1;
   }

   @Override
   public int bridge$getThirdPersonView() {
      return this.thirdPersonView;
   }

   @Override
   public KeyCode bridge$getScreenshotKey() {
      return ThreadModuleDump63.MC_VERSION >= 1
         ? Bridge2Handler.method7(this.keyBindScreenshot.getKeyCode())
         : Bridge2Handler.method7(this.keyBindScreenshot$v1_7.getKeyCode());
   }

   @Override
   public MixinHelper_15 bridge$getDropKey() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? (MixinHelper_15)this.keyBindDrop : (MixinHelper_15)this.keyBindDrop$v1_7;
   }

   @Override
   public MixinHelper_15 bridge$keyBindForward() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? (MixinHelper_15)this.keyBindForward : (MixinHelper_15)this.keyBindForward$v1_7;
   }

   @Override
   public MixinHelper_15 bridge$keyBindLeft() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? (MixinHelper_15)this.keyBindLeft : (MixinHelper_15)this.keyBindLeft$v1_7;
   }

   @Override
   public MixinHelper_15 bridge$keyBindBack() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? (MixinHelper_15)this.keyBindBack : (MixinHelper_15)this.keyBindBack$v1_7;
   }

   @Override
   public MixinHelper_15 bridge$keyBindRight() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? (MixinHelper_15)this.keyBindRight : (MixinHelper_15)this.keyBindRight$v1_7;
   }

   @Override
   public MixinHelper_15 bridge$keyBindJump() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? (MixinHelper_15)this.keyBindJump : (MixinHelper_15)this.keyBindJump$v1_7;
   }

   @Override
   public MixinHelper_15 bridge$keyBindAttack() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? (MixinHelper_15)this.keyBindAttack : (MixinHelper_15)this.keyBindAttack$v1_7;
   }

   @Override
   public MixinHelper_15 bridge$keyBindUseItem() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? (MixinHelper_15)this.keyBindUseItem : (MixinHelper_15)this.keyBindUseItem$v1_7;
   }

   @Override
   public MixinHelper_15 bridge$keyBindPickBlock() {
      return ThreadModuleDump63.MC_VERSION <= 0 ? (MixinHelper_15)this.keyBindPickBlock$v1_7 : (MixinHelper_15)this.keyBindPickBlock;
   }

   @Override
   public MixinHelper_15 bridge$keyBindSprint() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? (MixinHelper_15)this.keyBindSprint : (MixinHelper_15)this.keyBindSprint$v1_7;
   }

   @Override
   public MixinHelper_15 bridge$keyBindSneak() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? (MixinHelper_15)this.keyBindSneak : (MixinHelper_15)this.keyBindSneak$v1_7;
   }

   @Override
   public MixinHelper_15 bridge$keyBindPlayerList() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? (MixinHelper_15)this.keyBindPlayerList : (MixinHelper_15)this.keyBindPlayerList$v1_7;
   }

   @Override
   public MixinHelper_15 bridge$keyBindTogglePerspective() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? (MixinHelper_15)this.keyBindTogglePerspective : (MixinHelper_15)this.keyBindTogglePerspective$v1_7;
   }

   @Override
   public int bridge$getRenderDistance() {
      return this.renderDistanceChunks;
   }

   @Override
   public void bridge$setGamma(float var1) {
      if (this.lunar$gammaOverridden) {
         this.lunar$savedGamma = var1;
      } else {
         this.gammaSetting = var1;
      }
   }

   @Override
   public void bridge$setGammaOverride(float var1) {
      if (!this.lunar$gammaOverridden) {
         this.lunar$gammaOverridden = true;
         this.lunar$savedGamma = this.gammaSetting;
      }

      this.gammaSetting = var1;
   }

   @Override
   public void bridge$removeGammaOverride() {
      if (this.lunar$gammaOverridden) {
         this.lunar$gammaOverridden = false;
         this.gammaSetting = this.lunar$savedGamma;
      }
   }

   @Inject(method = "setOptionFloatValue", at = @At("HEAD"), cancellable = true)
   private void impl$setOption(Options var1, float var2, CallbackInfo var3) {
      if (var1 == Options.GAMMA) {
         ThreadModuleDump63.method4().method40().method56().method16();
      }
   }

   @Redirect(
      method = {"setOptionFloatValue()V", "loadOptions()V", "resetSettings()V"},
      at = @At(value = "FIELD", target = "net/minecraft/client/settings/GameSettings.gammaSetting : F", opcode = 181)
   )
   private void impl$redirectGammaSet(GameSettings var1, float var2) {
      if (this.lunar$gammaOverridden) {
         this.lunar$savedGamma = var2;
      } else {
         this.gammaSetting = var2;
      }
   }

   @Override
   public boolean bridge$showDebugInfo() {
      return this.showDebugInfo;
   }

   @Override
   public Set<Bridge5_6> bridge$getModelParts() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? this.getModelParts() : null;
   }

   @Override
   public boolean bridge$isHideGui() {
      return this.hideGUI;
   }

   @Override
   public void bridge$setHideGui(boolean var1) {
      this.hideGUI = var1;
   }

   @Override
   public int bridge$getGuiScale() {
      return this.guiScale;
   }

   @Override
   public MixinHelper_15[] bridge$getKeyBindings() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? (MixinHelper_15[])this.keyBindings : (MixinHelper_15[])this.keyBindings$v1_7;
   }

   @Override
   public void bridge$setKeyBinds(MixinHelper_15[] var1) {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         this.keyBindings = (KeyBinding[])var1;
      } else {
         this.keyBindings$v1_7 = (KeyBinding_v1_7[])var1;
      }
   }

   @Override
   public float bridge$getChatScale() {
      return this.chatScale;
   }

   @Annotation2(min = 1)
   @Override
   public boolean bridge$showHat() {
      return this.setModelParts.contains(EnumPlayerModelParts.HAT);
   }

   @Annotation2(min = 1)
   @Override
   public boolean bridge$showJacket() {
      return this.setModelParts.contains(EnumPlayerModelParts.JACKET);
   }

   @Annotation2(min = 1)
   @Override
   public boolean bridge$showLeftSleeve() {
      return this.setModelParts.contains(EnumPlayerModelParts.LEFT_SLEEVE);
   }

   @Annotation2(min = 1)
   @Override
   public boolean bridge$showRightSleeve() {
      return this.setModelParts.contains(EnumPlayerModelParts.RIGHT_SLEEVE);
   }

   @Annotation2(min = 1)
   @Override
   public boolean bridge$showLeftPant() {
      return this.setModelParts.contains(EnumPlayerModelParts.LEFT_PANTS_LEG);
   }

   @Annotation2(min = 1)
   @Override
   public boolean bridge$showRightPant() {
      return this.setModelParts.contains(EnumPlayerModelParts.RIGHT_PANTS_LEG);
   }

   @Override
   public void bridge$setOptionFloatValue(int var1, float var2) {
      if (ThreadModuleDump63.MC_VERSION == 5) {
         this.setOptionFloatValue(Options.byOrdinal$v1_12(var1), var2);
      } else {
         if (ThreadModuleDump63.MC_VERSION <= 0 && var1 == 25) {
            this.chatScale = var2;
            this.mc.ingameGUI.getChatGUI().refreshChat();
         }

         this.setOptionFloatValue(Options.getEnumOptions(var1), var2);
      }
   }

   @Override
   public void bridge$setKeyBindState(KeyCode var1, boolean var2) {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         KeyBinding.setKeyBindState(Bridge2Handler.method6(var1), var2);
      } else {
         KeyBinding_v1_7.setKeyBindState(Bridge2Handler.method6(var1), var2);
      }
   }

   @Override
   public void bridge$unpressAllKeys() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         KeyBinding.unPressAllKeys();
      } else {
         KeyBinding_v1_7.unPressAllKeys();
      }
   }

   @Override
   public void bridge$setSmoothCamera(boolean var1) {
      this.smoothCamera = var1;
   }

   @Override
   public boolean bridge$getSmoothCamera() {
      return this.smoothCamera;
   }

   @Override
   public void bridge$setFancyGraphics(boolean var1) {
      this.fancyGraphics = var1;
   }

   @Override
   public boolean bridge$isFancyGraphics() {
      return this.fancyGraphics;
   }

   @Override
   public boolean bridge$isFabulousGraphics() {
      return false;
   }

   @Unique
   private void impl$swapSavedGamma() {
      float var1 = this.gammaSetting;
      this.gammaSetting = this.lunar$savedGamma;
      this.lunar$savedGamma = var1;
   }

   @Inject(method = "saveOptions", at = @At("HEAD"))
   private void lunar$saveOptions(CallbackInfo var1) {
      if (UnfocusedFpsLimiter.method2().isPresent() && !UnfocusedFpsLimiter.method4()) {
         this.limitFramerate = UnfocusedFpsLimiter.method2().getAsInt();
         UnfocusedFpsLimiter.method5(true);
      }

      if (this.lunar$gammaOverridden) {
         this.impl$swapSavedGamma();
      }
   }

   @Inject(method = "loadOptions", at = @At("HEAD"))
   public void impl$beforeLoadOptions(CallbackInfo var1) {
      if (this.lunar$gammaOverridden) {
         this.impl$swapSavedGamma();
      }

      VanillaOptionsFile.method1(this.optionsFile.getParentFile());
   }

   @Inject(method = "loadOptions", at = @At("RETURN"))
   public void impl$afterLoadOptions(CallbackInfo var1) {
      if (this.lunar$gammaOverridden) {
         this.impl$swapSavedGamma();
      }
   }

   @Annotation2(min = 5)
   @Redirect(method = "loadOptions", at = @At(target = "Lnet/minecraft/nbt/NBTTagCompound;getString(Ljava/lang/String;)Ljava/lang/String;", value = "INVOKE"))
   public String impl$getString(NBTTagCompound var1, String var2) {
      return VanillaOptionsFile.method3(var2, new JsonPrimitive(var1.getString(var2))).getAsString();
   }

   @Redirect(method = "saveOptions", at = @At(target = "Ljava/io/PrintWriter;println(Ljava/lang/String;)V", value = "INVOKE"))
   public void impl$saveSetting(PrintWriter var1, String var2) {
      VanillaOptionsFile.method5(var2);
      var1.println(var2);
   }

   @Inject(method = "saveOptions", at = @At("TAIL"))
   public void impl$saveOptionsOverwrite(CallbackInfo var1) {
      ClientEventBus.method29().method12(OptionsSavedEvent.class, OptionsSavedEvent::new);
      VanillaOptionsFile.method2(this.optionsFile.getParentFile());
      if (this.lunar$gammaOverridden) {
         this.impl$swapSavedGamma();
      }
   }

   @Override
   public int bridge$getFrameRateLimit() {
      return this.limitFramerate;
   }

   @Override
   public void bridge$setFrameRateLimit(int var1) {
      this.limitFramerate = var1;
   }

   @Override
   public boolean bridge$getVSync() {
      return this.enableVsync;
   }

   @Override
   public boolean bridge$getAmbientOcclusion() {
      return this.ambientOcclusion > 0;
   }

   @Override
   public boolean bridge$getEntityShadows() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? this.entityShadows : true;
   }

   @Override
   public double bridge$getEntityScaling() {
      return 100.0;
   }

   @Override
   public MixinHelper2$Type3 bridge$getPrioritizeChunkUpdates() {
      return MixinHelper2$Type3.UNSPECIFIED;
   }

   @Override
   public MixinHelper2$Type2 bridge$getCloudStatus() {
      if (ThreadModuleDump63.MC_VERSION <= 0) {
         return this.clouds$v1_7 ? MixinHelper2$Type2.FAST : MixinHelper2$Type2.OFF;
      } else {
         return this.clouds == 2 ? MixinHelper2$Type2.FANCY : (this.clouds == 1 ? MixinHelper2$Type2.FAST : MixinHelper2$Type2.OFF);
      }
   }

   @Override
   public MixinHelper2$Type bridge$getParticleStatus() {
      return this.particleSetting == 2 ? MixinHelper2$Type.ALL : (this.particleSetting == 1 ? MixinHelper2$Type.DECREASED : MixinHelper2$Type.MINIMAL);
   }

   @Override
   public int bridge$getBiomeBlend() {
      return 0;
   }

   @Override
   public int bridge$getFov() {
      return (int)Math.round(FovOptionMigration.method3(this.fovSetting));
   }

   @Override
   public void bridge$setVBO(boolean var1) {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         this.useVbo = var1;
      }
   }

   @Override
   public boolean bridge$isChatLinks() {
      return this.chatLinks;
   }

   @Override
   public boolean bridge$isChatPromptLinks() {
      return this.chatLinksPrompt;
   }

   @Override
   public double bridge$getNotificationDisplayTime() {
      return 1.0;
   }

   @Override
   public boolean bridge$isShowSubtitles() {
      return ThreadModuleDump63.MC_VERSION == 5 ? this.showSubtitles$v1_12 : false;
   }

   @Override
   public void bridge$setShowSubtitles(boolean var1) {
      if (ThreadModuleDump63.MC_VERSION == 5) {
         this.showSubtitles$v1_12 = var1;
      }
   }

   @Annotation2(min = 5)
   @Override
   public boolean bridge$isAttackIndicatorEnabled() {
      return this.attackIndicator$v1_12 != 0;
   }

   @Override
   public boolean bridge$isAdvancedItemTooltips() {
      return this.advancedItemTooltips;
   }

   @Override
   public float bridge$getMasterVolume() {
      return this.getSoundLevel(SoundCategory.MASTER);
   }

   @Override
   public boolean bridge$isStreamKey(KeyCode var1) {
      int var2 = Bridge2Handler.method6(var1);
      if (ThreadModuleDump63.MC_VERSION <= 0) {
         return this.keyBindStreamCommercials$v1_7.getKeyCode() == var2
            || this.keyBindStreamToggleMic$v1_7.getKeyCode() == var2
            || this.keyBindStreamStartStop$v1_7.getKeyCode() == var2
            || this.keyBindStreamPauseUnpause$v1_7.getKeyCode() == var2;
      } else {
         return ThreadModuleDump63.MC_VERSION != 1
            ? false
            : this.keyBindStreamToggleMic.getKeyCode() == var2
               || this.keyBindStreamStartStop.getKeyCode() == var2
               || this.keyBindStreamPauseUnpause.getKeyCode() == var2;
      }
   }

   @Annotation2(min = 1)
   @Override
   public boolean bridge$isNativeTransport() {
      return this.isUsingNativeTransport();
   }

   @Override
   public MixinHelper2$Type4 bridge$getTextureFiltering() {
      return MixinHelper2$Type4.NONE;
   }

   @Annotation2(max = 0)
   @Inject(
      method = "loadOfOptions",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/settings/GameSettings;updateChunkLoading()V", shift = Shift.BEFORE),
      require = 0,
      expect = 0
   )
   private void impl$onLoadOfOptions(CallbackInfo var1) {
      this.originalOptiFineChunkLoading = this.HHCCROIIIIOIOIOHIIIHIICHOIIOOH();
   }

   @Annotation2(max = 0)
   @Inject(method = "updateChunkLoading", at = @At("HEAD"), cancellable = true, require = 0, expect = 0)
   private void impl$onUpdateChunkLoading(CallbackInfo var1) {
      if (this.originalOptiFineChunkLoading != 0.0F) {
         this.IOORROCRCOIRIHOICRHHOHIHHHHROO(0);
         this.originalOptiFineChunkLoading = 0.0F;
      } else {
         this.IOORROCRCOIRIHOICRHHOHIHHHHROO(0);
         var1.cancel();
      }
   }

   @Redirect(
      method = "loadOptions",
      at = @At(
         target = "Lnet/minecraft/client/settings/GameSettings;dataFix$v1_12(Lnet/minecraft/nbt/NBTTagCompound;)Lnet/minecraft/nbt/NBTTagCompound;",
         value = "INVOKE"
      )
   )
   @Annotation2(min = 5)
   public NBTTagCompound impl$loadOptions$split$v1_12(GameSettings var1, NBTTagCompound var2) {
      NBTTagCompound var3 = new NBTTagCompound();

      for (String var5 : var2.getKeySet()) {
         String var6 = var2.getString(var5);
         if (var5.equalsIgnoreCase("maxFps")) {
            this.limitFramerate = Integer.parseInt(var6);
            if (this.limitFramerate <= 0) {
               this.limitFramerate = (int)Options.FRAMERATE_LIMIT.getValueMax();
            }
         } else if (var5.equalsIgnoreCase("enableVsync")) {
            this.enableVsync = Boolean.parseBoolean(var6);
            this.bridge$updateVSync();
         } else {
            var3.setString(var5, var6);
         }
      }

      return var3;
   }

   @Redirect(method = "loadOptions", at = @At(target = "Ljava/lang/String;split(Ljava/lang/String;)[Ljava/lang/String;", value = "INVOKE"))
   @Annotation2(max = 1)
   public String[] impl$loadOptions$split(String var1, String var2) {
      if (var2.equals(":")) {
         if (var1.startsWith("maxFps:")) {
            String[] var5 = var1.split(":");
            if (var5.length > 1) {
               this.limitFramerate = Integer.parseInt(var5[1]);
               if (this.limitFramerate <= 0) {
                  this.limitFramerate = (int)Options.FRAMERATE_LIMIT.getValueMax();
               }
            }

            return new String[]{"Invalid", "1"};
         }

         if (var1.startsWith("enableVsync:")) {
            String[] var4 = var1.split(":");
            if (var4.length > 1) {
               this.enableVsync = Boolean.parseBoolean(var4[1]);
               this.bridge$updateVSync();
            }

            return new String[]{"Invalid", "1"};
         }
      }

      String[] var3 = var1.split(var2);
      return var3.length == 1 ? new String[]{"Invalid", "1"} : new String[]{var3[0], VanillaOptionsFile.method3(var3[0], new JsonPrimitive(var3[1])).getAsString()};
   }

   @Override
   public void bridge$setForceUnicode(boolean var1) {
      this.forceUnicodeFont = var1;
      this.mc.fontRendererObj.setUnicodeFlag(this.mc.getLanguageManager().isCurrentLocaleUnicode() || this.forceUnicodeFont);
      this.saveOptions();
      this.mc.refreshResources();
   }

   @Override
   public boolean bridge$isForceUnicode() {
      return this.forceUnicodeFont;
   }
}
