package com.moonsworth.lunar.ichor.mixin;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import lombok.Generated;
import lombok.NonNull;

public class MixinHelper3 {
   public static final MixinHelper3 field1 = new MixinHelper3(new PrimitiveTypeSignature('V'));
   @NonNull
   DescriptorType field2;
   @Nullable
   List<MixinHelper3> params = null;

   public void method1(MixinHelper3 mixinhelper31) {
      if (this.params == null) {
         this.params = new ArrayList<>();
      }

      this.params.add(mixinhelper31);
   }

   @Override
   public String toString() {
      StringBuilder builder1 = new StringBuilder();
      if (this.field2 instanceof ClassTypeSignature mixinhelper72) {
         builder1.append("L").append(mixinhelper72.name());
      } else {
         builder1.append(this.field2.getDescriptor());
      }

      if (this.params != null && !this.params.isEmpty()) {
         builder1.append('<');

         for (MixinHelper3 mixinhelper36 : this.params) {
            if (mixinhelper36 != this) {
               if (mixinhelper36 instanceof GenericSignatureParser.TypeVariableRef typeVariableRef) {
                  builder1.append("T").append(typeVariableRef.field3).append(";");
               } else {
                  builder1.append(mixinhelper36);
               }
            }
         }

         builder1.append(">");
      }

      if (this.field2 instanceof ClassTypeSignature) {
         builder1.append(';');
      }

      return builder1.toString();
   }

   @Override
   public boolean equals(Object object) {
      if (this == object) {
         return true;
      }

      if (object instanceof MixinHelper3 mixinhelper32) {
         if (!this.field2.equals(mixinhelper32.field2)) {
            return false;
         }

         if (this.params == null) {
            return mixinhelper32.params == null;
         }

         if (mixinhelper32.params == null) {
            return false;
         }

         if (this.params.size() != mixinhelper32.params.size()) {
            return false;
         }

         for (int index3 = 0; index3 < this.params.size(); index3++) {
            MixinHelper3 mixinhelper34 = this.params.get(index3);
            MixinHelper3 mixinhelper35 = mixinhelper32.params.get(index3);
            if (mixinhelper34 != this && mixinhelper35 != mixinhelper32 && mixinhelper34 != mixinhelper35 && !mixinhelper34.equals(mixinhelper35)) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   @NonNull
   @Generated
   public DescriptorType method2() {
      return this.field2;
   }

   @Nullable
   @Generated
   public List<MixinHelper3> getParams() {
      return this.params;
   }

   @Generated
   public void method3(@NonNull DescriptorType mixinhelper1) {
      if (mixinhelper1 == null) {
         throw new NullPointerException("typeDesc is marked non-null but is null");
      }

      this.field2 = mixinhelper1;
   }

   @Generated
   public void setParams(@Nullable List<MixinHelper3> list1) {
      this.params = list1;
   }

   @Generated
   public MixinHelper3(@NonNull DescriptorType mixinhelper1) {
      if (mixinhelper1 == null) {
         throw new NullPointerException("typeDesc is marked non-null but is null");
      }

      this.field2 = mixinhelper1;
   }
}
