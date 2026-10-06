package com.moonsworth.lunar.network;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MixinHelper63 extends MixinHelper6 implements Serializable {
   private static final Logger field3 = Logger.getLogger(MixinHelper63.class.getName());
   public static final Map<String, Class<?>> field4 = new HashMap<>();

   public MixinHelper63() {
      super("oneOf", Boolean.FALSE);
   }

   public MixinHelper63(Object var1) {
      super("oneOf", Boolean.FALSE);
      this.setInstance(var1);
   }

   @Override
   public Map<String, Class<?>> method1() {
      return field4;
   }

   @Override
   public void setInstance(Object var1) {
      if (var1 instanceof GameRewindLayerAudio) {
         super.setInstance(var1);
      } else if (var1 instanceof GameRewindLayerGameplay) {
         super.setInstance(var1);
      } else if (var1 instanceof GameRewindLayerEffect) {
         super.setInstance(var1);
      } else {
         throw new RuntimeException("Invalid instance type. Must be GameRewindLayerAudio, GameRewindLayerEffect, GameRewindLayerGameplay");
      }
   }

   @Override
   public Object getInstance() {
      return super.getInstance();
   }

   public GameRewindLayerAudio method5() {
      return (GameRewindLayerAudio)super.getInstance();
   }

   public GameRewindLayerGameplay method7() {
      return (GameRewindLayerGameplay)super.getInstance();
   }

   public GameRewindLayerEffect method9() {
      return (GameRewindLayerEffect)super.getInstance();
   }

   public static void validateJsonElement(JsonElement var0) {
      int var1 = 0;
      ArrayList var2 = new ArrayList();

      try {
         GameRewindLayerAudio.validateJsonElement(var0);
         var1++;
      } catch (Exception var6) {
         var2.add(String.format("Deserialization for GameRewindLayerAudio failed with `%s`.", var6.getMessage()));
      }

      try {
         GameRewindLayerGameplay.validateJsonElement(var0);
         var1++;
      } catch (Exception var5) {
         var2.add(String.format("Deserialization for GameRewindLayerGameplay failed with `%s`.", var5.getMessage()));
      }

      try {
         GameRewindLayerEffect.validateJsonElement(var0);
         var1++;
      } catch (Exception var4) {
         var2.add(String.format("Deserialization for GameRewindLayerEffect failed with `%s`.", var4.getMessage()));
      }

      if (var1 != 1) {
         throw new IOException(
            String.format(
               "The JSON string is invalid for GameRewindLayer with oneOf schemas: GameRewindLayerAudio, GameRewindLayerEffect, GameRewindLayerGameplay. %d class(es) match the result, expected 1. Detailed failure message for oneOf schemas: %s. JSON: %s",
               var1,
               var2,
               var0.toString()
            )
         );
      }
   }

   public static MixinHelper63 method7(String var0) {
      return (MixinHelper63)com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().fromJson(var0, MixinHelper63.class);
   }

   public String method10() {
      return com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().toJson(this);
   }

   static {
      field4.put("GameRewindLayerAudio", GameRewindLayerAudio.class);
      field4.put("GameRewindLayerGameplay", GameRewindLayerGameplay.class);
      field4.put("GameRewindLayerEffect", GameRewindLayerEffect.class);
   }

   public static class Data implements TypeAdapterFactory {
      public <T> TypeAdapter<T> create(Gson var1, TypeToken<T> var2) {
         if (!MixinHelper63.class.isAssignableFrom(var2.getRawType())) {
            return null;
         }

         final TypeAdapter var3 = var1.getAdapter(JsonElement.class);
         final TypeAdapter var4 = var1.getDelegateAdapter(this, TypeToken.get(GameRewindLayerAudio.class));
         final TypeAdapter var5 = var1.getDelegateAdapter(this, TypeToken.get(GameRewindLayerGameplay.class));
         final TypeAdapter var6 = var1.getDelegateAdapter(this, TypeToken.get(GameRewindLayerEffect.class));
         return (new TypeAdapter<MixinHelper63>() {
               public void method1(JsonWriter var1, MixinHelper63 var2x) {
                  if (var2x == null || var2x.getInstance() == null) {
                     var3.write(var1, null);
                  } else if (var2x.getInstance() instanceof GameRewindLayerAudio) {
                     JsonElement var5x = var4.toJsonTree((GameRewindLayerAudio)var2x.getInstance());
                     var3.write(var1, var5x);
                  } else if (var2x.getInstance() instanceof GameRewindLayerGameplay) {
                     JsonElement var4x = var5.toJsonTree((GameRewindLayerGameplay)var2x.getInstance());
                     var3.write(var1, var4x);
                  } else if (var2x.getInstance() instanceof GameRewindLayerEffect) {
                     JsonElement var3x = var6.toJsonTree((GameRewindLayerEffect)var2x.getInstance());
                     var3.write(var1, var3x);
                  } else {
                     throw new IOException(
                        "Failed to serialize as the type doesn't match oneOf schemas: GameRewindLayerAudio, GameRewindLayerEffect, GameRewindLayerGameplay"
                     );
                  }
               }

               public MixinHelper63 method2(JsonReader var1) {
                  Object var2x = null;
                  JsonElement var3x = (JsonElement)var3.read(var1);
                  int var4x = 0;
                  ArrayList var5x = new ArrayList();
                  TypeAdapter var6x = var3;

                  try {
                     GameRewindLayerAudio.validateJsonElement(var3x);
                     var6x = var4;
                     var4x++;
                     MixinHelper63.field3.log(Level.FINER, "Input data matches schema 'GameRewindLayerAudio'");
                  } catch (Exception var10) {
                     var5x.add(String.format("Deserialization for GameRewindLayerAudio failed with `%s`.", var10.getMessage()));
                     MixinHelper63.field3.log(Level.FINER, "Input data does not match schema 'GameRewindLayerAudio'", var10);
                  }

                  try {
                     GameRewindLayerGameplay.validateJsonElement(var3x);
                     var6x = var5;
                     var4x++;
                     MixinHelper63.field3.log(Level.FINER, "Input data matches schema 'GameRewindLayerGameplay'");
                  } catch (Exception var9) {
                     var5x.add(String.format("Deserialization for GameRewindLayerGameplay failed with `%s`.", var9.getMessage()));
                     MixinHelper63.field3.log(Level.FINER, "Input data does not match schema 'GameRewindLayerGameplay'", var9);
                  }

                  try {
                     GameRewindLayerEffect.validateJsonElement(var3x);
                     var6x = var6;
                     var4x++;
                     MixinHelper63.field3.log(Level.FINER, "Input data matches schema 'GameRewindLayerEffect'");
                  } catch (Exception var8) {
                     var5x.add(String.format("Deserialization for GameRewindLayerEffect failed with `%s`.", var8.getMessage()));
                     MixinHelper63.field3.log(Level.FINER, "Input data does not match schema 'GameRewindLayerEffect'", var8);
                  }

                  if (var4x == 1) {
                     MixinHelper63 var7 = new MixinHelper63();
                     var7.setInstance(var6x.fromJsonTree(var3x));
                     return var7;
                  } else {
                     throw new IOException(
                        String.format(
                           "Failed deserialization for GameRewindLayer: %d classes match result, expected 1. Detailed failure message for oneOf schemas: %s. JSON: %s",
                           var4x,
                           var5x,
                           var3x.toString()
                        )
                     );
                  }
               }
            })
            .nullSafe();
      }
   }
}
