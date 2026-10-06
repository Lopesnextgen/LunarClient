package com.moonsworth.lunar.client.framework.feature.minimap;

import com.google.common.collect.ImmutableMap;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.BridgeExtension2_5;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.config.option.NumberRule;
import com.moonsworth.lunar.client.mod.render.minimap.Minimap.Type2;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump67;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Nullable;

public class Minimap2Impl2 extends Minimap2_2<BridgeExtension2_5> {
   public static final Map<String, String> field5 = ImmutableMap.builder()
      .put("entityhorse", "horse")
      .put("pigzombie", "zombie_pigman")
      .put("cavespider", "cave_spider")
      .put("lavaslime", "magma_cube")
      .put("enderdragon", "ender_dragon")
      .put("witherboss", "wither")
      .put("mushroomcow", "mooshroom")
      .put("snowman", "snow_golem")
      .put("ozelot", "ocelot")
      .put("villagergolem", "iron_golem")
      .build();

   public Minimap2Impl2(com.moonsworth.lunar.client.mod.render.minimap.Minimap var1, double var2, double var4, float var6, float var7, BridgeExtension2_5 var8) {
      super(var1, var2, var4, var6, var7, var8);
   }

   @Override
   public void method1(MixinHelper_4 var1, Bridge5Extension_5 var2, float var3, float var4, float var5) {
      var1.push();
      var1.method38(var4, var5, 0.0F);
      var1.method42(-var3);
      this.method2(this.method7(), var2, var1, (BridgeExtension)this.method10(), var3);
      var1.pop();
   }

   private void method2(com.moonsworth.lunar.client.mod.render.minimap.Minimap var1, Bridge5Extension_5 var2, MixinHelper_4 var3, BridgeExtension var4, float var5) {
      Type2 var6 = (Type2)var1.method24().get();
      boolean var7 = var4 instanceof Bridge6_10;
      if (!var7 || ThreadModuleDump63.method3().bridge$getCurrentServerData() == null) {
         if (var6.needsRotation) {
            var3.push();
            var3.method42(var5);
            var3.method42((float)var4.bridge$getRotationYaw());
         }

         float var8 = (Float)var1.method26().get();
         int var9 = var4.bridge$getSpawnEggColor(0);
         if (var9 == -1) {
            var9 = 0;
         }

         var9 = var9 & 16777215 | (int)((Float)var1.method25().get() * 255.0F) << 24;
         int var10 = var4.bridge$getSpawnEggColor(1);
         if (var10 == -1) {
            var10 = 0;
         }

         var10 = var10 & 16777215 | (int)((Float)var1.method25().get() * 255.0F) << 24;
         if (var6 == Type2.CIRCLE) {
            if ((Boolean)var1.method27().get()) {
               LcuiScreen.method78(var3, 0.0, 0.0, var8 + 0.5F, var10);
            }

            LcuiScreen.method78(var3, 0.0, 0.0, var8, var9);
         } else if (var6 == Type2.TRIANGLE) {
            float var11 = var8 / 5.0F * 8.0F;
            if ((Boolean)var1.method27().get()) {
               var1.method16(var3, var8 + 0.5F, var11 + 0.5F, var10);
            }

            var1.method16(var3, var8, var11, var9);
         } else if (var6 == Type2.HEAD) {
            String var18 = ((BridgeExtension2_5)this.method10()).bridge$getType();
            if (var18 != null || var7) {
               if (!var7 && Bridge.getMinecraftVersion().method21() && field5.containsKey(var18)) {
                  var18 = field5.get(var18);
               }

               ResourceLocationBridge var12;
               if (var7) {
                  var12 = ((Bridge6_10)var4).bridge$getServerSkinTexture();
               } else {
                  var12 = ResourceLocationBridge.create("lunar", "mobs/" + var18 + ".png");
               }

               if (var12 != null) {
                  int var13 = 16777215
                     | (int)(ThreadModuleDump67.method3(1.0F - (float)Math.abs(var4.bridge$getPosY() - var2.bridge$getPosY()) / 20.0F, 0.0F, 1.0F) * 255.0F)
                        << 24;
                  NumberRule var14 = (NumberRule)var1.method26().RHRHIOOCICIORIOCIHHCIIRCRHHOII(com.moonsworth.lunar.client.config.option.OptionTraits.field7);
                  float var15 = (Float)var1.method26().get() / var14.getMax().floatValue() * 2.0F;
                  var3.push();
                  var3.scale(var15, var15, 1.0F);
                  var3.method38(-4.0F, -4.0F, 0.0F);
                  if (var7) {
                     LcuiScreen.method50(var3, var12, 0.0F, 0.0F, var13, true);
                  } else {
                     LcuiScreen.method35(var3, var12, -4.0F, -4.0F, 8.0F, 8.0F, 0.0F, 0.0F, 1.0F, 1.0F, var13);
                  }

                  var3.pop();
               }
            }
         }

         if (var6.needsRotation) {
            var3.pop();
         }
      }
   }

   @Nullable
   @Override
   public LinkedHashMap<String, Runnable> method6() {
      return null;
   }
}
