package com.moonsworth.lunar.client.render.particle;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.moonsworth.lunar.client.render.particle.BedrockParticle;
import com.moonsworth.lunar.client.render.particle.MolangExpression;
import javax.vecmath.Vector3d;

public abstract class ParticleDirection {
   public static final ParticleDirection field1 = new ParticleDirection.RadialVelocity(-1.0F);
   public static final ParticleDirection field2 = new ParticleDirection.RadialVelocity(1.0F);

   public ParticleDirection() {
   }

   public abstract void method1(BedrockParticle glintcolorizer4_21, double value2, double value4, double value6);

   public abstract JsonElement method2();

   public static class FixedVelocity extends ParticleDirection {
      public MolangExpression field3;
      public MolangExpression field4;
      public MolangExpression field5;

      public FixedVelocity(MolangExpression glintcolorizer_51, MolangExpression glintcolorizer_52, MolangExpression glintcolorizer_53) {
         this.field3 = glintcolorizer_51;
         this.field4 = glintcolorizer_52;
         this.field5 = glintcolorizer_53;
      }

      @Override
      public void method1(BedrockParticle glintcolorizer4_21, double value2, double value4, double value6) {
         glintcolorizer4_21.field30.set((float)this.field3.get(), (float)this.field4.get(), (float)this.field5.get());
         if (glintcolorizer4_21.field30.length() <= 0.0F) {
            glintcolorizer4_21.field30.set(0.0F, 0.0F, 0.0F);
         } else {
            glintcolorizer4_21.field30.normalize();
         }
      }

      @Override
      public JsonElement method2() {
         JsonArray array1 = new JsonArray();
         array1.add(this.field3.method5());
         array1.add(this.field4.method5());
         array1.add(this.field5.method5());
         return array1;
      }
   }

   private static class RadialVelocity extends ParticleDirection {
      private float factor;

      public RadialVelocity(float value) {
         this.factor = value;
      }

      @Override
      public void method1(BedrockParticle glintcolorizer4_21, double value2, double value4, double value6) {
         Vector3d vector3d8 = new Vector3d(glintcolorizer4_21.field25);
         vector3d8.sub(new Vector3d(value2, value4, value6));
         if (vector3d8.length() <= 0.0) {
            vector3d8.set(0.0, 0.0, 0.0);
         } else {
            vector3d8.normalize();
            vector3d8.scale(this.factor);
         }

         glintcolorizer4_21.field30.set(vector3d8);
      }

      @Override
      public JsonElement method2() {
         return new JsonPrimitive(this.factor < 0.0F ? "inwards" : "outwards");
      }
   }
}
