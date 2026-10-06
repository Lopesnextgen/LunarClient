package com.moonsworth.webosr.javascript;

import com.moonsworth.webosr.BridgeValueCodec;
import com.moonsworth.webosr.handler.FunctionJS;
import com.moonsworth.webosr.wrappers.Browser;
import com.moonsworth.webosr.wrappers.PromiseJS;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class FunctionBus implements FunctionJS {
   protected static final ArrayBlockingQueue<Runnable> MAIN_ACTOR_QUEUE = new ArrayBlockingQueue<>(4096);
   protected final Map<String, FunctionBus> children = new ConcurrentHashMap<>();
   protected final Map<String, FunctionBus.MethodInstancePair> routines = new ConcurrentHashMap<>();
   protected final Map<Object, Map<String, FunctionBus.MethodInstancePair>> cache = new ConcurrentHashMap<>();

   public static void consumeMainActorQueue() {
      Runnable var0;
      while ((var0 = MAIN_ACTOR_QUEUE.poll()) != null) {
         var0.run();
      }
   }

   public void registerChild(String var1, FunctionBus var2) {
      this.children.put(var1, var2);
   }

   public void unregisterChild(String var1) {
      this.children.remove(var1);
   }

   public void subscribe(Object var1) {
      if (var1 instanceof Class var2) {
         var1 = null;
      } else {
         var2 = var1.getClass();
      }

      if (var1 != null) {
         Map var3 = this.cache.get(var1);
         if (var3 != null) {
            this.routines.putAll(var3);
         }
      }

      Map var26 = this.cache.get(var2);
      if (var26 != null) {
         this.routines.putAll(var26);
      }

      Lookup var4 = MethodHandles.lookup();
      HashMap var5 = new HashMap();
      HashMap var6 = new HashMap();

      for (Method var10 : var2.getMethods()) {
         CallbackJS var11 = var10.getAnnotation(CallbackJS.class);
         if (var11 != null) {
            boolean var12 = !Modifier.isStatic(var10.getModifiers());
            if (!var12 || var1 != null) {
               MethodHandle var13;
               try {
                  var13 = var4.unreflect(var10);
               } catch (IllegalAccessException var25) {
                  throw new RuntimeException(var25);
               }

               Parameter[] var14 = var10.getParameters();
               boolean var15 = false;
               boolean var16 = false;
               boolean var17 = false;
               Class var18 = null;
               int var19 = 0;

               for (Parameter var23 : var14) {
                  Class var24 = var23.getType();
                  if (var24 == Browser.class) {
                     var15 = true;
                  } else if (var24 == PromiseJS.class) {
                     if (var16) {
                        throw new IllegalArgumentException("Multiple PromiseJS parameters are not allowed on the same method");
                     }

                     var16 = true;
                     var18 = var23.getParameterizedType().getClass();
                  } else if (var24 == String[].class) {
                     var17 = true;
                  } else {
                     var19++;
                  }
               }

               FunctionBus.MethodInstancePair var27 = new FunctionBus.MethodInstancePair(
                  var12 ? var1 : null, var13, var11.async(), var12, var14, var10.getReturnType(), var15, var16, var18, var17, var19
               );
               if (var12) {
                  var5.put(var11.value(), var27);
               } else {
                  var6.put(var11.value(), var27);
               }
            }
         }
      }

      if (!var5.isEmpty()) {
         this.routines.putAll(var5);
         this.cache.put(var1, var5);
      }

      if (!var6.isEmpty()) {
         this.routines.putAll(var6);
         this.cache.put(var2, var6);
      }
   }

   @Override
   public void invoke(PromiseJS<?> var1, Browser var2, String var3, String[] var4) {
      String var5 = var3;
      String[] var6 = var4;
      if (var4.length > 0 && this.isExplicitTarget(var4[0])) {
         var5 = var4[0];
         var6 = Arrays.copyOfRange(var4, 1, var4.length);
      }

      FunctionBus var7 = this.children.get(var5);
      if (var7 != null) {
         var7.invoke(var1, var2, var5, var6);
      } else {
         FunctionBus.MethodInstancePair var8 = this.routines.get(var5);
         if (var8 != null) {
            Object[] var9;
            try {
               var9 = this.buildInvocationParameters(var8, var1, var2, var6, var5);
            } catch (IllegalArgumentException var12) {
               throw new RuntimeException(var12);
            }

            if (var8.async) {
               try {
                  Object var10 = var8.method.invokeWithArguments(var9);
                  this.completePromiseIfNeeded(var1, var8, var10);
               } catch (Throwable var11) {
                  throw new RuntimeException(var11);
               }
            } else {
               MAIN_ACTOR_QUEUE.add(() -> {
                  try {
                     Object var4x = var8.method.invokeWithArguments(var9);
                     this.completePromiseIfNeeded(var1, var8, var4x);
                  } catch (Throwable var5x) {
                     throw new RuntimeException(var5x);
                  }
               });
            }
         }
      }
   }

   private Object[] buildInvocationParameters(FunctionBus.MethodInstancePair var1, PromiseJS<?> var2, Browser var3, String[] var4, String var5) {
      if (var4.length < var1.requiredJsArgCount) {
         throw new IllegalArgumentException("Not enough JS arguments for \"" + var5 + "\" (need " + var1.requiredJsArgCount + ", got " + var4.length + ")");
      }

      if (!var1.hasStringArray && var4.length > var1.requiredJsArgCount) {
         throw new IllegalArgumentException("Too many JS arguments for \"" + var5 + "\" (expected " + var1.requiredJsArgCount + ", got " + var4.length + ")");
      }

      int var6 = (var1.passInstance ? 1 : 0) + var1.params.length;
      Object[] var7 = new Object[var6];
      int var8 = 0;
      if (var1.passInstance) {
         var7[var8++] = var1.instance;
      }

      int var9 = 0;

      for (Parameter var13 : var1.params) {
         Class var14 = var13.getType();
         if (var14 == Browser.class) {
            var7[var8++] = var3;
         } else if (var14 == PromiseJS.class) {
            var7[var8++] = var2;
         } else if (var14 == String[].class) {
            var7[var8++] = Arrays.copyOfRange(var4, var9, var4.length);
            var9 = var4.length;
         } else {
            var7[var8++] = this.stringToObject(var4[var9++], var14);
         }
      }

      if (var1.promiseValueType != null) {
         var2.bindBridgeCodec(this.asBridgeCodec(), var1.promiseValueType);
      }

      return var7;
   }

   public BridgeValueCodec asBridgeCodec() {
      return this::objectToString;
   }

   private boolean isExplicitTarget(String var1) {
      return this.children.containsKey(var1) || this.routines.containsKey(var1);
   }

   private void completePromiseIfNeeded(PromiseJS<?> var1, FunctionBus.MethodInstancePair var2, Object var3) {
      if (!var2.hasPromiseJS) {
         if (var2.returnType != void.class) {
            if (var3 == null) {
               var1._resolve((String)null);
            } else {
               var1._resolve(this.objectToString(var3, var2.returnType));
            }
         } else {
            var1.resolve();
         }
      }
   }

   protected Object stringToObject(String var1, Class<?> var2) {
      if (var2 == String.class) {
         return var1;
      }

      if (var2 == Integer.class || var2 == int.class) {
         return Integer.parseInt(var1);
      }

      if (var2 == Double.class || var2 == double.class) {
         return Double.parseDouble(var1);
      }

      if (var2 == Boolean.class || var2 == boolean.class) {
         return Boolean.parseBoolean(var1);
      }

      if (var2 == Float.class || var2 == float.class) {
         return Float.parseFloat(var1);
      }

      if (var2 == Long.class || var2 == long.class) {
         return Long.parseLong(var1);
      }

      if (var2 == Short.class || var2 == short.class) {
         return Short.parseShort(var1);
      }

      if (var2 == Byte.class || var2 == byte.class) {
         return Byte.parseByte(var1);
      }

      if (var2 != Character.class && var2 != char.class) {
         if (var2.isEnum()) {
            for (Object var6 : var2.getEnumConstants()) {
               if (var6.toString().equalsIgnoreCase(var1)) {
                  return var6;
               }
            }

            throw new IllegalArgumentException("Argument's value of JsFunction \"" + var1 + "\" is not a valid enum value");
         } else if (var2 == UUID.class) {
            return UUID.fromString(var1);
         } else {
            throw new IllegalArgumentException("Argument's value of JsFunction \"" + var1 + "\" is of an unsupported type");
         }
      } else if (var1.length() != 1) {
         throw new IllegalArgumentException("Argument's value of JsFunction \"" + var1 + "\" is a character, but the argument is not a single character");
      } else {
         return var1.charAt(0);
      }
   }

   protected String objectToString(Object var1, Class<?> var2) {
      if (var2 == String.class) {
         return (String)var1;
      } else if (var2 == Integer.class || var2 == int.class) {
         return Integer.toString(((Number)var1).intValue());
      } else if (var2 == Double.class || var2 == double.class) {
         return Double.toString(((Number)var1).doubleValue());
      } else if (var2 == Boolean.class || var2 == boolean.class) {
         return Boolean.toString((Boolean)var1);
      } else if (var2 == Float.class || var2 == float.class) {
         return Float.toString(((Number)var1).floatValue());
      } else if (var2 == Long.class || var2 == long.class) {
         return Long.toString(((Number)var1).longValue());
      } else if (var2 == Short.class || var2 == short.class) {
         return Short.toString(((Number)var1).shortValue());
      } else if (var2 == Byte.class || var2 == byte.class) {
         return Byte.toString(((Number)var1).byteValue());
      } else if (var2 == Character.class || var2 == char.class) {
         return String.valueOf(((Character)var1).charValue());
      } else if (var2.isEnum()) {
         return ((Enum)var1).toString();
      } else if (var2 == UUID.class) {
         return ((UUID)var1).toString();
      } else {
         throw new IllegalArgumentException("Return value is of an unsupported type: " + var2.getName());
      }
   }

   protected class MethodInstancePair {
      private final Object instance;
      private final MethodHandle method;
      private final boolean async;
      private final boolean passInstance;
      private final Parameter[] params;
      private final Class<?> returnType;
      private final boolean hasBrowser;
      private final boolean hasPromiseJS;
      private final Class<?> promiseValueType;
      private final boolean hasStringArray;
      private final int requiredJsArgCount;

      protected MethodInstancePair(
         Object var1,
         MethodHandle var2,
         boolean var3,
         boolean var4,
         Parameter[] var5,
         Class<?> var6,
         boolean var7,
         boolean var8,
         Class<?> var9,
         boolean var10,
         int var11
      ) {
         this.instance = var1;
         this.method = var2;
         this.async = var3;
         this.passInstance = var4;
         this.params = var5;
         this.returnType = var6;
         this.hasBrowser = var7;
         this.hasPromiseJS = var8;
         this.promiseValueType = var9;
         this.hasStringArray = var10;
         this.requiredJsArgCount = var11;
      }
   }
}
