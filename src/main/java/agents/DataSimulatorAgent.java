package agents;

import java.util.Random;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

public class DataSimulatorAgent extends Agent {

    @Override
    protected void setup() {
        System.out.println(getLocalName() + " démarré");

        addBehaviour(new TickerBehaviour(this, 5000) { // toutes les 5 secondes

            private final Random rand = new Random();

            @Override
            protected void onTick() {
                int pulse = 60 + rand.nextInt(100); // simulate 60-160 bpm

                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                msg.addReceiver(getAID("MonitoringAgent"));
                msg.setContent("Pouls: " + pulse + (pulse > 120 ? " CRITIQUE" : ""));
                send(msg);
            }
        });
    }
}
