package com.moonsworth.lunar.client.render.particle;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.moonsworth.lunar.client.render.particle.Constant;
import com.moonsworth.lunar.client.render.particle.Variable;
import com.moonsworth.lunar.client.render.particle.IValue;
import com.moonsworth.lunar.client.render.particle.MathBuilder;
import com.moonsworth.lunar.client.render.particle.MolangAssignment;
import com.moonsworth.lunar.client.render.particle.MolangValue;
import com.moonsworth.lunar.client.render.particle.MolangMultiStatement;
import com.moonsworth.lunar.client.render.particle.MolangExpression;
import java.util.ArrayList;
import java.util.List;

public class MolangParser extends MathBuilder {
   public static final MolangExpression field3 = new MolangValue(null, new Constant(0.0));
   public static final MolangExpression field4 = new MolangValue(null, new Constant(1.0));
   public static final String field5 = "return ";
   public boolean field6 = true;

   public MolangParser() {
      this.HIOIHOOCRCOIRCRRCOCRHICORHIHCI.put("cos", CosDegrees.class);
      this.HIOIHOOCRCOIRCRRCOCRHICORHIHCI.put("sin", SinDegrees.class);
      this.method1("abs", "math.abs");
      this.method1("ceil", "math.ceil");
      this.method1("clamp", "math.clamp");
      this.method1("cos", "math.cos");
      this.method1("exp", "math.exp");
      this.method1("floor", "math.floor");
      this.method1("lerp", "math.lerp");
      this.method1("lerprotate", "math.lerprotate");
      this.method1("ln", "math.ln");
      this.method1("max", "math.max");
      this.method1("min", "math.min");
      this.method1("mod", "math.mod");
      this.method1("pow", "math.pow");
      this.method1("random", "math.random");
      this.method1("round", "math.round");
      this.method1("sin", "math.sin");
      this.method1("sqrt", "math.sqrt");
      this.method1("trunc", "math.trunc");
      this.method1("hermite_blend", "math.hermite_blend");
   }

   public void method1(String text1, String text2) {
      this.HIOIHOOCRCOIRCRRCOCRHICORHIHCI.put(text2, (Class)this.HIOIHOOCRCOIRCRRCOCRHICORHIHCI.remove(text1));
   }

   public void method2(String text1, double value2) {
      Variable glintcolorizer2handler224 = this.method12(text1);
      if (glintcolorizer2handler224 != null) {
         glintcolorizer2handler224.set(value2);
      }
   }

   public IValue method4(String text1) {
      this.field6 = false;
      IValue glintcolorizer2_42 = super.method2(text1);
      this.field6 = true;
      return glintcolorizer2_42;
   }

   @Override
   protected Variable method12(String text1) {
      if (!this.HHCCROCRRCCOCHCOHIRHHIHHOCRRCH.containsKey(text1) && this.field6) {
         this.method1(new Variable(text1, 0.0));
      }

      return super.method12(text1);
   }

   public MolangExpression method5(JsonElement element1) {
      if (element1.isJsonPrimitive()) {
         JsonPrimitive json2 = element1.getAsJsonPrimitive();
         if (json2.isString()) {
            try {
               return new MolangValue(this, new Constant(Float.parseFloat(json2.getAsString())));
            } catch (Exception exception4) {
               return this.method6(json2.getAsString());
            }
         } else {
            return new MolangValue(this, new Constant(json2.getAsDouble()));
         }
      } else {
         return field3;
      }
   }

   public MolangExpression method6(String text1) {
      ArrayList list2 = new ArrayList();

      for (String text6 : text1.toLowerCase().trim().split(";")) {
         if (!text6.trim().isEmpty()) {
            list2.add(text6.trim());
         }
      }

      if (list2.size() == 0) {
         throw new MolangException("Molang expression cannot be blank!");
      }

      if (list2.size() == 1) {
         return this.method7((String)list2.get(0));
      }

      MolangMultiStatement glintcolorizeriterator7 = new MolangMultiStatement(this);

      for (String text9 : list2) {
         glintcolorizeriterator7.expressions.add(this.method7(text9));
      }

      return glintcolorizeriterator7;
   }

   protected MolangExpression method7(String text1) {
      if (text1.startsWith("return ")) {
         try {
            return new MolangValue(this, this.method2(text1.substring("return ".length()))).method1();
         } catch (Exception exception4) {
            throw new MolangException("Couldn't parse return '" + text1 + "' expression!");
         }
      } else {
         try {
            List list2 = this.method4(this.method3(text1));
            if (list2.size() >= 3 && list2.get(0) instanceof String && this.method14(list2.get(0)) && list2.get(1).equals("=")) {
               String text3 = (String)list2.get(0);
               list2 = list2.subList(2, list2.size());
               return new MolangAssignment(this, this.method12(text3), this.method8(list2));
            } else {
               return new MolangValue(this, this.method8(list2));
            }
         } catch (Exception exception5) {
            throw new MolangException("Couldn't parse '" + text1 + "' expression!");
         }
      }
   }

   private IValue method8(List<Object> list1) {
      try {
         return this.method6(list1);
      } catch (Exception exception3) {
         exception3.printStackTrace();
         throw new MolangException("Couldn't parse an expression!");
      }
   }

   @Override
   protected boolean method16(String text1) {
      return super.method16(text1) || text1.equals("=");
   }
}
