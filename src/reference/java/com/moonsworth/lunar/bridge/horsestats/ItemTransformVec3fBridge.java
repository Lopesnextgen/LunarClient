package com.moonsworth.lunar.bridge.horsestats;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.moonsworth.lunar.bridge.Bridge5_16;
import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.ichor.Annotation2;
import java.lang.reflect.Type;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class ItemTransformVec3fBridge {
   public static ItemTransformVec3fBridge field1 = new ItemTransformVec3fBridge(new Vector3f(), new Vector3f(), new Vector3f(1.0F, 1.0F, 1.0F));
   public Vector3f field2;
   public Vector3f field3;
   public Vector3f field4;

   public ItemTransformVec3fBridge(Vector3f var1, Vector3f var2, Vector3f var3) {
      this.field2 = new Vector3f(var1);
      this.field3 = new Vector3f(var2);
      this.field4 = new Vector3f(var3);
   }

   @Annotation2(min = 6)
   public void method1(boolean var1, Bridge5_16 var2) {
      if (this != field1) {
         float var3 = this.field2.x();
         float var4 = this.field2.y();
         float var5 = this.field2.z();
         if (var1) {
            var4 = -var4;
            var5 = -var5;
         }

         int var6 = var1 ? -1 : 1;
         var2.bridge$translate(var6 * this.field3.x(), this.field3.y(), this.field3.z());
         var2.bridge$mulPose(method3(Math.toRadians(var3), Math.toRadians(var4), Math.toRadians(var5)));
         var2.bridge$scale(this.field4.x(), this.field4.y(), this.field4.z());
      }
   }

   @Annotation2(max = 5)
   public void method2(boolean var1, BridgeExtension3_5 var2) {
      if (this != field1) {
         float var3 = this.field2.x();
         float var4 = this.field2.y();
         float var5 = this.field2.z();
         if (var1) {
            var4 = -var4;
            var5 = -var5;
         }

         int var6 = var1 ? -1 : 1;
         var2.translate(var6 * this.field3.x(), this.field3.y(), this.field3.z());
         var2.method13(method3(Math.toRadians(var3), Math.toRadians(var4), Math.toRadians(var5)));
         var2.scale(this.field4.x(), this.field4.y(), this.field4.z());
      }
   }

   public static Quaternionf method3(double var0, double var2, double var4) {
      float var6 = (float)Math.sin(0.5 * var0);
      float var7 = (float)Math.cos(0.5 * var0);
      float var8 = (float)Math.sin(0.5 * var2);
      float var9 = (float)Math.cos(0.5 * var2);
      float var10 = (float)Math.sin(0.5 * var4);
      float var11 = (float)Math.cos(0.5 * var4);
      float var12 = var6 * var9 * var11 + var7 * var8 * var10;
      float var13 = var7 * var8 * var11 - var6 * var9 * var10;
      float var14 = var6 * var8 * var11 + var7 * var9 * var10;
      float var15 = var7 * var9 * var11 - var6 * var8 * var10;
      return new Quaternionf(var12, var13, var14, var15);
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      }

      if (this.getClass() != var1.getClass()) {
         return false;
      }

      ItemTransformVec3fBridge var2 = (ItemTransformVec3fBridge)var1;
      return this.field2.equals(var2.field2) && this.field4.equals(var2.field4) && this.field3.equals(var2.field3);
   }

   @Override
   public int hashCode() {
      int var1 = this.field2.hashCode();
      var1 = 31 * var1 + this.field3.hashCode();
      return 31 * var1 + this.field4.hashCode();
   }

   public static JsonArray method4(JsonObject var0, String var1) {
      if (var0.has(var1)) {
         return method5(var0.get(var1), var1);
      } else {
         throw new JsonSyntaxException("Missing " + var1 + ", expected to find a JsonArray");
      }
   }

   public static JsonArray method5(JsonElement var0, String var1) {
      if (var0.isJsonArray()) {
         return var0.getAsJsonArray();
      } else {
         throw new JsonSyntaxException("Expected " + var1 + " to be a JsonArray");
      }
   }

   public static float method6(JsonElement var0, String var1) {
      if (var0.isJsonPrimitive() && var0.getAsJsonPrimitive().isNumber()) {
         return var0.getAsFloat();
      } else {
         throw new JsonSyntaxException("Expected " + var1 + " to be a Float");
      }
   }

   public static class Data implements JsonDeserializer<ItemTransformVec3fBridge> {
      public static Vector3f field1 = new Vector3f(0.0F, 0.0F, 0.0F);
      public static Vector3f field2 = new Vector3f(0.0F, 0.0F, 0.0F);
      public static Vector3f field3 = new Vector3f(1.0F, 1.0F, 1.0F);
      public static float field4;
      public static float MAX_SCALE;

      public ItemTransformVec3fBridge method1(JsonElement var1, Type var2, JsonDeserializationContext var3) {
         JsonObject var4 = var1.getAsJsonObject();
         Vector3f var5 = this.method3(var4, "rotation", field1);
         Vector3f var6 = this.method3(var4, "translation", field2);
         var6.mul(0.0625F);
         method2(var6, -5.0F, 5.0F);
         Vector3f var7 = this.method3(var4, "scale", field3);
         method2(var7, -4.0F, 4.0F);
         return new ItemTransformVec3fBridge(var5, var6, var7);
      }

      private static void method2(Vector3f var0, float var1, float var2) {
         var0.set(clamp(var0.x, var1, var2), clamp(var0.y, var1, var2), clamp(var0.z, var1, var2));
      }

      public static float clamp(float var0, float var1, float var2) {
         return Math.min(var2, Math.max(var0, var1));
      }

      public Vector3f method3(JsonObject var1, String var2, Vector3f var3) {
         if (!var1.has(var2)) {
            return var3;
         }

         JsonArray var4 = ItemTransformVec3fBridge.method4(var1, var2);
         if (var4.size() != 3) {
            throw new JsonParseException("Expected 3 " + var2 + " values, found: " + var4.size());
         }

         float[] var5 = new float[3];

         for (int var6 = 0; var6 < var5.length; var6++) {
            var5[var6] = ItemTransformVec3fBridge.method6(var4.get(var6), var2 + "[" + var6 + "]");
         }

         return new Vector3f(var5[0], var5[1], var5[2]);
      }
   }
}
