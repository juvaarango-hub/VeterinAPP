package app;


import app.configuration.Config;
import app.ui.CliVeterinAppInterface;

public class Aplication {

    public static void main(String[] args) {

        CliVeterinAppInterface application =
                Config.getCliVeterinAppInterface();

        application.applicationInit();
    }
}



