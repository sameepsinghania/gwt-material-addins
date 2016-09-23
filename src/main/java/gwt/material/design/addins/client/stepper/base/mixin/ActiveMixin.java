package gwt.material.design.addins.client.stepper.base.mixin;

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

import gwt.material.design.client.base.HasActive;

import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author kevzlou7979
 */
public class ActiveMixin<T extends UIObject & HasActive> extends AbstractMixin<T> implements HasActive {

    public ActiveMixin(final T widget) {
        super(widget);
    }
    private boolean active;

    @Override
    public void setActive(boolean active) {
        this.active = active;
        Widget obj = (Widget)uiObject;
        if (active) {
            obj.removeStyleName("inactive");
            obj.addStyleName("active");
        } else {
            obj.removeStyleName("active");
            obj.addStyleName("inactive");
        }
    }

    @Override
    public boolean isActive() {
        return active;
    }
}
