package Behavioural_Design_Pattern.Command_Design_Pattern;

import java.util.Stack;

class TextEditor {
    private StringBuilder content = new StringBuilder();

    public void append(String text) {
        content.append(text);
    }

    public String deleteLast(int count) {
        int start = Math.max(0, content.length() - count);
        String deleted = content.substring(start);
        content.delete(start, content.length());
        return deleted;
    }

    public String getContent() {
        return content.toString();
    }
}

interface EditorCommand {
    void execute();
    void undo();
}

class TypeCommand implements EditorCommand {
    private final TextEditor editor;
    private final String text;

    public TypeCommand(TextEditor editor, String text) {
        this.editor = editor;
        this.text = text;
    }

    @Override
    public void execute() {
        editor.append(text);
        System.out.println("Typed: \"" + text + "\"");
    }

    @Override
    public void undo() {
        editor.deleteLast(text.length());
        System.out.println("Undo type: \"" + text + "\"");
    }
}

class DeleteCommand implements EditorCommand {
    private final TextEditor editor;
    private final int count;
    private String deletedText;

    public DeleteCommand(TextEditor editor, int count) {
        this.editor = editor;
        this.count = count;
    }

    @Override
    public void execute() {
        deletedText = editor.deleteLast(count);
        System.out.println("Deleted: \"" + deletedText + "\"");
    }

    @Override
    public void undo() {
        editor.append(deletedText);
        System.out.println("Undo delete: restored \"" + deletedText + "\"");
    }
}

class EditorInvoker {
    private final Stack<EditorCommand> undoStack = new Stack<>();
    private final Stack<EditorCommand> redoStack = new Stack<>();

    public void execute(EditorCommand command) {
        command.execute();
        undoStack.push(command);
        redoStack.clear();
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            EditorCommand command = undoStack.pop();
            command.undo();
            redoStack.push(command);
        } else {
            System.out.println("Nothing to undo.");
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            EditorCommand command = redoStack.pop();
            command.execute();
            undoStack.push(command);
        } else {
            System.out.println("Nothing to redo.");
        }
    }
}

public class Text_Editor {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        EditorInvoker invoker = new EditorInvoker();

        invoker.execute(new TypeCommand(editor, "Hello"));
        invoker.execute(new TypeCommand(editor, " World"));
        invoker.execute(new TypeCommand(editor, "!"));
        System.out.println("Content: \"" + editor.getContent() + "\"");

        System.out.println("\n--- Undo ---");
        invoker.undo();
        System.out.println("Content: \"" + editor.getContent() + "\"");

        invoker.undo();
        System.out.println("Content: \"" + editor.getContent() + "\"");

        System.out.println("\n--- Redo ---");
        invoker.redo();
        System.out.println("Content: \"" + editor.getContent() + "\"");

        System.out.println("\n--- New operation clears redo ---");
        invoker.execute(new DeleteCommand(editor, 3));
        System.out.println("Content: \"" + editor.getContent() + "\"");

        invoker.redo();

        System.out.println("\n--- Undo delete ---");
        invoker.undo();
        System.out.println("Content: \"" + editor.getContent() + "\"");
    }
}
