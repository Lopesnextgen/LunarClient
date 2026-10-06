package com.moonsworth.lunar.legacy.mixin;

import com.google.common.collect.ImmutableSet;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import com.moonsworth.lunar.bridge.Bridge2_20;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.Bridge5Extension610;
import com.moonsworth.lunar.bridge.Bridge5Extension62;
import com.moonsworth.lunar.bridge.Bridge5Extension66;
import com.moonsworth.lunar.bridge.Bridge7_8;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.mainmenu.MainMenuButton;
import com.moonsworth.lunar.client.framework.feature.gui.Gui2;
import com.moonsworth.lunar.client.framework.feature.mod.GuiRewindhandlersHandler2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate2;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.render.ScreenInitEvent;
import com.moonsworth.lunar.client.event.input.KeyInputEvent;
import com.moonsworth.lunar.client.event.input.MarkerInputEvent;
import com.moonsworth.lunar.client.event.render.TooltipRenderEvent;
import com.moonsworth.lunar.client.event.render.ButtonRenderEvent;
import com.moonsworth.lunar.client.event.mixin.chat.EventChatSendLegacy;
import com.moonsworth.lunar.client.event.input.KeyInputTypeLegacy;
import com.moonsworth.lunar.client.event.input.MouseInputTypeLegacy;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventMouseScrollLegacy;
import com.moonsworth.lunar.client.driver.DriverRouteRegistryLegacy;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.driver.core.DriverViewLegacy;
import com.moonsworth.lunar.client.mod.misc.chat.Chat;
import com.moonsworth.lunar.client.mod.misc.rewind.Rewind;
import com.moonsworth.lunar.client.mod.render.menublur.MenuBlur;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump71;
import com.moonsworth.lunar.ichor.Annotation2;
import com.moonsworth.lunar.ichor.Annotation_2;
import com.moonsworth.lunar.legacy.Bridge2Handler;
import com.moonsworth.lunar.legacy.Bridge3Handler;
import com.moonsworth.lunar.legacy.MixinCore3;
import com.moonsworth.lunar.legacy.MixinMisc;
import java.io.File;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiControls;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.gui.GuiCustomizeSkin;
import net.minecraft.client.gui.GuiDownloadTerrain;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiRenameWorld;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenAddServer;
import net.minecraft.client.gui.GuiScreenOptionsSounds;
import net.minecraft.client.gui.GuiScreenServerList;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.gui.GuiVideoSettings;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.ScreenChatOptions;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.client.gui.advancements.GuiScreenAdvancements;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.gui.stream.GuiStreamOptions;
import net.minecraft.client.gui.stream.GuiStreamUnavailable;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.text.event.ClickEvent.Action;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GuiScreen.class)
public abstract class GuiScreenEventsMixin implements Bridge5Extension6 {
   @Shadow
   public Minecraft mc;
   @Shadow
   public int width;
   @Shadow
   public int height;
   @Unique
   private static boolean lunar$lazyInit = true;
   @Unique
   private static final Set<Class<?>> LUNAR$HIDE_BRAND_ON = ImmutableSet.of(
      GuiMultiplayer.class,
      GuiScreenServerList.class,
      GuiScreenAddServer.class,
      GuiChat.class,
      GuiControls.class,
      GuiSelectWorld.class,
      new Class[]{
         GuiRenameWorld.class, GuiConnecting.class, GuiCreateWorld.class, GuiLanguage.class, GuiYesNo.class, GuiDownloadTerrain.class, GuiMainMenu.class
      }
   );

   @Shadow
   public abstract void initGui();

   @Shadow
   public static boolean isShiftKeyDown() {
      return false;
   }

   @Annotation2(min = 1)
   @WrapMethod(method = "sendChatMessage$v1_8(Ljava/lang/String;Z)V")
   private void lunar$wrapSendChat(String var1, boolean var2, Operation<Void> var3) {
      EventChatSendLegacy var4 = ClientEventBus.method29().method12(EventChatSendLegacy.class, () -> new EventChatSendLegacy(var1));
      boolean var5 = var4 != null && var4.isCancelled();
      if (!var5) {
         String var6 = var4 == null ? var1 : var4.getMessage();
         Chat.method85(var6);
         var3.call(new Object[]{var6, var2});
      }
   }

   @Annotation2(max = 0)
   @WrapWithCondition(method = "keyTyped", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;setIngameFocus()V"))
   private boolean lunar$shouldWrapKeyTyped(Minecraft var1) {
      return var1.currentScreen == null;
   }

   @Inject(method = "handleMouseInput", at = @At("HEAD"))
   private void lunar$webosr$handleMouseInput(CallbackInfo var1) {
      Bridge7_8 var2 = null;
      if (this instanceof Bridge5Extension62 var3) {
         var2 = var3.method2();
      }

      if (var2 instanceof MainMenuButton || this.lunar$forwardInput()) {
         int var5 = Mouse.getEventButton();
         if (var5 != -1) {
            boolean var6 = Mouse.getEventButtonState();
            DriverViewLegacy.method21().method8(var5, var6 ? 1 : 0, Bridge3Handler.method2());
         }
      }
   }

   @Unique
   private boolean lunar$forwardInput() {
      if (Client.method109() == null) {
         return false;
      }

      if (this instanceof Bridge5Extension610 && Client.method109().method25()) {
         return true;
      }

      DriverViewportLegacy var1 = DriverViewportLegacy.method50();
      if (var1 == null) {
         return false;
      }

      DriverRouteRegistryLegacy var2 = var1.method61();
      return var2.method25() != null && var2.method25().isInstance(this);
   }

   @Inject(method = "keyTyped", at = @At("HEAD"), cancellable = true)
   private void lunar$fixConfirmGuiCallback(char var1, int var2, CallbackInfo var3) {
      if (this instanceof Bridge5Extension66 var4) {
         if (var2 == 1) {
            var4.bridge$getYesNoCallback().accept(false, var4.bridge$getParentButtonClickedId());
            if (this.mc.currentScreen == null) {
               this.mc.setIngameFocus();
            }

            var3.cancel();
         }
      }
   }

   @WrapMethod(method = "setWorldAndResolution")
   private void lunar$wrapSetWorldAndResolution(Minecraft var1, int var2, int var3, Operation<Void> var4) {
      if (ThreadModuleDump63.method2()
         && (
            ThreadModuleDump63.method4() == null
               || ThreadModuleDump63.method4().method40() == null
               || !ThreadModuleDump63.method4().method40().method85().method19()
         )) {
         LcuiScreen.method150(new ThreadModuleDump71(ThreadModuleDump63.method3()));
      }

      var4.call(new Object[]{var1, var2, var3});
      ClientEventBus.method29().method12(ScreenInitEvent.ScreenInitPostEvent.class, () -> new ScreenInitEvent.ScreenInitPostEvent(this));
   }

   @Inject(
      method = "setWorldAndResolution(Lnet/minecraft/client/Minecraft;II)V",
      at = @At(value = "INVOKE", target = "Ljava/util/List;clear()V", shift = Shift.BEFORE, ordinal = 0)
   )
   private void lunar$setWorldAndResolution$pre(Minecraft var1, int var2, int var3, CallbackInfo var4) {
      ClientEventBus.method29().method12(ScreenInitEvent.ScreenInitPreEvent.class, () -> new ScreenInitEvent.ScreenInitPreEvent(this));
   }

   @WrapMethod(method = "handleMouseInput")
   private void lunar$onMouseScroll(Operation<Void> var1) {
      int var2 = Mouse.getEventDWheel();
      if (var2 != 0) {
         EventMouseScrollLegacy var3 = ClientEventBus.method29().method12(EventMouseScrollLegacy.class, () -> new EventMouseScrollLegacy(var2 / 120.0));
         if (var3 != null && var3.isCancelled()) {
            return;
         }
      }

      var1.call(new Object[0]);
   }

   @Annotation2(max = 1)
   @Redirect(method = "handleKeyboardInput", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;getEventKeyState()Z"))
   private boolean lunar$fixChineseCharacters() {
      return Keyboard.getEventKeyState() || Keyboard.getEventKey() == 0 && Character.isDefined(Keyboard.getEventCharacter());
   }

   @WrapOperation(
      method = "mouseClicked",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiScreen;actionPerformed(Lnet/minecraft/client/gui/GuiButton;)V")
   )
   private void lunar$onMouseClicked(GuiScreen var1, GuiButton var2, Operation<Void> var3) {
      ButtonRenderEvent var4 = ClientEventBus.method29().method12(ButtonRenderEvent.class, () -> new ButtonRenderEvent((Bridge5Extension6)var1, (Bridge2_20)var2));
      if (var4 == null || !var4.isCancelled()) {
         var3.call(new Object[]{var1, var2});
      }
   }

   @Annotation2(max = 1)
   @WrapOperation(
      method = "drawScreen",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiButton;drawButton$v1_7(Lnet/minecraft/client/Minecraft;II)V")
   )
   private void lunar$tintButton$v1_7(GuiButton var1, Minecraft var2, int var3, int var4, Operation<Void> var5) {
      Gui2.method17(() -> var5.call(new Object[]{var1, var2, var3, var4}));
   }

   @Annotation2(min = 5)
   @WrapOperation(
      method = "drawScreen",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiButton;drawButton$v1_12(Lnet/minecraft/client/Minecraft;IIF)V")
   )
   private void lunar$tintButton$v1_12(GuiButton var1, Minecraft var2, int var3, int var4, float var5, Operation<Void> var6) {
      Gui2.method17(() -> var6.call(new Object[]{var1, var2, var3, var4, var5}));
   }

   @Inject(method = "drawScreen", at = @At("HEAD"))
   private void lunar$renderLunarClientBrand(CallbackInfo var1) {
      MenuBlur var2 = Client.method109().method40().method43();
      var var3 = (GuiScreenEventsMixin & GuiScreen)this;
      if (!ThreadModuleDump63.method4().method40().method85().method19() && var2.isEnabled() && var2.shouldBlurScreen(var3)) {
         var2.ensureBlurStarted();
      }

      if (!LUNAR$HIDE_BRAND_ON.contains(this.getClass()) && !(var3 instanceof GuiChat)) {
         LcuiScreen.method123(AbstractRenderContext.method32().method42(), this.width, this.height);
      }
   }

   @Inject(
      method = "mouseClicked",
      at = @At(value = "INVOKE", target = "net/minecraft/client/gui/GuiScreen.actionPerformed (Lnet/minecraft/client/gui/GuiButton;)V", shift = Shift.AFTER),
      cancellable = true
   )
   private void lunar$preventMultipleButtonClicksFromOneMouseClick(int var1, int var2, int var3, CallbackInfo var4) {
      var4.cancel();
   }

   @WrapOperation(method = "handleMouseInput", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiScreen;mouseClicked(III)V"))
   private void lunar$mouseClickedEvent(GuiScreen var1, int var2, int var3, int var4, Operation<Void> var5) {
      if (var1 == Minecraft.getMinecraft().currentScreen) {
         MarkerInputEvent var6 = ClientEventBus.method29()
            .method12(
               MarkerInputEvent.class,
               () -> new MarkerInputEvent(this, new com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data4(var2, var3), var4, MouseInputTypeLegacy.CLICK)
            );
         if (var6 != null && var6.isCancelled()) {
            return;
         }
      }

      var5.call(new Object[]{var1, var2, var3, var4});
   }

   @WrapOperation(method = "handleMouseInput", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiScreen;mouseReleased(III)V"))
   private void lunar$mouseReleasedEvent(GuiScreen var1, int var2, int var3, int var4, Operation<Void> var5) {
      if (var1 == Minecraft.getMinecraft().currentScreen) {
         MarkerInputEvent var6 = ClientEventBus.method29()
            .method12(
               MarkerInputEvent.class,
               () -> new MarkerInputEvent(this, new com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data4(var2, var3), var4, MouseInputTypeLegacy.RELEASE)
            );
         if (var6 != null && var6.isCancelled()) {
            return;
         }
      }

      var5.call(new Object[]{var1, var2, var3, var4});
   }

   @WrapOperation(method = "handleMouseInput", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiScreen;mouseClickMove(IIIJ)V"))
   private void lunar$mouseDragEvent(GuiScreen var1, int var2, int var3, int var4, long var5, Operation<Void> var7) {
      if (var1 == Minecraft.getMinecraft().currentScreen) {
         MarkerInputEvent var8 = ClientEventBus.method29().method12(MarkerInputEvent.class, () -> {
            MarkerInputEvent var6 = new MarkerInputEvent(this, new com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data4(var2, var3), var4, MouseInputTypeLegacy.DRAG);
            var6.method10(var5);
            return var6;
         });
         if (var8 != null && var8.isCancelled()) {
            return;
         }
      }

      var7.call(new Object[]{var1, var2, var3, var4, var5});
   }

   @Inject(method = "handleMouseInput", at = @At("HEAD"))
   private void lunar$mouseScrolledEvent(CallbackInfo var1) {
      int var2 = Mouse.getEventDWheel();
      if (var2 != 0 && this == Minecraft.getMinecraft().currentScreen) {
         ClientEventBus.method29().method12(MarkerInputEvent.class, () -> {
            int var2x = Mouse.getEventX() * this.width / this.mc.displayWidth;
            int var3 = this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1;
            MarkerInputEvent var4 = new MarkerInputEvent(this, new com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data4(var2x, var3), -1, MouseInputTypeLegacy.SCROLL);
            var4.method14(var2);
            return var4;
         });
      }
   }

   @WrapOperation(method = "handleKeyboardInput", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiScreen;keyTyped(CI)V"))
   private void lunar$keyTypedEvent(GuiScreen var1, char var2, int var3, Operation<Void> var4) {
      if (var1 == Minecraft.getMinecraft().currentScreen) {
         KeyInputEvent var5 = ClientEventBus.method29()
            .method12(KeyInputEvent.class, () -> new KeyInputEvent(Bridge2Handler.method7(var3), var2, var3, -1, KeyInputTypeLegacy.PRESS));
         if (var5 != null && var5.isCancelled()) {
            return;
         }
      }

      var4.call(new Object[]{var1, var2, var3});
   }

   @Inject(method = "isShiftKeyDown", at = @At("HEAD"), cancellable = true)
   private static void lunar$isShiftKeyDown(CallbackInfoReturnable<Boolean> var0) {
      Rewind var1 = ThreadModuleDump63.method4().method40().method85();
      if (var1.method19()) {
         Nameplate2 var2 = var1.method35().method42().get().method8();
         var0.setReturnValue(var2.method10());
      }
   }

   @Inject(method = "isCtrlKeyDown", at = @At("HEAD"), cancellable = true)
   private static void lunar$isCtrlKeyDown(CallbackInfoReturnable<Boolean> var0) {
      Rewind var1 = ThreadModuleDump63.method4().method40().method85();
      if (var1.method19()) {
         Nameplate2 var2 = var1.method35().method42().get().method8();
         var0.setReturnValue(var2.method11());
      }
   }

   @Redirect(method = "drawWorldBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiScreen;drawGradientRect(IIIIII)V"))
   private void lunar$menuBlurGradient(GuiScreen var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      MenuBlur var8 = Client.method109().method40().method43();
      var var9 = (GuiScreenEventsMixin & GuiScreen)this;
      if (var8.isEnabled()) {
         Optional var10 = var8.getScreenBackgroundColor(var9);
         if (var10.isPresent()) {
            var6 = var7 = (Integer)var10.get();
         }
      }

      var1.drawGradientRect(var2, var3, var4, var5, var6, var7);
   }

   @Inject(method = "drawWorldBackground", at = @At("HEAD"), cancellable = true)
   private void lunar$fixNullMinecraftCheck(int var1, CallbackInfo var2) {
      if (this.mc == null) {
         var2.cancel();
      }
   }

   @Inject(method = "<init>", at = @At("TAIL"))
   private void lunar$blurMenus(CallbackInfo var1) {
      if (lunar$lazyInit && Client.method109() != null) {
         MenuBlur var2 = Client.method109().method40().method43();
         Set var3 = var2.getPauseScreenData().method5();
         var3.add(GuiIngameMenu.class);
         var3.add(GuiVideoSettings.class);
         var3.add(GuiOptions.class);
         var3.add(GuiScreenOptionsSounds.class);
         var3.add(ScreenChatOptions.class);
         if (ThreadModuleDump63.MC_VERSION >= 1) {
            var3.add(GuiCustomizeSkin.class);
         }

         if (ThreadModuleDump63.MC_VERSION < 5) {
            var3.add(GuiStreamOptions.class);
            var3.add(GuiStreamUnavailable.class);
            var3.add(GuiAchievements.class);
         } else {
            var3.add(GuiScreenAdvancements.class);
         }

         Set var4 = var2.getInventoryScreenData().method5();
         var4.add(GuiInventory.class);
         var4.add(GuiContainer.class);
         lunar$lazyInit = false;
      }
   }

   @WrapMethod(method = {"drawHoveringText$v1_7", "drawHoveringText$v1_8"})
   @Annotation_2(absent = "forge")
   private void lunar$wrapTooltipRender$v1_8(
      List<String> var1,
      int var2,
      int var3,
      Operation<Void> var4,
      @Share("context") LocalRef<AbstractRenderContext> var5,
      @Share("guiContext") LocalRef<MixinHelper_4> var6,
      @Share("modifyComponents") LocalRef<TooltipRenderEvent.TooltipPreRenderEvent> var7
   ) {
      if (!MixinCore3.method1(var1, var2, var3, var5, var6, var7)) {
         ((AbstractRenderContext)var5.get()).push();
         var4.call(new Object[]{var1, var2, var3});
         ((AbstractRenderContext)var5.get()).pop();
      }
   }

   @ModifyExpressionValue(method = {"drawHoveringText$v1_7", "drawHoveringText$v1_8"}, at = @At(value = "INVOKE", target = "Ljava/util/List;size()I"))
   @Annotation_2(absent = "forge")
   private int lunar$cancelLoopIter$v1_8(int var1, @Share("modifyComponents") LocalRef<TooltipRenderEvent.TooltipPreRenderEvent> var2) {
      return var2.get() != null && ((TooltipRenderEvent.TooltipPreRenderEvent)var2.get()).isModified() ? 0 : var1;
   }

   @Inject(method = {"drawHoveringText$v1_7", "drawHoveringText$v1_8"}, at = @At(value = "CONSTANT", args = "intValue=12", ordinal = 0))
   @Annotation_2(absent = "forge")
   private static void lunar$updateWidth(
      List<String> var0,
      int var1,
      int var2,
      CallbackInfo var3,
      @Local(ordinal = 2) LocalIntRef var4,
      @Share("actualW") LocalIntRef var5,
      @Share("modifyComponents") LocalRef<TooltipRenderEvent.TooltipPreRenderEvent> var6
   ) {
      MixinCore3.method2(var4, var5, var6);
   }

   @Inject(
      method = {"drawHoveringText$v1_7", "drawHoveringText$v1_8"},
      at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/GuiScreen;width:I", opcode = 180)
   )
   @Annotation_2(absent = "forge")
   private void lunar$updateHeight(
      List<String> var1,
      int var2,
      int var3,
      CallbackInfo var4,
      @Local(ordinal = 6) LocalIntRef var5,
      @Share("actualH") LocalIntRef var6,
      @Share("modifyComponents") LocalRef<TooltipRenderEvent.TooltipPreRenderEvent> var7
   ) {
      MixinCore3.method3(var5, var6, var7);
   }

   @Inject(
      method = {"drawHoveringText$v1_7", "drawHoveringText$v1_8"},
      at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/RenderItem;zLevel:F", opcode = 181, ordinal = 0)
   )
   @Annotation_2(absent = "forge")
   private void lunar$modifyPosition(
      List<String> var1,
      int var2,
      int var3,
      CallbackInfo var4,
      @Local(ordinal = 2) LocalIntRef var5,
      @Local(ordinal = 5) LocalIntRef var6,
      @Local(ordinal = 6) LocalIntRef var7,
      @Local(ordinal = 3) LocalIntRef var8,
      @Local(ordinal = 4) LocalIntRef var9,
      @Share("actualW") LocalIntRef var10,
      @Share("actualH") LocalIntRef var11,
      @Share("context") LocalRef<AbstractRenderContext> var12,
      @Share("guiContext") LocalRef<MixinHelper_4> var13
   ) {
      MixinCore3.method8(var5, var6, var7, var10, var11, var8, var9, var12, var13);
   }

   @Inject(
      method = {"drawHoveringText$v1_7", "drawHoveringText$v1_8"},
      at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/GuiScreen;zLevel:F", ordinal = 1, opcode = 181)
   )
   @Annotation_2(absent = "forge")
   private void lunar$renderComponents(
      List<String> var1,
      int var2,
      int var3,
      CallbackInfo var4,
      @Local(ordinal = 3) int var5,
      @Local(ordinal = 4) int var6,
      @Share("modifyComponents") LocalRef<TooltipRenderEvent.TooltipPreRenderEvent> var7,
      @Share("context") LocalRef<AbstractRenderContext> var8,
      @Share("guiContext") LocalRef<MixinHelper_4> var9
   ) {
      MixinCore3.method9(var5, var6, var7, var8, var9);
   }

   @Annotation2(min = 1)
   @Inject(
      method = "handleComponentClick$v1_8",
      at = @At(value = "FIELD", target = "Lnet/minecraft/client/settings/GameSettings;chatLinksPrompt:Z", opcode = 180, shift = Shift.BEFORE),
      cancellable = true
   )
   private static void lunar$maliciousUrlWarning$v1_8(IChatComponent var0, CallbackInfoReturnable<Boolean> var1) {
      URI var2 = lunar$getClickEventValue$v1_8(var0);
      if (ThreadModuleDump63.method4().method93().method3(ThreadModuleDump63.method3().bridge$getCurrentScreen(), var2)) {
         var1.setReturnValue(false);
      }
   }

   @Annotation2(min = 1)
   @Inject(method = "handleComponentClick$v1_8", at = @At("HEAD"), cancellable = true)
   private static void lunar$preventRewindSystemClickEvent$v1_8(IChatComponent var0, CallbackInfoReturnable<Boolean> var1) {
      if (var0 != null && ThreadModuleDump63.method4().method40().method85().method19() && !isShiftKeyDown()) {
         ClickEvent var2;
         if (ThreadModuleDump63.MC_VERSION == 1) {
            var2 = var0.getChatStyle().getChatClickEvent();
         } else {
            var2 = var0.getStyle$v1_12().getClickEvent$v1_12();
         }

         if (var2 != null && (var2.getAction() == Action.OPEN_FILE || var2.getAction() == Action.OPEN_URL)) {
            var1.setReturnValue(true);
         }
      }
   }

   @Annotation2(min = 1)
   @Unique
   private static URI lunar$getClickEventValue$v1_8(IChatComponent var0) {
      ChatStyle var1;
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         var1 = var0.getStyle$v1_12();
      } else if (ThreadModuleDump63.MC_VERSION >= 1) {
         var1 = var0.getChatStyle();
      } else {
         var1 = null;
      }

      if (var1 == null) {
         return null;
      }

      ClickEvent var2 = ThreadModuleDump63.MC_VERSION >= 5 ? var1.getClickEvent$v1_12() : var1.getChatClickEvent();
      if (var2 != null && var2.getAction() == Action.OPEN_URL) {
         try {
            return new URI(var2.getValue());
         } catch (Exception var4) {
         }
      }

      return null;
   }

   @Annotation2(min = 1)
   @Inject(method = "openWebLink$v1_8", at = @At("HEAD"), cancellable = true)
   private void lunar$openFileWithDefaultApp$v1_8(URI var1, CallbackInfo var2) {
      if ("file".equals(var1.getScheme())) {
         MixinMisc.method1().openFile(new File(var1));
         var2.cancel();
      }
   }

   @Annotation2(min = 1)
   @Inject(method = "handleInput", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiScreen;handleMouseInput()V", shift = Shift.AFTER))
   private void lunar$overwriteGuiScroll(CallbackInfo var1) {
      int var2 = Mouse.getEventDWheel();
      if (var2 != 0) {
         GuiRewindhandlersHandler2.field7.method11().method4(ThreadModuleDump63.method3().bridge$getCurrentScreen(), var2 / 120.0);
      }
   }

   @Annotation2(min = 1)
   @WrapWithCondition(method = "handleMouseInput", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiScreen;mouseClicked(III)V"))
   private boolean lunar$overwriteGuiClick(GuiScreen var1, int var2, int var3, int var4) {
      return !GuiRewindhandlersHandler2.field7.method11().method5(ThreadModuleDump63.method3().bridge$getCurrentScreen(), var2, var3, var4);
   }

   @Annotation2(min = 1)
   @WrapWithCondition(method = "handleMouseInput", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiScreen;mouseReleased(III)V"))
   private boolean lunar$overwriteGuiRelease(GuiScreen var1, int var2, int var3, int var4) {
      return !GuiRewindhandlersHandler2.field7.method11().method6(ThreadModuleDump63.method3().bridge$getCurrentScreen(), var2, var3, var4);
   }

   @Annotation2(min = 1)
   @WrapWithCondition(method = "handleMouseInput", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiScreen;mouseClickMove(IIIJ)V"))
   private boolean lunar$overwriteGuiDrag(GuiScreen var1, int var2, int var3, int var4, long var5) {
      return !GuiRewindhandlersHandler2.field7.method11().method7(ThreadModuleDump63.method3().bridge$getCurrentScreen(), var2, var3, var4, var5);
   }

   @Annotation2(min = 1)
   @WrapWithCondition(method = "handleInput", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiScreen;handleKeyboardInput()V"))
   private boolean lunar$overwriteGuiKeyboard(GuiScreen var1) {
      BridgeHandler var2 = new BridgeHandler(Keyboard.getEventCharacter(), Keyboard.getEventKey(), Keyboard.getEventKeyState());
      return !GuiRewindhandlersHandler2.field7.method11().method8(ThreadModuleDump63.method3().bridge$getCurrentScreen(), var2);
   }

   @WrapMethod(method = "setWorldAndResolution")
   private void lunar$inventoryScale(Minecraft var1, int var2, int var3, Operation<Void> var4) {
      int var5 = this.bridge$getInventoryScale();
      if (var5 <= 0) {
         var4.call(new Object[]{var1, var2, var3});
      } else {
         int var6 = LcuiScreen.method151().method3();
         float var7 = (float)var5 / var6;
         var4.call(new Object[]{var1, Math.round(var2 / var7), Math.round(var3 / var7)});
      }
   }
}
