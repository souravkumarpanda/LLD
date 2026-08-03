package composition;

// Component 1: CPU
class CPU{
    public void process(){
        System.out.println("CPU is processing data...");
    }
}
// Component 2: Monitor
class Monitor{
    public void display(){
        System.out.println("Monitor is displaying output...");
    }
}
// Component 3: Keyboard
class Keyboard{
    public void type(){
        System.out.println("Keyboard is typing input...");
    }
}
// Computer class which is composed of CPU, Monitor, and Keyboard
class Computer{
    private CPU cpu;
    private Monitor monitor;
    private Keyboard keyboard;

    public Computer(){
        this.cpu = new CPU();
        this.monitor = new Monitor();
        this.keyboard = new Keyboard();
    }
    public void start(){
        System.out.println("Computer is starting...");
        cpu.process();
        monitor.display();
        keyboard.type();
    }
}
// Main Class
public class ConcreteComposition {
    public static void main(String[] args) {
        Computer myComputer = new Computer();
        myComputer.start();
    }
}
