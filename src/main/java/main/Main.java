package main;

import java.util.logging.Level;
import java.util.logging.Logger;

import gui.RpmGUI;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class Main {

    private static AgentContainer container;
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        // Lancer l'interface Swing
        javax.swing.SwingUtilities.invokeLater(() -> {
            RpmGUI gui = new RpmGUI();
            gui.setVisible(true);
        });
    }

    public static void startJadeAgents() {
        try {
            Runtime rt = Runtime.instance();
            Profile p = new ProfileImpl();
            container = rt.createMainContainer(p);

            AgentController monitoring = container.createNewAgent("MonitoringAgent", "agents.MonitoringAgent", null);
            AgentController notifier = container.createNewAgent("NotificationAgent", "agents.NotificationAgent", null);
            AgentController simulator = container.createNewAgent("SimulatorAgent", "agents.DataSimulatorAgent", null);

            monitoring.start();
            notifier.start();
            simulator.start();

        } catch (StaleProxyException e) {
            LOGGER.log(Level.SEVERE, "Erreur lors du démarrage des agents", e);
        }
    }
}
