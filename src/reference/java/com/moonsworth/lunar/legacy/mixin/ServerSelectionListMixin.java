package com.moonsworth.lunar.legacy.mixin;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import com.moonsworth.lunar.bridge.Bridge3_19;
import com.moonsworth.lunar.bridge.Bridge4_4;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import com.moonsworth.lunar.legacy.GuiListExtendedImpl;
import com.moonsworth.lunar.legacy.GuiListExtendedImpl2;
import com.moonsworth.lunar.legacy.MixinHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.ServerListEntryLanDetected;
import net.minecraft.client.gui.ServerListEntryNormal;
import net.minecraft.client.gui.ServerSelectionList;
import net.minecraft.client.gui.GuiListExtended.IGuiListEntry;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.ServerList;
import net.minecraft.client.network.LanServerInfo;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.input.Mouse;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerSelectionList.class)
public abstract class ServerSelectionListMixin extends GuiListExtended implements MixinHelper {
   @Unique
   private static final int MAX_ACTIVE_PINGER_THREADS = 30;
   @Unique
   private static final int lunar$ROW_ACTIONS_WIDTH = 32;
   @Final
   @Shadow
   public GuiMultiplayer owner;
   @Final
   @Shadow
   public List<ServerListEntryNormal> serverListInternet;
   @Final
   @Shadow
   public List field_148198_l$v1_7;
   @Unique
   private final List<GuiListExtendedImpl2> lunar$hostedWorldServerEntries = new ArrayList<>();
   @Unique
   private final GuiListExtendedImpl lunar$hostedWorldsHeader = new GuiListExtendedImpl();
   @Unique
   private boolean lunar$hasInitializedCustomThreadPool = false;
   @Unique
   @Nullable
   private ServerData lunar$draggingServer;

   public ServerSelectionListMixin(Minecraft var1, int var2, int var3, int var4, int var5, int var6) {
      super(var1, var2, var3, var4, var5, var6);
   }

   public void drawScreen(int var1, int var2, float var3) {
      super.drawScreen(var1, var2, var3);
      this.lunar$tickServerDrag(var1, var2);
   }

   @Override
   public void lunar$armServerDrag(int var1, int var2, int var3) {
      this.lunar$draggingServer = null;
      if (var3 == 0 && ThreadModuleDump63.method4().method41().method6().method57().get()) {
         int var4 = this.getSlotIndexFromScreenCoords(var1, var2);
         ServerList var5 = this.owner.getServerList();
         if (var4 >= 0 && var4 < this.lunar$onlineServerCount() && var4 < var5.countServers()) {
            int var6 = this.left + this.width / 2 - this.getListWidth() / 2 + 2;
            if (var1 - var6 > 32) {
               ServerData var7 = var5.getServerData(var4);
               if (!((Bridge3_19)var7).bridge$isPinned()) {
                  this.lunar$draggingServer = var7;
               }
            }
         }
      }
   }

   @Annotation2(min = 5)
   @Inject(method = "updateNetworkServers$v1_12", at = @At("HEAD"))
   private void lunar$onUpdateServers$v1_12(List<LanServerInfo> var1, CallbackInfo var2) {
      this.lunar$addHostedWorlds();
   }

   @Annotation2(max = 0)
   @Inject(method = "func_148194_a$v1_7", at = @At("HEAD"))
   private void lunar$onUpdateServers$v1_7(List var1, CallbackInfo var2) {
      this.lunar$addHostedWorlds();
   }

   @Unique
   private void lunar$addHostedWorlds() {
      this.lunar$hostedWorldServerEntries.clear();
      ThreadModuleDump63.method4().method81().method41().forEach(var1 -> this.lunar$hostedWorldServerEntries.add(new GuiListExtendedImpl2(this.owner, var1)));
   }

   @Annotation2(min = 5)
   @WrapOperation(method = "updateNetworkServers$v1_12", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z"))
   private <E> boolean lunar$excludeSelfLAN$v1_12(List var1, E var2, Operation<Boolean> var3) {
      return this.lunar$shouldExcludeLanServer(var2) ? false : (Boolean)var3.call(new Object[]{var1, var2});
   }

   @Annotation2(max = 0)
   @WrapOperation(method = "func_148194_a$v1_7", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z"))
   private <E> boolean lunar$excludeSelfLAN$v1_7(List var1, E var2, Operation<Boolean> var3) {
      return this.lunar$shouldExcludeLanServer(var2) ? false : (Boolean)var3.call(new Object[]{var1, var2});
   }

   @Unique
   private <E> boolean lunar$shouldExcludeLanServer(E var1) {
      if (var1 instanceof ServerListEntryLanDetected var2 && ThreadModuleDump63.method4().method81().method20()) {
         String var3 = ThreadModuleDump63.MC_VERSION >= 5 ? var2.getServerData$v1_12().lanServerIpPort : "0";
         if (var3.endsWith(":" + ThreadModuleDump63.method4().method81().method28().method1().getPort())) {
            return true;
         }
      }

      return false;
   }

   @ModifyReturnValue(method = "getSize", at = @At("RETURN"))
   public int lunar$getSize(int var1) {
      return var1 + 1 + this.lunar$hostedWorldServerEntries.size();
   }

   @Inject(method = "getListEntry", at = @At(value = "INVOKE", target = "Ljava/util/List;size()I", ordinal = 1, shift = Shift.BY, by = 3), cancellable = true)
   public void lunar$addHostedWorlds(int var1, CallbackInfoReturnable<IGuiListEntry> var2, @Local(argsOnly = true) LocalIntRef var3) {
      if (var3.get() == 0) {
         var2.setReturnValue(this.lunar$hostedWorldsHeader);
      } else {
         var3.set(var3.get() - 1);
         if (var3.get() < this.lunar$hostedWorldServerEntries.size()) {
            var2.setReturnValue(this.lunar$hostedWorldServerEntries.get(var3.get()));
         } else {
            var3.set(var3.get() - this.lunar$hostedWorldServerEntries.size());
         }
      }
   }

   @Inject(method = {"func_148195_a$v1_7", "updateOnlineServers$v1_12"}, at = @At("HEAD"))
   public void lunar$updateOnlineServers(ServerList var1, CallbackInfo var2) {
      int var3 = ThreadModuleDump63.MC_VERSION >= 1 ? this.serverListInternet.size() : this.field_148198_l$v1_7.size();
      int var4 = Math.min(30, var3);
      if (!this.lunar$hasInitializedCustomThreadPool) {
         this.lunar$hasInitializedCustomThreadPool = true;
         this.lunar$resetCustomThreadPool(var4);
      } else if (ServerListEntryNormal.field_148302_b.getActiveCount() == var4) {
         this.lunar$resetCustomThreadPool(var4);
      }
   }

   @Unique
   private void lunar$resetCustomThreadPool(int var1) {
      ServerListEntryNormal.field_148302_b.shutdownNow();
      ServerListEntryNormalMixin3.bridge$setExecutor(
         new ScheduledThreadPoolExecutor(var1, new ThreadFactoryBuilder().setNameFormat("Server Pinger #%d").setDaemon(true).build())
      );
      ServerListEntryNormal.field_148302_b.setKeepAliveTime(3L, TimeUnit.SECONDS);
      ServerListEntryNormal.field_148302_b.allowCoreThreadTimeOut(true);
   }

   @Unique
   private void lunar$tickServerDrag(int var1, int var2) {
      if (this.lunar$draggingServer != null) {
         if (!Mouse.isButtonDown(0)) {
            this.lunar$draggingServer = null;
         } else {
            if (ThreadModuleDump63.MC_VERSION >= 1) {
               this.initialClickY = -2;
            } else {
               this.initialClickY$v1_7 = -2.0F;
            }

            ServerList var3 = this.owner.getServerList();
            int var4 = -1;

            for (int var5 = 0; var5 < var3.countServers(); var5++) {
               if (var3.getServerData(var5) == this.lunar$draggingServer) {
                  var4 = var5;
                  break;
               }
            }

            if (var4 == -1) {
               this.lunar$draggingServer = null;
            } else {
               int var9 = this.getSlotIndexFromScreenCoords(var1, var2);
               if (var9 >= 0 && var9 != var4 && var9 < this.lunar$onlineServerCount() && var9 < var3.countServers()) {
                  Bridge4_4 var6 = (Bridge4_4)var3;
                  int var7 = var4;

                  while (var7 != var9) {
                     int var8 = var7 < var9 ? var7 + 1 : var7 - 1;
                     if (!var6.bridge$canSwapServers(var7, var8)) {
                        break;
                     }

                     var3.swapServers(var7, var8);
                     var7 = var8;
                  }

                  if (var7 != var4) {
                     this.owner.selectServer(var7);
                     ServerSelectionList var10 = (ServerSelectionList)this;
                     if (ThreadModuleDump63.MC_VERSION >= 5) {
                        var10.updateOnlineServers$v1_12(var3);
                     } else {
                        var10.func_148195_a(var3);
                     }
                  }
               }
            }
         }
      }
   }

   @Unique
   private int lunar$onlineServerCount() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? this.serverListInternet.size() : this.field_148198_l$v1_7.size();
   }
}
