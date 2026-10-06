package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.mixin.SignatureVisitorImpl;
import java.util.Objects;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

public class TargetPathMember {
   private final ClassNode field1;
   private final com.moonsworth.lunar.ichor.mixin.MixinHelper3 field2;
   private final FieldNode field3;
   private final MethodNode field4;
   private final com.moonsworth.lunar.ichor.mixin.MixinHelper2 field5;
   private final boolean field6;

   public TargetPathMember(
      ClassNode type,
      com.moonsworth.lunar.ichor.mixin.MixinHelper3 mixinHelper3,
      FieldNode fieldNode,
      MethodNode methodNode,
      com.moonsworth.lunar.ichor.mixin.MixinHelper2 mixinHelper2,
      boolean flag
   ) {
      this.field1 = type;
      this.field2 = mixinHelper3;
      this.field3 = fieldNode;
      this.field4 = methodNode;
      this.field5 = mixinHelper2;
      this.field6 = flag;
   }

   public com.moonsworth.lunar.ichor.mixin.MixinHelper3 method1() {
      return this.field3 != null
         ? SignatureVisitorImpl.method2(Objects.requireNonNullElse(this.field3.signature, this.field3.desc), this.field1.signature, this.field2)
         : this.field5.method2();
   }

   public ClassNode method2() {
      return this.field1;
   }

   public com.moonsworth.lunar.ichor.mixin.MixinHelper3 method3() {
      return this.field2;
   }

   public FieldNode method4() {
      return this.field3;
   }

   public MethodNode method5() {
      return this.field4;
   }

   public com.moonsworth.lunar.ichor.mixin.MixinHelper2 method6() {
      return this.field5;
   }

   public boolean isInterface() {
      return this.field6;
   }
}
