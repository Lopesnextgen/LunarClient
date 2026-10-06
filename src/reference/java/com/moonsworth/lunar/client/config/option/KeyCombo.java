package com.moonsworth.lunar.client.config.option;

import com.lunarclient.dfu.serialization.Codec;
import com.lunarclient.dfu.serialization.codecs.RecordCodecBuilder;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.util.nameplate.Nameplate;
import lombok.Generated;
import org.jetbrains.annotations.Contract;

public class KeyCombo {
   public static final Codec<KeyCombo> field1 = RecordCodecBuilder.create(
      var0 -> var0.group(
            Codec.BOOL.optionalFieldOf("alt", false).forGetter(KeyCombo::method5),
            Codec.BOOL.optionalFieldOf("shift", false).forGetter(KeyCombo::method6),
            Codec.BOOL.optionalFieldOf("control", false).forGetter(KeyCombo::method7),
            Nameplate.method25(KeyCode::values).fieldOf("value").forGetter(KeyCombo::method8)
         )
         .apply(var0, KeyCombo::new)
   );
   private final boolean field2;
   private final boolean field3;
   private final boolean field4;
   private final KeyCode field5;

   public boolean hasModifiers() {
      return this.field2 || this.field3 || this.field4;
   }

   @Contract("_->new")
   public static KeyCombo method1(KeyCode var0) {
      return new KeyCombo(false, false, false, var0);
   }

   @Contract("_->new")
   public static KeyCombo method2(KeyCode var0) {
      return new KeyCombo(false, false, true, var0);
   }

   @Contract("_->new")
   public static KeyCombo method3(KeyCode var0) {
      return new KeyCombo(false, true, false, var0);
   }

   @Contract("_->new")
   public static KeyCombo method4(KeyCode var0) {
      return new KeyCombo(true, false, false, var0);
   }

   @Generated
   public boolean method5() {
      return this.field2;
   }

   @Generated
   public boolean method6() {
      return this.field3;
   }

   @Generated
   public boolean method7() {
      return this.field4;
   }

   @Generated
   public KeyCode method8() {
      return this.field5;
   }

   @Generated
   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof KeyCombo var2)) {
         return false;
      } else {
         if (!var2.canEqual(this)) {
            return false;
         }

         if (this.method5() != var2.method5()) {
            return false;
         }

         if (this.method6() != var2.method6()) {
            return false;
         }

         if (this.method7() != var2.method7()) {
            return false;
         }

         KeyCode var3 = this.method8();
         KeyCode var4 = var2.method8();
         return var3 == null ? var4 == null : var3.equals(var4);
      }
   }

   @Generated
   protected boolean canEqual(Object var1) {
      return var1 instanceof KeyCombo;
   }

   @Generated
   @Override
   public int hashCode() {
      byte var1 = 59;
      int var2 = 1;
      var2 = var2 * 59 + (this.method5() ? 79 : 97);
      var2 = var2 * 59 + (this.method6() ? 79 : 97);
      var2 = var2 * 59 + (this.method7() ? 79 : 97);
      KeyCode var3 = this.method8();
      return var2 * 59 + (var3 == null ? 43 : var3.hashCode());
   }

   @Generated
   @Override
   public String toString() {
      return "KeyBind(alt=" + this.method5() + ", shift=" + this.method6() + ", control=" + this.method7() + ", value=" + this.method8() + ")";
   }

   @Generated
   public KeyCombo(boolean var1, boolean var2, boolean var3, KeyCode var4) {
      this.field2 = var1;
      this.field3 = var2;
      this.field4 = var3;
      this.field5 = var4;
   }
}
