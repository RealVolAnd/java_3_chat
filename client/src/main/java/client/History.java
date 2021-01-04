package client;

import com.sun.jndi.toolkit.url.Uri;
import javafx.scene.control.TextArea;
import sun.rmi.runtime.Log;

import java.io.*;
import java.util.ArrayList;

public class History {
    private File directory;
    private File historyFile;
    private TextArea textArea;
    private ArrayList<String> history;

    public History(File directory) {
        this.directory = directory;
        history = new ArrayList<>();
    }

    public void historyInit(String login, TextArea textArea) {
        this.textArea = textArea;
        historyFile = new File(directory, "history_" + login + ".txt");
        if (!historyFile.exists()) {
            try {
                historyFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        loadHistory();
        refreshTextArea();
    }

    private void refreshTextArea() {
        for (String s : history) {
            textArea.appendText(s);
        }
    }

    private void loadHistory() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(historyFile));
            String line;
            while ((line = br.readLine()) != null) {
                history.add(line + "\n");
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public synchronized void saveStringToHistory(String string) {

        history.add(string + "\n");
        if (history.size() > 100) {
            history.remove(0);
        }

        try {
            FileWriter writer = new FileWriter(historyFile);
            for (String s : history) {
                writer.write(s);
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
