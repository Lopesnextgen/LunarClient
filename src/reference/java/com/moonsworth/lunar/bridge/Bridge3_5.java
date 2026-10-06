package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.util.AsmUtils;
import org.objectweb.asm.tree.MethodNode;

public class Bridge3_5 {
   private final Bridge4_2 field1;
   private final MethodNode field2;

   public Bridge3_5(Bridge4_2 bridge4_21, MethodNode method2_) {
      this.field1 = bridge4_21;
      this.field2 = method2_;
   }

   public boolean isStatic() {
      return AsmUtils.isStatic(this.field2.access);
   }

   public Bridge4_2 method1() {
      return this.field1;
   }

   public MethodNode method2() {
      return this.field2;
   }
}
