package agents;

import gui.RpmGUI;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class NotificationAgent extends Agent {
@Override
protected void setup() {
    System.out.println(getLocalName() + " démarré");

    addBehaviour(new CyclicBehaviour(this) {
        @Override
        public void action() {
            ACLMessage msg = receive();
            if (msg != null) {
                String content = msg.getContent();
                System.out.println("ENVOI ALERTE: " + content);
                RpmGUI.getInstance().appendLog("🚨 Alerte envoyée: " + content);
            } else {
                block();
            }
        }
    });
}

}
