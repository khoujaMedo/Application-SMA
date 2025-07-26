package gui;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.*;

import main.Main;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class RpmGUI extends JFrame {
private static RpmGUI instance;

private final JButton startButton;
private final JButton stopButton;
private final JLabel monitoringStatus;
private final JLabel notificationStatus;
private final JLabel simulatorStatus;
private final JLabel pulseLabel;
private final JLabel stateLabel;
private final JTextArea logArea;

public RpmGUI() {
    instance = this;

    setTitle("Remote Patient Monitoring - Interface");
    setSize(600, 500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setLayout(new BorderLayout());

    // Boutons
    startButton = new JButton("▶ Démarrer tous les agents");
    stopButton = new JButton("◼ Arrêter tous les agents");

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(startButton);
    buttonPanel.add(stopButton);
    add(buttonPanel, BorderLayout.NORTH);

    // Statuts
    monitoringStatus = new JLabel("MonitoringAgent : ⏺ Inactif");
    notificationStatus = new JLabel("NotificationAgent : ⏺ Inactif");
    simulatorStatus = new JLabel("SimulatorAgent : ⏺ Inactif");

    JPanel statusPanel = new JPanel(new GridLayout(3, 1));
    statusPanel.setBorder(BorderFactory.createTitledBorder("Agents actifs"));
    statusPanel.add(monitoringStatus);
    statusPanel.add(notificationStatus);
    statusPanel.add(simulatorStatus);

    // Données patient
    pulseLabel = new JLabel("Pouls : -- bpm");
    stateLabel = new JLabel("État : --");

    JPanel patientPanel = new JPanel(new GridLayout(2, 1));
    patientPanel.setBorder(BorderFactory.createTitledBorder("Données patient"));
    patientPanel.add(pulseLabel);
    patientPanel.add(stateLabel);

    JPanel centerPanel = new JPanel(new GridLayout(2, 1));
    centerPanel.add(statusPanel);
    centerPanel.add(patientPanel);
    add(centerPanel, BorderLayout.CENTER);

    // Zone de log
    logArea = new JTextArea();
    logArea.setEditable(false);
    JScrollPane scrollPane = new JScrollPane(logArea);
    scrollPane.setBorder(BorderFactory.createTitledBorder("Logs"));
    add(scrollPane, BorderLayout.SOUTH);

    // Actions
    startButton.addActionListener(e -> {
        Main.startJadeAgents();
        updateStatus("MonitoringAgent", true);
        updateStatus("NotificationAgent", true);
        updateStatus("SimulatorAgent", true);
        appendLog("✔️ Agents démarrés.");
    });

    stopButton.addActionListener(e -> {
        appendLog("🛑 Arrêt simulé des agents.");
    });
}

public static RpmGUI getInstance() {
    return instance;
}

public void updatePulse(String bpm, String etat) {
    pulseLabel.setText("Pouls : " + bpm + " bpm");
    stateLabel.setText("État : " + etat);
}

public void updateStatus(String agentName, boolean actif) {
    String status = agentName + " : " + (actif ? "✔ Actif" : "⏺ Inactif");
    switch (agentName) {
        case "MonitoringAgent" -> monitoringStatus.setText(status);
        case "NotificationAgent" -> notificationStatus.setText(status);
        case "SimulatorAgent" -> simulatorStatus.setText(status);
    }
}

public void appendLog(String message) {
    String timestamp = new SimpleDateFormat("[EEE MMM dd HH:mm:ss z yyyy]").format(new Date());
    logArea.append(timestamp + " " + message + "\n");
}

}
