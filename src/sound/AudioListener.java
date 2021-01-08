package sound;

import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineEvent.Type;

public class AudioListener implements LineListener {
    public boolean done = false;
    @Override public synchronized void update(LineEvent event) {
      Type eventType = event.getType();
      if (eventType == Type.STOP || eventType == Type.CLOSE) {
        done = true;
        notifyAll();
      }
    }
    public synchronized void waitUntilDone() throws InterruptedException {
      while (!done) { wait(); }
    }
  }
