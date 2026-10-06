package com.moonsworth.lunar.client.config.option;

import com.lunarclient.dfu.serialization.Codec;
import com.lunarclient.dfu.serialization.codecs.RecordCodecBuilder;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.util.io.ExtraCodecs;
import lombok.Generated;
import org.jetbrains.annotations.Contract;

public class KeyBind {
   public static final Codec<KeyBind> field1 = RecordCodecBuilder.create(
      arg0 -> arg0.group(
            Codec.BOOL.optionalFieldOf("alt", false).forGetter(KeyBind::method5),
            Codec.BOOL.optionalFieldOf("shift", false).forGetter(KeyBind::method6),
            Codec.BOOL.optionalFieldOf("control", false).forGetter(KeyBind::method7),
            ExtraCodecs.method25(KeyCode::values).fieldOf("value").forGetter(KeyBind::method8)
         )
         .apply(arg0, KeyBind::new)
   );
   private final boolean field2;
   private final boolean field3;
   private final boolean field4;
   private final KeyCode field5;

   public boolean hasModifiers() {
      return this.field2 || this.field3 || this.field4;
   }

   @Contract("_->new")
   public static KeyBind method1(KeyCode bridgetype_80) {
      return new KeyBind(false, false, false, bridgetype_80);
   }

   @Contract("_->new")
   public static KeyBind method2(KeyCode bridgetype_80) {
      return new KeyBind(false, false, true, bridgetype_80);
   }

   @Contract("_->new")
   public static KeyBind method3(KeyCode bridgetype_80) {
      return new KeyBind(false, true, false, bridgetype_80);
   }

   @Contract("_->new")
   public static KeyBind method4(KeyCode bridgetype_80) {
      return new KeyBind(true, false, false, bridgetype_80);
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
   public boolean equals(Object obj1) {
      if (obj1 == this) {
         return true;
      } else if (!(obj1 instanceof KeyBind lighting22)) {
         return false;
      } else {
         if (!lighting22.canEqual(this)) {
            return false;
         }

         if (this.method5() != lighting22.method5()) {
            return false;
         }

         if (this.method6() != lighting22.method6()) {
            return false;
         }

         if (this.method7() != lighting22.method7()) {
            return false;
         }

         KeyCode bridgetype_83 = this.method8();
         KeyCode bridgetype_84 = lighting22.method8();
         return bridgetype_83 == null ? bridgetype_84 == null : bridgetype_83.equals(bridgetype_84);
      }
   }

   @Generated
   protected boolean canEqual(Object obj1) {
      return obj1 instanceof KeyBind;
   }

   @Generated
   @Override
   public int hashCode() {
      byte number1 = 59;
      int number2 = 1;
      number2 = number2 * 59 + (this.method5() ? 79 : 97);
      number2 = number2 * 59 + (this.method6() ? 79 : 97);
      number2 = number2 * 59 + (this.method7() ? 79 : 97);
      KeyCode bridgetype_83 = this.method8();
      return number2 * 59 + (bridgetype_83 == null ? 43 : bridgetype_83.hashCode());
   }

   @Generated
   @Override
   public String toString() {
      return "KeyBind(alt=" + this.method5() + ", shift=" + this.method6() + ", control=" + this.method7() + ", value=" + this.method8() + ")";
   }

   @Generated
   public KeyBind(boolean flag, boolean flag2, boolean flag3, KeyCode bridgetype_84) {
      this.field2 = flag;
      this.field3 = flag2;
      this.field4 = flag3;
      this.field5 = bridgetype_84;
   }
}
