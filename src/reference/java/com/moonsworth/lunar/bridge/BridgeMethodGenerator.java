package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.config.Config;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import lombok.Generated;
import org.cadixdev.bombe.provider.ClassProvider;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;

public class BridgeMethodGenerator {
   private final Queue<MethodParameter> field1 = new ArrayDeque<>();
   private final Stack<com.moonsworth.lunar.ichor.mixin.MixinHelper3> field2 = new Stack<>();
   private final Bridge3_5 field3;
   private final Config field4;
   private final ClassProvider field5;
   private final ClassNode field6;
   private final MixinHelper2_2 field7;
   private final Bridge2_24 field8;
   private InsnList instructions = new InsnList();

   public BridgeMethodGenerator(Bridge3_5 var1, Bridge2_24 var2, Config var3, ClassProvider provider, ClassNode type) {
      this.field3 = var1;
      this.field4 = var3;
      this.field5 = provider;
      this.field6 = type;
      this.field7 = new MixinHelper2_2(var2, var3, provider);
      this.field8 = var2;
   }

   public InsnList method1() {
      this.method2(new SignatureEmitter(this.field3));
      return this.instructions;
   }

   public void method2(BytecodeEmitter var1) {
      try {
         var1.method1(this, this.field1, this.field2, this.instructions);
      } catch (Exception var3) {
         throw new Bridge$Data(var1, var3);
      }
   }

   public void method3(BytecodeEmitter var1, InsnList var2) {
      InsnList var3 = this.instructions;

      try {
         this.instructions = var2;
         this.method2(var1);
      } finally {
         this.instructions = var3;
      }
   }

   @Generated
   public Config method4() {
      return this.field4;
   }

   @Generated
   public ClassProvider method5() {
      return this.field5;
   }

   @Generated
   public ClassNode getTargetClassNode() {
      return this.field6;
   }

   @Generated
   public MixinHelper2_2 method6() {
      return this.field7;
   }

   @Generated
   public Bridge2_24 method7() {
      return this.field8;
   }
}
