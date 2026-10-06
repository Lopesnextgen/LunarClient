package com.moonsworth.lunar.client.framework.feature.pkg;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import com.moonsworth.lunar.bridge.AutoCloseableExtension;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge20Extension;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.Bridge8Extension3;
import com.moonsworth.lunar.bridge.Bridge8Extension33;
import com.moonsworth.lunar.bridge.Bridge8Handler2;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.TexturePixelFormat;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;

public class Pkg2<T> {
   private final ConcurrentHashMap<ResourceLocationBridge, CompletableFuture<T>> field1;
   private final ConcurrentHashMap<ResourceLocationBridge, T> field2;
   private final int field3;
   private final int field4;

   public Pkg2(int var1, int var2) {
      this.field3 = var1;
      this.field4 = var2;
      this.field1 = new ConcurrentHashMap<>();
      this.field2 = new ConcurrentHashMap<>();
   }

   public Optional<T> method1(EntityPlayerBridge var1, Function<AutoCloseableExtension, T> var2) {
      if (var1.bridge$isSkinTextureUploaded() && !Pkg5.method1(var1)) {
         ResourceLocationBridge var3 = var1.bridge$getLocationSkin();
         return this.method3(var3, var2);
      } else {
         return Optional.empty();
      }
   }

   public Optional<T> method2(@NotNull GameProfile var1, Function<AutoCloseableExtension, T> var2) {
      Optional var3 = ThreadModuleDump63.method3().bridge$getSkinManager().bridge$getSkinLocation(var1, Type.SKIN);
      return var3.isEmpty() ? Optional.empty() : this.method3((ResourceLocationBridge)var3.get(), var2);
   }

   public Optional<T> method3(ResourceLocationBridge var1, Function<AutoCloseableExtension, T> var2) {
      Object var3 = this.field2.get(var1);
      if (var3 != null) {
         return Optional.of((T)var3);
      }

      CompletableFuture var4 = this.field1.get(var1);
      if (var4 != null) {
         return this.method4(var1, var4);
      }

      Bridge8Handler2 var5 = ThreadModuleDump63.method3().bridge$getTextureManager();
      Bridge8Extension3 var6 = var5.bridge$getTexture(var1);
      if (var6 == null) {
         return Optional.empty();
      }

      if (var6 instanceof Bridge20Extension var8) {
         CompletableFuture var9 = var8.bridge$requestContent();
         if (var9 == null) {
            return Optional.empty();
         }

         var4 = var9.exceptionallyCompose(
               var2x -> {
                  CompletableFuture var3x = new CompletableFuture();
                  ThreadModuleDump63.method3()
                     .bridge$submit(
                        () -> Bridge.method8()
                           .method91(
                              var8,
                              0,
                              0,
                              this.field3,
                              this.field4,
                              TexturePixelFormat.RGBA8,
                              var2xxx -> var3x.complete(AutoCloseableExtension.method3(this.field3, this.field4, var2xxx.asIntBuffer()))
                           )
                     );
                  return var3x;
               }
            )
            .thenApply(var2);
         this.field1.put(var1, var4);
      } else {
         if (ThreadModuleDump63.MC_VERSION < 6 || !(var6 instanceof Bridge8Extension33 var7)) {
            return Optional.empty();
         }

         var4 = CompletableFuture.completedFuture(var2.apply(Bridge.method8().method16(var7.bridge$getNativeImage())));
      }

      return this.method4(var1, var4);
   }

   private Optional<T> method4(ResourceLocationBridge var1, CompletableFuture<T> var2) {
      if (var2.isDone()) {
         try {
            Object var3 = var2.get();
            this.field1.remove(var1);
            this.field2.put(var1, (T)var3);
            return Optional.of((T)var3);
         } catch (ExecutionException var4) {
            Inventorymod2.method5(var4, "pollFuture SkinDataCache");
            return Optional.empty();
         } catch (InterruptedException var5) {
         }
      }

      return Optional.empty();
   }

   public void clear() {
      this.field1.clear();
      this.field2.clear();
   }

   public void method5(ResourceLocationBridge var1) {
      CompletableFuture var2 = this.field1.get(var1);
      this.field1.clear();
      if (var2 != null) {
         this.field1.put(var1, var2);
      }

      Object var3 = this.field2.get(var1);
      this.field2.clear();
      if (var3 != null) {
         this.field2.put(var1, (T)var3);
      }
   }

   public void method6(Bridge5_11 var1) {
      this.method7(var1.bridge$getLocationSkin());
   }

   public void method7(ResourceLocationBridge var1) {
      this.field1.remove(var1);
      this.field2.remove(var1);
   }
}
