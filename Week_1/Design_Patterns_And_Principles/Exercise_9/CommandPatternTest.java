// Command Interface
interface Command {
    void execute();
}

// Concrete Command - Light On
class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }
}

// Concrete Command - Light Off
class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }
}// Invoker
class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        if (command != null) {
            command.execute();
        } else {
            System.out.println("No command set.");
        }
    }
}
// Receiver
class Light {
    private String location;

    public Light(String location) {
        this.location = location;
    }

    public void turnOn() {
        System.out.println(location + " light is turned on.");
    }

    public void turnOff() {
        System.out.println(location + " light is turned off.");
    }
}
public class CommandPatternTest {
    public static void main(String[] args) {
        // Create the receiver
        Light livingRoomLight = new Light("Living Room");
        Light kitchenLight = new Light("Kitchen");

        // Create commands
        Command livingRoomLightOn = new LightOnCommand(livingRoomLight);
        Command livingRoomLightOff = new LightOffCommand(livingRoomLight);
        Command kitchenLightOn = new LightOnCommand(kitchenLight);
        Command kitchenLightOff = new LightOffCommand(kitchenLight);

        // Create the invoker
        RemoteControl remote = new RemoteControl();

        // Use the remote to control lights
        System.out.println("Controlling Living Room Light:");
        remote.setCommand(livingRoomLightOn);
        remote.pressButton();
        remote.setCommand(livingRoomLightOff);
        remote.pressButton();

        System.out.println("\nControlling Kitchen Light:");
        remote.setCommand(kitchenLightOn);
        remote.pressButton();
        remote.setCommand(kitchenLightOff);
        remote.pressButton();

        // Try pressing button without setting a command
        System.out.println("\nTrying to press button without setting a command:");
        remote.setCommand(null);
        remote.pressButton();
    }
}
