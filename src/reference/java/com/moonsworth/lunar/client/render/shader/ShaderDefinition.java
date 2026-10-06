package com.moonsworth.lunar.client.render.shader;

import com.moonsworth.lunar.bridge.GlslUniformType;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.render.shader.ShaderCatalog;
import com.moonsworth.lunar.client.render.shader.ShaderKey;
import com.moonsworth.lunar.client.render.shader.ShaderUniform;
import com.moonsworth.lunar.client.render.shader.ShaderResource;
import com.moonsworth.lunar.client.render.shader.GlslBuiltin;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;
import java.util.function.BiConsumer;
import lombok.Generated;

public final class ShaderDefinition {
   private final ResourceLocationBridge field1;
   private final ShaderKey field2;
   private final ShaderKey field3;
   private final List<GlslBuiltin> field4;
   private final List<ShaderResource> field5;
   private final boolean field6;
   private final int field7;
   private final int field8;
   private String field9;
   private String field10;

   public boolean method1() {
      if (this.method2().method3()) {
         return true;
      }

      for (GlslBuiltin colorsaturationtype22 : this.field4) {
         if (colorsaturationtype22.isDifferentPerPlayer()) {
            return true;
         }
      }

      return false;
   }

   public ShaderUniform method2() {
      if (this.field3.equals(ShaderCatalog.field5)) {
         return ShaderUniform.field3;
      } else {
         return this.field3.equals(ShaderCatalog.field4) ? ShaderUniform.field2 : ShaderUniform.field1;
      }
   }

   public static void method3(ShaderUniform colorsaturation40, BiConsumer<String, GlslUniformType> biconsumer1) {
      if (colorsaturation40.method3()) {
         if (Ref.MC_VERSION >= 6) {
            biconsumer1.accept("LunarModelViewMat", GlslUniformType.MATRIX4X4);
            biconsumer1.accept("LunarNormalMat", GlslUniformType.MATRIX4X4);
            if (colorsaturation40 == ShaderUniform.field3) {
               biconsumer1.accept("LunarProjectionMat", GlslUniformType.MATRIX4X4);
            }
         } else {
            biconsumer1.accept("LunarLegacyUISize", GlslUniformType.VEC2);
         }
      }
   }

   @Generated
   public ShaderDefinition(
      ResourceLocationBridge horsestats141,
      ShaderKey colorsaturation22,
      ShaderKey colorsaturation23,
      List<GlslBuiltin> list,
      List<ShaderResource> list2,
      boolean flag,
      int value,
      int value2
   ) {
      this.field1 = horsestats141;
      this.field2 = colorsaturation22;
      this.field3 = colorsaturation23;
      this.field4 = list;
      this.field5 = list2;
      this.field6 = flag;
      this.field7 = value;
      this.field8 = value2;
   }

   @Generated
   public ResourceLocationBridge method4() {
      return this.field1;
   }

   @Generated
   public ShaderKey method5() {
      return this.field2;
   }

   @Generated
   public ShaderKey method6() {
      return this.field3;
   }

   @Generated
   public List<GlslBuiltin> method7() {
      return this.field4;
   }

   @Generated
   public List<ShaderResource> method8() {
      return this.field5;
   }

   @Generated
   public boolean method9() {
      return this.field6;
   }

   @Generated
   public int method10() {
      return this.field7;
   }

   @Generated
   public int method11() {
      return this.field8;
   }

   @Generated
   public String method12() {
      return this.field9;
   }

   @Generated
   public String method13() {
      return this.field10;
   }

   @Generated
   @Override
   public boolean equals(Object object) {
      if (object == this) {
         return true;
      } else if (!(object instanceof ShaderDefinition alert62)) {
         return false;
      } else {
         if (this.method9() != alert62.method9()) {
            return false;
         }

         if (this.method10() != alert62.method10()) {
            return false;
         }

         if (this.method11() != alert62.method11()) {
            return false;
         }

         ResourceLocationBridge horsestats143 = this.method4();
         ResourceLocationBridge horsestats144 = alert62.method4();
         if (horsestats143 == null ? horsestats144 == null : horsestats143.equals(horsestats144)) {
            ShaderKey colorsaturation25 = this.method5();
            ShaderKey colorsaturation26 = alert62.method5();
            if (colorsaturation25 == null ? colorsaturation26 == null : colorsaturation25.equals(colorsaturation26)) {
               ShaderKey colorsaturation27 = this.method6();
               ShaderKey colorsaturation28 = alert62.method6();
               if (colorsaturation27 == null ? colorsaturation28 == null : colorsaturation27.equals(colorsaturation28)) {
                  List list9 = this.method7();
                  List list10 = alert62.method7();
                  if (list9 == null ? list10 == null : list9.equals(list10)) {
                     List list11 = this.method8();
                     List list12 = alert62.method8();
                     if (list11 == null ? list12 == null : list11.equals(list12)) {
                        String text13 = this.method12();
                        String text14 = alert62.method12();
                        if (text13 == null ? text14 == null : text13.equals(text14)) {
                           String text15 = this.method13();
                           String text16 = alert62.method13();
                           return text15 == null ? text16 == null : text15.equals(text16);
                        } else {
                           return false;
                        }
                     } else {
                        return false;
                     }
                  } else {
                     return false;
                  }
               } else {
                  return false;
               }
            } else {
               return false;
            }
         } else {
            return false;
         }
      }
   }

   @Generated
   @Override
   public int hashCode() {
      byte number1 = 59;
      int number2 = 1;
      number2 = number2 * 59 + (this.method9() ? 79 : 97);
      number2 = number2 * 59 + this.method10();
      number2 = number2 * 59 + this.method11();
      ResourceLocationBridge horsestats143 = this.method4();
      number2 = number2 * 59 + (horsestats143 == null ? 43 : horsestats143.hashCode());
      ShaderKey colorsaturation24 = this.method5();
      number2 = number2 * 59 + (colorsaturation24 == null ? 43 : colorsaturation24.hashCode());
      ShaderKey colorsaturation25 = this.method6();
      number2 = number2 * 59 + (colorsaturation25 == null ? 43 : colorsaturation25.hashCode());
      List list6 = this.method7();
      number2 = number2 * 59 + (list6 == null ? 43 : list6.hashCode());
      List list7 = this.method8();
      number2 = number2 * 59 + (list7 == null ? 43 : list7.hashCode());
      String text8 = this.method12();
      number2 = number2 * 59 + (text8 == null ? 43 : text8.hashCode());
      String text9 = this.method13();
      return number2 * 59 + (text9 == null ? 43 : text9.hashCode());
   }

   @Generated
   public ShaderDefinition method14(String text1) {
      this.field9 = text1;
      return this;
   }

   @Generated
   public ShaderDefinition method15(String text1) {
      this.field10 = text1;
      return this;
   }
}
