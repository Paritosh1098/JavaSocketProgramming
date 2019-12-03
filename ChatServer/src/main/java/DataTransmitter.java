import java.util.Iterator;
import java.util.Map;
import java.util.Queue;

public class DataTransmitter implements Runnable {

    Queue<String> queueOfMessages;
    volatile Map<String, ClientConnection> socketMap;

    public DataTransmitter( Map<String, ClientConnection> socketMap, Queue<String> queueOfMessages) {
        this.queueOfMessages = queueOfMessages;
        this.socketMap = socketMap;
    }

    /**
     * Read the queue and write to the socket
     */
    public void run() {
        while(true) {

            //wait if queue is empty
            while(queueOfMessages.isEmpty()){
//                try {
//                    Thread.sleep(2000);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }


            String message = queueOfMessages.remove();
            String destination = message.split(":")[0];

            if(destination.equals("DISCONNECT")) {
                //initiate a disconnect for everyone

                Iterator iterator = socketMap.keySet().iterator();
                while(iterator.hasNext()) {
                     socketMap.get(iterator.next()).out.println(message);
                }

            }
             else if(socketMap.containsKey(destination)){
                String outM = message.substring(destination.length()+1,message.length());
                socketMap.get(destination).out.println(outM);
                System.out.println("Written to client-" + destination+ ":" + outM);

            } else {
                System.out.println("Discarding message :" + message);
            }

        }
    }
}
