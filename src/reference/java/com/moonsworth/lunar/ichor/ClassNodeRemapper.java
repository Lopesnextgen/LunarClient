package com.moonsworth.lunar.ichor;

import java.util.List;
import lombok.Generated;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.Handle;
import org.objectweb.asm.commons.ModuleHashesAttribute;
import org.objectweb.asm.commons.Remapper;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.FrameNode;
import org.objectweb.asm.tree.InnerClassNode;
import org.objectweb.asm.tree.InvokeDynamicInsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.LocalVariableNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.ModuleExportNode;
import org.objectweb.asm.tree.ModuleNode;
import org.objectweb.asm.tree.ModuleOpenNode;
import org.objectweb.asm.tree.ModuleProvideNode;
import org.objectweb.asm.tree.ModuleRequireNode;
import org.objectweb.asm.tree.MultiANewArrayInsnNode;
import org.objectweb.asm.tree.RecordComponentNode;
import org.objectweb.asm.tree.TryCatchBlockNode;
import org.objectweb.asm.tree.TypeInsnNode;

public class ClassNodeRemapper {
   private static final boolean field1 = false;
   protected final ClassNode field2;
   protected final Remapper field3;

   public static ClassNode method1(IchorPipeline var0, ClassNode var1, Remapper var2) {
      long var3 = System.currentTimeMillis();
      new ClassNodeRemapper(var1, var2).run();
      long var5 = System.currentTimeMillis() - var3;
      if (IchorPipeline.field1) {
         var0.method40().field5.compute(var1.name, (var2x, var3x) -> var3x == null ? var5 : var3x + var5);
      }

      return var1;
   }

   public static void method2(ClassNode var0, MethodNode var1, Remapper var2) {
      ClassNodeRemapper var3 = new ClassNodeRemapper(var0, var2);
      var3.new Data3(var1).run();
   }

   public static void method3(ClassNode var0, Remapper var1) {
      new ClassNodeRemapper(var0, var1).run();
   }

   public void run() {
      this.method4();
      this.method5();
      this.method6();
      this.method7();
      this.method8();
      this.method9();
      this.method10();
      this.method11();
      this.method12();
      this.remap();
   }

   protected void remap() {
      this.field2.name = this.field3.mapType(this.field2.name);
      this.field2.signature = this.field3.mapSignature(this.field2.signature, false);
      this.field2.superName = this.field3.mapType(this.field2.superName);
      if (this.field2.interfaces != null) {
         for (int var1 = 0; var1 < this.field2.interfaces.size(); var1++) {
            this.field2.interfaces.set(var1, this.field3.mapType((String)this.field2.interfaces.get(var1)));
         }
      }

      this.method13(this.field2.visibleAnnotations, this.field2.invisibleAnnotations);
      this.method13(this.field2.visibleTypeAnnotations, this.field2.invisibleTypeAnnotations);
   }

   protected void method4() {
      if (this.field2.module != null) {
         new ClassNodeRemapper.Data4(this.field2.module).run();
      }
   }

   protected void method5() {
      if (this.field2.attrs != null) {
         for (Attribute var2 : this.field2.attrs) {
            if (var2 instanceof ModuleHashesAttribute var3) {
               List var4 = var3.modules;

               for (int var5 = 0; var5 < var4.size(); var5++) {
                  var4.set(var5, this.field3.mapModuleName((String)var4.get(var5)));
               }
            }
         }
      }
   }

   protected void method6() {
      if (this.field2.recordComponents != null) {
         for (RecordComponentNode var2 : this.field2.recordComponents) {
            new ClassNodeRemapper.Data5(var2).run();
         }
      }
   }

   protected void method7() {
      for (FieldNode var2 : this.field2.fields) {
         new ClassNodeRemapper.Data(var2).run();
      }
   }

   protected void method8() {
      for (MethodNode var2 : this.field2.methods) {
         new ClassNodeRemapper.Data3(var2).run();
      }
   }

   protected void method9() {
      for (int var1 = 0; var1 < this.field2.innerClasses.size(); var1++) {
         InnerClassNode var2 = (InnerClassNode)this.field2.innerClasses.get(var1);
         String var3 = var2.name;
         String var4 = var2.outerName;
         String var5 = var2.innerName;
         var2.name = this.field3.mapType(var3);
         if (var4 != null) {
            var2.outerName = this.field3.mapType(var4);
         }

         if (var5 != null) {
            var2.innerName = this.field3.mapInnerClassName(var3, var4, var5);
         }
      }
   }

   protected void method10() {
      if (this.field2.outerClass != null) {
         this.field2.outerClass = this.field3.mapType(this.field2.outerClass);
      }

      if (this.field2.outerMethod != null) {
         this.field2.outerMethod = this.field3.mapMethodName(this.field2.outerClass, this.field2.outerMethod, this.field2.outerMethodDesc);
      }

      if (this.field2.outerMethodDesc != null) {
         this.field2.outerMethodDesc = this.field3.mapMethodDesc(this.field2.outerMethodDesc);
      }
   }

   protected void method11() {
      if (this.field2.nestHostClass != null) {
         this.field2.nestHostClass = this.field3.mapType(this.field2.nestHostClass);
      }

      if (this.field2.nestMembers != null) {
         for (int var1 = 0; var1 < this.field2.nestMembers.size(); var1++) {
            this.field2.nestMembers.set(var1, this.field3.mapType((String)this.field2.nestMembers.get(var1)));
         }
      }
   }

   protected void method12() {
      if (this.field2.permittedSubclasses != null) {
         for (int var1 = 0; var1 < this.field2.permittedSubclasses.size(); var1++) {
            this.field2.permittedSubclasses.set(var1, this.field3.mapType((String)this.field2.permittedSubclasses.get(var1)));
         }
      }
   }

   protected <T extends AnnotationNode> void method13(List<T> var1, List<T> var2) {
      if (var1 != null) {
         for (AnnotationNode var4 : var1) {
            new ClassNodeRemapper.Data2(var4).run();
         }
      }

      if (var2 != null) {
         for (AnnotationNode var6 : var2) {
            new ClassNodeRemapper.Data2(var6).run();
         }
      }
   }

   @Generated
   public ClassNodeRemapper(ClassNode var1, Remapper var2) {
      this.field2 = var1;
      this.field3 = var2;
   }

   public class Data {
      protected final FieldNode field1;

      public void run() {
         this.field1.name = ClassNodeRemapper.this.field3.mapFieldName(ClassNodeRemapper.this.field2.name, this.field1.name, this.field1.desc);
         this.field1.desc = ClassNodeRemapper.this.field3.mapDesc(this.field1.desc);
         this.field1.signature = ClassNodeRemapper.this.field3.mapSignature(this.field1.signature, true);
         if (this.field1.value != null) {
            this.field1.value = ClassNodeRemapper.this.field3.mapValue(this.field1.value);
         }

         ClassNodeRemapper.this.method13(this.field1.visibleAnnotations, this.field1.invisibleAnnotations);
         ClassNodeRemapper.this.method13(this.field1.visibleTypeAnnotations, this.field1.invisibleTypeAnnotations);
      }

      @Generated
      public Data(FieldNode var2) {
         this.field1 = var2;
      }
   }

   public class Data2 {
      private final AnnotationNode field1;

      public void run() {
         this.field1.desc = ClassNodeRemapper.this.field3.mapDesc(this.field1.desc);
         boolean var1 = this.field1.desc.contains("kotlin/Metadata");
         if (this.field1.values != null) {
            for (int var2 = 0; var2 < this.field1.values.size(); var2++) {
               Object var3 = ClassNodeRemapper.this.field3.mapValue(this.field1.values.get(var2));
               if (var1 && var3 instanceof List var4) {
                  for (int var5 = 0; var5 < var4.size(); var5++) {
                     if (var4.get(var5) instanceof String var7) {
                        String var8 = var7;

                        try {
                           if (var7.startsWith("(")) {
                              var8 = ClassNodeRemapper.this.field3.mapMethodDesc(var7);
                           } else if (var7.startsWith("L") && var7.startsWith(";")) {
                              var8 = ClassNodeRemapper.this.field3.mapType(var7);
                           }
                        } catch (Exception var10) {
                        }

                        if (!var8.equals(var7)) {
                           var4.set(var5, var8);
                        }
                     }
                  }
               }

               this.field1.values.set(var2, var3);
            }
         }
      }

      @Generated
      public Data2(AnnotationNode var2) {
         this.field1 = var2;
      }
   }

   public class Data3 {
      protected final MethodNode field1;

      public void run() {
         this.field1.name = ClassNodeRemapper.this.field3.mapMethodName(ClassNodeRemapper.this.field2.name, this.field1.name, this.field1.desc);
         this.field1.desc = ClassNodeRemapper.this.field3.mapMethodDesc(this.field1.desc);

         try {
            this.field1.signature = ClassNodeRemapper.this.field3.mapSignature(this.field1.signature, false);
         } catch (Exception var3) {
         }

         if (this.field1.exceptions != null) {
            for (int var1 = 0; var1 < this.field1.exceptions.size(); var1++) {
               this.field1.exceptions.set(var1, ClassNodeRemapper.this.field3.mapType((String)this.field1.exceptions.get(var1)));
            }
         }

         ClassNodeRemapper.this.method13(this.field1.visibleAnnotations, this.field1.invisibleAnnotations);
         ClassNodeRemapper.this.method13(this.field1.visibleTypeAnnotations, this.field1.invisibleTypeAnnotations);
         if (this.field1.visibleParameterAnnotations != null) {
            for (int var4 = 0; var4 < this.field1.visibleParameterAnnotations.length; var4++) {
               List var2 = this.field1.visibleParameterAnnotations[var4];
               ClassNodeRemapper.this.method13(var2, null);
            }
         }

         if (this.field1.instructions != null) {
            AbstractInsnNode var5 = this.field1.instructions.getFirst();
            AbstractInsnNode var8 = this.field1.instructions.getLast();

            while (var5 != null) {
               this.method1(var5);
               if (var5 == var8) {
                  break;
               }

               var5 = var5.getNext();
            }
         }

         if (this.field1.tryCatchBlocks != null) {
            for (int var6 = 0; var6 < this.field1.tryCatchBlocks.size(); var6++) {
               TryCatchBlockNode var9 = (TryCatchBlockNode)this.field1.tryCatchBlocks.get(var6);
               var9.type = ClassNodeRemapper.this.field3.mapType(var9.type);
               ClassNodeRemapper.this.method13(var9.visibleTypeAnnotations, var9.invisibleTypeAnnotations);
            }
         }

         if (this.field1.localVariables != null) {
            for (int var7 = 0; var7 < this.field1.localVariables.size(); var7++) {
               LocalVariableNode var10 = (LocalVariableNode)this.field1.localVariables.get(var7);
               var10.desc = ClassNodeRemapper.this.field3.mapDesc(var10.desc);
               var10.signature = ClassNodeRemapper.this.field3.mapSignature(var10.signature, true);
            }
         }

         ClassNodeRemapper.this.method13(this.field1.visibleLocalVariableAnnotations, this.field1.invisibleLocalVariableAnnotations);
      }

      protected void method1(AbstractInsnNode var1) {
         ClassNodeRemapper.this.method13(var1.visibleTypeAnnotations, var1.invisibleTypeAnnotations);
         if (var1 instanceof FrameNode var2) {
            if (var2.local != null) {
               for (int var9 = 0; var9 < var2.local.size(); var9++) {
                  if (var2.local.get(var9) instanceof String var11) {
                     var2.local.set(var9, ClassNodeRemapper.this.field3.mapType(var11));
                  }
               }
            }

            if (var2.stack != null) {
               for (int var12 = 0; var12 < var2.stack.size(); var12++) {
                  if (var2.stack.get(var12) instanceof String var17) {
                     var2.stack.set(var12, ClassNodeRemapper.this.field3.mapType(var17));
                  }
               }
            }
         } else if (var1 instanceof FieldInsnNode var3) {
            String var13 = var3.owner;
            var3.owner = ClassNodeRemapper.this.field3.mapType(var13);
            var3.name = ClassNodeRemapper.this.field3.mapFieldName(var13, var3.name, var3.desc);
            var3.desc = ClassNodeRemapper.this.field3.mapDesc(var3.desc);
         } else if (var1 instanceof MethodInsnNode var4) {
            String var14 = var4.owner;
            var4.owner = ClassNodeRemapper.this.field3.mapType(var14);
            var4.name = ClassNodeRemapper.this.field3.mapMethodName(var14, var4.name, var4.desc);
            var4.desc = ClassNodeRemapper.this.field3.mapDesc(var4.desc);
         } else if (var1 instanceof InvokeDynamicInsnNode var5) {
            var5.name = ClassNodeRemapper.this.field3.mapInvokeDynamicMethodName(var5.name, var5.desc, var5.bsm, var5.bsmArgs);
            var5.desc = ClassNodeRemapper.this.field3.mapDesc(var5.desc);
            var5.bsm = (Handle)ClassNodeRemapper.this.field3.mapValue(var5.bsm);
            if (var5.bsmArgs != null) {
               for (int var15 = 0; var15 < var5.bsmArgs.length; var15++) {
                  var5.bsmArgs[var15] = ClassNodeRemapper.this.field3.mapValue(var5.bsmArgs[var15]);
               }
            }
         } else if (var1 instanceof TypeInsnNode var6) {
            var6.desc = ClassNodeRemapper.this.field3.mapType(var6.desc);
         } else if (var1 instanceof LdcInsnNode var7) {
            var7.cst = ClassNodeRemapper.this.field3.mapValue(var7.cst);
         } else if (var1 instanceof MultiANewArrayInsnNode var8) {
            var8.desc = ClassNodeRemapper.this.field3.mapDesc(var8.desc);
         }
      }

      @Generated
      public Data3(MethodNode var2) {
         this.field1 = var2;
      }
   }

   public class Data4 {
      protected final ModuleNode field1;

      public void run() {
         this.field1.name = ClassNodeRemapper.this.field3.mapModuleName(this.field1.name);
         this.field1.mainClass = ClassNodeRemapper.this.field3.mapType(this.field1.mainClass);
         if (this.field1.packages != null) {
            for (int var1 = 0; var1 < this.field1.packages.size(); var1++) {
               this.field1.packages.set(var1, ClassNodeRemapper.this.field3.mapPackageName((String)this.field1.packages.get(var1)));
            }
         }

         if (this.field1.requires != null) {
            for (ModuleRequireNode var2 : this.field1.requires) {
               var2.module = ClassNodeRemapper.this.field3.mapModuleName(var2.module);
            }
         }

         if (this.field1.exports != null) {
            for (ModuleExportNode var9 : this.field1.exports) {
               var9.packaze = ClassNodeRemapper.this.field3.mapPackageName(var9.packaze);

               for (int var3 = 0; var3 < var9.modules.size(); var3++) {
                  var9.modules.set(var3, ClassNodeRemapper.this.field3.mapModuleName((String)var9.modules.get(var3)));
               }
            }
         }

         if (this.field1.opens != null) {
            for (ModuleOpenNode var10 : this.field1.opens) {
               var10.packaze = ClassNodeRemapper.this.field3.mapPackageName(var10.packaze);

               for (int var12 = 0; var12 < var10.modules.size(); var12++) {
                  var10.modules.set(var12, ClassNodeRemapper.this.field3.mapModuleName((String)var10.modules.get(var12)));
               }
            }
         }

         if (this.field1.uses != null) {
            for (int var7 = 0; var7 < this.field1.uses.size(); var7++) {
               this.field1.uses.set(var7, ClassNodeRemapper.this.field3.mapType((String)this.field1.uses.get(var7)));
            }
         }

         if (this.field1.provides != null) {
            for (ModuleProvideNode var11 : this.field1.provides) {
               var11.service = ClassNodeRemapper.this.field3.mapType(var11.service);

               for (int var13 = 0; var13 < var11.providers.size(); var13++) {
                  var11.providers.set(var13, ClassNodeRemapper.this.field3.mapType((String)var11.providers.get(var13)));
               }
            }
         }
      }

      @Generated
      public Data4(ModuleNode var2) {
         this.field1 = var2;
      }
   }

   public class Data5 {
      protected final RecordComponentNode field1;

      public void run() {
         this.field1.name = ClassNodeRemapper.this.field3.mapRecordComponentName(ClassNodeRemapper.this.field2.name, this.field1.name, this.field1.descriptor);
         this.field1.descriptor = ClassNodeRemapper.this.field3.mapDesc(this.field1.descriptor);
         this.field1.signature = ClassNodeRemapper.this.field3.mapSignature(this.field1.signature, true);
         ClassNodeRemapper.this.method13(this.field1.visibleAnnotations, this.field1.invisibleAnnotations);
         ClassNodeRemapper.this.method13(this.field1.visibleTypeAnnotations, this.field1.invisibleTypeAnnotations);
      }

      @Generated
      public Data5(RecordComponentNode var2) {
         this.field1 = var2;
      }
   }
}
