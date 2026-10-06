package com.moonsworth.lunar.network;

import com.google.gson.JsonElement;
import com.moonsworth.lunar.network.mixin.MixinHelper7;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class GameRewindLayer extends AbstractOpenApiSchema implements Serializable {
   private static final Logger field3 = Logger.getLogger(GameRewindLayer.class.getName());
   public static final Map<String, Class<?>> field4 = new HashMap<>();

   public GameRewindLayer() {
      super("oneOf", Boolean.FALSE);
   }

   public GameRewindLayer(Object obj1) {
      super("oneOf", Boolean.FALSE);
      this.setInstance(obj1);
   }

   public Map<String, Class<?>> method1() {
      return field4;
   }

   public void setInstance(Object obj1) {
      if (obj1 instanceof GameRewindLayerAudio) {
         super.setInstance(obj1);
      } else if (obj1 instanceof GameRewindLayerGameplay) {
         super.setInstance(obj1);
      } else if (obj1 instanceof GameRewindLayerEffect) {
         super.setInstance(obj1);
      } else {
         throw new RuntimeException("Invalid instance type. Must be GameRewindLayerAudio, GameRewindLayerEffect, GameRewindLayerGameplay");
      }
   }

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

   public static void validateJsonElement(JsonElement element0) {
      int index1 = 0;
      ArrayList list2 = new ArrayList();

      try {
         GameRewindLayerAudio.validateJsonElement(element0);
         index1++;
      } catch (Exception exception6) {
         list2.add(String.format("Deserialization for GameRewindLayerAudio failed with `%s`.", exception6.getMessage()));
      }

      try {
         GameRewindLayerGameplay.validateJsonElement(element0);
         index1++;
      } catch (Exception exception5) {
         list2.add(String.format("Deserialization for GameRewindLayerGameplay failed with `%s`.", exception5.getMessage()));
      }

      try {
         GameRewindLayerEffect.validateJsonElement(element0);
         index1++;
      } catch (Exception exception4) {
         list2.add(String.format("Deserialization for GameRewindLayerEffect failed with `%s`.", exception4.getMessage()));
      }

      if (index1 != 1) {
         throw new IOException(
            String.format(
               "The JSON string is invalid for GameRewindLayer with oneOf schemas: GameRewindLayerAudio, GameRewindLayerEffect, GameRewindLayerGameplay. %d class(es) match the result, expected 1. Detailed failure message for oneOf schemas: %s. JSON: %s",
               index1,
               list2,
               element0.toString()
            )
         );
      }
   }

   public static GameRewindLayer method7(String text0) {
      return (GameRewindLayer)MixinHelper7.getGson().fromJson(text0, GameRewindLayer.class);
   }

   public String method10() {
      return MixinHelper7.getGson().toJson(this);
   }

   static {
      field4.put("GameRewindLayerAudio", GameRewindLayerAudio.class);
      field4.put("GameRewindLayerGameplay", GameRewindLayerGameplay.class);
      field4.put("GameRewindLayerEffect", GameRewindLayerEffect.class);
   }
}
