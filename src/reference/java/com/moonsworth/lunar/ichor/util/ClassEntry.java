package com.moonsworth.lunar.ichor.util;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;

public class ClassEntry {
   private ClassNode classNode;
   private byte[] field1;
   private String className;

   public ClassEntry(byte[] items1) {
      this.field1 = items1;
      ClassReader classreader2 = new ClassReader(this.field1);
      this.className = classreader2.readClass(classreader2.header + 2, new char[classreader2.getMaxStringLength()]);
   }

   public ClassEntry(ClassNode node1) {
      this.classNode = node1;
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

   public void method2(ClassNode node1) {
      this.classNode = node1;
      this.field1 = null;
      this.className = null;
   }

   public void method3(byte[] items1, String text) {
      this.field1 = items1;
      this.className = text;
      this.classNode = null;
   }

   public void method4(int value) {
      if (this.field1 == null) {
         throw new IllegalStateException("Trying to parse class node too late");
      }

      this.classNode = AsmUtils.method16(this.field1, value);
      this.field1 = null;
      this.className = null;
   }

   public String getClassName() {
      return this.className == null ? this.classNode.name : this.className;
   }
}
