package com.moonsworth.lunar.client.render.texture;

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
import com.moonsworth.lunar.client.util.alert.Alert3;
import com.moonsworth.lunar.client.render.texture.Alert4;
import com.moonsworth.lunar.client.util.alert.Alert5;
import com.moonsworth.lunar.client.util.alert.Alert5Handler;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import lombok.Generated;
import org.jspecify.annotations.Nullable;
import com.moonsworth.lunar.client.util.Util2;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.Util_2;

public class Util2Handler implements Bridge3Extension_7, Util2<Bridge8Extension3>, Alert5 {
   public static final int field2 = 16777216;
   public static final int field3 = 67108864;
   private static final int field4 = 0;
   private boolean field5;
   private Future<Optional<Bridge6_9>> field6;
   public final ResourceLocationBridge field7;
   public Bridge8Extension3[] field8;
   private @Nullable Bridge_58 field9;
   private @Nullable AutoCloseableImpl field10;
   private int field11;
   private @Nullable BridgeType2_5 field12;
   protected boolean field13;
   protected BridgeType2_5 field14;
   private Bridge8Extension34 field15;
   private boolean field16;
   private @Nullable Consumer<Bridge8Extension3> field17;

   public Util2Handler(ResourceLocationBridge var1, BridgeType2_5 var2) {
      this.field7 = var1;
      this.field12 = null;
      this.field14 = var2;
   }

   @Override
   public boolean method4() {
      if (this.field11 > 0) {
         return false;
      } else {
         return this.field12 == null ? false : ThreadModuleDump63.method3().bridge$getTextureManager().bridge$getTextureMap().containsKey(this.field7);
      }
   }

   public boolean method13() {
      return this.field12 != null && this.field13;
   }

   @Override
   public void method2(boolean var1) {
      if (var1 && this.field9 != null) {
         this.field9.updateAnimation();
      }

      if (this.field11 > 0) {
         this.field11--;
      }

      if (this.field6 != null) {
         if (this.field15 == null) {
            this.field6.cancel(false);
            this.field6 = null;
         } else {
            if (this.field6.isDone()) {
               try {
                  this.field6
                     .get()
                     .ifPresent(
                        var1x -> {
                           if (var1x.getWidth() * var1x.getHeight() > 16777216) {
                              Slayer.method6(
                                 "Texture",
                                 "Texture too large: a size of %dx%d at 4 bytes per pixel exceeds the maximum texture size of %d bytes! Ask a dev if you need to add a larger texture. (failed to load %s)",
                                 new Object[]{var1x.getWidth(), var1x.getHeight(), 67108864, this.field7}
                              );
                              this.field5 = true;
                              this.field6 = null;
                           } else {
                              int var2 = var1x.getWidth() * var1x.getHeight() * 4;
                              Util_2.field1.method4(this, this.field12, this.field14, var2);
                              this.field11 = 0;
                              this.field9 = null;
                              this.field10 = null;

                              try {
                                 Bridge3Extension2_2 var3 = var1x.method4();
                                 ResourceLocationBridge var4 = null;
                                 if (var3 != null) {
                                    var4 = var3.method1();
                                 }

                                 boolean var5 = false;
                                 if (var1x.method3() != null) {
                                    var5 = var3 != null && var3.method2() && var4 != null;
                                    if (!var5 && var4 != null) {
                                       try {
                                          this.field10 = new AutoCloseableImpl(
                                             false, var4, ThreadModuleDump63.method3().bridge$getTextureManager().method3(var4, new Alert5Handler()), null
                                          );
                                       } catch (Exception var9) {
                                          Slayer.method6("AsyncTexture", "Failed to load emissive component for {}", new Object[]{var4});
                                       }
                                    }

                                    Bridge3Extension var6x = var1x.method3();
                                    this.field9 = new Bridge_58(var6x);
                                    this.field9.setFrameCount(var1x.method5());
                                    this.method4(var1x, var6x, var5 ? var4 : null);
                                    this.restartAnimation();
                                 } else {
                                    Bridge7_3 var12 = Bridge.method55();
                                    var12.method1(var1x, this.field15);
                                    if (var4 != null) {
                                       try {
                                          this.field10 = new AutoCloseableImpl(
                                             false,
                                             var4,
                                             ThreadModuleDump63.method3().bridge$getTextureManager().method3(var4, new Util2Handler(var4, BridgeType2_5.FULL)),
                                             null
                                          );
                                       } catch (Exception var8) {
                                          Slayer.method6("AsyncTexture", "Failed to load emissive component for {}", new Object[]{var4});
                                       }
                                    }
                                 }
                              } catch (IllegalStateException var10) {
                                 throw new IllegalStateException(String.format("Failed to upload %s\n", this.field7), var10);
                              }

                              this.field12 = this.field14;
                              this.method5();
                           }
                        }
                     );
               } catch (InterruptedException | ExecutionException var6) {
                  Slayer.method6("Texture", "Failed to load texture %s asynchronously", new Object[]{this.field7.toString(), var6});
                  this.field5 = true;
               } finally {
                  this.field6 = null;
               }
            }
         }
      }
   }

   private void method4(Bridge6_9 var1, Bridge3Extension var2, ResourceLocationBridge var3) {
      int var4 = var1.method5() / var2.bridge$getFrameHeight();
      int var5 = var1.getWidth();
      int var6 = var1.getHeight() / var4;
      int var7 = var5 * var6;
      this.field8 = new Bridge8Extension3[var4];
      Bridge8Extension3[] var8 = var3 != null ? new Bridge8Extension3[var4] : null;
      int[] var9 = new int[var7];
      Bridge8Handler2 var10 = ThreadModuleDump63.method3().bridge$getTextureManager();
      Bridge7_3 var11 = Bridge.method55();

      for (int var12 = 0; var12 < var4; var12++) {
         ResourceLocationBridge var13 = ResourceLocationBridge.create(this.field7.bridge$getDomain(), this.field7.bridge$getPath() + "-" + var12);
         Bridge8Extension34 var14 = var10.method3(var13, new Alert5Handler());
         System.arraycopy(var1.getData(), var12 * var7, var9, 0, var9.length);
         Bridge6_9 var15 = new Bridge6_9(var1.isClamp(), var1.method2(), null, null, var5, var6, var1.method5(), var1.method6(), var9, var13.toString());
         var11.method1(var15, var14);
         this.field8[var12] = var14;
         if (var8 != null) {
            try {
               var13 = ResourceLocationBridge.create(var3.bridge$getDomain(), var3.bridge$getPath() + "-" + var12);
               var14 = var10.method3(var13, new Alert5Handler());
               var11.method1(var15, var14);
               var8[var12] = var14;
            } catch (Exception var18) {
               Slayer.method6("AsyncTexture", "Failed to load emissive frame texture for {} at frame {}", new Object[]{var3, var12});
               var8[var12] = null;
            }
         }
      }

      if (var8 != null) {
         try {
            Bridge8Extension34 var19 = var10.method3(var3, new Alert5Handler());
            this.field10 = new AutoCloseableImpl(true, var3, var19, var8);
         } catch (Exception var17) {
            Slayer.method6("AsyncTexture", "Failed to load animated emissive component for {}", new Object[]{var3});
         }
      }
   }

   protected void method5() {
   }

   public void method3(boolean var1) {
      if (var1) {
         Util_2.field1.method5(this);
      }

      BridgeType2_5 var2 = this.field14 == BridgeType2_5.LOW && !var1 ? BridgeType2_5.LOW : BridgeType2_5.FULL;
      this.getAnimation(ThreadModuleDump63.method3().bridge$getResourceManager(), var2);
      if (this.field10 != null) {
         Bridge8Extension34 var3 = this.field10.method3();
         if (var3 == null) {
            return;
         }

         if (var3.method3() instanceof Bridge3Extension_7 var4) {
            var4.method3(var1);
         }
      }
   }

   public void method1(Bridge11_2 var1, Bridge8Extension34 var2) {
      this.field15 = var2;
      this.field16 = false;
      if (this.field12 == BridgeType2_5.FULL && this.field17 != null) {
         this.field17.accept(var2);
      }

      Util_2.field1.method4(this, null, null, 0);
   }

   @Override
   public void getAnimation(Bridge11_2 var1, BridgeType2_5 var2) {
      if (var2.equals(this.field12)) {
         if (!var2.equals(this.field14)) {
            this.method9();
         }
      } else if (!this.field5) {
         if (this.field6 != null) {
            if (var2.equals(this.field14)) {
               return;
            }

            this.field6.cancel(false);
            this.field6 = null;
         }

         this.field14 = var2;
         this.method10(var1);
      }
   }

   private void method9() {
      if (this.field12 == null) {
         throw new IllegalStateException("Cannot abort changing the quality of a texture that isn't uploaded yet.");
      }

      if (this.field6 != null) {
         this.field6.cancel(false);
         this.field6 = null;
      }

      this.field14 = this.field12;
   }

   private void method10(Bridge11_2 var1) {
      ColorChannelOrder var2;
      if (Bridge.getMinecraftVersion().method19()) {
         var2 = ColorChannelOrder.ABGR;
      } else {
         var2 = ColorChannelOrder.ARGB;
      }

      if (this.field15 == null) {
         if (!this.field16 && ThreadModuleDump63.method3().bridge$areResourcesLoaded()) {
            throw new IllegalStateException(
               "Attempted to schedule a texture load task for " + this.field7 + ", but the underlying OpenGL texture object hasn't been created yet."
            );
         }
      } else {
         this.field6 = field1.submit(this.method11(var1, var2));
      }
   }

   protected Callable<Optional<Bridge6_9>> method11(Bridge11_2 var1, ColorChannelOrder var2) {
      return new Alert4(var1, this.field7, var2, this.field14, this.field13);
   }

   @Override
   public @Nullable Bridge_58 getAnimation() {
      return this.field9;
   }

   public void method22() {
      this.field15 = null;
      this.field16 = true;
      Util_2.field1.method6(this);
      if (this.field8 != null) {
         for (Bridge8Extension3 var4 : this.field8) {
            var4.method1();
         }

         Arrays.fill(this.field8, null);
         this.field8 = null;
      }

      if (this.field10 != null) {
         try {
            this.field10.cleanUp();
            this.field10.close();
         } catch (Exception var5) {
            throw new RuntimeException(var5);
         }

         this.field10 = null;
      }

      if (this.field6 != null) {
         this.field6.cancel(false);
         this.field6 = null;
      }

      this.field12 = null;
      this.field9 = null;
   }

   public Bridge8Extension3 method14() {
      return this.field15;
   }

   @Override
   public Optional<Bridge8Extension3> method4(BridgeType2_5 var1) {
      if (this.field12 == var1) {
         return Optional.ofNullable(this.field15);
      } else {
         return Alert3.loadTexture(this.field7, var1 == BridgeType2_5.FULL) ? Optional.ofNullable(this.field15) : Optional.empty();
      }
   }

   @Override
   public void method5(Consumer<Bridge8Extension3> var1) {
      if (this.field12 == BridgeType2_5.FULL) {
         var1.accept(this.field15);
      }
   }

   @Override
   public void restartAnimation() {
      if (this.field9 != null && this.field8 != null) {
         int var1 = this.field9.getFrame();
         Bridge8Extension3 var2 = this.field8[var1 % this.field8.length];
         this.field15.method2(var2);
         if (this.field10 != null && this.field10.method3() != null) {
            Bridge8Extension3[] var3 = this.field10.method4();
            if (var3 != null) {
               var2 = var3[var1 % var3.length];
               if (var2 != null) {
                  this.field10.method3().method2(var2);
               }
            }
         }
      }
   }

   @Override
   public void method6(Consumer<Bridge8Extension3> var1) {
      if (this.field12 == BridgeType2_5.FULL) {
         var1.accept(this.field15);
      }

      this.field17 = var1;
   }

   @Generated
   public @Nullable AutoCloseableImpl method19() {
      return this.field10;
   }

   @Generated
   public @Nullable BridgeType2_5 method20() {
      return this.field12;
   }

   @Generated
   public void method21(boolean var1) {
      this.field13 = var1;
   }

   @Generated
   @Override
   public BridgeType2_5 method8() {
      return this.field14;
   }

   @Generated
   public boolean isDeleted() {
      return this.field16;
   }
}
