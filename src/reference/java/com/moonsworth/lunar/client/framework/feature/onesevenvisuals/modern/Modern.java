package com.moonsworth.lunar.client.framework.feature.onesevenvisuals.modern;

import com.google.common.collect.ImmutableMap.Builder;
import com.moonsworth.lunar.bridge.ItemBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderGlintTransform;
import java.util.Map;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

class Modern {
   private static final Map<Modern.Type, Modern.Data> field1 = new Builder()
      .put(
         Modern.Type.ROD,
         new Modern.Data(
            new Modern.ItemTransform(new Vector3f(0.01F, 0.13F, -0.19F), new Vector3f(1.6F, 4.57F, 1.63F), 0.9F),
            new Modern.ItemTransform(new Vector3f(0.0F, 0.0F, 0.11F), new Vector3f(-13.04F, 0.0F, 0.0F), 1.05F)
         )
      )
      .put(
         Modern.Type.CARPET,
         new Modern.Data(new Modern.ItemTransform(new Vector3f(0.0F, -0.26F, 0.0F), null, null), new Modern.ItemTransform(new Vector3f(0.0F, 0.1F, 0.0F), null, null))
      )
      .put(Modern.Type.TOOL, new Modern.Data(null, new Modern.ItemTransform(new Vector3f(-0.02F, 0.01F, 0.05F), new Vector3f(19.24F, -4.24F, 2.5F), 1.05F)))
      .put(Modern.Type.BOW, new Modern.Data(null, new Modern.ItemTransform(new Vector3f(0.09F, -0.05F, 0.0F), new Vector3f(4.57F, 0.0F, 13.37F), 1.05F)))
      .put(Modern.Type.DOOR, new Modern.Data(null, new Modern.ItemTransform(new Vector3f(0.04F, 0.01F, 0.01F), new Vector3f(10.43F, -7.17F, 13.37F), null)))
      .put(Modern.Type.TRAPDOOR, new Modern.Data(null, new Modern.ItemTransform(new Vector3f(0.0F, -0.04F, 0.01F), null, null)))
      .put(Modern.Type.ANVIL_FENCE, new Modern.Data(null, new Modern.ItemTransform(new Vector3f(0.16F, 0.0F, -0.11F), new Vector3f(-13.04F, -18.91F, -86.41F), null)))
      .put(Modern.Type.BED, new Modern.Data(null, new Modern.ItemTransform(new Vector3f(0.03F, -0.04F, -0.09F), new Vector3f(60.33F, -39.46F, -10.11F), null)))
      .put(Modern.Type.BLAZE_ROD, new Modern.Data(null, new Modern.ItemTransform(new Vector3f(0.16F, -0.07F, -0.04F), new Vector3f(30.98F, -104.02F, 22.17F), 0.7F)))
      .put(Modern.Type.OTHER_BLOCK, new Modern.Data(null, new Modern.ItemTransform(new Vector3f(0.0F, 0.0F, 0.04F), new Vector3f(-6.0F, 0.0F, 0.0F), 1.05F)))
      .put(
         Modern.Type.OTHER_ITEM,
         new Modern.Data(
            new Modern.ItemTransform(new Vector3f(-0.01F, -0.01F, -0.02F), new Vector3f(1.63F, 4.57F, 0.0F), null),
            new Modern.ItemTransform(new Vector3f(0.05F, -0.01F, -0.01F), new Vector3f(22.17F, -10.11F, 16.0F), null)
         )
      )
      .build();

   Modern() {
   }

   @Nullable
   public static Modern.Data method1(ItemStackBridge bridgeextension_40) {
      if (bridgeextension_40 == null) {
         return null;
      } else {
         ItemBridge bridge6_41 = bridgeextension_40.bridge$getItem();
         if (bridge6_41.bridge$isItemRod() || bridge6_41.bridge$isItemCarrotOnStick()) {
            return method2(Modern.Type.ROD, bridgeextension_40);
         } else if (bridge6_41.bridge$isItemCarpet()) {
            return method2(Modern.Type.CARPET, bridgeextension_40);
         } else if (bridge6_41.bridge$isItemBasicTool() || bridge6_41.bridge$isItemSword() || bridge6_41.bridge$isItemStick() || bridge6_41.bridge$isItemBone()) {
            return method2(Modern.Type.TOOL, bridgeextension_40);
         } else if (bridge6_41.bridge$isItemBow()) {
            return method2(Modern.Type.BOW, bridgeextension_40);
         } else if (bridge6_41.bridge$isItemDoor()) {
            return method2(Modern.Type.DOOR, bridgeextension_40);
         } else if (bridge6_41.bridge$isItemTrapdoor()) {
            return method2(Modern.Type.TRAPDOOR, bridgeextension_40);
         } else if (bridge6_41.bridge$isItemAnvil() || bridge6_41.bridge$isItemFence()) {
            return method2(Modern.Type.ANVIL_FENCE, bridgeextension_40);
         } else if (bridge6_41.bridge$isItemBed()) {
            return method2(Modern.Type.BED, bridgeextension_40);
         } else if (bridge6_41.bridge$isItemBlazeRod()) {
            return method2(Modern.Type.BLAZE_ROD, bridgeextension_40);
         } else {
            return bridgeextension_40.bridge$isItemCubeBlock() ? field1.get(Modern.Type.OTHER_BLOCK) : field1.get(Modern.Type.OTHER_ITEM);
         }
      }
   }

   private static Modern.Data method2(Modern.Type type0, ItemStackBridge bridgeextension_41) {
      Modern.Data data2 = field1.get(type0);
      if (data2 == null) {
         throw new IllegalStateException("No item transform entry for key " + type0 + ", this should never happen!");
      }

      if (data2.field1 != null && data2.field2 != null) {
         return data2;
      }

      Modern.Data data3 = field1.get(bridgeextension_41.bridge$isItemCubeBlock() ? Modern.Type.OTHER_BLOCK : Modern.Type.OTHER_ITEM);
      return new Modern.Data(method3(data2.field1, data3.field1), method3(data2.field2, data3.field2));
   }

   private static Modern.ItemTransform method3(Modern.ItemTransform data20, Modern.ItemTransform data21) {
      return data20 == null ? data21 : data20;
   }

   public class Data {
      @Nullable
      private final Modern.ItemTransform field1;
      @Nullable
      private final Modern.ItemTransform field2;

      public Data(@Nullable Modern.ItemTransform data21, @Nullable Modern.ItemTransform data22) {
         this.field1 = data21;
         this.field2 = data22;
      }

      public void method1(EventRenderGlintTransform highlightimpl61) {
         Modern.ItemTransform data22 = highlightimpl61.method8().firstPerson() ? this.field1 : (highlightimpl61.method8().thirdPerson() ? this.field2 : null);
         if (data22 != null) {
            highlightimpl61.method1(data22.method1(), data22.method2(), data22.method3());
            highlightimpl61.method2(data22.method4());
            highlightimpl61.method3(data22.method5());
            highlightimpl61.method4(data22.method6());
            float value3 = data22.scale();
            highlightimpl61.scale(value3, value3, value3);
         }
      }

      @Nullable
      public Modern.ItemTransform method2() {
         return this.field1;
      }

      @Nullable
      public Modern.ItemTransform method3() {
         return this.field2;
      }
   }

   public class ItemTransform {
      @Nullable
      private final Vector3f field1;
      @Nullable
      private final Vector3f field2;
      @Nullable
      private final Float field3;

      public ItemTransform(@Nullable Vector3f vector3f1, @Nullable Vector3f vector3f2, @Nullable Float value3) {
         this.field1 = vector3f1;
         this.field2 = vector3f2;
         this.field3 = value3;
      }

      public float method1() {
         return this.field1 == null ? 0.0F : this.field1.x;
      }

      public float method2() {
         return this.field1 == null ? 0.0F : this.field1.y;
      }

      public float method3() {
         return this.field1 == null ? 0.0F : this.field1.z;
      }

      public float method4() {
         return this.field2 == null ? 0.0F : this.field2.x;
      }

      public float method5() {
         return this.field2 == null ? 0.0F : this.field2.y;
      }

      public float method6() {
         return this.field2 == null ? 0.0F : this.field2.z;
      }

      public float scale() {
         return this.field3 == null ? 1.0F : this.field3;
      }

      @Nullable
      public Vector3f method7() {
         return this.field1;
      }

      @Nullable
      public Vector3f method8() {
         return this.field2;
      }

      @Nullable
      public Float method9() {
         return this.field3;
      }
   }

   private enum Type {
      ROD,
      CARPET,
      TOOL,
      BOW,
      DOOR,
      TRAPDOOR,
      ANVIL_FENCE,
      BED,
      BLAZE_ROD,
      OTHER_BLOCK,
      OTHER_ITEM;

      Type() {
      }
   }
}
