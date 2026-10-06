package com.moonsworth.lunar.client.config.option;

import com.moonsworth.lunar.client.config.option.ClientOption;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;
import org.jspecify.annotations.NonNull;

public class OptionChildren implements AlertExtension {
   private final List<ClientOption<?>> field1;

   public OptionChildren() {
      this.field1 = new ArrayList<>();
   }

   public OptionChildren(List<ClientOption<?>> list1) {
      this.field1 = list1;
   }

   public void method1(ClientOption<?> lightingextension1, List<ClientOption<?>> list2) {
      this.field1.clear();
      this.field1.addAll(list2);
   }

   public boolean method2(@NonNull ClientOption<?> lightingextension1) {
      return this.field1.add(lightingextension1);
   }

   public void method3(@NonNull ClientOption<?> lightingextension1) {
      this.field1.remove(lightingextension1);
   }

   @Generated
   public List<ClientOption<?>> getChildren() {
      return this.field1;
   }
}
