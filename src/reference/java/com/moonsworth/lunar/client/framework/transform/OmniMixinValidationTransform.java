package com.moonsworth.lunar.client.framework.transform;

import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.ichor.Annotation2;
import com.moonsworth.lunar.ichor.Annotation7;
import com.moonsworth.lunar.ichor.IchorTransformer;
import com.moonsworth.lunar.ichor.IchorInjection;
import com.moonsworth.lunar.ichor.Ichor4;
import com.moonsworth.lunar.ichor.IchorPipeline;
import com.moonsworth.lunar.ichor.URLClassLoader;
import com.moonsworth.lunar.ichor.util.FatalIchorError6;
import com.moonsworth.lunar.loader.Ichor4Type;
import com.moonsworth.lunar.loader.mixin.MixinHelper;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import javax.annotation.Nullable;
import org.objectweb.asm.Handle;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InvokeDynamicInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class OmniMixinValidationTransform implements IchorInjection {
   private static final Set<String> field1 = new LinkedHashSet<>();
   private final Config field2;
   private final String field3;
   private final List<String> field4;

   public OmniMixinValidationTransform(Config var1, @Nullable String var2, List<String> var3) {
      this.field2 = var1;
      this.field3 = Objects.requireNonNullElse(var2, "com/moonsworth/");
      this.field4 = var3;
      com.moonsworth.lunar.ichor.MixinInternalTask.method28(OmniMixinValidationTransform::method9);
      IchorPipeline.field3.info("[OmniMixin] Setting up OmniMixin checker");
   }

   @Override
   public boolean method1(IchorTransformer.Data3 var1) {
      String var2 = var1.className();
      if (var2.startsWith("com/moonsworth/lunar/client")) {
         return false;
      }

      for (String var4 : this.field4) {
         if (var2.startsWith(var4)) {
            return false;
         }
      }

      return this.field3 == null || var2.startsWith(this.field3);
   }

   @Override
   public Ichor4[] method2() {
      return new Ichor4[]{Ichor4Type.PRE_META_MIXIN};
   }

   @Annotation7
   public void method3(ClassNode var1, URLClassLoader var2) {
      AnnotationNode var3 = FatalIchorError6.method25(var1, Annotation2.class);
      if (var3 == null || MixinHelper.method1(var3, this.field2)) {
         boolean var4 = FatalIchorError6.method17(var1);
         LinkedList var5 = new LinkedList();
         Set var6 = Collections.newSetFromMap(new IdentityHashMap());

         for (MethodNode var8 : var1.methods) {
            if (!var8.name.startsWith("lambda$")
               && (var8.access & 4096) == 0
               && !var8.name.equals("<clinit>")
               && !FatalIchorError6.method23(var8, "Lorg/spongepowered/asm/mixin/Shadow;")
               && !FatalIchorError6.method24(var8, "Llombok/Generated;")) {
               this.method4(var1, var8, var4, var5, var6);
            }
         }

         while (!var5.isEmpty()) {
            MethodNode var9 = (MethodNode)var5.poll();
            this.method4(var1, var9, var4, var5, var6);
         }
      }
   }

   private void method4(ClassNode var1, MethodNode var2, boolean var3, Queue<MethodNode> var4, Set<MethodNode> var5) {
      for (AbstractInsnNode var7 : var2.instructions) {
         if (var7 instanceof MethodInsnNode var8 && this.method6(var8.name)) {
            boolean var21 = this.method5(var8.owner);
            if (!var21 && var3 && var8.owner.equals(var1.name)) {
               for (MethodNode var25 : var1.methods) {
                  if (var25.name.equals(var8.name) && var25.desc.equals(var8.desc) && FatalIchorError6.method23(var25, "Lorg/spongepowered/asm/mixin/Shadow;")) {
                     var21 = true;
                     break;
                  }
               }
            }

            if (var21) {
               this.method8(var1, "Method", var2.name, var2.desc, var8.owner + "." + var8.name + var8.desc);
            }
         } else if (var7 instanceof FieldInsnNode var9 && this.method6(var9.name)) {
            boolean var20 = this.method5(var9.owner);
            if (!var20 && var3 && var9.owner.equals(var1.name)) {
               for (FieldNode var24 : var1.fields) {
                  if (var24.name.equals(var9.name) && var24.desc.equals(var9.desc) && FatalIchorError6.method21(var24, "Lorg/spongepowered/asm/mixin/Shadow;")) {
                     var20 = true;
                     break;
                  }
               }
            }

            if (var20) {
               this.method8(var1, "Field", var2.name, var2.desc, var9.owner + "." + var9.name + var9.desc);
            }
         } else if (var7 instanceof InvokeDynamicInsnNode var10) {
            for (Object var14 : var10.bsmArgs) {
               if (var14 instanceof Handle var15) {
                  if (var15.getTag() >= 1 && var15.getTag() <= 4 && this.method6(var15.getName())) {
                     boolean var26 = this.method5(var15.getOwner());
                     if (!var26 && var3 && var15.getOwner().equals(var1.name)) {
                        for (FieldNode var28 : var1.fields) {
                           if (var28.name.equals(var15.getName())
                              && var28.desc.equals(var15.getDesc())
                              && FatalIchorError6.method21(var28, "Lorg/spongepowered/asm/mixin/Shadow;")) {
                              var26 = true;
                              break;
                           }
                        }
                     }

                     if (var26) {
                        this.method7(var1, "FieldRef", var2.name, var2.desc, var15);
                     }
                  } else if (var15.getTag() >= 5 && var15.getTag() <= 9) {
                     boolean var16 = this.method6(var15.getName());
                     boolean var17 = var16 && this.method5(var15.getOwner());
                     if (!var17 && var15.getOwner().equals(var1.name)) {
                        for (MethodNode var19 : var1.methods) {
                           if (var19.name.equals(var15.getName()) && var19.desc.equals(var15.getDesc())) {
                              if (var3 && var16 && FatalIchorError6.method23(var19, "Lorg/spongepowered/asm/mixin/Shadow;")) {
                                 var17 = true;
                                 break;
                              }

                              if (var19.name.startsWith("lambda$")) {
                                 if (var5.add(var19)) {
                                    var4.add(var19);
                                 }
                                 break;
                              }
                           }
                        }
                     }

                     if (var17) {
                        this.method7(var1, "MethodRef", var2.name, var2.desc, var15);
                     }
                  }
               }
            }
         }
      }
   }

   private boolean method5(String var1) {
      return !var1.contains("/") || var1.startsWith("net/minecraft/") || var1.startsWith("com/mojang/");
   }

   private boolean method6(String var1) {
      int var2 = var1.lastIndexOf("$v");
      return var2 != -1 && var2 != var1.length() - 1
         ? Character.isDigit(var1.charAt(var2 + 2)) && !var1.startsWith("bridge$") && !var1.startsWith("lunar$") && !var1.startsWith("ichor$")
         : false;
   }

   private void method7(ClassNode var1, String var2, String var3, String var4, Handle var5) {
      this.method8(var1, var2, var3, var4, var5.getOwner() + "." + var5.getName() + var5.getDesc());
   }

   private void method8(ClassNode var1, String var2, String var3, String var4, String var5) {
      String var6 = " - " + var2 + ": " + var1.name + "." + var3 + var4 + " -> " + var5;
      field1.add(var6);
   }

   public static void method9() {
      if (field1.isEmpty()) {
         IchorPipeline.field3.info("[OmniMixin] All OmniMixins are ok!");
      } else {
         StringBuilder var0 = new StringBuilder();
         field1.forEach(var1 -> var0.append(var1).append("\n"));
         IchorPipeline.field3.fatal("[OmniMixin] %d broken OmniMixins were detected: \n%s", field1.size(), var0.toString());
         System.exit(1);
      }
   }
}
