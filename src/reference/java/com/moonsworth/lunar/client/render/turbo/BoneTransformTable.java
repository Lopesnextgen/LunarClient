package com.moonsworth.lunar.client.render.turbo;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge2_46;
import com.moonsworth.lunar.bridge.BridgeExtension2_7;
import com.moonsworth.lunar.bridge.BipedModelBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.Bridge_53;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEventAlt;
import com.moonsworth.lunar.client.cosmetics.emote.IBoneRenderer;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import javax.vecmath.Matrix4f;
import javax.vecmath.Vector4f;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;

public class BoneTransformTable implements IBoneRenderer {
   private static final List<Matrix4f> field1 = new ArrayList<>();
   private static final EnumMap<BoneTransformTable.Type2, Function<BoneTransformTable, Matrix4f>> field2 = method3();
   private static final Map<String, BoneTransformTable.Type2> field3 = new HashMap<>();
   private final Matrix4f field4;
   private final BridgeExtension2_7 field5;
   private final EnumMap<BoneTransformTable.Type2, Matrix4f> field6;

   public BoneTransformTable(Matrix4f var1, BridgeExtension2_7 var2) {
      this.field4 = var1;
      this.field5 = var2;
      this.field6 = new EnumMap<>(BoneTransformTable.Type2.class);
      this.method1();
   }

   private void method1() {
      if (LunarBuildData.field4 || Bridge.getMinecraftVersion().method23()) {
         field1.clear();
      } else if (ThreadModuleDump63.method3().bridge$getGameSettings().bridge$showDebugInfo()
         && !ThreadModuleDump63.method3().bridge$getGameSettings().bridge$isHideGui()) {
         for (BoneTransformTable.Type2 var4 : BoneTransformTable.Type2.values()) {
            field1.add(this.method2(var4));
         }
      } else {
         field1.clear();
      }
   }

   public void applyBoneTransform(Matrix4f var1, String var2) {
      BoneTransformTable.Type2 var3 = BoneTransformTable.Type2.byName(var2);
      if (var3 != null) {
         var1.mul(this.method2(var3));
      }
   }

   public Matrix4f method2(BoneTransformTable.Type2 var1) {
      Matrix4f var2 = this.field6.get(var1);
      if (var2 == null) {
         var2 = field2.get(var1).apply(this);
         this.field6.put(var1, var2);
      }

      return var2;
   }

   public static EnumMap<BoneTransformTable.Type2, Function<BoneTransformTable, Matrix4f>> method3() {
      EnumMap var0 = new EnumMap<>(BoneTransformTable.Type2.class);
      var0.put(BoneTransformTable.Type2.HEAD, method4(BipedModelBridge::bridge$bipedHead, var0x -> var0x.scale(1.0F, -1.0F, -1.0F)));
      var0.put(BoneTransformTable.Type2.LEFT_LEG, method4(BipedModelBridge::bridge$bipedLeftLeg, var0x -> var0x.scale(-1.0F, 1.0F, -1.0F)));
      var0.put(BoneTransformTable.Type2.RIGHT_LEG, method4(BipedModelBridge::bridge$bipedRightLeg, var0x -> var0x.scale(-1.0F, 1.0F, -1.0F)));
      var0.put(BoneTransformTable.Type2.LOW_LEFT_LEG, method5(BoneTransformTable.Type2.LEFT_LEG, var0x -> var0x.method7(0.0F, 0.375F, 0.0F)));
      var0.put(BoneTransformTable.Type2.LOW_RIGHT_LEG, method5(BoneTransformTable.Type2.RIGHT_LEG, var0x -> var0x.method7(0.0F, 0.375F, 0.0F)));
      var0.put(BoneTransformTable.Type2.LEFT_ARM, method4(BipedModelBridge::bridge$bipedLeftArm, var0x -> {
         var0x.scale(-1.0F, 1.0F, -1.0F);
         var0x.method7(-0.0625F, 0.0F, 0.0F);
      }));
      var0.put(BoneTransformTable.Type2.RIGHT_ARM, method4(BipedModelBridge::bridge$bipedRightArm, var0x -> {
         var0x.scale(-1.0F, 1.0F, -1.0F);
         var0x.method7(0.0625F, 0.0F, 0.0F);
      }));
      Consumer var1 = var0x -> var0x.method7(0.0F, 0.25F, 0.0F);
      var0.put(BoneTransformTable.Type2.LOW_LEFT_ARM, method5(BoneTransformTable.Type2.LEFT_ARM, var1));
      var0.put(BoneTransformTable.Type2.LOW_RIGHT_ARM, method5(BoneTransformTable.Type2.RIGHT_ARM, var1));
      Consumer var2 = var0x -> {
         var0x.method7(0.0F, 0.25F, 0.0F);
         var0x.scale(-1.0F, 1.0F, -1.0F);
      };
      var0.put(BoneTransformTable.Type2.LOW_LEFT_ARM_END, method5(BoneTransformTable.Type2.LOW_LEFT_ARM, var2));
      var0.put(BoneTransformTable.Type2.LOW_RIGHT_ARM_END, method5(BoneTransformTable.Type2.LOW_RIGHT_ARM, var2));
      Consumer var3 = var0x -> var0x.method7(0.0F, 0.0F, -0.125F);
      var0.put(BoneTransformTable.Type2.LOW_LEFT_ARM_ITEM, method5(BoneTransformTable.Type2.LOW_LEFT_ARM_END, var3));
      var0.put(BoneTransformTable.Type2.LOW_RIGHT_ARM_ITEM, method5(BoneTransformTable.Type2.LOW_RIGHT_ARM_END, var3));
      var0.put(BoneTransformTable.Type2.BODY, method4(BipedModelBridge::bridge$bipedBody, var0x -> {
         var0x.method7(0.0F, 0.375F, 0.0F);
         var0x.scale(-1.0F, -1.0F, 1.0F);
      }));
      var0.put(BoneTransformTable.Type2.LOW_BODY, method5(BoneTransformTable.Type2.BODY, var0x -> var0x.method7(0.0F, -0.375F, 0.0F)));
      var0.put(BoneTransformTable.Type2.ANCHOR, method5(BoneTransformTable.Type2.LOW_BODY, var0x -> var0x.method6(-90.0F, 1.0F, 0.0F, 0.0F)));
      return var0;
   }

   private static Function<BoneTransformTable, Matrix4f> method4(Function<BridgeExtension2_7, Bridge2_46> var0, Consumer<Bridge_53> var1) {
      return var2 -> {
         Bridge_53 var3 = new Bridge_53((Matrix4f)var2.field4.clone());
         ((Bridge2_46)var0.apply(var2.field5)).method1(var3);
         var1.accept(var3);
         return var3.getMatrix();
      };
   }

   private static Function<BoneTransformTable, Matrix4f> method5(BoneTransformTable.Type2 var0, Consumer<Bridge_53> var1) {
      return var2 -> {
         Matrix4f var3 = Objects.requireNonNull(var2.method2(var0));
         Bridge_53 var4 = new Bridge_53((Matrix4f)var3.clone());
         var1.accept(var4);
         return var4.getMatrix();
      };
   }

   public static void method6(HudRenderLegacyEventAlt var0) {
      if (LunarBuildData.field4 || Bridge.getMinecraftVersion().method23()) {
         field1.clear();
      } else if (ThreadModuleDump63.method3().bridge$getGameSettings().bridge$showDebugInfo()
         && !ThreadModuleDump63.method3().bridge$getGameSettings().bridge$isHideGui()) {
         AbstractRenderContext var1 = var0.method3();
         var1.method19();
         var1.method13();

         for (Matrix4f var3 : field1) {
            Vector4f var4 = new Vector4f(0.0F, 0.0F, 0.0F, 1.0F);
            Vector4f var5 = new Vector4f(0.1F, 0.0F, 0.0F, 1.0F);
            Vector4f var6 = new Vector4f(0.0F, 0.1F, 0.0F, 1.0F);
            Vector4f var7 = new Vector4f(0.0F, 0.0F, 0.1F, 1.0F);
            var3.transform(var4);
            var3.transform(var5);
            var3.transform(var6);
            var3.transform(var7);
            GL11.glPointSize(5.0F);
            GL11.glBegin(0);
            var1.method19(1.0F, 1.0F, 1.0F);
            GL11.glVertex3f(var4.x, var4.y, var4.z);
            GL11.glEnd();
            GL11.glLineWidth(2.0F);
            GL11.glBegin(1);
            var1.method19(1.0F, 0.0F, 0.0F);
            GL11.glVertex3f(var4.x, var4.y, var4.z);
            GL11.glVertex3f(var5.x, var5.y, var5.z);
            GL11.glEnd();
            GL11.glBegin(1);
            var1.method19(0.0F, 1.0F, 0.0F);
            GL11.glVertex3f(var4.x, var4.y, var4.z);
            GL11.glVertex3f(var6.x, var6.y, var6.z);
            GL11.glEnd();
            GL11.glBegin(1);
            var1.method19(0.0F, 0.0F, 1.0F);
            GL11.glVertex3f(var4.x, var4.y, var4.z);
            GL11.glVertex3f(var7.x, var7.y, var7.z);
            GL11.glEnd();
         }

         field1.clear();
         var1.method19(1.0F, 1.0F, 1.0F);
         GL11.glLineWidth(1.0F);
         var1.method18();
         var1.method12();
      } else {
         field1.clear();
      }
   }

   static {
      ClientEventBus.method29().method2(HudRenderLegacyEventAlt.class, BoneTransformTable::method6);

      for (BoneTransformTable.Type2 var3 : BoneTransformTable.Type2.values()) {
         field3.put(var3.getBoneName(), var3);
      }
   }

   public enum Type2 {
      HEAD("head"),
      LEFT_LEG("left_leg"),
      RIGHT_LEG("right_leg"),
      LOW_LEFT_LEG("low_left_leg"),
      LOW_RIGHT_LEG("low_leg_right"),
      LEFT_ARM("left_arm"),
      RIGHT_ARM("right_arm"),
      LOW_LEFT_ARM("low_left_arm"),
      LOW_RIGHT_ARM("low_right_arm"),
      LOW_LEFT_ARM_END("low_left_arm.end"),
      LOW_RIGHT_ARM_END("low_right_arm.end"),
      LOW_LEFT_ARM_ITEM("low_left_arm.item"),
      LOW_RIGHT_ARM_ITEM("low_right_arm.item"),
      BODY("body"),
      LOW_BODY("low_body"),
      ANCHOR("anchor");

      private final String boneName;

      @Nullable
      public static BoneTransformTable.Type2 byName(String var0) {
         return BoneTransformTable.field3.get(var0);
      }

      @Generated
      Type2(String var3) {
         this.boneName = var3;
      }

      @Generated
      public String getBoneName() {
         return this.boneName;
      }
   }
}
