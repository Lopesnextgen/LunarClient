package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.mixin.MethodSignature;
import com.moonsworth.lunar.ichor.mixin.MixinHelper3;
import com.moonsworth.lunar.ichor.mixin.GenericSignatureParser;
import java.util.Objects;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

public class MixinTargetMember {
   private final ClassNode field1;
   private final MixinHelper3 field2;
   private final FieldNode field3;
   private final MethodNode field4;
   private final MethodSignature field5;
   private final boolean field6;

   public MixinTargetMember(ClassNode node1, MixinHelper3 mixinhelper32, FieldNode field3_, MethodNode method4_, MethodSignature mixinhelper25, boolean flag) {
      this.field1 = node1;
      this.field2 = mixinhelper32;
      this.field3 = field3_;
      this.field4 = method4_;
      this.field5 = mixinhelper25;
      this.field6 = flag;
   }

   public MixinHelper3 method1() {
      return this.field3 != null
         ? GenericSignatureParser.method2(Objects.requireNonNullElse(this.field3.signature, this.field3.desc), this.field1.signature, this.field2)
         : this.field5.method2();
   }

   public ClassNode method2() {
      return this.field1;
   }

   public MixinHelper3 method3() {
      return this.field2;
   }

   public FieldNode method4() {
      return this.field3;
   }

   public MethodNode method5() {
      return this.field4;
   }

   public MethodSignature method6() {
      return this.field5;
   }

   public boolean isInterface() {
      return this.field6;
   }
}
