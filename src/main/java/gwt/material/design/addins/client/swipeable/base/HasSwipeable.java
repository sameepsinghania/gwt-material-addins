package gwt.material.design.addins.client.swipeable.base;

/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 GwtMaterialDesign
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import gwt.material.design.addins.client.swipeable.events.SwipeLeftEvent;
import gwt.material.design.addins.client.swipeable.events.SwipeRightEvent;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.ui.Widget;

public interface HasSwipeable<T> extends HasHandlers {

    /**
     * Initialize the swipeable component
     */
    void initSwipeable(Element element, Widget target);

    /**
     * Add swipe left handler
     * @param handler
     */
    HandlerRegistration addSwipeLeft(SwipeLeftEvent.SwipeLeftHandler<T> handler);

    /**
     * Add swipe right handler
     * @param handler
     */
    HandlerRegistration addSwipeRight(SwipeRightEvent.SwipeRightHandler<T> handler);

}
