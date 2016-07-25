package gwt.material.design.addins.client.combobox;

/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2016 GwtMaterialDesign
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


import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.logical.shared.*;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HasConstrainedValue;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.addins.client.MaterialAddins;
import gwt.material.design.addins.client.combobox.events.ComboBoxEvents;
import gwt.material.design.addins.client.combobox.js.JsComboBoxOptions;
import gwt.material.design.client.MaterialDesignBase;
import gwt.material.design.client.base.HasPlaceholder;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.ui.MaterialToast;
import gwt.material.design.client.ui.html.Label;
import gwt.material.design.client.ui.html.OptGroup;
import gwt.material.design.client.ui.html.Option;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static gwt.material.design.addins.client.combobox.js.JsComboBox.$;

//@formatter:off

/**
 * ComboBox component used on chat module
 *
 * <h3>XML Namespace Declaration</h3>
 * <pre>
 * {@code
 * xmlns:ma='urn:import:gwt.material.design.addins.client'
 * }
 * </pre>
 *
 * <h3>UiBinder Usage:</h3>
 * <pre>
 * {@code
 * <combobox:MaterialComboBox>
 *   <m:html.Option value="1" text="Sample 1"/>
 *   <m:html.Option value="2" text="Sample 2"/>
 *   <m:html.Option value="3" text="Sample 3"/>
 * </combobox:MaterialComboBox>
 * }
 * </pre>
 *
 * @author kevzlou7979
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#combobox">Material ComboBox</a>
 */
//@formatter:on
public class MaterialComboBox<T> extends MaterialWidget implements HasPlaceholder, HasConstrainedValue<T>, HasSelectionHandlers<T> {

    static {
        if(MaterialAddins.isDebug()) {
            MaterialDesignBase.injectDebugJs(MaterialComboBoxDebugClientBundle.INSTANCE.select2DebugJs());
            MaterialDesignBase.injectCss(MaterialComboBoxDebugClientBundle.INSTANCE.select2DebugCss());
        } else {
            MaterialDesignBase.injectJs(MaterialComboBoxClientBundle.INSTANCE.select2Js());
            MaterialDesignBase.injectCss(MaterialComboBoxClientBundle.INSTANCE.select2Css());
        }
    }

    private String placeholder;
    private boolean allowClear;
    private int limit;
    private boolean hideSearch;
    private List<T> values = new ArrayList<>();
    private int selectedIndex;
    private String uid = DOM.createUniqueId();

    private Label label = new Label();
    private MaterialWidget listbox = new MaterialWidget(Document.get().createSelectElement());

    public MaterialComboBox() {
        super(Document.get().createDivElement(), "input-field", "combobox");
    }

    @Override
    public void onLoad() {
        super.onLoad();
        initialize();
    }

    @Override
    protected void onUnload() {
        super.onUnload();
        $(listbox.getElement()).off(ComboBoxEvents.CHANGE);
        $(listbox.getElement()).off(ComboBoxEvents.SELECT);
    }

    @Override
    public void add(Widget child) {
        if(child instanceof OptGroup) {
            for(Widget w : ((OptGroup) child).getChildren()) {
                if(w instanceof Option) {
                    String value = ((Option) w).getValue();
                    values.add((T) value);
                }
            }
        } else if(child instanceof Option) {
            String value = ((Option) child).getValue();
            values.add((T) value);
            MaterialToast.fireToast("Puta");
        }
        listbox.add(child);
    }

    protected void initialize() {
        label.setInitialClasses("select2label");
        super.add(listbox);
        super.add(label);
        setId(uid);
        JsComboBoxOptions options = new JsComboBoxOptions();
        options.allowClear = allowClear;
        options.placeholder = placeholder;
        options.maximumSelectionLength = limit;
        if(isHideSearch()) {
            options.minimumResultsForSearch = "Infinity";
        }
        $(listbox.getElement()).select2(options);
        listbox.setGwtDisplay(Style.Display.BLOCK);


        $(listbox.getElement()).on(ComboBoxEvents.CHANGE, event -> {
            ValueChangeEvent.fire(MaterialComboBox.this, getValue());
            return true;
        });


        $(listbox.getElement()).on(ComboBoxEvents.SELECT, event -> {
            SelectionEvent.fire(MaterialComboBox.this, getValue());
            return true;
        });
    }

    public void setMultiple(boolean value) {
        if(value) {
            $(listbox.getElement()).attr("multiple", "multiple");
        }else {
            $(listbox.getElement()).removeAttr("multiple");
        }
    }

    public void setLabel(String text) {
        label.setText(text);
    }

    @Override
    public String getPlaceholder() {
        return placeholder;
    }

    @Override
    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    @Override
    public void setEnabled(boolean enabled) {
        listbox.setEnabled(enabled);
    }

    public boolean isAllowClear() {
        return allowClear;
    }

    public void setAllowClear(boolean allowClear) {
        this.allowClear = allowClear;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public boolean isHideSearch() {
        return hideSearch;
    }

    public void setHideSearch(boolean hideSearch) {
        this.hideSearch = hideSearch;
    }

    @Override
    public void setAcceptableValues(Collection<T> collection) {
        values.clear();
        for(T value : collection) {
            values.add(value);
        }
    }

    @Override
    public T getValue() {
        return values.get(getSelectedIndex());
    }

    public T getSelectedValue() {
        return this.getValue();
    }

    @Override
    public void setValue(T t) {
        setValue(t, true);
    }

    @Override
    public void setValue(T value, boolean fireEvents) {
        int index = getValueIndex(value);
        if(index > 0 && values.contains(value)) {
            T before = getValue();
            setSelectedIndex(index);
            if (fireEvents) {
                ValueChangeEvent.fireIfNotEqual(this, before, value);
            }
        }
    }

    public int getValueIndex(T value) {
        return values.indexOf(value);
    }

    public void addGroup(OptGroup group) {
        listbox.add(group);
    }

    public void addValue(String text, T value, OptGroup optGroup) {
        if(!values.contains(value)) {
            values.add(value);
            optGroup.add(buildOption(text, value));
        }
        MaterialToast.fireToast("dsadsa");
    }

    public void addValue(String text, T value) {
        if(!values.contains(value)) {
            values.add(value);
            listbox.add(buildOption(text, value));
        }
    }

    protected Option buildOption(String text, T value) {
        Option option = new Option();
        option.setText(text);
        option.setValue(value.toString());
        return option;
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
        T value = values.get(selectedIndex);
        if(value != null) {
            $(listbox.getElement()).val(value.toString()).trigger("change", selectedIndex);
        }else {
            GWT.log("Value Index is not found.", new IndexOutOfBoundsException());
        }
    }

    public int getSelectedIndex() {
        Object o = $("#" + uid).find("option:selected").prop("index");
        if(o != null) {
            return Integer.parseInt(o.toString());
        } else {
            GWT.log("Can't find the selected index.", new IndexOutOfBoundsException());
        }
        return -1;
    }

    public List<T> getValues() {
        return values;
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<T> valueChangeHandler) {
        return addHandler(valueChangeHandler, ValueChangeEvent.getType());
    }

    @Override
    public HandlerRegistration addSelectionHandler(SelectionHandler<T> selectionHandler) {
        return addHandler(selectionHandler, SelectionEvent.getType());
    }
}