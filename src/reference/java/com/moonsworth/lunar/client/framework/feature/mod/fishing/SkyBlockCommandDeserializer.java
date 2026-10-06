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

public class SkyBlockCommandDeserializer implements JsonDeserializer<SkyBlockCommandConfig> {
   public SkyBlockCommandDeserializer() {
   }

   public SkyBlockCommandConfig method1(JsonElement element1, Type type2, JsonDeserializationContext jsondeserializationcontext3) {
      if (!(element1 instanceof JsonObject json4)) {
         throw new JsonParseException(element1.toString());
      } else {
         LinkedHashSet set5 = new LinkedHashSet();

         for (Entry entry7 : json4.entrySet()) {
            set5.add(this.method2((String)entry7.getKey(), (JsonElement)entry7.getValue()));
         }

         this.method3(set5);
         return new SkyBlockCommandConfig(set5);
      }
   }

   private SkyBlockCommand method2(String text1, JsonElement element2) {
      SkyBlockCommandBuilder fishing$data3 = SkyBlockCommand.method2();
      fishing$data3.method1(text1);
      if (!(element2 instanceof JsonObject json4)) {
         throw new JsonParseException(element2.toString());
      } else {
         JsonElement element5 = json4.get("command");
         if (element5 instanceof JsonPrimitive json6) {
            if (!json6.isString()) {
               throw new JsonParseException("command is not a string");
            }

            fishing$data3.method2(json6.getAsString());
            JsonElement element7 = json4.get("allowsArguments");
            if (element7 != null) {
               if (!(element7 instanceof JsonPrimitive json8)) {
                  throw new JsonParseException(element7.toString());
               }

               if (!json8.isBoolean()) {
                  throw new JsonParseException("allowsArguments is not a boolean");
               }

               boolean flag9 = json8.getAsBoolean();
               fishing$data3.method3(flag9);
               boolean flag10 = false;
               JsonElement element11 = json4.get("anySubCommand");
               if (element11 != null) {
                  if (!(element11 instanceof JsonPrimitive json12)) {
                     throw new JsonParseException(element11.toString());
                  }

                  if (!json12.isBoolean()) {
                     throw new JsonParseException("anySubCommands is not a boolean");
                  }

                  flag10 = json12.getAsBoolean();
                  fishing$data3.method5(flag10);
               }

               if (flag9 && !flag10) {
                  JsonElement element17 = json4.get("subCommands");
                  if (element17 == null) {
                     throw new JsonParseException("subCommands is null while anySubCommands is false");
                  }

                  if (!(element17 instanceof JsonObject json13)) {
                     throw new JsonParseException(element17.toString());
                  }

                  LinkedHashSet set14 = new LinkedHashSet();

                  for (Entry entry16 : json13.entrySet()) {
                     set14.add(this.method2((String)entry16.getKey(), (JsonElement)entry16.getValue()));
                  }

                  fishing$data3.method6(set14);
               } else {
                  fishing$data3.method6(new LinkedHashSet<>());
                  fishing$data3.method7(new LinkedHashSet<>());
               }
            }

            return fishing$data3.method9();
         } else {
            throw new JsonParseException(element5.toString());
         }
      }
   }

   private void method3(Set<SkyBlockCommand> set1) {
      for (SkyBlockCommand fishing_23 : set1) {
         this.method4(fishing_23);
      }
   }

   private void method4(SkyBlockCommand fishing_21) {
      if (fishing_21.method3() && !fishing_21.method4()) {
         for (SkyBlockCommand fishing_23 : fishing_21.method5()) {
            fishing_23.method8(fishing_21);
            this.method4(fishing_23);
         }
      }
   }
}
