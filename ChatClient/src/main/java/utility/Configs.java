package utility;

import gui.ChatUI;
import gui.HomeScreen;
import model.ClientModel;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Configs {

    public static volatile HomeScreen homeScreen;
    public static volatile JFrame homeFrame;
    public static volatile ClientModel model;
    public static volatile PrintWriter out;
    public static volatile BufferedReader in;
    public static volatile GuiUpdateUtility guiUpdateUtility;
    public static volatile String name;
    public static volatile Socket socket;
    public static volatile Map<String, ChatUI> mapOfFrames = new HashMap<>();
}
