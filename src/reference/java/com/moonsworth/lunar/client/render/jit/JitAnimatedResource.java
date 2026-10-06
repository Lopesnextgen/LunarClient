package com.moonsworth.lunar.client.render.jit;

import com.moonsworth.lunar.bridge.AutoCloseableImpl;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge11_2;
import com.moonsworth.lunar.bridge.Bridge3Extension;
import com.moonsworth.lunar.bridge.Bridge3Extension2_2;
import com.moonsworth.lunar.bridge.Bridge3Extension_7;
import com.moonsworth.lunar.bridge.Bridge6_9;
import com.moonsworth.lunar.bridge.Bridge7_3;
import com.moonsworth.lunar.bridge.Bridge8Extension3;
import com.moonsworth.lunar.bridge.Bridge8Extension34;
import com.moonsworth.lunar.bridge.Bridge8Handler2;
import com.moonsworth.lunar.bridge.BridgeType2_5;
import com.moonsworth.lunar.bridge.ColorChannelOrder;
import com.moonsworth.lunar.bridge.Bridge_58;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.util.ThreadModuleDump37;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.render.texture.Alert4;
import com.moonsworth.lunar.client.util.alert.Alert5;
import com.moonsworth.lunar.client.util.alert.Alert5Handler;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class JitAnimatedResource extends JitResource<Bridge8Extension3> implements Bridge3Extension_7, Alert5 {
   private final CompletableFuture<Bridge8Extension3> field7 = new CompletableFuture<>();
   private final boolean field8;
   private Bridge8Extension3 @Nullable [] field9;
   @Nullable
   private Bridge_58 field10;
   @Nullable
   private AutoCloseableImpl field11;

   public JitAnimatedResource(JitAssetKey var1, boolean var2) {
      super(var1, new JitResource.Data6(true, true, true));
      this.field8 = var2;
   }

   @Override
   public void cleanUp() {
      if (this.value != null) {
         ThreadModuleDump63.method3().bridge$getTextureManager().bridge$deleteTexture(this.field3.method3());
         if (this.field11 != null) {
            this.field11.cleanUp();
         }
      }
   }

   @Override
   protected CompletableFuture<Bridge8Extension3> method8() {
      ThreadModuleDump37.method7(() -> ThreadModuleDump63.method3().bridge$getTextureManager().method3(this.field3.method3(), this));
      Alert4 var1 = Alert4.builder()
         .method1(ThreadModuleDump63.method3().bridge$getResourceManager())
         .method2(this.field3.method3())
         .method3(Bridge.getMinecraftVersion().method19() ? ColorChannelOrder.ABGR : ColorChannelOrder.ARGB)
         .method4(BridgeType2_5.FULL)
         .method5(this.field8)
         .method6();
      return this.field7
         .thenCombineAsync(
            CompletableFuture.supplyAsync(var1::call, ThreadModuleDump37.method6()),
            (var1x, var2) -> this.method2(var1x, var2.orElseThrow()),
            ThreadModuleDump37.method9(1)
         );
   }

   private Bridge8Extension3 method2(Bridge8Extension3 var1, Bridge6_9 var2) {
      Bridge7_3 var3 = Bridge.method55();
      Bridge3Extension2_2 var4 = var2.method4();
      ResourceLocationBridge var5 = null;
      if (var4 != null) {
         var5 = var4.method1();
      }

      if (var2.method3() != null) {
         Bridge3Extension var6 = var2.method3();
         this.field10 = new Bridge_58(var6);
         this.field10.setFrameCount(var2.method5());
         boolean var7 = true;
         if (var5 != null && !var4.method2()) {
            try {
               this.field11 = new AutoCloseableImpl(false, var5, null, null);
            } catch (Exception var10) {
               Slayer.method6("JitTexture", "Failed to load emissive component for {}", var5, var10);
            }

            var7 = false;
         }

         this.method3(var2, var6, var7 ? var5 : null);
      } else {
         var3.method1(var2, var1);
         if (var5 != null) {
            try {
               this.field11 = new AutoCloseableImpl(false, var5, null, null);
            } catch (Exception var9) {
               Slayer.method6("JitTexture", "Failed to load emissive component for {}", var5, var9);
            }
         }
      }

      int var11 = var2.getWidth() * var2.getHeight() * 4;
      if (var5 != null) {
         var11 *= 2;
      }

      this.method4(var11);
      return var1;
   }

   private void method3(Bridge6_9 var1, Bridge3Extension var2, @Nullable ResourceLocationBridge var3) {
      int var4 = var1.method5() / var2.bridge$getFrameHeight();
      int var5 = var1.getWidth();
      int var6 = var1.getHeight() / var4;
      int var7 = var5 * var6;
      this.field9 = new Bridge8Extension3[var4];
      Bridge8Extension3[] var8 = var3 == null ? null : new Bridge8Extension3[var4];
      int[] var9 = new int[var7];
      Bridge8Handler2 var10 = ThreadModuleDump63.method3().bridge$getTextureManager();

      for (int var11 = 0; var11 < var4; var11++) {
         ResourceLocationBridge var12 = ResourceLocationBridge.create(
            this.field3.method3().bridge$getDomain(), this.field3.method3().bridge$getPath() + "-" + var11
         );
         System.arraycopy(var1.getData(), var11 * var7, var9, 0, var9.length);
         Bridge6_9 var13 = new Bridge6_9(var1.isClamp(), var1.method2(), null, null, var5, var6, var1.method5(), var1.method6(), var9, var12.toString());
         Bridge8Extension34 var14 = var10.method3(var12, new Alert5Handler());
         Bridge7_3 var15 = Bridge.method55();
         var15.method1(var13, var14);
         this.field9[var11] = var14;
         if (var8 != null) {
            try {
               var12 = ResourceLocationBridge.create(var3.bridge$getDomain(), var3.bridge$getPath() + "-" + var11);
               var14 = var10.method3(var12, new Alert5Handler());
               var15.method1(var13, var14);
               var8[var11] = var14;
            } catch (Exception var18) {
               Slayer.method6("JitTexture", "Failed to load emissive frame texture for {} at frame {}", var3, var11);
               var8[var11] = null;
            }
         }
      }

      if (var3 != null) {
         try {
            Bridge8Extension34 var19 = var10.method3(var3, new Alert5Handler());
            this.field11 = new AutoCloseableImpl(true, var3, var19, var8);
         } catch (Exception var17) {
            Slayer.method6("JitTexture", "Failed to load animated emissive component for {}", var3);
         }
      }
   }

   @Override
   public void tick() {
      if (this.field10 != null) {
         this.field10.updateAnimation();
      }
   }

   @Override
   public boolean method4() {
      return this.value != null;
   }

   @Override
   public boolean method13() {
      return this.field8;
   }

   @Override
   public void method3(boolean var1) {
   }

   @Override
   public void method1(Bridge11_2 var1, Bridge8Extension34 var2) {
      this.field7.complete(var2);
   }

   @Override
   public void method22() {
      if (this.value != null) {
         this.value = null;
         this.field10 = null;
         this.field5.set(false);
         if (this.field9 != null) {
            for (Bridge8Extension3 var4 : this.field9) {
               var4.method1();
            }

            Arrays.fill(this.field9, null);
            this.field9 = null;
         }

         if (this.field11 != null) {
            try {
               this.field11.close();
            } catch (Exception var5) {
               throw new RuntimeException(var5);
            }

            this.field11 = null;
         }

         this.method4(0);
      }
   }

   @Nullable
   @Override
   public Bridge_58 getAnimation() {
      return this.field10;
   }

   @Override
   public void restartAnimation() {
      Bridge8Extension34 var1 = (Bridge8Extension34)this.value;
      if (this.field10 != null && this.field9 != null && var1 != null) {
         int var2 = this.field10.getFrame();
         Bridge8Extension3 var3 = this.field9[var2 % this.field9.length];
         var1.method2(var3);
         if (this.field11 != null && this.field11.method4() != null) {
            Bridge8Extension34 var4 = this.field11.method3();
            if (var4 == null) {
               return;
            }

            Bridge8Extension3[] var5 = this.field11.method4();
            var3 = var5[var2 % var5.length];
            if (var3 != null) {
               var4.method2(var3);
            }
         }
      }
   }

   @Generated
   public CompletableFuture<Bridge8Extension3> method14() {
      return this.field7;
   }

   @Generated
   public boolean method15() {
      return this.field8;
   }

   @Generated
   public Bridge8Extension3 @Nullable [] method16() {
      return this.field9;
   }

   @Nullable
   @Generated
   public AutoCloseableImpl method17() {
      return this.field11;
   }
}
