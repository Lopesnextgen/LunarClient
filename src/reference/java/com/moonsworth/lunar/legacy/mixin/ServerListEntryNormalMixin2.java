package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import com.moonsworth.lunar.bridge.Bridge3_19;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
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
public abstract class ServerListEntryNormalMixin2 {
   @Final
   @Shadow
   public static ThreadPoolExecutor EXECUTOR;
   @Final
   @Shadow
   public ServerData server;
   @Unique
   private static final ExecutorService TIMEOUT_EXECUTOR = Executors.newCachedThreadPool();

   @Shadow
   public abstract ServerData getServerData();

   @Annotation2(min = 5)
   @Inject(method = "drawEntry$v1_12", at = @At("HEAD"))
   private void lunar$drawEntry(int var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, float var9, CallbackInfo var10) {
      ThreadModuleDump63.method4()
         .method78()
         .method2((Bridge3_19)this.getServerData(), AbstractRenderContext.method32().method42(), var2, var3);
   }

   @Annotation2(1)
   @Inject(method = "drawEntry$v1_8", at = @At("HEAD"))
   private void lunar$drawEntry(int var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, CallbackInfo var9) {
      ThreadModuleDump63.method4()
         .method78()
         .method2((Bridge3_19)this.getServerData(), AbstractRenderContext.method32().method42(), var2, var3);
   }

   @Annotation2(max = 0)
   @Inject(method = "drawEntry$v1_7", at = @At("HEAD"))
   private void lunar$drawEntry(int var1, int var2, int var3, int var4, int var5, Tessellator var6, int var7, int var8, boolean var9, CallbackInfo var10) {
      ThreadModuleDump63.method4()
         .method78()
         .method2((Bridge3_19)this.getServerData(), AbstractRenderContext.method32().method42(), var2, var3);
   }

   @Redirect(
      method = {"drawEntry$v1_7", "drawEntry$v1_8", "drawEntry$v1_12"},
      at = @At(value = "INVOKE", target = "Ljava/util/concurrent/ThreadPoolExecutor;submit(Ljava/lang/Runnable;)Ljava/util/concurrent/Future;")
   )
   private Future<?> lunar$removeOldSubmit(ThreadPoolExecutor var1, Runnable var2, @Share("runnable") LocalRef<Runnable> var3) {
      var3.set(var2);
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
   private void lunar$addPingTimeout(CallbackInfo var1, @Share("runnable") LocalRef<Runnable> var2) {
      Runnable var3 = (Runnable)var2.get();
      if (var3 != null) {
         Future var4 = TIMEOUT_EXECUTOR.submit(var3);
         EXECUTOR.submit(() -> {
            try {
               var4.get(3L, TimeUnit.SECONDS);
            } catch (Exception var2x) {
            }
         });
      }
   }
}
