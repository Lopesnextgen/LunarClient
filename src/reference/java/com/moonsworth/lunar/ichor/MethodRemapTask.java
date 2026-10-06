package com.moonsworth.lunar.ichor;

import java.util.List;
import lombok.Generated;
import org.objectweb.asm.Handle;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FrameNode;
import org.objectweb.asm.tree.InvokeDynamicInsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.LocalVariableNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.MultiANewArrayInsnNode;
import org.objectweb.asm.tree.TryCatchBlockNode;
import org.objectweb.asm.tree.TypeInsnNode;

public class MethodRemapTask {
   protected final MethodNode field1;

   public void run() {
      this.field1.name = this.field2.field3.mapMethodName(this.field2.field2.name, this.field1.name, this.field1.desc);
      this.field1.desc = this.field2.field3.mapMethodDesc(this.field1.desc);

      try {
         this.field1.signature = this.field2.field3.mapSignature(this.field1.signature, false);
      } catch (Exception exception3) {
      }

      if (this.field1.exceptions != null) {
         for (int index1 = 0; index1 < this.field1.exceptions.size(); index1++) {
            this.field1.exceptions.set(index1, this.field2.field3.mapType((String)this.field1.exceptions.get(index1)));
         }
      }

      this.field2.method13(this.field1.visibleAnnotations, this.field1.invisibleAnnotations);
      this.field2.method13(this.field1.visibleTypeAnnotations, this.field1.invisibleTypeAnnotations);
      if (this.field1.visibleParameterAnnotations != null) {
         for (int index4 = 0; index4 < this.field1.visibleParameterAnnotations.length; index4++) {
            List list2 = this.field1.visibleParameterAnnotations[index4];
            this.field2.method13(list2, null);
         }
      }

      if (this.field1.instructions != null) {
         AbstractInsnNode instruction5 = this.field1.instructions.getFirst();
         AbstractInsnNode instruction8 = this.field1.instructions.getLast();

         while (instruction5 != null) {
            this.method1(instruction5);
            if (instruction5 == instruction8) {
               break;
            }

            instruction5 = instruction5.getNext();
         }
      }

      if (this.field1.tryCatchBlocks != null) {
         for (int index6 = 0; index6 < this.field1.tryCatchBlocks.size(); index6++) {
            TryCatchBlockNode trycatchblocknode9 = (TryCatchBlockNode)this.field1.tryCatchBlocks.get(index6);
            trycatchblocknode9.type = this.field2.field3.mapType(trycatchblocknode9.type);
            this.field2.method13(trycatchblocknode9.visibleTypeAnnotations, trycatchblocknode9.invisibleTypeAnnotations);
         }
      }

      if (this.field1.localVariables != null) {
         for (int index7 = 0; index7 < this.field1.localVariables.size(); index7++) {
            LocalVariableNode localvariablenode10 = (LocalVariableNode)this.field1.localVariables.get(index7);
            localvariablenode10.desc = this.field2.field3.mapDesc(localvariablenode10.desc);
            localvariablenode10.signature = this.field2.field3.mapSignature(localvariablenode10.signature, true);
         }
      }

      this.field2.method13(this.field1.visibleLocalVariableAnnotations, this.field1.invisibleLocalVariableAnnotations);
   }

   protected void method1(AbstractInsnNode instruction1) {
      this.field2.method13(instruction1.visibleTypeAnnotations, instruction1.invisibleTypeAnnotations);
      if (instruction1 instanceof FrameNode framenode2) {
         if (framenode2.local != null) {
            for (int index9 = 0; index9 < framenode2.local.size(); index9++) {
               if (framenode2.local.get(index9) instanceof String text11) {
                  framenode2.local.set(index9, this.field2.field3.mapType(text11));
               }
            }
         }

         if (framenode2.stack != null) {
            for (int index12 = 0; index12 < framenode2.stack.size(); index12++) {
               if (framenode2.stack.get(index12) instanceof String text17) {
                  framenode2.stack.set(index12, this.field2.field3.mapType(text17));
               }
            }
         }
      } else if (instruction1 instanceof FieldInsnNode fieldinsnnode3) {
         String text13 = fieldinsnnode3.owner;
         fieldinsnnode3.owner = this.field2.field3.mapType(text13);
         fieldinsnnode3.name = this.field2.field3.mapFieldName(text13, fieldinsnnode3.name, fieldinsnnode3.desc);
         fieldinsnnode3.desc = this.field2.field3.mapDesc(fieldinsnnode3.desc);
      } else if (instruction1 instanceof MethodInsnNode methodinsnnode4) {
         String text14 = methodinsnnode4.owner;
         methodinsnnode4.owner = this.field2.field3.mapType(text14);
         methodinsnnode4.name = this.field2.field3.mapMethodName(text14, methodinsnnode4.name, methodinsnnode4.desc);
         methodinsnnode4.desc = this.field2.field3.mapDesc(methodinsnnode4.desc);
      } else if (instruction1 instanceof InvokeDynamicInsnNode invokedynamicinsnnode5) {
         invokedynamicinsnnode5.name = this.field2.field3.mapInvokeDynamicMethodName(invokedynamicinsnnode5.name, invokedynamicinsnnode5.desc, invokedynamicinsnnode5.bsm, invokedynamicinsnnode5.bsmArgs);
         invokedynamicinsnnode5.desc = this.field2.field3.mapDesc(invokedynamicinsnnode5.desc);
         invokedynamicinsnnode5.bsm = (Handle)this.field2.field3.mapValue(invokedynamicinsnnode5.bsm);
         if (invokedynamicinsnnode5.bsmArgs != null) {
            for (int index15 = 0; index15 < invokedynamicinsnnode5.bsmArgs.length; index15++) {
               invokedynamicinsnnode5.bsmArgs[index15] = this.field2.field3.mapValue(invokedynamicinsnnode5.bsmArgs[index15]);
            }
         }
      } else if (instruction1 instanceof TypeInsnNode typeinsnnode6) {
         typeinsnnode6.desc = this.field2.field3.mapType(typeinsnnode6.desc);
      } else if (instruction1 instanceof LdcInsnNode ldcinsnnode7) {
         ldcinsnnode7.cst = this.field2.field3.mapValue(ldcinsnnode7.cst);
      } else if (instruction1 instanceof MultiANewArrayInsnNode multianewarrayinsnnode8) {
         multianewarrayinsnnode8.desc = this.field2.field3.mapDesc(multianewarrayinsnnode8.desc);
      }
   }

   @Generated
   public MethodRemapTask(ClassNodeRemapper mixinmisc31, MethodNode method2) {
      this.field2 = mixinmisc31;
      this.field1 = method2;
   }
}
