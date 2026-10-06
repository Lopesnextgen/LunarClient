package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.mixin.SignatureVisitorImpl;
import com.moonsworth.lunar.ichor.util.FatalIchorError6;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;
import org.cadixdev.bombe.provider.ClassProvider;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class GroupingEmitter implements BytecodeEmitter {
   private final com.moonsworth.lunar.ichor.mixin.MixinHelper3 field1;
   private final List<BytecodeEmitter> field2;
   private final ClassProvider field3;
   private static final String field4 = Type.getInternalName(Bridge4_17.class);
   private static volatile Map<String, List<GroupingEmitter.Data>> field5 = null;

   public GroupingEmitter(com.moonsworth.lunar.ichor.mixin.MixinHelper3 var1, List<BytecodeEmitter> var2, ClassProvider var3) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
   }

   @Override
   public void method1(BridgeMethodGenerator var1, Queue<MethodParameter> var2, Stack<com.moonsworth.lunar.ichor.mixin.MixinHelper3> var3, InsnList var4) {
      this.method2();
      if (this.field2.size() <= 1) {
         for (BytecodeEmitter var15 : this.field2) {
            var1.method2(var15);
         }
      } else {
         ArrayList var5 = new ArrayList();
         ArrayList var6 = new ArrayList();

         for (BytecodeEmitter var8 : this.field2) {
            InsnList var9 = new InsnList();
            var1.method3(var8, var9);
            var5.add(var9);
            var6.add((com.moonsworth.lunar.ichor.mixin.MixinHelper3)var3.pop());
         }

         label47:
         for (GroupingEmitter.Data var17 : field5.get(this.field1.toString())) {
            ArrayList var18 = new ArrayList();
            int var10 = 0;

            for (com.moonsworth.lunar.ichor.mixin.MixinHelper3 var12 : var17.method1().method1()) {
               InsnList var13 = new InsnList();
               if (!var1.method6().method4((com.moonsworth.lunar.ichor.mixin.MixinHelper3)var6.get(var10), var12, var13)) {
                  continue label47;
               }

               var18.add(var13);
               var10++;
            }

            for (int var19 = 0; var19 < this.field2.size(); var19++) {
               var4.add((InsnList)var5.get(var19));
               var4.add((InsnList)var18.get(var19));
            }

            var4.add(new MethodInsnNode(184, field4, var17.name(), var17.method1().getDescriptor(), false));
            var3.push(this.field1);
            return;
         }

         throw new NoSuchElementException("Could not find a valid grouping to convert from %s to %s!".formatted(var6, this.field1));
      }
   }

   private void method2() {
      if (field5 == null) {
         synchronized (GroupingEmitter.class) {
            if (field5 == null) {
               HashMap var2 = new HashMap();
               ClassNode var3 = this.field3.getAsNode(field4, 1);

               for (MethodNode var5 : var3.methods) {
                  if (FatalIchorError6.isStatic(var5.access) && Type.getReturnType(var5.desc) != Type.VOID_TYPE) {
                     com.moonsworth.lunar.ichor.mixin.MixinHelper2 var6 = SignatureVisitorImpl.method1(
                        Objects.requireNonNullElse(var5.signature, var5.desc), var3.signature, null
                     );
                     String var7 = var6.method2().toString();
                     var2.compute(
                        var7,
                        (var2x, var3x) -> {
                           if (var3x == null) {
                              var3x = new ArrayList();
                              var3x.add(new GroupingEmitter.Data(var5.name, var6));
                           } else {
                              for (GroupingEmitter.Data var5x : var3x) {
                                 if (var5x.method1().method1().equals(var6.method1())) {
                                    throw new IllegalStateException(
                                       String.format(
                                          "Both %s and %s in GroupingUtil share the signature %s. Ensure only one method exists per signature!",
                                          var5x.field1,
                                          var5.name,
                                          var6
                                       )
                                    );
                                 }
                              }
                           }

                           return (List)var3x;
                        }
                     );
                  }
               }

               field5 = var2;
            }
         }
      }
   }

   public com.moonsworth.lunar.ichor.mixin.MixinHelper3 method3() {
      return this.field1;
   }

   public List<BytecodeEmitter> method4() {
      return this.field2;
   }

   public ClassProvider method5() {
      return this.field3;
   }

   private class Data {
      private final String field1;
      private final com.moonsworth.lunar.ichor.mixin.MixinHelper2 field2;

      private Data(String var1, com.moonsworth.lunar.ichor.mixin.MixinHelper2 var2) {
         this.field1 = var1;
         this.field2 = var2;
      }

      public String name() {
         return this.field1;
      }

      public com.moonsworth.lunar.ichor.mixin.MixinHelper2 method1() {
         return this.field2;
      }
   }
}
