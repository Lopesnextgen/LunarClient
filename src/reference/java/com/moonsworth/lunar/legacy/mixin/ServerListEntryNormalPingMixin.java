package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import com.moonsworth.lunar.bridge.ServerDataBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.gui.ServerListEntryNormal;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.renderer.Tessellator;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerListEntryNormal.class)
public abstract class ServerListEntryNormalPingMixin {
   @Final
   @Shadow
   public static ThreadPoolExecutor EXECUTOR;
   @Final
   @Shadow
   public ServerData server;
   @Unique
   private static final ExecutorService TIMEOUT_EXECUTOR = Executors.newCachedThreadPool();

   public ServerListEntryNormalPingMixin() {
   }

   @Shadow
   public abstract ServerData getServerData();

   @VersionGate(min = 5)
   @Inject(method = "drawEntry$v1_12", at = @At("HEAD"))
   private void lunar$drawEntry(int number1, int number2, int number3, int number4, int number5, int number6, int number7, boolean flag8, float value9, CallbackInfo callback10) {
      Ref.method4()
         .method78()
         .method2((ServerDataBridge)this.getServerData(), AbstractRenderContext.method32().RHCRIRCHOIICCIIIIHROORCHOHRIRO(), number2, number3);
   }

   @VersionGate(1)
   @Inject(method = "drawEntry$v1_8", at = @At("HEAD"))
   private void lunar$drawEntry(int number1, int number2, int number3, int number4, int number5, int number6, int number7, boolean flag8, CallbackInfo callback9) {
      Ref.method4()
         .method78()
         .method2((ServerDataBridge)this.getServerData(), AbstractRenderContext.method32().RHCRIRCHOIICCIIIIHROORCHOHRIRO(), number2, number3);
   }

   @VersionGate(max = 0)
   @Inject(method = "drawEntry$v1_7", at = @At("HEAD"))
   private void lunar$drawEntry(int number1, int number2, int number3, int number4, int number5, Tessellator tessellator6, int number7, int number8, boolean flag9, CallbackInfo callback10) {
      Ref.method4()
         .method78()
         .method2((ServerDataBridge)this.getServerData(), AbstractRenderContext.method32().RHCRIRCHOIICCIIIIHROORCHOHRIRO(), number2, number3);
   }

   @Redirect(
      method = {"drawEntry$v1_7", "drawEntry$v1_8", "drawEntry$v1_12"},
      at = @At(value = "INVOKE", target = "Ljava/util/concurrent/ThreadPoolExecutor;submit(Ljava/lang/Runnable;)Ljava/util/concurrent/Future;")
   )
   private Future<?> lunar$removeOldSubmit(ThreadPoolExecutor threadpoolexecutor1, Runnable runnable2, @Share("runnable") LocalRef<Runnable> localref3) {
      localref3.set(runnable2);
      return null;
   }

   @Inject(
      method = {"drawEntry$v1_7", "drawEntry$v1_8", "drawEntry$v1_12"},
      at = @At(
         value = "INVOKE",
         target = "Ljava/util/concurrent/ThreadPoolExecutor;submit(Ljava/lang/Runnable;)Ljava/util/concurrent/Future;",
         shift = Shift.AFTER
      )
   )
   private void lunar$addPingTimeout(CallbackInfo callback1, @Share("runnable") LocalRef<Runnable> localref2) {
      Runnable runnable3 = (Runnable)localref2.get();
      if (runnable3 != null) {
         Future future4 = TIMEOUT_EXECUTOR.submit(runnable3);
         EXECUTOR.submit(() -> {
            try {
               future4.get(3L, TimeUnit.SECONDS);
            } catch (Exception exception2x) {
            }
         });
      }
   }
}
