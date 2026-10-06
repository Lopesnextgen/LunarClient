package com.moonsworth.lunar.client.cosmetics;

import com.moonsworth.lunar.bridge.Bridge11_2;
import com.moonsworth.lunar.bridge.IResourceBridge;
import com.moonsworth.lunar.bridge.Bridge3Extension;
import com.moonsworth.lunar.bridge.Bridge3_4;
import com.moonsworth.lunar.bridge.Bridge6_9;
import com.moonsworth.lunar.bridge.Bridge8Extension3;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.BridgeExtension2_2;
import com.moonsworth.lunar.bridge.BridgeType2_5;
import com.moonsworth.lunar.bridge.ColorChannelOrder;
import com.moonsworth.lunar.bridge.Bridge_58;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager;
import com.moonsworth.lunar.client.cosmetics.OwnedCosmetic;
import com.moonsworth.lunar.client.cosmetics.CosmeticCategoryType;
import com.moonsworth.lunar.client.util.ThreadModuleDump59;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.render.texture.Util2Handler;
import com.moonsworth.lunar.client.render.texture.Alert4;
import com.moonsworth.lunar.client.util.alert.Alert5;
import com.moonsworth.lunar.client.util.alert.Alert6;
import it.unimi.dsi.fastutil.Pair;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector2i;

public class ElytraTextureLoader extends Util2Handler {
   private static final ConcurrentHashMap<Long, ElytraTextureLoader> field18 = new ConcurrentHashMap<>();
   private static final ResourceLocationBridge field19 = ResourceLocationBridge.create(
      "minecraft", ThreadModuleDump63.MC_VERSION >= 26 ? "textures/entity/equipment/wings/elytra.png" : "textures/entity/elytra.png"
   );
   public final ResourceLocationBridge field20;
   private final ResourceLocationBridge field21;
   private BufferedImage field22;

   public ElytraTextureLoader(ResourceLocationBridge var1, ResourceLocationBridge var2, ResourceLocationBridge var3, BridgeType2_5 var4) {
      super(var1, var4);
      this.field20 = var2;
      this.field21 = var3;
   }

   protected Callable<Optional<Bridge6_9>> method11(Bridge11_2 var1, ColorChannelOrder var2) {
      return new ElytraTextureLoader.Data(var1, var2, this.field14);
   }

   protected void method5() {
      this.field22.flush();
      this.field22 = null;
   }

   public static ResourceLocationBridge method3(ResourceLocationBridge var0, BridgeExtension2_2 var1, @Nullable Consumer<Bridge_58> var2) {
      if (!(Boolean)ThreadModuleDump63.method4().method41().method6().method75().get()) {
         return var0;
      }

      if (var1 instanceof EntityPlayerBridge var3) {
         OwnedCosmetic var4 = CosmeticManager.method41(var3.bridge$getWornCosmetics(), CosmeticCategoryType.CLOAK, 0);
         if (var4 != null && var4.method10().canShowCosmetic()) {
            ResourceLocationBridge var5 = var4.method5(var3);
            Optional var6 = ThreadModuleDump63.method4().method53().method37(var5, var3.bridge$getUniqueID());
            if (var6.isEmpty()) {
               return var0;
            }

            ResourceLocationBridge var7 = CosmeticManager.method38(var5, var3.bridge$getUniqueID());
            Alert6 var8 = ThreadModuleDump63.method4().method53().method73().get(var7);
            if (var8 != null) {
               return var0;
            }

            ElytraTextureLoader var9;
            if (field18.containsKey(var4.method9())) {
               var9 = field18.get(var4.method9());
            } else {
               ResourceLocationBridge var10 = ResourceLocationBridge.create("lunar", "elytra_" + var5.bridge$getPath());
               ElytraTextureLoader var11 = new ElytraTextureLoader(var10, var5, field19, BridgeType2_5.FULL);
               ThreadModuleDump63.method3().bridge$getTextureManager().method3(var10, var11);
               field18.put(var4.method9(), var11);
               var9 = var11;
            }

            if (var9 != null && var9.method4()) {
               if (var2 != null) {
                  Bridge8Extension3 var13 = ThreadModuleDump63.method3().bridge$getTextureManager().bridge$getTexture(var5);
                  if (var13.method2() instanceof Alert5 var14) {
                     Bridge_58 var15 = var14.method7();
                     if (var15 != null) {
                        var2.accept(var15);
                     }
                  }
               }

               var9.restartAnimation();
               return var9.field7;
            }

            if (var9 != null) {
               var9.method3(true);
            }

            return var0;
         }
      }

      return var0;
   }

   private class Data extends Alert4 {
      private static final List<Pair<Vector2i, Vector2i>> field6 = List.of(
         Pair.of(new Vector2i(0, 10), new Vector2i(0, 16)),
         Pair.of(new Vector2i(1, 12), new Vector2i(1, 16)),
         Pair.of(new Vector2i(2, 14), new Vector2i(2, 16)),
         Pair.of(new Vector2i(1, 16), new Vector2i(4, 16)),
         Pair.of(new Vector2i(1, 0), new Vector2i(7, 0)),
         Pair.of(new Vector2i(7, 1), new Vector2i(10, 1)),
         Pair.of(new Vector2i(8, 2), new Vector2i(10, 2)),
         Pair.of(new Vector2i(9, 3), new Vector2i(10, 5)),
         Pair.of(new Vector2i(10, 1), new Vector2i(10, 9))
      );
      private Bridge3Extension field7 = null;
      private int field8 = -1;

      public Data(Bridge11_2 var2, ColorChannelOrder var3, BridgeType2_5 var4) {
         super(var2, ElytraTextureLoader.this.field21, var3, var4, ElytraTextureLoader.this.IIOIRRHOICRIOHOCIHIHOOCOCICOCR);
      }

      protected BufferedImage method3(BufferedImage var1, BridgeType2_5 var2) {
         IResourceBridge var3 = this.OHOIIIOOOOCIRHRHCIRRCRHRCHRICH.bridge$getResource(ElytraTextureLoader.this.field20);
         if (var3 == null) {
            throw new RuntimeException("Couldn't find elytra cloak resource: " + ElytraTextureLoader.this.field20);
         }

         try {
            InputStream var4 = var3.bridge$getInputStream();
            BufferedImage var5 = ThreadModuleDump59.method1(var4, null);
            var5 = this.getAnimation(var5);
            this.field8 = var5.getHeight();
            Bridge8Extension3 var6 = ThreadModuleDump63.method3().bridge$getTextureManager().bridge$getTexture(ElytraTextureLoader.this.field20);
            float var7 = (float)var5.getWidth() / var5.getHeight();
            boolean var8 = var7 < 1.9 || var7 > 2.1;
            int var9 = var5.getWidth() / (var8 ? 22 : 64);
            Bridge_58 var10 = null;
            Bridge3_4 var11 = var6.method2();
            if (var11 != null) {
               if (var11 instanceof Alert5 var12) {
                  var10 = var12.method7();
                  if (var10 == null && var3.bridge$hasMetadata()) {
                     try {
                        Bridge3Extension var13 = (Bridge3Extension)var3.bridge$getMetadata("animation");
                        var10 = new Bridge_58(var13);
                        var10.setFrameCount(this.field8);
                     } catch (RuntimeException var21) {
                        var21.printStackTrace();
                        Slayer.method6(
                           "Texture", "Failed reading metadata of %s: %s", new Object[]{ElytraTextureLoader.this.OHIIOOIORCHHHOOOORIOCOHRHROCOH, var21.getMessage()}
                        );
                     }
                  }
               }

               if (var10 != null) {
                  this.field7 = var10.method1();
                  var9 = var5.getWidth() / 22;
                  this.RIRCOCRCIOCOHICCOCIICOORRRCCHH = BridgeType2_5.LOW;
               }
            }

            int var24 = var10 == null ? 1 : var10.getFrameCount();
            BufferedImage var25 = new BufferedImage(64 * var9, 32 * var9 * var24, 2);
            int var14 = 32 * var9;
            int var15 = var5.getHeight() / var24;

            for (int var16 = 0; var16 < var24; var16++) {
               int var17 = var16 * var14;
               int var18 = var16 * var15;

               for (Pair var20 : field6) {
                  this.method6((Vector2i)var20.first(), (Vector2i)var20.second(), var5, var9, var18);
               }

               this.method5(new Vector2i(0, 1), new Vector2i(1, 9), new Vector2i(35, 1), var5, var25, var9, var18, var17);
               this.method5(new Vector2i(1, 1), new Vector2i(11, 17), new Vector2i(35, 1), var5, var25, var9, var18, var17);
               this.method5(new Vector2i(8, 0), new Vector2i(11, 1), new Vector2i(23, 0), var5, var25, var9, var18, var17);
               this.method5(new Vector2i(12, 10), new Vector2i(13, 16), new Vector2i(10, 1), var5, var25, var9, var18, var17);
            }

            ElytraTextureLoader.this.field22 = var25;
            return ElytraTextureLoader.this.field22;
         } catch (IOException var22) {
            throw new RuntimeException(var22);
         }
      }

      protected int method2(BufferedImage var1) {
         return 32;
      }

      protected Bridge3Extension method4(Bridge3Extension var1) {
         return this.field7 != null ? this.field7 : var1;
      }

      protected int method5(BufferedImage var1, int var2) {
         return this.field7 != null ? this.field8 : super.method5(var1, var2);
      }

      private void method5(Vector2i var1, Vector2i var2, Vector2i var3, BufferedImage var4, BufferedImage var5, int var6, int var7, int var8) {
         for (int var9 = var1.x * var6; var9 < var2.x * var6; var9++) {
            for (int var10 = var1.y * var6; var10 < var2.y * var6; var10++) {
               var5.setRGB(var9 + var3.x * var6, var10 + var3.y * var6 + var8, var4.getRGB(var9, var10 + var7));
            }
         }
      }

      private void method6(Vector2i var1, Vector2i var2, BufferedImage var3, int var4, int var5) {
         for (int var6 = var1.x * var4; var6 < (var2.x + 1) * var4; var6++) {
            for (int var7 = var1.y * var4; var7 < (var2.y + 1) * var4; var7++) {
               var3.setRGB(var6, var7 + var5, 0);
            }
         }
      }

      private BufferedImage method7(BufferedImage var1) {
         BufferedImage var2 = new BufferedImage(var1.getWidth(), var1.getHeight(), 2);
         var2.getGraphics().drawImage(var1, 0, 0, null);
         return var2;
      }
   }
}
