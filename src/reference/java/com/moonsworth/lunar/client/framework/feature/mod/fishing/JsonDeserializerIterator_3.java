package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import java.lang.reflect.Type;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Map.Entry;

public class JsonDeserializerIterator_3 implements JsonDeserializer<JsonDeserializerIterator$Data> {
   public JsonDeserializerIterator$Data method1(JsonElement var1, Type var2, JsonDeserializationContext var3) {
      if (!(var1 instanceof JsonObject var4)) {
         throw new JsonParseException(var1.toString());
      } else {
         LinkedHashSet var5 = new LinkedHashSet();

         for (Entry var7 : var4.entrySet()) {
            var5.add(this.method2((String)var7.getKey(), (JsonElement)var7.getValue()));
         }

         this.method3(var5);
         return new JsonDeserializerIterator$Data(var5);
      }
   }

   private Fishing_2 method2(String var1, JsonElement var2) {
      Fishing$Data var3 = Fishing_2.method2();
      var3.method1(var1);
      if (!(var2 instanceof JsonObject var4)) {
         throw new JsonParseException(var2.toString());
      } else {
         JsonElement var5 = var4.get("command");
         if (var5 instanceof JsonPrimitive var6) {
            if (!var6.isString()) {
               throw new JsonParseException("command is not a string");
            }

            var3.method2(var6.getAsString());
            JsonElement var7 = var4.get("allowsArguments");
            if (var7 != null) {
               if (!(var7 instanceof JsonPrimitive var8)) {
                  throw new JsonParseException(var7.toString());
               }

               if (!var8.isBoolean()) {
                  throw new JsonParseException("allowsArguments is not a boolean");
               }

               boolean var9 = var8.getAsBoolean();
               var3.method3(var9);
               boolean var10 = false;
               JsonElement var11 = var4.get("anySubCommand");
               if (var11 != null) {
                  if (!(var11 instanceof JsonPrimitive var12)) {
                     throw new JsonParseException(var11.toString());
                  }

                  if (!var12.isBoolean()) {
                     throw new JsonParseException("anySubCommands is not a boolean");
                  }

                  var10 = var12.getAsBoolean();
                  var3.method5(var10);
               }

               if (var9 && !var10) {
                  JsonElement var17 = var4.get("subCommands");
                  if (var17 == null) {
                     throw new JsonParseException("subCommands is null while anySubCommands is false");
                  }

                  if (!(var17 instanceof JsonObject var13)) {
                     throw new JsonParseException(var17.toString());
                  }

                  LinkedHashSet var14 = new LinkedHashSet();

                  for (Entry var16 : var13.entrySet()) {
                     var14.add(this.method2((String)var16.getKey(), (JsonElement)var16.getValue()));
                  }

                  var3.method6(var14);
               } else {
                  var3.method6(new LinkedHashSet<>());
                  var3.method7(new LinkedHashSet<>());
               }
            }

            return var3.method9();
         } else {
            throw new JsonParseException(var5.toString());
         }
      }
   }

   private void method3(Set<Fishing_2> var1) {
      for (Fishing_2 var3 : var1) {
         this.method4(var3);
      }
   }

   private void method4(Fishing_2 var1) {
      if (var1.method3() && !var1.method4()) {
         for (Fishing_2 var3 : var1.method5()) {
            var3.method8(var1);
            this.method4(var3);
         }
      }
   }
}
