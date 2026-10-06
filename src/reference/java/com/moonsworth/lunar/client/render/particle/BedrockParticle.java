package com.moonsworth.lunar.client.render.particle;

import com.moonsworth.lunar.client.render.particle.ParticleMath;
import java.util.concurrent.ThreadLocalRandom;
import javax.vecmath.Matrix3f;
import javax.vecmath.SingularMatrixException;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

public class BedrockParticle {
   public float field1 = ThreadLocalRandom.current().nextFloat();
   public float field2 = ThreadLocalRandom.current().nextFloat();
   public float field3 = ThreadLocalRandom.current().nextFloat();
   public float field4 = ThreadLocalRandom.current().nextFloat();
   public int field5;
   public int lifetime;
   public boolean field6;
   public boolean field7;
   public boolean field8;
   public boolean field9;
   public boolean field10;
   public boolean field11;
   public boolean field12;
   public boolean field13;
   public float field14;
   public float field15;
   public boolean field16;
   public boolean field17;
   public boolean field18;
   public boolean field19;
   public float rotation;
   public float field20;
   public float field21;
   public float field22;
   public float field23;
   public float field24;
   public Vector3d field25 = new Vector3d();
   public Vector3d field26 = new Vector3d();
   public Vector3d field27 = new Vector3d();
   public Matrix3f field28 = new Matrix3f();
   private boolean field29;
   public Vector3f field30 = new Vector3f();
   public Vector3f field31 = new Vector3f();
   public Vector3f field32 = new Vector3f(1.0F, 1.0F, 1.0F);
   public float field33 = 0.0F;
   public float field34 = 0.0F;
   public float field35 = 1.0F;
   public float field36 = 1.0F;
   public float field37 = 1.0F;
   public float field38 = 1.0F;
   private final Vector3d field39 = new Vector3d();

   public BedrockParticle() {
      ThreadLocalRandom threadlocalrandom1 = ThreadLocalRandom.current();
      this.field30.set(threadlocalrandom1.nextFloat() - 0.5F, threadlocalrandom1.nextFloat() - 0.5F, threadlocalrandom1.nextFloat() - 0.5F);
      this.field30.normalize();
      this.field28.setIdentity();
   }

   public double method1(float value) {
      return (this.field5 + value) / 20.0;
   }

   public Vector3d method2(BedrockEmitter glintcolorizer5_21) {
      return this.method3(glintcolorizer5_21, this.field25);
   }

   public Vector3d method3(BedrockEmitter glintcolorizer5_21, Vector3d vector3d2) {
      double value3 = vector3d2.x;
      double value5 = vector3d2.y;
      double value7 = vector3d2.z;
      if (this.field7 && this.field8) {
         Vector3f vector3f9 = new Vector3f((float)value3, (float)value5, (float)value7);
         glintcolorizer5_21.field10.transform(vector3f9);
         value3 = vector3f9.x;
         value5 = vector3f9.y;
         value7 = vector3f9.z;
         value3 += glintcolorizer5_21.field8.x;
         value5 += glintcolorizer5_21.field8.y;
         value7 += glintcolorizer5_21.field8.z;
      }

      this.field39.set(value3, value5, value7);
      return this.field39;
   }

   public void method4(BedrockEmitter glintcolorizer5_21) {
      this.field21 = this.rotation;
      this.field27.set(this.field25);
      this.method5(glintcolorizer5_21);
      if (!this.field17) {
         if (this.field13
            && Math.round(this.field30.x * 10000.0F) == 0
            && Math.round(this.field30.y * 10000.0F) == 0
            && Math.round(this.field30.z * 10000.0F) == 0) {
            this.field34 = 0.0F;
            this.field30.scale(0.0F);
         }

         float value2 = this.field23 / 20.0F - this.field24 * this.field22;
         this.field22 += value2 / 20.0F;
         this.rotation = this.field20 + this.field22 * this.field5;
         if (this.field5 == 0) {
            if (this.field9) {
               glintcolorizer5_21.field10.transform(this.field30);
            }

            if (this.field14 != 0.0F) {
               Vector3f vector3f3 = new Vector3f(glintcolorizer5_21.field8);
               vector3f3.x = (float)(vector3f3.x - glintcolorizer5_21.field9.x);
               vector3f3.y = (float)(vector3f3.y - glintcolorizer5_21.field9.y);
               vector3f3.z = (float)(vector3f3.z - glintcolorizer5_21.field9.z);
               this.field30.x = this.field30.x + vector3f3.x * this.field14;
               this.field30.y = this.field30.y + vector3f3.y * this.field14;
               this.field30.z = this.field30.z + vector3f3.z * this.field14;
            }

            if (this.field15 != 0.0F) {
               Matrix3f matrix3f10 = new Matrix3f(glintcolorizer5_21.field10);
               Matrix3f matrix3f4 = new Matrix3f();
               matrix3f4.setIdentity();

               try {
                  Matrix3f matrix3f5 = new Matrix3f(glintcolorizer5_21.field11);
                  matrix3f5.invert();
                  matrix3f10.mul(matrix3f5);
                  Vector3f vector3f6 = ParticleMath.method1(matrix3f10);
                  Vector3f vector3f7 = new Vector3f(glintcolorizer5_21.field12);
                  vector3f7.x = (float)(vector3f7.x + (this.field25.x - glintcolorizer5_21.field8.x));
                  vector3f7.y = (float)(vector3f7.y + (this.field25.y - glintcolorizer5_21.field8.y));
                  vector3f7.z = (float)(vector3f7.z + (this.field25.z - glintcolorizer5_21.field8.z));
                  Vector3f vector3f8 = new Vector3f();
                  vector3f8.cross(vector3f6, vector3f7);
                  this.field30.x = this.field30.x + vector3f8.x * this.field15;
                  this.field30.y = this.field30.y + vector3f8.y * this.field15;
                  this.field30.z = this.field30.z + vector3f8.z * this.field15;
               } catch (SingularMatrixException singularmatrixexception9) {
               }
            }
         }

         if (this.field12) {
            glintcolorizer5_21.field10.transform(this.field31);
         }

         Vector3f vector3f11 = new Vector3f(this.field30);
         vector3f11.scale(-(this.field33 + this.field34));
         if (this.field16) {
            this.field31.y = (float)(this.field31.y - 9.81);
         }

         this.field31.add(vector3f11);
         this.field31.scale(0.05F);
         this.field30.add(this.field31);
         Vector3f vector3f12 = new Vector3f(this.field30);
         vector3f12.x = vector3f12.x * this.field32.x;
         vector3f12.y = vector3f12.y * this.field32.y;
         vector3f12.z = vector3f12.z * this.field32.z;
         if (this.field7 || this.field8) {
            this.field28.transform(vector3f12);
         }

         this.field25.x = this.field25.x + vector3f12.x / 20.0F;
         this.field25.y = this.field25.y + vector3f12.y / 20.0F;
         this.field25.z = this.field25.z + vector3f12.z / 20.0F;
      }

      if (this.lifetime >= 0 && this.field5 >= this.lifetime) {
         this.field6 = true;
      }

      this.field5++;
   }

   public void method5(BedrockEmitter glintcolorizer5_21) {
      if (this.field7) {
         if (this.field8) {
            this.field28.setIdentity();
         } else if (!this.field29) {
            this.field28.set(glintcolorizer5_21.field10);
            this.field29 = true;
         }
      } else if (this.field8) {
         this.field28.set(glintcolorizer5_21.field10);
      }
   }
}
