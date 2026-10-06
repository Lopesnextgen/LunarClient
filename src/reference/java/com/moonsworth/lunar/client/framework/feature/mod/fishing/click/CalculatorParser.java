package com.moonsworth.lunar.client.framework.feature.mod.fishing.click;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.moonsworth.lunar.client.util.math.MathUtils;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.jetbrains.annotations.Nullable;

public final class CalculatorParser {
   private static final LoadingCache<String, Double> field1 = CacheBuilder.newBuilder()
      .expireAfterAccess(5L, TimeUnit.MINUTES)
      .softValues()
      .build(new CacheLoader<String, Double>() {
         public Double formatNumber(String text1) {
            return CalculatorParser.calculate(text1);
         }
      });
   private static final Pattern field2 = Pattern.compile("[ _,]");
   private static final Pattern field3 = Pattern.compile("[0-9.]");
   private static final Pattern field4 = Pattern.compile("[\\(\\)]");
   private static final Pattern ALLOWED_CHARACTERS_PATTERN = Pattern.compile("[\\(\\)0-9 _.,kmbtseKMBTSE+\\-*/x^%]");
   private static final HashMap<String, Double> SUFFIX_MULTIPLIERS = new HashMap<>();
   private static final DecimalFormat SCIENTIFIC_FORMAT = (DecimalFormat)DecimalFormat.getInstance(Locale.ROOT).clone();
   private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,##0.#");

   public static String formatNumber(double value0) {
      if (MathUtils.method18(value0, 0.0, 1.0E-8) || Math.abs(value0) >= 1.0E12) {
         return field7.format(value0);
      } else if (MathUtils.method18(value0 % 1.0, 0.0, 1.0E-8)) {
         char character2 = DecimalFormatSymbols.getInstance().getDecimalSeparator();
         return String.format("%,f", value0).split("\\" + character2)[0];
      } else {
         return String.format("%,f", value0).replaceAll("0+$", "");
      }
   }

   public static double calculateCached(String text0) {
      try {
         return (Double)field1.get(text0);
      } catch (ExecutionException executionexception2) {
         return Double.NaN;
      }
   }

   public static double calculate(String text0) {
      try {
         LinkedList list1 = new LinkedList();

         for (String text5 : text0.toLowerCase(Locale.ROOT).split("")) {
            if (!field2.matcher(text5).matches()) {
               list1.add(text5);
            }
         }

         Queue list7 = tokenize(list1);
         CalculatorParser.ExpressionNode data28 = parseExpression(list7);
         return data28.method3();
      } catch (Exception exception6) {
         return Double.NaN;
      }
   }

   public static Optional<Double> evaluate(String text0) {
      String text1 = field5.matcher(text0).replaceAll("");
      if (!text1.isEmpty()) {
         return Optional.empty();
      }

      double value2 = calculateCached(text0);
      return Double.isNaN(value2) ? Optional.empty() : Optional.of(value2);
   }

   @Nullable
   public static String formatExpression(String text0) {
      return formatResult(text0, false);
   }

   @Nullable
   public static String formatResult(String text0, boolean flag1) {
      double value2 = calculateCached(text0);
      if (!Double.isNaN(value2) && !Double.isInfinite(value2)) {
         String text4 = field8.format(BigDecimal.valueOf(value2));
         if (text4.length() > 30) {
            return null;
         } else {
            return flag1 && text4.equals(text0) ? null : text4;
         }
      } else {
         return null;
      }
   }

   public static TextComponent createResultComponent(String text0) {
      text0 = text0.replaceAll(" ", "");
      boolean flag1 = text0.endsWith("=");
      if (flag1) {
         text0 = text0.substring(0, text0.length() - 1);
      }

      Double value2 = evaluate(text0).orElse(null);
      if (value2 == null) {
         return Component.empty();
      }

      String text3 = formatNumber(value2);
      TextComponent text4 = Component.text(text3, NamedTextColor.GREEN);
      if (flag1) {
         text4 = (TextComponent)Component.space().append(text4);
      } else {
         text4 = (TextComponent)Component.text(" = ", NamedTextColor.GRAY).append(text4);
      }

      return text4;
   }

   private static Queue<CalculatorParser.Token> tokenize(Queue<String> list0) {
      LinkedList list1 = new LinkedList();
      CalculatorParser.Token data42 = null;

      while (!list0.isEmpty()) {
         String text3 = (String)list0.remove();
         char character4 = text3.charAt(0);
         if (field6.containsKey(text3)) {
            CalculatorParser.Token data49 = (CalculatorParser.Token)list1.removeLast();
            ArrayList list10 = new ArrayList();
            list10.add(data49);
            if (data49.method1() == CalculatorParser.Type.GROUP && data49.getValue().equals(")")) {
               int index7 = 1;

               while (index7 > 0 && !list1.isEmpty()) {
                  CalculatorParser.Token data48 = (CalculatorParser.Token)list1.removeLast();
                  list10.add(data48);
                  if (data48.method1() == CalculatorParser.Type.GROUP && data48.getValue().equals(")")) {
                     index7++;
                  }

                  if (data48.method1() == CalculatorParser.Type.GROUP && data48.getValue().equals("(")) {
                     index7--;
                  }
               }
            }

            Collections.reverse(list10);
            if (list1.peekLast() != null
               && ((CalculatorParser.Token)list1.peekLast()).method1() == CalculatorParser.Type.GROUP
               && ((CalculatorParser.Token)list1.peekLast()).getValue().equals(")")) {
               list1.add(new CalculatorParser.Token("*", CalculatorParser.Type.OPERATION));
            }

            list1.add(new CalculatorParser.Token("(", CalculatorParser.Type.GROUP));
            list1.addAll(list10);
            list1.add(new CalculatorParser.Token("*", CalculatorParser.Type.OPERATION));
            list1.add(new CalculatorParser.Token(field6.get(text3)));
            data42 = new CalculatorParser.Token(")", CalculatorParser.Type.GROUP);
            list1.add(data42);
         } else if (!field3.matcher(text3).matches()) {
            if (field4.matcher(text3).matches()) {
               if (data42 != null && data42.getValue().equals(")") && text3.equals("(")) {
                  list1.add(new CalculatorParser.Token("*", CalculatorParser.Type.OPERATION));
               }

               if (data42 != null && data42.method1() == CalculatorParser.Type.NUMBER && text3.equals("(")) {
                  list1.add(new CalculatorParser.Token("*", CalculatorParser.Type.OPERATION));
               }

               data42 = new CalculatorParser.Token(text3, CalculatorParser.Type.GROUP);
               list1.add(data42);
            } else {
               data42 = new CalculatorParser.Token(text3, CalculatorParser.Type.OPERATION);
               list1.add(data42);
            }
         } else {
            StringBuilder builder5 = new StringBuilder();
            builder5.append(character4);

            while (!list0.isEmpty() && field3.matcher((CharSequence)list0.peek()).matches()) {
               builder5.append((String)list0.remove());
            }

            String text6 = builder5.toString();
            if (data42 != null && data42.getValue().equals(")")) {
               list1.add(new CalculatorParser.Token("*", CalculatorParser.Type.OPERATION));
            }

            data42 = new CalculatorParser.Token(text6, CalculatorParser.Type.NUMBER);
            list1.add(data42);
         }
      }

      return list1;
   }

   private static CalculatorParser.ExpressionNode parseExpression(Queue<CalculatorParser.Token> list0) {
      return CalculatorParser.ExpressionNode.method1(list0);
   }

   @Generated
   private CalculatorParser() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   static {
      field6.put("k", 1000.0);
      field6.put("m", 1000000.0);
      field6.put("b", 1.0E9);
      field6.put("t", 1.0E12);
      field6.put("s", 64.0);
      field6.put("e", 160.0);
      field7.applyPattern("#.#####E0");
   }

   private static class Data extends CalculatorParser.ExpressionNode {
      public CalculatorParser.ExpressionNode field1 = null;
      public CalculatorParser.ExpressionNode field2 = null;
      public CalculatorParser.Token field3 = null;
      public boolean field4 = false;

      private Data() {
      }

      @Override
      public double calculate() {
         String text1 = this.DIGIT_PATTERN.getValue();
         if (this.cache == null) {
            if (!text1.equals("+") && !text1.equals("-")) {
               return Double.NaN;
            }

            this.cache = new CalculatorParser.NumberNode(0.0);
         }

         double value2 = this.cache.method3();
         double value4 = this.SEPARATOR_PATTERN.method3();

         return switch (text1) {
            case "+" -> value2 + value4;
            case "-" -> value2 - value4;
            case "*", "x" -> value2 * value4;
            case "/" -> value2 / value4;
            case "%" -> value2 % value4;
            case "^" -> Math.pow(value2, value4);
            default -> Double.NaN;
         };
      }

      public int getPriority() {
         return switch (this.DIGIT_PATTERN.getValue()) {
            case "+" -> 1;
            case "-" -> 1;
            case "*", "x" -> 2;
            case "/" -> 2;
            case "%" -> 2;
            case "^" -> 3;
            default -> 0;
         };
      }
   }

   private abstract static class ExpressionNode {
      private ExpressionNode() {
      }

      public static CalculatorParser.ExpressionNode formatNumber(Queue<CalculatorParser.Token> list0) {
         if (list0.isEmpty()) {
            return new CalculatorParser.NumberNode(Double.NaN);
         }

         ArrayList list1 = new ArrayList();

         while (!list0.isEmpty()) {
            list1.add(calculateCached(list0));
         }

         boolean flag2 = true;

         while (list1.size() > 1 && flag2) {
            int index3 = -1;
            int number4 = -1;

            for (int index5 = 0; index5 < list1.size(); index5++) {
               CalculatorParser.ExpressionNode data26 = (CalculatorParser.ExpressionNode)list1.get(index5);
               if (data26 instanceof CalculatorParser.Data data7) {
                  int number8 = data7.getPriority();
                  if (index5 == 0 || list1.get(index5 - 1) instanceof CalculatorParser.Data && !((CalculatorParser.Data)list1.get(index5 - 1)).field4) {
                     number8 = 4;
                  }

                  if (number8 > number4 && !data7.field4) {
                     index3 = index5;
                     number4 = number8;
                  }
               }
            }

            flag2 = index3 != -1;
            if (flag2) {
               CalculatorParser.Data data9 = (CalculatorParser.Data)list1.get(index3);
               if (index3 <= list1.size() - 2) {
                  data9.field2 = (CalculatorParser.ExpressionNode)list1.get(index3 + 1);
                  list1.remove(index3 + 1);
               }

               if (index3 >= 1) {
                  data9.field1 = (CalculatorParser.ExpressionNode)list1.get(index3 - 1);
                  if (data9.field1 instanceof CalculatorParser.Data && !((CalculatorParser.Data)data9.field1).field4) {
                     data9.field1 = null;
                  } else {
                     list1.remove(index3 - 1);
                  }
               }

               data9.field4 = true;
            }
         }

         return (CalculatorParser.ExpressionNode)list1.get(0);
      }

      private static CalculatorParser.ExpressionNode calculateCached(Queue<CalculatorParser.Token> list0) {
         CalculatorParser.Token data41 = (CalculatorParser.Token)list0.remove();
         switch (data41.method1()) {
            case OPERATION:
               CalculatorParser.Data data2 = new CalculatorParser.Data();
               data2.field3 = data41;
               return data2;
            case NUMBER:
               return new CalculatorParser.NumberNode(data41);
            case GROUP:
               LinkedList list3 = new LinkedList();
               int index4 = 1;

               while (index4 > 0) {
                  CalculatorParser.Token data45 = (CalculatorParser.Token)list0.remove();
                  if (data45.getValue().equals(")")) {
                     if (--index4 == 0) {
                        continue;
                     }
                  }

                  if (data45.getValue().equals("(")) {
                     index4++;
                  }

                  list3.add(data45);
               }

               return formatNumber(list3);
            default:
               return new CalculatorParser.NumberNode(Double.NaN);
         }
      }

      public abstract double calculate();
   }

   private static class NumberNode extends CalculatorParser.ExpressionNode {
      private final CalculatorParser.Token field1;

      public NumberNode(double value1) {
         this.cache = new CalculatorParser.Token(value1);
      }

      @Override
      public double calculate() {
         return this.cache.method2();
      }

      @Generated
      public NumberNode(CalculatorParser.Token data41) {
         this.cache = data41;
      }
   }

   private static class Token {
      private String value = "";
      private final CalculatorParser.Type field1;
      private Double field2 = Double.NaN;

      public Token(String text1, CalculatorParser.Type type2) {
         this.value = text1;
         this.cache = type2;
         if (this.cache == CalculatorParser.Type.NUMBER) {
            this.SEPARATOR_PATTERN = Double.parseDouble(text1);
         }
      }

      public Token(double value1) {
         this.cache = CalculatorParser.Type.NUMBER;
         this.SEPARATOR_PATTERN = value1;
      }

      @Generated
      public String getValue() {
         return this.value;
      }

      @Generated
      public CalculatorParser.Type formatNumber() {
         return this.cache;
      }

      @Generated
      public Double calculateCached() {
         return this.SEPARATOR_PATTERN;
      }
   }

   private enum Type {
      NUMBER,
      GROUP,
      OPERATION;

      Type() {
      }
   }
}
