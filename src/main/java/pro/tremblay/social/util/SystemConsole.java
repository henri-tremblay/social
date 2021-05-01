package pro.tremblay.social.util;

import java.util.Scanner;

public final class SystemConsole implements Console {

    private final Scanner scanner;

    public SystemConsole() {
        scanner = new Scanner(System.in);
    }

    @Override
    public String readline() {
        return scanner.nextLine();
    }

    @Override
    public void write(String output) {
        System.out.println(output);
    }
}
