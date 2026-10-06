package com.moonsworth.lunar.client.cosmetics.skin;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import com.moonsworth.lunar.bridge.AutoCloseableExtension;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ThreadDownloadImageDataBridge;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.Bridge8Extension3;
import com.moonsworth.lunar.bridge.Bridge8Extension33;
import com.moonsworth.lunar.bridge.TextureManagerBridge;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.TextureFormat;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;

public class SkinDataCache<T> {
   private final ConcurrentHashMap<ResourceLocationBridge, CompletableFuture<T>> field1;
   private final ConcurrentHashMap<ResourceLocationBridge, T> field2;
   private final int field3;
   private final int field4;

   public SkinDataCache(int number1, int number2) {
      this.field3 = number1;
      this.field4 = number2;
      this.field1 = new ConcurrentHashMap<>();
      this.field2 = new ConcurrentHashMap<>();
   }

   public Optional<T> method1(EntityPlayerBridge bridgeextension2221, Function<AutoCloseableExtension, T> function2) {
      if (bridgeextension2221.bridge$isSkinTextureUploaded() && !SkinLayerFactory.method1(bridgeextension2221)) {
         ResourceLocationBridge horsestats143 = bridgeextension2221.bridge$getLocationSkin();
         return this.method3(horsestats143, function2);
      } else {
         return Optional.empty();
      }
   }

   public Optional<T> method2(@NotNull GameProfile gameprofile1, Function<AutoCloseableExtension, T> function2) {
      Optional optional3 = Ref.method3().bridge$getSkinManager().bridge$getSkinLocation(gameprofile1, Type.SKIN);
      return optional3.isEmpty() ? Optional.empty() : this.method3((ResourceLocationBridge)optional3.get(), function2);
   }

   public Optional<T> method3(ResourceLocationBridge horsestats141, Function<AutoCloseableExtension, T> function2) {
      Object obj3 = this.field2.get(horsestats141);
      if (obj3 != null) {
         return Optional.of((T)obj3);
      }

      CompletableFuture completablefuture4 = this.field1.get(horsestats141);
      if (completablefuture4 != null) {
         return this.method4(horsestats141, completablefuture4);
      }

      TextureManagerBridge bridge8handler25 = Ref.method3().bridge$getTextureManager();
      Bridge8Extension3 bridge8extension36 = bridge8handler25.bridge$getTexture(horsestats141);
      if (bridge8extension36 == null) {
         return Optional.empty();
      }

      if (bridge8extension36 instanceof ThreadDownloadImageDataBridge bridge20extension8) {
         CompletableFuture completablefuture9 = bridge20extension8.bridge$requestContent();
         if (completablefuture9 == null) {
            return Optional.empty();
         }

         completablefuture4 = completablefuture9.exceptionallyCompose(
               arg2x -> {
                  CompletableFuture completablefuture3x = new CompletableFuture();
                  Ref.method3()
                     .bridge$submit(
                        () -> Bridge.method8()
                           .method91(
                              bridge20extension8,
                              0,
                              0,
                              this.field3,
                              this.field4,
                              TextureFormat.RGBA8,
                              arg2xxx -> completablefuture3x.complete(AutoCloseableExtension.method3(this.field3, this.field4, arg2xxx.asIntBuffer()))
                           )
                     );
                  return completablefuture3x;
               }
            )
            .thenApply(function2);
         this.field1.put(horsestats141, completablefuture4);
      } else {
         if (Ref.MC_VERSION < 6 || !(bridge8extension36 instanceof Bridge8Extension33 bridge8extension337)) {
            return Optional.empty();
         }

         completablefuture4 = CompletableFuture.completedFuture(function2.apply(Bridge.method8().method16(bridge8extension337.bridge$getNativeImage())));
      }

      return this.method4(horsestats141, completablefuture4);
   }

   private Optional<T> method4(ResourceLocationBridge horsestats141, CompletableFuture<T> completablefuture2) {
      if (completablefuture2.isDone()) {
         try {
            Object obj3 = completablefuture2.get();
            this.field1.remove(horsestats141);
            this.field2.put(horsestats141, (T)obj3);
            return Optional.of((T)obj3);
         } catch (ExecutionException executionexception4) {
            CrashReporter.method5(executionexception4, "pollFuture SkinDataCache");
            return Optional.empty();
         } catch (InterruptedException interruptedexception5) {
         }
      }

      return Optional.empty();
   }

   public void clear() {
      this.field1.clear();
      this.field2.clear();
   }

   public void method5(ResourceLocationBridge horsestats141) {
      CompletableFuture completablefuture2 = this.field1.get(horsestats141);
      this.field1.clear();
      if (completablefuture2 != null) {
         this.field1.put(horsestats141, completablefuture2);
      }

      Object obj3 = this.field2.get(horsestats141);
      this.field2.clear();
      if (obj3 != null) {
         this.field2.put(horsestats141, (T)obj3);
      }
   }

   public void method6(Bridge5_11 bridge5_111) {
      this.method7(bridge5_111.bridge$getLocationSkin());
   }

   public void method7(ResourceLocationBridge horsestats141) {
      this.field1.remove(horsestats141);
      this.field2.remove(horsestats141);
   }
}
