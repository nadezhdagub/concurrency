package ExacutorsSample;

import java.util.concurrent.Executor;

public class SpaceshipLauncher {

    private Executor as = new Executor() {
        @Override
        public void execute(Runnable spaceship) {
            Thread t = new Thread(spaceship);
            t.start();
        }
    };

    public void launch(Spaceship spaceship){
        as.execute(spaceship);
   }
}
