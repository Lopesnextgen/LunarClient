package com.moonsworth.lunar.ichor.util;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.JSRInlinerAdapter;

public class JsrInlinerClassVisitor extends ClassVisitor {
   public JsrInlinerClassVisitor(int number1, ClassVisitor classvisitor2) {
      super(number1, classvisitor2);
   }

   public MethodVisitor visitMethod(int number1, String text, String text2, String text3, String[] items5) {
      return new JSRInlinerAdapter(super.visitMethod(number1, text, text2, text3, items5), number1, text, text2, text3, items5);
   }
}
