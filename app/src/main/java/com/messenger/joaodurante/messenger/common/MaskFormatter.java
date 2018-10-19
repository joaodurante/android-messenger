package com.messenger.joaodurante.messenger.common;

import android.content.Context;
import android.widget.EditText;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

public class MaskFormatter {
    public MaskFormatter(String template, EditText editText){
        SimpleMaskFormatter simpleMaskPhone = new SimpleMaskFormatter(template);
        MaskTextWatcher maskTextPhone = new MaskTextWatcher(editText, simpleMaskPhone);
        editText.addTextChangedListener(maskTextPhone);
    }

    public String replaceMaskFormatter(String text){
        text = text
                .replace("-", "")
                .replace(" ", "")
                .replace("(", "")
                .replace(")", "");
        return text;
    }
}
