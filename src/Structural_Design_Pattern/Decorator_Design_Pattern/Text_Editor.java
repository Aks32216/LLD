package Structural_Design_Pattern.Decorator_Design_Pattern;

interface TextView{
    public void render();
}

class PlainTextView implements TextView{
    private String text;

    PlainTextView(String text){
        this.text=text;
    }

    @Override
    public void render() {
        System.out.println("Rendering plain text: "+text);
    }
}

abstract class TextDecorator implements TextView{
    protected final TextView textView;

    TextDecorator(TextView textView){
        this.textView=textView;
    }
}

class BoldTextDecorator extends TextDecorator{

    BoldTextDecorator(TextView textView) {
        super(textView);
    }

    @Override
    public void render() {
        System.out.println("<B>");
        textView.render();
        System.out.println("</B>");
    }
}

class ItalicTextDecorator extends TextDecorator{

    ItalicTextDecorator(TextView text){
        super(text);
    }

    @Override
    public void render() {
        System.out.println("<I>");
        textView.render();
        System.out.println("</I>");
    }
}

class UnderlineTextDecorator extends TextDecorator{

    UnderlineTextDecorator(TextView text){
        super(text);
    }

    @Override
    public void render() {
        System.out.println("<U>");
        textView.render();
        System.out.println("</U>");
    }
}

public class Text_Editor {
    public static void main(String[] args) {
        TextView textView1=new BoldTextDecorator(new PlainTextView("amish"));
        TextView textView2=new UnderlineTextDecorator(new BoldTextDecorator(new PlainTextView("anshuman")));
        textView1.render();
        textView2.render();
    }
}
