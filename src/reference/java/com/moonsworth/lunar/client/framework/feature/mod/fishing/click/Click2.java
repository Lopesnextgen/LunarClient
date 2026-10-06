package com.moonsworth.lunar.client.framework.feature.mod.fishing.click;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.moonsworth.lunar.client.util.ThreadModuleDump67;
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

public final class Click2 {
   private static final LoadingCache<String, Double> field1 = CacheBuilder.newBuilder()
      .expireAfterAccess(5L, TimeUnit.MINUTES)
      .softValues()
      .build(new CacheLoader<String, Double>() {
         public Double formatNumber(String var1) {
            return Click2.calculate(var1);
         }
      });
   private static final Pattern field2 = Pattern.compile("[ _,]");
   private static final Pattern field3 = Pattern.compile("[0-9.]");
   private static final Pattern field4 = Pattern.compile("[\\(\\)]");
   private static final Pattern ALLOWED_CHARACTERS_PATTERN = Pattern.compile("[\\(\\)0-9 _.,kmbtseKMBTSE+\\-*/x^%]");
   private static final HashMap<String, Double> SUFFIX_MULTIPLIERS = new HashMap<>();
   private static final DecimalFormat SCIENTIFIC_FORMAT = (DecimalFormat)DecimalFormat.getInstance(Locale.ROOT).clone();
   private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,##0.#");

   public static String formatNumber(double var0) {
      if (ThreadModuleDump67.method18(var0, 0.0, 1.0E-8) || Math.abs(var0) >= 1.0E12) {
         return field7.format(var0);
      } else if (ThreadModuleDump67.method18(var0 % 1.0, 0.0, 1.0E-8)) {
         char var2 = DecimalFormatSymbols.getInstance().getDecimalSeparator();
         return String.format("%,f", var0).split("\\" + var2)[0];
      } else {
         return String.format("%,f", var0).replaceAll("0+$", "");
      }
   }

   public static double calculateCached(String var0) {
      try {
         return (Double)field1.get(var0);
      } catch (ExecutionException var2) {
         return Double.NaN;
      }
   }

   public static double calculate(String var0) {
      try {
         LinkedList var1 = new LinkedList();

         for (String var5 : var0.toLowerCase(Locale.ROOT).split("")) {
            if (!field2.matcher(var5).matches()) {
               var1.add(var5);
            }
         }

         Queue var7 = tokenize(var1);
         Click2.Data2 var8 = parseExpression(var7);
         return var8.method3();
      } catch (Exception var6) {
         return Double.NaN;
      }
   }

   public static Optional<Double> evaluate(String var0) {
      String var1 = field5.matcher(var0).replaceAll("");
      if (!var1.isEmpty()) {
         return Optional.empty();
      }

      double var2 = calculateCached(var0);
      return Double.isNaN(var2) ? Optional.empty() : Optional.of(var2);
   }

   @Nullable
   public static String formatExpression(String var0) {
      return formatResult(var0, false);
   }

   @Nullable
   public static String formatResult(String var0, boolean var1) {
      double var2 = calculateCached(var0);
      if (!Double.isNaN(var2) && !Double.isInfinite(var2)) {
         String var4 = field8.format(BigDecimal.valueOf(var2));
         if (var4.length() > 30) {
            return null;
         } else {
            return var1 && var4.equals(var0) ? null : var4;
         }
      } else {
         return null;
      }
   }

   public static TextComponent createResultComponent(String var0) {
      var0 = var0.replaceAll(" ", "");
      boolean var1 = var0.endsWith("=");
      if (var1) {
         var0 = var0.substring(0, var0.length() - 1);
      }

      Double var2 = evaluate(var0).orElse(null);
      if (var2 == null) {
         return Component.empty();
      }

      String var3 = formatNumber(var2);
      TextComponent var4 = Component.text(var3, NamedTextColor.GREEN);
      if (var1) {
         var4 = (TextComponent)Component.space().append(var4);
      } else {
         var4 = (TextComponent)Component.text(" = ", NamedTextColor.GRAY).append(var4);
      }

      return var4;
   }

   private static Queue<Click2.Data4> tokenize(Queue<String> var0) {
      LinkedList var1 = new LinkedList();
      Click2.Data4 var2 = null;

      while (!var0.isEmpty()) {
         String var3 = (String)var0.remove();
         char var4 = var3.charAt(0);
         if (field6.containsKey(var3)) {
            Click2.Data4 var9 = (Click2.Data4)var1.removeLast();
            ArrayList var10 = new ArrayList();
            var10.add(var9);
            if (var9.method1() == Click2.Type.GROUP && var9.getValue().equals(")")) {
               int var7 = 1;

               while (var7 > 0 && !var1.isEmpty()) {
                  Click2.Data4 var8 = (Click2.Data4)var1.removeLast();
                  var10.add(var8);
                  if (var8.method1() == Click2.Type.GROUP && var8.getValue().equals(")")) {
                     var7++;
                  }

                  if (var8.method1() == Click2.Type.GROUP && var8.getValue().equals("(")) {
                     var7--;
                  }
               }
            }

            Collections.reverse(var10);
            if (var1.peekLast() != null
               && ((Click2.Data4)var1.peekLast()).method1() == Click2.Type.GROUP
               && ((Click2.Data4)var1.peekLast()).getValue().equals(")")) {
               var1.add(new Click2.Data4("*", Click2.Type.OPERATION));
            }

            var1.add(new Click2.Data4("(", Click2.Type.GROUP));
            var1.addAll(var10);
            var1.add(new Click2.Data4("*", Click2.Type.OPERATION));
            var1.add(new Click2.Data4(field6.get(var3)));
            var2 = new Click2.Data4(")", Click2.Type.GROUP);
            var1.add(var2);
         } else if (!field3.matcher(var3).matches()) {
            if (field4.matcher(var3).matches()) {
               if (var2 != null && var2.getValue().equals(")") && var3.equals("(")) {
                  var1.add(new Click2.Data4("*", Click2.Type.OPERATION));
               }

               if (var2 != null && var2.method1() == Click2.Type.NUMBER && var3.equals("(")) {
                  var1.add(new Click2.Data4("*", Click2.Type.OPERATION));
               }

               var2 = new Click2.Data4(var3, Click2.Type.GROUP);
               var1.add(var2);
            } else {
               var2 = new Click2.Data4(var3, Click2.Type.OPERATION);
               var1.add(var2);
            }
         } else {
            StringBuilder var5 = new StringBuilder();
            var5.append(var4);

            while (!var0.isEmpty() && field3.matcher((CharSequence)var0.peek()).matches()) {
               var5.append((String)var0.remove());
            }

            String var6 = var5.toString();
            if (var2 != null && var2.getValue().equals(")")) {
               var1.add(new Click2.Data4("*", Click2.Type.OPERATION));
            }

            var2 = new Click2.Data4(var6, Click2.Type.NUMBER);
            var1.add(var2);
         }
      }

      return var1;
   }

   private static Click2.Data2 parseExpression(Queue<Click2.Data4> var0) {
      return Click2.Data2.method1(var0);
   }

   @Generated
   private Click2() {
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

   private static class Data extends Click2.Data2 {
      public Click2.Data2 field1 = null;
      public Click2.Data2 field2 = null;
      public Click2.Data4 field3 = null;
      public boolean field4 = false;

      @Override
      public double calculate() {
         String var1 = this.DIGIT_PATTERN.getValue();
         if (this.cache == null) {
            if (!var1.equals("+") && !var1.equals("-")) {
               return Double.NaN;
            }

            this.cache = new Click2.Data3(0.0);
         }

         double var2 = this.cache.method3();
         double var4 = this.SEPARATOR_PATTERN.method3();

         return switch (var1) {
            case "+" -> var2 + var4;
            case "-" -> var2 - var4;
            case "*", "x" -> var2 * var4;
            case "/" -> var2 / var4;
            case "%" -> var2 % var4;
            case "^" -> Math.pow(var2, var4);
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

   private abstract static class Data2 {
      public static Click2.Data2 formatNumber(Queue<Click2.Data4> var0) {
         if (var0.isEmpty()) {
            return new Click2.Data3(Double.NaN);
         }

         ArrayList var1 = new ArrayList();

         while (!var0.isEmpty()) {
            var1.add(calculateCached(var0));
         }

         boolean var2 = true;

         while (var1.size() > 1 && var2) {
            int var3 = -1;
            int var4 = -1;

            for (int var5 = 0; var5 < var1.size(); var5++) {
               Click2.Data2 var6 = (Click2.Data2)var1.get(var5);
               if (var6 instanceof Click2.Data var7) {
                  int var8 = var7.getPriority();
                  if (var5 == 0 || var1.get(var5 - 1) instanceof Click2.Data && !((Click2.Data)var1.get(var5 - 1)).field4) {
                     var8 = 4;
                  }

                  if (var8 > var4 && !var7.field4) {
                     var3 = var5;
                     var4 = var8;
                  }
               }
            }

            var2 = var3 != -1;
            if (var2) {
               Click2.Data var9 = (Click2.Data)var1.get(var3);
               if (var3 <= var1.size() - 2) {
                  var9.field2 = (Click2.Data2)var1.get(var3 + 1);
                  var1.remove(var3 + 1);
               }

               if (var3 >= 1) {
                  var9.field1 = (Click2.Data2)var1.get(var3 - 1);
                  if (var9.field1 instanceof Click2.Data && !((Click2.Data)var9.field1).field4) {
                     var9.field1 = null;
                  } else {
                     var1.remove(var3 - 1);
                  }
               }

               var9.field4 = true;
            }
         }

         return (Click2.Data2)var1.get(0);
      }

      private static Click2.Data2 calculateCached(Queue<Click2.Data4> var0) {
         Click2.Data4 var1 = (Click2.Data4)var0.remove();
         switch (var1.method1()) {
            case OPERATION:
               Click2.Data var2 = new Click2.Data();
               var2.field3 = var1;
               return var2;
            case NUMBER:
               return new Click2.Data3(var1);
            case GROUP:
               LinkedList var3 = new LinkedList();
               int var4 = 1;

               while (var4 > 0) {
                  Click2.Data4 var5 = (Click2.Data4)var0.remove();
                  if (var5.getValue().equals(")")) {
                     if (--var4 == 0) {
                        continue;
                     }
                  }

                  if (var5.getValue().equals("(")) {
                     var4++;
                  }

                  var3.add(var5);
               }

               return formatNumber(var3);
            default:
               return new Click2.Data3(Double.NaN);
         }
      }

      public abstract double calculate();
   }

   private static class Data3 extends Click2.Data2 {
      private final Click2.Data4 field1;

      public Data3(double var1) {
         this.cache = new Click2.Data4(var1);
      }

      @Override
      public double calculate() {
         return this.cache.method2();
      }

      @Generated
      public Data3(Click2.Data4 var1) {
         this.cache = var1;
      }
   }

   private static class Data4 {
      private String value = "";
      private final Click2.Type field1;
      private Double field2 = Double.NaN;

      public Data4(String var1, Click2.Type var2) {
         this.value = var1;
         this.cache = var2;
         if (this.cache == Click2.Type.NUMBER) {
            this.SEPARATOR_PATTERN = Double.parseDouble(var1);
         }
      }

      public Data4(double var1) {
         this.cache = Click2.Type.NUMBER;
         this.SEPARATOR_PATTERN = var1;
      }

      @Generated
      public String getValue() {
         return this.value;
      }

      @Generated
      public Click2.Type formatNumber() {
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
   }
}
