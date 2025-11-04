package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextEditorDecoratorTests {
    @Test
    void testPlainEditor() {
        TextEditor editor = new SimpleTextEditor();
        assertEquals("Plain text", editor.edit());
    }

    @Test
    void testSpellCheckDecorator() {
        TextEditor editor = new SpellCheckDecorator(new SimpleTextEditor());
        assertEquals("Plain text + Spell Checking", editor.edit());
    }

    @Test
    void testAutoCorrectDecorator() {
        TextEditor editor = new AutoCorrectDecorator(new SimpleTextEditor());
        assertEquals("Plain text + Auto Correction", editor.edit());
    }

    @Test
    void testSyntaxHighlightDecorator() {
        TextEditor editor = new SyntaxHighlightDecorator(new SimpleTextEditor());
        assertEquals("Plain text + Syntax Highlighting", editor.edit());
    }

    @Test
    void testMultipleDecorators() {
        TextEditor editor = new SyntaxHighlightDecorator(
                new AutoCorrectDecorator(
                        new SpellCheckDecorator(
                                new SimpleTextEditor()
                        )));
        assertEquals("Plain text + Spell Checking + Auto Correction + Syntax Highlighting", editor.edit());
    }
}
