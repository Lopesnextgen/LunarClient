package com.moonsworth.lunar.network;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
public class GameBatchEventTypeAdapterFactory implements TypeAdapterFactory {
   public GameBatchEventTypeAdapterFactory() {
   }

   public <T> TypeAdapter<T> create(Gson gson1, TypeToken<T> typetoken2) {
      if (!GameBatchEvent.class.isAssignableFrom(typetoken2.getRawType())) {
         return null;
      }

      TypeAdapter typeadapter3 = gson1.getAdapter(JsonElement.class);
      TypeAdapter typeadapter4 = gson1.getDelegateAdapter(this, TypeToken.get(NetworkIterator.class));
      TypeAdapter typeadapter5 = gson1.getDelegateAdapter(this, TypeToken.get(GamePromotionInteractionEvent.class));
      TypeAdapter typeadapter6 = gson1.getDelegateAdapter(this, TypeToken.get(GameRewindEditorSessionEvent.class));
      TypeAdapter typeadapter7 = gson1.getDelegateAdapter(this, TypeToken.get(GameRewindProjectExportEvent.class));
      TypeAdapter typeadapter8 = gson1.getDelegateAdapter(this, TypeToken.get(GameRewindRecordingEvent.class));
      TypeAdapter typeadapter9 = gson1.getDelegateAdapter(this, TypeToken.get(GameRewindLayerAddEvent.class));
      TypeAdapter typeadapter10 = gson1.getDelegateAdapter(this, TypeToken.get(GameFailedParseEvent.class));
      return new Data$1(this, typeadapter3, typeadapter4, typeadapter5, typeadapter6, typeadapter7, typeadapter8, typeadapter9, typeadapter10).nullSafe();
   }
}
