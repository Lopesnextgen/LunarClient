package com.moonsworth.lunar.network;

import com.google.gson.JsonElement;
import com.moonsworth.lunar.network.mixin.MixinHelper7;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class GameBatchEvent extends AbstractOpenApiSchema implements Serializable {
   private static final Logger field3 = Logger.getLogger(GameBatchEvent.class.getName());
   public static final Map<String, Class<?>> field4 = new HashMap<>();

   public GameBatchEvent() {
      super("anyOf", Boolean.FALSE);
   }

   public GameBatchEvent(Object obj1) {
      super("anyOf", Boolean.FALSE);
      this.setInstance(obj1);
   }

   public Map<String, Class<?>> method1() {
      return field4;
   }

   public void setInstance(Object obj1) {
      if (obj1 instanceof NetworkIterator) {
         super.setInstance(obj1);
      } else if (obj1 instanceof GamePromotionInteractionEvent) {
         super.setInstance(obj1);
      } else if (obj1 instanceof GameRewindEditorSessionEvent) {
         super.setInstance(obj1);
      } else if (obj1 instanceof GameRewindProjectExportEvent) {
         super.setInstance(obj1);
      } else if (obj1 instanceof GameRewindRecordingEvent) {
         super.setInstance(obj1);
      } else if (obj1 instanceof GameRewindLayerAddEvent) {
         super.setInstance(obj1);
      } else if (obj1 instanceof GameFailedParseEvent) {
         super.setInstance(obj1);
      } else {
         throw new RuntimeException(
            "Invalid instance type. Must be GameBlogPostInteractionEvent, GameFailedParseEvent, GamePromotionInteractionEvent, GameRewindEditorSessionEvent, GameRewindLayerAddEvent, GameRewindProjectExportEvent, GameRewindRecordingEvent"
         );
      }
   }

   public Object getInstance() {
      return super.getInstance();
   }

   public NetworkIterator method5() {
      return (NetworkIterator)super.getInstance();
   }

   public GamePromotionInteractionEvent method7() {
      return (GamePromotionInteractionEvent)super.getInstance();
   }

   public GameRewindEditorSessionEvent method9() {
      return (GameRewindEditorSessionEvent)super.getInstance();
   }

   public GameRewindProjectExportEvent method10() {
      return (GameRewindProjectExportEvent)super.getInstance();
   }

   public GameRewindRecordingEvent method11() {
      return (GameRewindRecordingEvent)super.getInstance();
   }

   public GameRewindLayerAddEvent method12() {
      return (GameRewindLayerAddEvent)super.getInstance();
   }

   public GameFailedParseEvent method13() {
      return (GameFailedParseEvent)super.getInstance();
   }

   public static void validateJsonElement(JsonElement element0) {
      ArrayList list1 = new ArrayList();

      try {
         NetworkIterator.validateJsonElement(element0);
      } catch (Exception exception9) {
         list1.add(String.format("Deserialization for GameBlogPostInteractionEvent failed with `%s`.", exception9.getMessage()));

         try {
            GamePromotionInteractionEvent.validateJsonElement(element0);
         } catch (Exception exception8) {
            list1.add(String.format("Deserialization for GamePromotionInteractionEvent failed with `%s`.", exception8.getMessage()));

            try {
               GameRewindEditorSessionEvent.validateJsonElement(element0);
            } catch (Exception exception7) {
               list1.add(String.format("Deserialization for GameRewindEditorSessionEvent failed with `%s`.", exception7.getMessage()));

               try {
                  GameRewindProjectExportEvent.validateJsonElement(element0);
               } catch (Exception exception6) {
                  list1.add(String.format("Deserialization for GameRewindProjectExportEvent failed with `%s`.", exception6.getMessage()));

                  try {
                     GameRewindRecordingEvent.validateJsonElement(element0);
                  } catch (Exception exception5) {
                     list1.add(String.format("Deserialization for GameRewindRecordingEvent failed with `%s`.", exception5.getMessage()));

                     try {
                        GameRewindLayerAddEvent.validateJsonElement(element0);
                     } catch (Exception exception4) {
                        list1.add(String.format("Deserialization for GameRewindLayerAddEvent failed with `%s`.", exception4.getMessage()));

                        try {
                           GameFailedParseEvent.validateJsonElement(element0);
                        } catch (Exception exception3) {
                           list1.add(String.format("Deserialization for GameFailedParseEvent failed with `%s`.", exception3.getMessage()));
                           throw new IOException(
                              String.format(
                                 "The JSON string is invalid for GameBatchEvent with anyOf schemas: GameBlogPostInteractionEvent, GameFailedParseEvent, GamePromotionInteractionEvent, GameRewindEditorSessionEvent, GameRewindLayerAddEvent, GameRewindProjectExportEvent, GameRewindRecordingEvent. no class match the result, expected at least 1. Detailed failure message for anyOf schemas: %s. JSON: %s",
                                 list1,
                                 element0.toString()
                              )
                           );
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public static GameBatchEvent method11(String text0) {
      return (GameBatchEvent)MixinHelper7.getGson().fromJson(text0, GameBatchEvent.class);
   }

   public String method14() {
      return MixinHelper7.getGson().toJson(this);
   }

   static {
      field4.put("GameBlogPostInteractionEvent", NetworkIterator.class);
      field4.put("GamePromotionInteractionEvent", GamePromotionInteractionEvent.class);
      field4.put("GameRewindEditorSessionEvent", GameRewindEditorSessionEvent.class);
      field4.put("GameRewindProjectExportEvent", GameRewindProjectExportEvent.class);
      field4.put("GameRewindRecordingEvent", GameRewindRecordingEvent.class);
      field4.put("GameRewindLayerAddEvent", GameRewindLayerAddEvent.class);
      field4.put("GameFailedParseEvent", GameFailedParseEvent.class);
   }
}
