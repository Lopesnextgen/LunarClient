package com.moonsworth.lunar.ichor.util;

import com.google.common.collect.Lists;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import lombok.Generated;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

public final class FatalIchorError4 {
   public static String getDesc(Class<? extends java.lang.annotation.Annotation> var0) {
      return Type.getType(var0).getInternalName();
   }

   public static String getSimpleName(Class<? extends java.lang.annotation.Annotation> var0) {
      return var0.getSimpleName();
   }

   public static void setVisible(FieldNode var0, Class<? extends java.lang.annotation.Annotation> var1, Object... var2) {
      AnnotationNode var3 = createNode(Type.getDescriptor(var1), var2);
      var0.visibleAnnotations = add(var0.visibleAnnotations, var3);
   }

   public static void setInvisible(FieldNode var0, Class<? extends java.lang.annotation.Annotation> var1, Object... var2) {
      AnnotationNode var3 = createNode(Type.getDescriptor(var1), var2);
      var0.invisibleAnnotations = add(var0.invisibleAnnotations, var3);
   }

   public static void setVisible(MethodNode var0, Class<? extends java.lang.annotation.Annotation> var1, Object... var2) {
      AnnotationNode var3 = createNode(Type.getDescriptor(var1), var2);
      var0.visibleAnnotations = add(var0.visibleAnnotations, var3);
   }

   public static void setInvisible(MethodNode var0, Class<? extends java.lang.annotation.Annotation> var1, Object... var2) {
      AnnotationNode var3 = createNode(Type.getDescriptor(var1), var2);
      var0.invisibleAnnotations = add(var0.invisibleAnnotations, var3);
   }

   private static AnnotationNode createNode(String var0, Object... var1) {
      AnnotationNode var2 = new AnnotationNode(var0);

      for (int var3 = 0; var3 < var1.length - 1; var3 += 2) {
         if (!(var1[var3] instanceof String)) {
            throw new IllegalArgumentException(
               "Annotation keys must be strings, found "
                  + var1[var3].getClass().getSimpleName()
                  + " with "
                  + var1[var3].toString()
                  + " at index "
                  + var3
                  + " creating "
                  + var0
            );
         }

         var2.visit((String)var1[var3], var1[var3 + 1]);
      }

      return var2;
   }

   private static List<AnnotationNode> add(List<AnnotationNode> var0, AnnotationNode var1) {
      if (var0 == null) {
         var0 = new ArrayList(1);
      } else {
         var0.remove(get(var0, var1.desc));
      }

      var0.add(var1);
      return var0;
   }

   public static AnnotationNode getVisible(FieldNode var0, Class<? extends java.lang.annotation.Annotation> var1) {
      return get(var0.visibleAnnotations, Type.getDescriptor(var1));
   }

   public static AnnotationNode getInvisible(FieldNode var0, Class<? extends java.lang.annotation.Annotation> var1) {
      return get(var0.invisibleAnnotations, Type.getDescriptor(var1));
   }

   public static AnnotationNode getVisible(MethodNode var0, Class<? extends java.lang.annotation.Annotation> var1) {
      return get(var0.visibleAnnotations, Type.getDescriptor(var1));
   }

   public static AnnotationNode getInvisible(MethodNode var0, Class<? extends java.lang.annotation.Annotation> var1) {
      return get(var0.invisibleAnnotations, Type.getDescriptor(var1));
   }

   public static AnnotationNode getSingleVisible(MethodNode var0, Class<? extends java.lang.annotation.Annotation>... var1) {
      return getSingle(var0.visibleAnnotations, var1);
   }

   public static AnnotationNode getSingleInvisible(MethodNode var0, Class<? extends java.lang.annotation.Annotation>... var1) {
      return getSingle(var0.invisibleAnnotations, var1);
   }

   public static AnnotationNode getVisible(ClassNode var0, Class<? extends java.lang.annotation.Annotation> var1) {
      return get(var0.visibleAnnotations, Type.getDescriptor(var1));
   }

   public static AnnotationNode getInvisible(ClassNode var0, Class<? extends java.lang.annotation.Annotation> var1) {
      return get(var0.invisibleAnnotations, Type.getDescriptor(var1));
   }

   public static AnnotationNode getVisibleParameter(MethodNode var0, Class<? extends java.lang.annotation.Annotation> var1, int var2) {
      return var2 < 0 ? getVisible(var0, var1) : getParameter(var0.visibleParameterAnnotations, Type.getDescriptor(var1), var2);
   }

   public static AnnotationNode getInvisibleParameter(MethodNode var0, Class<? extends java.lang.annotation.Annotation> var1, int var2) {
      return var2 < 0 ? getInvisible(var0, var1) : getParameter(var0.invisibleParameterAnnotations, Type.getDescriptor(var1), var2);
   }

   public static AnnotationNode getParameter(List<AnnotationNode>[] var0, String var1, int var2) {
      return var0 != null && var2 >= 0 && var2 < var0.length ? get(var0[var2], var1) : null;
   }

   public static AnnotationNode get(List<AnnotationNode> var0, String var1) {
      if (var0 == null) {
         return null;
      }

      for (AnnotationNode var3 : var0) {
         if (var1.equals(var3.desc)) {
            return var3;
         }
      }

      return null;
   }

   private static AnnotationNode getSingle(List<AnnotationNode> var0, Class<? extends java.lang.annotation.Annotation>[] var1) {
      ArrayList var2 = new ArrayList();

      for (Class var6 : var1) {
         AnnotationNode var7 = get(var0, Type.getDescriptor(var6));
         if (var7 != null) {
            var2.add(var7);
         }
      }

      int var8 = var2.size();
      if (var8 > 1) {
         throw new IllegalArgumentException(
            "Conflicting annotations found: " + Lists.<AnnotationNode, String>transform(var2, new com.google.common.base.Function<AnnotationNode, String>() {
               public String apply(AnnotationNode var1) {
                  return var1.desc;
               }
            })
         );
      } else {
         return var8 == 0 ? null : (AnnotationNode)var2.get(0);
      }
   }

   public static <T> T getValue(AnnotationNode var0) {
      return getValue(var0, "value");
   }

   public static <T> T getValue(AnnotationNode var0, String var1, T var2) {
      Object var3 = getValue(var0, var1);
      return (T)(var3 != null ? var3 : var2);
   }

   public static <T> T getValue(AnnotationNode var0, String var1, Class<?> var2) {
      Preconditions.checkNotNull(var2, "annotationClass cannot be null");
      Object var3 = getValue(var0, var1);
      if (var3 == null) {
         try {
            var3 = var2.getDeclaredMethod(var1).getDefaultValue();
         } catch (NoSuchMethodException var5) {
         }
      }

      return (T)var3;
   }

   public static <T> T getValue(AnnotationNode var0, String var1) {
      boolean var2 = false;
      if (var0 != null && var0.values != null) {
         for (Object var4 : var0.values) {
            if (var2) {
               return (T)var4;
            }

            if (var4.equals(var1)) {
               var2 = true;
            }
         }

         return null;
      } else {
         return null;
      }
   }

   public static <T extends Enum<T>> T getValue(AnnotationNode var0, String var1, Class<T> var2, T var3) {
      String[] var4 = getValue(var0, var1);
      return (T)(var4 == null ? var3 : toEnumValue(var2, var4));
   }

   public static <T> List<T> getValue(AnnotationNode var0, String var1, boolean var2) {
      Object var3 = getValue(var0, var1);
      if (var3 instanceof List) {
         return (List<T>)var3;
      } else if (var3 != null) {
         ArrayList var4 = new ArrayList();
         var4.add(var3);
         return var4;
      } else {
         return Collections.emptyList();
      }
   }

   public static <T extends Enum<T>> List<T> getValue(AnnotationNode var0, String var1, boolean var2, Class<T> var3) {
      Object var4 = getValue(var0, var1);
      if (!(var4 instanceof List)) {
         if (var4 instanceof String[]) {
            ArrayList var6 = new ArrayList();
            var6.add(toEnumValue(var3, (String[])var4));
            return var6;
         } else {
            return Collections.emptyList();
         }
      } else {
         ListIterator var5 = ((List)var4).listIterator();

         while (var5.hasNext()) {
            var5.set(toEnumValue(var3, (String[])var5.next()));
         }

         return (List<T>)var4;
      }
   }

   public static void setValue(AnnotationNode var0, String var1, Object var2) {
      if (var0 != null) {
         int var3 = 0;
         if (var0.values != null) {
            for (byte var4 = 0; var4 < var0.values.size() - 1; var4 += 2) {
               String var5 = var0.values.get(var4).toString();
               if (var1.equals(var5)) {
                  var3 = var4 + 1;
                  break;
               }
            }
         } else {
            var0.values = new ArrayList();
         }

         if (var3 > 0) {
            var0.values.set(var3, packValue(var2));
         } else {
            var0.values.add(var1);
            var0.values.add(packValue(var2));
         }
      }
   }

   private static Object packValue(Object var0) {
      Class var1 = var0.getClass();
      return var1.isEnum() ? new String[]{Type.getDescriptor(var1), var0.toString()} : var0;
   }

   private static <T extends Enum<T>> T toEnumValue(Class<T> var0, String[] var1) {
      if (!var0.getName().equals(Type.getType(var1[0]).getClassName())) {
         throw new IllegalArgumentException("The supplied enum class does not match the stored enum value");
      } else {
         return Enum.valueOf(var0, var1[1]);
      }
   }
}
