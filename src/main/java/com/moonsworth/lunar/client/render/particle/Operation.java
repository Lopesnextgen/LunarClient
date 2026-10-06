package com.moonsworth.lunar.client.render.particle;

import java.util.HashSet;
import java.util.Set;
import lombok.Generated;

public enum Operation {
   ADD("+", 1) {
      @Override
      public double calculate(double value1, double value3) {
         return value1 + value3;
      }
   },
   SUB("-", 1) {
      @Override
      public double calculate(double value1, double value3) {
         return value1 - value3;
      }
   },
   MUL("*", 2) {
      @Override
      public double calculate(double value1, double value3) {
         return value1 * value3;
      }
   },
   DIV("/", 2) {
      @Override
      public double calculate(double value1, double value3) {
         return value1 / (value3 == 0.0 ? 1.0 : value3);
      }
   },
   MOD("%", 2) {
      @Override
      public double calculate(double value1, double value3) {
         return value1 % value3;
      }
   },
   POW("^", 3) {
      @Override
      public double calculate(double value1, double value3) {
         return Math.pow(value1, value3);
      }
   },
   AND("&&", 5) {
      @Override
      public double calculate(double value1, double value3) {
         return value1 != 0.0 && value3 != 0.0 ? 1.0 : 0.0;
      }
   },
   OR("||", 5) {
      @Override
      public double calculate(double value1, double value3) {
         return value1 == 0.0 && value3 == 0.0 ? 0.0 : 1.0;
      }
   },
   LESS("<", 5) {
      @Override
      public double calculate(double value1, double value3) {
         return value1 < value3 ? 1.0 : 0.0;
      }
   },
   LESS_THAN("<=", 5) {
      @Override
      public double calculate(double value1, double value3) {
         return value1 <= value3 ? 1.0 : 0.0;
      }
   },
   GREATER_THAN(">=", 5) {
      @Override
      public double calculate(double value1, double value3) {
         return value1 >= value3 ? 1.0 : 0.0;
      }
   },
   GREATER(">", 5) {
      @Override
      public double calculate(double value1, double value3) {
         return value1 > value3 ? 1.0 : 0.0;
      }
   },
   EQUALS("==", 5) {
      @Override
      public double calculate(double value1, double value3) {
         return equals(value1, value3) ? 1.0 : 0.0;
      }
   },
   NOT_EQUALS("!=", 5) {
      @Override
      public double calculate(double value1, double value3) {
         return !equals(value1, value3) ? 1.0 : 0.0;
      }
   };

   public static final Set<String> OPERATORS = new HashSet<>();
   public final String sign;
   public final int value;

   public static boolean equals(double value0, double value) {
      return Math.abs(value0 - value) < 1.0E-5;
   }

   public static boolean isTrue(double value0) {
      return !equals(value0, 0.0);
   }

   public abstract double calculate(double value1, double value3);

   @Generated
   Operation(String text, int value2) {
      this.sign = text;
      this.value = value2;
   }

   static {
      for (Operation glintcolorizertype3 : values()) {
         OPERATORS.add(glintcolorizertype3.sign);
      }
   }
}
