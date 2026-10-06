package com.moonsworth.lunar.client.cosmetics.molang;

import com.eliotlash.molang.ast.Stmt;
import com.eliotlash.molang.ast.Expr.Block;
import com.eliotlash.molang.ast.Expr.Constant;
import com.eliotlash.molang.ast.Stmt.Expression;
import com.eliotlash.molang.ast.Stmt.If;
import com.eliotlash.molang.ast.Stmt.Loop;
import com.eliotlash.molang.ast.Stmt.Return;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.moonsworth.lunar.ichor.util.Annotation2;
import it.unimi.dsi.fastutil.Pair;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
import com.moonsworth.lunar.client.cosmetics.molang.MolangJitSignature;
import com.moonsworth.lunar.client.cosmetics.molang.MolangScope;
import com.moonsworth.lunar.client.cosmetics.molang.ConstantExpression;
import com.moonsworth.lunar.client.cosmetics.molang.VariablesMap;
import com.moonsworth.lunar.client.cosmetics.molang.MolangCompileOptions;
import com.moonsworth.lunar.client.cosmetics.molang.MolangClassBuilder;
import com.moonsworth.lunar.client.cosmetics.molang.MolangClassDefiner;

public interface MolangStmtCompiler {
   @Annotation2
   double run();

   static MolangStmtCompiler method1(List<Stmt> var0, MolangScope var1) {
      return var0.size() == 1 ? method4((Stmt)var0.get(0), var1, new MolangCompileOptions()) : method4(new Expression(new Block(var0)), var1, new MolangCompileOptions());
   }

   static MolangStmtCompiler method2(Stmt var0, MolangScope var1) {
      return method4(var0, var1, new MolangCompileOptions());
   }

   static MolangStmtCompiler method3(List<Stmt> var0, MolangScope var1, MolangCompileOptions var2) {
      return var0.size() == 1 ? method4((Stmt)var0.get(0), var1, var2) : method4(new Expression(new Block(var0)), var1, var2);
   }

   static MolangStmtCompiler method4(Stmt var0, MolangScope var1, MolangCompileOptions var2) {
      if (var0 instanceof Expression var3 && var3.expr() instanceof Constant var7) {
         return new ConstantExpression(var7.value());
      } else {
         MolangStmtCompiler.MolangStmtContext var6 = new MolangStmtCompiler.MolangStmtContext();
         var6.field2 = true;
         MolangJitSignature var4 = new MolangJitSignature();
         MolangStmtCompiler.MolangStmtEmitter var8 = method6(var0, new VariablesMap(), var1, var2, var6, var4);
         return var8.method2(var4);
      }
   }

   static void method5(List<Stmt> var0, MolangScope var1, MolangCompileOptions var2) {
      MolangStmtCompiler.MolangStmtContext var3 = new MolangStmtCompiler.MolangStmtContext();
      method6(new Expression(new Block(var0)), new VariablesMap(), var1, var2, var3, new MolangJitSignature());
   }

   static MolangStmtCompiler.MolangStmtEmitter method6(Stmt var0, VariablesMap var1, MolangScope var2, MolangCompileOptions var3, MolangStmtCompiler.MolangStmtContext var4, MolangJitSignature var5) {
      if (var0 instanceof Expression var13) {
         var5.method1(new MolangJitSignature.Data17());
         MolangExprCompiler.MolangValueEmitter var16 = MolangExprCompiler.method1(var13.expr(), var1, var2, var3, new MolangStmtCompiler.MolangStmtContext(), var5);
         return method7(var2x -> {
            if (var4.field2) {
               MolangExprCompiler.method8(var16, var2x, MolangExprCompiler.MolangValueType.DOUBLE_PRIM);
            } else {
               MolangExprCompiler.method6(var16, var2x);
            }
         });
      } else if (var0 instanceof Loop var12) {
         var5.method1(new MolangJitSignature.Data3());
         MolangExprCompiler.MolangValueEmitter var15 = MolangExprCompiler.method1(var12.count(), var1, var2, var3, new MolangStmtCompiler.MolangStmtContext(), var5);
         MolangStmtCompiler.MolangStmtContext var17 = new MolangStmtCompiler.MolangStmtContext();
         var17.field1 = false;
         MolangExprCompiler.MolangValueEmitter var18 = MolangExprCompiler.method1(var12.expr(), var1, var2, var3, var17, var5);
         return method7(
            var4x -> {
               int var5x = var1.method3();
               Label var6x = new Label();
               Label var7x = new Label();
               MolangExprCompiler.method8(var15, var4x, MolangExprCompiler.MolangValueType.DOUBLE_PRIM);
               var4x.method2(
                  var4xx -> {
                     var4xx.visitMethodInsn(
                        184, Type.getInternalName(Math.class), "ceil", Type.getMethodDescriptor(Type.DOUBLE_TYPE, new Type[]{Type.DOUBLE_TYPE}), false
                     );
                     var4xx.visitInsn(142);
                     int var5xx = var1.method3();
                     var4xx.visitVarInsn(54, var5xx);
                     MolangExprCompiler.method11(var4xx, 0);
                     var4xx.visitVarInsn(54, var5x);
                     var4xx.visitLabel(var7x);
                     var4xx.visitVarInsn(21, var5x);
                     var4xx.visitVarInsn(21, var5xx);
                     var4xx.visitJumpInsn(162, var6x);
                  }
               );
               MolangExprCompiler.method6(var18, var4x);
               var4x.method2(var3xx -> {
                  var3xx.visitIincInsn(var5x, 1);
                  var3xx.visitJumpInsn(167, var7x);
                  var3xx.visitLabel(var6x);
               });
               if (var4.field2) {
                  var4x.method2(var0xx -> MolangExprCompiler.method11(var0xx, 0.0));
               }
            }
         );
      } else if (var0 instanceof Return var11) {
         var5.method1(new MolangJitSignature.Data8());
         MolangExprCompiler.MolangValueEmitter var14 = MolangExprCompiler.method1(var11.value(), var1, var2, var3, new MolangStmtCompiler.MolangStmtContext(), var5);
         return method7(var2x -> {
            if (!var4.field2) {
               MolangExprCompiler.method6(var14, var2x);
            } else {
               MolangExprCompiler.method8(var14, var2x, MolangExprCompiler.MolangValueType.DOUBLE_PRIM);
            }
         });
      } else if (!(var0 instanceof If var6)) {
         throw new RuntimeException("Not jiting stmt: " + var0.getClass());
      } else {
         var5.method1(new MolangJitSignature.Data12());
         MolangStmtCompiler.MolangStmtContext var7 = new MolangStmtCompiler.MolangStmtContext();
         var7.field1 = false;
         ArrayList var8 = new ArrayList();
         var8.add(Pair.of(MolangExprCompiler.method1(var6.condition(), var1, var2, var3, var7, var5), MolangExprCompiler.method1(var6.body(), var1, var2, var3, var7, var5)));

         for (If var10 : var6.elifs()) {
            var5.method1(new MolangJitSignature.Data5());
            var8.add(Pair.of(MolangExprCompiler.method1(var10.condition(), var1, var2, var3, var7, var5), MolangExprCompiler.method1(var10.body(), var1, var2, var3, var7, var5)));
         }

         if (var6.elseBlock() != null) {
            var5.method1(new MolangJitSignature.Data());
            var8.add(Pair.of(null, MolangExprCompiler.method1(var6.elseBlock(), var1, var2, var3, var7, var5)));
         }

         return method7(var2x -> {
            Label var3x = new Label();

            for (Pair var5x : var8) {
               MolangExprCompiler.MolangValueEmitter var6x = (MolangExprCompiler.MolangValueEmitter)var5x.first();
               MolangExprCompiler.MolangValueEmitter var7x = (MolangExprCompiler.MolangValueEmitter)var5x.second();
               Label var8x = new Label();
               Label var9 = new Label();
               if (var6x != null) {
                  MolangExprCompiler.method8(var6x, var2x, MolangExprCompiler.MolangValueType.BOOLEAN);
                  var2x.method2(var2xx -> {
                     var2xx.visitJumpInsn(154, var9);
                     var2xx.visitJumpInsn(167, var8x);
                     var2xx.visitLabel(var9);
                  });
               }

               MolangExprCompiler.method6(var7x, var2x);
               var2x.method2(var2xx -> {
                  var2xx.visitJumpInsn(167, var3x);
                  var2xx.visitLabel(var8x);
               });
            }

            var2x.method2(var1xx -> var1xx.visitLabel(var3x));
            if (var4.field2) {
               var2x.method2(var0xx -> MolangExprCompiler.method11(var0xx, 0.0));
            }
         });
      }
   }

   static MolangStmtCompiler.MolangStmtEmitter method7(Consumer<MolangClassBuilder> var0) {
      return new MolangStmtCompiler.MolangStmtEmitter(var0);
   }

   class MolangStmtEmitter {
      private static final Cache<MolangJitSignature, Constructor<?>> field1 = CacheBuilder.newBuilder()
         .expireAfterAccess(10L, TimeUnit.MINUTES)
         .expireAfterWrite(10L, TimeUnit.MINUTES)
         .build();
      private final Consumer<MolangClassBuilder> field2;

      public MolangStmtEmitter(Consumer<MolangClassBuilder> var1) {
         this.field2 = var1;
      }

      public void method1(MolangClassBuilder var1) {
         this.field2.accept(var1);
      }

      public MolangStmtCompiler method2(MolangJitSignature var1) {
         ClassWriter var2 = new ClassWriter(3);
         var2.visit(60, 0, "MolangJit", null, "java/lang/Object", new String[]{Type.getInternalName(MolangStmtCompiler.class)});
         var2.visitSource("MolangJit", null);
         MolangClassBuilder var3 = new MolangClassBuilder();
         this.field2.accept(var3);
         int var4 = 0;

         for (Type var6 : var3.method4()) {
            String var7 = "_" + var4;
            var2.visitField(18, var7, var6.getDescriptor(), null, null).visitEnd();
            var4++;
         }

         MethodVisitor var12 = var2.visitMethod(1, "<init>", Type.getMethodDescriptor(Type.VOID_TYPE, var3.method4().toArray(new Type[0])), null, null);
         var12.visitVarInsn(25, 0);
         var12.visitMethodInsn(183, "java/lang/Object", "<init>", "()V", false);
         int var15 = 0;

         for (Type var8 : var3.method4()) {
            var12.visitVarInsn(25, 0);
            var12.visitVarInsn(25, var15 + 1);
            var12.visitFieldInsn(181, "MolangJit", "_" + var15, var8.getDescriptor());
            var15++;
         }

         var12.visitInsn(177);
         var12.visitMaxs(0, 0);
         var12.visitEnd();
         var12 = var2.visitMethod(1, "run", Type.getMethodDescriptor(Type.DOUBLE_TYPE, new Type[0]), null, null);

         for (Consumer var19 : var3.method3()) {
            var19.accept(var12);
         }

         var12.visitInsn(175);
         var12.visitMaxs(0, 0);
         var12.visitEnd();
         var2.visitEnd();
         byte[] var14 = var2.toByteArray();
         ArrayList var17 = new ArrayList();
         ArrayList var20 = new ArrayList();

         for (Object var9 : var3.method5()) {
            var17.add(var9.getClass());
            var20.add(var9);
         }

         Constructor var22;
         try {
            var22 = (Constructor)field1.get(var1, () -> MolangClassDefiner.method1("MolangJit", var14, var17.toArray(new Class[0])));
         } catch (ExecutionException var11) {
            var22 = MolangClassDefiner.method1("MolangJit", var14, var17.toArray(new Class[0]));
         }

         try {
            return (MolangStmtCompiler)var22.newInstance(var20.toArray());
         } catch (InstantiationException | IllegalAccessException | InvocationTargetException var10) {
            throw new RuntimeException(var10);
         }
      }
   }

   class MolangStmtContext {
      public boolean field1 = true;
      public boolean field2 = false;
   }
}
