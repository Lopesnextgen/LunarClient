package com.moonsworth.lunar.ichor.util;

import java.util.Arrays;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.ClassRemapper;
import org.objectweb.asm.commons.MethodRemapper;
import org.objectweb.asm.commons.Remapper;

public class ClassRemapperImpl extends ClassRemapper {
   private String className;

   public ClassRemapperImpl(ClassVisitor var1, Remapper var2) {
      super(var1, var2);
   }

   public void visit(int var1, int var2, String var3, String var4, String var5, String[] var6) {
      super.visit(var1, var2, var3, var4, var5, var6);
      this.className = var3;
   }

   public MethodVisitor visitMethod(int var1, String var2, String var3, String var4, String[] var5) {
      String var6 = this.remapper.mapMethodDesc(var3);
      MethodVisitor var7 = super.visitMethod(
         var1,
         this.remapper.mapMethodName(this.className, var2, var3),
         var6,
         this.remapper.mapSignature(var4, false),
         var5 == null ? null : this.remapper.mapTypes(var5)
      );
      return new ClassRemapperImpl.Data(var2, var7, this.remapper);
   }

   public class Data extends MethodRemapper {
      private final String field1;

      public Data(String var2, MethodVisitor var3, Remapper var4) {
         super(var3, var4);
         this.field1 = var2;
      }

      public void visitFrame(int var1, int var2, Object[] var3, int var4, Object[] var5) {
         super.visitFrame(var1, var2, this.remapFrameTypes(var2, var3), var4, this.remapFrameTypes(var4, var5));
      }

      private Object[] remapFrameTypes(int var1, Object[] var2) {
         if (var2 == null) {
            return null;
         }

         Object[] var3 = null;
         boolean var4 = false;

         for (int var5 = 0; var5 < var1; var5++) {
            if (var2[var5] instanceof String var6) {
               if (var3 == null) {
                  var3 = new Object[var1];
                  System.arraycopy(var2, 0, var3, 0, var1);
               }

               String var9 = this.remapper.mapType(var6);
               if ((var6 + var9).contains("dcl")) {
                  var4 = true;
               }

               var3[var5] = var9;
            }
         }

         if (var4) {
            String var8 = ClassRemapperImpl.this.className;
            System.out.println("PatchedMethodRemapper.remapFrameTypes: " + var8 + "#" + this.field1);
            System.out.println("frameTypes = " + Arrays.toString(var2));
            System.out.println("remappedFrameTypes = " + Arrays.toString(var3));
         }

         return var3 == null ? var2 : var3;
      }
   }
}
