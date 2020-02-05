package app;

import java.awt.image.BufferedImage;

import app.utility.GifDecoder;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Splash {

    static Scene splash;
    static Rectangle rect = new Rectangle();
    final private Pane pane;
    final private Transition ani;

    public Splash() {
        pane = new Pane();
        pane.setId("splashPane");
        pane.getStylesheets().add(this.getClass().getResource("application.css").toExternalForm());
        pane.setMaxSize(200, 200);
        pane.setMinSize(200, 200);
        pane.setPrefSize(200, 200);
        
        splash = new Scene(pane);
        splash.setFill(Color.TRANSPARENT);
        ani = new AnimatedGif(getClass().getResource("utility/gifLoading.gif").toExternalForm(), 1000);
    }

    public void show() {
    	
        ani.setCycleCount(6);
        ani.play();
        ((AnimatedGif) ani).getView().setX(30);
        ((AnimatedGif) ani).getView().setY(150);
        ((AnimatedGif) ani).getView().setFitWidth(140);
        ((AnimatedGif) ani).getView().setFitHeight(10);
        Image image = new Image("@../../res/imgs/logo2.png");
        ImageView iv = new ImageView(image);
        iv.setFitWidth(140);
        iv.setFitHeight(140);
        iv.setX(30);
        iv.setY(30);
        //now adding everything to position, opening the stage, start the animation
        pane.getChildren().addAll(rect, iv, ((Animation)ani ).getView());

        
    }

    public Scene getSplashScene() {
        return splash;
    }

    public Transition getTransition() {
        return ani;
    }
    
    public class AnimatedGif extends Animation {

        public AnimatedGif( String filename, double durationMs) {

            GifDecoder d = new GifDecoder();
            d.read( filename);

            Image[] sequence = new Image[ d.getFrameCount()];
            for( int i=0; i < d.getFrameCount(); i++) {

                WritableImage wimg = null;
                BufferedImage bimg = d.getFrame(i);
                sequence[i] = SwingFXUtils.toFXImage( bimg, wimg);

            }

            super.init( sequence, durationMs);
        }

    }
    
    public class Animation extends Transition {

        private ImageView imageView;
        private int count;

        private int lastIndex;

        private Image[] sequence;

        private Animation() {
        }

        public Animation( Image[] sequence, double durationMs) {
            init( sequence, durationMs);
        }

        private void init( Image[] sequence, double durationMs) {
            this.imageView = new ImageView(sequence[0]);
            this.sequence = sequence;
            this.count = sequence.length;

            setCycleCount(1);
            setCycleDuration(Duration.millis(durationMs));
            setInterpolator(Interpolator.LINEAR);

        }

        protected void interpolate(double k) {

            final int index = Math.min((int) Math.floor(k * count), count - 1);
            if (index != lastIndex) {
                imageView.setImage(sequence[index]);
                lastIndex = index;
            }

        }

        public ImageView getView() {
            return imageView;
        }

    }
}
