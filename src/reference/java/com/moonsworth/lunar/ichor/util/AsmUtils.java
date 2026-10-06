package com.moonsworth.lunar.ichor.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.moonsworth.lunar.ichor.MixinTargetRemapper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Nullable;
import lombok.Generated;
import org.cadixdev.bombe.analysis.InheritanceProvider.ClassInfo;
import org.cadixdev.bombe.provider.ClassProvider;
import org.cadixdev.bombe.type.BaseType;
import org.cadixdev.bombe.type.FieldType;
import org.cadixdev.bombe.type.MethodDescriptor;
import org.cadixdev.bombe.type.ObjectType;
import org.cadixdev.bombe.type.VoidType;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.IntInsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.spongepowered.asm.util.Bytecode;

public final class AsmUtils {
   public static final int field1 = 589824;
   private static final Map<ClassProvider, Cache<String, String>> field2 = new ConcurrentHashMap<>();

   private static Cache<String, String> method1(ClassProvider classprovider0) {
      return field2.computeIfAbsent(classprovider0, arg0x -> CacheBuilder.method1().method11(1000L).method15().method10(4).method35());
   }

   public static void method2(ClassNode node0, ClassNode node1) {
      node0.name = node1.name;
      node0.superName = node1.superName;
      node0.fields = node1.fields;
      node0.access = node1.access;
      node0.attrs = node1.attrs;
      node0.methods = node1.methods;
      node0.innerClasses = node1.innerClasses;
      node0.interfaces = node1.interfaces;
      node0.invisibleAnnotations = node1.invisibleAnnotations;
      node0.outerClass = node1.outerClass;
      node0.signature = node1.signature;
      node0.outerMethod = node1.outerMethod;
      node0.outerMethodDesc = node1.outerMethodDesc;
      node0.module = node1.module;
      node0.sourceFile = node1.sourceFile;
      node0.sourceDebug = node1.sourceDebug;
      node0.visibleAnnotations = node1.visibleAnnotations;
      node0.visibleTypeAnnotations = node1.visibleTypeAnnotations;
      node0.permittedSubclasses = node1.permittedSubclasses;
   }

   public static int method3(int number0) {
      return method4(number0, false);
   }

   public static int method4(int number0, boolean flag1) {
      number0 &= -5;
      number0 &= -3;
      if (flag1) {
         number0 &= -17;
      }

      return number0 | 1;
   }

   public static int method5(int number0, boolean flag1) {
      number0 &= -5;
      number0 &= -2;
      if (flag1) {
         number0 &= -17;
      }

      return number0 | 2;
   }

   public static Optional<String> method6(byte[] items0) {
      if (items0 != null && items0.length != 0) {
         final String[] items1 = new String[1];
         ClassVisitor classvisitor2 = new ClassVisitor(589824) {
            public void visit(int number1x, int number2x, String text3, String text4, String text5, String[] items6) {
               items1[0] = text3;
            }
         };
         ClassReader classreader3 = new ClassReader(items0);
         classreader3.accept(classvisitor2, 1);
         return Optional.ofNullable(items1[0]);
      } else {
         return Optional.empty();
      }
   }

   public static Optional<String> method7(byte[] items0) {
      if (items0 != null && items0.length != 0) {
         final String[] items1 = new String[1];
         ClassVisitor classvisitor2 = new ClassVisitor(589824) {
            public void visit(int number1x, int number2x, String text3, String text4, String text5, String[] items6) {
               if (text5 != null) {
                  items1[0] = text5;
               }
            }
         };
         ClassReader classreader3 = new ClassReader(items0);
         classreader3.accept(classvisitor2, 1);
         return Optional.ofNullable(items1[0]);
      } else {
         return Optional.empty();
      }
   }

   public static Set<String> method8(byte[] items0) {
      final HashSet set1 = new HashSet();
      if (items0 == null) {
         return set1;
      }

      ClassVisitor classvisitor2 = new ClassVisitor(589824) {
         public void visit(int number1x, int number2x, String text3, String text4, String text5, String[] items6) {
            if (text5 != null) {
               set1.add(text5);
            }

            if (items6 != null) {
               Collections.addAll(set1, items6);
            }
         }
      };
      ClassReader classreader3 = new ClassReader(items0);
      classreader3.accept(classvisitor2, 1);
      return set1;
   }

   public static Set<String> method9(String text0, ClassProvider classprovider1) {
      Set set2 = method10(classprovider1.getAsNode(text0));
      HashSet set3 = new HashSet(set2);

      for (String text5 : set2) {
         if (!text5.equals("java/lang/Object")) {
            set3.addAll(method9(text5, classprovider1));
         }
      }

      return set3;
   }

   public static Set<String> method10(ClassNode node0) {
      HashSet set1 = new HashSet();
      if (node0 == null) {
         return set1;
      }

      String text2 = node0.superName;
      if (text2 != null) {
         set1.add(text2);
      }

      List list3 = node0.interfaces;
      if (list3 != null) {
         set1.addAll(list3);
      }

      return set1;
   }

   public static Set<String> method11(ClassInfo classinfo0) {
      HashSet set1 = new HashSet();
      if (classinfo0 == null) {
         return set1;
      }

      String text2 = classinfo0.getSuperName();
      if (text2 != null) {
         set1.add(text2);
      }

      List list3 = classinfo0.getInterfaces();
      if (list3 != null) {
         set1.addAll(list3);
      }

      return set1;
   }

   public static Set<String> method12(String text0, org.cadixdev.bombe.analysis.InheritanceProvider inheritanceprovider1) {
      Set set2 = method11((ClassInfo)inheritanceprovider1.provide(text0).orElse(null));
      HashSet set3 = new HashSet(set2);

      for (String text5 : set2) {
         if (!text5.equals("java/lang/Object")) {
            set3.addAll(method12(text5, inheritanceprovider1));
         }
      }

      return set3;
   }

   public static boolean method13(String text0, String text1, ClassProvider classprovider2) {
      try {
         Cache mixinhelper4_103 = method1(classprovider2);
         String text4 = (String)mixinhelper4_103.get(text0, () -> {
            ClassNode node2x = classprovider2.getAsNode(text0);
            if (node2x == null) {
               return "";
            }

            String text3x = node2x.superName;
            return text3x == null ? "" : text3x;
         });

         while (text4.length() > 0) {
            if (text4.equals(text1)) {
               return true;
            }

            String text5 = text4;
            text4 = (String)mixinhelper4_103.get(text4, () -> {
               ClassNode node2x = classprovider2.getAsNode(text5);
               if (node2x == null) {
                  return "";
               }

               String text3x = node2x.superName;
               return text3x == null ? "" : text3x;
            });
         }

         return false;
      } catch (Throwable exception6) {
         throw exception6;
      }
   }

   public static Set<String> method14(byte[] items0) {
      final HashSet set1 = new HashSet();
      if (items0 == null) {
         return set1;
      }

      ClassVisitor classvisitor2 = new ClassVisitor(589824) {
         public void visitInnerClass(String text1x, String text2x, String text3, int number4) {
            set1.add(text1x);
         }
      };
      ClassReader classreader3 = new ClassReader(items0);
      classreader3.accept(classvisitor2, 1);
      return set1;
   }

   public static Set<String> method15(byte[] items0) {
      final HashSet set1 = new HashSet();
      if (items0 == null) {
         return set1;
      }

      ClassVisitor classvisitor2 = new ClassVisitor(589824) {
         public void visitInnerClass(String text1x, String text2x, String text3, int number4) {
            set1.add(text1x);
         }

         public MethodVisitor visitMethod(int number1x, String text2x, String text3, String text4, String[] items5) {
            MethodDescriptor methoddescriptor6 = MethodDescriptor.of(text3);

            for (FieldType fieldtype8 : methoddescriptor6.getParamTypes()) {
               if (fieldtype8 instanceof ObjectType objecttype9) {
                  set1.add(objecttype9.getClassName());
               }
            }

            if (methoddescriptor6.getReturnType() instanceof ObjectType objecttype11) {
               set1.add(objecttype11.getClassName());
            }

            return new MethodVisitor(589824) {
               public void visitMethodInsn(int number1x_, String text2x_, String text3x, String text4x, boolean flag5x) {
                  set1.add(text2x_);
               }
            };
         }

         public FieldVisitor visitField(int number1x, String text2x, String text3, String text4, Object obj5) {
            Type type6 = Type.getType(text3);
            if (type6.getSort() == 10) {
               set1.add(type6.getInternalName());
            }

            return null;
         }
      };
      ClassReader classreader3 = new ClassReader(items0);
      classreader3.accept(classvisitor2, 0);
      return set1;
   }

   public static ClassNode method16(byte[] items0, int number1) {
      ClassNode node2 = new ClassNode();
      ClassReader classreader3 = new ClassReader(items0);
      classreader3.accept(node2, number1);
      return node2;
   }

   public static boolean method17(ClassNode node0) {
      for (Entry entry2 : MixinTargetRemapper.method3(node0).entrySet()) {
         AnnotationNode annotation3 = (AnnotationNode)entry2.getKey();
         if (annotation3.desc.equals("Lorg/spongepowered/asm/mixin/Mixin;")) {
            return true;
         }
      }

      return false;
   }

   public static boolean method18(ClassNode node0, Class<?> clazz1) {
      if (node0.visibleAnnotations != null) {
         String text2 = Type.getDescriptor(clazz1);

         for (AnnotationNode annotation4 : node0.visibleAnnotations) {
            if (annotation4.desc.equals(text2)) {
               return true;
            }
         }
      }

      return false;
   }

   public static boolean method19(ClassNode node0, String text1) {
      if (node0.visibleAnnotations != null) {
         for (AnnotationNode annotation3 : node0.visibleAnnotations) {
            if (annotation3.desc.equals(text1)) {
               return true;
            }
         }
      }

      return false;
   }

   public static boolean method20(FieldNode field0, Class<?> clazz1) {
      if (field0.visibleAnnotations != null) {
         String text2 = Type.getDescriptor(clazz1);

         for (AnnotationNode annotation4 : field0.visibleAnnotations) {
            if (annotation4.desc.equals(text2)) {
               return true;
            }
         }
      }

      return false;
   }

   public static boolean method21(FieldNode field0, String text1) {
      if (field0.visibleAnnotations != null) {
         for (AnnotationNode annotation3 : field0.visibleAnnotations) {
            if (annotation3.desc.equals(text1)) {
               return true;
            }
         }
      }

      return false;
   }

   public static boolean method22(MethodNode method0, Class<?> clazz1) {
      if (method0.visibleAnnotations != null) {
         String text2 = Type.getDescriptor(clazz1);

         for (AnnotationNode annotation4 : method0.visibleAnnotations) {
            if (annotation4.desc.equals(text2)) {
               return true;
            }
         }
      }

      return false;
   }

   public static boolean method23(MethodNode method0, String text1) {
      if (method0.visibleAnnotations != null) {
         for (AnnotationNode annotation3 : method0.visibleAnnotations) {
            if (annotation3.desc.equals(text1)) {
               return true;
            }
         }
      }

      return false;
   }

   public static boolean method24(MethodNode method0, String text1) {
      if (method0.invisibleAnnotations != null) {
         for (AnnotationNode annotation3 : method0.invisibleAnnotations) {
            if (annotation3.desc.equals(text1)) {
               return true;
            }
         }
      }

      return false;
   }

   @Nullable
   public static AnnotationNode method25(Object obj0, Class<?> clazz1) {
      return method26(obj0, Type.getDescriptor(clazz1));
   }

   @Nullable
   public static AnnotationNode method26(Object obj0, String text1) {
      Object obj2 = null;
      if (obj0 instanceof ClassNode node4) {
         obj2 = node4.visibleAnnotations;
      } else if (obj0 instanceof MethodNode method5_) {
         obj2 = method5_.visibleAnnotations;
      } else {
         if (!(obj0 instanceof FieldNode field3)) {
            throw new IllegalArgumentException("Can't handle " + obj0.getClass().getName());
         }

         obj2 = field3.visibleAnnotations;
      }

      if (obj2 != null) {
         for (AnnotationNode annotation8 : obj2) {
            if (annotation8.desc.equals(text1)) {
               return annotation8;
            }
         }
      }

      return null;
   }

   @Nullable
   public static AnnotationNode method27(Object obj0, Class<?> clazz1) {
      return method28(obj0, Type.getDescriptor(clazz1));
   }

   @Nullable
   public static AnnotationNode method28(Object obj0, String text1) {
      Object obj2 = null;
      if (obj0 instanceof ClassNode node4) {
         obj2 = node4.invisibleAnnotations;
      } else if (obj0 instanceof MethodNode method5_) {
         obj2 = method5_.invisibleAnnotations;
      } else {
         if (!(obj0 instanceof FieldNode field3)) {
            throw new IllegalArgumentException("Can't handle " + obj0.getClass().getName());
         }

         obj2 = field3.invisibleAnnotations;
      }

      if (obj2 != null) {
         for (AnnotationNode annotation8 : obj2) {
            if (annotation8.desc.equals(text1)) {
               return annotation8;
            }
         }
      }

      return null;
   }

   public static void method29(Object obj0, Class<?> clazz1) {
      method30(obj0, Type.getDescriptor(clazz1));
   }

   public static void method30(Object obj0, String text1) {
      List list2 = null;
      if (obj0 instanceof ClassNode node4) {
         list2 = node4.visibleAnnotations;
      } else if (obj0 instanceof MethodNode method5_) {
         list2 = method5_.visibleAnnotations;
      } else {
         if (!(obj0 instanceof FieldNode field3)) {
            throw new IllegalArgumentException("Can't handle " + obj0.getClass().getName());
         }

         list2 = field3.visibleAnnotations;
      }

      if (list2 != null) {
         list2.removeIf(arg1x -> arg1x.desc.equals(text1));
      }
   }

   public static void method31(Object obj0, Class<?> clazz1) {
      method32(obj0, Type.getDescriptor(clazz1));
   }

   public static void method32(Object obj0, String text1) {
      List list2 = null;
      if (obj0 instanceof ClassNode node4) {
         list2 = node4.invisibleAnnotations;
      } else if (obj0 instanceof MethodNode method5_) {
         list2 = method5_.invisibleAnnotations;
      } else {
         if (!(obj0 instanceof FieldNode field3)) {
            throw new IllegalArgumentException("Can't handle " + obj0.getClass().getName());
         }

         list2 = field3.invisibleAnnotations;
      }

      if (list2 != null) {
         list2.removeIf(arg1x -> arg1x.desc.equals(text1));
      }
   }

   public static byte[] method33(ClassNode node0, ClassLoader classloader1, int number2) {
      LoaderAwareClassWriter classwriter3 = new LoaderAwareClassWriter(classloader1, number2);
      node0.accept(classwriter3);
      return classwriter3.toByteArray();
   }

   public static boolean isStatic(int number0) {
      return (number0 & 8) > 0;
   }

   public static boolean method34(ClassNode node0) {
      return (node0.access & 512) > 0;
   }

   @Nullable
   public static Integer method35(AbstractInsnNode instruction0) {
      int number1 = instruction0.getOpcode();
      if (number1 >= 2 && number1 <= 8) {
         return number1 - 3;
      } else if (instruction0 instanceof IntInsnNode intinsnnode2 && number1 != 188) {
         return intinsnnode2.operand;
      } else {
         return instruction0 instanceof LdcInsnNode ldcinsnnode3 && ldcinsnnode3.cst instanceof Integer number4 ? number4 : null;
      }
   }

   @Nullable
   public static Long method36(AbstractInsnNode instruction0) {
      int number1 = instruction0.getOpcode();
      if (number1 == 9) {
         return 0L;
      } else if (number1 == 10) {
         return 1L;
      } else {
         return instruction0 instanceof LdcInsnNode ldcinsnnode2 && ldcinsnnode2.cst instanceof Long number3 ? number3 : null;
      }
   }

   @Nullable
   public static Float method37(AbstractInsnNode instruction0) {
      int number1 = instruction0.getOpcode();
      if (number1 >= 11 && number1 <= 13) {
         return (float)(number1 - 11);
      } else {
         return instruction0 instanceof LdcInsnNode ldcinsnnode2 && ldcinsnnode2.cst instanceof Float value3 ? value3 : null;
      }
   }

   @Nullable
   public static Double method38(AbstractInsnNode instruction0) {
      int number1 = instruction0.getOpcode();
      if (number1 == 14) {
         return 0.0;
      } else if (number1 == 15) {
         return 1.0;
      } else {
         return instruction0 instanceof LdcInsnNode ldcinsnnode2 && ldcinsnnode2.cst instanceof Double value3 ? value3 : null;
      }
   }

   @Nullable
   public static String method39(AbstractInsnNode instruction0) {
      return instruction0 instanceof LdcInsnNode ldcinsnnode1 && ldcinsnnode1.cst instanceof String text2 ? text2 : null;
   }

   public static AbstractInsnNode method40(int number0) {
      if (number0 >= -1 && number0 <= 5) {
         return new InsnNode(number0 + 3);
      }

      if (number0 >= -128 && number0 <= 127) {
         new IntInsnNode(16, number0);
      } else if (number0 >= -32768 && number0 <= 32767) {
         new IntInsnNode(17, number0);
      }

      return new LdcInsnNode(number0);
   }

   public static AbstractInsnNode method41(long number0) {
      if (number0 == 0L) {
         return new InsnNode(9);
      } else {
         return (AbstractInsnNode)(number0 == 1L ? new InsnNode(10) : new LdcInsnNode(number0));
      }
   }

   public static Character method42(String text0) {
      return switch (text0) {
         case "java/lang/Boolean" -> 'Z';
         case "java/lang/Character" -> 'C';
         case "java/lang/Byte" -> 'B';
         case "java/lang/Short" -> 'S';
         case "java/lang/Integer" -> 'I';
         case "java/lang/Float" -> 'F';
         case "java/lang/Long" -> 'J';
         case "java/lang/Double" -> 'D';
         default -> null;
      };
   }

   public static String method43(char character0) {
      return Bytecode.getUnboxingMethod(Type.getType(String.valueOf(character0)));
   }

   public static int method44(org.cadixdev.bombe.type.Type type0) {
      if (type0 == VoidType.INSTANCE) {
         return 177;
      } else if (type0 == BaseType.DOUBLE) {
         return 175;
      } else if (type0 == BaseType.LONG) {
         return 173;
      } else if (type0 == BaseType.BOOLEAN || type0 == BaseType.INT || type0 == BaseType.SHORT || type0 == BaseType.BYTE) {
         return 172;
      } else {
         return type0 == BaseType.FLOAT ? 174 : 176;
      }
   }

   public static Optional<Integer> method45(org.cadixdev.bombe.type.Type type0) {
      if (type0 == VoidType.INSTANCE) {
         return Optional.empty();
      } else if (type0 == BaseType.DOUBLE) {
         return Optional.of(14);
      } else if (type0 == BaseType.LONG) {
         return Optional.of(9);
      } else if (type0 == BaseType.BOOLEAN || type0 == BaseType.INT || type0 == BaseType.SHORT || type0 == BaseType.BYTE) {
         return Optional.of(3);
      } else {
         return type0 == BaseType.FLOAT ? Optional.of(11) : Optional.of(1);
      }
   }

   public static int method46(org.cadixdev.bombe.type.Type type0) {
      if (type0 == BaseType.DOUBLE) {
         return 24;
      } else if (type0 == BaseType.LONG) {
         return 22;
      } else if (type0 == BaseType.BOOLEAN || type0 == BaseType.INT || type0 == BaseType.SHORT || type0 == BaseType.BYTE) {
         return 21;
      } else {
         return type0 == BaseType.FLOAT ? 23 : 25;
      }
   }

   public static AnnotationNode method47(Class<?> clazz0, Map<String, Object> map1) {
      AnnotationNode annotation2 = new AnnotationNode("L" + clazz0.getName().replace('.', '/') + ";");
      annotation2.values = new ArrayList();
      map1.forEach((arg1x, arg2x) -> {
         annotation2.values.add(arg1x);
         annotation2.values.add(arg2x);
      });
      return annotation2;
   }

   public static FieldNode method48(FieldNode field0) {
      return new FieldNode(field0.access, field0.name, field0.desc, field0.signature, field0.value);
   }

   @KeepName
   public static boolean isRecordLazy(Class<?> clazz0) {
      return clazz0.getSuperclass() == Record.class;
   }

   @Generated
   private AsmUtils() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
