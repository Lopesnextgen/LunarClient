package com.moonsworth.lunar.bridge;

import java.util.function.Consumer;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class RenderTypeBuilder {
   @Nullable
   protected EntityPlayerPacketFactory.MixinHelper7$Type3 field1;
   @Nullable
   protected EntityPlayerPacketFactory.MixinHelper7$Type7 field2;
   @Nullable
   protected EntityPlayerPacketFactory.MixinHelper7$Type field3;
   @NotNull
   protected final EntityPlayerPacketFactory.MixinHelper7$Data9 field4 = new MixinHelper7$Data9();
   @Nullable
   protected EntityPlayerPacketFactory.MixinHelper7$Type5 field5;
   @Nullable
   protected EntityPlayerPacketFactory.MixinHelper7$Data8 field6;
   @Nullable
   protected EntityPlayerPacketFactory.MixinHelper7$Data7 field7;
   @Nullable
   protected Boolean field8;

   @Contract("_->this")
   public RenderTypeBuilder method1(@Nullable EntityPlayerPacketFactory.MixinHelper7$Type3 var1) {
      this.field1 = var1;
      return this;
   }

   @Contract("_->this")
   public RenderTypeBuilder method2(@Nullable EntityPlayerPacketFactory.MixinHelper7$Type7 var1) {
      this.field2 = var1;
      return this;
   }

   @Contract("_->this")
   public RenderTypeBuilder method3(@Nullable EntityPlayerPacketFactory.MixinHelper7$Type var1) {
      this.field3 = var1;
      return this;
   }

   @Contract("_->this")
   public RenderTypeBuilder method4(@Nullable EntityPlayerPacketFactory.MixinHelper7$Type6 var1) {
      this.field4.method4(var1);
      return this;
   }

   @Contract("_->this")
   public RenderTypeBuilder method5(@Nullable OutputStateShardBridge var1) {
      this.field4.method3(var1);
      return this;
   }

   @Contract("_->this")
   public RenderTypeBuilder method6(@Nullable EntityPlayerPacketFactory.MixinHelper7$Type5 var1) {
      this.field5 = var1;
      return this;
   }

   @Contract("_->this")
   public RenderTypeBuilder method7(@Nullable EntityPlayerPacketFactory.MixinHelper7$Data8 var1) {
      this.field6 = var1;
      return this;
   }

   @Contract("_->this")
   public RenderTypeBuilder method8(@Nullable EntityPlayerPacketFactory.MixinHelper7$Data7 var1) {
      this.field7 = var1;
      return this;
   }

   @Contract("_->this")
   public RenderTypeBuilder method9(@Nullable EntityPlayerPacketFactory.MixinHelper7$Type4 var1) {
      return this;
   }

   @Contract("_->this")
   public RenderTypeBuilder method10(@Nullable EntityPlayerPacketFactory.MixinHelper7$Type2 var1) {
      return this;
   }

   @Contract("_->this")
   public RenderTypeBuilder method11(Consumer<RenderTypeBuilder> var1) {
      var1.accept(this);
      return this;
   }

   @Contract("_->this")
   public RenderTypeBuilder method12(boolean var1) {
      this.field8 = var1;
      return this;
   }

   @Contract("_->this")
   public RenderTypeBuilder method13(RenderTypeBuilder var1) {
      if (var1.field1 != null) {
         this.method1(var1.field1);
      }

      if (var1.field2 != null) {
         this.method2(var1.field2);
      }

      if (var1.field3 != null) {
         this.method3(var1.field3);
      }

      var1.field4.method5(this::method4, this::method5);
      if (var1.field5 != null) {
         this.method6(var1.field5);
      }

      if (var1.field6 != null) {
         this.method7(var1.field6);
      }

      if (var1.field7 != null) {
         this.method8(var1.field7);
      }

      if (var1.field8 != null) {
         this.method12(var1.field8);
      }

      return this;
   }

   @Contract("_,_,_,_,_,_->new")
   public RenderLayerBridge method14(Bridge_45 var1, String var2, int var3, boolean var4, boolean var5, boolean var6) {
      return this.method15(var1, var2, var3, var4, var5, var6 ? MixinHelper7$Type8.AFFECTS_OUTLINE : MixinHelper7$Type8.NONE);
   }

   @Contract("_,_,_,_,_,_->new")
   public abstract RenderLayerBridge method15(Bridge_45 var1, String var2, int var3, boolean var4, boolean var5, MixinHelper7$Type8 var6);
}
