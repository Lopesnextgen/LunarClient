package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.ichor.util.FatalIchorError6;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Nullable;
import lombok.Generated;
import org.cadixdev.bombe.type.ObjectType;
import org.cadixdev.lorenz.MappingSet;
import org.cadixdev.lorenz.io.srg.xsrg.XSrgWriter;
import org.cadixdev.lorenz.merge.MappingSetMerger;
import org.cadixdev.lorenz.merge.MappingSetMergerHandler;
import org.cadixdev.lorenz.merge.MergeConfig;
import org.cadixdev.lorenz.merge.MergeContext;
import org.cadixdev.lorenz.merge.MergeResult;
import org.cadixdev.lorenz.model.ClassMapping;
import org.cadixdev.lorenz.model.FieldMapping;
import org.cadixdev.lorenz.model.Mapping;
import org.cadixdev.lorenz.model.MethodMapping;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.spongepowered.asm.util.Annotations;

@Annotation3
public abstract class MixinInternalTask implements MixinInternal {
   private static final Map<String, Boolean> field1 = new ConcurrentHashMap<>();
   private static Runnable field2;
   @Nullable
   private final MappingSet field3;
   @Nullable
   private final MappingSet field4;
   private final int field5;

   public MixinInternalTask(@Nullable MappingSet var1, @Nullable MappingSet var2, int var3) {
      this.field3 = var1;
      this.field4 = var2;
      this.field5 = var3;
   }

   public boolean method1(String var1) {
      return true;
   }

   public abstract boolean method2(AnnotationNode var1);

   protected abstract boolean method3(FieldInsnNode var1);

   protected abstract boolean method4(MethodInsnNode var1);

   @Override
   public void method2(ClassNode var1, URLClassLoader var2) {
      if (this.method1(var1.name)) {
         IchorPipeline var3 = var2.method1();
         this.method7(var1.methods, var3);
         this.method8(var1.fields, var3);
         MixinOther.method1(var1, var2);
      }
   }

   @Override
   public boolean method1(ClassNode var1, IchorPipeline var2) {
      AnnotationNode var3 = Annotations.getVisible(var1, Annotation2.class);
      if (var3 != null) {
         var1.visibleAnnotations.remove(var3);
         boolean var4 = this.method2(var3);
         if (!var4) {
            return false;
         }
      }

      AnnotationNode var8 = Annotations.getInvisible(var1, Annotation_2.class);
      if (var8 != null) {
         var1.invisibleAnnotations.remove(var8);
         AnnotationNode var5 = (AnnotationNode)Annotations.getValue(var8, "available");
         if (var5 != null && !this.method2(var5)) {
            return true;
         }

         List var6 = (List)Annotations.getValue(var8, "present", Collections.emptyList());
         List var7 = (List)Annotations.getValue(var8, "absent", Collections.emptyList());
         return var6.stream().allMatch(var2::hasModule) && var7.stream().noneMatch(var2::hasModule);
      } else {
         return true;
      }
   }

   private void method7(List<MethodNode> var1, IchorPipeline var2) {
      var1.removeIf(var2x -> {
         AnnotationNode var3 = Annotations.getInvisible(var2x, Annotation_2.class);
         if (var3 != null) {
            var2x.invisibleAnnotations.remove(var3);
            return this.method9(var3, var2);
         } else {
            return false;
         }
      });
   }

   private void method8(List<FieldNode> var1, IchorPipeline var2) {
      var1.removeIf(var2x -> {
         AnnotationNode var3 = Annotations.getInvisible(var2x, Annotation_2.class);
         if (var3 != null) {
            var2x.invisibleAnnotations.remove(var3);
            return this.method9(var3, var2);
         } else {
            return false;
         }
      });
   }

   private boolean method9(AnnotationNode var1, IchorPipeline var2) {
      AnnotationNode var3 = (AnnotationNode)Annotations.getValue(var1, "available");
      if (var3 != null && !this.method2(var3)) {
         return false;
      }

      List var4 = (List)Annotations.getValue(var1, "present", Collections.emptyList());
      List var5 = (List)Annotations.getValue(var1, "absent", Collections.emptyList());
      return !var4.stream().allMatch(var2::hasModule) || !var5.stream().noneMatch(var2::hasModule);
   }

   @Annotation7
   public void method10(ClassNode var1, URLClassLoader var2) {
      var1.fields.removeIf(this::method15);
      var1.methods.removeIf(this::method15);
      var1.innerClasses.removeIf(var1x -> !var2.hasClass(var1x.name));
      var1.methods.forEach(var2x -> this.method17(var2x, var2));
      var1.methods.forEach(var2x -> this.method25(var2x, var2));
      this.method16(var1);
   }

   @Annotation8(
      OORRORHHCIICCICOOOIOOHRRHCOIHI = true,
      RHRRICIHIHHROIRCHHROOOHHOCHORH = true,
      COCICIRCIOIRRHRHHRHOCOHRHOHCIO = true,
      HIHCIHIOOCIORRCIOROCCCRCOOICCR = true,
      HCCCHRRORIIOCCRHHROOOHORCHOICC = false
   )
   public RemapTransformInvoker.RemapTransformResult method11(URLClassLoader var1) {
      if (this.field3 == null) {
         return null;
      }

      MappingSet var2 = this.field3;
      if (this.field4 != null) {
         var2 = MappingSetMerger.create(
               this.field3,
               this.field4,
               MergeConfig.builder()
                  .withMergeHandler(
                     new MappingSetMergerHandler() {
                        private boolean method1(String var1) {
                           return var1.startsWith("com/mojang/blaze3d/")
                              || var1.equals("net/minecraft/client/gui/LayeredDraw$Layer")
                              || var1.equals("net/minecraft/client/gui/screens/packs/PackSelectionModel$Entry");
                        }

                        public MergeResult<MethodMapping> addLeftMethodMapping(MethodMapping var1, ClassMapping<?, ?> var2x, MergeContext var3) {
                           return !this.method1(var2x.getFullObfuscatedName())
                                 && !var1.getObfuscatedName().startsWith("newInstance")
                                 && !var1.getObfuscatedName().startsWith("close")
                                 && !var1.getObfuscatedName().startsWith("open")
                                 && !var1.getObfuscatedName().startsWith("mode")
                                 && (MixinInternalTask.this.field5 > 32 || !var1.getObfuscatedName().equals("getBuffer$v1_16_1"))
                              ? new MergeResult(null)
                              : super.addLeftMethodMapping(var1, var2x, var3);
                        }

                        public FieldMapping addLeftFieldMapping(FieldMapping var1, ClassMapping<?, ?> var2x, MergeContext var3) {
                           return this.method1(var2x.getFullObfuscatedName()) ? super.addLeftFieldMapping(var1, var2x, var3) : null;
                        }
                     }
                  )
                  .build()
            )
            .merge();
         if (RemapperIterator2.DEBUG) {
            try {
               XSrgWriter var3 = new XSrgWriter(new FileWriter(".ichor/inflight_merged.xsrg"));

               try {
                  var3.write(var2);
               } catch (Throwable var7) {
                  try {
                     var3.close();
                  } catch (Throwable var6) {
                     var7.addSuppressed(var6);
                  }

                  throw var7;
               }

               var3.close();
            } catch (IOException var8) {
               throw new RuntimeException(var8);
            }
         }
      }

      return new RemapTransformInvoker.RemapTransformResult(var2, var0 -> !var0.startsWith("com/moonsworth/lunar/bridge/"));
   }

   @Annotation7
   public void method12(ClassNode var1, URLClassLoader var2) {
      this.method13(var2, var1);
      var1.methods.forEach(var2x -> this.method18(var2x, var2));
      byte[] var3 = var2.method16().method9(var2, var1, true);
      ClassNode var4 = FatalIchorError6.method16(var3, 0);
      FatalIchorError6.method2(var1, var4);
   }

   private void method13(URLClassLoader var1, ClassNode var2) {
      if (this.field3 != null) {
         boolean var4 = FatalIchorError6.method34(var2);
         String var3;
         if (var2.superName.startsWith("java/")) {
            var3 = var2.superName;
         } else {
            var3 = this.remapClassName(var2.superName);
            ClassNode var5 = var1.getAsNode(var3);
            if (var5 == null) {
               var2.superName = "java/lang/Object";
               return;
            }

            boolean var6 = FatalIchorError6.method34(var5);
            if (!var4 && var6) {
               var2.superName = "java/lang/Object";
               var2.interfaces.add(var5.name);
            }
         }

         HashSet var7 = new HashSet();
         var2.interfaces.removeIf(var3x -> {
            String var4x = this.remapClassName(var3x);
            ClassNode var5x = var1.getAsNode(var4x);
            if (var5x == null) {
               return true;
            } else if (!FatalIchorError6.method34(var5x)) {
               var7.add(var4x);
               return true;
            } else {
               return false;
            }
         });
         switch (var7.size()) {
            case 1:
               String var8 = (String)var7.iterator().next();
               if (var4) {
                  throw new LinkageError("Class %s is an interface but needs to extend %s, which is a class!".formatted(var2.name, var8));
               } else if (!"java/lang/Object".equals(var3)) {
                  throw new LinkageError("Class %s needs to extend %s, but already extends %s!".formatted(var2.name, var8, var3));
               } else {
                  var2.superName = var8;
               }
            case 0:
               this.method14(var2, var3);
               return;
            default:
               throw new LinkageError("Class %s needs to extend all of %s. It currently extends %s.".formatted(var2.name, var7, var3));
         }
      }
   }

   private void method14(ClassNode var1, String var2) {
      if (FatalIchorError6.method17(var1)) {
         for (MethodNode var4 : var1.methods) {
            if (var4.name.equals("<init>")) {
               for (AbstractInsnNode var6 : var4.instructions) {
                  if (var6 instanceof MethodInsnNode var7 && var7.owner.equals(var2) && var7.name.equals("<init>")) {
                     var7.owner = var1.superName;
                  }
               }
            }
         }
      }
   }

   private boolean method15(Object var1) {
      AnnotationNode var2 = FatalIchorError6.method25(var1, Annotation2.class);
      if (var2 != null && !this.method2(var2)) {
         return true;
      }

      AnnotationNode var3 = FatalIchorError6.method25(var1, Annotation13.class);
      return var3 != null;
   }

   private void method16(ClassNode var1) {
      AnnotationNode var2 = FatalIchorError6.method25(var1, Annotation9.class);
      if (var2 != null) {
         List var3 = var2.values;
         List var4 = null;

         for (byte var5 = 0; var5 < var3.size(); var5 += 2) {
            String var6 = var3.get(var5).toString();
            Object var7 = var3.get(var5 + 1);
            if ("value".equals(var6) && var7 instanceof List) {
               var4 = (List)var7;
            }
         }

         if (var4 == null) {
            throw new RuntimeException(
               "Invalid @DynamicImplements annotation attached to "
                  + var1.name
                  + ": Missing \"value\", make sure your declaration is of the form @DynamicImplements({@Interface(...), ...})"
            );
         }

         for (AnnotationNode var14 : var4) {
            boolean var15 = false;
            boolean var8 = false;
            String var9 = null;

            for (byte var10 = 0; var10 < var14.values.size(); var10 += 2) {
               String var11 = var14.values.get(var10).toString();
               Object var12 = var14.values.get(var10 + 1);
               if ("value".equals(var11)) {
                  var9 = ((Type)var12).getClassName();
               } else if ("available".equals(var11)) {
                  var15 = true;
                  var8 = this.method2((AnnotationNode)var12);
               }
            }

            if (var9 == null || !var15) {
               String var16 = "";
               if (var9 == null) {
                  var16 = "@Interface.value";
               }

               if (!var15) {
                  if (var16.isEmpty()) {
                     var16 = "@Interface.available";
                  } else {
                     var16 = var16 + "and @Interface.available";
                  }
               }

               throw new RuntimeException(
                  "Invalid @DynamicImplements annotation attached to "
                     + var1.name
                     + ": Missing "
                     + var16
                     + ", make sure your declaration is of the form: @DynamicImplements({@Interface(value = Itf.class, available = @Available(...)), ...})"
               );
            }

            if (var8) {
               var1.interfaces.add(var9);
            }
         }
      }
   }

   private void method17(MethodNode var1, URLClassLoader var2) {
      IchorPipeline var3 = var2.method1();
      ListIterator var4 = var1.instructions.iterator();

      while (var4.hasNext()) {
         AbstractInsnNode var5 = (AbstractInsnNode)var4.next();
         this.method19(var1, var4, var5);
         this.method20(var1, var4, var5, var3);
      }
   }

   private void method18(MethodNode var1, URLClassLoader var2) {
      for (AbstractInsnNode var4 : var1.instructions) {
         this.method23(var4, var2);
      }
   }

   private void method19(MethodNode var1, ListIterator<AbstractInsnNode> var2, AbstractInsnNode var3) {
      if (var3 instanceof FieldInsnNode var4 && this.method3(var4)) {
         AbstractInsnNode var5 = var4.getNext();
         this.method21(var1, var2, var5, this.field5);
      }
   }

   private void method20(MethodNode var1, ListIterator<AbstractInsnNode> var2, AbstractInsnNode var3, IchorPipeline var4) {
      if (var3 instanceof MethodInsnNode var5 && this.method4(var5)) {
         AbstractInsnNode var6 = var5.getPrevious();
         String var7 = FatalIchorError6.method39(var6);
         if (var7 != null) {
            if (var5.getNext() instanceof JumpInsnNode var9) {
               if (this.method21(var1, var2, var9, var4.hasModule(var7) ? 1 : 0)) {
                  var2.previous();
                  var2.remove();
               } else {
                  var2.previous();
               }
            } else {
               var2.remove();
               var2.previous();
               var2.remove();
               var2.add(new InsnNode(var4.hasModule(var7) ? 4 : 3));
            }
         }
      }
   }

   private boolean method21(MethodNode var1, ListIterator<AbstractInsnNode> var2, AbstractInsnNode var3, int var4) {
      Integer var5 = FatalIchorError6.method35(var3);
      JumpInsnNode var6 = null;
      if (var5 == null && var3 instanceof JumpInsnNode var7) {
         var6 = var7;
         var5 = 0;
      } else if (var5 != null && var3.getNext() instanceof JumpInsnNode var8) {
         var6 = var8;
      }

      if (var6 != null) {
         int var10 = var6.getOpcode();
         if (var5 == 0 ? var10 >= 153 && var10 <= 158 : var10 >= 159 && var10 <= 164) {
            boolean var11 = this.method22(var4, var5, var10);
            if (var11) {
               var6.setOpcode(167);
            } else {
               var1.instructions.remove(var6);
            }

            var2.remove();
            if (var5 != 0) {
               var2.next();
               var2.remove();
            } else {
               var2.previous();
            }

            return true;
         }
      }

      return false;
   }

   private boolean method22(int var1, int var2, int var3) {
      return switch (var3) {
         case 153, 159 -> var1 == var2;
         case 154, 160 -> var1 != var2;
         case 155, 161 -> var1 < var2;
         case 156, 162 -> var1 >= var2;
         case 157, 163 -> var1 > var2;
         case 158, 164 -> var1 <= var2;
         default -> throw new UnsupportedOperationException("Cannot evaluate comparison using opcode " + var3);
      };
   }

   private void method23(AbstractInsnNode var1, URLClassLoader var2) {
      if (this.field3 != null) {
         if (var1 instanceof MethodInsnNode var3) {
            int var4 = var1.getOpcode();
            if (var3.owner.startsWith("java/")) {
               return;
            }

            if (this.field3.deobfuscate(org.cadixdev.bombe.type.Type.of("L" + var3.owner + ";")) instanceof ObjectType var6) {
               String var7 = var6.getClassName();
               boolean var8 = this.method24(var7, var2);
               if (var4 == 182 && var8) {
                  var3.setOpcode(185);
               } else if (var4 == 185 && !var8) {
                  var3.setOpcode(182);
               }

               var3.itf = var8;
            }
         }
      }
   }

   private boolean method24(String var1, URLClassLoader var2) {
      return field1.computeIfAbsent(var1, var2x -> {
         ClassNode var3 = var2.getAsNode(this.remapClassName(var2x), 1);
         return var3 != null && FatalIchorError6.method34(var3);
      });
   }

   private void method25(MethodNode var1, URLClassLoader var2) {
      if (var1.localVariables != null) {
         var1.localVariables.removeIf(var2x -> {
            Type var3 = this.method26(Type.getType(var2x.desc));
            return var3 != null && !var2.hasClass(var3.getInternalName());
         });
      }
   }

   private Type method26(Type var1) {
      return switch (var1.getSort()) {
         case 9 -> this.method26(var1.getElementType());
         case 10 -> var1;
         default -> null;
      };
   }

   protected String remapClassName(String var1) {
      String var2 = this.field3 == null ? var1 : this.field3.getClassMapping(var1).<String>map(Mapping::getFullDeobfuscatedName).orElse(var1);
      return this.field4 == null ? var2 : this.field4.getClassMapping(var2).<String>map(Mapping::getFullDeobfuscatedName).orElse(var2);
   }

   public static void method27() {
      if (field2 != null) {
         field2.run();
      }
   }

   @Generated
   public static void method28(Runnable var0) {
      field2 = var0;
   }
}
