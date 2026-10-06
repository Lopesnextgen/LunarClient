package com.moonsworth.lunar.client.cosmetics.gecko;

import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.client.cosmetics.PlayerModelPartMap;
import com.moonsworth.lunar.client.cosmetics.gecko.AnimationKeyframeParser;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import org.joml.Matrix4f;
import org.joml.Vector4f;
import com.moonsworth.lunar.client.cosmetics.gecko.ModelDescription;
import com.moonsworth.lunar.client.cosmetics.gecko.CubeMesh;

public class BoneList {
   public List<IBoneSerializer> field1 = new ArrayList<>();
   public ModelDescription field2;

   public AxisAlignedBBBridge method1() {
      return this.method5();
   }

   public Optional<IBoneSerializer> method2(String var1) {
      for (IBoneSerializer var3 : this.field1) {
         IBoneSerializer var4 = this.method3(var1, var3);
         if (var4 != null) {
            return Optional.of(var4);
         }
      }

      return Optional.empty();
   }

   private IBoneSerializer method3(String var1, IBoneSerializer var2) {
      if (var2.name.equals(var1)) {
         return var2;
      }

      for (IBoneSerializer var4 : var2.field1) {
         if (var4.name.equals(var1)) {
            return var4;
         }

         IBoneSerializer var5 = this.method3(var1, var4);
         if (var5 != null) {
            return var5;
         }
      }

      return null;
   }

   public void method4(Predicate<AxisAlignedBBBridge> var1) {
      AnimationKeyframeParser var2 = new AnimationKeyframeParser();

      for (IBoneSerializer var4 : this.field1) {
         if (this.method7(var4, var2, var1)) {
            break;
         }
      }
   }

   private AxisAlignedBBBridge method5() {
      AnimationKeyframeParser var1 = new AnimationKeyframeParser();
      AxisAlignedBBBridge var2 = null;

      for (IBoneSerializer var4 : this.field1) {
         var2 = this.method6(var2, var4, var1);
      }

      return var2;
   }

   private AxisAlignedBBBridge method6(AxisAlignedBBBridge var1, IBoneSerializer var2, AnimationKeyframeParser var3) {
      if (!var2.isHidden && (var2.getScaleX() != 0.0F || var2.getScaleY() != 0.0F || var2.getScaleZ() != 0.0F)) {
         var3.push();
         PlayerModelPartMap.method16(var2, var3);

         for (CubeMesh var5 : var2.field2) {
            var3.push();
            PlayerModelPartMap.method20(var5, var3);
            Matrix4f var6 = var3.method1();
            AxisAlignedBBBridge var7 = PlayerModelPartMap.method23(var5, var1x -> {
               Vector4f var2x = var6.transform(new Vector4f(var1x.x, var1x.y, var1x.z, 1.0F));
               var1x.x = var2x.x;
               var1x.y = var2x.y;
               var1x.z = var2x.z;
            });
            if (var7 == null) {
               var3.pop();
            } else {
               var1 = var1 == null ? var7 : var1.method6(var7);
               var3.pop();
            }
         }

         for (IBoneSerializer var9 : var2.field1) {
            var1 = this.method6(var1, var9, var3);
         }

         var3.pop();
         return var1;
      } else {
         return var1;
      }
   }

   private boolean method7(IBoneSerializer var1, AnimationKeyframeParser var2, Predicate<AxisAlignedBBBridge> var3) {
      if (var1.isHidden) {
         return false;
      }

      var2.push();
      PlayerModelPartMap.method16(var1, var2);

      for (CubeMesh var5 : var1.field2) {
         var2.push();
         PlayerModelPartMap.method20(var5, var2);
         Matrix4f var6 = var2.method1();
         AxisAlignedBBBridge var7 = PlayerModelPartMap.method23(var5, var1x -> {
            Vector4f var2x = var6.transform(new Vector4f(var1x.x, var1x.y, var1x.z, 1.0F));
            var1x.x = var2x.x;
            var1x.y = var2x.y;
            var1x.z = var2x.z;
         });
         if (var7 == null) {
            var2.pop();
         } else {
            if (var3.test(var7)) {
               return true;
            }

            var2.pop();
         }
      }

      for (IBoneSerializer var9 : var1.field1) {
         if (this.method7(var9, var2, var3)) {
            return true;
         }
      }

      var2.pop();
      return false;
   }
}
