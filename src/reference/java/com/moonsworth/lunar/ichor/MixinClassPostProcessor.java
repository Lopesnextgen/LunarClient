package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.files.ValuePair;
import com.moonsworth.lunar.ichor.util.AsmUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.Handle;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FrameNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InvokeDynamicInsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LineNumberNode;
import org.objectweb.asm.tree.LookupSwitchInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TableSwitchInsnNode;
import org.objectweb.asm.tree.analysis.Analyzer;
import org.objectweb.asm.tree.analysis.BasicInterpreter;
import org.objectweb.asm.tree.analysis.BasicValue;
import org.objectweb.asm.tree.analysis.Frame;
import org.spongepowered.asm.mixin.Shadow;

public class MixinClassPostProcessor {
   private static final boolean field1 = false;

   public MixinClassPostProcessor() {
   }

   public static void method1(ClassNode node0, URLClassLoader urlclassloader1) {
      byte[] items2 = urlclassloader1.method16().method9(urlclassloader1, node0, false);
      ClassNode node3 = AsmUtils.method16(items2, 0);
      AsmUtils.method2(node0, node3);
      method2(node0);
      method4(node0);
      method10(node0);
   }

   private static void method2(ClassNode node0) {
      node0.fields.removeIf(arg0x -> AsmUtils.method25(arg0x, Shadow.class) != null && method3(arg0x.name));
      node0.methods.removeIf(arg0x -> AsmUtils.method25(arg0x, Shadow.class) != null && method3(arg0x.name));
   }

   private static boolean method3(String text0) {
      int index1 = text0.lastIndexOf("$v");
      return index1 != -1 && index1 < text0.length() - 2 && Character.isDigit(text0.charAt(index1 + 2));
   }

   private static void method4(ClassNode node0) {
      node0.methods.stream().filter(arg0x -> arg0x.name.endsWith("init>")).forEach(arg1 -> {
         for (ValuePair files6_24 : method5(node0, arg1)) {
            AbstractInsnNode instruction5 = (AbstractInsnNode)files6_24.field1;
            AbstractInsnNode instruction6 = (AbstractInsnNode)files6_24.field2;
            LabelNode labelnode7 = new LabelNode();
            arg1.instructions.insertBefore(instruction5, new JumpInsnNode(167, labelnode7));
            arg1.instructions.insert(instruction6, labelnode7);
         }
      });
   }

   private static List<ValuePair<AbstractInsnNode, AbstractInsnNode>> method5(ClassNode node0, MethodNode method1_) {
      try {
         ArrayList list2 = new ArrayList();
         Analyzer analyzer3 = new Analyzer(new BasicInterpreter());
         analyzer3.analyze(node0.name, method1_);
         Frame[] items4 = analyzer3.getFrames();

         for (AbstractInsnNode instruction6 : method1_.instructions) {
            if (instruction6 instanceof FieldInsnNode fieldinsnnode7 && method6(node0, fieldinsnnode7) && (instruction6.getOpcode() == 179 || instruction6.getOpcode() == 181)) {
               AbstractInsnNode instruction8 = method7(fieldinsnnode7, method1_.instructions, items4);
               if (instruction8 != null) {
                  list2.add(new ValuePair<>(instruction8, instruction6));
               }
            }
         }

         return list2;
      } catch (Throwable exception9) {
         throw exception9;
      }
   }

   private static boolean method6(ClassNode node0, FieldInsnNode fieldinsnnode1) {
      return fieldinsnnode1.owner.equals(node0.name) && node0.fields.stream().noneMatch(arg1x -> arg1x.name.equals(fieldinsnnode1.name));
   }

   @Nullable
   private static AbstractInsnNode method7(FieldInsnNode fieldinsnnode0, InsnList instructions1, Frame<BasicValue>[] items2) {
      AbstractInsnNode instruction3 = fieldinsnnode0.getPrevious();

      for (int index4 = instructions1.indexOf(fieldinsnnode0) - 1; instruction3 != null; index4--) {
         Frame frame5 = items2[index4];
         if (frame5 != null && frame5.getStackSize() == 0) {
            AbstractInsnNode instruction6 = method8(instruction3);
            if (!method9(instruction6, instructions1) && !(instruction6 instanceof TableSwitchInsnNode) && !(instruction6 instanceof LookupSwitchInsnNode)) {
               return instruction3;
            }
         } else if (frame5 == null) {
         }

         instruction3 = instruction3.getPrevious();
      }

      return null;
   }

   @Nullable
   private static AbstractInsnNode method8(AbstractInsnNode instruction0) {
      do {
         instruction0 = instruction0.getPrevious();
      } while (instruction0 instanceof LabelNode || instruction0 instanceof LineNumberNode || instruction0 instanceof FrameNode);

      return instruction0;
   }

   private static boolean method9(AbstractInsnNode instruction0, InsnList instructions1) {
      return instruction0 instanceof JumpInsnNode jumpinsnnode2 && instructions1.indexOf(jumpinsnnode2.label) > instructions1.indexOf(jumpinsnnode2);
   }

   private static void method10(ClassNode node0) {
      try {
         HashMap map1 = new HashMap();

         for (MethodNode method3_ : node0.methods) {
            if (method11(method3_)) {
               map1.put(method3_.name + method3_.desc, new ArrayList());
            }
         }

         if (!map1.isEmpty()) {
            for (MethodNode method15 : node0.methods) {
               Frame[] items4 = null;
               int index5 = 0;

               for (AbstractInsnNode instruction7 : method15.instructions) {
                  if (instruction7 instanceof InvokeDynamicInsnNode invokedynamicinsnnode8) {
                     if (items4 == null) {
                        Analyzer analyzer9 = new Analyzer(new BasicInterpreter());
                        analyzer9.analyze(node0.name, method15);
                        items4 = analyzer9.getFrames();
                     }

                     if (items4[index5] == null) {
                        continue;
                     }

                     String text25 = method12(invokedynamicinsnnode8, node0);
                     if (text25 != null) {
                        List list10 = (List)map1.get(text25);
                        if (list10 == null) {
                           ArrayList list11 = new ArrayList();
                           list11.add(method15.name + method15.desc);
                           map1.put(text25, list11);
                        } else {
                           list10.add(method15.name + method15.desc);
                        }
                     }
                  }

                  index5++;
               }
            }

            ArrayList list14 = new ArrayList();
            Iterator iterator16 = node0.methods.iterator();

            while (iterator16.hasNext()) {
               MethodNode method17 = (MethodNode)iterator16.next();
               String text19 = method17.name + method17.desc;
               List list21 = (List)map1.get(text19);
               if (list21 != null && list21.isEmpty()) {
                  list14.add(text19);
                  iterator16.remove();
                  map1.remove(text19);
               }
            }

            while (!list14.isEmpty()) {
               ArrayList list18 = new ArrayList(list14);
               list14.clear();

               for (String text22 : list18) {
                  Iterator iterator23 = map1.entrySet().iterator();

                  while (iterator23.hasNext()) {
                     Entry entry24 = (Entry)iterator23.next();
                     if (((List)entry24.getValue()).remove(text22) && ((List)entry24.getValue()).isEmpty()) {
                        iterator23.remove();
                        String text26 = (String)entry24.getKey();
                        list14.add(text26);
                        node0.methods.removeIf(arg1x -> (arg1x.name + arg1x.desc).equals(text26));
                     }
                  }
               }
            }
         }
      } catch (Throwable exception12) {
         throw exception12;
      }
   }

   private static boolean method11(MethodNode method0) {
      return (method0.access & 4096) != 0 && method0.name.startsWith("lambda$");
   }

   private static String method12(InvokeDynamicInsnNode invokedynamicinsnnode0, ClassNode node1) {
      if (invokedynamicinsnnode0.getOpcode() != 186) {
         return null;
      }

      if (invokedynamicinsnnode0.bsm.getOwner().equals("java/lang/invoke/LambdaMetafactory") && invokedynamicinsnnode0.bsm.getName().equals("metafactory")) {
         for (Object obj5 : invokedynamicinsnnode0.bsmArgs) {
            if (obj5 instanceof Handle handle6 && handle6.getOwner().equals(node1.name)) {
               return handle6.getName() + handle6.getDesc();
            }
         }

         return null;
      } else {
         return null;
      }
   }
}
