package Creational_Design_Pattern.Abstract_Factory_Design_Pattern;

/*
Problem Statement:
Render Widgets on windows and macOS. Each Platform has its own Button, Checkbox, and scrollBar.
they must never be mixed. The app picks a platform once at startup, then creates widgets witout
ever naming a platform specific class again. Design the structure that guarantees consistency.
*/

import java.util.Scanner;

interface Button{
    public void click();
}

class WindowsButton implements Button{
    @Override
    public void click() {
        System.out.println("Clicked on Windows Button");
    }
}

class MacButton implements Button{
    @Override
    public void click() {
        System.out.println("Clicked on Mac Button");
    }
}

interface CheckBox{
    public void check();
}

class WindowsCheckBox implements CheckBox{
    @Override
    public void check() {
        System.out.println("Checked on Windows");
    }
}

class MacCheckBox implements CheckBox{
    @Override
    public void check() {
        System.out.println("Checked on Mac");
    }
}

interface ScrollBar{
    public void scroll();
}

class WindowsScrollBar implements ScrollBar{
    @Override
    public void scroll() {
        System.out.println("Scrolling on Windows");
    }
}

class MacScrollBar implements ScrollBar{
    @Override
    public void scroll() {
        System.out.println("Checked on Mac");
    }
}

interface SystemFactory{
    Button createButton();
    ScrollBar createScrollBar();
    CheckBox createCheckBox();
}

class WindowsFactory implements SystemFactory{
    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public ScrollBar createScrollBar() {
        return new WindowsScrollBar();
    }

    @Override
    public CheckBox createCheckBox() {
        return new WindowsCheckBox();
    }
}

class MacFactory implements SystemFactory{
    @Override
    public Button createButton() {
        return new MacButton();
    }

    @Override
    public ScrollBar createScrollBar() {
        return new MacScrollBar();
    }

    @Override
    public CheckBox createCheckBox() {
        return new MacCheckBox();
    }
}

class SystemManager{
    private SystemFactory systemFactory;

    SystemManager(SystemFactory systemFactory){
        this.systemFactory=systemFactory;
    }

    public void renderUI(){
        Button button=systemFactory.createButton();
        ScrollBar scrollBar = systemFactory.createScrollBar();
        CheckBox checkBox = systemFactory.createCheckBox();
        button.click();
        scrollBar.scroll();
        checkBox.check();
    }
}

public class Cross_platform_ui_toolkit {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String system=scn.nextLine();
        SystemFactory systemFactory;
        if(system.equals("windows")){
            systemFactory=new WindowsFactory();
        } else if(system.equals("macos")) {
            systemFactory=new MacFactory();
        } else {
            throw  new IllegalArgumentException();
        }

        SystemManager systemManager=new SystemManager(systemFactory);
        systemManager.renderUI();
    }
}
