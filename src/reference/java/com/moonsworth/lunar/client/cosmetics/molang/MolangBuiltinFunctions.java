package com.moonsworth.lunar.client.cosmetics.molang;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MolangBuiltinFunctions {
   private static Map<String, MolangBuiltin> methods = new HashMap<>();

   public MolangBuiltinFunctions() {
   }

   public static void method1(MolangScope fps110) {
      for (Entry entry2 : methods.entrySet()) {
         fps110.method2((String)entry2.getKey(), (MolangSymbol)entry2.getValue());
      }
   }

   static {
      methods.put("math.min", new MolangMathMin());
      methods.put("math.max", new MolangMathMax());
      methods.put("math.cos", new MolangMathCos());
      methods.put("math.cosradians", new MolangMathCosRadians());
      methods.put("math.sin", new MolangMathSin());
      methods.put("math.sinradians", new MolangMathSinRadians());
      methods.put("math.tan", new MolangMathTan());
      methods.put("math.tanradians", new MolangMathTanRadians());
      methods.put("math.pow", new MolangMathPow());
      methods.put("math.clamp", new MolangMathClamp());
      methods.put("math.exp", new MolangMathExp());
      methods.put("math.lerp", new MolangMathLerp());
      methods.put("math.round", new MolangMathRound());
      methods.put("math.floor", new MolangMathFloor());
      methods.put("math.sqrt", new MolangMathSqrt());
      methods.put("math.mod", new MolangMathMod());
      methods.put("math.abs", new MolangMathAbs());
      methods.put("math.sign", new MolangMathSign());
      methods.put("math.asin", new MolangMathAsin());
      methods.put("math.acos", new MolangMathAcos());
      methods.put("math.atan", new MolangMathAtan());
      methods.put("math.atan2", new MolangMathAtan2());
      methods.put("math.ln", new MolangMathLn());
      methods.put("math.ceil", new MolangMathCeil());
      methods.put("math.trunc", new MolangMathTrunc());
      methods.put("math.lerprotate", new MolangMathLerpRotate());
      methods.put("math.min_angle", new MolangMathMinAngle());
      methods.put("math.random", new MolangMathRandom());
      methods.put("math.random_integer", new MolangMathRandomInteger());
      methods.put("math.dice_roll", new MolangMathDiceRoll());
      methods.put("math.dice_roll_integer", new MolangMathDiceRollInteger());
   }
}
