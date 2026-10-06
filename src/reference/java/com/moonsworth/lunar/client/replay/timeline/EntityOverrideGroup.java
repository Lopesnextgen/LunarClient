package com.moonsworth.lunar.client.replay.timeline;

import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.replay.timeline.KeyframeProperty;
import com.moonsworth.lunar.client.replay.timeline.EntityOverrideRegistry;
import com.moonsworth.lunar.client.replay.gui.EntityOptionOverrides;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.util.io.SerializedNameOnly;
import com.moonsworth.lunar.client.util.collection.ValueHolder;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;
import lombok.Generated;

@SerializedNameOnly
public abstract class EntityOverrideGroup extends DynamicCategory {
   private final EntityOptionOverrides field15;

   public EntityOverrideGroup(
      com.moonsworth.lunar.client.replay.timeline.UndoRedoManager nameplate21, List<String> list2, String text3, EntityOptionOverrides rewindhandlers_44
   ) {
      super(nameplate21, list2, text3, arg1x -> {
         ArrayList list2x = new ArrayList();
         EntityOverrideRegistry holograms23x = arg1x.method40().method39();

         for (Entry entry5 : holograms23x.method3().entrySet()) {
            if (((Set)entry5.getValue()).contains(text3)) {
               list2x.add(new SettingOption((String)entry5.getKey(), Client.method109().method67().method2("settings", (String)entry5.getKey(), new Object[0])));
            }
         }

         return list2x;
      });
      this.field15 = rewindhandlers_44;
      this.method6(PropertyGroup.NodeType.KEYFRAMES);
   }

   @Override
   public void method6(ValueHolder<ReplayContext> threadmoduledump61, int number2) {
      super.method6(threadmoduledump61, number2);
      if (this.isEnabled()) {
         for (KeyframeProperty fishing2loader4 : this.method12().values()) {
            this.field15.method1(this.getEntityId(), fishing2loader4.type(), fishing2loader4.method26());
         }
      }
   }

   @Override
   protected void method5(String text1) {
      KeyframeProperty fishing2loader2 = (KeyframeProperty)this.method12().get(text1);
      if (fishing2loader2 != null) {
         this.field15.method2(this.getEntityId(), fishing2loader2.type(), fishing2loader2.method26());
      }
   }

   @Override
   public void method7() {
      super.method7();

      for (KeyframeProperty fishing2loader2 : this.method12().values()) {
         this.field15.method2(this.getEntityId(), fishing2loader2.type(), fishing2loader2.method26());
      }
   }

   protected abstract String getEntityId();

   @Generated
   public EntityOptionOverrides method4() {
      return this.field15;
   }
}
