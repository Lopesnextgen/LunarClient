package com.moonsworth.lunar.client.cosmetics.gecko;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Validate;
import org.joml.Vector3f;

public class ModelVertex {
   public final Vector3f field1;
   public float field2;
   public float field3;

   public ModelVertex(float value1, float value2, float value3) {
      this.field1 = new Vector3f(value1, value2, value3);
   }

   public ModelVertex(double value1, double value3, double value) {
      this.field1 = new Vector3f((float)value1, (float)value3, (float)value);
   }

   public ModelVertex method1(float value1, float value2) {
      return new ModelVertex(this.field1, value1, value2);
   }

   public ModelVertex method2(double[] items1) {
      Validate.validIndex(ArrayUtils.toObject(items1), 1);
      return new ModelVertex(this.field1, (float)items1[0], (float)items1[1]);
   }

   public ModelVertex(Vector3f vector3f1, float value2, float value3) {
      this.field1 = vector3f1;
      this.field2 = value2;
      this.field3 = value3;
   }
}
