package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.files.Files6_2;
import com.moonsworth.lunar.ichor.util.FatalIchorError6;
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

public class MixinOther {
   private static final boolean field1 = false;

   public static void method1(ClassNode var0, URLClassLoader var1) {
      byte[] var2 = var1.method16().method9(var1, var0, false);
      ClassNode var3 = FatalIchorError6.method16(var2, 0);
      FatalIchorError6.method2(var0, var3);
      method2(var0);
      method4(var0);
      method10(var0);
   }

   private static void method2(ClassNode var0) {
      var0.fields.removeIf(var0x -> FatalIchorError6.method25(var0x, Shadow.class) != null && method3(var0x.name));
      var0.methods.removeIf(var0x -> FatalIchorError6.method25(var0x, Shadow.class) != null && method3(var0x.name));
   }

   private static boolean method3(String var0) {
      int var1 = var0.lastIndexOf("$v");
      return var1 != -1 && var1 < var0.length() - 2 && Character.isDigit(var0.charAt(var1 + 2));
   }

   private static void method4(ClassNode var0) {
      var0.methods.stream().filter(var0x -> var0x.name.endsWith("init>")).forEach(var1 -> {
         for (Files6_2 var4 : method5(var0, var1)) {
            AbstractInsnNode var5 = (AbstractInsnNode)var4.field1;
            AbstractInsnNode var6 = (AbstractInsnNode)var4.field2;
            LabelNode var7 = new LabelNode();
            var1.instructions.insertBefore(var5, new JumpInsnNode(167, var7));
            var1.instructions.insert(var6, var7);
         }
      });
   }

   private static List<Files6_2<AbstractInsnNode, AbstractInsnNode>> method5(ClassNode var0, MethodNode var1) {
      try {
         ArrayList var2 = new ArrayList();
         Analyzer var3 = new Analyzer(new BasicInterpreter());
         var3.analyze(var0.name, var1);
         Frame[] var4 = var3.getFrames();

         for (AbstractInsnNode var6 : var1.instructions) {
            if (var6 instanceof FieldInsnNode var7 && method6(var0, var7) && (var6.getOpcode() == 179 || var6.getOpcode() == 181)) {
               AbstractInsnNode var8 = method7(var7, var1.instructions, var4);
               if (var8 != null) {
                  var2.add(new Files6_2<>(var8, var6));
               }
            }
         }

         return var2;
      } catch (Throwable var9) {
         throw var9;
      }
   }

   private static boolean method6(ClassNode var0, FieldInsnNode var1) {
      return var1.owner.equals(var0.name) && var0.fields.stream().noneMatch(var1x -> var1x.name.equals(var1.name));
   }

   @Nullable
   private static AbstractInsnNode method7(FieldInsnNode var0, InsnList var1, Frame<BasicValue>[] var2) {
      AbstractInsnNode var3 = var0.getPrevious();

      for (int var4 = var1.indexOf(var0) - 1; var3 != null; var4--) {
         Frame var5 = var2[var4];
         if (var5 != null && var5.getStackSize() == 0) {
            AbstractInsnNode var6 = method8(var3);
            if (!method9(var6, var1) && !(var6 instanceof TableSwitchInsnNode) && !(var6 instanceof LookupSwitchInsnNode)) {
               return var3;
            }
         } else if (var5 == null) {
         }

         var3 = var3.getPrevious();
      }

      return null;
   }

   @Nullable
   private static AbstractInsnNode method8(AbstractInsnNode var0) {
      do {
         var0 = var0.getPrevious();
      } while (var0 instanceof LabelNode || var0 instanceof LineNumberNode || var0 instanceof FrameNode);

      return var0;
   }

   private static boolean method9(AbstractInsnNode var0, InsnList var1) {
      return var0 instanceof JumpInsnNode var2 && var1.indexOf(var2.label) > var1.indexOf(var2);
   }

   private static void method10(ClassNode var0) {
      try {
         HashMap var1 = new HashMap();

         for (MethodNode var3 : var0.methods) {
            if (method11(var3)) {
               var1.put(var3.name + var3.desc, new ArrayList());
            }
         }

         if (!var1.isEmpty()) {
            for (MethodNode var15 : var0.methods) {
               Frame[] var4 = null;
               int var5 = 0;

               for (AbstractInsnNode var7 : var15.instructions) {
                  if (var7 instanceof InvokeDynamicInsnNode var8) {
                     if (var4 == null) {
                        Analyzer var9 = new Analyzer(new BasicInterpreter());
                        var9.analyze(var0.name, var15);
                        var4 = var9.getFrames();
                     }

                     if (var4[var5] == null) {
                        continue;
                     }

                     String var25 = method12(var8, var0);
                     if (var25 != null) {
                        List var10 = (List)var1.get(var25);
                        if (var10 == null) {
                           ArrayList var11 = new ArrayList();
                           var11.add(var15.name + var15.desc);
                           var1.put(var25, var11);
                        } else {
                           var10.add(var15.name + var15.desc);
                        }
                     }
                  }

                  var5++;
               }
            }

            ArrayList var14 = new ArrayList();
            Iterator var16 = var0.methods.iterator();

            while (var16.hasNext()) {
               MethodNode var17 = (MethodNode)var16.next();
               String var19 = var17.name + var17.desc;
               List var21 = (List)var1.get(var19);
               if (var21 != null && var21.isEmpty()) {
                  var14.add(var19);
                  var16.remove();
                  var1.remove(var19);
               }
            }

            while (!var14.isEmpty()) {
               ArrayList var18 = new ArrayList(var14);
               var14.clear();

               for (String var22 : var18) {
                  Iterator var23 = var1.entrySet().iterator();

                  while (var23.hasNext()) {
                     Entry var24 = (Entry)var23.next();
                     if (((List)var24.getValue()).remove(var22) && ((List)var24.getValue()).isEmpty()) {
                        var23.remove();
                        String var26 = (String)var24.getKey();
                        var14.add(var26);
                        var0.methods.removeIf(var1x -> (var1x.name + var1x.desc).equals(var26));
                     }
                  }
               }
            }
         }
      } catch (Throwable var12) {
         throw var12;
      }
   }

   private static boolean method11(MethodNode var0) {
      return (var0.access & 4096) != 0 && var0.name.startsWith("lambda$");
   }

   private static String method12(InvokeDynamicInsnNode var0, ClassNode var1) {
      if (var0.getOpcode() != 186) {
         return null;
      }

      if (var0.bsm.getOwner().equals("java/lang/invoke/LambdaMetafactory") && var0.bsm.getName().equals("metafactory")) {
         for (Object var5 : var0.bsmArgs) {
            if (var5 instanceof Handle var6 && var6.getOwner().equals(var1.name)) {
               return var6.getName() + var6.getDesc();
            }
         }

         return null;
      } else {
         return null;
      }
   }
}
