package com.moonsworth.lunar.client.cosmetics.gecko;

import com.eliotlash.molang.Molang;
import com.eliotlash.molang.ast.Evaluatable;
import com.eliotlash.molang.ast.EvaluatableExpr;
import com.eliotlash.molang.ast.EvaluatableStmt;
import com.eliotlash.molang.ast.Expr;
import com.eliotlash.molang.ast.Expr.Constant;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.cosmetics.molang.ast.ConstantEvaluatable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import org.apache.commons.lang3.math.NumberUtils;
import software.bernie.geckolib3.core.easing.EasingType;
import software.bernie.geckolib3.core.keyframe.KeyFrame;
import software.bernie.geckolib3.core.keyframe.VectorKeyFrameList;

public class AnimationKeyframeParser {
   public AnimationKeyframeParser() {
   }

   private static VectorKeyFrameList<KeyFrame<Evaluatable>> method1(List<Entry<String, JsonElement>> list0, boolean flag) {
      Object obj2 = null;
      Object obj3 = null;
      Object obj4 = null;
      ArrayList list5 = new ArrayList();
      ArrayList list6 = new ArrayList();
      ArrayList list7 = new ArrayList();

      for (int index8 = 0; index8 < list0.size(); index8++) {
         Entry entry9 = (Entry)list0.get(index8);
         if (!((String)entry9.getKey()).equals("easing") && !((String)entry9.getKey()).equals("easingArgs")) {
            Entry entry10 = index8 == 0 ? null : (Entry)list0.get(index8 - 1);
            Double value11 = entry10 == null ? 0.0 : Double.parseDouble((String)entry10.getKey());
            Double value12 = NumberUtils.isNumber((String)entry9.getKey()) ? Double.parseDouble((String)entry9.getKey()) : 0.0;
            Double value13 = value12 - value11;
            JsonArray array14 = method2((JsonElement)entry9.getValue());
            Evaluatable evaluatable15 = method9(array14.get(0));
            Evaluatable evaluatable16 = method9(array14.get(1));
            Evaluatable evaluatable17 = method9(array14.get(2));
            Object obj18 = flag && evaluatable15.isConstant() ? new ConstantEvaluatable(Math.toRadians(-evaluatable15.getConstant())) : evaluatable15;
            Object obj19 = flag && evaluatable16.isConstant() ? new ConstantEvaluatable(Math.toRadians(-evaluatable16.getConstant())) : evaluatable16;
            Object obj20 = flag && evaluatable17.isConstant() ? new ConstantEvaluatable(Math.toRadians(evaluatable17.getConstant())) : evaluatable17;
            KeyFrame keyframe21;
            KeyFrame keyframe22;
            KeyFrame keyframe23;
            if (((JsonElement)entry9.getValue()).isJsonObject() && method3((JsonElement)entry9.getValue())) {
               EasingType easingtype24 = method5((JsonElement)entry9.getValue());
               if (method4((JsonElement)entry9.getValue())) {
                  List list25 = method6((JsonElement)entry9.getValue());
                  keyframe21 = new KeyFrame(
                     com.moonsworth.lunar.client.cosmetics.gecko.TickConverter.method2(value13), index8 == 0 ? obj18 : obj2, obj18, easingtype24, list25
                  );
                  keyframe22 = new KeyFrame(
                     com.moonsworth.lunar.client.cosmetics.gecko.TickConverter.method2(value13), index8 == 0 ? obj19 : obj3, obj19, easingtype24, list25
                  );
                  keyframe23 = new KeyFrame(
                     com.moonsworth.lunar.client.cosmetics.gecko.TickConverter.method2(value13), index8 == 0 ? obj20 : obj4, obj20, easingtype24, list25
                  );
               } else {
                  keyframe21 = new KeyFrame(
                     com.moonsworth.lunar.client.cosmetics.gecko.TickConverter.method2(value13), index8 == 0 ? obj18 : obj2, obj18, easingtype24
                  );
                  keyframe22 = new KeyFrame(
                     com.moonsworth.lunar.client.cosmetics.gecko.TickConverter.method2(value13), index8 == 0 ? obj19 : obj3, obj19, easingtype24
                  );
                  keyframe23 = new KeyFrame(
                     com.moonsworth.lunar.client.cosmetics.gecko.TickConverter.method2(value13), index8 == 0 ? obj20 : obj4, obj20, easingtype24
                  );
               }
            } else {
               keyframe21 = new KeyFrame(com.moonsworth.lunar.client.cosmetics.gecko.TickConverter.method2(value13), index8 == 0 ? obj18 : obj2, obj18);
               keyframe22 = new KeyFrame(com.moonsworth.lunar.client.cosmetics.gecko.TickConverter.method2(value13), index8 == 0 ? obj19 : obj3, obj19);
               keyframe23 = new KeyFrame(com.moonsworth.lunar.client.cosmetics.gecko.TickConverter.method2(value13), index8 == 0 ? obj20 : obj4, obj20);
            }

            obj2 = obj18;
            obj3 = obj19;
            obj4 = obj20;
            list5.add(keyframe21);
            list6.add(keyframe22);
            list7.add(keyframe23);
         }
      }

      return new VectorKeyFrameList(list5, list6, list7);
   }

   private static JsonArray method2(JsonElement element0) {
      return element0.isJsonArray() ? element0.getAsJsonArray() : element0.getAsJsonObject().get("vector").getAsJsonArray();
   }

   private static boolean method3(JsonElement element0) {
      return element0.getAsJsonObject().has("easing");
   }

   private static boolean method4(JsonElement element0) {
      return element0.getAsJsonObject().has("easingArgs");
   }

   private static EasingType method5(JsonElement element0) {
      String text1 = element0.getAsJsonObject().get("easing").getAsString();

      try {
         String text2 = Character.toUpperCase(text1.charAt(0)) + text1.substring(1);
         return EasingType.valueOf(text2);
      } catch (Exception exception4) {
         LunarLogger.method7("Unknown easing type: {}", new Object[]{text1});
         throw new RuntimeException(exception4);
      }
   }

   private static List<Evaluatable> method6(JsonElement element0) {
      JsonObject json1 = element0.getAsJsonObject();
      JsonElement element2 = json1.get("easingArgs");
      JsonArray array3 = element2.getAsJsonArray();
      return AnimationJsonParser.method14(array3);
   }

   public static VectorKeyFrameList<KeyFrame<Evaluatable>> method7(List<Entry<String, JsonElement>> list0) {
      return method1(list0, false);
   }

   public static VectorKeyFrameList<KeyFrame<Evaluatable>> method8(List<Entry<String, JsonElement>> list0) {
      VectorKeyFrameList vectorkeyframelist1 = method1(list0, true);
      return new VectorKeyFrameList(vectorkeyframelist1.xKeyFrames, vectorkeyframelist1.yKeyFrames, vectorkeyframelist1.zKeyFrames);
   }

   public static Evaluatable method9(JsonElement element0) {
      return (Evaluatable)(element0.getAsJsonPrimitive().isString() ? method10(element0.getAsString()) : new ConstantEvaluatable(element0.getAsDouble()));
   }

   public static Evaluatable method10(String text) {
      if (!text.contains("\n") && !text.contains(";")) {
         Expr expr1 = Molang.parseExpression(text);
         return (Evaluatable)(expr1 instanceof Constant constant2 ? new ConstantEvaluatable(constant2.value()) : new EvaluatableExpr(expr1));
      } else {
         return new EvaluatableStmt(Molang.parse(text));
      }
   }
}
