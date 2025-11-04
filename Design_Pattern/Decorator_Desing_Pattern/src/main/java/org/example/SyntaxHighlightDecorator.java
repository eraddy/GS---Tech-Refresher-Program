package org.example;

public class SyntaxHighlightDecorator extends TextEditorDecorator {
    public SyntaxHighlightDecorator(TextEditor editor)
    {
        super(editor);
    }

    @Override
    public String edit()
    {
        return super.edit() + " + Syntax Highlighting";
    }
}
