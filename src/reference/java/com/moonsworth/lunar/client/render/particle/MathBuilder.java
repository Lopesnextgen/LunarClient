package com.moonsworth.lunar.client.render.particle;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MathBuilder {
   public Map<String, Variable> field1 = new HashMap<>();
   public Map<String, Class<? extends MathFunction>> field2 = new HashMap<>();
   protected boolean strict = true;

   public MathBuilder() {
      this.method1(new Variable("PI", Math.PI));
      this.method1(new Variable("E", Math.E));
      this.field2.put("floor", Floor.class);
      this.field2.put("round", Round.class);
      this.field2.put("ceil", Ceil.class);
      this.field2.put("trunc", Trunc.class);
      this.field2.put("clamp", Clamp.class);
      this.field2.put("max", Max.class);
      this.field2.put("min", Min.class);
      this.field2.put("abs", Abs.class);
      this.field2.put("cos", Cos.class);
      this.field2.put("sin", Sin.class);
      this.field2.put("exp", Exp.class);
      this.field2.put("ln", Ln.class);
      this.field2.put("sqrt", Sqrt.class);
      this.field2.put("mod", Mod.class);
      this.field2.put("pow", Pow.class);
      this.field2.put("lerp", com.moonsworth.lunar.client.render.particle.Lerp.class);
      this.field2.put("lerprotate", com.moonsworth.lunar.client.render.particle.LerpRotate.class);
      this.field2.put("random", com.moonsworth.lunar.client.render.particle.RandomFunction.class);
      this.field2.put("hermite_blend", com.moonsworth.lunar.client.render.particle.HermiteBlend.class);
   }

   public void method1(Variable glintcolorizer2handler221) {
      this.field1.put(glintcolorizer2handler221.getName(), glintcolorizer2handler221);
   }

   public IValue method2(String text1) {
      return this.method6(this.method4(this.method3(text1)));
   }

   public String[] method3(String text1) {
      if (this.strict && !text1.matches("^[\\w\\d\\s_+-/*%^&|<>=!?:.,()\"'@~\\[\\]]+$")) {
         throw new Exception("Given expression '" + text1 + "' contains illegal characters!");
      }

      String[] items2 = text1.split("(?!^)");
      int index3 = 0;
      int index4 = 0;

      for (String text8 : items2) {
         if (text8.equals("(")) {
            index3++;
         } else if (text8.equals(")")) {
            index4++;
         }
      }

      if (index3 != index4) {
         throw new Exception("Given expression '" + text1 + "' has more uneven amount of parenthesis, there are " + index3 + " open and " + index4 + " closed!");
      } else {
         return items2;
      }
   }

   public List<Object> method4(String[] items1) {
      ArrayList list2 = new ArrayList();
      String text3 = "";
      int number4 = items1.length;
      boolean flag5 = false;

      for (int index6 = 0; index6 < number4; index6++) {
         String text7 = items1[index6];
         boolean flag8 = index6 < items1.length - 1 && this.method16(text7 + items1[index6 + 1]);
         if (text7.equals("\"")) {
            flag5 = !flag5;
         }

         if (flag5) {
            text3 = text3 + text7;
         } else if (!this.method16(text7) && !flag8 && !text7.equals(",")) {
            if (text7.equals("(")) {
               if (!text3.isEmpty()) {
                  list2.add(text3);
                  text3 = "";
               }

               int index13 = 1;

               for (int index14 = index6 + 1; index14 < number4; index14++) {
                  String text15 = items1[index14];
                  if (text15.equals("(")) {
                     index13++;
                  } else if (text15.equals(")")) {
                     index13--;
                  }

                  if (index13 == 0) {
                     list2.add(this.method4(text3.split("(?!^)")));
                     index6 = index14;
                     text3 = "";
                     break;
                  }

                  text3 = text3 + text15;
               }
            } else {
               text3 = text3 + text7;
            }
         } else {
            if (text7.equals("-")) {
               int index9 = list2.size();
               boolean flag10 = text3.trim().isEmpty();
               boolean flag11 = index9 == 0 && flag10;
               boolean flag12 = index9 > 0 && (this.method15(list2.get(index9 - 1)) || list2.get(index9 - 1).equals(",")) && flag10;
               if (flag11 || flag12) {
                  text3 = text3 + text7;
                  continue;
               }
            }

            if (!text3.isEmpty()) {
               list2.add(text3);
               text3 = "";
            }

            if (flag8) {
               list2.add(text7 + items1[index6 + 1]);
               index6++;
            } else {
               list2.add(text7);
            }
         }
      }

      if (!text3.isEmpty()) {
         list2.add(text3);
      }

      return this.method5(list2);
   }

   private List<Object> method5(List<Object> list1) {
      ArrayList list2 = new ArrayList();

      for (int index3 = 0; index3 < list1.size(); index3++) {
         Object obj4 = list1.get(index3);
         if (obj4 instanceof String) {
            String text5 = ((String)obj4).trim();
            if (!text5.isEmpty()) {
               list2.add(text5);
            }
         } else {
            list2.add(this.method5((List<Object>)obj4));
         }
      }

      return list2;
   }

   public IValue method6(List<Object> list1) {
      IValue glintcolorizer2_42 = this.method9(list1);
      if (glintcolorizer2_42 != null) {
         return glintcolorizer2_42;
      }

      int index3 = list1.size();
      if (index3 == 1) {
         return this.method11(list1.get(0));
      }

      if (index3 == 2) {
         Object obj4 = list1.get(0);
         Object obj5 = list1.get(1);
         if ((this.method14(obj4) || obj4.equals("-")) && obj5 instanceof List) {
            return this.method10((String)obj4, (List<Object>)obj5);
         }
      }

      int index12 = this.method7(list1);
      int index13 = index12;

      while (index13 != -1) {
         int index6 = this.method8(list1, index13 - 1);
         if (index6 != -1) {
            Operation glintcolorizertype7 = this.method13((String)list1.get(index6));
            Operation glintcolorizertype8 = this.method13((String)list1.get(index13));
            if (glintcolorizertype8.value > glintcolorizertype7.value) {
               IValue glintcolorizer2_415 = this.method6(list1.subList(0, index6));
               IValue glintcolorizer2_417 = this.method6(list1.subList(index6 + 1, index3));
               return new Operator(glintcolorizertype7, glintcolorizer2_415, glintcolorizer2_417);
            }

            if (glintcolorizertype7.value > glintcolorizertype8.value) {
               Operation glintcolorizertype9 = this.method13((String)list1.get(index12));
               if (glintcolorizertype9.value < glintcolorizertype7.value) {
                  IValue glintcolorizer2_416 = this.method6(list1.subList(0, index12));
                  IValue glintcolorizer2_418 = this.method6(list1.subList(index12 + 1, index3));
                  return new Operator(glintcolorizertype9, glintcolorizer2_416, glintcolorizer2_418);
               }

               IValue glintcolorizer2_410 = this.method6(list1.subList(0, index13));
               IValue glintcolorizer2_411 = this.method6(list1.subList(index13 + 1, index3));
               return new Operator(glintcolorizertype8, glintcolorizer2_410, glintcolorizer2_411);
            }
         }

         index13 = index6;
      }

      Operation glintcolorizertype14 = this.method13((String)list1.get(index12));
      return new Operator(glintcolorizertype14, this.method6(list1.subList(0, index12)), this.method6(list1.subList(index12 + 1, index3)));
   }

   protected int method7(List<Object> list1) {
      return this.method8(list1, list1.size() - 1);
   }

   protected int method8(List<Object> list1, int number2) {
      int index3 = number2;

      while (true) {
         if (index3 < 0) {
            return -1;
         }

         Object obj4 = list1.get(index3);
         if (this.method15(obj4)) {
            if (!obj4.equals("-")) {
               break;
            }

            Object obj5 = index3 < list1.size() - 1 ? list1.get(index3 + 1) : null;
            Object obj6 = index3 > 0 ? list1.get(index3 - 1) : null;
            if (!(obj5 instanceof List) || !this.method15(obj6) && obj6 != null) {
               break;
            }
         }

         index3--;
      }

      return index3;
   }

   protected IValue method9(List<Object> list1) {
      int index2 = -1;
      int index3 = 0;
      int index4 = -1;
      int index5 = 0;
      int index6 = list1.size();

      for (int index7 = 0; index7 < index6; index7++) {
         Object obj8 = list1.get(index7);
         if (obj8 instanceof String) {
            if (obj8.equals("?")) {
               if (index2 == -1) {
                  index2 = index7;
               }

               index3++;
            } else if (obj8.equals(":")) {
               if (index5 + 1 == index3 && index4 == -1) {
                  index4 = index7;
               }

               index5++;
            }
         }
      }

      return index3 == index5 && index2 > 0 && index2 + 1 < index4 && index4 < index6 - 1
         ? new Ternary(
            this.method6(list1.subList(0, index2)), this.method6(list1.subList(index2 + 1, index4)), this.method6(list1.subList(index4 + 1, index6))
         )
         : null;
   }

   protected IValue method10(String text1, List<Object> list2) {
      if (text1.equals("!")) {
         return new Negate(this.method6(list2));
      }

      if (text1.startsWith("!") && text1.length() > 1) {
         return new Negate(this.method10(text1.substring(1), list2));
      }

      if (text1.equals("-")) {
         return new Negative(new Group(this.method6(list2)));
      }

      if (text1.startsWith("-") && text1.length() > 1) {
         return new Negative(this.method10(text1.substring(1), list2));
      }

      if (!this.field2.containsKey(text1)) {
         throw new Exception("Function '" + text1 + "' couldn't be found!");
      }

      ArrayList list3 = new ArrayList();
      ArrayList list4 = new ArrayList();

      for (Object obj6 : list2) {
         if (obj6.equals(",")) {
            list3.add(this.method6(list4));
            list4.clear();
         } else {
            list4.add(obj6);
         }
      }

      if (!list4.isEmpty()) {
         list3.add(this.method6(list4));
      }

      Class clazz8 = this.field2.get(text1);
      Constructor constructor9 = clazz8.getConstructor(IValue[].class, String.class);
      return (MathFunction)constructor9.newInstance(list3.toArray(new IValue[list3.size()]), text1);
   }

   public IValue method11(Object obj1) {
      if (obj1 instanceof String text2) {
         if (text2.startsWith("!")) {
            return new Negate(this.method11(text2.substring(1)));
         }

         if (text2.startsWith("\"") && text2.endsWith("\"")) {
            return new Constant(text2.substring(1, text2.length() - 1));
         }

         if (this.method17(text2)) {
            return new Constant(Double.parseDouble(text2));
         }

         if (this.method14(text2)) {
            if (text2.startsWith("-")) {
               String text4 = text2.substring(1);
               Variable glintcolorizer2handler223 = this.method12(text4);
               if (glintcolorizer2handler223 != null) {
                  return new Negative(glintcolorizer2handler223);
               }
            } else {
               Variable glintcolorizer2handler225 = this.method12(text2);
               if (glintcolorizer2handler225 != null) {
                  return glintcolorizer2handler225;
               }
            }
         }
      } else if (obj1 instanceof List) {
         return new Group(this.method6((List<Object>)obj1));
      }

      throw new Exception("Given object couldn't be converted to value! " + obj1);
   }

   protected Variable method12(String text1) {
      return this.field1.get(text1);
   }

   protected Operation method13(String text1) {
      for (Operation glintcolorizertype5 : Operation.values()) {
         if (glintcolorizertype5.sign.equals(text1)) {
            return glintcolorizertype5;
         }
      }

      throw new Exception("There is no such operator '" + text1 + "'!");
   }

   protected boolean method14(Object obj1) {
      return obj1 instanceof String && !this.method17((String)obj1) && !this.method16((String)obj1);
   }

   protected boolean method15(Object obj1) {
      return obj1 instanceof String && this.method16((String)obj1);
   }

   protected boolean method16(String text1) {
      return Operation.OPERATORS.contains(text1) || text1.equals("?") || text1.equals(":");
   }

   protected boolean method17(String text1) {
      return text1.matches("^-?\\d+(\\.\\d+)?$");
   }
}
