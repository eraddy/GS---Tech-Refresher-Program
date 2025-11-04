package org.example;


public class Application {
    public static void main(String[] args) {
        // Base Editor
        TextEditor simpleEditor = new SimpleTextEditor();
        System.out.println("Base: " + simpleEditor.edit());

        // Add Spell Check
        TextEditor spellChecked = new SpellCheckDecorator(simpleEditor);
        System.out.println("With Spell Check: " + spellChecked.edit());

        // Add Auto Correct
        TextEditor autoCorrected = new AutoCorrectDecorator(spellChecked);
        System.out.println("With Spell Check + Auto Correct: " + autoCorrected.edit());

        // Add Syntax Highlighting
        TextEditor fullFeatureEditor = new SyntaxHighlightDecorator(autoCorrected);
        System.out.println("Full Featured Editor: " + fullFeatureEditor.edit());
    }
}
