package com.pingpal.components;

import java.util.UUID;

import com.webforj.annotation.JavaScript;
import com.webforj.annotation.StyleSheet;
import com.webforj.component.element.Element;
import com.webforj.component.html.elements.Div;

@StyleSheet("https://cdn.jsdelivr.net/npm/jsoneditor@10.2.0/dist/jsoneditor.min.css")
@JavaScript("https://cdn.jsdelivr.net/npm/jsoneditor@10.2.0/dist/jsoneditor.min.js")
public class JsonEditor extends Div {

    private final String uuid = UUID.randomUUID().toString();

    public JsonEditor() {
        setWidth("100%");
        setHeight("100%");
        setAttribute("id", "editor_" + uuid);

        Element el = getElement();
        el.whenAttached().thenAccept(element -> {
            String script = """
                function waitForScript(src) {
                    return new Promise((res, rej) => {
                        let s = [...document.scripts].find(x => x.src === src);
                        if (s) return s.readyState === 'complete' || s.dataset.loaded ? res() : s.addEventListener('load', res);
                        s = document.head.appendChild(Object.assign(document.createElement('script'), {src, async: true}));
                        s.onload = () => { s.dataset.loaded = 1; res(); };
                        s.onerror = rej;
                    });
                }

                waitForScript('https://cdn.jsdelivr.net/npm/jsoneditor@10.2.0/dist/jsoneditor.min.js').then(() => {
                    const options = {"mode": "code"}
                    const editor = new JSONEditor(component, options);
                    document.getElementById('editor_%s')._jsoneditor = editor;
                });
            """;
            el.executeJs(script.formatted(uuid));
        });
    }

    public String getData() {
        String script = "const editor = document.getElementById('editor_" + uuid + "')._jsoneditor;";
        script += "editor.get();";
        String json = (String) getElement().executeJs(script);
        return json;
    }
    
}
