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

public class MixinHelper62 extends MixinHelper6 implements Serializable {
   private static final Logger field3 = Logger.getLogger(MixinHelper62.class.getName());
   public static final Map<String, Class<?>> field4 = new HashMap<>();

   public MixinHelper62() {
      super("anyOf", Boolean.FALSE);
   }

   public MixinHelper62(Object var1) {
      super("anyOf", Boolean.FALSE);
      this.setInstance(var1);
   }

   @Override
   public Map<String, Class<?>> method1() {
      return field4;
   }

   @Override
   public void setInstance(Object var1) {
      if (var1 instanceof NetworkIterator) {
         super.setInstance(var1);
      } else if (var1 instanceof GamePromotionInteractionEvent) {
         super.setInstance(var1);
      } else if (var1 instanceof GameRewindEditorSessionEvent) {
         super.setInstance(var1);
      } else if (var1 instanceof GameRewindProjectExportEvent) {
         super.setInstance(var1);
      } else if (var1 instanceof GameRewindRecordingEvent) {
         super.setInstance(var1);
      } else if (var1 instanceof GameRewindLayerAddEvent) {
         super.setInstance(var1);
      } else if (var1 instanceof MixinHelper4) {
         super.setInstance(var1);
      } else {
         throw new RuntimeException(
            "Invalid instance type. Must be GameBlogPostInteractionEvent, GameFailedParseEvent, GamePromotionInteractionEvent, GameRewindEditorSessionEvent, GameRewindLayerAddEvent, GameRewindProjectExportEvent, GameRewindRecordingEvent"
         );
      }
   }

   @Override
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

   public MixinHelper4 method13() {
      return (MixinHelper4)super.getInstance();
   }

   public static void validateJsonElement(JsonElement var0) {
      ArrayList var1 = new ArrayList();

      try {
         NetworkIterator.validateJsonElement(var0);
      } catch (Exception var9) {
         var1.add(String.format("Deserialization for GameBlogPostInteractionEvent failed with `%s`.", var9.getMessage()));

         try {
            GamePromotionInteractionEvent.validateJsonElement(var0);
         } catch (Exception var8) {
            var1.add(String.format("Deserialization for GamePromotionInteractionEvent failed with `%s`.", var8.getMessage()));

            try {
               GameRewindEditorSessionEvent.validateJsonElement(var0);
            } catch (Exception var7) {
               var1.add(String.format("Deserialization for GameRewindEditorSessionEvent failed with `%s`.", var7.getMessage()));

               try {
                  GameRewindProjectExportEvent.validateJsonElement(var0);
               } catch (Exception var6) {
                  var1.add(String.format("Deserialization for GameRewindProjectExportEvent failed with `%s`.", var6.getMessage()));

                  try {
                     GameRewindRecordingEvent.validateJsonElement(var0);
                  } catch (Exception var5) {
                     var1.add(String.format("Deserialization for GameRewindRecordingEvent failed with `%s`.", var5.getMessage()));

                     try {
                        GameRewindLayerAddEvent.validateJsonElement(var0);
                     } catch (Exception var4) {
                        var1.add(String.format("Deserialization for GameRewindLayerAddEvent failed with `%s`.", var4.getMessage()));

                        try {
                           MixinHelper4.validateJsonElement(var0);
                        } catch (Exception var3) {
                           var1.add(String.format("Deserialization for GameFailedParseEvent failed with `%s`.", var3.getMessage()));
                           throw new IOException(
                              String.format(
                                 "The JSON string is invalid for GameBatchEvent with anyOf schemas: GameBlogPostInteractionEvent, GameFailedParseEvent, GamePromotionInteractionEvent, GameRewindEditorSessionEvent, GameRewindLayerAddEvent, GameRewindProjectExportEvent, GameRewindRecordingEvent. no class match the result, expected at least 1. Detailed failure message for anyOf schemas: %s. JSON: %s",
                                 var1,
                                 var0.toString()
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

   public static MixinHelper62 method11(String var0) {
      return (MixinHelper62)com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().fromJson(var0, MixinHelper62.class);
   }

   public String method14() {
      return com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().toJson(this);
   }

   static {
      field4.put("GameBlogPostInteractionEvent", NetworkIterator.class);
      field4.put("GamePromotionInteractionEvent", GamePromotionInteractionEvent.class);
      field4.put("GameRewindEditorSessionEvent", GameRewindEditorSessionEvent.class);
      field4.put("GameRewindProjectExportEvent", GameRewindProjectExportEvent.class);
      field4.put("GameRewindRecordingEvent", GameRewindRecordingEvent.class);
      field4.put("GameRewindLayerAddEvent", GameRewindLayerAddEvent.class);
      field4.put("GameFailedParseEvent", MixinHelper4.class);
   }

   public static class Data implements TypeAdapterFactory {
      public <T> TypeAdapter<T> create(Gson var1, TypeToken<T> var2) {
         if (!MixinHelper62.class.isAssignableFrom(var2.getRawType())) {
            return null;
         }

         final TypeAdapter var3 = var1.getAdapter(JsonElement.class);
         final TypeAdapter var4 = var1.getDelegateAdapter(this, TypeToken.get(NetworkIterator.class));
         final TypeAdapter var5 = var1.getDelegateAdapter(this, TypeToken.get(GamePromotionInteractionEvent.class));
         final TypeAdapter var6 = var1.getDelegateAdapter(this, TypeToken.get(GameRewindEditorSessionEvent.class));
         final TypeAdapter var7 = var1.getDelegateAdapter(this, TypeToken.get(GameRewindProjectExportEvent.class));
         final TypeAdapter var8 = var1.getDelegateAdapter(this, TypeToken.get(GameRewindRecordingEvent.class));
         final TypeAdapter var9 = var1.getDelegateAdapter(this, TypeToken.get(GameRewindLayerAddEvent.class));
         final TypeAdapter var10 = var1.getDelegateAdapter(this, TypeToken.get(MixinHelper4.class));
         return (new TypeAdapter<MixinHelper62>() {
               public void method1(JsonWriter var1, MixinHelper62 var2x) {
                  if (var2x == null || var2x.getInstance() == null) {
                     var3.write(var1, null);
                  } else if (var2x.getInstance() instanceof NetworkIterator) {
                     JsonElement var9x = var4.toJsonTree((NetworkIterator)var2x.getInstance());
                     var3.write(var1, var9x);
                  } else if (var2x.getInstance() instanceof GamePromotionInteractionEvent) {
                     JsonElement var8x = var5.toJsonTree((GamePromotionInteractionEvent)var2x.getInstance());
                     var3.write(var1, var8x);
                  } else if (var2x.getInstance() instanceof GameRewindEditorSessionEvent) {
                     JsonElement var7x = var6.toJsonTree((GameRewindEditorSessionEvent)var2x.getInstance());
                     var3.write(var1, var7x);
                  } else if (var2x.getInstance() instanceof GameRewindProjectExportEvent) {
                     JsonElement var6x = var7.toJsonTree((GameRewindProjectExportEvent)var2x.getInstance());
                     var3.write(var1, var6x);
                  } else if (var2x.getInstance() instanceof GameRewindRecordingEvent) {
                     JsonElement var5x = var8.toJsonTree((GameRewindRecordingEvent)var2x.getInstance());
                     var3.write(var1, var5x);
                  } else if (var2x.getInstance() instanceof GameRewindLayerAddEvent) {
                     JsonElement var4x = var9.toJsonTree((GameRewindLayerAddEvent)var2x.getInstance());
                     var3.write(var1, var4x);
                  } else if (var2x.getInstance() instanceof MixinHelper4) {
                     JsonElement var3x = var10.toJsonTree((MixinHelper4)var2x.getInstance());
                     var3.write(var1, var3x);
                  } else {
                     throw new IOException(
                        "Failed to serialize as the type doesn't match anyOf schemas: GameBlogPostInteractionEvent, GameFailedParseEvent, GamePromotionInteractionEvent, GameRewindEditorSessionEvent, GameRewindLayerAddEvent, GameRewindProjectExportEvent, GameRewindRecordingEvent"
                     );
                  }
               }

               public MixinHelper62 method2(JsonReader var1) {
                  Object var2x = null;
                  JsonElement var3x = (JsonElement)var3.read(var1);
                  ArrayList var4x = new ArrayList();
                  TypeAdapter var5x = var3;

                  try {
                     NetworkIterator.validateJsonElement(var3x);
                     var5x = var4;
                     MixinHelper62 var26 = new MixinHelper62();
                     var26.setInstance(var5x.fromJsonTree(var3x));
                     return var26;
                  } catch (Exception var13) {
                     var4x.add(String.format("Deserialization for GameBlogPostInteractionEvent failed with `%s`.", var13.getMessage()));
                     MixinHelper62.field3.log(Level.FINER, "Input data does not match schema 'GameBlogPostInteractionEvent'", var13);

                     try {
                        GamePromotionInteractionEvent.validateJsonElement(var3x);
                        var5x = var5;
                        MixinHelper62 var25 = new MixinHelper62();
                        var25.setInstance(var5x.fromJsonTree(var3x));
                        return var25;
                     } catch (Exception var12) {
                        var4x.add(String.format("Deserialization for GamePromotionInteractionEvent failed with `%s`.", var12.getMessage()));
                        MixinHelper62.field3.log(Level.FINER, "Input data does not match schema 'GamePromotionInteractionEvent'", var12);

                        try {
                           GameRewindEditorSessionEvent.validateJsonElement(var3x);
                           var5x = var6;
                           MixinHelper62 var24 = new MixinHelper62();
                           var24.setInstance(var5x.fromJsonTree(var3x));
                           return var24;
                        } catch (Exception var11) {
                           var4x.add(String.format("Deserialization for GameRewindEditorSessionEvent failed with `%s`.", var11.getMessage()));
                           MixinHelper62.field3.log(Level.FINER, "Input data does not match schema 'GameRewindEditorSessionEvent'", var11);

                           try {
                              GameRewindProjectExportEvent.validateJsonElement(var3x);
                              var5x = var7;
                              MixinHelper62 var23 = new MixinHelper62();
                              var23.setInstance(var5x.fromJsonTree(var3x));
                              return var23;
                           } catch (Exception var10x) {
                              var4x.add(String.format("Deserialization for GameRewindProjectExportEvent failed with `%s`.", var10x.getMessage()));
                              MixinHelper62.field3.log(Level.FINER, "Input data does not match schema 'GameRewindProjectExportEvent'", var10x);

                              try {
                                 GameRewindRecordingEvent.validateJsonElement(var3x);
                                 var5x = var8;
                                 MixinHelper62 var22 = new MixinHelper62();
                                 var22.setInstance(var5x.fromJsonTree(var3x));
                                 return var22;
                              } catch (Exception var9x) {
                                 var4x.add(String.format("Deserialization for GameRewindRecordingEvent failed with `%s`.", var9x.getMessage()));
                                 MixinHelper62.field3.log(Level.FINER, "Input data does not match schema 'GameRewindRecordingEvent'", var9x);

                                 try {
                                    GameRewindLayerAddEvent.validateJsonElement(var3x);
                                    var5x = var9;
                                    MixinHelper62 var21 = new MixinHelper62();
                                    var21.setInstance(var5x.fromJsonTree(var3x));
                                    return var21;
                                 } catch (Exception var8x) {
                                    var4x.add(String.format("Deserialization for GameRewindLayerAddEvent failed with `%s`.", var8x.getMessage()));
                                    MixinHelper62.field3.log(Level.FINER, "Input data does not match schema 'GameRewindLayerAddEvent'", var8x);

                                    try {
                                       MixinHelper4.validateJsonElement(var3x);
                                       var5x = var10;
                                       MixinHelper62 var6x = new MixinHelper62();
                                       var6x.setInstance(var5x.fromJsonTree(var3x));
                                       return var6x;
                                    } catch (Exception var7x) {
                                       var4x.add(String.format("Deserialization for GameFailedParseEvent failed with `%s`.", var7x.getMessage()));
                                       MixinHelper62.field3.log(Level.FINER, "Input data does not match schema 'GameFailedParseEvent'", var7x);
                                       throw new IOException(
                                          String.format(
                                             "Failed deserialization for GameBatchEvent: no class matches result, expected at least 1. Detailed failure message for anyOf schemas: %s. JSON: %s",
                                             var4x,
                                             var3x.toString()
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
            })
            .nullSafe();
      }
   }
}
