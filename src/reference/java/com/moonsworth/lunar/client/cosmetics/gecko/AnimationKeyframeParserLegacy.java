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
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.cosmetics.molang.ast.ConstantEvaluatable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import org.apache.commons.lang3.math.NumberUtils;
import software.bernie.geckolib3.core.easing.EasingType;
import software.bernie.geckolib3.core.keyframe.KeyFrame;
import software.bernie.geckolib3.core.keyframe.VectorKeyFrameList;
import com.moonsworth.lunar.client.cosmetics.gecko.AnimationJsonParser;

public class AnimationKeyframeParserLegacy {
   private static VectorKeyFrameList<KeyFrame<Evaluatable>> method1(List<Entry<String, JsonElement>> var0, boolean var1) {
      Object var2 = null;
      Object var3 = null;
      Object var4 = null;
      ArrayList var5 = new ArrayList();
      ArrayList var6 = new ArrayList();
      ArrayList var7 = new ArrayList();

      for (int var8 = 0; var8 < var0.size(); var8++) {
         Entry var9 = (Entry)var0.get(var8);
         if (!((String)var9.getKey()).equals("easing") && !((String)var9.getKey()).equals("easingArgs")) {
            Entry var10 = var8 == 0 ? null : (Entry)var0.get(var8 - 1);
            Double var11 = var10 == null ? 0.0 : Double.parseDouble((String)var10.getKey());
            Double var12 = NumberUtils.isNumber((String)var9.getKey()) ? Double.parseDouble((String)var9.getKey()) : 0.0;
            Double var13 = var12 - var11;
            JsonArray var14 = method2((JsonElement)var9.getValue());
            Evaluatable var15 = method9(var14.get(0));
            Evaluatable var16 = method9(var14.get(1));
            Evaluatable var17 = method9(var14.get(2));
            Object var18 = var1 && var15.isConstant() ? new ConstantEvaluatable(Math.toRadians(-var15.getConstant())) : var15;
            Object var19 = var1 && var16.isConstant() ? new ConstantEvaluatable(Math.toRadians(-var16.getConstant())) : var16;
            Object var20 = var1 && var17.isConstant() ? new ConstantEvaluatable(Math.toRadians(var17.getConstant())) : var17;
            KeyFrame var21;
            KeyFrame var22;
            KeyFrame var23;
            if (((JsonElement)var9.getValue()).isJsonObject() && method3((JsonElement)var9.getValue())) {
               EasingType var24 = method5((JsonElement)var9.getValue());
               if (method4((JsonElement)var9.getValue())) {
                  List var25 = method6((JsonElement)var9.getValue());
                  var21 = new KeyFrame(
                     com.moonsworth.lunar.client.cosmetics.gecko.TickConverter.method2(var13), var8 == 0 ? var18 : var2, var18, var24, var25
                  );
                  var22 = new KeyFrame(
                     com.moonsworth.lunar.client.cosmetics.gecko.TickConverter.method2(var13), var8 == 0 ? var19 : var3, var19, var24, var25
                  );
                  var23 = new KeyFrame(
                     com.moonsworth.lunar.client.cosmetics.gecko.TickConverter.method2(var13), var8 == 0 ? var20 : var4, var20, var24, var25
                  );
               } else {
                  var21 = new KeyFrame(
                     com.moonsworth.lunar.client.cosmetics.gecko.TickConverter.method2(var13), var8 == 0 ? var18 : var2, var18, var24
                  );
                  var22 = new KeyFrame(
                     com.moonsworth.lunar.client.cosmetics.gecko.TickConverter.method2(var13), var8 == 0 ? var19 : var3, var19, var24
                  );
                  var23 = new KeyFrame(
                     com.moonsworth.lunar.client.cosmetics.gecko.TickConverter.method2(var13), var8 == 0 ? var20 : var4, var20, var24
                  );
               }
            } else {
               var21 = new KeyFrame(com.moonsworth.lunar.client.cosmetics.gecko.TickConverter.method2(var13), var8 == 0 ? var18 : var2, var18);
               var22 = new KeyFrame(com.moonsworth.lunar.client.cosmetics.gecko.TickConverter.method2(var13), var8 == 0 ? var19 : var3, var19);
               var23 = new KeyFrame(com.moonsworth.lunar.client.cosmetics.gecko.TickConverter.method2(var13), var8 == 0 ? var20 : var4, var20);
            }

            var2 = var18;
            var3 = var19;
            var4 = var20;
            var5.add(var21);
            var6.add(var22);
            var7.add(var23);
         }
      }

      return new VectorKeyFrameList(var5, var6, var7);
   }

   private static JsonArray method2(JsonElement var0) {
      return var0.isJsonArray() ? var0.getAsJsonArray() : var0.getAsJsonObject().get("vector").getAsJsonArray();
   }

   private static boolean method3(JsonElement var0) {
      return var0.getAsJsonObject().has("easing");
   }

   private static boolean method4(JsonElement var0) {
      return var0.getAsJsonObject().has("easingArgs");
   }

   private static EasingType method5(JsonElement var0) {
      String var1 = var0.getAsJsonObject().get("easing").getAsString();

      try {
         String var2 = Character.toUpperCase(var1.charAt(0)) + var1.substring(1);
         return EasingType.valueOf(var2);
      } catch (Exception var4) {
         Slayer.method7("Unknown easing type: {}", new Object[]{var1});
         throw new RuntimeException(var4);
      }
   }

   private static List<Evaluatable> method6(JsonElement var0) {
      JsonObject var1 = var0.getAsJsonObject();
      JsonElement var2 = var1.get("easingArgs");
      JsonArray var3 = var2.getAsJsonArray();
      return AnimationJsonParser.method14(var3);
   }

   public static VectorKeyFrameList<KeyFrame<Evaluatable>> method7(List<Entry<String, JsonElement>> var0) {
      return method1(var0, false);
   }

   public static VectorKeyFrameList<KeyFrame<Evaluatable>> method8(List<Entry<String, JsonElement>> var0) {
      VectorKeyFrameList var1 = method1(var0, true);
      return new VectorKeyFrameList(var1.xKeyFrames, var1.yKeyFrames, var1.zKeyFrames);
   }

   public static Evaluatable method9(JsonElement var0) {
      return (Evaluatable)(var0.getAsJsonPrimitive().isString() ? method10(var0.getAsString()) : new ConstantEvaluatable(var0.getAsDouble()));
   }

   public static Evaluatable method10(String var0) {
      if (!var0.contains("\n") && !var0.contains(";")) {
         Expr var1 = Molang.parseExpression(var0);
         return (Evaluatable)(var1 instanceof Constant var2 ? new ConstantEvaluatable(var2.value()) : new EvaluatableExpr(var1));
      } else {
         return new EvaluatableStmt(Molang.parse(var0));
      }
   }
}
