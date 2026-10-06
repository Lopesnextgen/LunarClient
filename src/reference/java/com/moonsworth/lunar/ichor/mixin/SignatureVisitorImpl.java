package com.moonsworth.lunar.ichor.mixin;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import javax.annotation.Nullable;
import lombok.Generated;
import org.jetbrains.annotations.Contract;
import org.objectweb.asm.signature.SignatureReader;
import org.objectweb.asm.signature.SignatureVisitor;

public class SignatureVisitorImpl extends SignatureVisitor {
   private SignatureVisitorImpl.Type field1 = SignatureVisitorImpl.Type.NONE;
   private Stack<MixinHelper3> stack = null;
   private MixinHelper3 field2 = null;
   private SignatureVisitorImpl.Data field3 = null;
   private final List<SignatureVisitorImpl.Data2> field4 = new ArrayList<>();
   private List<MixinHelper3> field5;
   private final Map<String, SignatureVisitorImpl.Data> field6 = new LinkedHashMap<>(4);
   private final List<MixinHelper3> field7 = new ArrayList<>();
   private MixinHelper3 field8 = null;
   private MixinHelper3 field9 = null;
   private final List<MixinHelper3> field10 = new ArrayList<>();

   @Nullable
   @Contract("!null, _, _ -> !null")
   public static MixinHelper2 method1(String var0, @Nullable String var1, @Nullable MixinHelper3 var2) {
      if (var0 == null) {
         return null;
      }

      SignatureVisitorImpl var3 = new SignatureVisitorImpl();
      if (var1 != null) {
         if (var2 != null) {
            var3.field5 = var2.getParams();
         }

         new SignatureReader(var1).accept(var3);
      }

      try {
         SignatureReader var4 = new SignatureReader(var0);
         var4.accept(var3);
         return var3.method5();
      } catch (Exception var5) {
         throw new IllegalArgumentException("Invalid method signature: " + var0, var5);
      }
   }

   @Nullable
   @Contract("!null, _, _ -> !null")
   public static MixinHelper3 method2(String var0, @Nullable String var1, @Nullable MixinHelper3 var2) {
      if (var0 == null) {
         return null;
      }

      SignatureVisitorImpl var3 = new SignatureVisitorImpl();
      if (var1 != null) {
         if (var2 != null) {
            var3.field5 = var2.getParams();
         }

         new SignatureReader(var1).accept(var3);
      }

      SignatureReader var4 = new SignatureReader(var0);
      var3.method4(SignatureVisitorImpl.Type.RETURN);

      try {
         var4.acceptType(var3);
         var3.method4(SignatureVisitorImpl.Type.NONE);
         return var3.field8;
      } catch (Exception var6) {
         throw new IllegalArgumentException("Invalid type signature: " + var0, var6);
      }
   }

   @Nullable
   @Contract("!null, _, _ -> !null")
   public static MixinHelper4 method3(String var0, @Nullable String var1, @Nullable MixinHelper3 var2) {
      if (var0 == null) {
         return null;
      }

      SignatureVisitorImpl var3 = new SignatureVisitorImpl();
      if (var1 != null) {
         if (var2 != null) {
            var3.field5 = var2.getParams();
         }

         new SignatureReader(var1).accept(var3);
      }

      SignatureReader var4 = new SignatureReader(var0);

      try {
         var4.accept(var3);
         return var3.method6();
      } catch (Exception var6) {
         throw new IllegalArgumentException("Invalid class signature: " + var0, var6);
      }
   }

   public SignatureVisitorImpl() {
      super(589824);
   }

   public void method4(SignatureVisitorImpl.Type var1) {
      SignatureVisitorImpl.Type var2 = this.field1;
      switch (var2) {
         case CLASS_BOUND:
            this.field3.field3 = this.field2;
            break;
         case INTERFACE_BOUND:
            this.field3.field4.add(this.field2);
            break;
         case PARAM:
            this.field7.add(this.field2);
            break;
         case RETURN:
            this.field8 = this.field2;
            break;
         case SUPERCLASS:
            this.field9 = this.field2;
            break;
         case INTERFACE:
            this.field10.add(this.field2);
      }

      this.field2 = null;
      this.stack = null;
      this.field1 = var1;
   }

   public MixinHelper2 method5() {
      this.method4(SignatureVisitorImpl.Type.NONE);
      this.field4.forEach(SignatureVisitorImpl.Data2::resolve);
      return new MixinHelper2(this.field7, this.field8);
   }

   public MixinHelper4 method6() {
      this.method4(SignatureVisitorImpl.Type.NONE);
      return new MixinHelper4(this.field6, this.field9, this.field10);
   }

   public void visitFormalTypeParameter(String var1) {
      int var2 = this.field6.size();
      MixinHelper3 var3 = null;
      if (this.field5 != null && this.field5.size() > var2) {
         var3 = this.field5.get(var2);
      }

      SignatureVisitorImpl.Data var4 = new SignatureVisitorImpl.Data(var1, var3);
      this.field6.put(var4.field1, var4);
      this.method4(SignatureVisitorImpl.Type.TYPE_PARAMETER);
      this.field3 = var4;
   }

   public SignatureVisitor visitClassBound() {
      this.method4(SignatureVisitorImpl.Type.CLASS_BOUND);
      return this;
   }

   public SignatureVisitor visitInterfaceBound() {
      this.method4(SignatureVisitorImpl.Type.INTERFACE_BOUND);
      return this;
   }

   public SignatureVisitor visitSuperclass() {
      this.method4(SignatureVisitorImpl.Type.SUPERCLASS);
      return this;
   }

   public SignatureVisitor visitInterface() {
      this.method4(SignatureVisitorImpl.Type.INTERFACE);
      return this;
   }

   public SignatureVisitor visitParameterType() {
      this.method4(SignatureVisitorImpl.Type.PARAM);
      return this;
   }

   public SignatureVisitor visitReturnType() {
      this.method4(SignatureVisitorImpl.Type.RETURN);
      return this;
   }

   public SignatureVisitor visitExceptionType() {
      this.method4(SignatureVisitorImpl.Type.NONE);
      return this;
   }

   public void visitBaseType(char var1) {
      if (this.field1 != SignatureVisitorImpl.Type.NONE) {
         if (this.field2 instanceof MixinHelper33 var2 && var2.field3 == null) {
            var2.field3 = new MixinHelper3(new MixinHelper5(var1));
         } else {
            this.field2 = new MixinHelper3(new MixinHelper5(var1));
         }
      }
   }

   public SignatureVisitor visitArrayType() {
      if (this.field1 != SignatureVisitorImpl.Type.NONE) {
         if (this.field2 instanceof MixinHelper33 var1) {
            var1.depth++;
         } else {
            this.field2 = new MixinHelper33();
         }
      }

      return this;
   }

   public void visitClassType(String var1) {
      if (this.field1 != SignatureVisitorImpl.Type.NONE) {
         MixinHelper7 var2 = new MixinHelper7(var1);
         if (this.field2 instanceof MixinHelper33 var3 && var3.field3 == null) {
            var3.field3 = new MixinHelper3(var2);
         } else {
            this.field2 = new MixinHelper3(var2);
         }
      }
   }

   public void visitTypeVariable(String var1) {
      if (this.field1 != SignatureVisitorImpl.Type.NONE) {
         SignatureVisitorImpl.Data2 var2 = new SignatureVisitorImpl.Data2(var1);
         if (this.field2 instanceof MixinHelper33 var3 && var3.field3 == null) {
            var3.field3 = var2;
         } else if (this.field2 != null) {
            this.field2.method1(var2);
            if (this.stack != null && this.stack.peek() == this.field2) {
               this.stack.pop();
            }
         } else {
            this.field2 = var2;
         }
      }
   }

   public void visitInnerClassType(String var1) {
   }

   public void visitTypeArgument() {
      if (this.field1 != SignatureVisitorImpl.Type.NONE && this.field2 != null) {
         this.field2.method1(new MixinHelper32());
         if (this.stack != null && !this.stack.isEmpty() && this.stack.peek() == this.field2) {
            this.stack.pop();
         }
      }
   }

   public SignatureVisitor visitTypeArgument(char var1) {
      if (this.field1 != SignatureVisitorImpl.Type.NONE && this.field2 != null) {
         if (this.stack == null) {
            this.stack = new Stack<>();
         }

         this.stack.push(this.field2);
      }

      return this;
   }

   public void visitEnd() {
      if (this.stack != null && !this.stack.isEmpty()) {
         MixinHelper3 var1 = this.stack.pop();
         if (this.field2 != null) {
            var1.method1(this.field2);
         }

         this.field2 = var1;
      }
   }

   public static class Data {
      public final String field1;
      @Nullable
      public final MixinHelper3 field2;
      @Nullable
      public MixinHelper3 field3 = null;
      public final List<MixinHelper3> field4 = new ArrayList<>();

      public Data(String var1, @Nullable MixinHelper3 var2) {
         this.field1 = var1;
         this.field2 = var2;
      }

      public MixinHelper3 method1() {
         return this.field2 != null ? this.field2 : (this.field3 != null ? this.field3 : (!this.field4.isEmpty() ? this.field4.get(0) : null));
      }

      @Override
      public String toString() {
         return this.field1 + ":" + this.field2;
      }

      @Generated
      @Override
      public boolean equals(Object var1) {
         if (var1 == this) {
            return true;
         } else if (!(var1 instanceof SignatureVisitorImpl.Data var2)) {
            return false;
         } else {
            if (!var2.canEqual(this)) {
               return false;
            }

            String var3 = this.field1;
            String var4 = var2.field1;
            if (var3 == null ? var4 == null : var3.equals(var4)) {
               MixinHelper3 var5 = this.field2;
               MixinHelper3 var6 = var2.field2;
               if (var5 == null ? var6 == null : var5.equals(var6)) {
                  MixinHelper3 var7 = this.field3;
                  MixinHelper3 var8 = var2.field3;
                  if (var7 == null ? var8 == null : var7.equals(var8)) {
                     List var9 = this.field4;
                     List var10 = var2.field4;
                     return var9 == null ? var10 == null : var9.equals(var10);
                  } else {
                     return false;
                  }
               } else {
                  return false;
               }
            } else {
               return false;
            }
         }
      }

      @Generated
      protected boolean canEqual(Object var1) {
         return var1 instanceof SignatureVisitorImpl.Data;
      }

      @Generated
      @Override
      public int hashCode() {
         byte var1 = 59;
         int var2 = 1;
         String var3 = this.field1;
         var2 = var2 * 59 + (var3 == null ? 43 : var3.hashCode());
         MixinHelper3 var4 = this.field2;
         var2 = var2 * 59 + (var4 == null ? 43 : var4.hashCode());
         MixinHelper3 var5 = this.field3;
         var2 = var2 * 59 + (var5 == null ? 43 : var5.hashCode());
         List var6 = this.field4;
         return var2 * 59 + (var6 == null ? 43 : var6.hashCode());
      }
   }

   public class Data2 extends MixinHelper3 {
      public final String field3;

      public Data2(String var2) {
         super(new SignatureVisitorImpl.Data3(var2));
         this.field3 = var2;
         SignatureVisitorImpl.this.field4.add(this);
      }

      public void resolve() {
         if (this.IOHHCRICHOICOROOIRIRCHHRHORCHH instanceof SignatureVisitorImpl.Data3) {
            SignatureVisitorImpl.Data var1 = SignatureVisitorImpl.this.field6.get(this.field3);
            if (var1 == null) {
               this.IOHHCRICHOICOROOIRIRCHHRHORCHH = new SignatureVisitorImpl.Data3(this.field3);
            } else {
               MixinHelper3 var2 = var1.method1();
               if (var2 == null) {
                  this.IOHHCRICHOICOROOIRIRCHHRHORCHH = new SignatureVisitorImpl.Data3(this.field3);
               } else {
                  if (var2 instanceof SignatureVisitorImpl.Data2 var3) {
                     var3.resolve();
                  }

                  this.IOHHCRICHOICOROOIRIRCHHRHORCHH = var2.field2;
                  this.params = var2.params == null ? null : new ArrayList<>(var2.params);
               }
            }
         }
      }
   }

   class Data3 implements MixinHelper {
      private final String field1;

      Data3(String var1) {
         this.field1 = var1;
      }

      @Override
      public String getDescriptor() {
         return "T" + this.field1 + ";";
      }

      public String name() {
         return this.field1;
      }
   }

   public enum Type {
      NONE,
      TYPE_PARAMETER,
      CLASS_BOUND,
      INTERFACE_BOUND,
      PARAM,
      RETURN,
      SUPERCLASS,
      INTERFACE;
   }
}
