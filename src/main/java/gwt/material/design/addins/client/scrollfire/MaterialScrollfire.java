package gwt.material.design.addins.client.scrollfire;

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

import gwt.material.design.addins.client.MaterialAddins;
import gwt.material.design.addins.client.scrollfire.js.JsScrollfire;
import gwt.material.design.client.MaterialDesignBase;
import gwt.material.design.jquery.client.api.Functions;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;

//@formatter:off

/**
 * Material Scrollfire - executes callback functions depending on how far into the page you've scrolled.
 *
 * <h3>Java Usage:</h3>
 *
 * <pre>
 * {@code
 * MaterialScrollfire.apply(Element e, Runnable runnableCallback);
 * }
 * </pre>
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#scrollfire">Material Scrollfire</a>
 * @author kevzlou7979
 */
//@formatter:on
public class MaterialScrollfire {

    static {
        if(MaterialAddins.isDebug()) {
            MaterialDesignBase.injectDebugJs(MaterialScrollfireDebugClientBundle.INSTANCE.scrollfireDebugJs());
        } else {
            MaterialDesignBase.injectJs(MaterialScrollfireClientBundle.INSTANCE.scrollfireJs());
        }
    }

    public static void apply(Element element, Runnable callback) {
        String uid = DOM.createUniqueId();
        element.setId(uid);
        apply("#" + uid, callback);
    }

    public static void apply(String selector, Runnable callback) {
        int offset = 100;
        Functions.Func function = () -> {
            callback.run();
        };
        JsScrollfire.apply(selector, offset, function);
    }
}
