package com.moonsworth.lunar.bridge.horsestats;

import com.google.common.base.Objects;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ItemTransformsBridge {
   public static final Gson field1 = new GsonBuilder()
      .registerTypeAdapter(ItemTransformVec3fBridge.class, new ItemTransformVec3fBridge.Data())
      .registerTypeAdapter(ItemTransformsBridge.class, new ItemTransformsBridge.Data())
      .create();
   public static ItemTransformsBridge field2 = new ItemTransformsBridge();
   public ItemTransformVec3fBridge field3;
   public ItemTransformVec3fBridge field4;
   public ItemTransformVec3fBridge field5;
   public ItemTransformVec3fBridge field6;
   public ItemTransformVec3fBridge field7;
   public ItemTransformVec3fBridge field8;
   public ItemTransformVec3fBridge field9;
   public ItemTransformVec3fBridge field10;
   public ItemTransformVec3fBridge field11;
   public ItemTransformVec3fBridge field12;

   public ItemTransformsBridge() {
      this(
         ItemTransformVec3fBridge.field1,
         ItemTransformVec3fBridge.field1,
         ItemTransformVec3fBridge.field1,
         ItemTransformVec3fBridge.field1,
         ItemTransformVec3fBridge.field1,
         ItemTransformVec3fBridge.field1,
         ItemTransformVec3fBridge.field1,
         ItemTransformVec3fBridge.field1,
         ItemTransformVec3fBridge.field1
      );
   }

   public ItemTransformsBridge(ItemTransformsBridge var1) {
      this.field3 = var1.field3;
      this.field4 = var1.field4;
      this.field5 = var1.field5;
      this.field6 = var1.field6;
      this.field7 = var1.field7;
      this.field8 = var1.field8;
      this.field10 = var1.field10;
      this.field11 = var1.field11;
      this.field12 = var1.field12;
   }

   public ItemTransformsBridge(
      ItemTransformVec3fBridge var1,
      ItemTransformVec3fBridge var2,
      ItemTransformVec3fBridge var3,
      ItemTransformVec3fBridge var4,
      ItemTransformVec3fBridge var5,
      ItemTransformVec3fBridge var6,
      ItemTransformVec3fBridge var7,
      ItemTransformVec3fBridge var8,
      ItemTransformVec3fBridge var9
   ) {
      this.field3 = var1;
      this.field4 = var2;
      this.field5 = var3;
      this.field6 = var4;
      this.field7 = var5;
      this.field8 = var6;
      this.field10 = var7;
      this.field11 = var8;
      this.field12 = var9;
   }

   public ItemTransformVec3fBridge method1(ItemTransformsBridge.Type var1, boolean var2) {
      return switch (var1) {
         case THIRD_PERSON_LEFT_HAND -> this.field3;
         case THIRD_PERSON_RIGHT_HAND -> this.field4;
         case FIRST_PERSON_LEFT_HAND -> this.field5;
         case FIRST_PERSON_RIGHT_HAND -> this.field6;
         case HEAD -> this.field7;
         case GUI -> var2 ? (ItemTransformVec3fBridge)Objects.firstNonNull(this.field9, this.field8) : this.field8;
         case GROUND -> this.field10;
         case FIXED -> this.field11;
         case ON_SHELF -> this.field12;
         default -> ItemTransformVec3fBridge.field1;
      };
   }

   public static class Data implements JsonDeserializer<ItemTransformsBridge> {
      public ItemTransformsBridge method1(JsonElement var1, java.lang.reflect.Type var2, JsonDeserializationContext var3) {
         JsonObject var4 = var1.getAsJsonObject().get("display").getAsJsonObject();
         ItemTransformVec3fBridge var5 = this.method2(var3, var4, "thirdperson_righthand");
         ItemTransformVec3fBridge var6 = this.method2(var3, var4, "thirdperson_lefthand");
         if (var6 == ItemTransformVec3fBridge.field1) {
            var6 = var5;
         }

         ItemTransformVec3fBridge var7 = this.method2(var3, var4, "firstperson_righthand");
         ItemTransformVec3fBridge var8 = this.method2(var3, var4, "firstperson_lefthand");
         if (var8 == ItemTransformVec3fBridge.field1) {
            var8 = var7;
         }

         ItemTransformVec3fBridge var9 = this.method2(var3, var4, "head");
         ItemTransformVec3fBridge var10 = this.method2(var3, var4, "gui");
         ItemTransformVec3fBridge var11 = this.method2(var3, var4, "ground");
         ItemTransformVec3fBridge var12 = this.method2(var3, var4, "fixed");
         ItemTransformVec3fBridge var13 = this.method2(var3, var4, "on_shelf");
         return new ItemTransformsBridge(var6, var5, var8, var7, var9, var10, var11, var12, var13);
      }

      public ItemTransformVec3fBridge method2(JsonDeserializationContext var1, JsonObject var2, String var3) {
         return var2.has(var3) ? (ItemTransformVec3fBridge)var1.deserialize(var2.get(var3), ItemTransformVec3fBridge.class) : ItemTransformVec3fBridge.field1;
      }
   }

   public enum Type {
      NONE,
      THIRD_PERSON_LEFT_HAND,
      THIRD_PERSON_RIGHT_HAND,
      FIRST_PERSON_LEFT_HAND,
      FIRST_PERSON_RIGHT_HAND,
      HEAD,
      GUI,
      GROUND,
      FIXED,
      ON_SHELF;

      public static final ItemTransformsBridge.Type[] VALUES = values();

      public boolean firstPerson() {
         return this == FIRST_PERSON_LEFT_HAND || this == FIRST_PERSON_RIGHT_HAND;
      }

      public boolean thirdPerson() {
         return this == THIRD_PERSON_LEFT_HAND || this == THIRD_PERSON_RIGHT_HAND;
      }

      public boolean rightHand() {
         return this == FIRST_PERSON_RIGHT_HAND || this == THIRD_PERSON_RIGHT_HAND;
      }

      public boolean leftHand() {
         return this == FIRST_PERSON_LEFT_HAND || this == THIRD_PERSON_LEFT_HAND;
      }

      public static ItemTransformsBridge.Type firstPerson(boolean var0) {
         return var0 ? FIRST_PERSON_LEFT_HAND : FIRST_PERSON_RIGHT_HAND;
      }

      public static ItemTransformsBridge.Type thirdPerson(boolean var0) {
         return var0 ? THIRD_PERSON_LEFT_HAND : THIRD_PERSON_RIGHT_HAND;
      }
   }
}
