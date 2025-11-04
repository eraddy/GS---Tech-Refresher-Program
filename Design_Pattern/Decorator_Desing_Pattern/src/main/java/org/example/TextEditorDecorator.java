package org.example;

public abstract class TextEditorDecorator implements TextEditor{

    protected TextEditor editor;

    public TextEditorDecorator(TextEditor editor)
    {
        this.editor = editor;
    }

    @Override
    public String edit() {
        return editor.edit();
    }
}
