package agents;

import gui.RpmGUI;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class MonitoringAgent extends Agent {
@Override
protected void setup() {
    System.out.println(getLocalName() + " démarré");

    addBehaviour(new CyclicBehaviour(this) {
        @Override
        public void action() {
            ACLMessage msg = receive();
            if (msg != null) {
                String content = msg.getContent();
                System.out.println("Reçu données: " + content);

                String bpm = content.replaceAll("[^0-9]", "");
                String etat = content.contains("CRITIQUE") ? "CRITIQUE" : "NORMAL";

                // Mise à jour GUI
                RpmGUI.getInstance().updatePulse(bpm, etat);
                RpmGUI.getInstance().appendLog("📡 Données reçues: " + content);

                if (content.contains("CRITIQUE")) {
                    ACLMessage alert = new ACLMessage(ACLMessage.INFORM);
                    alert.addReceiver(getAID("Notification"));
                    alert.setContent("ALERTE: " + content);
                    send(alert);
                }

            } else {
                block();
            }
        }
    });
}

}
