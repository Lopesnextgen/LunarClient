package com.moonsworth.lunar.client.mod.render.itemphysics;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.EntityItemExtensionBridge;
import com.moonsworth.lunar.bridge.EntityItemBridge;
import com.moonsworth.lunar.bridge.PoseStackBridge;
import com.moonsworth.lunar.client.render.turbo.TurboEngineManager;
import com.moonsworth.lunar.client.render.turbo.BatchEntityType;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderDroppedItem;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderItemRotation;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderItemClump;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.config.Config;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.lang.ref.WeakReference;
import java.util.Optional;
import java.util.Random;

public class ItemPhysics extends AbstractFeature {
   private final FloatOption field8 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "rotationSpeed"
            )
            .method4(0.5F))
         .method8(0.1F, 4.0F))
      .method31();
   private final Int2ObjectMap<ItemPhysics.Data> field9 = new Int2ObjectOpenHashMap();

   public ItemPhysics() {
      super(false);
      this.handle(EventTick.class, this::method1);
      this.handle(EventRenderDroppedItem.class, this::method3);
      this.handle(EventRenderItemRotation.class, this::method4);
      this.handle(EventRenderItemClump.class, this::method5);
   }

   public String getId() {
      return "ITEM_PHYSICS";
   }

   private void method1(EventTick highlightimpl21) {
      if (this.mc.bridge$getWorld() == null) {
         this.field9.clear();
      } else if (!this.mc.bridge$isGamePaused()) {
         this.field9.int2ObjectEntrySet().removeIf(arg1x -> {
            ItemPhysics.Data data2 = (ItemPhysics.Data)arg1x.getValue();
            if (data2 == null) {
               return true;
            }

            EntityItemBridge bridgeextension523 = data2.field1.get();
            if (bridgeextension523 == null) {
               return true;
            }

            boolean flag4 = !Double.isNaN(bridgeextension523.bridge$getPosX()) && !Double.isNaN(bridgeextension523.bridge$getPosY()) && !Double.isNaN(bridgeextension523.bridge$getPosZ());
            if (!flag4) {
               return false;
            }

            if (bridgeextension523.bridge$isOnGround()) {
               if (data2.field2 != 0.0 && data2.field2 != 180.0) {
                  if (data2.field2 > 90.0 && data2.field2 < 270.0) {
                     data2.field2 = 180.0;
                  } else {
                     data2.field2 = 0.0;
                  }
               }

               data2.field3 = data2.field2;
               data2.field4 = 0.0;
            } else {
               data2.field3 = data2.field2;
               double value5 = bridgeextension523.bridge$getMotionX();
               double value7 = bridgeextension523.bridge$getMotionY();
               double value9 = bridgeextension523.bridge$getMotionZ();
               double value11 = value5 * value5 + value7 * value7 + value9 * value9;
               double value13 = Math.sqrt(value11);
               double value15 = Math.min(value13 * 100.0, 30.0);
               value15 *= 0.2;
               value15 += 0.8 * data2.field4;
               data2.field2 = data2.field2 + value15 * ((Float)this.field8.get()).floatValue();
               data2.field4 = value15;
            }

            return false;
         });
      }
   }

   public void method3(boolean flag1) {
      if (!flag1 && this.field9 != null) {
         this.field9.clear();
      }

      if (Ref.MC_VERSION >= 8) {
         TurboEngineManager fogiterator_32 = Ref.method4().method89();
         if (fogiterator_32 != null && fogiterator_32.method3()) {
            fogiterator_32.method10(BatchEntityType.ENTITIES);
         }
      }
   }

   private void method3(EventRenderDroppedItem highlightimpl1) {
      EntityItemExtensionBridge bridgeextension3_22 = highlightimpl1.method1();
      Random random3 = new Random(bridgeextension3_22.bridge$getEntityId());
      float value4 = 0.035F;
      if (Bridge.getMinecraftVersion() == Config.field1) {
         value4 = -0.08F;
      }

      value4 += random3.nextFloat() * 0.01F;
      if (Ref.method8() != null
         && Ref.method8().bridge$getBlockAt(bridgeextension3_22.bridge$getPosX(), bridgeextension3_22.bridge$getPosY(), bridgeextension3_22.bridge$getPosZ()) == Bridge.method34().method5()) {
         value4 += 0.125F;
      }

      highlightimpl1.method3(value4);
   }

   private void method4(EventRenderItemRotation highlightimpl161) {
      highlightimpl161.setCancelled(true);
      PoseStackBridge bridge_82 = highlightimpl161.method1();
      EntityItemExtensionBridge bridgeextension3_23 = highlightimpl161.method2();
      float value4 = highlightimpl161.method3();
      bridge_82.method5(90.0F, 0.0F, 0.0F);
      bridge_82.method5(0.0F, 0.0F, (float)bridgeextension3_23.bridge$getRotationYaw());
      int index5 = bridgeextension3_23.bridge$getEntityId();
      ItemPhysics.Data data6 = (ItemPhysics.Data)this.field9.get(index5);
      if (data6 == null) {
         Optional optional7 = Ref.method8().bridge$getEntityById(index5);
         if (optional7.isPresent() && optional7.get() instanceof EntityItemBridge bridgeextension528) {
            data6 = new ItemPhysics.Data(bridgeextension528);
            this.field9.put(index5, data6);
         }
      }

      if (data6 != null) {
         double value10 = lerp(data6.field3, data6.field2, value4);
         bridge_82.method5((float)value10, 0.0F, 0.0F);
      }
   }

   private static double lerp(double value0, double value2, double value4) {
      return value0 + (value2 - value0) * value4;
   }

   private void method5(EventRenderItemClump highlightimpl91) {
      highlightimpl91.method5(highlightimpl91.method3() * 0.5F);
      if (Ref.MC_VERSION > 1) {
         highlightimpl91.method4(highlightimpl91.method1().bridge$renderSeed());
      }
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field8});
   }

   private static class Data {
      private final WeakReference<EntityItemBridge> field1;
      private double field2;
      private double field3;
      private double field4;

      public Data(EntityItemBridge bridgeextension521) {
         this.field1 = new WeakReference<>(bridgeextension521);
      }
   }
}
