package gwt.material.design.addins.client.docviewer;

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


import gwt.material.design.client.base.MaterialWidget;

import com.google.gwt.dom.client.Document;

//@formatter:off
/**
 * A document viewer for your word, excel, powerpoint, pdf and other <a href='http://wiki.mobileread.com/wiki/Google_Docs_Viewer'></a> supported
 * file types </a>. <br/>
 * Note that this viewer only work with public files.
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
 * <ma:docviewer.MaterialDocViewer url="http://infolab.stanford.edu/pub/papers/google.pdf" embedded="true"/>
 * }
 * </pre>
 *
 * @author kevzlou7979
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#docviewer">Doc Viewer</a>
 */
//@formatter:on
public class MaterialDocViewer extends MaterialWidget {

    private String url;
    private boolean embedded = true;

    public MaterialDocViewer() {
        super(Document.get().createIFrameElement());
    }

    @Override
    protected void onLoad() {
        super.onLoad();
        getElement().setAttribute("src", "http://docs.google.com/gview?url=" + url + "&embedded=" + embedded);
    }

    /**
     * Get the url of the Iframe component
     * @return
     */
    public String getUrl() {
        return url;
    }

    /**
     * Set the url of the public document
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Check whether the iframe is embedded or not
     * @return
     */
    public boolean isEmbedded() {
        return embedded;
    }

    /**
     * Set the emebedded value of the iframe
     * @param embedded
     */
    public void setEmbedded(boolean embedded) {
        this.embedded = embedded;
    }
}
