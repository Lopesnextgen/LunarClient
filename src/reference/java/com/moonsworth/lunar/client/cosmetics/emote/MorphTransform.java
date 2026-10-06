package com.moonsworth.lunar.client.cosmetics.emote;

import javax.vecmath.Matrix4f;
import javax.vecmath.Vector3f;
import mchorse.emoticons.morph.Morph;

public class MorphTransform {
   private final float[] field1;
   private final float[] rotation;
   private final float[] scale;
   public static final MorphTransform field2 = new MorphTransform();
   private static final Matrix4f field3 = new Matrix4f();

   private MorphTransform() {
      this.field1 = null;
      this.rotation = null;
      this.scale = null;
   }

   public MorphTransform(Morph morph1) {
      this.field1 = morph1.getTranslate();
      this.rotation = morph1.getRotation();
      this.scale = morph1.getScale();
   }

   public void method1(Matrix4f matrix4f1) {
      if (this.field1 != null && this.field1.length == 3) {
         field3.setIdentity();
         field3.setTranslation(new Vector3f(this.field1[0], this.field1[1], this.field1[2]));
         matrix4f1.mul(field3);
      }

      if (this.rotation != null && this.rotation.length == 3) {
         field3.rotX((float)Math.toRadians(this.rotation[0]));
         matrix4f1.mul(field3);
         field3.rotY((float)Math.toRadians(this.rotation[1]));
         matrix4f1.mul(field3);
         field3.rotZ((float)Math.toRadians(this.rotation[2]));
         matrix4f1.mul(field3);
      }

      if (this.scale != null && this.scale.length == 3) {
         field3.setIdentity();
         field3.setM00(this.scale[0]);
         field3.setM11(this.scale[1]);
         field3.setM22(this.scale[2]);
         matrix4f1.mul(field3);
      }
   }
}
