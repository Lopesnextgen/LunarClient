package com.moonsworth.lunar.ichor.util;

import com.google.common.collect.Lists;
import com.google.common.base.Function;
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

public final class AnnotationUtils {
   public static String getDesc(Class<? extends java.lang.annotation.Annotation> clazz0) {
      return Type.getType(clazz0).getInternalName();
   }

   public static String getSimpleName(Class<? extends java.lang.annotation.Annotation> clazz0) {
      return clazz0.getSimpleName();
   }

   public static void setVisible(FieldNode field0, Class<? extends java.lang.annotation.Annotation> clazz1, Object... items2) {
      AnnotationNode annotation3 = createNode(Type.getDescriptor(clazz1), items2);
      field0.visibleAnnotations = add(field0.visibleAnnotations, annotation3);
   }

   public static void setInvisible(FieldNode field0, Class<? extends java.lang.annotation.Annotation> clazz1, Object... items2) {
      AnnotationNode annotation3 = createNode(Type.getDescriptor(clazz1), items2);
      field0.invisibleAnnotations = add(field0.invisibleAnnotations, annotation3);
   }

   public static void setVisible(MethodNode method0, Class<? extends java.lang.annotation.Annotation> clazz1, Object... items2) {
      AnnotationNode annotation3 = createNode(Type.getDescriptor(clazz1), items2);
      method0.visibleAnnotations = add(method0.visibleAnnotations, annotation3);
   }

   public static void setInvisible(MethodNode method0, Class<? extends java.lang.annotation.Annotation> clazz1, Object... items2) {
      AnnotationNode annotation3 = createNode(Type.getDescriptor(clazz1), items2);
      method0.invisibleAnnotations = add(method0.invisibleAnnotations, annotation3);
   }

   private static AnnotationNode createNode(String text0, Object... items1) {
      AnnotationNode annotation2 = new AnnotationNode(text0);

      for (int index3 = 0; index3 < items1.length - 1; index3 += 2) {
         if (!(items1[index3] instanceof String)) {
            throw new IllegalArgumentException(
               "Annotation keys must be strings, found "
                  + items1[index3].getClass().getSimpleName()
                  + " with "
                  + items1[index3].toString()
                  + " at index "
                  + index3
                  + " creating "
                  + text0
            );
         }

         annotation2.visit((String)items1[index3], items1[index3 + 1]);
      }

      return annotation2;
   }

   private static List<AnnotationNode> add(List<AnnotationNode> list0, AnnotationNode annotation1) {
      if (list0 == null) {
         list0 = new ArrayList(1);
      } else {
         list0.remove(get(list0, annotation1.desc));
      }

      list0.add(annotation1);
      return list0;
   }

   public static AnnotationNode getVisible(FieldNode field0, Class<? extends java.lang.annotation.Annotation> clazz1) {
      return get(field0.visibleAnnotations, Type.getDescriptor(clazz1));
   }

   public static AnnotationNode getInvisible(FieldNode field0, Class<? extends java.lang.annotation.Annotation> clazz1) {
      return get(field0.invisibleAnnotations, Type.getDescriptor(clazz1));
   }

   public static AnnotationNode getVisible(MethodNode method0, Class<? extends java.lang.annotation.Annotation> clazz1) {
      return get(method0.visibleAnnotations, Type.getDescriptor(clazz1));
   }

   public static AnnotationNode getInvisible(MethodNode method0, Class<? extends java.lang.annotation.Annotation> clazz1) {
      return get(method0.invisibleAnnotations, Type.getDescriptor(clazz1));
   }

   public static AnnotationNode getSingleVisible(MethodNode method0, Class<? extends java.lang.annotation.Annotation>... items1) {
      return getSingle(method0.visibleAnnotations, items1);
   }

   public static AnnotationNode getSingleInvisible(MethodNode method0, Class<? extends java.lang.annotation.Annotation>... items1) {
      return getSingle(method0.invisibleAnnotations, items1);
   }

   public static AnnotationNode getVisible(ClassNode node0, Class<? extends java.lang.annotation.Annotation> clazz1) {
      return get(node0.visibleAnnotations, Type.getDescriptor(clazz1));
   }

   public static AnnotationNode getInvisible(ClassNode node0, Class<? extends java.lang.annotation.Annotation> clazz1) {
      return get(node0.invisibleAnnotations, Type.getDescriptor(clazz1));
   }

   public static AnnotationNode getVisibleParameter(MethodNode method0, Class<? extends java.lang.annotation.Annotation> clazz1, int number2) {
      return number2 < 0 ? getVisible(method0, clazz1) : getParameter(method0.visibleParameterAnnotations, Type.getDescriptor(clazz1), number2);
   }

   public static AnnotationNode getInvisibleParameter(MethodNode method0, Class<? extends java.lang.annotation.Annotation> clazz1, int number2) {
      return number2 < 0 ? getInvisible(method0, clazz1) : getParameter(method0.invisibleParameterAnnotations, Type.getDescriptor(clazz1), number2);
   }

   public static AnnotationNode getParameter(List<AnnotationNode>[] items0, String text1, int index2) {
      return items0 != null && index2 >= 0 && index2 < items0.length ? get(items0[index2], text1) : null;
   }

   public static AnnotationNode get(List<AnnotationNode> list0, String text1) {
      if (list0 == null) {
         return null;
      }

      for (AnnotationNode annotation3 : list0) {
         if (text1.equals(annotation3.desc)) {
            return annotation3;
         }
      }

      return null;
   }

   private static AnnotationNode getSingle(List<AnnotationNode> list0, Class<? extends java.lang.annotation.Annotation>[] items1) {
      ArrayList list2 = new ArrayList();

      for (Class clazz6 : items1) {
         AnnotationNode annotation7 = get(list0, Type.getDescriptor(clazz6));
         if (annotation7 != null) {
            list2.add(annotation7);
         }
      }

      int number8 = list2.size();
      if (number8 > 1) {
         throw new IllegalArgumentException("Conflicting annotations found: " + Lists.method1(list2, new Function<AnnotationNode, String>() {
            public String apply(AnnotationNode annotation1) {
               return annotation1.desc;
            }
         }));
      } else {
         return number8 == 0 ? null : (AnnotationNode)list2.get(0);
      }
   }

   public static <T> T getValue(AnnotationNode annotation0) {
      return getValue(annotation0, "value");
   }

   public static <T> T getValue(AnnotationNode annotation0, String text1, T value2) {
      Object obj3 = getValue(annotation0, text1);
      return (T)(obj3 != null ? obj3 : value2);
   }

   public static <T> T getValue(AnnotationNode annotation0, String text1, Class<?> clazz2) {
      Preconditions.checkNotNull(clazz2, "annotationClass cannot be null");
      Object obj3 = getValue(annotation0, text1);
      if (obj3 == null) {
         try {
            obj3 = clazz2.getDeclaredMethod(text1).getDefaultValue();
         } catch (NoSuchMethodException nosuchmethodexception5) {
         }
      }

      return (T)obj3;
   }

   public static <T> T getValue(AnnotationNode annotation0, String text1) {
      boolean flag2 = false;
      if (annotation0 != null && annotation0.values != null) {
         for (Object obj4 : annotation0.values) {
            if (flag2) {
               return (T)obj4;
            }

            if (obj4.equals(text1)) {
               flag2 = true;
            }
         }

         return null;
      } else {
         return null;
      }
   }

   public static <T extends Enum<T>> T getValue(AnnotationNode annotation0, String text1, Class<T> clazz2, T value3) {
      String[] items4 = getValue(annotation0, text1);
      return (T)(items4 == null ? value3 : toEnumValue(clazz2, items4));
   }

   public static <T> List<T> getValue(AnnotationNode annotation0, String text1, boolean flag2) {
      Object obj3 = getValue(annotation0, text1);
      if (obj3 instanceof List) {
         return (List<T>)obj3;
      } else if (obj3 != null) {
         ArrayList list4 = new ArrayList();
         list4.add(obj3);
         return list4;
      } else {
         return Collections.emptyList();
      }
   }

   public static <T extends Enum<T>> List<T> getValue(AnnotationNode annotation0, String text1, boolean flag2, Class<T> clazz3) {
      Object obj4 = getValue(annotation0, text1);
      if (!(obj4 instanceof List)) {
         if (obj4 instanceof String[]) {
            ArrayList list6 = new ArrayList();
            list6.add(toEnumValue(clazz3, (String[])obj4));
            return list6;
         } else {
            return Collections.emptyList();
         }
      } else {
         ListIterator iterator5 = ((List)obj4).listIterator();

         while (iterator5.hasNext()) {
            iterator5.set(toEnumValue(clazz3, (String[])iterator5.next()));
         }

         return (List<T>)obj4;
      }
   }

   public static void setValue(AnnotationNode annotation0, String text1, Object obj2) {
      if (annotation0 != null) {
         int index3 = 0;
         if (annotation0.values != null) {
            for (byte index4 = 0; index4 < annotation0.values.size() - 1; index4 += 2) {
               String text5 = annotation0.values.get(index4).toString();
               if (text1.equals(text5)) {
                  index3 = index4 + 1;
                  break;
               }
            }
         } else {
            annotation0.values = new ArrayList();
         }

         if (index3 > 0) {
            annotation0.values.set(index3, packValue(obj2));
         } else {
            annotation0.values.add(text1);
            annotation0.values.add(packValue(obj2));
         }
      }
   }

   private static Object packValue(Object obj0) {
      Class clazz1 = obj0.getClass();
      return clazz1.isEnum() ? new String[]{Type.getDescriptor(clazz1), obj0.toString()} : obj0;
   }

   private static <T extends Enum<T>> T toEnumValue(Class<T> clazz0, String[] items1) {
      if (!clazz0.getName().equals(Type.getType(items1[0]).getClassName())) {
         throw new IllegalArgumentException("The supplied enum class does not match the stored enum value");
      } else {
         return Enum.valueOf(clazz0, items1[1]);
      }
   }

   @Generated
   public AnnotationUtils() {
   }
}
