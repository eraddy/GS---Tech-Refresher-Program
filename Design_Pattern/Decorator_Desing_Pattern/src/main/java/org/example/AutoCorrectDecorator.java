package org.example;

public class AutoCorrectDecorator extends TextEditorDecorator {

    public AutoCorrectDecorator(TextEditor editor)
    {
        super(editor);
    }

    @Override
    public String edit()
    {
        return super.edit() + " + Auto Correction";
    }
}
