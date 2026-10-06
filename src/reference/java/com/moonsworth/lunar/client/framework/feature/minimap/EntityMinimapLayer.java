package com.moonsworth.lunar.client.framework.feature.minimap;

import com.google.common.collect.ImmutableMap;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityLivingBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.config.option.NumberRule;
import com.moonsworth.lunar.client.mod.render.minimap.MinimapMod.EntityMarkerType;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.math.MathUtils;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Nullable;

public class EntityMinimapLayer extends MinimapLayer<EntityLivingBridge> {
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

   public EntityMinimapLayer(com.moonsworth.lunar.client.mod.render.minimap.MinimapMod minimap1, double value2, double value4, float value6, float value7, EntityLivingBridge bridgeextension2_58) {
      super(minimap1, value2, value4, value6, value7, bridgeextension2_58);
   }

   @Override
   public void method1(MixinHelper_4 mixinhelper_41, Bridge5Extension_5 bridge5extension_52, float value3, float value4, float value5) {
      mixinhelper_41.push();
      mixinhelper_41.method38(value4, value5, 0.0F);
      mixinhelper_41.method42(-value3);
      this.method2(this.method7(), bridge5extension_52, mixinhelper_41, (BridgeExtension)this.method10(), value3);
      mixinhelper_41.pop();
   }

   private void method2(com.moonsworth.lunar.client.mod.render.minimap.MinimapMod minimap1, Bridge5Extension_5 bridge5extension_52, MixinHelper_4 mixinhelper_43, BridgeExtension bridgeextension4, float value5) {
      EntityMarkerType type26 = (EntityMarkerType)minimap1.method24().get();
      boolean flag7 = bridgeextension4 instanceof Bridge6_10;
      if (!flag7 || Ref.method3().bridge$getCurrentServerData() == null) {
         if (type26.needsRotation) {
            mixinhelper_43.push();
            mixinhelper_43.method42(value5);
            mixinhelper_43.method42((float)bridgeextension4.bridge$getRotationYaw());
         }

         float value8 = (Float)minimap1.method26().get();
         int number9 = bridgeextension4.bridge$getSpawnEggColor(0);
         if (number9 == -1) {
            number9 = 0;
         }

         number9 = number9 & 16777215 | (int)((Float)minimap1.method25().get() * 255.0F) << 24;
         int number10 = bridgeextension4.bridge$getSpawnEggColor(1);
         if (number10 == -1) {
            number10 = 0;
         }

         number10 = number10 & 16777215 | (int)((Float)minimap1.method25().get() * 255.0F) << 24;
         if (type26 == EntityMarkerType.CIRCLE) {
            if ((Boolean)minimap1.method27().get()) {
               LcuiScreen.method78(mixinhelper_43, 0.0, 0.0, value8 + 0.5F, number10);
            }

            LcuiScreen.method78(mixinhelper_43, 0.0, 0.0, value8, number9);
         } else if (type26 == EntityMarkerType.TRIANGLE) {
            float value11 = value8 / 5.0F * 8.0F;
            if ((Boolean)minimap1.method27().get()) {
               minimap1.method16(mixinhelper_43, value8 + 0.5F, value11 + 0.5F, number10);
            }

            minimap1.method16(mixinhelper_43, value8, value11, number9);
         } else if (type26 == EntityMarkerType.HEAD) {
            String text18 = ((EntityLivingBridge)this.method10()).bridge$getType();
            if (text18 != null || flag7) {
               if (!flag7 && Bridge.getMinecraftVersion().method21() && field5.containsKey(text18)) {
                  text18 = field5.get(text18);
               }

               ResourceLocationBridge horsestats1412;
               if (flag7) {
                  horsestats1412 = ((Bridge6_10)bridgeextension4).bridge$getServerSkinTexture();
               } else {
                  horsestats1412 = ResourceLocationBridge.create("lunar", "mobs/" + text18 + ".png");
               }

               if (horsestats1412 != null) {
                  int number13 = 16777215
                     | (int)(MathUtils.method3(1.0F - (float)Math.abs(bridgeextension4.bridge$getPosY() - bridge5extension_52.bridge$getPosY()) / 20.0F, 0.0F, 1.0F) * 255.0F)
                        << 24;
                  NumberRule nameplate14 = (NumberRule)minimap1.method26().RHRHIOOCICIORIOCIHHCIIRCRHHOII(com.moonsworth.lunar.client.config.option.OptionTraits.field7);
                  float value15 = (Float)minimap1.method26().get() / nameplate14.getMax().floatValue() * 2.0F;
                  mixinhelper_43.push();
                  mixinhelper_43.scale(value15, value15, 1.0F);
                  mixinhelper_43.method38(-4.0F, -4.0F, 0.0F);
                  if (flag7) {
                     LcuiScreen.method50(mixinhelper_43, horsestats1412, 0.0F, 0.0F, number13, true);
                  } else {
                     LcuiScreen.method35(mixinhelper_43, horsestats1412, -4.0F, -4.0F, 8.0F, 8.0F, 0.0F, 0.0F, 1.0F, 1.0F, number13);
                  }

                  mixinhelper_43.pop();
               }
            }
         }

         if (type26.needsRotation) {
            mixinhelper_43.pop();
         }
      }
   }

   @Nullable
   @Override
   public LinkedHashMap<String, Runnable> method6() {
      return null;
   }
}
