package com.moonsworth.lunar.client.config.option;

import com.moonsworth.lunar.client.util.Annotation;
import com.moonsworth.lunar.client.util.Annotation3;
import org.jetbrains.annotations.Contract;

public abstract class CharacterOptionBuilder<B extends CharacterOptionBuilder<B, O>, O extends ClientOption<Character>> extends OptionBuilderBase<B, O, Character> {
   protected char field14 = this.method10();

   protected CharacterOptionBuilder(@Annotation3 @Annotation(method1 = Annotation.Type.SETTING) String var1) {
      super(var1);
   }

   protected char method10() {
      return '\u0000';
   }

   @Contract("_->this")
   public B method2(char var1) {
      this.field14 = var1;
      return (B)this;
   }
}
