package com.moonsworth.lunar.client.cosmetics.gecko;

import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.client.cosmetics.PlayerModelPartMap;
import com.moonsworth.lunar.client.cosmetics.gecko.ModelMatrixStack;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import org.joml.Matrix4f;
import org.joml.Vector4f;

public class BedrockGeometry {
   public List<IBoneSerializer> field1 = new ArrayList<>();
   public ModelDescription field2;

   public BedrockGeometry() {
   }

   public AxisAlignedBBBridge method1() {
      return this.method5();
   }

   public Optional<IBoneSerializer> method2(String text1) {
      for (IBoneSerializer iboneserializer3 : this.field1) {
         IBoneSerializer iboneserializer4 = this.method3(text1, iboneserializer3);
         if (iboneserializer4 != null) {
            return Optional.of(iboneserializer4);
         }
      }

      return Optional.empty();
   }

   private IBoneSerializer method3(String text1, IBoneSerializer iboneserializer2) {
      if (iboneserializer2.name.equals(text1)) {
         return iboneserializer2;
      }

      for (IBoneSerializer iboneserializer4 : iboneserializer2.field1) {
         if (iboneserializer4.name.equals(text1)) {
            return iboneserializer4;
         }

         IBoneSerializer iboneserializer5 = this.method3(text1, iboneserializer4);
         if (iboneserializer5 != null) {
            return iboneserializer5;
         }
      }

      return null;
   }

   public void method4(Predicate<AxisAlignedBBBridge> predicate1) {
      ModelMatrixStack colorsaturation2 = new ModelMatrixStack();

      for (IBoneSerializer iboneserializer4 : this.field1) {
         if (this.method7(iboneserializer4, colorsaturation2, predicate1)) {
            break;
         }
      }
   }

   private AxisAlignedBBBridge method5() {
      ModelMatrixStack colorsaturation1 = new ModelMatrixStack();
      AxisAlignedBBBridge horsestats122 = null;

      for (IBoneSerializer iboneserializer4 : this.field1) {
         horsestats122 = this.method6(horsestats122, iboneserializer4, colorsaturation1);
      }

      return horsestats122;
   }

   private AxisAlignedBBBridge method6(AxisAlignedBBBridge horsestats121, IBoneSerializer iboneserializer2, ModelMatrixStack colorsaturation3) {
      if (!iboneserializer2.isHidden && (iboneserializer2.getScaleX() != 0.0F || iboneserializer2.getScaleY() != 0.0F || iboneserializer2.getScaleZ() != 0.0F)) {
         colorsaturation3.push();
         PlayerModelPartMap.method16(iboneserializer2, colorsaturation3);

         for (CubeMesh rewindhandlers_25 : iboneserializer2.field2) {
            colorsaturation3.push();
            PlayerModelPartMap.method20(rewindhandlers_25, colorsaturation3);
            Matrix4f matrix4f6 = colorsaturation3.method1();
            AxisAlignedBBBridge horsestats127 = PlayerModelPartMap.method23(rewindhandlers_25, arg1x -> {
               Vector4f vector4f2x = matrix4f6.transform(new Vector4f(arg1x.x, arg1x.y, arg1x.z, 1.0F));
               arg1x.x = vector4f2x.x;
               arg1x.y = vector4f2x.y;
               arg1x.z = vector4f2x.z;
            });
            if (horsestats127 == null) {
               colorsaturation3.pop();
            } else {
               horsestats121 = horsestats121 == null ? horsestats127 : horsestats121.method6(horsestats127);
               colorsaturation3.pop();
            }
         }

         for (IBoneSerializer iboneserializer9 : iboneserializer2.field1) {
            horsestats121 = this.method6(horsestats121, iboneserializer9, colorsaturation3);
         }

         colorsaturation3.pop();
         return horsestats121;
      } else {
         return horsestats121;
      }
   }

   private boolean method7(IBoneSerializer iboneserializer1, ModelMatrixStack colorsaturation2, Predicate<AxisAlignedBBBridge> predicate3) {
      if (iboneserializer1.isHidden) {
         return false;
      }

      colorsaturation2.push();
      PlayerModelPartMap.method16(iboneserializer1, colorsaturation2);

      for (CubeMesh rewindhandlers_25 : iboneserializer1.field2) {
         colorsaturation2.push();
         PlayerModelPartMap.method20(rewindhandlers_25, colorsaturation2);
         Matrix4f matrix4f6 = colorsaturation2.method1();
         AxisAlignedBBBridge horsestats127 = PlayerModelPartMap.method23(rewindhandlers_25, arg1x -> {
            Vector4f vector4f2x = matrix4f6.transform(new Vector4f(arg1x.x, arg1x.y, arg1x.z, 1.0F));
            arg1x.x = vector4f2x.x;
            arg1x.y = vector4f2x.y;
            arg1x.z = vector4f2x.z;
         });
         if (horsestats127 == null) {
            colorsaturation2.pop();
         } else {
            if (predicate3.test(horsestats127)) {
               return true;
            }

            colorsaturation2.pop();
         }
      }

      for (IBoneSerializer iboneserializer9 : iboneserializer1.field1) {
         if (this.method7(iboneserializer9, colorsaturation2, predicate3)) {
            return true;
         }
      }

      colorsaturation2.pop();
      return false;
   }
}
