package com.moonsworth.lunar.client.cosmetics.gecko;

import com.eliotlash.molang.ParseException;
import com.eliotlash.molang.ast.Evaluatable;
import com.google.common.collect.ImmutableSet;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.collection.CachedValuesMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import org.json.JSONException;
import software.bernie.geckolib3.core.builder.Animation;
import software.bernie.geckolib3.core.keyframe.BoneAnimation;
import software.bernie.geckolib3.core.keyframe.EventKeyFrame;
import software.bernie.geckolib3.core.keyframe.ParticleEventKeyFrame;
import software.bernie.geckolib3.core.keyframe.VectorKeyFrameList;

public class AnimationJsonParser {
   public AnimationJsonParser() {
   }

   public static Set<Entry<String, JsonElement>> method1(JsonObject json0) {
      return method11(json0.getAsJsonObject("animations"));
   }

   public static List<Entry<String, JsonElement>> method2(JsonObject json0) {
      JsonObject json1 = json0.getAsJsonObject("bones");
      return json1 == null ? new ArrayList<>() : new ArrayList<>(method11(json1));
   }

   public static Set<Entry<String, JsonElement>> method3(JsonObject json0) {
      if (!json0.has("rotation")) {
         return ImmutableSet.of();
      } else {
         JsonElement element1 = json0.get("rotation");
         if (element1.isJsonArray()) {
            return ImmutableSet.of(new SimpleEntry<>("0", element1.getAsJsonArray()));
         } else if (element1.isJsonPrimitive()) {
            JsonPrimitive json2 = element1.getAsJsonPrimitive();
            Gson gson3 = new Gson();
            JsonElement element4 = gson3.toJsonTree(Arrays.asList(json2, json2, json2));
            return ImmutableSet.of(new SimpleEntry<>("0", element4));
         } else {
            return method11(element1.getAsJsonObject());
         }
      }
   }

   public static Set<Entry<String, JsonElement>> method4(JsonObject json0) {
      if (!json0.has("position")) {
         return ImmutableSet.of();
      } else {
         JsonElement element1 = json0.get("position");
         if (element1.isJsonArray()) {
            return ImmutableSet.of(new SimpleEntry<>("0", element1.getAsJsonArray()));
         } else if (element1.isJsonPrimitive()) {
            JsonPrimitive json2 = element1.getAsJsonPrimitive();
            Gson gson3 = new Gson();
            JsonElement element4 = gson3.toJsonTree(Arrays.asList(json2, json2, json2));
            return ImmutableSet.of(new SimpleEntry<>("0", element4));
         } else {
            return method11(element1.getAsJsonObject());
         }
      }
   }

   public static Set<Entry<String, JsonElement>> method5(JsonObject json0) {
      if (!json0.has("scale")) {
         return ImmutableSet.of();
      } else {
         JsonElement element1 = json0.get("scale");
         if (element1.isJsonArray()) {
            return ImmutableSet.of(new SimpleEntry<>("0", element1.getAsJsonArray()));
         } else if (element1.isJsonPrimitive()) {
            JsonPrimitive json2 = element1.getAsJsonPrimitive();
            Gson gson3 = new Gson();
            JsonElement element4 = gson3.toJsonTree(Arrays.asList(json2, json2, json2));
            return ImmutableSet.of(new SimpleEntry<>("0", element4));
         } else {
            return method11(element1.getAsJsonObject());
         }
      }
   }

   public static ArrayList<Entry<String, JsonElement>> method6(JsonObject json0) {
      JsonObject json1 = json0.getAsJsonObject("sound_effects");
      return json1 == null ? new ArrayList<>() : new ArrayList<>(method11(json1));
   }

   public static ArrayList<Entry<String, JsonElement>> method7(JsonObject json0) {
      JsonObject json1 = json0.getAsJsonObject("particle_effects");
      return json1 == null ? new ArrayList<>() : new ArrayList<>(method11(json1));
   }

   public static ArrayList<Entry<String, JsonElement>> method8(JsonObject json0) {
      JsonObject json1 = json0.getAsJsonObject("timeline");
      return json1 == null ? new ArrayList<>() : new ArrayList<>(method11(json1));
   }

   private static JsonElement method9(Set<Entry<String, JsonElement>> set0, String text1) {
      return (JsonElement)set0.stream()
         .filter(arg1x -> ((String)arg1x.getKey()).equals(text1))
         .findFirst()
         .orElseThrow(() -> new JSONException("Could not find key: " + text1))
         .getValue();
   }

   public static Entry<String, JsonElement> method10(JsonObject json0, String text1) {
      return new SimpleEntry<>(text1, method9(method1(json0), text1));
   }

   public static Set<Entry<String, JsonElement>> method11(JsonObject json0) {
      return json0.entrySet();
   }

   public static Animation method12(Entry<String, JsonElement> entry0, ResourceLocationBridge horsestats141) {
      Animation animation2 = new Animation();
      JsonObject json3 = ((JsonElement)entry0.getValue()).getAsJsonObject();
      animation2.animationName = (String)entry0.getKey();
      JsonElement element4 = json3.get("animation_length");
      animation2.animationLength = element4 == null ? null : com.moonsworth.lunar.client.cosmetics.gecko.TickConverter.method2(element4.getAsDouble());
      animation2.boneAnimations = new CachedValuesMap();
      JsonElement element5 = json3.get("loop");
      animation2.loop = element5 != null && element5.getAsBoolean();
      ArrayList list6 = method6(json3);
      if (list6 != null) {
         for (Entry entry8 : list6) {
            animation2.soundKeyFrames
               .add(
                  new EventKeyFrame(
                     Double.parseDouble((String)entry8.getKey()) * 20.0, ((JsonElement)entry8.getValue()).getAsJsonObject().get("effect").getAsString()
                  )
               );
         }
      }

      ArrayList list22 = method7(json3);
      if (list22 != null) {
         for (Entry entry9 : list22) {
            JsonObject json10 = ((JsonElement)entry9.getValue()).getAsJsonObject();
            JsonElement element11 = json10.get("effect");
            JsonElement element12 = json10.get("locator");
            JsonElement element13 = json10.get("pre_effect_script");
            animation2.particleKeyFrames
               .add(
                  new ParticleEventKeyFrame(
                     Double.parseDouble((String)entry9.getKey()) * 20.0,
                     element11 == null ? "" : element11.getAsString(),
                     element12 == null ? "" : element12.getAsString(),
                     element13 == null ? "" : element13.getAsString()
                  )
               );
         }
      }

      ArrayList list24 = method8(json3);
      if (list24 != null) {
         for (Entry entry27 : list24) {
            animation2.customInstructionKeyframes
               .add(new EventKeyFrame(Double.parseDouble((String)entry27.getKey()) * 20.0, AnimationKeyframeParser.method9((JsonElement)entry27.getValue())));
         }
      }

      boolean flag26 = false;
      if (Ref.method4().method40().method73().isValid()) {
         flag26 = (Boolean)Ref.method4().method40().method73().field32.get();
      }

      for (Entry entry30 : method2(json3)) {
         BoneAnimation boneanimation31 = new BoneAnimation();
         boneanimation31.boneName = (String)entry30.getKey();
         JsonObject json14 = ((JsonElement)entry30.getValue()).getAsJsonObject();

         try {
            Set set15 = method5(json14);
            boneanimation31.scaleKeyFrames = AnimationKeyframeParser.method7(new ArrayList<>(set15));
         } catch (ParseException parseexception20) {
            if (flag26) {
               LunarLogger.method7("Invalid molang in file %s at scale keyframe: %s", new Object[]{horsestats141.toString(), json14.get("rotation").toString()});
               parseexception20.printStackTrace();
            }

            boneanimation31.scaleKeyFrames = new VectorKeyFrameList();
         } catch (Exception exception21) {
            boneanimation31.scaleKeyFrames = new VectorKeyFrameList();
         }

         try {
            Set set32 = method4(json14);
            boneanimation31.positionKeyFrames = AnimationKeyframeParser.method7(new ArrayList<>(set32));
         } catch (ParseException parseexception18) {
            if (flag26) {
               LunarLogger.method7("Invalid molang in file %s at position keyframe: %s", new Object[]{horsestats141.toString(), json14.get("rotation").toString()});
               parseexception18.printStackTrace();
            }

            boneanimation31.positionKeyFrames = new VectorKeyFrameList();
         } catch (Exception exception19) {
            boneanimation31.positionKeyFrames = new VectorKeyFrameList();
         }

         try {
            Set set33 = method3(json14);
            boneanimation31.rotationKeyFrames = AnimationKeyframeParser.method8(new ArrayList<>(set33));
         } catch (ParseException parseexception16) {
            if (flag26) {
               LunarLogger.method7("Invalid molang in file %s at rotation keyframe: %s", new Object[]{horsestats141.toString(), json14.get("rotation").toString()});
               parseexception16.printStackTrace();
            }

            boneanimation31.rotationKeyFrames = new VectorKeyFrameList();
         } catch (Exception exception17) {
            boneanimation31.rotationKeyFrames = new VectorKeyFrameList();
         }

         animation2.boneAnimations.put(boneanimation31.boneName, boneanimation31);
      }

      if (animation2.animationLength == null) {
         animation2.animationLength = method13(animation2.boneAnimations.values());
      }

      return animation2;
   }

   private static double method13(Collection<BoneAnimation> list0) {
      double value1 = 0.0;

      for (BoneAnimation boneanimation4 : list0) {
         double value5 = boneanimation4.rotationKeyFrames.getLastKeyframeTime();
         double value7 = boneanimation4.positionKeyFrames.getLastKeyframeTime();
         double value9 = boneanimation4.scaleKeyFrames.getLastKeyframeTime();
         value1 = method15(value1, value5, value7, value9);
      }

      return value1 == 0.0 ? Double.MAX_VALUE : value1;
   }

   static List<Evaluatable> method14(JsonArray array0) {
      return (List<Evaluatable>)new Gson().fromJson(array0, ArrayList.class);
   }

   public static double method15(double... items0) {
      double value1 = 0.0;

      for (double value6 : items0) {
         value1 = Math.max(value6, value1);
      }

      return value1;
   }
}
