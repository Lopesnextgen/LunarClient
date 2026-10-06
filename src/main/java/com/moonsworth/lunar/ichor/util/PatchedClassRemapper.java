package com.moonsworth.lunar.ichor.util;

import java.util.Arrays;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.ClassRemapper;
import org.objectweb.asm.commons.MethodRemapper;
import org.objectweb.asm.commons.Remapper;

public class PatchedClassRemapper extends ClassRemapper {
   private String className;

   public PatchedClassRemapper(ClassVisitor classvisitor1, Remapper remapper2) {
      super(classvisitor1, remapper2);
   }

   public void visit(int number1, int number2, String text3, String text4, String text, String[] items6) {
      super.visit(number1, number2, text3, text4, text, items6);
      this.className = text3;
   }

   public MethodVisitor visitMethod(int number1, String text2, String text3, String text4, String[] items5) {
      String text6 = this.remapper.mapMethodDesc(text3);
      MethodVisitor methodvisitor7 = super.visitMethod(
         number1,
         this.remapper.mapMethodName(this.className, text2, text3),
         text6,
         this.remapper.mapSignature(text4, false),
         items5 == null ? null : this.remapper.mapTypes(items5)
      );
      return new PatchedClassRemapper.Data(text2, methodvisitor7, this.remapper);
   }

   public class Data extends MethodRemapper {
      private final String field1;

      public Data(String text2, MethodVisitor methodvisitor3, Remapper remapper4) {
         super(methodvisitor3, remapper4);
         this.field1 = text2;
      }

      public void visitFrame(int number1, int number2, Object[] items3, int value, Object[] items5) {
         super.visitFrame(number1, number2, this.remapFrameTypes(number2, items3), value, this.remapFrameTypes(value, items5));
      }

      private Object[] remapFrameTypes(int index1, Object[] items2) {
         if (items2 == null) {
            return null;
         }

         Object[] items3 = null;
         boolean flag4 = false;

         for (int index5 = 0; index5 < index1; index5++) {
            if (items2[index5] instanceof String text6) {
               if (items3 == null) {
                  items3 = new Object[index1];
                  System.arraycopy(items2, 0, items3, 0, index1);
               }

               String text9 = this.remapper.mapType(text6);
               if ((text6 + text9).contains("dcl")) {
                  flag4 = true;
               }

               items3[index5] = text9;
            }
         }

         if (flag4) {
            String text8 = PatchedClassRemapper.this.className;
            System.out.println("PatchedMethodRemapper.remapFrameTypes: " + text8 + "#" + this.field1);
            System.out.println("frameTypes = " + Arrays.toString(items2));
            System.out.println("remappedFrameTypes = " + Arrays.toString(items3));
         }

         return items3 == null ? items2 : items3;
      }
   }
}
