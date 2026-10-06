package com.moonsworth.lunar.client.cosmetics.molang;

import com.eliotlash.molang.ast.Expr;
import com.eliotlash.molang.ast.Stmt;
import com.eliotlash.molang.ast.Expr.Access;
import com.eliotlash.molang.ast.Expr.Assignment;
import com.eliotlash.molang.ast.Expr.BinOp;
import com.eliotlash.molang.ast.Expr.Block;
import com.eliotlash.molang.ast.Expr.Call;
import com.eliotlash.molang.ast.Expr.Coalesce;
import com.eliotlash.molang.ast.Expr.Conditional;
import com.eliotlash.molang.ast.Expr.Constant;
import com.eliotlash.molang.ast.Expr.Group;
import com.eliotlash.molang.ast.Expr.Negate;
import com.eliotlash.molang.ast.Expr.Not;
import com.eliotlash.molang.ast.Expr.Str;
import com.eliotlash.molang.ast.Expr.Struct;
import com.eliotlash.molang.ast.Expr.Ternary;
import com.eliotlash.molang.ast.Expr.Variable;
import com.eliotlash.molang.ast.Stmt.Expression;
import com.eliotlash.molang.ast.Stmt.Return;
import com.moonsworth.lunar.files.Files6_2;
import java.util.ArrayList;
import java.util.function.Consumer;
import lombok.Generated;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import com.moonsworth.lunar.client.cosmetics.molang.MolangBuiltin;
import com.moonsworth.lunar.client.cosmetics.molang.MolangJitSignature;
import com.moonsworth.lunar.client.cosmetics.molang.MolangScope;
import com.moonsworth.lunar.client.cosmetics.molang.MolangVariablePath;
import com.moonsworth.lunar.client.cosmetics.molang.MolangVariable;
import com.moonsworth.lunar.client.cosmetics.molang.VariablesMap;
import com.moonsworth.lunar.client.cosmetics.molang.MolangCompileOptions;
import com.moonsworth.lunar.client.cosmetics.molang.MolangClassBuilder;

public interface MolangExprCompiler {
   static MolangExprCompiler.MolangValueEmitter method1(Expr var0, VariablesMap var1, MolangScope var2, MolangCompileOptions var3, MolangStmtCompiler.MolangStmtContext var4, MolangJitSignature var5) {
      if (var0 instanceof Constant var25) {
         var5.method1(new MolangJitSignature.Data14(var25.value()));
         return method12(MolangExprCompiler.MolangValueType.DOUBLE_PRIM, var1x -> var1x.method2(var1xx -> method11(var1xx, var25.value())));
      } else if (var0 instanceof BinOp var24) {
         var5.method1(new MolangJitSignature.Data6(var24.operator()));
         MolangExprCompiler.MolangValueEmitter var34 = method1(var24.left(), var1, var2, var3, new MolangStmtCompiler.MolangStmtContext(), var5);
         MolangExprCompiler.MolangValueEmitter var42 = method1(var24.right(), var1, var2, var3, new MolangStmtCompiler.MolangStmtContext(), var5);
         if (var24.left() instanceof Str var49 && var24.right() instanceof Str var54) {
            return method1(new Constant(var24.operator().applyString(var49.val(), var54.val())), var1, var2, var3, new MolangStmtCompiler.MolangStmtContext(), var5);
         } else {
            return switch (var24.operator()) {
               case ADD -> method12(MolangExprCompiler.MolangValueType.DOUBLE_PRIM, var2x -> {
                  method8(var34, var2x, MolangExprCompiler.MolangValueType.DOUBLE_PRIM);
                  method8(var42, var2x, MolangExprCompiler.MolangValueType.DOUBLE_PRIM);
                  var2x.method2(var0xx -> var0xx.visitInsn(99));
               });
               case SUB -> method12(MolangExprCompiler.MolangValueType.DOUBLE_PRIM, var2x -> {
                  method8(var34, var2x, MolangExprCompiler.MolangValueType.DOUBLE_PRIM);
                  method8(var42, var2x, MolangExprCompiler.MolangValueType.DOUBLE_PRIM);
                  var2x.method2(var0xx -> var0xx.visitInsn(103));
               });
               case MUL -> method12(MolangExprCompiler.MolangValueType.DOUBLE_PRIM, var2x -> {
                  method8(var34, var2x, MolangExprCompiler.MolangValueType.DOUBLE_PRIM);
                  method8(var42, var2x, MolangExprCompiler.MolangValueType.DOUBLE_PRIM);
                  var2x.method2(var0xx -> var0xx.visitInsn(107));
               });
               case DIV -> method12(MolangExprCompiler.MolangValueType.DOUBLE_PRIM, var2x -> {
                  method8(var34, var2x, MolangExprCompiler.MolangValueType.DOUBLE_PRIM);
                  method8(var42, var2x, MolangExprCompiler.MolangValueType.DOUBLE_PRIM);
                  var2x.method2(var0xx -> var0xx.visitInsn(111));
               });
               case MOD -> method12(MolangExprCompiler.MolangValueType.DOUBLE_PRIM, var2x -> {
                  method8(var34, var2x, MolangExprCompiler.MolangValueType.DOUBLE_PRIM);
                  method8(var42, var2x, MolangExprCompiler.MolangValueType.DOUBLE_PRIM);
                  var2x.method2(var0xx -> var0xx.visitInsn(115));
               });
               case POW -> method12(
                  MolangExprCompiler.MolangValueType.DOUBLE_PRIM,
                  var2x -> {
                     method8(var34, var2x, MolangExprCompiler.MolangValueType.DOUBLE_PRIM);
                     method8(var42, var2x, MolangExprCompiler.MolangValueType.DOUBLE_PRIM);
                     var2x.method2(
                        var0xx -> var0xx.visitMethodInsn(
                           184,
                           org.objectweb.asm.Type.getInternalName(Math.class),
                           "pow",
                           org.objectweb.asm.Type.getMethodDescriptor(
                              org.objectweb.asm.Type.DOUBLE_TYPE,
                              new org.objectweb.asm.Type[]{org.objectweb.asm.Type.DOUBLE_TYPE, org.objectweb.asm.Type.DOUBLE_TYPE}
                           ),
                           false
                        )
                     );
                  }
               );
               case LT -> method10(var34, var42, 155, false);
               case LEQ -> method10(var34, var42, 158, false);
               case GEQ -> method10(var34, var42, 156, true);
               case GT -> method10(var34, var42, 157, true);
               case EQ -> method12(
                  MolangExprCompiler.MolangValueType.BOOLEAN,
                  var2x -> {
                     if (var34.method2() == MolangExprCompiler.MolangValueType.BOOLEAN && var42.method2() == MolangExprCompiler.MolangValueType.BOOLEAN) {
                        method8(var34, var2x, MolangExprCompiler.MolangValueType.BOOLEAN);
                        method8(var42, var2x, MolangExprCompiler.MolangValueType.BOOLEAN);
                        var2x.method2(var0xx -> {
                           Label var1xx = new Label();
                           Label var2xx = new Label();
                           var0xx.visitJumpInsn(160, var2xx);
                           method11(var0xx, 1);
                           var0xx.visitJumpInsn(167, var1xx);
                           var0xx.visitLabel(var2xx);
                           method11(var0xx, 0);
                           var0xx.visitLabel(var1xx);
                        });
                     } else {
                        method8(var34, var2x, MolangExprCompiler.MolangValueType.DOUBLE_PRIM);
                        method8(var42, var2x, MolangExprCompiler.MolangValueType.DOUBLE_PRIM);
                        var2x.method2(
                           var0xx -> var0xx.visitMethodInsn(
                              184,
                              org.objectweb.asm.Type.getInternalName(MolangVariablePath.class),
                              "epsilonEquals",
                              org.objectweb.asm.Type.getMethodDescriptor(
                                 org.objectweb.asm.Type.BOOLEAN_TYPE,
                                 new org.objectweb.asm.Type[]{org.objectweb.asm.Type.DOUBLE_TYPE, org.objectweb.asm.Type.DOUBLE_TYPE}
                              ),
                              false
                           )
                        );
                     }
                  }
               );
               case NEQ -> method12(
                  MolangExprCompiler.MolangValueType.BOOLEAN,
                  var2x -> {
                     if (var34.method2() == MolangExprCompiler.MolangValueType.BOOLEAN && var42.method2() == MolangExprCompiler.MolangValueType.BOOLEAN) {
                        method8(var34, var2x, MolangExprCompiler.MolangValueType.BOOLEAN);
                        method8(var42, var2x, MolangExprCompiler.MolangValueType.BOOLEAN);
                        var2x.method2(var0xx -> {
                           Label var1xx = new Label();
                           Label var2xx = new Label();
                           var0xx.visitJumpInsn(159, var2xx);
                           method11(var0xx, 1);
                           var0xx.visitJumpInsn(167, var1xx);
                           var0xx.visitLabel(var2xx);
                           method11(var0xx, 0);
                           var0xx.visitLabel(var1xx);
                        });
                     } else {
                        method8(var34, var2x, MolangExprCompiler.MolangValueType.DOUBLE_PRIM);
                        method8(var42, var2x, MolangExprCompiler.MolangValueType.DOUBLE_PRIM);
                        var2x.method2(
                           var0xx -> {
                              var0xx.visitMethodInsn(
                                 184,
                                 org.objectweb.asm.Type.getInternalName(MolangVariablePath.class),
                                 "epsilonEquals",
                                 org.objectweb.asm.Type.getMethodDescriptor(
                                    org.objectweb.asm.Type.BOOLEAN_TYPE,
                                    new org.objectweb.asm.Type[]{org.objectweb.asm.Type.DOUBLE_TYPE, org.objectweb.asm.Type.DOUBLE_TYPE}
                                 ),
                                 false
                              );
                              Label var1xx = new Label();
                              Label var2xx = new Label();
                              var0xx.visitJumpInsn(153, var1xx);
                              method11(var0xx, 0);
                              var0xx.visitJumpInsn(167, var2xx);
                              var0xx.visitLabel(var1xx);
                              method11(var0xx, 1);
                              var0xx.visitLabel(var2xx);
                           }
                        );
                     }
                  }
               );
               case AND -> method12(MolangExprCompiler.MolangValueType.BOOLEAN, var2x -> {
                  method8(var34, var2x, MolangExprCompiler.MolangValueType.BOOLEAN);
                  Label var3x = new Label();
                  var2x.method2(var1xx -> var1xx.visitJumpInsn(153, var3x));
                  method8(var42, var2x, MolangExprCompiler.MolangValueType.BOOLEAN);
                  var2x.method2(var1xx -> var1xx.visitJumpInsn(153, var3x));
                  var2x.method2(var1xx -> {
                     Label var2xx = new Label();
                     method11(var1xx, 1);
                     var1xx.visitJumpInsn(167, var2xx);
                     var1xx.visitLabel(var3x);
                     method11(var1xx, 0);
                     var1xx.visitLabel(var2xx);
                  });
               });
               case OR -> method12(MolangExprCompiler.MolangValueType.BOOLEAN, var2x -> {
                  method8(var34, var2x, MolangExprCompiler.MolangValueType.BOOLEAN);
                  Label var3x = new Label();
                  Label var4x = new Label();
                  var2x.method2(var1xx -> var1xx.visitJumpInsn(154, var3x));
                  method8(var42, var2x, MolangExprCompiler.MolangValueType.BOOLEAN);
                  var2x.method2(var1xx -> var1xx.visitJumpInsn(153, var4x));
                  var2x.method2(var2xx -> {
                     Label var3xx = new Label();
                     var2xx.visitLabel(var3x);
                     method11(var2xx, 1);
                     var2xx.visitJumpInsn(167, var3xx);
                     var2xx.visitLabel(var4x);
                     method11(var2xx, 0);
                     var2xx.visitLabel(var3xx);
                  });
               });
               default -> throw new IncompatibleClassChangeError();
            };
         }
      } else if (var0 instanceof Block var23) {
         var5.method1(new MolangJitSignature.Data15());
         ArrayList var33 = new ArrayList();
         Object var41 = new Constant(0.0);

         for (Stmt var53 : var23.statements()) {
            if (var53 instanceof Return var61) {
               var41 = var61.value();
               break;
            }

            if (var53 == var23.statements().get(var23.statements().size() - 1) && var53 instanceof Expression var60) {
               var41 = var60.expr();
               break;
            }

            MolangStmtCompiler.MolangStmtContext var59 = new MolangStmtCompiler.MolangStmtContext();
            var33.add(Files6_2.method1(MolangStmtCompiler.method6(var53, var1, var2, var3, var59, var5), var53));
         }

         MolangExprCompiler.MolangValueEmitter var48 = method1((Expr)var41, var1, var2, var3, new MolangStmtCompiler.MolangStmtContext(), var5);
         var5.method1(new MolangJitSignature.Data11());
         return method12(MolangExprCompiler.MolangValueType.DOUBLE_PRIM, var2x -> {
            for (Files6_2 var4x : var33) {
               method7((MolangStmtCompiler.MolangStmtEmitter)var4x.field1, var2x);
            }

            method8(var48, var2x, MolangExprCompiler.MolangValueType.DOUBLE_PRIM);
         });
      } else if (var0 instanceof Assignment var22) {
         if (var22.variable() instanceof Access var32) {
            MolangExprCompiler.MolangValueEmitter var40 = method1(var22.expression(), var1, var2, var3, new MolangStmtCompiler.MolangStmtContext(), var5);
            if (var32.target() instanceof Variable var46) {
               MolangVariable var58 = var2.method5(MolangVariablePath.method1(var32));
               if (var58 == null && !var3.method6()) {
                  var5.method1(new MolangJitSignature.Data4(1));
                  int var65 = var1.method1(var32, true);
                  return method12(MolangExprCompiler.MolangValueType.DOUBLE_PRIM, var2x -> {
                     method8(var40, var2x, MolangExprCompiler.MolangValueType.DOUBLE_PRIM);
                     var2x.method2(var1xx -> {
                        var1xx.visitInsn(92);
                        var1xx.visitVarInsn(57, var65);
                     });
                  });
               } else {
                  var5.method1(new MolangJitSignature.Data4(0));
                  MolangVariable var64 = var2.method7(MolangVariablePath.method1(var32));
                  return method12(
                     MolangExprCompiler.MolangValueType.DOUBLE_PRIM,
                     var2x -> {
                        method9(var64, var2x);
                        method8(var40, var2x, MolangExprCompiler.MolangValueType.DOUBLE_PRIM);
                        var2x.method2(var0xx -> var0xx.visitInsn(93));
                        var2x.method2(
                           var0xx -> var0xx.visitFieldInsn(
                              181, org.objectweb.asm.Type.getInternalName(MolangVariable.class), "value", org.objectweb.asm.Type.DOUBLE_TYPE.getDescriptor()
                           )
                        );
                     }
                  );
               }
            } else {
               if (var32.target() instanceof Struct var52) {
                  ;
               }

               throw new RuntimeException("Unexpected assignment to non variable/struct.");
            }
         } else {
            throw new IllegalStateException("Asigning to non variable!");
         }
      } else {
         if (var0 instanceof Access var6) {
            if (var1.method2(var6)) {
               var5.method1(new MolangJitSignature.Data2(1));
               int var31 = var1.method1(var6, true);
               return method12(MolangExprCompiler.MolangValueType.DOUBLE_PRIM, var1x -> var1x.method2(var1xx -> var1xx.visitVarInsn(24, var31)));
            }

            if (var6.target() instanceof Variable var30) {
               String var38 = MolangVariablePath.method1(var6);
               MolangVariable var45 = var2.method5(var38);
               if (var45 == null && var3.method6()) {
                  var45 = new MolangVariable();
                  var2.method2(var38, var45);
               }

               if (var45 != null) {
                  var5.method1(new MolangJitSignature.Data2(2));
                  MolangVariable var51 = var45;
                  return method12(
                     MolangExprCompiler.MolangValueType.DOUBLE_PRIM,
                     var1x -> {
                        method9(var51, var1x);
                        var1x.method2(
                           var0xx -> var0xx.visitFieldInsn(
                              180, org.objectweb.asm.Type.getInternalName(MolangVariable.class), "value", org.objectweb.asm.Type.DOUBLE_TYPE.getDescriptor()
                           )
                        );
                     }
                  );
               }

               throw new RuntimeException("Not found: " + var38);
            }
         }

         if (var0 instanceof Variable var21) {
            String var29 = MolangVariablePath.method4(var21);
            MolangVariable var37 = var2.method5(var29);
            if (var37 == null && var3.method6()) {
               var37 = new MolangVariable();
               var2.method2(var29, var37);
            }

            if (var37 != null) {
               var5.method1(new MolangJitSignature.Data18());
               MolangVariable var44 = var37;
               return method12(
                  MolangExprCompiler.MolangValueType.DOUBLE_PRIM,
                  var1x -> {
                     method9(var44, var1x);
                     var1x.method2(
                        var0xx -> var0xx.visitFieldInsn(
                           180, org.objectweb.asm.Type.getInternalName(MolangVariable.class), "value", org.objectweb.asm.Type.DOUBLE_TYPE.getDescriptor()
                        )
                     );
                  }
               );
            } else {
               throw new RuntimeException("Not found: " + var29);
            }
         } else if (!(var0 instanceof Call var14)) {
            if (var0 instanceof Negate var20) {
               var5.method1(new MolangJitSignature.Data16());
               MolangExprCompiler.MolangValueEmitter var28 = method1(var20.value(), var1, var2, var3, new MolangStmtCompiler.MolangStmtContext(), var5);
               return method12(MolangExprCompiler.MolangValueType.DOUBLE_PRIM, var1x -> {
                  method8(var28, var1x, MolangExprCompiler.MolangValueType.DOUBLE_PRIM);
                  var1x.method2(var0xx -> var0xx.visitInsn(119));
               });
            } else if (var0 instanceof Group var19) {
               var5.method1(new MolangJitSignature.Data10());
               return method1(var19.value(), var1, var2, var3, new MolangStmtCompiler.MolangStmtContext(), var5);
            } else if (var0 instanceof Ternary var18) {
               var5.method1(new MolangJitSignature.Data9());
               MolangExprCompiler.MolangValueEmitter var27 = method1(var18.condition(), var1, var2, var3, new MolangStmtCompiler.MolangStmtContext(), var5);
               MolangExprCompiler.MolangValueEmitter var36 = method1(var18.ifFalse(), var1, var2, var3, new MolangStmtCompiler.MolangStmtContext(), var5);
               MolangExprCompiler.MolangValueEmitter var43 = method1(var18.ifTrue(), var1, var2, var3, new MolangStmtCompiler.MolangStmtContext(), var5);
               MolangExprCompiler.MolangValueType var50 = MolangExprCompiler.MolangValueType.DOUBLE_OBJ;
               if (var36.method2() == MolangExprCompiler.MolangValueType.BOOLEAN && var43.method2() == MolangExprCompiler.MolangValueType.BOOLEAN) {
                  var50 = MolangExprCompiler.MolangValueType.BOOLEAN;
               }

               if (var36.method2() == MolangExprCompiler.MolangValueType.DOUBLE_PRIM && var43.method2() == MolangExprCompiler.MolangValueType.DOUBLE_PRIM) {
                  var50 = MolangExprCompiler.MolangValueType.DOUBLE_PRIM;
               }

               MolangExprCompiler.MolangValueType var55 = var50;
               return method12(var50, var4x -> {
                  method8(var27, var4x, MolangExprCompiler.MolangValueType.BOOLEAN);
                  Label var5x = new Label();
                  Label var6x = new Label();
                  var4x.method2(var1xx -> var1xx.visitJumpInsn(154, var5x));
                  method8(var36, var4x, var55);
                  var4x.method2(var2xx -> {
                     var2xx.visitJumpInsn(167, var6x);
                     var2xx.visitLabel(var5x);
                  });
                  method8(var43, var4x, var55);
                  var4x.method2(var1xx -> var1xx.visitLabel(var6x));
               });
            } else if (var0 instanceof Not var17) {
               var5.method1(new MolangJitSignature.Data7());
               MolangExprCompiler.MolangValueEmitter var26 = method1(var17.value(), var1, var2, var3, new MolangStmtCompiler.MolangStmtContext(), var5);
               return method12(MolangExprCompiler.MolangValueType.BOOLEAN, var1x -> {
                  method8(var26, var1x, MolangExprCompiler.MolangValueType.BOOLEAN);
                  var1x.method2(var0xx -> {
                     Label var1xx = new Label();
                     Label var2x = new Label();
                     var0xx.visitJumpInsn(153, var1xx);
                     method11(var0xx, 0);
                     var0xx.visitJumpInsn(167, var2x);
                     var0xx.visitLabel(var1xx);
                     method11(var0xx, 1);
                     var0xx.visitLabel(var2x);
                  });
               });
            } else if (var0 instanceof Coalesce var16) {
               return method1(var16.value(), var1, var2, var3, new MolangStmtCompiler.MolangStmtContext(), var5);
            } else if (var0 instanceof Conditional var15) {
               return method1(new Ternary(var15.condition(), var15.ifTrue(), new Constant(0.0)), var1, var2, var3, new MolangStmtCompiler.MolangStmtContext(), var5);
            } else {
               throw new RuntimeException("Not jiting expr: " + var0.getClass() + " - " + var0);
            }
         } else {
            String var7 = MolangVariablePath.method2(var14);
            MolangBuiltin var35 = var2.method4(var7);
            if (var35 == null) {
               if (var3.method9()) {
                  if (var3.method10() != null) {
                     var3.method10().add("Failed to find method " + var7);
                  }

                  return method1(new Constant(0.0), var1, var2, var3, new MolangStmtCompiler.MolangStmtContext(), var5);
               } else {
                  throw new IllegalStateException("Failed to find method " + var7);
               }
            } else {
               int var9 = var14.arguments().size();
               if (!var35.method1(var9) && var3.method10() != null) {
                  var3.method10().add("Invalid argument count for " + var7 + " got " + var14.arguments().size());
               }

               if (var3.method7()) {
                  while (!var35.method1(var9)) {
                     if (var9 == 0) {
                        if (var3.method8()) {
                           return method1(new Constant(0.0), var1, var2, var3, new MolangStmtCompiler.MolangStmtContext(), var5);
                        }

                        throw new IllegalStateException("Too few arguments for " + var7 + " got " + var14.arguments().size());
                     }

                     var9--;
                  }
               } else if (!var35.method1(var9)) {
                  if (var3.method8()) {
                     return method1(new Constant(0.0), var1, var2, var3, new MolangStmtCompiler.MolangStmtContext(), var5);
                  }

                  throw new IllegalStateException("Invalid argument count for " + var7 + " got " + var14.arguments().size());
               }

               var5.method1(new MolangJitSignature.Data13((Class<? extends MolangBuiltin>)var35.getClass()));
               int var10 = 0;
               ArrayList var11 = new ArrayList();

               for (Expr var13 : var14.arguments()) {
                  var11.add(method1(var13, var1, var2, var3, new MolangStmtCompiler.MolangStmtContext(), var5));
                  if (++var10 == var9) {
                     break;
                  }
               }

               return method12(MolangExprCompiler.MolangValueType.DOUBLE_PRIM, var3x -> var35.method4(var11, var3x, var1));
            }
         }
      }
   }

   static void method2(MolangClassBuilder var0) {
      var0.method2(MolangExprCompiler::method4);
   }

   static void method3(MolangClassBuilder var0) {
      var0.method2(MolangExprCompiler::method5);
   }

   static void method4(MethodVisitor var0) {
      var0.visitMethodInsn(
         184,
         org.objectweb.asm.Type.getInternalName(Double.class),
         "valueOf",
         org.objectweb.asm.Type.getMethodDescriptor(
            org.objectweb.asm.Type.getType(Double.class), new org.objectweb.asm.Type[]{org.objectweb.asm.Type.DOUBLE_TYPE}
         ),
         false
      );
   }

   static void method5(MethodVisitor var0) {
      var0.visitMethodInsn(
         182,
         org.objectweb.asm.Type.getInternalName(Double.class),
         "doubleValue",
         org.objectweb.asm.Type.getMethodDescriptor(org.objectweb.asm.Type.DOUBLE_TYPE, new org.objectweb.asm.Type[0]),
         false
      );
   }

   static void method6(MolangExprCompiler.MolangValueEmitter var0, MolangClassBuilder var1) {
      var0.method1(var1);
      var0.method2().pop(var1);
   }

   static void method7(MolangStmtCompiler.MolangStmtEmitter var0, MolangClassBuilder var1) {
      var0.method1(var1);
   }

   static void method8(MolangExprCompiler.MolangValueEmitter var0, MolangClassBuilder var1, MolangExprCompiler.MolangValueType var2) {
      var0.method1(var1);
      if (var2 != var0.method2()) {
         var0.method2().convertTo(var2, var1);
      }
   }

   static void method9(Object var0, MolangClassBuilder var1) {
      String var2 = var1.method1(var0);
      var1.method2(var2x -> {
         var2x.visitVarInsn(25, 0);
         var2x.visitFieldInsn(180, "MolangJit", var2, org.objectweb.asm.Type.getDescriptor(var0.getClass()));
      });
   }

   static MolangExprCompiler.MolangValueEmitter method10(MolangExprCompiler.MolangValueEmitter var0, MolangExprCompiler.MolangValueEmitter var1, int var2, boolean var3) {
      return method12(MolangExprCompiler.MolangValueType.BOOLEAN, var4 -> {
         method8(var0, var4, MolangExprCompiler.MolangValueType.DOUBLE_PRIM);
         method8(var1, var4, MolangExprCompiler.MolangValueType.DOUBLE_PRIM);
         var4.method2(var2xx -> {
            var2xx.visitInsn(var3 ? 152 : 151);
            Label var3xx = new Label();
            Label var4x = new Label();
            var2xx.visitJumpInsn(var2, var3xx);
            method11(var2xx, 0);
            var2xx.visitJumpInsn(167, var4x);
            var2xx.visitLabel(var3xx);
            method11(var2xx, 1);
            var2xx.visitLabel(var4x);
         });
      });
   }

   static void method11(MethodVisitor var0, Object var1) {
      if (var1 instanceof Double var2) {
         double var6 = var2;
         if (var6 == 0.0) {
            var0.visitInsn(14);
            return;
         }

         if (var6 == 1.0) {
            var0.visitInsn(15);
            return;
         }
      } else if (var1 instanceof Integer var3) {
         double var8 = var3.intValue();
         if (var8 == -1.0) {
            var0.visitInsn(2);
            return;
         }

         if (var8 == 0.0) {
            var0.visitInsn(3);
            return;
         }

         if (var8 == 1.0) {
            var0.visitInsn(4);
            return;
         }

         if (var8 == 2.0) {
            var0.visitInsn(5);
            return;
         }

         if (var8 == 3.0) {
            var0.visitInsn(6);
            return;
         }

         if (var8 == 4.0) {
            var0.visitInsn(7);
            return;
         }

         if (var8 == 5.0) {
            var0.visitInsn(8);
            return;
         }
      } else if (var1 instanceof Float var4) {
         double var9 = var4.floatValue();
         if (var9 == 0.0) {
            var0.visitInsn(11);
            return;
         }

         if (var9 == 1.0) {
            var0.visitInsn(12);
            return;
         }

         if (var9 == 2.0) {
            var0.visitInsn(13);
            return;
         }
      } else if (var1 instanceof Long var5) {
         double var10 = var5.longValue();
         if (var10 == 0.0) {
            var0.visitInsn(9);
            return;
         }

         if (var10 == 1.0) {
            var0.visitInsn(10);
            return;
         }
      }

      var0.visitLdcInsn(var1);
   }

   static MolangExprCompiler.MolangValueEmitter method12(MolangExprCompiler.MolangValueType var0, Consumer<MolangClassBuilder> var1) {
      return new MolangExprCompiler.MolangValueEmitter(var1, var0);
   }

   class MolangValueEmitter {
      private final Consumer<MolangClassBuilder> field1;
      private final MolangExprCompiler.MolangValueType field2;

      public MolangValueEmitter(Consumer<MolangClassBuilder> var1, MolangExprCompiler.MolangValueType var2) {
         this.field1 = var1;
         this.field2 = var2;
      }

      public void method1(MolangClassBuilder var1) {
         this.field1.accept(var1);
      }

      @Generated
      public MolangExprCompiler.MolangValueType method2() {
         return this.field2;
      }
   }

   enum MolangValueType {
      DOUBLE_PRIM,
      DOUBLE_OBJ,
      BOOLEAN;

      private static final int TRUE = 1;
      private static final int FALSE = 0;

      public void convertTo(MolangExprCompiler.MolangValueType var1, MolangClassBuilder var2) {
         switch (this) {
            case DOUBLE_OBJ:
               switch (var1) {
                  case BOOLEAN:
                     this.convertTo(DOUBLE_PRIM, var2);
                     DOUBLE_PRIM.convertTo(BOOLEAN, var2);
                     return;
                  case DOUBLE_PRIM:
                     MolangExprCompiler.method3(var2);
                     return;
                  default:
                     return;
               }
            case BOOLEAN:
               switch (var1) {
                  case DOUBLE_OBJ:
                     this.convertTo(DOUBLE_PRIM, var2);
                     DOUBLE_PRIM.convertTo(var1, var2);
                     return;
                  case DOUBLE_PRIM:
                     var2.method2(var0 -> var0.visitInsn(135));
                     return;
                  default:
                     return;
               }
            case DOUBLE_PRIM:
               switch (var1) {
                  case DOUBLE_OBJ:
                     MolangExprCompiler.method2(var2);
                     break;
                  case BOOLEAN:
                     var2.method2(var0 -> {
                        Label var1x = new Label();
                        Label var2x = new Label();
                        MolangExprCompiler.method11(var0, 0.0);
                        var0.visitInsn(151);
                        var0.visitJumpInsn(153, var1x);
                        MolangExprCompiler.method11(var0, 1);
                        var0.visitJumpInsn(167, var2x);
                        var0.visitLabel(var1x);
                        MolangExprCompiler.method11(var0, 0);
                        var0.visitLabel(var2x);
                     });
               }
         }
      }

      public void pop(MolangClassBuilder var1) {
         switch (this) {
            case DOUBLE_OBJ:
            case BOOLEAN:
               var1.method2(var0 -> var0.visitInsn(87));
               break;
            case DOUBLE_PRIM:
               var1.method2(var0 -> var0.visitInsn(88));
         }
      }
   }
}
