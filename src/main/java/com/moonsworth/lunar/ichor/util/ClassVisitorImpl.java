package com.moonsworth.lunar.ichor.util;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.JSRInlinerAdapter;

public class ClassVisitorImpl extends ClassVisitor {
   public ClassVisitorImpl(int var1, ClassVisitor var2) {
      super(var1, var2);
   }

   public MethodVisitor visitMethod(int var1, String var2, String text, String text2, String[] items) {
      return new JSRInlinerAdapter(super.visitMethod(var1, var2, text, text2, items), var1, var2, text, text2, items);
   }
}
