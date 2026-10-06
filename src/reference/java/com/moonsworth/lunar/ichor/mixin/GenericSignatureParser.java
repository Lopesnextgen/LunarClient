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

public class GenericSignatureParser extends SignatureVisitor {
   private GenericSignatureParser.Type field1 = GenericSignatureParser.Type.NONE;
   private Stack<MixinHelper3> stack = null;
   private MixinHelper3 field2 = null;
   private GenericSignatureParser.Data field3 = null;
   private final List<GenericSignatureParser.TypeVariableRef> field4 = new ArrayList<>();
   private List<MixinHelper3> field5;
   private final Map<String, GenericSignatureParser.Data> field6 = new LinkedHashMap<>(4);
   private final List<MixinHelper3> field7 = new ArrayList<>();
   private MixinHelper3 field8 = null;
   private MixinHelper3 field9 = null;
   private final List<MixinHelper3> field10 = new ArrayList<>();

   @Nullable
   @Contract("!null, _, _ -> !null")
   public static MethodSignature method1(String text0, @Nullable String text1, @Nullable MixinHelper3 mixinhelper32) {
      if (text0 == null) {
         return null;
      }

      GenericSignatureParser signaturevisitorimpl3 = new GenericSignatureParser();
      if (text1 != null) {
         if (mixinhelper32 != null) {
            signaturevisitorimpl3.field5 = mixinhelper32.getParams();
         }

         new SignatureReader(text1).accept(signaturevisitorimpl3);
      }

      try {
         SignatureReader signaturereader4 = new SignatureReader(text0);
         signaturereader4.accept(signaturevisitorimpl3);
         return signaturevisitorimpl3.method5();
      } catch (Exception exception5) {
         throw new IllegalArgumentException("Invalid method signature: " + text0, exception5);
      }
   }

   @Nullable
   @Contract("!null, _, _ -> !null")
   public static MixinHelper3 method2(String text0, @Nullable String text1, @Nullable MixinHelper3 mixinhelper32) {
      if (text0 == null) {
         return null;
      }

      GenericSignatureParser signaturevisitorimpl3 = new GenericSignatureParser();
      if (text1 != null) {
         if (mixinhelper32 != null) {
            signaturevisitorimpl3.field5 = mixinhelper32.getParams();
         }

         new SignatureReader(text1).accept(signaturevisitorimpl3);
      }

      SignatureReader signaturereader4 = new SignatureReader(text0);
      signaturevisitorimpl3.method4(GenericSignatureParser.Type.RETURN);

      try {
         signaturereader4.acceptType(signaturevisitorimpl3);
         signaturevisitorimpl3.method4(GenericSignatureParser.Type.NONE);
         return signaturevisitorimpl3.field8;
      } catch (Exception exception6) {
         throw new IllegalArgumentException("Invalid type signature: " + text0, exception6);
      }
   }

   @Nullable
   @Contract("!null, _, _ -> !null")
   public static ClassSignature method3(String text0, @Nullable String text1, @Nullable MixinHelper3 mixinhelper32) {
      if (text0 == null) {
         return null;
      }

      GenericSignatureParser signaturevisitorimpl3 = new GenericSignatureParser();
      if (text1 != null) {
         if (mixinhelper32 != null) {
            signaturevisitorimpl3.field5 = mixinhelper32.getParams();
         }

         new SignatureReader(text1).accept(signaturevisitorimpl3);
      }

      SignatureReader signaturereader4 = new SignatureReader(text0);

      try {
         signaturereader4.accept(signaturevisitorimpl3);
         return signaturevisitorimpl3.method6();
      } catch (Exception exception6) {
         throw new IllegalArgumentException("Invalid class signature: " + text0, exception6);
      }
   }

   public GenericSignatureParser() {
      super(589824);
   }

   public void method4(GenericSignatureParser.Type type1) {
      GenericSignatureParser.Type type2 = this.field1;
      switch (type2) {
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
      this.field1 = type1;
   }

   public MethodSignature method5() {
      this.method4(GenericSignatureParser.Type.NONE);
      this.field4.forEach(GenericSignatureParser.TypeVariableRef::resolve);
      return new MethodSignature(this.field7, this.field8);
   }

   public ClassSignature method6() {
      this.method4(GenericSignatureParser.Type.NONE);
      return new ClassSignature(this.field6, this.field9, this.field10);
   }

   public void visitFormalTypeParameter(String text1) {
      int index2 = this.field6.size();
      MixinHelper3 mixinhelper33 = null;
      if (this.field5 != null && this.field5.size() > index2) {
         mixinhelper33 = this.field5.get(index2);
      }

      GenericSignatureParser.Data data4 = new GenericSignatureParser.Data(text1, mixinhelper33);
      this.field6.put(data4.field1, data4);
      this.method4(GenericSignatureParser.Type.TYPE_PARAMETER);
      this.field3 = data4;
   }

   public SignatureVisitor visitClassBound() {
      this.method4(GenericSignatureParser.Type.CLASS_BOUND);
      return this;
   }

   public SignatureVisitor visitInterfaceBound() {
      this.method4(GenericSignatureParser.Type.INTERFACE_BOUND);
      return this;
   }

   public SignatureVisitor visitSuperclass() {
      this.method4(GenericSignatureParser.Type.SUPERCLASS);
      return this;
   }

   public SignatureVisitor visitInterface() {
      this.method4(GenericSignatureParser.Type.INTERFACE);
      return this;
   }

   public SignatureVisitor visitParameterType() {
      this.method4(GenericSignatureParser.Type.PARAM);
      return this;
   }

   public SignatureVisitor visitReturnType() {
      this.method4(GenericSignatureParser.Type.RETURN);
      return this;
   }

   public SignatureVisitor visitExceptionType() {
      this.method4(GenericSignatureParser.Type.NONE);
      return this;
   }

   public void visitBaseType(char character1) {
      if (this.field1 != GenericSignatureParser.Type.NONE) {
         if (this.field2 instanceof ArrayTypeSignature mixinhelper332 && mixinhelper332.field3 == null) {
            mixinhelper332.field3 = new MixinHelper3(new PrimitiveTypeSignature(character1));
         } else {
            this.field2 = new MixinHelper3(new PrimitiveTypeSignature(character1));
         }
      }
   }

   public SignatureVisitor visitArrayType() {
      if (this.field1 != GenericSignatureParser.Type.NONE) {
         if (this.field2 instanceof ArrayTypeSignature mixinhelper331) {
            mixinhelper331.depth++;
         } else {
            this.field2 = new ArrayTypeSignature();
         }
      }

      return this;
   }

   public void visitClassType(String text1) {
      if (this.field1 != GenericSignatureParser.Type.NONE) {
         ClassTypeSignature mixinhelper72 = new ClassTypeSignature(text1);
         if (this.field2 instanceof ArrayTypeSignature mixinhelper333 && mixinhelper333.field3 == null) {
            mixinhelper333.field3 = new MixinHelper3(mixinhelper72);
         } else {
            this.field2 = new MixinHelper3(mixinhelper72);
         }
      }
   }

   public void visitTypeVariable(String text1) {
      if (this.field1 != GenericSignatureParser.Type.NONE) {
         GenericSignatureParser.TypeVariableRef data22 = new GenericSignatureParser.TypeVariableRef(text1);
         if (this.field2 instanceof ArrayTypeSignature mixinhelper333 && mixinhelper333.field3 == null) {
            mixinhelper333.field3 = data22;
         } else if (this.field2 != null) {
            this.field2.method1(data22);
            if (this.stack != null && this.stack.peek() == this.field2) {
               this.stack.pop();
            }
         } else {
            this.field2 = data22;
         }
      }
   }

   public void visitInnerClassType(String text1) {
   }

   public void visitTypeArgument() {
      if (this.field1 != GenericSignatureParser.Type.NONE && this.field2 != null) {
         this.field2.method1(new WildcardTypeSignature());
         if (this.stack != null && !this.stack.isEmpty() && this.stack.peek() == this.field2) {
            this.stack.pop();
         }
      }
   }

   public SignatureVisitor visitTypeArgument(char character1) {
      if (this.field1 != GenericSignatureParser.Type.NONE && this.field2 != null) {
         if (this.stack == null) {
            this.stack = new Stack<>();
         }

         this.stack.push(this.field2);
      }

      return this;
   }

   public void visitEnd() {
      if (this.stack != null && !this.stack.isEmpty()) {
         MixinHelper3 mixinhelper31 = this.stack.pop();
         if (this.field2 != null) {
            mixinhelper31.method1(this.field2);
         }

         this.field2 = mixinhelper31;
      }
   }

   public static class Data {
      public final String field1;
      @Nullable
      public final MixinHelper3 field2;
      @Nullable
      public MixinHelper3 field3 = null;
      public final List<MixinHelper3> field4 = new ArrayList<>();

      public Data(String text1, @Nullable MixinHelper3 mixinhelper32) {
         this.field1 = text1;
         this.field2 = mixinhelper32;
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
      public boolean equals(Object obj1) {
         if (obj1 == this) {
            return true;
         } else if (!(obj1 instanceof GenericSignatureParser.Data data2)) {
            return false;
         } else {
            if (!data2.canEqual(this)) {
               return false;
            }

            String text3 = this.field1;
            String text4 = data2.field1;
            if (text3 == null ? text4 == null : text3.equals(text4)) {
               MixinHelper3 mixinhelper35 = this.field2;
               MixinHelper3 mixinhelper36 = data2.field2;
               if (mixinhelper35 == null ? mixinhelper36 == null : mixinhelper35.equals(mixinhelper36)) {
                  MixinHelper3 mixinhelper37 = this.field3;
                  MixinHelper3 mixinhelper38 = data2.field3;
                  if (mixinhelper37 == null ? mixinhelper38 == null : mixinhelper37.equals(mixinhelper38)) {
                     List list9 = this.field4;
                     List list10 = data2.field4;
                     return list9 == null ? list10 == null : list9.equals(list10);
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
      protected boolean canEqual(Object obj1) {
         return obj1 instanceof GenericSignatureParser.Data;
      }

      @Generated
      @Override
      public int hashCode() {
         byte number1 = 59;
         int number2 = 1;
         String text3 = this.field1;
         number2 = number2 * 59 + (text3 == null ? 43 : text3.hashCode());
         MixinHelper3 mixinhelper34 = this.field2;
         number2 = number2 * 59 + (mixinhelper34 == null ? 43 : mixinhelper34.hashCode());
         MixinHelper3 mixinhelper35 = this.field3;
         number2 = number2 * 59 + (mixinhelper35 == null ? 43 : mixinhelper35.hashCode());
         List list6 = this.field4;
         return number2 * 59 + (list6 == null ? 43 : list6.hashCode());
      }
   }

   public class TypeVariableRef extends MixinHelper3 {
      public final String field3;

      public TypeVariableRef(String text2) {
         super(new GenericSignatureParser.TypeVariableName(text2));
         this.field3 = text2;
         GenericSignatureParser.this.field4.add(this);
      }

      public void resolve() {
         if (this.IOHHCRICHOICOROOIRIRCHHRHORCHH instanceof GenericSignatureParser.TypeVariableName) {
            GenericSignatureParser.Data data1 = GenericSignatureParser.this.field6.get(this.field3);
            if (data1 == null) {
               this.IOHHCRICHOICOROOIRIRCHHRHORCHH = new GenericSignatureParser.TypeVariableName(this.field3);
            } else {
               MixinHelper3 mixinhelper32 = data1.method1();
               if (mixinhelper32 == null) {
                  this.IOHHCRICHOICOROOIRIRCHHRHORCHH = new GenericSignatureParser.TypeVariableName(this.field3);
               } else {
                  if (mixinhelper32 instanceof GenericSignatureParser.TypeVariableRef data23) {
                     data23.resolve();
                  }

                  this.IOHHCRICHOICOROOIRIRCHHRHORCHH = mixinhelper32.field2;
                  this.params = mixinhelper32.params == null ? null : new ArrayList<>(mixinhelper32.params);
               }
            }
         }
      }
   }

   class TypeVariableName implements DescriptorType {
      private final String field1;

      TypeVariableName(String text1) {
         this.field1 = text1;
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

      Type() {
      }
   }
}
