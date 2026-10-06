package com.moonsworth.lunar.ichor.util;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;

public class FatalIchorError8 {
   private ClassNode classNode;
   private byte[] field1;
   private String className;

   public FatalIchorError8(byte[] var1) {
      this.field1 = var1;
      ClassReader var2 = new ClassReader(this.field1);
      this.className = var2.readClass(var2.header + 2, new char[var2.getMaxStringLength()]);
   }

   public FatalIchorError8(ClassNode var1) {
      this.classNode = var1;
   }

   public ClassNode getClassNode() {
      if (this.classNode == null) {
         throw new IllegalStateException("Trying to get class node too early");
      } else {
         return this.classNode;
      }
   }

   public byte[] getClassBytes() {
      if (this.field1 == null) {
         throw new IllegalStateException("Trying to get class node too late");
      } else {
         return this.field1;
      }
   }

   public boolean method1() {
      return this.classNode != null;
   }

   public void method2(ClassNode var1) {
      this.classNode = var1;
      this.field1 = null;
      this.className = null;
   }

   public void method3(byte[] var1, String var2) {
      this.field1 = var1;
      this.className = var2;
      this.classNode = null;
   }

   public void method4(int var1) {
      if (this.field1 == null) {
         throw new IllegalStateException("Trying to parse class node too late");
      }

      this.classNode = FatalIchorError6.method16(this.field1, var1);
      this.field1 = null;
      this.className = null;
   }

   public String getClassName() {
      return this.className == null ? this.classNode.name : this.className;
   }
}
