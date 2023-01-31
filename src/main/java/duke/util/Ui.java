package duke.util;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    private static final String DASHED_LINE = "____________________________________________________________";
    private static final String LS = System.lineSeparator();
    private static final String LINE_PREFIX = "    ";
    private static final String MESSAGE_WELCOME = "You've found the Tohtoro!";
    private static final String MESSAGE_GOODBYE = "Meowww. Hope to see you soon!\n" +
            "THE TOHTORO PROGRAM HAS STOPPED";
    private static final String LOGO = ".                   *3k~               <Ry.              .\n" +
            ".                  x#86D.             T#$6h              .\n" +
            ".                  Q#666y            *#Q66D              .\n" +
            ".                 ,#BOO6D;          -Q#D66O              .\n" +
            ".                 !#BO66D^          c##06Rv              .\n" +
            ".                  0#866Qx:~*)((r^=,k#QR$)               .\n" +
            ".                  x##Q9009666OO66690$0g6'               .\n" +
            ".                 rD#Q06OO6OOOOOOOOOOO69R|`              .\n" +
            ".               =M#B$KeM9OOOOO666O66OOseOR3'             .\n" +
            ".      _:=~:-` ~###8*vByeO6E$####$66DrBG=E6s`:^^~!`      .\n" +
            ".   `...``',~^d##@#RwxyVdOOO6gQQ$6O6EITxzRgQQ)_-----.    .\n" +
            ".   `_:!=~~~~m@@##QO6666OOOOOO666OO6O6666ORB#QyY|<!:,`   .\n" +
            ".     -:!==~rQ####866OOOOOOOOOOOOOOO666OOOOR0$3~=;~!,    .\n" +
            ".    ._.`  ~8#####BO6OOOOO6OO6EDOO6OOOOOOOOOO66Z<        .\n" +
            ".        =5#######g6609bMjyVVyjhwykMWdE66O66OOOORw`      .\n" +
            ".      _3########gOsc}^~~~~~~~~~~~~~~~vxy5966OOOOOh:     .\n" +
            ".     _Q########Wx<~~~~~~~^TsGHk\\~~~~~*]i~*yGROOOOO9^    .\n" +
            ".    _Q######@Dx<}adbX|~~<OZXTTkH^~~iGOaWbi~~uM980OOO,   .\n" +
            ".    d####@##diiOMcxxY)~~~~~~~~~~^*~rr~~~vK^~~~hQQOOOZ.  .\n" +
            ".   c####@#Bewrc)~~~~~^}zhc*~~<VH0gZc<~~~^x]|~~~}gDOO6c  .\n" +
            ".  _#####@#MoVjMbKv~~v3wYTky~~*Ti(*v}^~~vO5PRG*~~xg6OO0. .\n" +
            ".  )#####@doIdkx*)^~~~~~~~~~~~~~~~~~~~~~~~~~<})~~~M6OO6* .\n" +
            ".  W#####QooT~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~U6OO6\\ .\n" +
            ".  D#####Uook*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~kEOOOv .\n" +
            ".  a####Boojz*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~VRO6D\" .\n" +
            ".  :####Boojo)~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~k6O6h  .\n" +
            ".   a###goojo}~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~<M6OM-  .\n" +
            ".   `y##8oojojx^~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|uD6b_   .\n" +
            ".     hg9joo3RgQDMI}r~~~~~~~~~~~~~~~~~~\\oZMdddzyjo8b.    .\n" +
            ".     :(VooH##QE66O6gc*~~~~~~~~~~~~~~*R#Q966OOO8U3Xv     .\n" +
            ".       ~ajB#B96O9669DEx~~~~~~~~~~~~xQ#BOOO6OOORO)`      .\n" +
            ".        hmB#B6E6g$Egg6$Txr*<^;~~~^\\B##BDgO6O6O$K`       .\n" +
            ".        `\\5##g$8gg8$8$$doojkkwVcyzW##QBgg$8ROOk`        .\n" +
            "xvTyIa5MbZbQ#@#BQ06gQQ8Q9oehP5W3KHHQ#Q$DR0DQD6QMVY)~_    .\n" +
            "##############@@QgB##8R@#Q#########@#QQBBDO8Q########B$5jy\n" +
            "################@@##B6Q@############@#$9#BB###############\n" +
            "Q$D9OOO6E0$$0EOM3szwkV########000$g8QwXWDQB###############\n" +
            ".                     M######86666OOj      -:<rYkG0B######\n" +
            ".                     Y######Q66OOO6r               -!^)vY\n" +
            ".                     'E######86O6RT                     .\n" +
            ".                      `d#######BQ}                      .\n" +
            ".                        !lG0$0GV,                       .";

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    private boolean shouldIgnore(String rawInputLine) {
        return rawInputLine.trim().isEmpty();
    }

    public String[] getUserCommand() {
        showToUser("Meowww... enter command:");
        String fullInputLine = sc.nextLine();
        while (shouldIgnore(fullInputLine)) {
            fullInputLine = sc.nextLine();
        }
        showToUser("[Meowww you've entered " + fullInputLine + "]");
        return fullInputLine.split(" ", 2);
    }

    public void showWelcomeMessage() {
        showToUser(DASHED_LINE,
                LOGO,
                MESSAGE_WELCOME,
                DASHED_LINE);
    }

    public void showGoodbyeMessage() {
        showToUser(DASHED_LINE,
                MESSAGE_GOODBYE,
                DASHED_LINE);
    }

    public void showToUser(String... message) {
        for (String m : message) {
            System.out.println(LINE_PREFIX + m.replace("\n", LS + LINE_PREFIX));
        }
    }

    public String formatMessage(String... message) {
        String formattedMessage = "";
        for (String m : message) {
            formattedMessage += LINE_PREFIX + m.replace("\n", LS + LINE_PREFIX);
        }
        return formattedMessage;
    }

    public void showError(String errorMessage, String errorType) {
        showToUser(DASHED_LINE,
                "ERROR! ERROR!",
                "The error type is: " + errorType,
                errorMessage,
                "Please try again! Tohtoro out",
                DASHED_LINE);
    }

    public void showToUserList(TaskList tl) {
        if (tl.isEmpty()) {
            showToUser("There does not seem to be any tasks meow, would you care to add some?");
        } else {
            String toShow = "Here are the tasks in your list:\n";
            for (int i = 0; i < tl.size(); i++) {
                Task t = tl.get(i);
                toShow += String.format("%s. %s", i + 1, t);
                if (i + 1 < tl.size()) {
                    toShow += "\n";
                }
            }
            showToUser(toShow);
        }
    }

    public String stringOfTaskNumbers(TaskList tl) {
        String toShow = String.format("Now you have %d tasks in your list", tl.size());
        return toShow;
    }
}