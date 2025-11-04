package org.example;

public class SpellCheckDecorator extends TextEditorDecorator{
    public SpellCheckDecorator(TextEditor editor)
    {
        super(editor);
    }

    @Override
    public String edit()
    {
        return super.edit() + " + Spell Checking";
    }

}
