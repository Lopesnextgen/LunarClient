package com.moonsworth.lunar.client.replay.timeline;

import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.client.replay.timeline.RewindSettingKeys;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.Range;

public class EntityOverridesCategory extends DynamicCategory {
   public EntityOverridesCategory(com.moonsworth.lunar.client.replay.timeline.UndoRedoManager nameplate21, List<String> list2, String text3) {
      super(nameplate21, list2, text3, arg0 -> {
         ArrayList list1x = new ArrayList();
         if (Ref.method8() != null) {
            for (Bridge6_10 bridge6_103x : Ref.method8().bridge$getPlayerEntities()) {
               String text4 = TextBridge.stripColor(bridge6_103x.bridge$getName()).isBlank() ? bridge6_103x.bridge$getUniqueID().toString() : bridge6_103x.bridge$getName();
               list1x.add(new SettingOption(RewindSettingKeys.method2("player", bridge6_103x.bridge$getUniqueID().toString()), text4, bridge6_103x.bridge$getUniqueID()));
            }
         }

         return list1x;
      });
      this.method6(PropertyGroup.NodeType.CHILD_PROPERTIES);
   }

   @Override
   public PropertyGroup method3(
      com.moonsworth.lunar.client.replay.timeline.UndoRedoManager nameplate21, Range<Integer> range2, Range<Integer> range3
   ) {
      EntityOverridesCategory nameplate2_24 = new EntityOverridesCategory(nameplate21, this.method10(), this.type());
      return this.method6(nameplate2_24, nameplate21, range2, range3);
   }
}
