package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.lunarclient.apollo.module.combat.CombatModule;
import com.lunarclient.apollo.module.serverrule.ServerRuleModule;
import com.lunarclient.apollo.option.SimpleOption;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.Bridge14_3;
import com.moonsworth.lunar.bridge.Bridge3_22;
import com.moonsworth.lunar.bridge.Bridge3_24;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.Bridge5Extension610;
import com.moonsworth.lunar.bridge.Bridge5_12;
import com.moonsworth.lunar.bridge.Bridge8Handler2;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.horsestats.MovingObjectPositionHitResult;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleHandler;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.render.texture.FramebufferCaptureTask;
import com.moonsworth.lunar.client.gui.LoadingScreen;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.config.ModsSettings;
import com.moonsworth.lunar.client.framework.feature.inventorymod.Inventorymod;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.Rewindhandlers;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.rewindhandlersCore.RewindHandlers3Impl8;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.rewindhandlersCore.RewindHandlers3Iterator;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.screen.ResolutionChangeEvent;
import com.moonsworth.lunar.client.event.screen.ScreenUpdateEvent;
import com.moonsworth.lunar.client.event.resourcepack.ResourcesReloadEvent;
import com.moonsworth.lunar.client.event.screen.ScreenChangeEvent;
import com.moonsworth.lunar.client.event.screen.ScreenOpenEvent;
import com.moonsworth.lunar.client.event.combat.EventMouseOverAttack;
import com.moonsworth.lunar.client.event.player.ItemDropEvent;
import com.moonsworth.lunar.client.event.input.InputActionLegacy;
import com.moonsworth.lunar.client.event.mixin.fishing.EventRenderTickPhase;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorldLifecycle;
import com.moonsworth.lunar.client.event.mixin.fishing.EventBlockPick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventRunDirectory;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTickEnd;
import com.moonsworth.lunar.client.event.mixin.fishing.EventEverySecond;
import com.moonsworth.lunar.client.event.mixin.fishing.mixin.EventUseItemOnBlockLegacy;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventPerspectiveChangeLegacy;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventMouseWheelLegacy;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import com.moonsworth.lunar.client.config.option.ListOption;
import com.moonsworth.lunar.client.driver.DriverRouteRegistryLegacy;
import com.moonsworth.lunar.client.driver.core.DualMarkerScreenLegacy;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.driver.core.DriverViewLegacy;
import com.moonsworth.lunar.client.driver.core.Fishing;
import com.moonsworth.lunar.client.mod.hud.f3display.F3Display;
import com.moonsworth.lunar.client.mod.misc.rewind.Rewind;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.mod.player.inventorymod.InventoryMods;
import com.moonsworth.lunar.client.mod.player.slotlocking.SlotLocking;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump71;
import com.moonsworth.lunar.ichor.Annotation2;
import com.moonsworth.lunar.legacy.Bridge2Handler;
import com.moonsworth.lunar.legacy.Bridge3Handler;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.FutureTask;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.settings.KeyBinding_v1_7;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.crash.CrashReport;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEgg;
import net.minecraft.item.ItemEnderEye;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.item.ItemExpBottle;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemSnowball;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.profiler.Profiler;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Timer;
import net.minecraft.util.Vec3;
import net.minecraft.util.math.RayTraceResult.MovingObjectType;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.storage.ISaveFormat;
import org.apache.logging.log4j.Logger;
import org.joml.Vector3i;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Group;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public abstract class MinecraftEventMixin implements Bridge5_12 {
   @Shadow
   public GuiScreen currentScreen;
   @Shadow
   public WorldClient world;
   @Shadow
   public FontRenderer fontRenderer;
   @Shadow
   public TextureManager renderEngine;
   @Shadow
   public int displayWidth;
   @Shadow
   public int displayHeight;
   @Shadow
   public GameSettings gameSettings;
   @Shadow
   public EntityRenderer entityRenderer;
   @Shadow
   public Entity renderViewEntity$v1_8;
   @Shadow
   public EntityLivingBase renderViewEntity$v1_7;
   @Shadow
   public EntityPlayerSP player$v1_8;
   @Final
   @Shadow
   public Timer timer;
   @Shadow
   public MovingObjectPosition objectMouseOver;
   @Shadow
   public RenderGlobal renderGlobal;
   @Shadow
   public boolean inGameHasFocus;
   @Shadow
   public PlayerControllerMP playerController;
   @Shadow
   public String serverName;
   @Shadow
   public int leftClickCounter;
   @Shadow
   public ISaveFormat saveLoader;
   @Shadow
   public boolean isGamePaused;
   @Shadow
   public float renderPartialTicksPaused$v1_12;
   @Final
   @Shadow
   public List<IResourcePack> defaultResourcePacks;
   @Shadow
   public List defaultResourcePacks$v1_7;
   @Shadow
   public long systemTime;
   @Shadow
   public EntityClientPlayerMP thePlayer$v1_7;
   @Final
   @Shadow
   public Profiler profiler;
   @Final
   @Shadow
   public Queue scheduledTasks$v1_7;
   @Shadow
   public static Minecraft instance;
   @Shadow
   public Framebuffer framebuffer;
   @Unique
   private long lunar$lastEvent = -1L;
   @Unique
   private boolean lunar$mouseEventButtonState;
   @Unique
   private boolean lunar$lastMouseEvent;
   @Unique
   private boolean lunar$currentlyBorderless;
   @Annotation2(max = 1)
   @Unique
   private static boolean lunar$leftClickPressed = false;
   @Annotation2(1)
   @Unique
   private static final Set<Class<? extends Item>> lunar$allowOneSevenUse = Set.of(
      ItemEnderPearl.class, ItemEnderEye.class, ItemSnowball.class, ItemEgg.class, ItemPotion.class, ItemExpBottle.class, ItemFishingRod.class
   );
   @Unique
   private boolean lunar$webosr$isDisplayActiveWebOSR;

   @Shadow
   public abstract TextureManager getTextureManager();

   @Shadow
   public abstract Entity getRenderViewEntity();

   @Shadow
   public abstract void dispatchKeypresses();

   @ModifyConstant(method = "getLimitFramerate", constant = @Constant(intValue = 30))
   private int lunar$getLimitFramerate(int var1) {
      return ThreadModuleDump63.method4().method22() ? ThreadModuleDump63.method4().method41().method7().method29().get() : this.gameSettings.limitFramerate;
   }

   @Inject(method = "launchIntegratedServer", at = @At("HEAD"))
   public void lunar$launchIntegratedServer(String var1, String var2, WorldSettings var3, CallbackInfo var4) {
      ClientEventBus.method29().method12(EventRunDirectory.class, () -> new EventRunDirectory(var2, this.saveLoader.getSaveLoader(var1, false).getWorldDirectory()));
   }

   @Inject(method = "clickMouse()V", at = @At("HEAD"))
   private void lunar$onSwing(CallbackInfo var1) {
      if (ThreadModuleDump63.MC_VERSION < 5 || this.leftClickCounter <= 0) {
         ClientEventBus.method29().method12(EventMouseOverAttack.class, () -> new EventMouseOverAttack((MovingObjectPositionHitResult)this.objectMouseOver));
      }
   }

   @Annotation2(max = 1)
   @Inject(method = "displayGuiScreen", at = @At(value = "INVOKE", shift = Shift.AFTER, target = "Lnet/minecraft/client/Minecraft;setIngameNotInFocus()V"))
   private void lunar$unPressKeysWhenOpeningGui(GuiScreen var1, CallbackInfo var2) {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         KeyBinding.unPressAllKeys();
      } else {
         KeyBinding_v1_7.unPressAllKeys();
      }

      if (Mouse.isCreated()) {
         while (Mouse.next()) {
         }
      }

      if (Keyboard.isCreated()) {
         while (Keyboard.next()) {
         }
      }
   }

   @Annotation2(1)
   @Inject(method = "runTick", at = @At("HEAD"))
   public void lunar$runTick(CallbackInfo var1) {
      lunar$leftClickPressed = false;
   }

   @Annotation2(1)
   @Inject(
      method = "runTick",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;isUsingItem()Z", ordinal = 0, shift = Shift.BEFORE)
   )
   public void lunar$runTick_isUsingItem(CallbackInfo var1) {
      if (this.gameSettings.keyBindAttack.isPressed()) {
         KeyBinding.onTick(this.gameSettings.keyBindAttack.keyCode);
         lunar$leftClickPressed = true;
      }
   }

   @Annotation2(1)
   @Redirect(method = "rightClickMouse", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/PlayerControllerMP;getIsHittingBlock$v1_8()Z"))
   private boolean lunar$getIsHittingBlock(PlayerControllerMP var1) {
      if (this.apollo$isDigAndUseAllowed()) {
         return false;
      }

      if (!lunar$leftClickPressed && var1.isHittingBlock && ThreadModuleDump63.method4().method40().method98().method14()) {
         ItemStack var2 = this.player$v1_8.getHeldItem();
         if (var2 == null) {
            return var1.isHittingBlock;
         }

         Item var3 = var2.getItem();
         if (var3 instanceof ItemFood var4) {
            if (!var4.alwaysEdible && !this.player$v1_8.foodStats.needFood() || this.player$v1_8.capabilities.disableDamage) {
               return var1.isHittingBlock;
            }
         } else if (var2.getItemUseAction() == EnumAction.NONE && !lunar$allowOneSevenUse.contains(var3.getClass())) {
            return var1.isHittingBlock;
         }

         if (this.objectMouseOver != null && this.objectMouseOver.typeOfHit == MovingObjectType.BLOCK) {
            BlockPos var5 = this.objectMouseOver.getBlockPos();
            Block var6 = this.world.getBlockState(var5).getBlock();
            if (var6.getMaterial() != Material.air) {
               this.playerController.resetBlockRemoving();
               this.playerController.blockHitDelay = 5;
               this.playerController.currentItemHittingBlock = null;
               return false;
            }
         }
      }

      return var1.isHittingBlock;
   }

   @Inject(method = "displayCrashReport", at = @At("HEAD"))
   private void lunar$reportCrash(CrashReport var1, CallbackInfo var2) {
      Inventorymod2.method7((Bridge3_22)var1);
   }

   @Annotation2(min = 1)
   @Redirect(method = "createDisplay$v1_8", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/Display;setTitle(Ljava/lang/String;)V"))
   private void lunar$onStartGame$v1_8(String var1) {
      Display.setTitle(Client.method17());
   }

   @Annotation2(max = 0)
   @Redirect(method = "startGame$v1_7", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/Display;setTitle(Ljava/lang/String;)V"))
   @Dynamic
   private void lunar$onStartGame$v1_7(String var1) {
      Display.setTitle(Client.method17());
   }

   @Annotation2(min = 1)
   @Inject(method = "createDisplay$v1_8", at = @At("TAIL"))
   private void lunar$initMessage$v1_8(CallbackInfo var1) {
      System.out.println("LUNARCLIENT_STATUS_INIT");
   }

   @Annotation2(max = 0)
   @Inject(method = "startGame$v1_7", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/OpenGlHelper;initializeTextures()V"))
   private void lunar$initMessage$v1_7(CallbackInfo var1) {
      System.out.println("LUNARCLIENT_STATUS_INIT");
   }

   @Inject(method = "shutdownMinecraftApplet", at = @At("HEAD"))
   private void lunar$shutdownMinecraftApplet(CallbackInfo var1) {
      Client.method109().close();
   }

   @Inject(method = "displayGuiScreen(Lnet/minecraft/client/gui/GuiScreen;)V", at = @At("HEAD"), cancellable = true)
   private void lunar$displayGuiScreen(GuiScreen var1, CallbackInfo var2) {
      if (var1 instanceof Bridge5Extension610) {
         Client var3 = Client.method109();
         boolean var4 = var3 != null && !var3.method25();
         if (var4) {
            if (!DualMarkerScreenLegacy.method14()) {
               var3.method23();
            }

            var2.cancel();
            return;
         }
      }

      ScreenChangeEvent var5 = ClientEventBus.method29().method12(ScreenChangeEvent.class, () -> new ScreenChangeEvent((Bridge5Extension6)var1));
      if (var5 != null && var5.isCancelled()) {
         var2.cancel();
      }
   }

   @Inject(method = {"init$v1_12", "startGame$v1_7"}, at = @At("HEAD"))
   private void lunar$startGame$v1_12(CallbackInfo var1) {
      Bridge.method4().forEach(var1x -> {
         System.out.println("[Bridge] Adding cosmetic pack to the default resource packs");
         if (ThreadModuleDump63.MC_VERSION >= 1) {
            this.defaultResourcePacks.add((IResourcePack)var1x);
         } else {
            this.defaultResourcePacks$v1_7.add(var1x);
         }
      });
   }

   @Inject(method = {"init$v1_12", "startGame$v1_7"}, at = @At(value = "NEW", target = "net/minecraft/client/gui/GuiIngame"))
   @Group(name = "lunar.Minecraft.init", min = 1, max = 1)
   private void lunar$startGame$afterInGame$vanilla(CallbackInfo var1) {
      this.lunar$startGame$afterInGame(var1);
   }

   @Annotation2(min = 5)
   @Inject(method = "init$v1_12", at = @At(value = "NEW", target = "net/minecraftforge/client/GuiIngameForge"))
   @Dynamic
   @Group(name = "lunar.Minecraft.init", min = 1, max = 1)
   private void lunar$startGame$afterInGame$forge$v1_12(CallbackInfo var1) {
      this.lunar$startGame$afterInGame(var1);
   }

   @Annotation2(max = 1)
   @Inject(method = "startGame$v1_7", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/fml/client/FMLClientHandler;finishMinecraftLoading()V"))
   @Dynamic
   @Group(name = "lunar.Minecraft.init", min = 1, max = 1)
   private void lunar$startGame$afterInGame$forge$v1_7(CallbackInfo var1) {
      this.lunar$startGame$afterInGame(var1);
   }

   private void lunar$startGame$afterInGame(CallbackInfo var1) {
      this.framebuffer.bindFramebuffer(true);
      if (LoadingScreen.method14() == null) {
         new LoadingScreen(this, (Bridge8Handler2)this.getTextureManager(), this.displayWidth, this.displayHeight);
      }

      new Client();
   }

   @Inject(method = {"init$v1_12", "startGame$v1_7"}, at = @At("TAIL"))
   private void lunar$startGame$tail(CallbackInfo var1) {
      Slayer.method3("LUNARCLIENT_STATUS_STARTED");
      if (this.serverName == null) {
         Client.method109().method23();
      }
   }

   @Redirect(
      method = {"init$v1_12", "startGame$v1_7"},
      at = @At(
         value = "NEW",
         target = "Lnet/minecraft/client/multiplayer/GuiConnecting;<init>(Lnet/minecraft/client/gui/GuiScreen;Lnet/minecraft/client/Minecraft;Ljava/lang/String;I)Lnet/minecraft/client/multiplayer/GuiConnecting;"
      ),
      require = 0
   )
   @Dynamic
   private GuiConnecting lunar$guiConnecting(GuiScreen var1, Minecraft var2, String var3, int var4) {
      return new GuiConnecting(var1, var2, new ServerData(var3, var3, false));
   }

   @Inject(method = "displayGuiScreen", at = @At("TAIL"))
   private void lunar$postDisplayGuiScreen(GuiScreen var1, CallbackInfo var2) {
      ClientEventBus.method29().method12(ScreenUpdateEvent.class, () -> new ScreenUpdateEvent((Bridge5Extension6)var1));
   }

   @Inject(method = "runTick()V", at = @At("HEAD"))
   private void lunar$preRunTick(CallbackInfo var1) {
      ClientEventBus.method29()
         .method12(
            com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick.class, com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick::new
         );
      if (com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick.field1 != 0
         && com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick.field1 % 20 == 0) {
         ClientEventBus.method29().method12(EventEverySecond.class, EventEverySecond::new);
      }

      com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick.field1++;
   }

   @Inject(method = "runTick()V", at = @At("RETURN"))
   private void lunar$postRunTick(CallbackInfo var1) {
      if (this.lunar$webosr$isDisplayActiveWebOSR != this.inGameHasFocus) {
         DriverViewLegacy.method21().method4(this.inGameHasFocus);
         this.lunar$webosr$isDisplayActiveWebOSR = this.inGameHasFocus;
      }

      ClientEventBus.method29().method12(EventTickEnd.class, EventTickEnd::new);
   }

   @Annotation2(min = 5)
   @Redirect(
      method = "processKeyBinds$v1_12()V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;dropItem$v1_12(Z)Lnet/minecraft/entity/item/EntityItem;")
   )
   private EntityItem lunar$processKeyBindsDropItem(EntityPlayerSP var1, boolean var2) {
      return var1.dropItem(ThreadModuleDump63.method4().method41().method8().method21().isKeyDown());
   }

   @Annotation2(1)
   @Redirect(
      method = "runTick()V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;dropOneItem$v1_8(Z)Lnet/minecraft/entity/item/EntityItem;")
   )
   @Dynamic
   private EntityItem lunar$onRunTickDropItem(EntityPlayerSP var1, boolean var2) {
      return var1.dropOneItem(ThreadModuleDump63.method4().method41().method8().method21().isKeyDown());
   }

   @Annotation2(max = 0)
   @Redirect(
      method = "runTick()V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityClientPlayerMP;dropOneItem$v1_7(Z)Lnet/minecraft/entity/item/EntityItem;")
   )
   @Dynamic
   private EntityItem lunar$onRunTickDropItem(EntityClientPlayerMP var1, boolean var2) {
      return var1.dropOneItem(ThreadModuleDump63.method4().method41().method8().method21().isKeyDown());
   }

   @Annotation2(min = 5)
   @Redirect(
      method = "processKeyBinds$v1_12()V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/EntityRenderer;loadEntityShader$v1_8(Lnet/minecraft/entity/Entity;)V")
   )
   private void lunar$loadEntityShader$v1_12(EntityRenderer var1, Entity var2) {
      if (!ThreadModuleDump63.method4().method40().method44().isEnabled() && !ThreadModuleDump63.method4().method40().method45().isEnabled()) {
         var1.loadEntityShader(var2);
      }
   }

   @Annotation2(1)
   @Redirect(
      method = "runTick",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/EntityRenderer;loadEntityShader$v1_8(Lnet/minecraft/entity/Entity;)V")
   )
   @Dynamic
   private void lunar$loadEntityShader$v1_8(EntityRenderer var1, Entity var2) {
      if (!ThreadModuleDump63.method4().method40().method44().isEnabled() && !ThreadModuleDump63.method4().method40().method45().isEnabled()) {
         var1.loadEntityShader(var2);
      }
   }

   @Inject(method = "runTick()V", at = @At("HEAD"))
   private void lunar$onRunTick(CallbackInfo var1) {
      ((Bridge10_2)this.fontRenderer).tick();
   }

   @Annotation2(min = 5)
   @Inject(method = "runTickMouse$v1_12()V", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;getEventButtonState()Z", ordinal = 0), require = 1)
   private void lunar$eventMouseAction$v1_12(CallbackInfo var1) {
      this.lunar$eventMouseAction();
   }

   @Annotation2(max = 1)
   @Inject(method = "runTick()V", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;getEventButtonState()Z", ordinal = 0), require = 1)
   @Dynamic
   private void lunar$eventMouseAction$v1_8(CallbackInfo var1) {
      this.lunar$eventMouseAction();
   }

   @Unique
   private void lunar$eventMouseAction() {
      this.lunar$mouseEventButtonState = Mouse.getEventButtonState();
      com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventMouseButtonLegacy var1 = ClientEventBus.method29()
         .method12(
            com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventMouseButtonLegacy.class,
            () -> new com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventMouseButtonLegacy(
               Mouse.getEventButton(), Bridge3Handler.method2(), this.lunar$mouseEventButtonState ? InputActionLegacy.DOWN : InputActionLegacy.UP
            )
         );
      this.lunar$lastMouseEvent = var1 != null && var1.isCancelled();
      if (this.lunar$lastMouseEvent) {
         this.lunar$mouseEventButtonState = false;
      }
   }

   @Annotation2(min = 5)
   @Redirect(
      method = "runTickMouse$v1_12()V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/settings/KeyBinding;setKeyBindState(IZ)V", ordinal = 0)
   )
   @Dynamic
   private void lunar$onKeyBindingState$v1_12(int var1, boolean var2) {
      this.lunar$onKeyBindingState(var1, var2);
   }

   @Annotation2(max = 1)
   @Redirect(method = "runTick()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/settings/KeyBinding;setKeyBindState(IZ)V", ordinal = 0))
   @Dynamic
   private void lunar$onKeyBindingState$v1_7(int var1, boolean var2) {
      this.lunar$onKeyBindingState(var1, var2);
   }

   @Unique
   private void lunar$onKeyBindingState(int var1, boolean var2) {
      if (!this.lunar$lastMouseEvent) {
         if (ThreadModuleDump63.MC_VERSION >= 1) {
            KeyBinding.setKeyBindState(var1, var2);
         } else {
            KeyBinding_v1_7.setKeyBindState(var1, var2);
         }
      }
   }

   @Annotation2(min = 5)
   @Redirect(method = "runTickMouse$v1_12()V", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;getEventButtonState()Z"))
   private boolean lunar$getEventButtonState$v1_12() {
      return this.lunar$mouseEventButtonState;
   }

   @Annotation2(max = 1)
   @Redirect(method = "runTick()V", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;getEventButtonState()Z"))
   @Dynamic
   private boolean lunar$getEventButtonState$v1_7() {
      return this.lunar$mouseEventButtonState;
   }

   @Annotation2(min = 5)
   @WrapOperation(method = "runTickMouse$v1_12()V", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;getEventDWheel()I"))
   private int lunar$onHotbarScroll$v1_12(Operation<Integer> var1) {
      int var2 = (Integer)var1.call(new Object[0]);
      if (var2 != 0) {
         EventMouseWheelLegacy var3 = ClientEventBus.method29().method12(EventMouseWheelLegacy.class, () -> new EventMouseWheelLegacy(var2));
         if (var3 != null && var3.isCancelled()) {
            return 0;
         }
      }

      return ThreadModuleDump63.method4().method41().method6().method64().get() ? 0 : var2;
   }

   @Annotation2(max = 1)
   @WrapOperation(method = "runTick()V", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;getEventDWheel()I"))
   @Dynamic
   private int lunar$onHotbarScroll(Operation<Integer> var1) {
      int var2 = (Integer)var1.call(new Object[0]);
      if (var2 != 0) {
         EventMouseWheelLegacy var3 = ClientEventBus.method29().method12(EventMouseWheelLegacy.class, () -> new EventMouseWheelLegacy(var2));
         if (var3 != null && var3.isCancelled()) {
            return 0;
         }
      }

      return ThreadModuleDump63.method4().method41().method6().method64().get() ? 0 : var2;
   }

   @Annotation2(min = 1)
   @Inject(method = "clickMouse()V", at = @At("HEAD"))
   private void apollo$clickMouseLegacyCombat(CallbackInfo var1) {
      if (this.objectMouseOver != null) {
         SimpleOption var2 = this.objectMouseOver.typeOfHit == MovingObjectType.BLOCK
            ? CombatModule.DISABLE_BLOCK_MISS_PENALTY
            : CombatModule.DISABLE_MISS_PENALTY;
         ThreadModuleDump63.method4()
            .method84()
            .<ApolloModuleHandler>method3(CombatModule.class)
            .filter(var1x -> (Boolean)var1x.getOptions().get(var2))
            .ifPresent(var1x -> this.leftClickCounter = 0);
      }
   }

   @Inject(method = "loadWorld(Lnet/minecraft/client/multiplayer/WorldClient;Ljava/lang/String;)V", at = @At("HEAD"))
   private void lunar$callWorldLoadEvent$pre(WorldClient var1, String var2, CallbackInfo var3) {
      ClientEventBus.method29().method12(EventWorldLifecycle.EventWorldChanged.class, () -> new EventWorldLifecycle.EventWorldChanged((Itemcounter6)var1));
   }

   @Inject(method = "loadWorld(Lnet/minecraft/client/multiplayer/WorldClient;Ljava/lang/String;)V", at = @At("RETURN"))
   private void lunar$callWorldLoadEvent$post(WorldClient var1, String var2, CallbackInfo var3) {
      ClientEventBus.method29().method12(EventWorldLifecycle.EventWorldLoaded.class, () -> new EventWorldLifecycle.EventWorldLoaded((Itemcounter6)var1));
   }

   @Redirect(
      method = "loadWorld(Lnet/minecraft/client/multiplayer/WorldClient;Ljava/lang/String;)V",
      at = @At(value = "INVOKE", target = "Ljava/lang/System;gc()V")
   )
   private void lunar$cancelSystemGC() {
   }

   @Redirect(
      method = "loadWorld(Lnet/minecraft/client/multiplayer/WorldClient;Ljava/lang/String;)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/server/integrated/IntegratedServer;initiateShutdown()V")
   )
   public void lunar$fix(IntegratedServer var1) {
      if (!var1.isPublic) {
         var1.initiateShutdown();
      }
   }

   @Unique
   private void lunar$startBorderlessFullscreen() {
      this.lunar$currentlyBorderless = true;
      System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
      GraphicsEnvironment var1 = GraphicsEnvironment.getLocalGraphicsEnvironment();
      Rectangle var2 = var1.getDefaultScreenDevice().getDefaultConfiguration().getBounds();
      this.displayWidth = Display.getDesktopDisplayMode().getWidth();
      this.displayHeight = Display.getDesktopDisplayMode().getHeight();
      Display.setLocation(var2.x, var2.y);
      Display.setResizable(false);
      Display.setDisplayMode(new DisplayMode(this.displayWidth, this.displayHeight));
   }

   @Redirect(method = "toggleFullscreen", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/Display;setFullscreen(Z)V"))
   private void lunar$setFullscreen(boolean var1) {
      if (this.currentScreen == null) {
         LcuiScreen.method150(new ThreadModuleDump71(ThreadModuleDump63.method3()));
      }

      if (var1) {
         if (ThreadModuleDump63.method4().method41().method6().method21().get()) {
            this.lunar$startBorderlessFullscreen();
         } else {
            this.lunar$currentlyBorderless = false;
         }

         Display.setFullscreen(true);
      } else {
         if (this.lunar$currentlyBorderless) {
            System.setProperty("org.lwjgl.opengl.Window.undecorated", "false");
            Display.setLocation(Display.getWidth() / 4, Display.getHeight() / 4);
         }

         this.lunar$currentlyBorderless = false;
         Display.setDisplayMode(new DisplayMode(this.displayWidth, this.displayHeight));
         Display.setFullscreen(false);
         Display.setResizable(true);
      }
   }

   @Inject(
      method = "resize",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/LoadingScreenRenderer;<init>(Lnet/minecraft/client/Minecraft;)V", shift = Shift.BEFORE)
   )
   private void lunar$onResize(int var1, int var2, CallbackInfo var3) {
      ClientEventBus.method29().method12(ResolutionChangeEvent.class, () -> {
         ScaledResolution var3x;
         if (ThreadModuleDump63.MC_VERSION >= 1) {
            var3x = new ScaledResolution((Minecraft)this);
         } else {
            var3x = new ScaledResolution((Minecraft)this, var1, var2);
         }

         return new ResolutionChangeEvent(var3x.getScaledWidth(), var3x.getScaledHeight());
      });
   }

   @Annotation2(min = 1)
   @Inject(method = "drawSplashScreen$v1_8", at = @At("HEAD"), cancellable = true)
   private void lunar$onDrawSplashScreen$v1_8(TextureManager var1, CallbackInfo var2) {
      new LoadingScreen(this, (Bridge8Handler2)var1, this.displayWidth, this.displayHeight);
      var2.cancel();
   }

   @Annotation2(max = 0)
   @Inject(method = "loadScreen$v1_7", at = @At("HEAD"), cancellable = true)
   private void lunar$onDrawSplashScreen$v1_7(CallbackInfo var1) {
      new LoadingScreen(this, (Bridge8Handler2)this.renderEngine, this.displayWidth, this.displayHeight);
      var1.cancel();
   }

   @Annotation2(min = 1, max = 1)
   @Inject(
      method = "runTick()V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/settings/KeyBinding_v1_8;onTick(I)V", shift = Shift.AFTER, ordinal = 0)
   )
   @Dynamic
   private void lunar$fixTogglePerspectiveMouse$v1_8(CallbackInfo var1) {
      if (this.currentScreen == null && this.inGameHasFocus && this.lunar$onTogglePerspective$v1_8(this.gameSettings.keyBindTogglePerspective)) {
         this.gameSettings.thirdPersonView++;
         if (this.gameSettings.thirdPersonView > 2) {
            this.gameSettings.thirdPersonView = 0;
         } else if (this.gameSettings.thirdPersonView == 0) {
            this.entityRenderer.loadEntityShader(this.getRenderViewEntity());
         } else if (this.gameSettings.thirdPersonView == 1) {
            this.entityRenderer.loadEntityShader(null);
         }

         this.renderGlobal.setDisplayListEntitiesDirty();
      }
   }

   @Annotation2(max = 0)
   @Inject(
      method = "runTick()V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/settings/KeyBinding_v1_7;onTick(I)V", shift = Shift.AFTER, ordinal = 0)
   )
   @Dynamic
   private void lunar$fixTogglePerspectiveMouse$v1_7(CallbackInfo var1) {
      if (this.currentScreen == null && this.inGameHasFocus && this.lunar$onTogglePerspective$v1_7(this.gameSettings.keyBindTogglePerspective$v1_7)) {
         this.gameSettings.thirdPersonView++;
         if (this.gameSettings.thirdPersonView > 2) {
            this.gameSettings.thirdPersonView = 0;
         }
      }
   }

   @Annotation2(min = 1, max = 1)
   @Redirect(method = "runTick()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/settings/KeyBinding_v1_8;isPressed()Z"))
   @Dynamic
   public boolean lunar$onTogglePerspective$v1_8(KeyBinding var1) {
      if (var1 == this.gameSettings.keyBindTogglePerspective) {
         boolean var2 = var1.isPressed();
         if (var2) {
            EventPerspectiveChangeLegacy var3 = ClientEventBus.method29().method12(EventPerspectiveChangeLegacy.class, () -> new EventPerspectiveChangeLegacy(this.gameSettings.thirdPersonView));
            if (var3 != null && var3.isCancelled()) {
               return false;
            }
         }

         return var2;
      } else {
         return var1.isPressed();
      }
   }

   @Annotation2(max = 0)
   @Redirect(method = "runTick()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/settings/KeyBinding_v1_7;isPressed()Z"))
   @Dynamic
   public boolean lunar$onTogglePerspective$v1_7(KeyBinding_v1_7 var1) {
      if (var1 == this.gameSettings.keyBindTogglePerspective$v1_7) {
         boolean var2 = var1.isPressed();
         if (var2) {
            EventPerspectiveChangeLegacy var3 = ClientEventBus.method29().method12(EventPerspectiveChangeLegacy.class, () -> new EventPerspectiveChangeLegacy(this.gameSettings.thirdPersonView));
            if (var3 != null && var3.isCancelled()) {
               return false;
            }
         }

         return var2;
      } else {
         return var1.isPressed();
      }
   }

   @Annotation2(max = 0)
   @Redirect(method = "runGameLoop", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/Display;isActive()Z"))
   @Dynamic
   private boolean lunar$runGameLoop() {
      return true;
   }

   @Annotation2(min = 1)
   @Inject(
      method = "runGameLoop",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/EntityRenderer;updateCameraAndRender$v1_8(FJ)V", shift = Shift.BEFORE)
   )
   private void lunar$onRenderTick$PRE(CallbackInfo var1) {
      ClientEventBus.method29().method12(EventRenderTickPhase.EventRenderTickBegin.class, () -> new EventRenderTickPhase.EventRenderTickBegin(this.timer.renderPartialTicks));
   }

   @Annotation2(min = 1)
   @Inject(
      method = "runGameLoop",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/EntityRenderer;updateCameraAndRender$v1_8(FJ)V", shift = Shift.AFTER)
   )
   private void lunar$onRenderTick$POST(CallbackInfo var1) {
      ClientEventBus.method29().method12(EventRenderTickPhase.EventRenderTickFinish.class, () -> new EventRenderTickPhase.EventRenderTickFinish(this.timer.renderPartialTicks));
   }

   @Annotation2(max = 0)
   @Inject(
      method = "runGameLoop",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/EntityRenderer;updateCameraAndRender(F)V", shift = Shift.BEFORE)
   )
   @Dynamic
   private void lunar$onRenderTick$PRE2_v1_7(CallbackInfo var1) {
      ClientEventBus.method29().method12(EventRenderTickPhase.EventRenderTickBegin.class, () -> new EventRenderTickPhase.EventRenderTickBegin(this.timer.renderPartialTicks));
   }

   @Annotation2(max = 0)
   @Inject(
      method = "runGameLoop",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/EntityRenderer;updateCameraAndRender(F)V", shift = Shift.AFTER)
   )
   @Dynamic
   private void lunar$onRenderTick$POST_v1_7(CallbackInfo var1) {
      ClientEventBus.method29().method12(EventRenderTickPhase.EventRenderTickFinish.class, () -> new EventRenderTickPhase.EventRenderTickFinish(this.timer.renderPartialTicks));
   }

   @Annotation2(max = 1)
   @Inject(method = "setIngameFocus", at = @At("HEAD"))
   private void lunar$updateKeybindState(CallbackInfo var1) {
      boolean var2 = Client.method109().method41().method6().method63().get();
      if (var2 && Display.isActive() && !this.inGameHasFocus) {
         if (ThreadModuleDump63.MC_VERSION >= 1) {
            for (KeyBinding var4 : KeyBinding.keybindArray) {
               boolean var5 = var4.keyCode > 0 && var4.keyCode < 256 && Keyboard.isKeyDown(var4.keyCode);
               KeyBinding.setKeyBindState(var4.keyCode, var5);
            }
         } else {
            for (KeyBinding_v1_7 var9 : KeyBinding_v1_7.keybindArray) {
               boolean var6 = var9.keyCode > 0 && var9.keyCode < 256 && Keyboard.isKeyDown(var9.keyCode);
               KeyBinding_v1_7.setKeyBindState(var9.keyCode, var6);
            }
         }
      }
   }

   @Inject(method = "getFramebuffer", at = @At("HEAD"), cancellable = true)
   private void lunar$replaceFramebuffer(CallbackInfoReturnable<Framebuffer> var1) {
      if (FramebufferCaptureTask.method6()) {
         var1.setReturnValue((Framebuffer)FramebufferCaptureTask.field8.method11());
      }

      if (ThreadModuleDump63.method4() != null && ThreadModuleDump63.method4().method40() != null) {
         Rewind var2 = ThreadModuleDump63.method4().method40().method85();
         RewindHandlers var3 = var2.method35();
         if (var2.method19() && var3.method48().method25() && var3.method48().method29()) {
            var1.setReturnValue((Framebuffer)var3.method48().method24().method11());
         }
      }
   }

   @Inject(method = "runGameLoop", at = @At("TAIL"))
   private void lunar$afterFramebufferCopy(CallbackInfo var1) {
      Fishing.method12();
   }

   @Inject(method = "refreshResources", at = @At("TAIL"))
   private void lunar$onRefreshResources(CallbackInfo var1) {
      if (this.fontRenderer != null) {
         ((Bridge10_2)this.fontRenderer).clearCaches();
      }

      ClientEventBus.method29().method12(ResourcesReloadEvent.class, ResourcesReloadEvent::new);
   }

   @Inject(method = "displayGuiScreen", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiScreen;onGuiClosed()V"))
   private void lunar$event$onGuiClosed(GuiScreen var1, CallbackInfo var2) {
      ClientEventBus.method29().method12(ScreenOpenEvent.class, () -> new ScreenOpenEvent((Bridge5Extension6)this.currentScreen, (Bridge5Extension6)var1));
   }

   @Annotation2(min = 5)
   @Redirect(method = "runTickKeyboard$v1_12()V", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;isKeyDown(I)Z"))
   private boolean lunar$isKeyDown$v1_12(int var1) {
      return this.lunar$isKeyDown(var1);
   }

   @Annotation2(max = 1)
   @Redirect(method = "runTick", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;isKeyDown(I)Z"))
   @Dynamic
   private boolean lunar$isKeyDown$v1_7(int var1) {
      return this.lunar$isKeyDown(var1);
   }

   @Unique
   private boolean lunar$isKeyDown(int var1) {
      int var2 = Keyboard.getEventKey() == 0 ? Keyboard.getEventCharacter() + 256 : Keyboard.getEventKey();
      if (var1 == 61) {
         switch (var2) {
            case 30:
               if (ThreadModuleDump63.method4()
                  .method84()
                  .<ApolloModuleHandler>method3(ServerRuleModule.class)
                  .filter(var0 -> (Boolean)var0.getOptions().get(ServerRuleModule.DISABLE_CHUNK_RELOADING))
                  .isPresent()) {
                  return false;
               }

               if (ThreadModuleDump63.method18()) {
                  return false;
               }
         }
      }

      return Keyboard.isKeyDown(var1);
   }

   @Annotation2(max = 0)
   @Inject(method = "startGame$v1_7", at = @At("TAIL"))
   private void lunar$initializeEntityGlowing(CallbackInfo var1) {
      ((Bridge14_3)this.renderGlobal).bridge$makeEntityOutlineShader();
   }

   @Inject(method = "getSystemTime", at = @At("HEAD"), cancellable = true)
   private static void lunar$overrideSystemTime(CallbackInfoReturnable<Long> var0) {
      Client var1 = ThreadModuleDump63.method4();
      if (var1 != null) {
         ModsSettings var2 = var1.method40();
         if (var2 != null) {
            Rewind var3 = var2.method85();
            if (var3.method19()) {
               Rewindhandlers var4 = var3.method35().method41();
               var0.setReturnValue(var4.getTime());
            }
         }
      }
   }

   @Unique
   private static void lunar$postUseItemOnEvent(int var0, int var1, int var2, int var3, int var4, Vec3Bridge var5) {
      ClientEventBus.method29().method12(EventUseItemOnBlockLegacy.class, () -> new EventUseItemOnBlockLegacy(new Vector3i(var0, var1, var2), var3, var4, var5.method6(), false, false));
   }

   @Annotation2(max = 0)
   @WrapOperation(
      method = "rightClickMouse",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/multiplayer/PlayerControllerMP;onPlayerRightClick$v1_7(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/world/World;Lnet/minecraft/item/ItemStack;IIIILnet/minecraft/util/math/Vec3d;)Z"
      )
   )
   @Dynamic
   private boolean lunar$onUseItemOn$v1_7(
      PlayerControllerMP var1, EntityPlayer var2, World var3, ItemStack var4, int var5, int var6, int var7, int var8, Vec3 var9, Operation<Boolean> var10
   ) {
      lunar$postUseItemOnEvent(var5, var6, var7, 0, var8, (Vec3Bridge)var9);
      return (Boolean)var10.call(new Object[]{var1, var2, var3, var4, var5, var6, var7, var8, var9});
   }

   @Annotation2(1)
   @WrapOperation(
      method = "rightClickMouse",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/multiplayer/PlayerControllerMP;onPlayerRightClick$v1_8(Lnet/minecraft/client/entity/EntityPlayerSP;Lnet/minecraft/client/multiplayer/WorldClient;Lnet/minecraft/item/ItemStack;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/EnumFacing;Lnet/minecraft/util/math/Vec3d;)Z"
      )
   )
   @Dynamic
   private boolean lunar$onUseItemOn$v1_8(
      PlayerControllerMP var1, EntityPlayerSP var2, WorldClient var3, ItemStack var4, BlockPos var5, EnumFacing var6, Vec3 var7, Operation<Boolean> var8
   ) {
      Horsestats20Extension2 var9 = (Horsestats20Extension2)var5;
      lunar$postUseItemOnEvent(var9.bridge$getX(), var9.bridge$getY(), var9.bridge$getZ(), 0, var6.ordinal(), (Vec3Bridge)var7);
      return (Boolean)var8.call(new Object[]{var1, var2, var3, var4, var5, var6, var7});
   }

   @Annotation2(min = 5)
   @WrapOperation(
      method = "rightClickMouse",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/multiplayer/PlayerControllerMP;processRightClickBlock$v1_12(Lnet/minecraft/client/entity/EntityPlayerSP;Lnet/minecraft/client/multiplayer/WorldClient;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/EnumFacing;Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/util/EnumHand;)Lnet/minecraft/util/EnumActionResult;"
      )
   )
   private EnumActionResult lunar$onUseItemOn$v1_12(
      PlayerControllerMP var1,
      EntityPlayerSP var2,
      WorldClient var3,
      BlockPos var4,
      EnumFacing var5,
      Vec3 var6,
      EnumHand var7,
      Operation<EnumActionResult> var8
   ) {
      Horsestats20Extension2 var9 = (Horsestats20Extension2)var4;
      lunar$postUseItemOnEvent(var9.bridge$getX(), var9.bridge$getY(), var9.bridge$getZ(), var7.ordinal(), var5.ordinal(), (Vec3Bridge)var6);
      return (EnumActionResult)var8.call(new Object[]{var1, var2, var3, var4, var5, var6, var7});
   }

   @Unique
   private static void lunar$postUseItemEvent(int var0) {
      ClientEventBus.method29()
         .method12(
            com.moonsworth.lunar.client.event.mixin.fishing.mixin.EventUseItemLegacy.class,
            () -> new com.moonsworth.lunar.client.event.mixin.fishing.mixin.EventUseItemLegacy(var0)
         );
   }

   @Annotation2(max = 1)
   @WrapOperation(
      method = "rightClickMouse",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/multiplayer/PlayerControllerMP;sendUseItem$v1_7(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/world/World;Lnet/minecraft/item/ItemStack;)Z"
      )
   )
   @Dynamic
   private boolean lunar$onUseItem$v1_7(PlayerControllerMP var1, EntityPlayer var2, World var3, ItemStack var4, Operation<Boolean> var5) {
      lunar$postUseItemEvent(0);
      return (Boolean)var5.call(new Object[]{var1, var2, var3, var4});
   }

   @Annotation2(min = 5)
   @WrapOperation(
      method = "rightClickMouse",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/multiplayer/PlayerControllerMP;processRightClick$v1_12(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/world/World;Lnet/minecraft/util/EnumHand;)Lnet/minecraft/util/EnumActionResult;"
      )
   )
   private EnumActionResult lunar$onUseItem$v1_12(PlayerControllerMP var1, EntityPlayer var2, World var3, EnumHand var4, Operation<EnumActionResult> var5) {
      lunar$postUseItemEvent(var4.ordinal());
      return (EnumActionResult)var5.call(new Object[]{var1, var2, var3, var4});
   }

   @Annotation2(max = 1)
   @WrapWithCondition(
      method = "runGameLoop",
      at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;isGamePaused:Z", opcode = 181, ordinal = 0),
      slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=Post render"))
   )
   private boolean lunar$addRewindPauseLogic$v1_8(Minecraft var1, boolean var2) {
      Rewind var3 = ThreadModuleDump63.method4().method40().method85();
      if (var3.method19()) {
         this.isGamePaused = var3.method35().method42().get().method19();
         return false;
      } else {
         return true;
      }
   }

   @Annotation2(min = 5)
   @WrapOperation(method = "runGameLoop", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;isSingleplayer()Z"))
   private boolean lunar$addRewindPauseLogic$v1_12(Minecraft var1, Operation<Boolean> var2) {
      Rewind var3 = ThreadModuleDump63.method4().method40().method85();
      if (var3.method19()) {
         boolean var4 = var3.method35().method42().get().method19();
         if (this.isGamePaused != var4) {
            if (var4) {
               this.renderPartialTicksPaused$v1_12 = this.timer.renderPartialTicks;
            } else {
               this.timer.renderPartialTicks = this.renderPartialTicksPaused$v1_12;
            }

            this.isGamePaused = var4;
         }

         return false;
      } else {
         return (Boolean)var2.call(new Object[]{var1});
      }
   }

   @Annotation2(min = 5)
   @WrapOperation(
      method = "runGameLoop",
      at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;isGamePaused:Z", ordinal = 0),
      slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;isSingleplayer()Z"))
   )
   private boolean lunar$disableVanillaPauseLogicIfRewind(Minecraft var1, Operation<Boolean> var2) {
      Rewind var3 = ThreadModuleDump63.method4().method40().method85();
      return var3.method19() ? false : (Boolean)var2.call(new Object[]{var1});
   }

   @WrapOperation(
      method = "refreshResources",
      at = {
            @At(value = "INVOKE", target = "Lnet/minecraft/client/resources/IReloadableResourceManager;reloadResources$v1_8(Ljava/util/List;)V", ordinal = 0),
            @At(value = "INVOKE", target = "Lnet/minecraft/client/resources/IReloadableResourceManager;reloadResources$v1_7(Ljava/util/List;)V", ordinal = 0)
      }
   )
   @Dynamic
   private void lunar$addRewindResourcePacks(IReloadableResourceManager var1, List var2, Operation<Void> var3) {
      if (ThreadModuleDump63.method4() != null && ThreadModuleDump63.method4().method40() != null) {
         Rewind var4 = ThreadModuleDump63.method4().method40().method85();
         if (var4.method19()) {
            RewindHandlers var5 = var4.method35();
            Nameplate4 var6 = var5.method42().get();
            RewindHandlers3Iterator var7 = var5.method47();
            var2 = new ArrayList(ThreadModuleDump63.MC_VERSION == 0 ? this.defaultResourcePacks$v1_7 : this.defaultResourcePacks);
            if (!var7.method17().get() || var7.method21().get()) {
               var2.addAll(var6.method13());
            }

            if (var7.method17().get()) {
               for (File var9 : this.lunar$getFiles(var7.method22())) {
                  try {
                     var2.add(Bridge.method8().method79(var9, false));
                  } catch (Exception var11) {
                     var11.printStackTrace();
                  }
               }
            }

            if (!var7.method17().get() || var7.method19().get()) {
               var2.addAll(var6.method11());
            }
         }

         var3.call(new Object[]{var1, var2});
      } else {
         var3.call(new Object[]{var1, var2});
      }
   }

   @Unique
   private List<File> lunar$getFiles(ListOption<String> var1) {
      return ((List)var1.get()).stream().map(var0 -> {
         File var1x = new File(new File(ThreadModuleDump63.method3().bridge$getMcDataDir(), "assets"), var0);
         return var1x.exists() ? var1x : new File(new File(ThreadModuleDump63.method3().bridge$getMcDataDir(), "resourcepacks"), var0);
      }).toList();
   }

   @Inject(method = "runGameLoop", at = @At("HEAD"))
   private void lunar$onTick(CallbackInfo var1) {
      ClientEventBus.method29()
         .method12(
            com.moonsworth.lunar.client.event.mixin.fishing.EventRewindRefresh.class, com.moonsworth.lunar.client.event.mixin.fishing.EventRewindRefresh::new
         );
   }

   @WrapOperation(method = "runGameLoop", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/shader/Framebuffer;bindFramebuffer(Z)V"))
   private void lunar$rewindFramebuffer(Framebuffer var1, boolean var2, Operation<Void> var3) {
      if (ThreadModuleDump63.method4() != null && ThreadModuleDump63.method4().method40() != null) {
         Rewind var4 = ThreadModuleDump63.method4().method40().method85();
         if (var4.method19() && DriverViewportLegacy.method50().method63() == DriverRouteRegistryLegacy.field10) {
            RewindHandlers var5 = var4.method35();
            RewindHandlers3Impl8 var6 = var5.method48();
            var6.method15();
            boolean var7 = var6.method5(this.displayWidth, this.displayHeight, var2);
            if (var7) {
               Bridge3_24 var8 = var6.method24().method11();
               this.displayWidth = var8.bridge$framebufferWidth();
               this.displayHeight = var8.bridge$framebufferHeight();
               LcuiScreen.method150(new ThreadModuleDump71(this));
               return;
            }
         }
      }

      var3.call(new Object[]{var1, var2});
   }

   @WrapOperation(method = "runGameLoop", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;getSystemTime()J"))
   private long lunar$rewindFpsSystemTime(Operation<Long> var1) {
      return ThreadModuleDump63.method3().bridge$getRealSystemTime();
   }

   @Annotation2(min = 5)
   @WrapOperation(method = "runTickMouse$v1_12", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;getSystemTime()J"))
   private long lunar$rewindSystemTime(Operation<Long> var1) {
      return ThreadModuleDump63.method4().method40().method85().method19() ? this.systemTime : (Long)var1.call(new Object[0]);
   }

   @Annotation2(min = 5)
   @WrapOperation(method = "runTickMouse$v1_12", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;isSpectator$v1_8()Z"))
   private boolean lunar$rewindFixNullPlayer$isSpectator(EntityPlayerSP var1, Operation<Boolean> var2) {
      return var1 == null ? false : (Boolean)var2.call(new Object[]{var1});
   }

   @Annotation2(min = 5)
   @WrapOperation(
      method = "runTickMouse$v1_12",
      at = @At(value = "FIELD", target = "Lnet/minecraft/client/entity/EntityPlayerSP;inventory:Lnet/minecraft/entity/player/InventoryPlayer;")
   )
   private InventoryPlayer lunar$rewindFixNullPlayer$inventory(EntityPlayerSP var1, Operation<InventoryPlayer> var2) {
      return var1 == null ? null : (InventoryPlayer)var2.call(new Object[]{var1});
   }

   @Annotation2(min = 5)
   @WrapWithCondition(method = "runTickMouse$v1_12", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/InventoryPlayer;changeCurrentItem(I)V"))
   private boolean lunar$rewindFixNullPlayer$changeCurrentItem(InventoryPlayer var1, int var2) {
      return var1 != null;
   }

   @Inject(method = "updateFramebufferSize", at = @At("TAIL"))
   private void lunar$webosr$updateFramebufferSize(CallbackInfo var1) {
      if (DriverViewLegacy.method21() != null) {
         DriverViewLegacy.method21().method3(this.displayWidth, this.displayHeight);
      }
   }

   @WrapOperation(method = "runTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiScreen;handleInput()V"))
   private void lunar$webosr$handleInput(GuiScreen var1, Operation<Void> var2) {
      if (DriverViewportLegacy.method50() != null && DriverViewportLegacy.method50().method40()) {
         if (Mouse.isCreated()) {
            while (Mouse.next()) {
               this.webosr$handleMouseInput();
            }
         }

         if (Keyboard.isCreated()) {
            while (Keyboard.next()) {
               this.webosr$handleKeyboardInput(true);
            }
         }
      } else {
         var2.call(new Object[]{var1});
      }
   }

   @WrapOperation(method = {"runTick", "runTickMouse$v1_12"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiScreen;handleMouseInput()V"))
   private void lunar$webosr$handleMouseInput(GuiScreen var1, Operation<Void> var2) {
      if (!this.webosr$handleMouseInput()) {
         var2.call(new Object[]{var1});
      }
   }

   @WrapOperation(
      method = {"runTick", "runTickKeyboard$v1_12"},
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiScreen;handleKeyboardInput()V")
   )
   private void lunar$webosr$handleKeyboardInput(GuiScreen var1, Operation<Void> var2) {
      if (!this.webosr$handleKeyboardInput(false)) {
         var2.call(new Object[]{var1});
      }
   }

   @Inject(method = "dispatchKeypresses", at = @At("HEAD"))
   private void lunar$webosr$dispatchKeypresses(CallbackInfo var1) {
      int var2 = Keyboard.getEventKey();
      char var3 = Keyboard.getEventCharacter();
      boolean var4 = Keyboard.isRepeatEvent();
      boolean var5 = Keyboard.getEventKeyState();
      KeyCode var6 = Bridge2Handler.method7(var2);
      if (var2 != 15 || !ThreadModuleDump63.method4().method40().method85().method17(RewindHandlers::method62)) {
         DriverViewLegacy.method21().method5(var6, var2, var2, var4 ? 2 : (var5 ? 1 : 0), Bridge2Handler.method5());
         boolean var7 = var2 == 203 || var2 == 205 || var2 == 200 || var2 == 208;
         if (var5 && !LcuiScreen.isCtrlKeyDown() && !var7) {
            DriverViewLegacy.method21().method6(var3);
         }
      }
   }

   @Inject(method = "dispatchKeypresses()V", at = @At("HEAD"), cancellable = true)
   private void lunar$dispatchKeyPress(CallbackInfo var1) {
      if (!Keyboard.isRepeatEvent()) {
         long var2 = Keyboard.getEventNanoseconds();
         com.moonsworth.lunar.client.event.mixin.rewindhandlers.KeybindEvent var4 = ClientEventBus.method29()
            .method12(
               com.moonsworth.lunar.client.event.mixin.rewindhandlers.KeybindEvent.class,
               () -> {
                  int var3 = Keyboard.getEventKey();
                  boolean var4x = Keyboard.getEventKeyState();
                  boolean var5 = this.lunar$lastEvent != -1L && var2 - this.lunar$lastEvent == 0L;
                  return new com.moonsworth.lunar.client.event.mixin.rewindhandlers.KeybindEvent(
                     Bridge2Handler.method7(var3), var4x ? InputActionLegacy.DOWN : InputActionLegacy.UP, var5
                  );
               }
            );
         if (var4 != null && var4.isCancelled()) {
            var1.cancel();
         }

         this.lunar$lastEvent = var2;
      }
   }

   @Unique
   private boolean webosr$handleMouseInput() {
      if (!ThreadModuleDump63.method4().method40().method85().method17(RewindHandlers::method62)) {
         int var1 = Mouse.getEventDWheel();
         if (var1 != 0) {
            DriverViewLegacy.method21().method9(0.0, var1 > 0 ? 1.0 : -1.0);
         }

         int var2 = Mouse.getEventButton();
         if (var2 != -1) {
            boolean var3 = Mouse.getEventButtonState();
            DriverViewLegacy.method21().method8(var2, var3 ? 1 : 0, Bridge3Handler.method2());
         }
      }

      return DriverViewportLegacy.method50() != null && DriverViewportLegacy.method50().method40();
   }

   @Unique
   private boolean webosr$handleKeyboardInput(boolean var1) {
      if (DriverViewportLegacy.method50() != null && DriverViewportLegacy.method50().method40()) {
         if (var1) {
            this.dispatchKeypresses();
         }

         return true;
      } else {
         return false;
      }
   }

   @Annotation2(1)
   @WrapOperation(
      method = "sendClickBlockToController",
      at = @At(value = "FIELD", target = "Lnet/minecraft/util/math/RayTraceResult$Type;BLOCK:Lnet/minecraft/util/math/RayTraceResult$Type;")
   )
   private MovingObjectType lunar$preventSendSwingIfPlacingBlockWhileBlockHitting(Operation<MovingObjectType> var1) {
      return !this.apollo$isDigAndUseAllowed() && ThreadModuleDump63.method4().method40().method98().method15().method14()
         ? MovingObjectType.MISS
         : (MovingObjectType)var1.call(new Object[0]);
   }

   @Annotation2(1)
   @Inject(method = "sendClickBlockToController", at = @At("RETURN"))
   private void lunar$resetBlockHittingForceAllowRightClick(boolean var1, CallbackInfo var2) {
      ThreadModuleDump63.method4().method40().method98().method15().method11(false);
   }

   @Annotation2(1)
   @WrapOperation(
      method = "rightClickMouse",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/PlayerControllerMP;getIsHittingBlock$v1_8()Z")
   )
   private boolean lunar$forcePlacingBlockWhenBlockHitting(PlayerControllerMP var1, Operation<Boolean> var2) {
      return ThreadModuleDump63.method4().method40().method98().method15().method14() ? false : (Boolean)var2.call(new Object[]{var1});
   }

   @Annotation2(1)
   @WrapOperation(
      method = "clickMouse",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getMaterial()Lnet/minecraft/block/material/Material;")
   )
   private Material lunar$doNotSendClickBlockIfUsingItemInBlockHit(Block var1, Operation<Material> var2) {
      return !this.apollo$isDigAndUseAllowed()
            && ThreadModuleDump63.method4().method40().method98().method15().method13()
            && this.gameSettings.keyBindUseItem.isKeyDown()
            && !this.playerController.getIsHittingBlock()
         ? Material.air
         : (Material)var2.call(new Object[]{var1});
   }

   @Annotation2(1)
   @WrapOperation(
      method = "sendClickBlockToController",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;isUsingItem$v1_7()Z")
   )
   private boolean apollo$allowDiggingWhileUsingItem$v1_8(EntityPlayerSP var1, Operation<Boolean> var2) {
      return this.apollo$isDigAndUseAllowed() ? false : (Boolean)var2.call(new Object[]{var1});
   }

   @Annotation2(5)
   @WrapOperation(
      method = "sendClickBlockToController",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;isHandActive$v1_12()Z")
   )
   private boolean apollo$allowDiggingWhileUsingItem$v1_12(EntityPlayerSP var1, Operation<Boolean> var2) {
      return this.apollo$isDigAndUseAllowed() ? false : (Boolean)var2.call(new Object[]{var1});
   }

   @Annotation2(5)
   @WrapOperation(
      method = "rightClickMouse",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/PlayerControllerMP;getIsHittingBlock$v1_8()Z")
   )
   private boolean apollo$allowUseWhileDigging$v1_12(PlayerControllerMP var1, Operation<Boolean> var2) {
      return this.apollo$isDigAndUseAllowed() ? false : (Boolean)var2.call(new Object[]{var1});
   }

   @Unique
   private boolean apollo$isDigAndUseAllowed() {
      return ThreadModuleDump63.method4()
         .method84()
         .<ApolloModuleHandler>method3(CombatModule.class)
         .filter(var0 -> (Boolean)var0.getOptions().get(CombatModule.ALLOW_DIG_AND_USE))
         .isPresent();
   }

   @Annotation2(max = 0)
   @WrapOperation(method = "dispatchKeypresses", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/settings/KeyBinding_v1_7;getKeyCode()I"))
   @Dynamic
   private int lunar$rewind$disableTwitchStream$v1_7(KeyBinding_v1_7 var1, Operation<Integer> var2) {
      return var1 == this.gameSettings.keyBindStreamStartStop$v1_7 && ThreadModuleDump63.method4().method40().method85().method19()
         ? -1
         : (Integer)var2.call(new Object[]{var1});
   }

   @Annotation2(1)
   @WrapOperation(method = "dispatchKeypresses", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/settings/KeyBinding_v1_8;getKeyCode()I"))
   private int lunar$rewind$disableTwitchStream$v1_8(KeyBinding var1, Operation<Integer> var2) {
      return var1 == this.gameSettings.keyBindStreamStartStop && ThreadModuleDump63.method4().method40().method85().method19()
         ? -1
         : (Integer)var2.call(new Object[]{var1});
   }

   @Inject(method = {"setIngameFocus", "setIngameNotInFocus"}, at = @At("HEAD"), cancellable = true)
   private void lunar$rewindSetIngameFocus(CallbackInfo var1) {
      if (ThreadModuleDump63.method4().method40().method85().method19()) {
         var1.cancel();
      }
   }

   @Annotation2(max = 0)
   @WrapOperation(method = "runTick()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/settings/KeyBinding_v1_7;isPressed()Z"))
   @Dynamic
   private boolean lunar$rewindPreventKeybind$v1_7(KeyBinding_v1_7 var1, Operation<Boolean> var2) {
      return ThreadModuleDump63.method4().method40().method85().method19() ? false : (Boolean)var2.call(new Object[]{var1});
   }

   @Annotation2(1)
   @WrapOperation(method = "runTick()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/settings/KeyBinding_v1_8;isPressed()Z"))
   @Dynamic
   private boolean lunar$rewindPreventKeybind$v1_8(KeyBinding var1, Operation<Boolean> var2) {
      return ThreadModuleDump63.method4().method40().method85().method19() ? false : (Boolean)var2.call(new Object[]{var1});
   }

   @Annotation2(min = 5)
   @Inject(method = "processKeyBinds$v1_12()V", at = @At("HEAD"), cancellable = true)
   private void lunar$rewindPreventKeybind$v1_12(CallbackInfo var1) {
      if (ThreadModuleDump63.method4().method40().method85().method19()) {
         var1.cancel();
      }
   }

   @Annotation2(min = 1)
   @Inject(method = "clickMouse", at = @At("HEAD"))
   private void lunar$startAttack(CallbackInfo var1) {
      if (this.leftClickCounter > 0 && ThreadModuleDump63.method4().method40().method98().method13()) {
         ThreadModuleDump63.method7().bridge$swingHandVisually(0);
      }
   }

   @Annotation2(max = 1)
   @WrapOperation(
      method = {
            "<init>(Lnet/minecraft/client/main/GameConfiguration;)V",
            "<init>(Lnet/minecraft/util/Session;IIZZLjava/io/File;Ljava/io/File;Ljava/io/File;Ljava/net/Proxy;Ljava/lang/String;Lcom/google/common/collect/Multimap;Ljava/lang/String;)V"
      },
      at = @At(value = "INVOKE", target = "Lorg/apache/logging/log4j/Logger;info(Ljava/lang/String;)V", ordinal = 1),
      require = 0
   )
   private void lunar$nullSessionLog$v1_8(Logger var1, String var2, Operation<Void> var3) {
   }

   @Annotation2(5)
   @WrapOperation(
      method = "<init>(Lnet/minecraft/client/main/GameConfiguration;)V",
      at = @At(value = "INVOKE", target = "Lorg/apache/logging/log4j/Logger;debug(Ljava/lang/String;Ljava/lang/Object;)V"),
      require = 0
   )
   private void lunar$nullSessionLog$v1_12(Logger var1, String var2, Object var3, Operation<Void> var4) {
   }

   @Annotation2(max = 0)
   @WrapOperation(method = "runTick", at = @At(value = "INVOKE", target = "Ljava/util/Queue;isEmpty()Z", ordinal = 0))
   @Dynamic
   private boolean lunar$rewindMoveScheduledTasksLogic$v1_7(Queue var1, Operation<Boolean> var2) {
      return ThreadModuleDump63.method4() != null
            && ThreadModuleDump63.method4().method40() != null
            && ThreadModuleDump63.method4().method40().method85().method19()
         ? true
         : (Boolean)var2.call(new Object[]{var1});
   }

   @Annotation2(max = 0)
   @Inject(method = "runGameLoop", at = @At(value = "INVOKE", target = "Ljava/lang/System;nanoTime()J", ordinal = 0))
   @Dynamic
   private void lunar$rewindMoveScheduledTasksLogic$v1_7(CallbackInfo var1) {
      if (ThreadModuleDump63.method4() != null
         && ThreadModuleDump63.method4().method40() != null
         && ThreadModuleDump63.method4().method40().method85().method19()) {
         this.profiler.startSection("scheduledExecutables");
         synchronized (this.scheduledTasks$v1_7) {
            while (!this.scheduledTasks$v1_7.isEmpty()) {
               ((FutureTask)this.scheduledTasks$v1_7.poll()).run();
            }
         }

         this.profiler.endSection();
      }
   }

   @Inject(method = "middleClickMouse", at = @At("HEAD"))
   private void lunar$pickBlockEvent(CallbackInfo var1) {
      ClientEventBus.method29().method12(EventBlockPick.class, EventBlockPick::new);
   }

   @Annotation2(max = 0)
   @WrapWithCondition(
      method = "runTick",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityClientPlayerMP;dropOneItem(Z)Lnet/minecraft/entity/item/EntityItem;")
   )
   @Dynamic
   private boolean lunar$wrapDropItem$v1_7(EntityClientPlayerMP var1, boolean var2) {
      if (var1 != ThreadModuleDump63.method7()) {
         return true;
      }

      ItemDropEvent var3 = ClientEventBus.method29().method12(ItemDropEvent.class, () -> new ItemDropEvent((ItemStackBridge)var1.getHeldItem()));
      return var3 == null || !var3.isCancelled();
   }

   @Annotation2(min = 1, max = 1)
   @WrapWithCondition(
      method = "runTick",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;dropOneItem$v1_8(Z)Lnet/minecraft/entity/item/EntityItem;")
   )
   @Dynamic
   private boolean lunar$wrapDropItem$v1_8(EntityPlayerSP var1, boolean var2) {
      if (var1 != ThreadModuleDump63.method7()) {
         return true;
      }

      ItemDropEvent var3 = ClientEventBus.method29().method12(ItemDropEvent.class, () -> new ItemDropEvent((ItemStackBridge)var1.getHeldItem()));
      return var3 == null || !var3.isCancelled();
   }

   @Annotation2(min = 5)
   @WrapWithCondition(
      method = "processKeyBinds$v1_12",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;dropItem$v1_12(Z)Lnet/minecraft/entity/item/EntityItem;")
   )
   private boolean lunar$wrapDropItem$v1_12(EntityPlayerSP var1, boolean var2) {
      if (var1 != ThreadModuleDump63.method7()) {
         return true;
      }

      ItemDropEvent var3 = ClientEventBus.method29()
         .method12(ItemDropEvent.class, () -> new ItemDropEvent((ItemStackBridge)var1.getHeldItem(EnumHand.MAIN_HAND)));
      return var3 == null || !var3.isCancelled();
   }

   @Annotation2(min = 5)
   @WrapWithCondition(
      method = "processKeyBinds$v1_12",
      slice = @Slice(
         from = @At(
            value = "FIELD",
            target = "Lnet/minecraft/client/settings/GameSettings;keyBindSwapHands$v1_8:Lnet/minecraft/client/settings/KeyBinding_v1_8;",
            opcode = 180
         )
      ),
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/network/NetHandlerPlayClient;sendPacket$v1_12(Lnet/minecraft/network/Packet_v1_8;)V",
         ordinal = 0
      )
   )
   private boolean lunar$preventSwapHands(NetHandlerPlayClient var1, Packet var2) {
      InventoryMods var3 = ThreadModuleDump63.method4().method40().method93();
      SlotLocking var4 = var3.method14();
      return var3.isEnabled() && var4.isEnabled() ? !var4.method11(Inventorymod.field1) && !var4.method11(Inventorymod.method2()) : true;
   }

   @WrapWithCondition(method = "runGameLoop", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/shader/Framebuffer;framebufferRender(II)V"))
   private boolean lunar$rewindLimitDisplayUpdateWhenRendering$framebufferRender(Framebuffer var1, int var2, int var3) {
      return lunar$rewindCanUpdateDisplay();
   }

   @Annotation2(max = 0)
   @WrapWithCondition(method = "runGameLoop", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;resetSize$v1_7()V"))
   @Dynamic
   private boolean lunar$rewindLimitDisplayUpdateWhenRendering$updateDisplay$v1_7(Minecraft var1) {
      return lunar$rewindCanUpdateDisplay();
   }

   @Annotation2(min = 1)
   @WrapWithCondition(method = "runGameLoop", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;updateDisplay$v1_8()V"))
   private boolean lunar$rewindLimitDisplayUpdateWhenRendering$updateDisplay$v1_8(Minecraft var1) {
      return lunar$rewindCanUpdateDisplay();
   }

   @WrapWithCondition(method = "runGameLoop", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/Display;sync(I)V"))
   private boolean lunar$rewindUnlimitFpsWhenRendering(int var1) {
      return ThreadModuleDump63.method4() == null
         || ThreadModuleDump63.method4().method40() == null
         || !ThreadModuleDump63.method4().method40().method85().method17(var0 -> var0.method57().method25());
   }

   @Unique
   private static boolean lunar$rewindCanUpdateDisplay() {
      return ThreadModuleDump63.method4() == null
         || ThreadModuleDump63.method4().method40() == null
         || !ThreadModuleDump63.method4().method40().method85().method17(var0 -> var0.method57().method25() && !var0.method57().method29());
   }

   @Inject(method = "displayInGameMenu", at = @At("HEAD"), cancellable = true)
   private void lunar$rewindPreventPauseMenu(CallbackInfo var1) {
      if (ThreadModuleDump63.method4().method40().method85().method19()) {
         var1.cancel();
      }
   }

   @Inject(method = "displayDebugInfo", at = @At("HEAD"), cancellable = true)
   private void lunar$displayPieChart(long var1, CallbackInfo var3) {
      if (ThreadModuleDump63.method4().method40().method95().isEnabled()) {
         var3.cancel();
      }
   }

   @ModifyExpressionValue(
      method = "runGameLoop",
      at = @At(value = "FIELD", target = "Lnet/minecraft/client/settings/GameSettings;showDebugProfilerChart:Z", opcode = 180, ordinal = 0)
   )
   private boolean lunar$runPieChartProfiler(boolean var1) {
      return F3Display.shouldShowVanillaPieChart() ? true : var1;
   }

   @Annotation2(min = 5)
   @ModifyExpressionValue(
      method = "runTickKeyboard$v1_12",
      at = @At(value = "FIELD", target = "Lnet/minecraft/client/settings/GameSettings;showDebugProfilerChart:Z", opcode = 180, ordinal = 0)
   )
   private boolean lunar$pieChartKeybinds$v1_12(boolean var1) {
      return F3Display.shouldShowVanillaPieChart() ? true : var1;
   }

   @Annotation2(max = 1)
   @ModifyExpressionValue(
      method = "runTick",
      at = @At(value = "FIELD", target = "Lnet/minecraft/client/settings/GameSettings;showDebugProfilerChart:Z", opcode = 180, ordinal = 0)
   )
   @Dynamic
   private boolean lunar$pieChartKeybinds$v1_8(boolean var1) {
      return F3Display.shouldShowVanillaPieChart() ? true : var1;
   }

   @Override
   public boolean bridge$isMouseButtonDown(int var1) {
      return Mouse.isButtonDown(var1);
   }
}
