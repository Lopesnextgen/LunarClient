package com.moonsworth.lunar.client.ui.external;

import com.moonsworth.lunar.client.ui.external.ExternalLink;

public interface TurboExternalLink extends ExternalLink {
   void lunar$onTurboEntityStateChange(boolean flag1);

   void lunar$onTurboBlockEntityStateChange(boolean flag1);
}
